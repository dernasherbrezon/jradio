package ru.r2cloud.jradio.fec;

public class ViterbiSoft {

	private static final int TAIL = 2;
	private final int[][] branchtab = new int[2][32];
	private final long[] decisions;
	private final byte[] resultWithTail;
	private final byte[] resultWithoutTail;

	private long[] metrics1 = new long[64];
	private long[] metrics2 = new long[64];

	private static final int[] lookup = new int[] { 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1,
			0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0 };

	public ViterbiSoft(byte poly1, byte poly2, boolean invertPoly2, int dataLength) {
		decisions = new long[dataLength];
		resultWithTail = new byte[dataLength / 16];
		resultWithoutTail = new byte[resultWithTail.length - 1];
		byte invertPoly2Byte;
		if (invertPoly2) {
			invertPoly2Byte = 1;
		} else {
			invertPoly2Byte = 0;
		}

		int state;

		for (state = 0; state < 32; state++) {
			branchtab[0][state] = lookup[(2 * state) & poly1] > 0 ? 255 : 0;
			branchtab[1][state] = (invertPoly2Byte ^ lookup[(2 * state) & poly2]) > 0 ? 255 : 0;
		}
	}

	// each byte coded 1 bit
	public byte[] decode(byte[] data) {
		if (data.length != decisions.length) {
			throw new IllegalArgumentException("data length mismatched. expected: " + decisions.length + " got: " + data.length);
		}
		
		for (int i = 0; i < metrics1.length; i++) {
			metrics1[i] = 63;
		}
		long[] oldMetrics = metrics1;
		long[] newMetrics = metrics2;
		oldMetrics[0 & 63] = 0;
		
		long m0;
		long m1;
		long decision;
		long metric;
		long sym0;
		long sym1;
		// use flat array for perf reasons
		// flat array will store decisions 0 and 1 one by one
		for (int i = 0; i < data.length; i += 2) {
			sym0 = 128L + data[i]; // convert to unsigned
			sym1 = 128L + data[i + 1]; // convert to unsigned
			decisions[i + 0] = 0;
			decisions[i + 1] = 0;
			for (int b = 0; b < 32; b++) {
				metric = (branchtab[0][b] ^ sym0) + (branchtab[1][b] ^ sym1);
				m0 = oldMetrics[b] + metric;
				m1 = oldMetrics[b + 32] + (510 - metric);
				decision = m0 > m1 ? 1 : 0;
				newMetrics[2 * b] = m0 > m1 ? m1 : m0;
				decisions[i + (b >> 4)] |= decision << ((2 * b) & 31);

				m0 -= (metric + metric - 510);
				m1 += (metric + metric - 510);
				decision = m0 > m1 ? 1 : 0;
				newMetrics[2 * b + 1] = m0 > m1 ? m1 : m0;
				decisions[i + (b >> 4)] |= decision << ((2 * b + 1) & 31);
			}

			long[] tmp = oldMetrics;
			oldMetrics = newMetrics;
			newMetrics = tmp;
		}

		long endstate = 0;

		// /8 for 8bit soft encoding, /2 because of convolutional code
		int nbits = ((data.length / 8 - TAIL) / 2) * 8;
		long k;
		while (nbits-- > 0) {
			k = (decisions[(nbits + 6) * 2 + (int) ((endstate >> 2) / 32)] >> ((endstate >> 2) % 32)) & 1;
			endstate = (endstate >> 1) | (k << 7);
			resultWithTail[nbits >> 3] = (byte) endstate;
		}
		System.arraycopy(resultWithTail, 0, resultWithoutTail, 0, resultWithoutTail.length);
		return resultWithoutTail;
	}

	public static byte[] decode(byte[] data, byte poly1, byte poly2, boolean invertPoly2) {
		ViterbiSoft v = new ViterbiSoft(poly1, poly2, invertPoly2, data.length);
		return v.decode(data);
	}

}
