package ru.r2cloud.jradio.util;

public class Deinterleave {

	public static byte[] deinterleaveBits(byte[] data, int wordLength, int numberOfWords) {
		if (wordLength > 8 || wordLength < 1) {
			throw new IllegalArgumentException("word length is expected: [1;8]. got: " + wordLength);
		}
		if (numberOfWords < 0 || numberOfWords > data.length * 8) {
			throw new IllegalArgumentException("unsupported number of words. got: " + numberOfWords);
		}
		byte[] deinterleaved = new byte[numberOfWords];
		int targetBit = 0;
		for (int j = 0; j < data.length; j++) {
			for (int i = 0; i < 8; i++) {
				int sourceBit = (((data[j] >> (7 - i))) & 0x1);
				int dIndex = targetBit % numberOfWords;
				int dBit = targetBit / numberOfWords;
				deinterleaved[dIndex] |= sourceBit << (wordLength - dBit - 1);

				targetBit++;
			}
		}
		return deinterleaved;
	}

	public static byte[] deinterleaveBitsUnpacked(byte[] data, int offset, int cols, int rows) {
		byte[] result = new byte[cols * rows];
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				result[j * cols + i] = data[offset + i * rows + j];
			}
		}
		return result;
	}

}
