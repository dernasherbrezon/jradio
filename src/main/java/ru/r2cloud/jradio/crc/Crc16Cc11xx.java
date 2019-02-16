package ru.r2cloud.jradio.crc;

//Code taken from CRC app note: http://www.ti.com/lit/an/swra111d/swra111d.pdf
public class Crc16Cc11xx {

	public static int calculate(byte[] data, int from, int length) {
		int crc = 0xFFFF;
		for (int i = from; i < length; i++) {
			crc = calcStep(data[i] & 0xFF, crc);
		}
		return crc;
	}

	public static int calculate(byte[] data) {
		return calculate(data, 0, data.length);
	}

	private static int calcStep(int cur, int crc) {
		int poly = 0x8005;
		for (int i = 0; i < 8; i++) {
			if ((((crc & 0x8000) >> 8) ^ (cur & 0x80)) > 0) {
				crc = (crc << 1) ^ poly;
			} else {
				crc = (crc << 1);
			}
			crc = crc & 0xFFFF;
			cur <<= 1;
		}
		return crc;
	}
	
	private Crc16Cc11xx() {
		//do nothing
	}
}
