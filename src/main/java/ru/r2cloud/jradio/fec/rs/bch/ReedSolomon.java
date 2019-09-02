package ru.r2cloud.jradio.fec.rs.bch;

import java.util.Arrays;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

/**
 * Reed solomon encoder/decoder based on improved Berlekamp-Massey algorithm https://en.wikipedia.org/wiki/Berlekamp–Massey_algorithm
 *
 */
public class ReedSolomon {

	private int[] gexp = new int[512];
	private int[] glog = new int[256];

	private int maxdeg;
	private int[] genPoly;
	private int npar;

	public ReedSolomon(int parityBytes) {
		initExpTable();
		this.npar = parityBytes;
		maxdeg = npar * 2;
		genPoly = new int[maxdeg * 2];
		computeGenpoly();
	}

	private void computeGenpoly() {
		int i = 0;
		int[] tp = new int[256];
		int[] tp1 = new int[256];

		/* multiply (x + a^n) for n = 1 to nbytes */

		zeroPoly(tp1);
		tp1[0] = 1;

		for (i = 1; i <= npar; i++) {
			zeroPoly(tp);
			tp[0] = gexp[i]; /* set up x+a^n */
			tp[1] = 1;

			multPolys(genPoly, tp, tp1);
			copyPoly(tp1, genPoly);
		}
	}

	void zeroPoly(int[] poly) {
		int i;
		for (i = 0; i < maxdeg; i++) {
			poly[i] = 0;
		}
	}

	void copyPoly(int[] dst, int[] src) {
		System.arraycopy(src, 0, dst, 0, maxdeg);
	}

	/* polynomial multiplication */
	void multPolys(int[] dst, int[] p1, int[] p2) {
		int i;
		int j;
		int[] tmp1 = new int[maxdeg * 2];

		for (i = 0; i < (maxdeg * 2); i++)
			dst[i] = 0;

		for (i = 0; i < maxdeg; i++) {
			for (j = maxdeg; j < (maxdeg * 2); j++)
				tmp1[j] = 0;

			/* scale tmp1 by p1[i] */
			for (j = 0; j < maxdeg; j++)
				tmp1[j] = gmult(p2[j], p1[i]);
			/* and mult (shift) tmp1 right by i */
			for (j = (maxdeg * 2) - 1; j >= i; j--)
				tmp1[j] = tmp1[j - i];
			for (j = 0; j < i; j++)
				tmp1[j] = 0;

			/* add into partial product */
			for (j = 0; j < (maxdeg * 2); j++)
				dst[j] ^= tmp1[j];
		}
	}

	int gmult(int a, int b) {
		int i;
		int j;
		if (a == 0 || b == 0) {
			return (0);
		}
		i = glog[a];
		j = glog[b];
		return (gexp[i + j]);
	}

	private void initExpTable() {
		int i;
		int z;
		int pinit = 0;
		int p1 = 1;
		int p2 = 0;
		int p3 = 0;
		int p4 = 0;
		int p5 = 0;
		int p6 = 0;
		int p7 = 0;
		int p8 = 0;

		gexp[0] = 1;
		gexp[255] = gexp[0];
		glog[0] = 0; /* shouldn't log[0] be an error? */

		for (i = 1; i < 256; i++) {
			pinit = p8;
			p8 = p7;
			p7 = p6;
			p6 = p5;
			p5 = p4 ^ pinit;
			p4 = p3 ^ pinit;
			p3 = p2 ^ pinit;
			p2 = p1;
			p1 = pinit;
			gexp[i] = p1 + p2 * 2 + p3 * 4 + p4 * 8 + p5 * 16 + p6 * 32 + p7 * 64 + p8 * 128;
			gexp[i + 255] = gexp[i];
		}

		for (i = 1; i < 256; i++) {
			for (z = 0; z < 256; z++) {
				if (gexp[z] == i) {
					glog[i] = z;
					break;
				}
			}
		}
	}

	public byte[] encode(byte[] data) {
		int i = 0;
		int[] lfsr = new int[npar + 1];
		int dbyte = 0;
		int j = 0;

		for (i = 0; i < npar + 1; i++) {
			lfsr[i] = 0;
		}

		for (i = 0; i < data.length; i++) {
			dbyte = data[i] ^ lfsr[npar - 1];
			for (j = npar - 1; j > 0; j--) {
				lfsr[j] = lfsr[j - 1] ^ gmult(genPoly[j], dbyte);
			}
			lfsr[0] = gmult(genPoly[0], dbyte);
		}

		int[] pBytes = new int[maxdeg];
		System.arraycopy(lfsr, 0, pBytes, 0, npar);
		return buildCodeword(data, pBytes);
	}

	private byte[] buildCodeword(byte[] data, int[] pBytes) {
		int i;
		byte[] result = new byte[data.length + npar];
		System.arraycopy(data, 0, result, 0, data.length);

		for (i = 0; i < npar; i++) {
			result[i + data.length] = (byte) pBytes[npar - 1 - i];
		}
		return result;
	}

	public byte[] decode(byte[] data) throws UncorrectableException {
		int[] synBytes = new int[maxdeg];
		int i;
		int j;
		int sum;
		for (j = 0; j < npar; j++) {
			sum = 0;
			for (i = 0; i < data.length; i++) {
				sum = (data[i] & 0xFF) ^ gmult(gexp[j + 1], sum);
			}
			synBytes[j] = sum;
		}
		if (checkSyndrome(synBytes) != 0 && correctErrorsErasures(data, synBytes) != 1) {
			throw new UncorrectableException("uncorrectable");
		}
		return Arrays.copyOfRange(data, 0, data.length - npar);
	}

	private int correctErrorsErasures(byte[] codeword, int[] synBytes) {
		int r;
		int i;
		int j;
		int err;
		int[] lambda = new int[maxdeg];
		int[] omega = new int[maxdeg];
		int[] errorLocs = new int[256];
		modifiedBerlekampMassey(lambda, omega, synBytes);
		int nerrors = findRoots(lambda, errorLocs);
		if ((nerrors <= npar) && nerrors > 0) {

			/* first check for illegal error locs */
			for (r = 0; r < nerrors; r++) {
				if (errorLocs[r] >= codeword.length) {
					return (0);
				}
			}

			for (r = 0; r < nerrors; r++) {
				int num;
				int denom;
				i = errorLocs[r];
				/* evaluate Omega at alpha^(-i) */

				num = 0;
				for (j = 0; j < maxdeg; j++)
					num ^= gmult(omega[j], gexp[((255 - i) * j) % 255]);

				/* evaluate Lambda' (derivative) at alpha^(-i) ; all odd powers disappear */
				denom = 0;
				for (j = 1; j < maxdeg; j += 2) {
					denom ^= gmult(lambda[j], gexp[((255 - i) * (j - 1)) % 255]);
				}

				err = gmult(num, ginv(denom));
				codeword[codeword.length - i - 1] ^= err;
			}
			return (1);
		} else {
			return (0);
		}
	}

	private void modifiedBerlekampMassey(int[] lambda, int[] omega, int[] synBytes) {
		int n;
		int L;
		int L2;
		int k;
		int d;
		int i;
		int[] psi = new int[maxdeg];
		int[] psi2 = new int[maxdeg];
		int[] localD = new int[maxdeg];
		int[] gamma = new int[maxdeg];

		/* initialize Gamma, the erasure locator polynomial */
		initGamma(gamma);

		/* initialize to z */
		copyPoly(localD, gamma);
		mulZPoly(localD);

		copyPoly(psi, gamma);
		k = -1;
		L = 0;

		for (n = 0; n < npar; n++) {

			d = computeDiscrepancy(psi, synBytes, L, n);

			if (d != 0) {

				/* psi2 = psi - d*D */
				for (i = 0; i < maxdeg; i++) {
					psi2[i] = psi[i] ^ gmult(d, localD[i]);
				}

				if (L < (n - k)) {
					L2 = n - k;
					k = n - L;
					/* D = scale_poly(ginv(d), psi); */
					for (i = 0; i < maxdeg; i++) {
						localD[i] = gmult(psi[i], ginv(d));
					}
					L = L2;
				}

				/* psi = psi2 */
				System.arraycopy(psi2, 0, psi, 0, maxdeg);
			}

			mulZPoly(localD);
		}

		System.arraycopy(psi, 0, lambda, 0, maxdeg);
		computeModifiedOmega(lambda, omega, synBytes);

	}

	private int findRoots(int[] lambda, int[] errorLocs) {
		int sum;
		int r;
		int k;
		int nerrors = 0;

		for (r = 1; r < 256; r++) {
			sum = 0;
			/* evaluate lambda at r */
			for (k = 0; k < npar + 1; k++) {
				sum ^= gmult(gexp[(k * r) % 255], lambda[k]);
			}
			if (sum == 0) {
				errorLocs[nerrors] = (255 - r);
				nerrors++;
			}
		}
		return nerrors;
	}

	/*
	 * given Psi (called Lambda in Modified_Berlekamp_Massey) and synBytes, compute the combined erasure/error evaluator polynomial as Psi*S mod z^4
	 */
	private void computeModifiedOmega(int[] lambda, int[] omega, int[] synBytes) {
		int[] product = new int[maxdeg * 2];

		multPolys(product, lambda, synBytes);
		zeroPoly(omega);
		System.arraycopy(product, 0, omega, 0, npar);
	}

	private int ginv(int elt) {
		return (gexp[255 - glog[elt]]);
	}

	/* gamma = product (1-z*a^Ij) for erasure locs Ij */
	private void initGamma(int[] gamma) {
		int[] tmp = new int[maxdeg];
		zeroPoly(gamma);
		zeroPoly(tmp);
		gamma[0] = 1;
	}

	private int computeDiscrepancy(int[] lambda, int[] s, int l, int n) {
		int i;
		int sum = 0;

		for (i = 0; i <= l; i++) {
			sum ^= gmult(lambda[i], s[n - i]);
		}
		return (sum);
	}

	private void mulZPoly(int[] src) {
		int i;
		for (i = maxdeg - 1; i > 0; i--) {
			src[i] = src[i - 1];
		}
		src[0] = 0;
	}

	private int checkSyndrome(int[] synBytes) {
		int i;
		int nz = 0;
		for (i = 0; i < npar; i++) {
			if (synBytes[i] != 0) {
				nz = 1;
				break;
			}
		}
		return nz;
	}

}
