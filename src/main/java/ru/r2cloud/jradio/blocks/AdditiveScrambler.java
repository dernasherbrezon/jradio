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
			int scramble_byte = 0x00;
			for (int k = 0; k < bitsPerByte; k++) {
				scramble_byte ^= (lfsr.nextBit() << k);
			}
			data[i] = (byte) ((data[i] & 0xFF) ^ scramble_byte);
		}
		lfsr.reset();
	}
	
}
