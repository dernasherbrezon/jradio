package ru.r2cloud.jradio.tubix20;

public class MobitexRandomizer {

	// 511 cannot be packed into 64 bytes
	private static final int[] sequence = new int[511 * 8];

	static {
		int scrambleShiftRegister = 0x01FF;
		for (int i = 0; i < sequence.length * 8; i++) {
			sequence[i / 8] = (sequence[i / 8] << 1) | (scrambleShiftRegister & 0x1);
			// Check 5th and 9th Stage of
			if (((scrambleShiftRegister & 0x0011) == 0x0010) || ((scrambleShiftRegister & 0x0011) == 0x0001)) {
				scrambleShiftRegister |= 0x0200;
			}
			scrambleShiftRegister = (scrambleShiftRegister >> 1) & 0x01FF;
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