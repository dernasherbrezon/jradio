package ru.r2cloud.jradio.snet;

public class Crc5Snet {

	public static int calculateCrc5(byte[] data) {
		int crc = 0x1f;
		for (int i = data.length / 8 - 1; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				int c = crc & 0x10;
				c >>= 4;
				crc <<= 1;
				int currentBit = data[i * 8 + j];
				if (c != currentBit) {
					crc ^= 0x15;
				}
				crc &= 0x1F;
			}
		}
		return crc;
	}
	
	private Crc5Snet() {
		// do nothing
	}
}
