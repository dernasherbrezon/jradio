package ru.r2cloud.jradio.util;

public class Deinterleave {

	public static byte[] deinterleaveBits(byte[] data) {
		byte[] deinterleaved = new byte[data.length];
		for (int j = 0; j < data.length; j++) {
			for (int i = 0; i < 8; i++) {
				int sourceBit = (((data[j] >> (7 - i))) & 0x1);
				int currentBitIndex = j * 8 + i;
				int dIndex = currentBitIndex % deinterleaved.length;
				int dBit = currentBitIndex / deinterleaved.length;
				deinterleaved[dIndex] |= sourceBit << (7 - dBit);
			}
		}
		return deinterleaved;
	}

}
