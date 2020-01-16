package ru.r2cloud.jradio.meteor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.imageio.plugins.jpeg.JPEGQTable;

import org.jtransforms.dct.DoubleDCT_2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.lrpt.Packet;

public class MeteorImagePacket implements Iterator<int[]> {

	private static final Logger LOG = LoggerFactory.getLogger(MeteorImagePacket.class);
	private static final int TWO_BYTES_POSSIBLE_VALUES = (int) (Math.pow(2, 8) * Math.pow(2, 8));
	private static final int BIT_IN_TWO_BYTES = 16;

	private final Packet packet;
	private final int mcuNumber;
	private final byte quantTableIndex;
	private final byte huffmanIndexDc;
	private final byte huffmanIndexAc;
	private final int qualityMarker;
	private final byte qualityValue;

	private double previousDc = Double.NaN;
	private int currentBitIndex = 0;
	private int currentMcu = 0;
	private int[] currentPixels = new int[64];
	private int[] worksheetQuantizationTable;
	private final double[] dct = new double[8 * 8];
	private boolean hasNext = false;

	private static final int MAX_MCU_PER_PACKET = 14;
	// used to convert from zigzag matrix to plain dct
	private static final byte[] zigzag_indexes = new byte[] { 0, 1, 5, 6, 14, 15, 27, 28, 2, 4, 7, 13, 16, 26, 29, 42, 3, 8, 12, 17, 25, 30, 41, 43, 9, 11, 18, 24, 31, 40, 44, 53, 10, 19, 23, 32, 39, 45, 52, 54, 20, 22, 33, 38, 46, 51, 55, 60, 21, 34, 37, 47, 50, 56, 59, 61, 35, 36, 48, 49, 57, 58, 62, 63 };

	// mapping between codeword and AcCode
	// spatial table, stores less AcCode objects than 65536
	private static AcCode[] acCodes;
	private static DcCode[] dcCodes;
	// mapping between all possible 2bytes and the codeword. Saves 12 bitwise shift and compare operations
	private static AcCode[] acLookup = new AcCode[TWO_BYTES_POSSIBLE_VALUES];
	// lookup table for max 2 bytes. category lookup table has max 9 bits (i.e. 8 bit + 1 bit)
	// it saves us 12 bitwise shift and compare operations (worst case scenario)
	private static int[] dcLookup = new int[TWO_BYTES_POSSIBLE_VALUES];
	private static final DoubleDCT_2D DCTTransform = new DoubleDCT_2D(8, 8);

	static {
		dcCodes = createTable(JPEGHuffmanTable.StdDCLuminance);
		Map<Integer, DcCode> dcIndex = indexByCode(dcCodes);
		acCodes = createAcTable(JPEGHuffmanTable.StdACLuminance);
		Map<Integer, AcCode> acIndex = indexByCode(acCodes);

		for (int i = 0; i < TWO_BYTES_POSSIBLE_VALUES; i++) {
			AcCode acCode = findCode(i, acIndex);
			if (acCode != null) {
				acLookup[i] = acCode;
			}
			DcCode code = findCode(i, dcIndex);
			// shouldn't happen. defensive check
			if (code != null) {
				dcLookup[i] = code.getCategory();
			}
		}
	}

	public MeteorImagePacket(Packet packet) {
		this.packet = packet;
		byte[] data = packet.getUserData();
		mcuNumber = (data[0] & 0xFF);
		quantTableIndex = data[1];
		huffmanIndexDc = (byte) ((data[2] & 0xFF) >> 4);
		huffmanIndexAc = (byte) (data[2] & 0x0F);
		qualityMarker = (data[3] & 0xFF) << 8 | (data[4] & 0xFF);
		qualityValue = data[5];
		currentBitIndex = 6 * 8;
	}

	@Override
	public boolean hasNext() {
		hasNext = hasNextInternal();
		return hasNext;
	}

	private boolean hasNextInternal() {
		if (currentMcu >= MAX_MCU_PER_PACKET) {
			return false;
		}
		// init table once lazily
		if (worksheetQuantizationTable == null) {
			worksheetQuantizationTable = createWorksheetQuantizationTable(qualityValue);
		}

		double[] zigzagDct = new double[dct.length];

		int dcCategory = dcLookup[getNext(BIT_IN_TWO_BYTES)];
		if (dcCategory == -1) {
			LOG.info("invalid dc category");
			return false;
		}
		int codeWordLength = dcCodes[dcCategory].getCodeLength();
		currentBitIndex += codeWordLength;
		int dcBitmask = getNext(dcCategory);
		// dc category also defines number of bits in the diff value
		currentBitIndex += dcCategory;

		zigzagDct[0] = mapBitmaskToValue(dcCategory, dcBitmask);
		// for the first MCU previous dc should be 0
		if (!Double.isNaN(previousDc)) {
			zigzagDct[0] += previousDc;
		}
		previousDc = zigzagDct[0];

		// decode AC
		for (int i = 1; i < 64; i++) {
			AcCode code = acLookup[getNext(BIT_IN_TWO_BYTES)];
			if (code == null) {
				LOG.info("invalid ac codeword");
				return false;
			}
			currentBitIndex += code.getCodeLength();
			// EOB
			if (code.getRun() == 0 && code.getCategory() == 0) {
				break;
			}
			i += code.getRun();
			// not ZRL
			if (code.getCategory() != 0) {
				int acBitmask = getNext(code.getCategory());
				currentBitIndex += code.getCategory();
				zigzagDct[i] = mapBitmaskToValue(code.getCategory(), acBitmask);
			}
		}

		for (int i = 0; i < zigzagDct.length; i++) {
			dct[i] = zigzagDct[zigzag_indexes[i]] * worksheetQuantizationTable[i];
		}
		DCTTransform.inverse(dct, true);
		for (int i = 0; i < dct.length; i++) {
			currentPixels[i] = (int) (Math.round(dct[i] + 128));
			if (currentPixels[i] < 0) {
				currentPixels[i] = 0;
			} else if (currentPixels[i] > 255) {
				currentPixels[i] = 255;
			}
		}
		currentMcu++;
		return true;
	}

	private int getNext(int numberOfBits) {
		int result = 0;
		for (int i = 0; i < numberOfBits; i++) {
			int bitIndex = currentBitIndex + i;
			int currentByteIndex = bitIndex >> 3;
			// peeking into next 16 bits might overflow byte array
			// this is fine, since not all bits will be used for matching codeword
			// stuff the overflow with zeroes
			if (currentByteIndex >= packet.getUserData().length) {
				result = result << 1;
				continue;
			}
			int bit;
			if (((packet.getUserData()[currentByteIndex] & 0xFF) & (1 << (7 - (bitIndex & 7)))) != 0) {
				bit = 1;
			} else {
				bit = 0;
			}
			result = (result << 1) | bit;
		}
		return result;
	}

	private static int mapBitmaskToValue(int dcCategory, int bitmask) {
		boolean startWith1 = (bitmask >> (dcCategory - 1)) != 0;
		int maxval = (1 << dcCategory) - 1;
		if (startWith1) {
			return bitmask;
		} else {
			return bitmask - maxval;
		}
	}

	private static int[] createWorksheetQuantizationTable(byte qualityValue) {
		float compressionRatio;
		if (qualityValue > 20 && qualityValue < 50) {
			compressionRatio = 5000.0f / qualityValue;
		} else {
			compressionRatio = 200.0f - 2 * qualityValue;
		}
		int[] result = new int[JPEGQTable.K1Luminance.getTable().length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Math.round(compressionRatio / 100 * JPEGQTable.K1Luminance.getTable()[i]);
			if (result[i] < 1) {
				result[i] = 1;
			}
		}
		return result;
	}

	@Override
	public int[] next() {
		if (!hasNext) {
			throw new NoSuchElementException();
		}
		return currentPixels;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public Packet getPacket() {
		return packet;
	}

	public int getMcuNumber() {
		return mcuNumber;
	}

	public byte getQuantTableIndex() {
		return quantTableIndex;
	}

	public byte getHuffmanIndexDc() {
		return huffmanIndexDc;
	}

	public byte getHuffmanIndexAc() {
		return huffmanIndexAc;
	}

	public int getQualityMarker() {
		return qualityMarker;
	}

	public byte getQualityValue() {
		return qualityValue;
	}

	private static <T extends DcCode> T findCode(int twoByteIndex, Map<Integer, T> index) {
		// iterate from shorter codewords to longer
		for (int j = BIT_IN_TWO_BYTES; j >= 0; j--) {
			int codeword = twoByteIndex >> j;
			T code = index.get(codeword);
			if (code == null) {
				continue;
			}
			// make sure we index codeword of the right length
			if (code.getCodeLength() != BIT_IN_TWO_BYTES - j) {
				continue;
			}
			return code;
		}
		return null;
	}

	private static <T extends DcCode> Map<Integer, T> indexByCode(T[] list) {
		Map<Integer, T> result = new HashMap<>();
		for (T cur : list) {
			result.put(cur.getCodeword(), cur);
		}
		return result;
	}

	private static DcCode[] createTable(JPEGHuffmanTable table) {
		List<DcCode> result = new ArrayList<>();
		int code = 0;
		int lengthIndex = 0;
		for (int i = 0; i < table.getLengths().length; i++) {
			for (int j = 0; j < table.getLengths()[i]; j++) {
				DcCode cur = new DcCode();
				cur.setCodeLength(i + 1);
				cur.setCodeword(code);
				cur.setCategory(table.getValues()[lengthIndex]);
				result.add(cur);
				code++;
				lengthIndex++;
			}
			code <<= 1;
		}
		return result.toArray(new DcCode[0]);
	}

	private static AcCode[] createAcTable(JPEGHuffmanTable table) {
		List<AcCode> result = new ArrayList<>();
		int code = 0;
		int lengthIndex = 0;
		for (int i = 0; i < table.getLengths().length; i++) {
			for (int j = 0; j < table.getLengths()[i]; j++) {
				int runLengthAndCategory = table.getValues()[lengthIndex];
				AcCode cur = new AcCode();
				cur.setCodeLength(i + 1);
				cur.setCodeword(code);
				cur.setRun(runLengthAndCategory >> 4);
				cur.setCategory(runLengthAndCategory & 0xF);
				result.add(cur);
				code++;
				lengthIndex++;
			}
			code <<= 1;
		}
		return result.toArray(new AcCode[0]);
	}

}
