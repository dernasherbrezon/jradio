package ru.r2cloud.jradio.blocks;

import ru.r2cloud.jradio.util.Lfsr;

public class AdditiveScrambler {

	private final Lfsr lfsr;
	private final int bitsPerByte;

	public AdditiveScrambler(int mask, int seed, int length, int bitsPerByte) {
		if (bitsPerByte < 1 || bitsPerByte > 8) {
			throw new IllegalArgumentException("invalid bits per byte: " + bitsPerByte + " expected: [1;8]");
		}
		lfsr = new Lfsr(mask, seed, length);
		this.bitsPerByte = bitsPerByte;
	}

	public void shuffle(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			int scrambleByte = 0x00;
			for (int k = 0; k < bitsPerByte; k++) {
				scrambleByte ^= (lfsr.nextBit() << k);
			}
			data[i] = (byte) ((data[i] & 0xFF) ^ scrambleByte);
		}
		lfsr.reset();
	}

	public void shufflePacked(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			int currentIn = data[i] & 0xFF;
			int currentOut = 0;
			for (int j = 0; j < 8; j++) {
				currentOut <<= 1;
				currentOut |= ((currentIn >> (7 - j)) & 0x1) ^ lfsr.nextBit();
			}
			data[i] = (byte) currentOut;
		}
		lfsr.reset();
	}

}
