package ru.r2cloud.jradio.fec.ccsds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReedSolomon {

	private static final Logger LOG = LoggerFactory.getLogger(ReedSolomon.class);

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
		ReedSolomon rs = new ReedSolomon(8, 0x187, 112, 11, 32);
		return rs.decodeData(data);
	}

	public static byte[] decode(byte[] data, int interleaving) throws UncorrectableException {
		ReedSolomon rs = new ReedSolomon(8, 0x187, 112, 11, 32);
		return rs.decodeData(data, interleaving);
	}

	public byte[] decodeData(byte[] data, int interleaving) throws UncorrectableException {
		byte[] result = new byte[data.length - nroots * interleaving];
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
			// interleave error-corrected results back
			for (int j = 0; j < decoded.length; j++) {
				result[j * interleaving + i] = decoded[j];
			}
		}
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
		ReedSolomon rs = new ReedSolomon(8, 0x187, 112, 11, 32);
		return rs.encodeData(data);
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


}
