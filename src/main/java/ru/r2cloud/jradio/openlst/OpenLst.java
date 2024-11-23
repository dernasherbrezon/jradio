package ru.r2cloud.jradio.openlst;

import ru.r2cloud.jradio.blocks.AdditiveScrambler;
import ru.r2cloud.jradio.crc.Crc16Cc11xx;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

// Implementation is based on:
//   Design Note DN504
//   Design Note DN507
// The only difference is checksum. OpenLst include checksum into length, while cc11xx doesn't
public class OpenLst {

	private static final int[] FEC_ENCODE_TABLE = new int[] { 0, 3, 1, 2, 3, 0, 2, 1, 3, 0, 2, 1, 0, 3, 1, 2 };
	// @formatter:off	
	private static final int[][] aTrellisSourceStateLut = new int[][] {
		{0, 4}, // State {0,4} -> State 0
		{0, 4}, // State {0,4} -> State 1
		{1, 5}, // State {1,5} -> State 2
		{1, 5}, // State {1,5} -> State 3
		{2, 6}, // State {2,6} -> State 4
		{2, 6}, // State {2,6} -> State 5
		{3, 7}, // State {3,7} -> State 6
		{3, 7}, // State {3,7} -> State 7
	};
	private static final int[][] aTrellisTransitionOutput = new int[][] {
		{0, 3}, // State {0,4} -> State 0 produces {"00", "11"}
		{3, 0}, // State {0,4} -> State 1 produces {"11", "00"}
		{1, 2}, // State {1,5} -> State 2 produces {"01", "10"}
		{2, 1}, // State {1,5} -> State 3 produces {"10", "01"}
		{3, 0}, // State {2,6} -> State 4 produces {"11", "00"}
		{0, 3}, // State {2,6} -> State 5 produces {"00", "11"}
		{2, 1}, // State {3,7} -> State 6 produces {"10", "01"}
		{1, 2}, // State {3,7} -> State 7 produces {"01", "10"}
	};
	// @formatter:on
	private static final int[] aTrellisTransitionInput = new int[] { 0, 1, 0, 1, 0, 1, 0, 1 };

	private final int[][] nCost = new int[2][8]; // Accumulated path cost
	private final long[][] aPath = new long[2][8]; // Encoder input data (32b window)
	private final boolean scrambling;

	private final byte[] temp = new byte[4];
	private final byte[] chunkTemp = new byte[4];
	private final byte[] dataTemp;
	private final AdditiveScrambler scrambler = new AdditiveScrambler(0x21, 0x1ff, 8, 8);

	// Indices of (last, current) buffer for each iteration
	private int iLastBuf;
	private int iCurrBuf;
	// Number of bits in each path buffer
	private int nPathBits;

	public OpenLst(int dataLength) {
		this(true, dataLength);
	}

	public OpenLst(boolean scrambling, int dataLength) {
		reset();
		this.scrambling = scrambling;
		// might be overkill, but we're dealing with very small messages
		this.dataTemp = new byte[dataLength];
	}

	public byte[] decode(byte[] data) throws UncorrectableException {
		int currentTempIndex = 0;
		for (int i = 0; i < data.length; i += 4) {
			int actualBytes = fecDecode(data, i, 4, chunkTemp, data.length - i);
			for (int j = 0; j < actualBytes; j++) {
				dataTemp[currentTempIndex++] = chunkTemp[j];
			}
		}
		reset();
		if (scrambling) {
			scrambler.shuffle(dataTemp);
		}
		int length = (dataTemp[0] & 0xFF) + 1;
		if (length > currentTempIndex) {
			throw new UncorrectableException("unexpected length: " + length);
		}
		int actual = ((dataTemp[length - 1] & 0xFF) << 8) | (dataTemp[length - 2] & 0xFF);
		int expected = Crc16Cc11xx.calculate(dataTemp, 0, length - 2);
		if (expected != actual) {
			throw new UncorrectableException("crc mismatch");
		}
		byte[] result = new byte[length - 3];
		System.arraycopy(dataTemp, 1, result, 0, result.length);
		return result;
	}

	public byte[] encode(byte[] data) {
		int inputNum = data.length + 1;
		byte[] input = new byte[inputNum + 2 + 2];
		input[0] = (byte) (data.length + 2);
		System.arraycopy(data, 0, input, 1, data.length);

		int crc = Crc16Cc11xx.calculate(input, 0, inputNum);
		input[inputNum++] = (byte) (crc & 0x00FF);
		input[inputNum++] = (byte) (crc >> 8);

		input[inputNum] = 0x0B;
		input[inputNum + 1] = 0x0B;

		if (scrambling) {
			scrambler.shuffle(input);
		}

		int fecNum = 2 * ((inputNum / 2) + 1);
		int fecReg = 0;
		byte[] fec = new byte[fecNum * 2];
		for (int i = 0; i < fecNum; i++) {
			fecReg = (fecReg & 0x700) | (input[i] & 0xFF);
			int fecOutput = 0;
			for (int j = 0; j < 8; j++) {
				fecOutput = (fecOutput << 2) | FEC_ENCODE_TABLE[fecReg >> 7];
				fecReg = (fecReg << 1) & 0x7FF;
			}
			fec[i * 2] = (byte) (fecOutput >> 8);
			fec[i * 2 + 1] = (byte) (fecOutput & 0xFF);
		}

		for (int i = 0; i < fecNum * 2; i += 4) {
			int intOutput = 0;
			for (int j = 0; j < 4 * 4; j++) {
				intOutput = (intOutput << 2) | ((fec[i + (~j & 0x03)] >> (2 * ((j & 0x0C) >> 2))) & 0x03);
			}
			fec[i] = (byte) ((intOutput >> 24) & 0xFF);
			fec[i + 1] = (byte) ((intOutput >> 16) & 0xFF);
			fec[i + 2] = (byte) ((intOutput >> 8) & 0xFF);
			fec[i + 3] = (byte) ((intOutput >> 0) & 0xFF);
		}

		return fec;
	}

	private int fecDecode(byte[] data, int offset, int length, byte[] output, int nRemBytes) {
		if (length != 4 || offset + length > data.length) {
			throw new IllegalArgumentException();
		}
		// De-interleave received data
		for (int iOut = 0; iOut < 4; iOut++) {
			byte dataByte = 0;
			for (int iIn = 3; iIn >= 0; iIn--) {
				dataByte = (byte) ((dataByte << 2) | (((data[iIn + offset] & 0xFF) >> (2 * iOut)) & 0x03));
			}
			temp[iOut] = dataByte;
		}

		// Variables used to hold # Viterbi iterations to run, # bytes output,
		// minimum cost for any destination state, bit index of input symbol
		int nMinCost = 0xFF;
		int iBit = 8 - 2;
		// Process up to 4 bytes of de-interleaved input data, processing one encoder
		// symbol (2b) at a time
		int currentIn = 0;
		int currentOut = 0;
		for (int nIterations = 16; nIterations > 0; nIterations--) {
			int symbol = ((temp[currentIn]) >> iBit) & 0x03;
			// Find minimum cost so that we can normalize costs (only last iteration used)
			nMinCost = 0xFF;
			// Get 2b input symbol (MSB first) and do one iteration of Viterbi decoding
			iBit -= 2;
			if (iBit < 0) {
				iBit = 6;
				currentIn++; // Update pointer to the next byte of received data
			}
			// For each destination state in the trellis, calculate hamming costs for both
			// possible paths into state and
			// select the one with lowest cost.
			for (int iDestState = 0; iDestState < 8; iDestState++) {
				int nCost0;
				int nCost1;
				int iSrcState0;
				int iSrcState1;
				int nInputBit;
				nInputBit = aTrellisTransitionInput[iDestState];
				// Calculate cost of transition from each of the two source states (cost is
				// Hamming difference between
				// received 2b symbol and expected symbol for transition)
				iSrcState0 = aTrellisSourceStateLut[iDestState][0];
				nCost0 = nCost[iLastBuf][iSrcState0];
				nCost0 += hammWeight(symbol ^ aTrellisTransitionOutput[iDestState][0]);
				iSrcState1 = aTrellisSourceStateLut[iDestState][1];
				nCost1 = nCost[iLastBuf][iSrcState1];
				nCost1 += hammWeight(symbol ^ aTrellisTransitionOutput[iDestState][1]);
				// Select transition that gives lowest cost in destination state, copy that
				// source state's path and add
				// new decoded bit
				if (nCost0 <= nCost1) {
					nCost[iCurrBuf][iDestState] = nCost0;
					nMinCost = Math.min(nMinCost, nCost0);
					aPath[iCurrBuf][iDestState] = ((aPath[iLastBuf][iSrcState0] << 1) | nInputBit);
				} else {
					nCost[iCurrBuf][iDestState] = nCost1;
					nMinCost = Math.min(nMinCost, nCost1);
					aPath[iCurrBuf][iDestState] = ((aPath[iLastBuf][iSrcState1] << 1) | nInputBit);
				}
			}
			nPathBits++;
			// If trellis history is sufficiently long, output a byte of decoded data
			if (nPathBits == 32) {
				output[currentOut] = (byte) ((aPath[iCurrBuf][0] >> 24) & 0xFF);
				currentOut++;
				nPathBits -= 8;
				nRemBytes--;
			}
			// After having processed 3-symbol trellis terminator, flush out remaining data
			if ((nRemBytes <= 3) && (nPathBits == ((8 * nRemBytes) + 3))) {
				while (nPathBits >= 8) {
					output[currentOut] = (byte) ((aPath[iCurrBuf][0] >> (nPathBits - 8)) & 0xFF);
					currentOut++;
					nPathBits -= 8;
				}
				return currentOut;
			}
			// Swap current and last buffers for next iteration
			iLastBuf = (iLastBuf + 1) % 2;
			iCurrBuf = (iCurrBuf + 1) % 2;
		}
		// Normalize costs so that minimum cost becomes 0
		for (int iState = 0; iState < 8; iState++) {
			nCost[iLastBuf][iState] -= nMinCost;
		}
		return currentOut;
	}

	private void reset() {
		// Initialize variables at start of packet (and return without doing any more)
		for (int i = 0; i < 8; i++) {
			nCost[0][i] = 100;
			nCost[1][i] = 0;
			aPath[0][i] = 0;
			aPath[1][i] = 0;
		}
		iLastBuf = 0;
		iCurrBuf = 1;
		nPathBits = 0;
	}

	private static int hammWeight(int a) {
		a = ((a & 0xAA) >> 1) + (a & 0x55);
		a = ((a & 0xCC) >> 2) + (a & 0x33);
		a = ((a & 0xF0) >> 4) + (a & 0x0F);
		return a;
	}

}
