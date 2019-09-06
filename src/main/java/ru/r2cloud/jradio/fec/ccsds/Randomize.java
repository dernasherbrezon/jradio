package ru.r2cloud.jradio.fec.ccsds;

public class Randomize {

	private static final int[] SEQUENCE = new int[255];

	static {
		int[] x = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		int i;

		/*
		 * The pseudo random sequence shall be generated using the polynomial h(x) = x8 + x7 + x5 + x3 + 1
		 */
		for (i = 0; i < SEQUENCE.length * 8; i++) {
			SEQUENCE[i / 8] = SEQUENCE[i / 8] | x[1] << 7 >> (i % 8);
			x[0] = (x[8] + x[6] + x[4] + x[1]) % 2;
			x[1] = x[2];
			x[2] = x[3];
			x[3] = x[4];
			x[4] = x[5];
			x[5] = x[6];
			x[6] = x[7];
			x[7] = x[8];
			x[8] = x[0];
		}
	}

	public static void shuffle(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ SEQUENCE[i % SEQUENCE.length]);
		}
	}

	private Randomize() {
		// do nothing
	}

}
