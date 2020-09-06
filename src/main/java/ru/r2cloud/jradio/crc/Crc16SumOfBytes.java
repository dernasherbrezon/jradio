package ru.r2cloud.jradio.crc;

public class Crc16SumOfBytes {

	public static int calculate(byte[] data) {
		short result = 0;
		for (int i = 0; i < data.length; i++) {
			result += data[i] & 0xFF;
		}
		return result;
	}

}
