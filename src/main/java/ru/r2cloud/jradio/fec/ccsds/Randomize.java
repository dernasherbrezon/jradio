package ru.r2cloud.jradio.fec.ccsds;

public class Randomize {

	public static byte[] shuffle(byte[] data) {
		int[] sequence = new int[data.length];
		int[] x = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		int i;

		/*
		 * The pseudo random sequence shall be generated using the polynomial
		 * h(x) = x8 + x7 + x5 + x3 + 1
		 */
		for (i = 0; i < data.length * 8; i++) {
			sequence[i / 8] = sequence[i / 8] | x[1] << 7 >> (i % 8);
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

		byte[] result = new byte[data.length];
		for (i = 0; i < data.length; i++) {
			result[i] = (byte)(data[i] ^ sequence[i]);
		}
		return result;
	}

}
