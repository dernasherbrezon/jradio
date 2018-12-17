package ru.r2cloud.jradio.beesat;

public class MobitexRandomizer {

	// 511 cannot be packed into 64 bytes
	private final static int[] sequence = new int[511 * 8];

	static {
		int scramble_shift_reg = 0x01FF;
		for (int i = 0; i < sequence.length * 8; i++) {
			sequence[i / 8] = (sequence[i / 8] << 1) | (scramble_shift_reg & 0x1);
			// Check 5th and 9th Stage of
			if (((scramble_shift_reg & 0x0011) == 0x0010) || ((scramble_shift_reg & 0x0011) == 0x0001)) {
				scramble_shift_reg |= 0x0200;
			}
			scramble_shift_reg = (scramble_shift_reg >> 1) & 0x01FF;
		}
	}

	private int currentIndex;

	public void shuffle(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ sequence[(currentIndex + i) % sequence.length]);
		}
		currentIndex += data.length;
	}

}