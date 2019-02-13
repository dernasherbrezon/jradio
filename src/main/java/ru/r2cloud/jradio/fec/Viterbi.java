package ru.r2cloud.jradio.fec;

import java.util.LinkedList;

public class Viterbi {
	
	public static final int TAIL = 2;
	private byte[] metrics1 = new byte[64];
	private byte[] metrics2 = new byte[64];

	private byte[] old_metrics;
	private byte[] new_metrics;
	
	private byte[][] branchtab = new byte[2][32];

	private static final int[] lookup = new int[] { 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1,
			0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, };

	public Viterbi(byte poly1, byte poly2, boolean invertPoly2) {
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
			branchtab[0][state] = (byte) lookup[(2 * state) & poly1];
			branchtab[1][state] = (byte) ((invertPoly2Byte ^ lookup[(2 * state) & poly2]));
		}
	}
	
	//each byte coded 8 bits
	private byte[] decodeInternal(byte[] data) {
		byte m0, m1, decision, metric, sym0, sym1;
		LinkedList<int[]> decisions = new LinkedList<int[]>();
		for (int i = 0; i < data.length; i++) {
			for (int j = 7; j >= 0;) {
				int[] d = new int[8];
				sym0 = (byte) ((data[i] >> j) & 1);
				j--;
				sym1 = (byte) ((data[i] >> j) & 1);
				j--;
				for (int b = 0; b < 32; b++) {
					metric = (byte) ((branchtab[0][b] ^ sym0) + (branchtab[1][b] ^ sym1));
					m0 = (byte) (old_metrics[b] + metric);
					m1 = (byte) (old_metrics[b + 32] + (2 - metric));
					decision = m0 > m1 ? (byte) 1 : 0;
					new_metrics[(b << 1)] = m0 > m1 ? m1 : m0;
					d[b >> 2] |= decision << (((b << 1)) & 7);

					m0 -= (metric + metric - 2);
					m1 += (metric + metric - 2);
					decision = m0 > m1 ? (byte) 1 : 0;
					new_metrics[(b << 1) + 1] = m0 > m1 ? m1 : m0;
					d[b >> 2] |= decision << (((b << 1) + 1) & 7);
				}

				byte[] tmp = old_metrics;
				old_metrics = new_metrics;
				new_metrics = tmp;
				
				decisions.add(d);
			}

		}

		int endstate = 0;

		byte[] result = new byte[data.length / 2];
		int nbits = ((data.length - TAIL) / 2 ) * 8;
		int k;
		while (nbits-- > 0) {
			k = (decisions.get(nbits + 6)[(endstate >> 2) / 8] >> ((endstate >> 2) & 7)) & 1;
			endstate = (endstate >> 1) | (k << 7);
			result[nbits >> 3] = (byte) endstate;
		}
		byte[] temp = new byte[result.length - 1];
		System.arraycopy(result, 0, temp, 0, temp.length);
		return temp;
	}

	public static byte[] encode(byte[] data, byte poly1, byte poly2, boolean invertPoly2) {
		byte state = 0x0;
		byte invertPoly2Byte;
		if (invertPoly2) {
			invertPoly2Byte = 1;
		} else {
			invertPoly2Byte = 0;
		}
		byte[] result = new byte[data.length * 2 + TAIL];
		for (int i = 0, k = 0; i < data.length + TAIL && k < result.length; i++, k++) {
			byte curByte;
			if (i >= data.length) {
				curByte = 0x0;
			} else {
				curByte = data[i];
			}
			result[k] = 0x0;
			result[k + 1] = 0x0;
			for (int j = 7; j >= 0; j--) {
				state = (byte) ((state << 1) | (curByte >> j) & 1);
				if (j == 3) {
					k++;
				}
				result[k] = (byte) ((result[k] << 1) | (lookup[state & poly1] & 1));
				result[k] = (byte) ((result[k] << 1) | (invertPoly2Byte ^ (lookup[state & poly2] & 1)));
			}
		}
		return result;
	}

	public static byte[] decode(byte[] data, byte poly1, byte poly2, boolean invertPoly2) {
		Viterbi v = new Viterbi(poly1, poly2, invertPoly2);
		return v.decodeInternal(data);
	}

}
