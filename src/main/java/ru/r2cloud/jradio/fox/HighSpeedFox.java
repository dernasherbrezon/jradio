package ru.r2cloud.jradio.fox;

import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class HighSpeedFox extends BeaconSource<Fox1DBeacon> {

	public static final int HIGH_SPEED_FRAME_SIZE = 5272;
	private static final int INTERLEAVING = 21;
	private static final int PAYLOAD_SIZE = 4600;
	private static final int MAX_ERASURES = 15;

	private final byte[][] rsBuffers = new byte[INTERLEAVING][255];
	private final int[] numberOfErasures = new int[INTERLEAVING];
	private final int[][] erasurePositions = new int[INTERLEAVING][255];

	public HighSpeedFox(MessageInput input) {
		super(input);
	}

	@Override
	protected Fox1DBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		int currentRsBuffer = 0;
		int currentIndex = 0;
		int totalBytesProcessed = 0;
		Arrays.fill(numberOfErasures, 0);
		for (int i = 0; i < erasurePositions.length; i++) {
			Arrays.fill(erasurePositions[i], 0);
			Arrays.fill(rsBuffers[i], (byte) 0);
		}
		for (int i = 0; i < raw.length / 10; i++) {
			int cur = 0;
			for (int j = 0; j < 10; j++) {
				cur += ((raw[i * 10 + j] << (10 - j - 1)));
			}
			byte curByte = -1;
			try {
				curByte = Code8b10b.decode(cur);
			} catch (UncorrectableException e) {
				// calculate erasures per interleaved RS codeword
				if (numberOfErasures[currentRsBuffer] < MAX_ERASURES) {
					erasurePositions[currentRsBuffer][numberOfErasures[currentRsBuffer]] = currentIndex;
					numberOfErasures[currentRsBuffer]++;
				} else {
					throw e;
				}
			}

			// the below is about how to fit 4600 bytes into 21 interleaved
			// reed solomon codeword
			// first rs codeword has 3 bytes padding at the beginning
			// the remaining rs codewords have 4 bytes padding
			totalBytesProcessed++;
			if (totalBytesProcessed == PAYLOAD_SIZE + 1) {
				// Reset to the first code word
				currentRsBuffer = 0;
			}

			int finalIndex;
			if (totalBytesProcessed >= PAYLOAD_SIZE + 1) {
				// parity bytes
				finalIndex = 4 + currentIndex;
			} else {
				if (currentRsBuffer == 0) {
					finalIndex = 3 + currentIndex;
				} else {
					finalIndex = 4 + currentIndex;
				}
			}

			rsBuffers[currentRsBuffer][finalIndex] = curByte;

			currentRsBuffer++;
			if (currentRsBuffer >= rsBuffers.length) {
				currentRsBuffer = 0;
				currentIndex++;
			}

		}

		byte[] payload = null;
		for (int i = 0; i < rsBuffers.length; i++) {
			byte[] data = ReedSolomon.CCSDS.decodeData(rsBuffers[i], erasurePositions[i], numberOfErasures[i]);
			// lazily initialize to avoid array creation
			if (payload == null) {
				payload = new byte[PAYLOAD_SIZE];
			}
			int padding;
			if (i == 0) {
				padding = 3;
			} else {
				padding = 4;
			}
			for (int j = padding, dest = 0; j < data.length; j++, dest++) {
				payload[dest * INTERLEAVING + i] = data[j];
			}
		}
		Fox1DBeacon result = new Fox1DBeacon();
		result.readExternal(payload);
		return result;
	}

}