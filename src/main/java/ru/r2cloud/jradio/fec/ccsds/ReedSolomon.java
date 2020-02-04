package ru.r2cloud.jradio.fec.ccsds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReedSolomon {

	private static final Logger LOG = LoggerFactory.getLogger(ReedSolomon.class);
	public static final ReedSolomon CCSDS = new ReedSolomon(8, 0x187, 112, 11, 32);

	private final int mm;
	private final int nn;
	private final int gfpoly;
	private final int fcr;
	private final int prim;
	private final int nroots;
	private final byte[] interleaved;

	private int iprim;
	private int[] indexOf;
	private int[] alphaTo;
	private int[] genpoly;
	private byte[] cdata;

	private int a0;

	public ReedSolomon(int symsize, int gfpoly, int fcr, int prim, int nroots) {
		if (symsize < 0 || symsize > 8) {
			throw new IllegalArgumentException();
		}
		if (fcr < 0 || fcr >= (1 << symsize)) {
			throw new IllegalArgumentException();
		}
		if (prim <= 0 || prim >= (1 << symsize)) {
			throw new IllegalAccessError();
		}
		if (nroots < 0 || nroots >= (1 << symsize)) {
			throw new IllegalArgumentException(); /* Can't have more roots than symbol values! */
		}
		this.mm = symsize;
		this.nn = (1 << symsize) - 1;
		this.a0 = nn;
		this.gfpoly = gfpoly;
		this.fcr = fcr;
		this.prim = prim;
		this.nroots = nroots;
		this.interleaved = new byte[nn];
		generateTables();
		cdata = new byte[nn];
	}

	private void generateTables() {
		int i;
		int j;
		int sr;
		int root;
		alphaTo = new int[nn + 1];
		indexOf = new int[nn + 1];

		/* Generate Galois field lookup tables */
		indexOf[0] = a0; /* log(zero) = -inf */
		alphaTo[a0] = 0; /* alpha**-inf = 0 */
		sr = 1;
		for (i = 0; i < nn; i++) {
			indexOf[sr] = i;
			alphaTo[i] = sr;
			sr <<= 1;
			if ((sr & (1 << mm)) > 0)
				sr ^= gfpoly;
			sr &= nn;
		}
		if (sr != 1) {
			throw new IllegalArgumentException("field generator polynomial is not primitive");
		}

		/* Form RS code generator polynomial from its roots */
		genpoly = new int[nroots + 1];

		/* Find prim-th root of 1, used in decoding */
		for (iprim = 1; (iprim % prim) != 0; iprim += nn) {
			// do nothing
		}

		iprim = iprim / prim;

		genpoly[0] = 1;
		for (i = 0, root = fcr * prim; i < nroots; i++, root += prim) {
			genpoly[i + 1] = 1;

			/* Multiply rs->genpoly[] by @**(root + x) */
			for (j = i; j > 0; j--) {
				if (genpoly[j] != 0)
					genpoly[j] = genpoly[j - 1] ^ alphaTo[modnn(indexOf[genpoly[j]] + root)];
				else
					genpoly[j] = genpoly[j - 1];
			}
			/* rs->genpoly[0] can never be zero */
			genpoly[0] = alphaTo[modnn(indexOf[genpoly[0]] + root)];
		}
		/* convert rs->genpoly[] to index form for quicker encoding */
		for (i = 0; i <= nroots; i++)
			genpoly[i] = indexOf[genpoly[i]];

	}

	private int modnn(int x) {
		while (x >= nn) {
			x -= nn;
			x = (x >> mm) + (x & nn);
		}
		return x;
	}

	public static byte[] decode(byte[] data) throws UncorrectableException {
		return CCSDS.decodeData(data);
	}

	public static byte[] decode(byte[] data, int interleaving) throws UncorrectableException {
		return CCSDS.decodeData(data, interleaving);
	}

	public byte[] decodeData(byte[] data, int interleaving) throws UncorrectableException {
		byte[] result = null;
		for (int i = 0; i < interleaving; i++) {
			// deinterleave
			// transform 0 1 2 3 4 5 6 7 p1 p2 p3 p4 bytes into ${interleaving} blocks:
			// 0 4 p1
			// 1 5 p2
			// 2 6 p3
			// 3 7 p4 &etc
			for (int j = 0; j < interleaved.length; j++) {
				interleaved[j] = data[j * interleaving + i];
			}
			// decode each block
			byte[] decoded = decodeData(interleaved);
			// allocate lazily
			// decodeData might fail, so no need to generate array for that
			if (result == null) {
				result = new byte[data.length - nroots * interleaving];
			}
			// interleave error-corrected results back
			for (int j = 0; j < decoded.length; j++) {
				result[j * interleaving + i] = decoded[j];
			}
		}
		return result;
	}

	public byte[] decodeDualBasis(byte[] data, int interleaving) throws UncorrectableException {
		byte[] result = null;
		for (int i = 0; i < interleaving; i++) {
			// deinterleave
			// transform 0 1 2 3 4 5 6 7 p1 p2 p3 p4 bytes into ${interleaving} blocks:
			// 0 4 p1
			// 1 5 p2
			// 2 6 p3
			// 3 7 p4 &etc
			for (int j = 0; j < interleaved.length; j++) {
				interleaved[j] = data[j * interleaving + i];
			}
			// decode each block
			byte[] decoded = decodeDualBasis(interleaved);
			// allocate lazily
			// decodeData might fail, so no need to generate array for that
			if (result == null) {
				result = new byte[data.length - nroots * interleaving];
			}
			// interleave error-corrected results back
			for (int j = 0; j < decoded.length; j++) {
				result[j * interleaving + i] = decoded[j];
			}
		}
		return result;
	}

	public byte[] decodeDualBasis(byte[] data) throws UncorrectableException {
		for (int i = 0; i < data.length; i++) {
			cdata[i] = (byte) TAL1TAB[(data[i] & 0xFF)];
		}
		decode(cdata);
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) TALTAB[(cdata[i] & 0xFF)];
		}
		byte[] result = new byte[data.length - nroots];
		System.arraycopy(data, 0, result, 0, result.length);
		return result;

	}

	public byte[] decodeData(byte[] data) throws UncorrectableException {
		int pad = nn - nroots - (data.length - nroots);
		int noEras = 0;

		if (pad < 0 || pad > 222) {
			throw new IllegalArgumentException("invalid pad: " + pad);
		}

		int degLambda;
		int el;
		int degOmega;
		int i;
		int j;
		int r;
		int k;
		int q;
		int tmp;
		int num1;
		int num2;
		int den;
		int discrR;
		int[] lambda = new int[nroots + 1];
		int[] s = new int[nroots];
		int[] b = new int[nroots + 1];
		int[] t = new int[nroots + 1];
		int[] omega = new int[nroots + 1];
		int[] root = new int[nroots];
		int[] reg = new int[nroots + 1];
		int[] loc = new int[nroots];
		int synError;
		int count;

		/* form the syndromes; i.e., evaluate data(x) at roots of g(x) */
		for (i = 0; i < nroots; i++)
			s[i] = data[0] & 0xff;

		for (j = 1; j < nn - pad; j++) {
			for (i = 0; i < nroots; i++) {
				if (s[i] == 0) {
					s[i] = data[j];
				} else {
					s[i] = data[j] ^ alphaTo[modnn(indexOf[s[i]] + (fcr + i) * prim)];
				}
				s[i] = s[i] & 0xff;
			}
		}

		/* Convert syndromes to index form, checking for nonzero condition */
		synError = 0;
		for (i = 0; i < nroots; i++) {
			synError |= s[i];
			s[i] = indexOf[s[i]];
		}

		if (synError == 0) {
			/*
			 * if syndrome is zero, data[] is a codeword and there are no errors to correct. So return data[] unmodified
			 */
			count = 0;
		} else {

			lambda[0] = 1;

			for (i = 0; i < nroots + 1; i++) {
				b[i] = indexOf[lambda[i]];
			}

			/*
			 * Begin Berlekamp-Massey algorithm to determine error+erasure locator polynomial
			 */
			r = noEras;
			el = noEras;
			while (++r <= nroots) { /* r is the step number */
				/* Compute discrepancy at the r-th step in poly-form */
				discrR = 0;
				for (i = 0; i < r; i++) {
					if ((lambda[i] != 0) && (s[r - i - 1] != a0)) {
						discrR ^= alphaTo[modnn(indexOf[lambda[i]] + s[r - i - 1])];
					}
				}
				discrR = indexOf[discrR]; /* Index form */
				if (discrR == a0) {
					/* 2 lines below: B(x) <-- x*B(x) */
					System.arraycopy(b, 0, b, 1, nroots);
					b[0] = a0;
				} else {
					/* 7 lines below: T(x) <-- lambda(x) - discr_r*x*b(x) */
					t[0] = lambda[0];
					for (i = 0; i < nroots; i++) {
						if (b[i] != a0)
							t[i + 1] = lambda[i + 1] ^ alphaTo[modnn(discrR + b[i])];
						else
							t[i + 1] = lambda[i + 1];
					}
					if (2 * el <= r + noEras - 1) {
						el = r + noEras - el;
						/*
						 * 2 lines below: B(x) <-- inv(discr_r) * lambda(x)
						 */
						for (i = 0; i <= nroots; i++)
							b[i] = (lambda[i] == 0) ? a0 : modnn(indexOf[lambda[i]] - discrR + nn);
					} else {
						/* 2 lines below: B(x) <-- x*B(x) */
						System.arraycopy(b, 0, b, 1, nroots);
						b[0] = a0;
					}
					System.arraycopy(t, 0, lambda, 0, nroots + 1);
				}
			}

			/* Convert lambda to index form and compute deg(lambda(x)) */
			degLambda = 0;
			for (i = 0; i < nroots + 1; i++) {
				lambda[i] = indexOf[lambda[i]];
				if (lambda[i] != a0)
					degLambda = i;
			}
			/*
			 * Find roots of the error+erasure locator polynomial by Chien search
			 */
			System.arraycopy(lambda, 1, reg, 1, nroots);
			count = 0; /* Number of roots of lambda(x) */
			for (i = 1, k = iprim - 1; i <= nn; i++, k = modnn(k + iprim)) {
				q = 1; /* lambda[0] is always 0 */
				for (j = degLambda; j > 0; j--) {
					if (reg[j] != a0) {
						reg[j] = modnn(reg[j] + j);
						q ^= alphaTo[reg[j]];
					}
				}
				if (q != 0)
					continue; /* Not a root */
				/* store root (index-form) and error location number */
				root[count] = i;
				loc[count] = k;
				/*
				 * If we've already found max possible roots, abort the search to save time
				 */
				if (++count == degLambda)
					break;
			}
			if (degLambda != count) {
				/*
				 * deg(lambda) unequal to number of roots => uncorrectable error detected
				 */
				throw new UncorrectableException("uncorrectable");
			} else {
				/*
				 * Compute err+eras evaluator poly omega(x) = s(x)*lambda(x) (modulo x**NROOTS). in index form. Also find deg(omega).
				 */
				degOmega = degLambda - 1;
				for (i = 0; i <= degOmega; i++) {
					tmp = 0;
					for (j = i; j >= 0; j--) {
						if ((s[i - j] != a0) && (lambda[j] != a0))
							tmp ^= alphaTo[modnn(s[i - j] + lambda[j])];
					}
					omega[i] = indexOf[tmp];
				}

				/*
				 * Compute error values in poly-form. num1 = omega(inv(X(l))), num2 = inv(X(l))**(FCR-1) and den = lambda_pr(inv(X(l))) all in poly-form
				 */
				for (j = count - 1; j >= 0; j--) {
					num1 = 0;
					for (i = degOmega; i >= 0; i--) {
						if (omega[i] != a0)
							num1 ^= alphaTo[modnn(omega[i] + i * root[j])];
					}
					num2 = alphaTo[modnn(root[j] * (fcr - 1) + nn)];
					den = 0;

					/*
					 * lambda[i+1] for i even is the formal derivative lambda_pr of lambda[i]
					 */
					for (i = Math.min(degLambda, nroots - 1) & ~1; i >= 0; i -= 2) {
						if (lambda[i + 1] != a0)
							den ^= alphaTo[modnn(lambda[i + 1] + i * root[j])];
					}
					/* Apply error to data */
					if (num1 != 0 && loc[j] >= pad) {
						data[loc[j] - pad] ^= alphaTo[modnn(indexOf[num1] + indexOf[num2] + nn - indexOf[den])];
					}
				}
			}
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("corrected byte errors: {}", count);
		}
		byte[] result = new byte[data.length - nroots];
		System.arraycopy(data, 0, result, 0, result.length);
		return result;
	}

	public static byte[] encode(byte[] data) {
		return CCSDS.encodeData(data);
	}

	public byte[] encodeData(byte[] data) {
		int i;
		int j;
		int feedback;
		int pad = nn - data.length - nroots;
		int a0Local = nn;

		byte[] parity = new byte[32];
		for (i = 0; i < nn - nroots - pad; i++) {
			feedback = (indexOf[(data[i] & 0xff) ^ (parity[0] & 0xff)] & 0xff);
			if (feedback != a0Local) { /* feedback term is non-zero */
				for (j = 1; j < nroots; j++)
					parity[j] ^= alphaTo[modnn(feedback + genpoly[nroots - j])];
			}

			/* Shift */
			System.arraycopy(parity, 1, parity, 0, parity.length - 1);
			parity[nroots - 1] = (feedback != a0Local) ? (byte) alphaTo[modnn(feedback + genpoly[0])] : 0;
		}
		byte[] result = new byte[data.length + parity.length];
		System.arraycopy(data, 0, result, 0, data.length);
		System.arraycopy(parity, 0, result, data.length, parity.length);
		return result;
	}

	private static final int[] TALTAB = new int[] { 0x00, 0x7b, 0xaf, 0xd4, 0x99, 0xe2, 0x36, 0x4d, 0xfa, 0x81, 0x55, 0x2e, 0x63, 0x18, 0xcc, 0xb7, 0x86, 0xfd, 0x29, 0x52, 0x1f, 0x64, 0xb0, 0xcb, 0x7c, 0x07, 0xd3, 0xa8, 0xe5, 0x9e, 0x4a, 0x31, 0xec, 0x97, 0x43, 0x38, 0x75, 0x0e, 0xda, 0xa1, 0x16, 0x6d, 0xb9, 0xc2, 0x8f, 0xf4, 0x20, 0x5b, 0x6a, 0x11, 0xc5, 0xbe, 0xf3, 0x88, 0x5c, 0x27, 0x90, 0xeb, 0x3f, 0x44, 0x09, 0x72, 0xa6, 0xdd, 0xef, 0x94, 0x40, 0x3b, 0x76, 0x0d, 0xd9, 0xa2, 0x15, 0x6e,
			0xba, 0xc1, 0x8c, 0xf7, 0x23, 0x58, 0x69, 0x12, 0xc6, 0xbd, 0xf0, 0x8b, 0x5f, 0x24, 0x93, 0xe8, 0x3c, 0x47, 0x0a, 0x71, 0xa5, 0xde, 0x03, 0x78, 0xac, 0xd7, 0x9a, 0xe1, 0x35, 0x4e, 0xf9, 0x82, 0x56, 0x2d, 0x60, 0x1b, 0xcf, 0xb4, 0x85, 0xfe, 0x2a, 0x51, 0x1c, 0x67, 0xb3, 0xc8, 0x7f, 0x04, 0xd0, 0xab, 0xe6, 0x9d, 0x49, 0x32, 0x8d, 0xf6, 0x22, 0x59, 0x14, 0x6f, 0xbb, 0xc0, 0x77, 0x0c, 0xd8, 0xa3, 0xee, 0x95, 0x41, 0x3a, 0x0b, 0x70, 0xa4, 0xdf, 0x92, 0xe9, 0x3d, 0x46, 0xf1, 0x8a, 0x5e,
			0x25, 0x68, 0x13, 0xc7, 0xbc, 0x61, 0x1a, 0xce, 0xb5, 0xf8, 0x83, 0x57, 0x2c, 0x9b, 0xe0, 0x34, 0x4f, 0x02, 0x79, 0xad, 0xd6, 0xe7, 0x9c, 0x48, 0x33, 0x7e, 0x05, 0xd1, 0xaa, 0x1d, 0x66, 0xb2, 0xc9, 0x84, 0xff, 0x2b, 0x50, 0x62, 0x19, 0xcd, 0xb6, 0xfb, 0x80, 0x54, 0x2f, 0x98, 0xe3, 0x37, 0x4c, 0x01, 0x7a, 0xae, 0xd5, 0xe4, 0x9f, 0x4b, 0x30, 0x7d, 0x06, 0xd2, 0xa9, 0x1e, 0x65, 0xb1, 0xca, 0x87, 0xfc, 0x28, 0x53, 0x8e, 0xf5, 0x21, 0x5a, 0x17, 0x6c, 0xb8, 0xc3, 0x74, 0x0f, 0xdb, 0xa0,
			0xed, 0x96, 0x42, 0x39, 0x08, 0x73, 0xa7, 0xdc, 0x91, 0xea, 0x3e, 0x45, 0xf2, 0x89, 0x5d, 0x26, 0x6b, 0x10, 0xc4, 0xbf, };

	private static final int[] TAL1TAB = new int[] { 0x00, 0xcc, 0xac, 0x60, 0x79, 0xb5, 0xd5, 0x19, 0xf0, 0x3c, 0x5c, 0x90, 0x89, 0x45, 0x25, 0xe9, 0xfd, 0x31, 0x51, 0x9d, 0x84, 0x48, 0x28, 0xe4, 0x0d, 0xc1, 0xa1, 0x6d, 0x74, 0xb8, 0xd8, 0x14, 0x2e, 0xe2, 0x82, 0x4e, 0x57, 0x9b, 0xfb, 0x37, 0xde, 0x12, 0x72, 0xbe, 0xa7, 0x6b, 0x0b, 0xc7, 0xd3, 0x1f, 0x7f, 0xb3, 0xaa, 0x66, 0x06, 0xca, 0x23, 0xef, 0x8f, 0x43, 0x5a, 0x96, 0xf6, 0x3a, 0x42, 0x8e, 0xee, 0x22, 0x3b, 0xf7, 0x97, 0x5b, 0xb2, 0x7e,
			0x1e, 0xd2, 0xcb, 0x07, 0x67, 0xab, 0xbf, 0x73, 0x13, 0xdf, 0xc6, 0x0a, 0x6a, 0xa6, 0x4f, 0x83, 0xe3, 0x2f, 0x36, 0xfa, 0x9a, 0x56, 0x6c, 0xa0, 0xc0, 0x0c, 0x15, 0xd9, 0xb9, 0x75, 0x9c, 0x50, 0x30, 0xfc, 0xe5, 0x29, 0x49, 0x85, 0x91, 0x5d, 0x3d, 0xf1, 0xe8, 0x24, 0x44, 0x88, 0x61, 0xad, 0xcd, 0x01, 0x18, 0xd4, 0xb4, 0x78, 0xc5, 0x09, 0x69, 0xa5, 0xbc, 0x70, 0x10, 0xdc, 0x35, 0xf9, 0x99, 0x55, 0x4c, 0x80, 0xe0, 0x2c, 0x38, 0xf4, 0x94, 0x58, 0x41, 0x8d, 0xed, 0x21, 0xc8, 0x04, 0x64,
			0xa8, 0xb1, 0x7d, 0x1d, 0xd1, 0xeb, 0x27, 0x47, 0x8b, 0x92, 0x5e, 0x3e, 0xf2, 0x1b, 0xd7, 0xb7, 0x7b, 0x62, 0xae, 0xce, 0x02, 0x16, 0xda, 0xba, 0x76, 0x6f, 0xa3, 0xc3, 0x0f, 0xe6, 0x2a, 0x4a, 0x86, 0x9f, 0x53, 0x33, 0xff, 0x87, 0x4b, 0x2b, 0xe7, 0xfe, 0x32, 0x52, 0x9e, 0x77, 0xbb, 0xdb, 0x17, 0x0e, 0xc2, 0xa2, 0x6e, 0x7a, 0xb6, 0xd6, 0x1a, 0x03, 0xcf, 0xaf, 0x63, 0x8a, 0x46, 0x26, 0xea, 0xf3, 0x3f, 0x5f, 0x93, 0xa9, 0x65, 0x05, 0xc9, 0xd0, 0x1c, 0x7c, 0xb0, 0x59, 0x95, 0xf5, 0x39,
			0x20, 0xec, 0x8c, 0x40, 0x54, 0x98, 0xf8, 0x34, 0x2d, 0xe1, 0x81, 0x4d, 0xa4, 0x68, 0x08, 0xc4, 0xdd, 0x11, 0x71, 0xbd, };

}
