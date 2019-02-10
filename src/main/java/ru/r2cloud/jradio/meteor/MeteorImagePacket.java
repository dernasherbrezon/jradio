package ru.r2cloud.jradio.meteor;

import java.util.Iterator;

import org.jtransforms.dct.DoubleDCT_2D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.lrpt.Packet;

public class MeteorImagePacket implements Iterator<int[]> {

	private static final Logger LOG = LoggerFactory.getLogger(MeteorImagePacket.class);

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

	private final static int MAX_MCU_PER_PACKET = 14;
	private final static byte[] initial_quantization_table = new byte[] { 16, 11, 10, 16, 24, 40, 51, 61, 12, 12, 14, 19, 26, 58, 60, 55, 14, 13, 16, 24, 40, 57, 69, 56, 14, 17, 22, 29, 51, 87, 80, 62, 18, 22, 37, 56, 68, 109, 103, 77, 24, 35, 55, 64, 81, 104, 113, 92, 49, 64, 78, 87, 103, 121, 120, 101, 72, 92, 95, 98, 112, 100, 103, 99 };
	// used to convert from zigzag matrix to plain dct
	private final static byte[] zigzag_indexes = new byte[] { 0, 1, 5, 6, 14, 15, 27, 28, 2, 4, 7, 13, 16, 26, 29, 42, 3, 8, 12, 17, 25, 30, 41, 43, 9, 11, 18, 24, 31, 40, 44, 53, 10, 19, 23, 32, 39, 45, 52, 54, 20, 22, 33, 38, 46, 51, 55, 60, 21, 34, 37, 47, 50, 56, 59, 61, 35, 36, 48, 49, 57, 58, 62, 63 };

	// mapping between codeword and AcCode
	// spatial table, stores less AcCode objects than 65536
	private static AcCode[] acCodes = new AcCode[162];
	// mapping between all possible 2bytes and the codeword. Saves 12 bitwise shift and compare operations
	private static int[] ac_lookup = new int[65536];
	// lookup table for max 2 bytes. category lookup table has max 9 bits (i.e. 8 bit + 1 bit)
	// it saves us 12 bitwise shift and compare operations (worst case scenario)
	private static int[] dc_lookup = new int[65536];
	// map category (array index) to the number of bits in codeword. used to skip detected codeword and extract diff value
	private static int[] codeLengthLookup = new int[] { 2, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9 };

	private static final DoubleDCT_2D DCT = new DoubleDCT_2D(8, 8);

	static {
		setupAcCodes();

		for (int i = 0; i < 65536; i++) {
			ac_lookup[i] = map2BytesToAcCodeword(i);
			dc_lookup[i] = map2BytesToDcCategory(i);
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
		if (currentMcu >= MAX_MCU_PER_PACKET) {
			return false;
		}
		// init table once lazily
		if (worksheetQuantizationTable == null) {
			worksheetQuantizationTable = createWorksheetQuantizationTable(qualityValue);
		}

		double[] zigzagDct = new double[dct.length];

		int dcCategory = dc_lookup[getNext(16)];
		if (dcCategory == -1) {
			LOG.info("invalid dc category");
			return false;
		}
		int codeWordLength = codeLengthLookup[dcCategory];
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
			int index = ac_lookup[getNext(16)];
			if (index == -1) {
				LOG.info("invalid ac codeword");
				return false;
			}
			AcCode code = acCodes[index];
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
		DCT.inverse(dct, true);
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
				result = (result << 1) | 0;
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
		int[] result = new int[initial_quantization_table.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Math.round(compressionRatio / 100 * initial_quantization_table[i]);
			if (result[i] < 1) {
				result[i] = 1;
			}
		}
		return result;
	}

	@Override
	public int[] next() {
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

	// used to build dc lookup table
	// for every possible 2 bytes of input, try to find category
	// For example:
	// input bytes - 1101 1100
	// 110 should be mapped to 5 and ignore the rest of the input (1 1100). i.e. for every possible reminder (1 1100, 1 1101, 0 0100) output 5.
	private static int map2BytesToDcCategory(int codeword) {
		if ((codeword >> 14) == 0) {
			return 0;
		}

		switch (codeword >> 13) {
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 3;
		case 5:
			return 4;
		case 6:
			return 5;
		default:
			break;
		}

		if ((codeword >> 12) == 0x00E) {
			return 6;
		} else if ((codeword >> 11) == 0x01E) {
			return 7;
		} else if ((codeword >> 10) == 0x03E) {
			return 8;
		} else if ((codeword >> 9) == 0x07E) {
			return 9;
		} else if ((codeword >> 8) == 0x0FE) {
			return 10;
		} else if ((codeword >> 7) == 0x1FE) {
			return 11;
		} else {
			return -1;
		}
	}

	// used to build dc lookup table
	// for every possible 2 bytes of input, try to find codeword
	// For example:
	// input bytes - 1100 1100
	// 1100 should be mapped to 1,1 and ignore the rest of the input (1100). i.e. for every possible reminder (1100, 1101, 0100) output 1,1.
	private static int map2BytesToAcCodeword(int twobytes) {
		for (int i = 0; i < acCodes.length; i++) {
			if ((twobytes >> (16 - acCodes[i].getCodeLength()) & acCodes[i].getMask()) == acCodes[i].getCodeword()) {
				return i;
			}
		}
		return -1;
	}

	private static void setupAcCodes() {
		acCodes[0] = new AcCode(0, 1, 2, 0, 3);
		acCodes[1] = new AcCode(0, 2, 2, 1, 3);
		acCodes[2] = new AcCode(0, 3, 3, 4, 7);
		acCodes[3] = new AcCode(0, 0, 4, 10, 15);
		acCodes[4] = new AcCode(0, 4, 4, 11, 15);
		acCodes[5] = new AcCode(1, 1, 4, 12, 15);
		acCodes[6] = new AcCode(0, 5, 5, 26, 31);
		acCodes[7] = new AcCode(1, 2, 5, 27, 31);
		acCodes[8] = new AcCode(2, 1, 5, 28, 31);
		acCodes[9] = new AcCode(3, 1, 6, 58, 63);
		acCodes[10] = new AcCode(4, 1, 6, 59, 63);
		acCodes[11] = new AcCode(0, 6, 7, 120, 127);
		acCodes[12] = new AcCode(1, 3, 7, 121, 127);
		acCodes[13] = new AcCode(5, 1, 7, 122, 127);
		acCodes[14] = new AcCode(6, 1, 7, 123, 127);
		acCodes[15] = new AcCode(0, 7, 8, 248, 255);
		acCodes[16] = new AcCode(2, 2, 8, 249, 255);
		acCodes[17] = new AcCode(7, 1, 8, 250, 255);
		acCodes[18] = new AcCode(1, 4, 9, 502, 511);
		acCodes[19] = new AcCode(3, 2, 9, 503, 511);
		acCodes[20] = new AcCode(8, 1, 9, 504, 511);
		acCodes[21] = new AcCode(9, 1, 9, 505, 511);
		acCodes[22] = new AcCode(10, 1, 9, 506, 511);
		acCodes[23] = new AcCode(0, 8, 10, 1014, 1023);
		acCodes[24] = new AcCode(2, 3, 10, 1015, 1023);
		acCodes[25] = new AcCode(4, 2, 10, 1016, 1023);
		acCodes[26] = new AcCode(11, 1, 10, 1017, 1023);
		acCodes[27] = new AcCode(12, 1, 10, 1018, 1023);
		acCodes[28] = new AcCode(1, 5, 11, 2038, 2047);
		acCodes[29] = new AcCode(5, 2, 11, 2039, 2047);
		acCodes[30] = new AcCode(13, 1, 11, 2040, 2047);
		acCodes[31] = new AcCode(15, 0, 11, 2041, 2047);
		acCodes[32] = new AcCode(2, 4, 12, 4084, 4095);
		acCodes[33] = new AcCode(3, 3, 12, 4085, 4095);
		acCodes[34] = new AcCode(6, 2, 12, 4086, 4095);
		acCodes[35] = new AcCode(7, 2, 12, 4087, 4095);
		acCodes[36] = new AcCode(8, 2, 15, 32704, 32767);
		acCodes[37] = new AcCode(0, 9, 16, 65410, 65535);
		acCodes[38] = new AcCode(0, 10, 16, 65411, 65535);
		acCodes[39] = new AcCode(1, 6, 16, 65412, 65535);
		acCodes[40] = new AcCode(1, 7, 16, 65413, 65535);
		acCodes[41] = new AcCode(1, 8, 16, 65414, 65535);
		acCodes[42] = new AcCode(1, 9, 16, 65415, 65535);
		acCodes[43] = new AcCode(1, 10, 16, 65416, 65535);
		acCodes[44] = new AcCode(2, 5, 16, 65417, 65535);
		acCodes[45] = new AcCode(2, 6, 16, 65418, 65535);
		acCodes[46] = new AcCode(2, 7, 16, 65419, 65535);
		acCodes[47] = new AcCode(2, 8, 16, 65420, 65535);
		acCodes[48] = new AcCode(2, 9, 16, 65421, 65535);
		acCodes[49] = new AcCode(2, 10, 16, 65422, 65535);
		acCodes[50] = new AcCode(3, 4, 16, 65423, 65535);
		acCodes[51] = new AcCode(3, 5, 16, 65424, 65535);
		acCodes[52] = new AcCode(3, 6, 16, 65425, 65535);
		acCodes[53] = new AcCode(3, 7, 16, 65426, 65535);
		acCodes[54] = new AcCode(3, 8, 16, 65427, 65535);
		acCodes[55] = new AcCode(3, 9, 16, 65428, 65535);
		acCodes[56] = new AcCode(3, 10, 16, 65429, 65535);
		acCodes[57] = new AcCode(4, 3, 16, 65430, 65535);
		acCodes[58] = new AcCode(4, 4, 16, 65431, 65535);
		acCodes[59] = new AcCode(4, 5, 16, 65432, 65535);
		acCodes[60] = new AcCode(4, 6, 16, 65433, 65535);
		acCodes[61] = new AcCode(4, 7, 16, 65434, 65535);
		acCodes[62] = new AcCode(4, 8, 16, 65435, 65535);
		acCodes[63] = new AcCode(4, 9, 16, 65436, 65535);
		acCodes[64] = new AcCode(4, 10, 16, 65437, 65535);
		acCodes[65] = new AcCode(5, 3, 16, 65438, 65535);
		acCodes[66] = new AcCode(5, 4, 16, 65439, 65535);
		acCodes[67] = new AcCode(5, 5, 16, 65440, 65535);
		acCodes[68] = new AcCode(5, 6, 16, 65441, 65535);
		acCodes[69] = new AcCode(5, 7, 16, 65442, 65535);
		acCodes[70] = new AcCode(5, 8, 16, 65443, 65535);
		acCodes[71] = new AcCode(5, 9, 16, 65444, 65535);
		acCodes[72] = new AcCode(5, 10, 16, 65445, 65535);
		acCodes[73] = new AcCode(6, 3, 16, 65446, 65535);
		acCodes[74] = new AcCode(6, 4, 16, 65447, 65535);
		acCodes[75] = new AcCode(6, 5, 16, 65448, 65535);
		acCodes[76] = new AcCode(6, 6, 16, 65449, 65535);
		acCodes[77] = new AcCode(6, 7, 16, 65450, 65535);
		acCodes[78] = new AcCode(6, 8, 16, 65451, 65535);
		acCodes[79] = new AcCode(6, 9, 16, 65452, 65535);
		acCodes[80] = new AcCode(6, 10, 16, 65453, 65535);
		acCodes[81] = new AcCode(7, 3, 16, 65454, 65535);
		acCodes[82] = new AcCode(7, 4, 16, 65455, 65535);
		acCodes[83] = new AcCode(7, 5, 16, 65456, 65535);
		acCodes[84] = new AcCode(7, 6, 16, 65457, 65535);
		acCodes[85] = new AcCode(7, 7, 16, 65458, 65535);
		acCodes[86] = new AcCode(7, 8, 16, 65459, 65535);
		acCodes[87] = new AcCode(7, 9, 16, 65460, 65535);
		acCodes[88] = new AcCode(7, 10, 16, 65461, 65535);
		acCodes[89] = new AcCode(8, 3, 16, 65462, 65535);
		acCodes[90] = new AcCode(8, 4, 16, 65463, 65535);
		acCodes[91] = new AcCode(8, 5, 16, 65464, 65535);
		acCodes[92] = new AcCode(8, 6, 16, 65465, 65535);
		acCodes[93] = new AcCode(8, 7, 16, 65466, 65535);
		acCodes[94] = new AcCode(8, 8, 16, 65467, 65535);
		acCodes[95] = new AcCode(8, 9, 16, 65468, 65535);
		acCodes[96] = new AcCode(8, 10, 16, 65469, 65535);
		acCodes[97] = new AcCode(9, 2, 16, 65470, 65535);
		acCodes[98] = new AcCode(9, 3, 16, 65471, 65535);
		acCodes[99] = new AcCode(9, 4, 16, 65472, 65535);
		acCodes[100] = new AcCode(9, 5, 16, 65473, 65535);
		acCodes[101] = new AcCode(9, 6, 16, 65474, 65535);
		acCodes[102] = new AcCode(9, 7, 16, 65475, 65535);
		acCodes[103] = new AcCode(9, 8, 16, 65476, 65535);
		acCodes[104] = new AcCode(9, 9, 16, 65477, 65535);
		acCodes[105] = new AcCode(9, 10, 16, 65478, 65535);
		acCodes[106] = new AcCode(10, 2, 16, 65479, 65535);
		acCodes[107] = new AcCode(10, 3, 16, 65480, 65535);
		acCodes[108] = new AcCode(10, 4, 16, 65481, 65535);
		acCodes[109] = new AcCode(10, 5, 16, 65482, 65535);
		acCodes[110] = new AcCode(10, 6, 16, 65483, 65535);
		acCodes[111] = new AcCode(10, 7, 16, 65484, 65535);
		acCodes[112] = new AcCode(10, 8, 16, 65485, 65535);
		acCodes[113] = new AcCode(10, 9, 16, 65486, 65535);
		acCodes[114] = new AcCode(10, 10, 16, 65487, 65535);
		acCodes[115] = new AcCode(11, 2, 16, 65488, 65535);
		acCodes[116] = new AcCode(11, 3, 16, 65489, 65535);
		acCodes[117] = new AcCode(11, 4, 16, 65490, 65535);
		acCodes[118] = new AcCode(11, 5, 16, 65491, 65535);
		acCodes[119] = new AcCode(11, 6, 16, 65492, 65535);
		acCodes[120] = new AcCode(11, 7, 16, 65493, 65535);
		acCodes[121] = new AcCode(11, 8, 16, 65494, 65535);
		acCodes[122] = new AcCode(11, 9, 16, 65495, 65535);
		acCodes[123] = new AcCode(11, 10, 16, 65496, 65535);
		acCodes[124] = new AcCode(12, 2, 16, 65497, 65535);
		acCodes[125] = new AcCode(12, 3, 16, 65498, 65535);
		acCodes[126] = new AcCode(12, 4, 16, 65499, 65535);
		acCodes[127] = new AcCode(12, 5, 16, 65500, 65535);
		acCodes[128] = new AcCode(12, 6, 16, 65501, 65535);
		acCodes[129] = new AcCode(12, 7, 16, 65502, 65535);
		acCodes[130] = new AcCode(12, 8, 16, 65503, 65535);
		acCodes[131] = new AcCode(12, 9, 16, 65504, 65535);
		acCodes[132] = new AcCode(12, 10, 16, 65505, 65535);
		acCodes[133] = new AcCode(13, 2, 16, 65506, 65535);
		acCodes[134] = new AcCode(13, 3, 16, 65507, 65535);
		acCodes[135] = new AcCode(13, 4, 16, 65508, 65535);
		acCodes[136] = new AcCode(13, 5, 16, 65509, 65535);
		acCodes[137] = new AcCode(13, 6, 16, 65510, 65535);
		acCodes[138] = new AcCode(13, 7, 16, 65511, 65535);
		acCodes[139] = new AcCode(13, 8, 16, 65512, 65535);
		acCodes[140] = new AcCode(13, 9, 16, 65513, 65535);
		acCodes[141] = new AcCode(13, 10, 16, 65514, 65535);
		acCodes[142] = new AcCode(14, 1, 16, 65515, 65535);
		acCodes[143] = new AcCode(14, 2, 16, 65516, 65535);
		acCodes[144] = new AcCode(14, 3, 16, 65517, 65535);
		acCodes[145] = new AcCode(14, 4, 16, 65518, 65535);
		acCodes[146] = new AcCode(14, 5, 16, 65519, 65535);
		acCodes[147] = new AcCode(14, 6, 16, 65520, 65535);
		acCodes[148] = new AcCode(14, 7, 16, 65521, 65535);
		acCodes[149] = new AcCode(14, 8, 16, 65522, 65535);
		acCodes[150] = new AcCode(14, 9, 16, 65523, 65535);
		acCodes[151] = new AcCode(14, 10, 16, 65524, 65535);
		acCodes[152] = new AcCode(15, 1, 16, 65525, 65535);
		acCodes[153] = new AcCode(15, 2, 16, 65526, 65535);
		acCodes[154] = new AcCode(15, 3, 16, 65527, 65535);
		acCodes[155] = new AcCode(15, 4, 16, 65528, 65535);
		acCodes[156] = new AcCode(15, 5, 16, 65529, 65535);
		acCodes[157] = new AcCode(15, 6, 16, 65530, 65535);
		acCodes[158] = new AcCode(15, 7, 16, 65531, 65535);
		acCodes[159] = new AcCode(15, 8, 16, 65532, 65535);
		acCodes[160] = new AcCode(15, 9, 16, 65533, 65535);
		acCodes[161] = new AcCode(15, 10, 16, 65534, 65535);
	}

}
