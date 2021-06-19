package ru.r2cloud.jradio.eseo;

public class Nrzi {

	public static void decode(byte[] data) {
		int previous = 0;
		for (int i = 0; i < data.length; i++) {
			int toCompare = ((data[i] & 0xFF) >> 1) | ((previous & 0x1) << 7);
			previous = data[i] & 0xFF;
			data[i] = (byte) ((data[i] & 0xFF) ^ toCompare ^ 0xFF);
		}
	}

	public static void encode(byte[] data) {
		int previous = 0;
		for (int i = 0; i < data.length; i++) {
			int curByte = 0;
			for (int j = 0; j < 8; j++) {
				int curBit = ((data[i] & 0xFF) >> (7 - j)) & 0x1;
				int outputBit = ~(curBit ^ previous) & 0x1;
				previous = outputBit;
				curByte = curByte << 1;
				curByte = curByte | outputBit;
			}
			data[i] = (byte) curByte;
		}
	}

	private Nrzi() {
		// do nothing
	}

}
