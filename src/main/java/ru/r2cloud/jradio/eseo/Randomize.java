package ru.r2cloud.jradio.eseo;

public class Randomize {

	public static void shuffle(byte[] data) {
		int register = 0;
		for (int i = 0; i < data.length; i++) {
			int outputByte = 0;
			for (int j = 0; j < 8; j++) {
				int inputBit = (data[i] >> (7 - j)) & 0x1;
				int register12th = (register >> 11) & 0x1;
				int register17th = (register >> 16) & 0x1;
				outputByte = outputByte << 1;
				outputByte = outputByte | (inputBit ^ register12th ^ register17th);
				register = (register << 1) & 0x1FFFF;
				register = register | inputBit;
			}
			data[i] = (byte) outputByte;
		}
	}

	private Randomize() {
		// do nothing
	}

}
