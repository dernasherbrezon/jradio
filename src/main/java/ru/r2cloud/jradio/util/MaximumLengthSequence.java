package ru.r2cloud.jradio.util;

import java.util.Arrays;

public class MaximumLengthSequence {

	private static final int[][] TAPS_BY_BITS = new int[][] { { 1 }, { 2 }, { 3 }, { 3 }, { 5 }, { 6 }, { 7, 6, 1 }, { 5 }, { 7 }, { 9 }, { 11, 10, 4 }, { 12, 11, 8 }, { 13, 12, 2 }, { 14 }, { 15, 13, 4 }, { 14 }, { 11 }, { 18, 17, 14 }, { 17 }, { 19 }, { 21 }, { 18 }, { 23, 22, 17 }, { 22 }, { 25, 24, 20 }, { 26, 25, 22 }, { 25 }, { 27 }, { 29, 28, 7 }, { 28 }, { 31, 30, 10 } };

	public static byte[] generate(int bits) {
		if (bits < 2 || bits > 32) {
			throw new IllegalArgumentException("unsupported number of bits");
		}
		byte[] result = new byte[(int) Math.pow(2, bits) - 1];
		int feedback = 0;
		int idx = 0;
		int[] state = new int[bits];
		Arrays.fill(state, 1);
		int[] taps = TAPS_BY_BITS[bits - 2];
		for (int i = 0; i < result.length; i++) {
			feedback = state[idx];
			result[i] = (byte) feedback;
			for (int j = 0; j < taps.length; j++) {
				feedback ^= state[(taps[j] + idx) % bits];
			}
			state[idx] = feedback;
			idx = (idx + 1) % bits;
		}
		return result;
	}

	private MaximumLengthSequence() {
		// do nothing
	}
}
