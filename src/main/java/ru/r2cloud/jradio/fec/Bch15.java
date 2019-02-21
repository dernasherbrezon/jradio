package ru.r2cloud.jradio.fec;

import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Bch15 {

	private static final int[] exp_table = new int[] { 8, 4, 2, 1, 12, 6, 3, 13, 10, 5, 14, 7, 15, 11, 9 }; // exp_table[k] = a^k
	private static final int[] log_table = new int[] { 3, 2, 6, 1, 9, 5, 11, 0, 14, 8, 13, 4, 7, 10, 12 }; // j+1 = a^log_table[j]

	public static void decode(byte[] array, int offset, int d) throws UncorrectableException {
		if (offset + 15 > array.length) {
			throw new ArrayIndexOutOfBoundsException("not enough data. expected: " + (offset + 15) + " got: " + array.length);
		}
		int data = 0;
		for (int i = 0; i < 15; i++) {
			data = data << 1;
			data = data | array[offset + i];
		}
		int[] syndromes = new int[d - 1];
		boolean allZero = true;
		for (int i = 0; i < (d - 1); i++) {
			int cur = compute_syndrome(data, i);
			if (cur != 0) {
				allZero = false;
			}
			syndromes[i] = cur;
		}
		if (allZero) {
			return;
		}
		List<Integer> errorLocations = compute_error_locations(syndromes);
		for (int i = 0; i < errorLocations.size(); i++) {
			array[offset + errorLocations.get(i)] ^= 1;
		}
	}

	private static List<Integer> compute_error_locations(int... syndromes) throws UncorrectableException {
		int[] l = new int[] { 1, 0, 0, 0 };// # coefficients of error locator polynomial
		if (syndromes.length == 2) {
			l[1] = gfMultiply(syndromes[1], gfInvert(syndromes[0])); // # will raise ValueError if s[0] == 0, indicating decoding failure
		} else if (syndromes.length == 4) {
			int det_S = gfMultiply(syndromes[0], syndromes[2]) ^ gfMultiply(syndromes[1], syndromes[1]);
			if (det_S == 0) {
				return compute_error_locations(syndromes[0], syndromes[1]); // # matrix is non-invertible, throw away 2 syndromes
			}
			int inv_det_S = gfInvert(det_S);
			l[2] = gfMultiply(syndromes[2], syndromes[2]) ^ gfMultiply(syndromes[3], syndromes[1]);
			l[2] = gfMultiply(l[2], inv_det_S);
			l[1] = gfMultiply(syndromes[0], syndromes[3]) ^ gfMultiply(syndromes[2], syndromes[1]);
			l[1] = gfMultiply(l[1], inv_det_S);
		} else if (syndromes.length == 6) {
			int det_S = gfMultiply(gfMultiply(syndromes[0], syndromes[2]), syndromes[4]) ^ gfMultiply(gfMultiply(syndromes[2], syndromes[2]), syndromes[2]) ^ gfMultiply(gfMultiply(syndromes[1], syndromes[1]), syndromes[4]) ^ gfMultiply(gfMultiply(syndromes[3], syndromes[3]), syndromes[0]);
			if (det_S == 0) {
				return compute_error_locations(syndromes[0], syndromes[1], syndromes[2], syndromes[3]);// # matrix is non-invertible, throw away 2 syndromes
			}
			int inv_det_S = gfInvert(det_S);
			l[3] = gfMultiply(gfMultiply(syndromes[3], syndromes[2]), syndromes[4]) ^ gfMultiply(gfMultiply(syndromes[1], syndromes[3]), syndromes[5]) ^ gfMultiply(gfMultiply(syndromes[4], syndromes[3]), syndromes[2]) ^ gfMultiply(gfMultiply(syndromes[2], syndromes[2]), syndromes[5]) ^ gfMultiply(gfMultiply(syndromes[1], syndromes[4]), syndromes[4]) ^ gfMultiply(gfMultiply(syndromes[3], syndromes[3]), syndromes[3]);
			l[3] = gfMultiply(l[3], inv_det_S);
			l[2] = gfMultiply(gfMultiply(syndromes[0], syndromes[4]), syndromes[4]) ^ gfMultiply(gfMultiply(syndromes[1], syndromes[2]), syndromes[5]) ^ gfMultiply(gfMultiply(syndromes[3], syndromes[3]), syndromes[2]) ^ gfMultiply(gfMultiply(syndromes[2], syndromes[2]), syndromes[4]) ^ gfMultiply(gfMultiply(syndromes[1], syndromes[3]), syndromes[4]) ^ gfMultiply(gfMultiply(syndromes[0], syndromes[3]), syndromes[5]);
			l[2] = gfMultiply(l[2], inv_det_S);
			l[1] = gfMultiply(gfMultiply(syndromes[0], syndromes[2]), syndromes[5]) ^ gfMultiply(gfMultiply(syndromes[1], syndromes[3]), syndromes[3]) ^ gfMultiply(gfMultiply(syndromes[4], syndromes[1]), syndromes[2]) ^ gfMultiply(gfMultiply(syndromes[2], syndromes[2]), syndromes[3]) ^ gfMultiply(gfMultiply(syndromes[1], syndromes[1]), syndromes[5]) ^ gfMultiply(gfMultiply(syndromes[0], syndromes[3]), syndromes[4]);
			l[1] = gfMultiply(l[1], inv_det_S);
		}
		List<Integer> result = new ArrayList<>();
		int maxIndex = 15;
		for (int i = 0; i < maxIndex; i++) {
			int firstIndex = maxIndex - i % exp_table.length;
			if (firstIndex == maxIndex) {
				firstIndex = 0;
			}
			int secondIndex = maxIndex - 2 * i % exp_table.length;
			if (secondIndex == maxIndex) {
				secondIndex = 0;
			}
			int thirdIndex = maxIndex - 3 * i % exp_table.length;
			if (thirdIndex == maxIndex) {
				thirdIndex = 0;
			}
			int nonError = exp_table[0] ^ gfMultiply(l[1], exp_table[firstIndex]) ^ gfMultiply(l[2], exp_table[secondIndex]) ^ gfMultiply(l[3], exp_table[thirdIndex]);
			if (nonError == 0) {
				result.add(i);
			}
		}
		return result;
	}

	private static int compute_syndrome(int p, int j) {
		int s = 0;
		int n = 15;
		for (int k = n - 1; k >= 0; k--) {
			if ((p & 1) > 0) {
				s ^= exp_table[(k * j) % exp_table.length];
			}
			p >>= 1;
		}
		return s;
	}

	private static int gfInvert(int x) throws UncorrectableException {
		if (x == 0) {
			throw new UncorrectableException("uncorrectable");
		}
		int index = exp_table.length - log_table[x - 1] % exp_table.length;
		if (index == exp_table.length) {
			index = 0;
		}
		return exp_table[index];
	}

	private static int gfMultiply(int x, int y) {
		if (x == 0 || y == 0) {
			return 0;
		}
		return exp_table[(log_table[x - 1] + log_table[y - 1]) % exp_table.length];
	}
}
