package ru.r2cloud.jradio.crc;

public class Crc16Ccitt {

	public static int calculate(byte[] data) {
		int crc = 0xFFFF;
		for (int i = 0; i < data.length; ++i) {
			crc ^= data[i] << 8;
			for (int j = 0; j < 8; ++j) {
				if ((crc & 0x8000) > 0) {
					crc = (crc << 1) ^ 0x1021;
				} else {
					crc = crc << 1;
				}
			}
		}
		return crc & 0xFFFF;
	}

	private Crc16Ccitt() {
		// do nothing
	}

}
