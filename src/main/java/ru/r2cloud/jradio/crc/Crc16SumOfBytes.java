package ru.r2cloud.jradio.crc;

public class Crc16SumOfBytes {

	public static int calculate(byte[] data) {
		return calculate(data, 0, data.length);
	}

	public static int calculate(byte[] data, int offset, int length) {
		short result = 0;
		for (int i = offset; i < (offset + length); i++) {
			result += data[i] & 0xFF;
		}
		return result & 0xFFFF;
	}

	private Crc16SumOfBytes() {
		// do nothing
	}

}
