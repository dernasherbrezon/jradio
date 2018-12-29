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

}
