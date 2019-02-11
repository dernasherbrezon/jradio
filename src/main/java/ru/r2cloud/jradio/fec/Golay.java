package ru.r2cloud.jradio.fec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Golay {

	private static final Logger LOG = LoggerFactory.getLogger(Golay.class);
	private static final int N = 12;
	private final int[] H = new int[] { 0x8008ed, 0x4001db, 0x2003b5, 0x100769, 0x80ed1, 0x40da3, 0x20b47, 0x1068f, 0x8d1d, 0x4a3b, 0x2477, 0x1ffe };

	public int encode(int data) {
		int r = (data) & 0xfff;
		int s = 0;
		for (int i = 0; i < N; i++) {
			s <<= 1;
			s |= parity(H[i] & r);
		}
		return ((0xFFF & s) << N) | r;
	}

	public int decode(int data) throws UncorrectableException {
		int errorVector = decodeInternal(data);
		// Step 8. c = r + e
		int result = data ^ errorVector;

		if (LOG.isDebugEnabled()) {
			LOG.debug("corrected byte errors: " + volk_32u_popcnt(errorVector));
		}
		return result & 0xFFF;
	}

	private int decodeInternal(int data) throws UncorrectableException {
		int r = data;
		int s; /* syndrome */
		int q; /* modified syndrome */
		int errorVector; /* estimated error vector */
		int i;
		int popcount;

		// Step 1. s = H*r
		s = 0;
		for (i = 0; i < N; i++) {
			s <<= 1;
			s |= parity(H[i] & r);
		}

		// Step 2. if w(s) <= 3, then e = (s, 0) and go to step 8
		popcount = volk_32u_popcnt(s);
		if (popcount <= 3) {
			errorVector = s;
			errorVector <<= N;
			return errorVector;
		}

		// Step 3. if w(s + B[i]) <= 2, then e = (s + B[i], e_{i+1}) and go to step 8
		for (i = 0; i < N; i++) {
			popcount = volk_32u_popcnt(s ^ (H[i] & 0xfff));
			if (popcount <= 2) {
				errorVector = s ^ (H[i] & 0xfff);
				errorVector <<= N;
				errorVector |= 1 << (N - i - 1);
				return errorVector;
			}
		}

		// Step 4. compute q = B*s
		q = 0;
		for (i = 0; i < N; i++) {
			q <<= 1;
			q |= parity((H[i] & 0xfff) & s);
		}

		// Step 5. If w(q) <= 3, then e = (0, q) and go to step 8
		popcount = volk_32u_popcnt(q);
		if (popcount <= 3) {
			errorVector = q;
			return errorVector;
		}

		// Step 6. If w(q + B[i]) <= 2, then e = (e_{i+1}, q + B[i]) and got to step 8
		for (i = 0; i < N; i++) {
			popcount = volk_32u_popcnt(q ^ (H[i] & 0xfff));
			if (popcount <= 2) {
				errorVector = 1 << (2 * N - i - 1);
				errorVector |= q ^ (H[i] & 0xfff);
				return errorVector;
			}
		}

		// Step 7. r is uncorrectable
		throw new UncorrectableException("uncorrectable");
	}

	private static int volk_32u_popcnt(int value) {
		// This is faster than a lookup table
		int retVal = value;

		retVal = (retVal & 0x55555555) + (retVal >> 1 & 0x55555555);
		retVal = (retVal & 0x33333333) + (retVal >> 2 & 0x33333333);
		retVal = (retVal + (retVal >> 4)) & 0x0F0F0F0F;
		retVal = (retVal + (retVal >> 8));
		retVal = (retVal + (retVal >> 16)) & 0x0000003F;

		return retVal;
	}

	private static short parity(long x) {
		short result = 0;
		while (x != 0) {
			result ^= (x & 1);
			x >>>= 1;
		}
		return result;
	}
}
