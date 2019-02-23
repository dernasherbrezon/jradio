package ru.r2cloud.jradio.snet;

public class Crc13Snet {

	public static int calculateCrc13(byte[] pdu) {
		int crc = 0x1FFF;
		for (int i = pdu.length / 8 - 1; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				int c = crc & 0x1000;
				crc <<= 1;
				int currentBit = pdu[i * 8 + j];
				// BUG in the spacecraft. The correct would be c != bit
				if (c > 0 || currentBit > 0) {
					crc ^= 0x1CF5;
				}
				crc &= 0x1FFF;
			}
		}
		return crc;
	}
	
	private Crc13Snet() {
		// do nothing
	}
}
