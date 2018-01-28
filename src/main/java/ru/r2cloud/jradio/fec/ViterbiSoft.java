package ru.r2cloud.jradio.fec;

import java.util.LinkedList;

public class ViterbiSoft {

	private final static int TAIL = 2;
	private long[] metrics1 = new long[64];
	private long[] metrics2 = new long[64];

	private long[] old_metrics;
	private long[] new_metrics;

	private int[][] branchtab = new int[2][32];

	private static final int[] lookup = new int[] { 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1,
			0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0 };

	public ViterbiSoft(byte poly1, byte poly2, boolean invertPoly2) {
		byte invertPoly2Byte;
		if (invertPoly2) {
			invertPoly2Byte = 1;
		} else {
			invertPoly2Byte = 0;
		}

		for (int i = 0; i < metrics1.length; i++) {
			metrics1[i] = 63;
		}
		old_metrics = metrics1;
		new_metrics = metrics2;
		old_metrics[0 & 63] = 0;
		int state;

		for (state = 0; state < 32; state++) {
			branchtab[0][state] = lookup[(2 * state) & poly1] > 0 ? 255 : 0;
			branchtab[1][state] = ((invertPoly2Byte ^ lookup[(2 * state) & poly2])) > 0 ? 255 : 0;
		}
	}

	// each byte coded 1 bit
	private byte[] decodeInternal(byte[] data) {
		long m0, m1, decision, metric, sym0, sym1;
		LinkedList<long[]> decisions = new LinkedList<long[]>();
		for (int i = 0; i < data.length; i += 2) {
			long[] d = new long[2];
			sym0 = data[i] & 0xFF; // convert to unsigned
			sym1 = data[i + 1] & 0xFF; // convert to unsigned
			for (int b = 0; b < 32; b++) {
				metric = (branchtab[0][b] ^ sym0) + (branchtab[1][b] ^ sym1);
				m0 = old_metrics[b] + metric;
				m1 = old_metrics[b + 32] + (510 - metric);
				decision = m0 > m1 ? 1 : 0;
				new_metrics[2 * b] = m0 > m1 ? m1 : m0;
				d[b / 16] |= decision << ((2 * b) & 31);

				m0 -= (metric + metric - 510);
				m1 += (metric + metric - 510);
				decision = m0 > m1 ? 1 : 0;
				new_metrics[2 * b + 1] = m0 > m1 ? m1 : m0;
				d[b / 16] |= decision << ((2 * b + 1) & 31);
			}

			long[] tmp = old_metrics;
			old_metrics = new_metrics;
			new_metrics = tmp;

			decisions.add(d);

		}

		long endstate = 0;

		// /8 for 8bit soft encoding, /2 because of convolutional code
		byte[] result = new byte[data.length / 16];
		int nbits = ((data.length / 8 - TAIL) / 2) * 8;
		long k;
		while (nbits-- > 0) {
			k = (decisions.get(nbits + 6)[(int)((endstate >> 2) / 32)] >> ((endstate >> 2) % 32)) & 1;
			endstate = (endstate >> 1) | (k << 7);
			result[nbits >> 3] = (byte) endstate;
		}
		byte[] temp = new byte[result.length - 1];
		System.arraycopy(result, 0, temp, 0, temp.length);
		return temp;
	}

	public static byte[] decode(byte[] data, byte poly1, byte poly2, boolean invertPoly2) {
		ViterbiSoft v = new ViterbiSoft(poly1, poly2, invertPoly2);
		return v.decodeInternal(data);
	}

}
