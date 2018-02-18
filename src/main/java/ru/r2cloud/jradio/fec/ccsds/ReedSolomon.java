package ru.r2cloud.jradio.fec.ccsds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReedSolomon {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReedSolomon.class);

	private final static int NROOTS = 32;
	private final static int NN = 255;

	private final static int[] CCSDS_index_of = new int[] { 255, 0, 1, 99, 2, 198, 100, 106, 3, 205, 199, 188, 101, 126, 107, 42, 4, 141, 206, 78, 200, 212, 189, 225, 102, 221, 127, 49, 108, 32, 43, 243, 5, 87, 142, 232, 207, 172, 79, 131, 201, 217, 213, 65, 190, 148, 226, 180, 103, 39, 222, 240, 128, 177, 50, 53, 109, 69, 33, 18, 44, 13, 244, 56, 6, 155, 88, 26, 143, 121, 233, 112, 208, 194, 173, 168, 80, 117, 132, 72, 202, 252, 218, 138, 214, 84, 66, 36, 191, 152, 149, 249, 227, 94, 181, 21,
			104, 97, 40, 186, 223, 76, 241, 47, 129, 230, 178, 63, 51, 238, 54, 16, 110, 24, 70, 166, 34, 136, 19, 247, 45, 184, 14, 61, 245, 164, 57, 59, 7, 158, 156, 157, 89, 159, 27, 8, 144, 9, 122, 28, 234, 160, 113, 90, 209, 29, 195, 123, 174, 10, 169, 145, 81, 91, 118, 114, 133, 161, 73, 235, 203, 124, 253, 196, 219, 30, 139, 210, 215, 146, 85, 170, 67, 11, 37, 175, 192, 115, 153, 119, 150, 92, 250, 82, 228, 236, 95, 74, 182, 162, 22, 134, 105, 197, 98, 254, 41, 125, 187, 204, 224, 211,
			77, 140, 242, 31, 48, 220, 130, 171, 231, 86, 179, 147, 64, 216, 52, 176, 239, 38, 55, 12, 17, 68, 111, 120, 25, 154, 71, 116, 167, 193, 35, 83, 137, 251, 20, 93, 248, 151, 46, 75, 185, 96, 15, 237, 62, 229, 246, 135, 165, 23, 58, 163, 60, 183, };
	private final static int[] CCSDS_alpha_to = new int[] { 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x87, 0x89, 0x95, 0xad, 0xdd, 0x3d, 0x7a, 0xf4, 0x6f, 0xde, 0x3b, 0x76, 0xec, 0x5f, 0xbe, 0xfb, 0x71, 0xe2, 0x43, 0x86, 0x8b, 0x91, 0xa5, 0xcd, 0x1d, 0x3a, 0x74, 0xe8, 0x57, 0xae, 0xdb, 0x31, 0x62, 0xc4, 0x0f, 0x1e, 0x3c, 0x78, 0xf0, 0x67, 0xce, 0x1b, 0x36, 0x6c, 0xd8, 0x37, 0x6e, 0xdc, 0x3f, 0x7e, 0xfc, 0x7f, 0xfe, 0x7b, 0xf6, 0x6b, 0xd6, 0x2b, 0x56, 0xac, 0xdf, 0x39, 0x72, 0xe4, 0x4f,
			0x9e, 0xbb, 0xf1, 0x65, 0xca, 0x13, 0x26, 0x4c, 0x98, 0xb7, 0xe9, 0x55, 0xaa, 0xd3, 0x21, 0x42, 0x84, 0x8f, 0x99, 0xb5, 0xed, 0x5d, 0xba, 0xf3, 0x61, 0xc2, 0x03, 0x06, 0x0c, 0x18, 0x30, 0x60, 0xc0, 0x07, 0x0e, 0x1c, 0x38, 0x70, 0xe0, 0x47, 0x8e, 0x9b, 0xb1, 0xe5, 0x4d, 0x9a, 0xb3, 0xe1, 0x45, 0x8a, 0x93, 0xa1, 0xc5, 0x0d, 0x1a, 0x34, 0x68, 0xd0, 0x27, 0x4e, 0x9c, 0xbf, 0xf9, 0x75, 0xea, 0x53, 0xa6, 0xcb, 0x11, 0x22, 0x44, 0x88, 0x97, 0xa9, 0xd5, 0x2d, 0x5a, 0xb4, 0xef, 0x59, 0xb2,
			0xe3, 0x41, 0x82, 0x83, 0x81, 0x85, 0x8d, 0x9d, 0xbd, 0xfd, 0x7d, 0xfa, 0x73, 0xe6, 0x4b, 0x96, 0xab, 0xd1, 0x25, 0x4a, 0x94, 0xaf, 0xd9, 0x35, 0x6a, 0xd4, 0x2f, 0x5e, 0xbc, 0xff, 0x79, 0xf2, 0x63, 0xc6, 0x0b, 0x16, 0x2c, 0x58, 0xb0, 0xe7, 0x49, 0x92, 0xa3, 0xc1, 0x05, 0x0a, 0x14, 0x28, 0x50, 0xa0, 0xc7, 0x09, 0x12, 0x24, 0x48, 0x90, 0xa7, 0xc9, 0x15, 0x2a, 0x54, 0xa8, 0xd7, 0x29, 0x52, 0xa4, 0xcf, 0x19, 0x32, 0x64, 0xc8, 0x17, 0x2e, 0x5c, 0xb8, 0xf7, 0x69, 0xd2, 0x23, 0x46, 0x8c,
			0x9f, 0xb9, 0xf5, 0x6d, 0xda, 0x33, 0x66, 0xcc, 0x1f, 0x3e, 0x7c, 0xf8, 0x77, 0xee, 0x5b, 0xb6, 0xeb, 0x51, 0xa2, 0xc3, 0x00, };

	private static final int[] CCSDS_poly = new int[] { 0, 249, 59, 66, 4, 43, 126, 251, 97, 30, 3, 213, 50, 66, 170, 5, 24, 5, 170, 66, 50, 213, 3, 30, 97, 251, 126, 43, 4, 66, 59, 249, 0, };

	public static byte[] decode(byte[] data, int interleaving) {
		byte[][] interleaved = new byte[interleaving][NN];
		byte[] result = new byte[data.length - NROOTS * interleaving];
		for (int i = 0; i < interleaving; i++) {
			// deinterleave
			// transform 0 1 2 3 4 5 6 7 p1 p2 p3 p4 bytes into ${interleaving} blocks:
			// 0 4 p1
			// 1 5 p2
			// 2 6 p3
			// 3 7 p4 &etc
			for (int j = 0; j < NN; j++) {
				interleaved[i][j] = data[j * interleaving + i];
			}
			// decode each block
			byte[] decoded = decode(interleaved[i]);
			// interleave error-corrected results back
			for (int j = 0; j < decoded.length; j++) {
				result[j * interleaving + i] = decoded[j];
			}
		}
		return result;
	}

	public static byte[] decode(byte[] data) {
		int pad = NN - NROOTS - (data.length - NROOTS);
		int FCR = 112;
		int PRIM = 11;
		int A0 = NN;
		int IPRIM = 116;
		int no_eras = 0;

		if (pad < 0 || pad > 222) {
			throw new IllegalArgumentException("invalid pad: " + pad);
		}

		int deg_lambda, el, deg_omega;
		int i, j, r, k;
		int q, tmp, num1, num2, den, discr_r;
		int[] lambda = new int[NROOTS + 1],
				s = new int[NROOTS]; /*
										 * Err+Eras Locator poly and syndrome
										 * poly
										 */
		int[] b = new int[NROOTS + 1], t = new int[NROOTS + 1], omega = new int[NROOTS + 1];
		int[] root = new int[NROOTS], reg = new int[NROOTS + 1], loc = new int[NROOTS];
		int syn_error, count;

		/* form the syndromes; i.e., evaluate data(x) at roots of g(x) */
		for (i = 0; i < NROOTS; i++)
			s[i] = data[0] & 0xff;

		for (j = 1; j < NN - pad; j++) {
			for (i = 0; i < NROOTS; i++) {
				if (s[i] == 0) {
					s[i] = data[j];
				} else {
					s[i] = data[j] ^ CCSDS_alpha_to[mod255(CCSDS_index_of[s[i]] + (FCR + i) * PRIM)];
				}
				s[i] = s[i] & 0xff;
			}
		}

		/* Convert syndromes to index form, checking for nonzero condition */
		syn_error = 0;
		for (i = 0; i < NROOTS; i++) {
			syn_error |= s[i];
			s[i] = CCSDS_index_of[s[i]];
		}

		if (syn_error == 0) {
			/*
			 * if syndrome is zero, data[] is a codeword and there are no errors
			 * to correct. So return data[] unmodified
			 */
			count = 0;
		} else {

			lambda[0] = 1;

			for (i = 0; i < NROOTS + 1; i++) {
				b[i] = CCSDS_index_of[lambda[i]];
			}

			/*
			 * Begin Berlekamp-Massey algorithm to determine error+erasure
			 * locator polynomial
			 */
			r = no_eras;
			el = no_eras;
			while (++r <= NROOTS) { /* r is the step number */
				/* Compute discrepancy at the r-th step in poly-form */
				discr_r = 0;
				for (i = 0; i < r; i++) {
					if ((lambda[i] != 0) && (s[r - i - 1] != A0)) {
						discr_r ^= CCSDS_alpha_to[mod255(CCSDS_index_of[lambda[i]] + s[r - i - 1])];
					}
				}
				discr_r = CCSDS_index_of[discr_r]; /* Index form */
				if (discr_r == A0) {
					/* 2 lines below: B(x) <-- x*B(x) */
					System.arraycopy(b, 0, b, 1, NROOTS);
					b[0] = A0;
				} else {
					/* 7 lines below: T(x) <-- lambda(x) - discr_r*x*b(x) */
					t[0] = lambda[0];
					for (i = 0; i < NROOTS; i++) {
						if (b[i] != A0)
							t[i + 1] = lambda[i + 1] ^ CCSDS_alpha_to[mod255(discr_r + b[i])];
						else
							t[i + 1] = lambda[i + 1];
					}
					if (2 * el <= r + no_eras - 1) {
						el = r + no_eras - el;
						/*
						 * 2 lines below: B(x) <-- inv(discr_r) * lambda(x)
						 */
						for (i = 0; i <= NROOTS; i++)
							b[i] = (lambda[i] == 0) ? A0 : mod255(CCSDS_index_of[lambda[i]] - discr_r + NN);
					} else {
						/* 2 lines below: B(x) <-- x*B(x) */
						System.arraycopy(b, 0, b, 1, NROOTS);
						b[0] = A0;
					}
					System.arraycopy(t, 0, lambda, 0, NROOTS + 1);
				}
			}

			/* Convert lambda to index form and compute deg(lambda(x)) */
			deg_lambda = 0;
			for (i = 0; i < NROOTS + 1; i++) {
				lambda[i] = CCSDS_index_of[lambda[i]];
				if (lambda[i] != A0)
					deg_lambda = i;
			}
			/*
			 * Find roots of the error+erasure locator polynomial by Chien
			 * search
			 */
			System.arraycopy(lambda, 1, reg, 1, NROOTS);
			count = 0; /* Number of roots of lambda(x) */
			for (i = 1, k = IPRIM - 1; i <= NN; i++, k = mod255(k + IPRIM)) {
				q = 1; /* lambda[0] is always 0 */
				for (j = deg_lambda; j > 0; j--) {
					if (reg[j] != A0) {
						reg[j] = mod255(reg[j] + j);
						q ^= CCSDS_alpha_to[reg[j]];
					}
				}
				if (q != 0)
					continue; /* Not a root */
				/* store root (index-form) and error location number */
				root[count] = i;
				loc[count] = k;
				/*
				 * If we've already found max possible roots, abort the search
				 * to save time
				 */
				if (++count == deg_lambda)
					break;
			}
			if (deg_lambda != count) {
				/*
				 * deg(lambda) unequal to number of roots => uncorrectable error
				 * detected
				 */
				throw new IllegalArgumentException("uncorrectable");
			} else {
				/*
				 * Compute err+eras evaluator poly omega(x) = s(x)*lambda(x)
				 * (modulo x**NROOTS). in index form. Also find deg(omega).
				 */
				deg_omega = deg_lambda - 1;
				for (i = 0; i <= deg_omega; i++) {
					tmp = 0;
					for (j = i; j >= 0; j--) {
						if ((s[i - j] != A0) && (lambda[j] != A0))
							tmp ^= CCSDS_alpha_to[mod255(s[i - j] + lambda[j])];
					}
					omega[i] = CCSDS_index_of[tmp];
				}

				/*
				 * Compute error values in poly-form. num1 = omega(inv(X(l))),
				 * num2 = inv(X(l))**(FCR-1) and den = lambda_pr(inv(X(l))) all
				 * in poly-form
				 */
				for (j = count - 1; j >= 0; j--) {
					num1 = 0;
					for (i = deg_omega; i >= 0; i--) {
						if (omega[i] != A0)
							num1 ^= CCSDS_alpha_to[mod255(omega[i] + i * root[j])];
					}
					num2 = CCSDS_alpha_to[mod255(root[j] * (FCR - 1) + NN)];
					den = 0;

					/*
					 * lambda[i+1] for i even is the formal derivative lambda_pr
					 * of lambda[i]
					 */
					for (i = Math.min(deg_lambda, NROOTS - 1) & ~1; i >= 0; i -= 2) {
						if (lambda[i + 1] != A0)
							den ^= CCSDS_alpha_to[mod255(lambda[i + 1] + i * root[j])];
					}
					/* Apply error to data */
					if (num1 != 0 && loc[j] >= pad) {
						data[loc[j] - pad] ^= CCSDS_alpha_to[mod255(CCSDS_index_of[num1] + CCSDS_index_of[num2] + NN - CCSDS_index_of[den])];
					}
				}
			}
		}
		
		LOG.info("corrected byte errors: " + count);
		byte[] result = new byte[data.length - NROOTS];
		System.arraycopy(data, 0, result, 0, result.length);
		return result;
	}

	public static byte[] encode(byte[] data) {
		int i, j;
		int feedback;
		int PAD = NN - data.length - NROOTS;
		int A0 = NN;

		byte[] parity = new byte[32];
		for (i = 0; i < NN - NROOTS - PAD; i++) {
			feedback = (CCSDS_index_of[(data[i] & 0xff) ^ (parity[0] & 0xff)] & 0xff);
			if (feedback != A0) { /* feedback term is non-zero */
				for (j = 1; j < NROOTS; j++)
					parity[j] ^= CCSDS_alpha_to[mod255(feedback + CCSDS_poly[NROOTS - j])];
			}

			/* Shift */
			System.arraycopy(parity, 1, parity, 0, parity.length - 1);
			parity[NROOTS - 1] = (feedback != A0) ? (byte) CCSDS_alpha_to[mod255(feedback + CCSDS_poly[0])] : 0;
		}
		byte[] result = new byte[data.length + parity.length];
		System.arraycopy(data, 0, result, 0, data.length);
		System.arraycopy(parity, 0, result, data.length, parity.length);
		return result;
	}

	private static int mod255(int x) {
		return x % 255;
	}

}
