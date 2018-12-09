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

	private int MAXDEG;
	private int[] genPoly;
	private int NPAR;

	public ReedSolomon(int parityBytes) {
		initExpTable();
		this.NPAR = parityBytes;
		MAXDEG = NPAR * 2;
		genPoly = new int[MAXDEG * 2];
		compute_genpoly();
	}

	private void compute_genpoly() {
		int i = 0;
		int[] tp = new int[256];
		int[] tp1 = new int[256];

		/* multiply (x + a^n) for n = 1 to nbytes */

		zero_poly(tp1);
		tp1[0] = 1;

		for (i = 1; i <= NPAR; i++) {
			zero_poly(tp);
			tp[0] = gexp[i]; /* set up x+a^n */
			tp[1] = 1;

			mult_polys(genPoly, tp, tp1);
			copy_poly(tp1, genPoly);
		}
	}

	void zero_poly(int poly[]) {
		int i;
		for (i = 0; i < MAXDEG; i++) {
			poly[i] = 0;
		}
	}

	void copy_poly(int dst[], int src[]) {
		int i;
		for (i = 0; i < MAXDEG; i++) {
			dst[i] = src[i];
		}
	}

	/* polynomial multiplication */
	void mult_polys(int dst[], int p1[], int p2[]) {
		int i, j;
		int[] tmp1 = new int[MAXDEG * 2];

		for (i = 0; i < (MAXDEG * 2); i++)
			dst[i] = 0;

		for (i = 0; i < MAXDEG; i++) {
			for (j = MAXDEG; j < (MAXDEG * 2); j++)
				tmp1[j] = 0;

			/* scale tmp1 by p1[i] */
			for (j = 0; j < MAXDEG; j++)
				tmp1[j] = gmult(p2[j], p1[i]);
			/* and mult (shift) tmp1 right by i */
			for (j = (MAXDEG * 2) - 1; j >= i; j--)
				tmp1[j] = tmp1[j - i];
			for (j = 0; j < i; j++)
				tmp1[j] = 0;

			/* add into partial product */
			for (j = 0; j < (MAXDEG * 2); j++)
				dst[j] ^= tmp1[j];
		}
	}

	int gmult(int a, int b) {
		int i, j;
		if (a == 0 || b == 0) {
			return (0);
		}
		i = glog[a];
		j = glog[b];
		return (gexp[i + j]);
	}

	private void initExpTable() {
		int i, z;
		int pinit, p1, p2, p3, p4, p5, p6, p7, p8;

		pinit = p2 = p3 = p4 = p5 = p6 = p7 = p8 = 0;
		p1 = 1;

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
		int[] LFSR = new int[NPAR + 1];
		int dbyte = 0;
		int j = 0;

		for (i = 0; i < NPAR + 1; i++) {
			LFSR[i] = 0;
		}

		for (i = 0; i < data.length; i++) {
			dbyte = data[i] ^ LFSR[NPAR - 1];
			for (j = NPAR - 1; j > 0; j--) {
				LFSR[j] = LFSR[j - 1] ^ gmult(genPoly[j], dbyte);
			}
			LFSR[0] = gmult(genPoly[0], dbyte);
		}

		int[] pBytes = new int[MAXDEG];
		for (i = 0; i < NPAR; i++) {
			pBytes[i] = LFSR[i];
		}

		return buildCodeword(data, pBytes);
	}

	private byte[] buildCodeword(byte[] data, int[] pBytes) {
		int i;
		byte[] result = new byte[data.length + NPAR];

		for (i = 0; i < data.length; i++) {
			result[i] = data[i];
		}

		for (i = 0; i < NPAR; i++) {
			result[i + data.length] = (byte) pBytes[NPAR - 1 - i];
		}
		return result;
	}

	public byte[] decode(byte[] data) throws UncorrectableException {
		int[] synBytes = new int[MAXDEG];
		int i, j, sum;
		for (j = 0; j < NPAR; j++) {
			sum = 0;
			for (i = 0; i < data.length; i++) {
				sum = (data[i] & 0xFF) ^ gmult(gexp[j + 1], sum);
			}
			synBytes[j] = sum;
		}
		if (check_syndrome(synBytes) != 0) {
			if (correct_errors_erasures(data, synBytes) != 1) {
				throw new UncorrectableException("uncorrectable");
			}
		}
		return Arrays.copyOfRange(data, 0, data.length - NPAR);
	}

	private int correct_errors_erasures(byte[] codeword, int[] synBytes) {
		int r, i, j, err;
		int[] Lambda = new int[MAXDEG];
		int[] Omega = new int[MAXDEG];
		int[] ErrorLocs = new int[256];
		Modified_Berlekamp_Massey(Lambda, Omega, synBytes);
		int NErrors = Find_Roots(Lambda, ErrorLocs);
		if ((NErrors <= NPAR) && NErrors > 0) {

			/* first check for illegal error locs */
			for (r = 0; r < NErrors; r++) {
				if (ErrorLocs[r] >= codeword.length) {
					return (0);
				}
			}

			for (r = 0; r < NErrors; r++) {
				int num, denom;
				i = ErrorLocs[r];
				/* evaluate Omega at alpha^(-i) */

				num = 0;
				for (j = 0; j < MAXDEG; j++)
					num ^= gmult(Omega[j], gexp[((255 - i) * j) % 255]);

				/* evaluate Lambda' (derivative) at alpha^(-i) ; all odd powers disappear */
				denom = 0;
				for (j = 1; j < MAXDEG; j += 2) {
					denom ^= gmult(Lambda[j], gexp[((255 - i) * (j - 1)) % 255]);
				}

				err = gmult(num, ginv(denom));
				codeword[codeword.length - i - 1] ^= err;
			}
			return (1);
		} else {
			return (0);
		}
	}

	private void Modified_Berlekamp_Massey(int[] Lambda, int[] Omega, int[] synBytes) {
		int n, L, L2, k, d, i;
		int[] psi = new int[MAXDEG];
		int[] psi2 = new int[MAXDEG];
		int[] D = new int[MAXDEG];
		int[] gamma = new int[MAXDEG];

		/* initialize Gamma, the erasure locator polynomial */
		init_gamma(gamma);

		/* initialize to z */
		copy_poly(D, gamma);
		mul_z_poly(D);

		copy_poly(psi, gamma);
		k = -1;
		L = 0;

		for (n = 0; n < NPAR; n++) {

			d = compute_discrepancy(psi, synBytes, L, n);

			if (d != 0) {

				/* psi2 = psi - d*D */
				for (i = 0; i < MAXDEG; i++)
					psi2[i] = psi[i] ^ gmult(d, D[i]);

				if (L < (n - k)) {
					L2 = n - k;
					k = n - L;
					/* D = scale_poly(ginv(d), psi); */
					for (i = 0; i < MAXDEG; i++)
						D[i] = gmult(psi[i], ginv(d));
					L = L2;
				}

				/* psi = psi2 */
				for (i = 0; i < MAXDEG; i++)
					psi[i] = psi2[i];
			}

			mul_z_poly(D);
		}

		for (i = 0; i < MAXDEG; i++) {
			Lambda[i] = psi[i];
		}
		compute_modified_omega(Lambda, Omega, synBytes);

	}

	private int Find_Roots(int[] Lambda, int[] ErrorLocs) {
		int sum, r, k;
		int NErrors = 0;

		for (r = 1; r < 256; r++) {
			sum = 0;
			/* evaluate lambda at r */
			for (k = 0; k < NPAR + 1; k++) {
				sum ^= gmult(gexp[(k * r) % 255], Lambda[k]);
			}
			if (sum == 0) {
				ErrorLocs[NErrors] = (255 - r);
				NErrors++;
			}
		}
		return NErrors;
	}

	/*
	 * given Psi (called Lambda in Modified_Berlekamp_Massey) and synBytes, compute the combined erasure/error evaluator polynomial as Psi*S mod z^4
	 */
	private void compute_modified_omega(int[] Lambda, int[] Omega, int[] synBytes) {
		int i;
		int[] product = new int[MAXDEG * 2];

		mult_polys(product, Lambda, synBytes);
		zero_poly(Omega);
		for (i = 0; i < NPAR; i++) {
			Omega[i] = product[i];
		}
	}

	private int ginv(int elt) {
		return (gexp[255 - glog[elt]]);
	}

	/* gamma = product (1-z*a^Ij) for erasure locs Ij */
	private void init_gamma(int gamma[]) {
		int[] tmp = new int[MAXDEG];
		zero_poly(gamma);
		zero_poly(tmp);
		gamma[0] = 1;
	}

	private int compute_discrepancy(int lambda[], int S[], int L, int n) {
		int i, sum = 0;

		for (i = 0; i <= L; i++) {
			sum ^= gmult(lambda[i], S[n - i]);
		}
		return (sum);
	}

	private void mul_z_poly(int src[]) {
		int i;
		for (i = MAXDEG - 1; i > 0; i--) {
			src[i] = src[i - 1];
		}
		src[0] = 0;
	}

	private int check_syndrome(int[] synBytes) {
		int i, nz = 0;
		for (i = 0; i < NPAR; i++) {
			if (synBytes[i] != 0) {
				nz = 1;
				break;
			}
		}
		return nz;
	}

}
