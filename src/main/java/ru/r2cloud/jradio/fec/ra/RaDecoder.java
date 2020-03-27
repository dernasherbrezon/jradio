package ru.r2cloud.jradio.fec.ra;

import java.util.Arrays;

public class RaDecoder {

	private static final int RA_MAX_DATA_LENGTH = 2048;
	private static final int RA_PUNCTURE_RATE = 3;
	private static final int RA_MAX_CODE_LENGTH = RA_MAX_DATA_LENGTH * 2 + 3;
	private static final int RA_BITCOUNT = 16;

	private final int size;
	private final int raDataLength;
	private final int raChckLength;
	private final int raCodeLength;
	private final RaLfsr[] raLfsrMasks = new RaLfsr[4];
	private final float[] raDatawordGen = new float[RA_MAX_DATA_LENGTH * RA_BITCOUNT];
	private final float[] raCodewordGen = new float[RA_MAX_CODE_LENGTH * RA_BITCOUNT];
	private final float[] raForwardGen = new float[RA_MAX_DATA_LENGTH * RA_BITCOUNT];

	private static final int[][] RA_LFSR_MASKS_TABLE = new int[][] { new int[] { 0x12, 0x17, 0x1B, 0x1E }, // highbit 4, data_length <= 31
			new int[] { 0x21, 0x2D, 0x30, 0x39 }, // highbit 5, data_length <= 63
			new int[] { 0x41, 0x53, 0x69, 0x7B }, // highbit 6, data_length <= 127
			new int[] { 0x8E, 0xAF, 0xC3, 0xE7 }, // highbit 7, data_length <= 255
			new int[] { 0x108, 0x13B, 0x168, 0x1DC }, // highbit 8, data_length <= 511
			new int[] { 0x204, 0x2E3, 0x369, 0x3AA }, // highbit 9, data_length <= 1023
			new int[] { 0x415, 0x4BF, 0x553, 0x62B }, // highbit 10, data_length <= 2047
			new int[] { 0x83E, 0x939, 0xAF5, 0xD70 }, // highbit 11, data_length <= 4095
			new int[] { 0x1013, 0x109D, 0x117D, 0x1271 }, // highbit 12, data_length <= 8191
	};
	private int raLfsrHighbit;
	private float[] bits;

	public RaDecoder(int size) {
		this.size = size;
		int dataLength = size / 2;
		if (dataLength < 4 || dataLength > RA_MAX_DATA_LENGTH) {
			throw new IllegalArgumentException("invalid size:" + size);
		}
		raDataLength = dataLength;
		raChckLength = (dataLength + RA_PUNCTURE_RATE - 1) / RA_PUNCTURE_RATE;
		raCodeLength = dataLength + raChckLength * 3;
		if (raCodeLength > RA_MAX_CODE_LENGTH) {
			throw new IllegalArgumentException("invalid code length: " + raCodeLength);
		}

		raLfsrHighbit = 4;
		while (dataLength >= 32) {
			dataLength /= 2;
			raLfsrHighbit += 1;
		}
		if (raLfsrHighbit < 4 || raLfsrHighbit > 12) {
			throw new IllegalStateException("invalid lfsr high bit: " + raLfsrHighbit);
		}

		raLfsrMasks[0] = initLfsr(0);
		raLfsrMasks[1] = initLfsr(1);
		raLfsrMasks[2] = initLfsr(2);
		raLfsrMasks[3] = initLfsr(3);

		bits = new float[raCodeLength * RA_BITCOUNT]; // ra_code_length
	}

	private RaLfsr initLfsr(int seqno) {
		int offset = raDataLength >> (1 + seqno);
		int state = 1 + seqno + offset;
		return new RaLfsr(RA_LFSR_MASKS_TABLE[raLfsrHighbit - 4][seqno], offset, state, raDataLength, raLfsrHighbit);
	}

	public byte[] decode(byte[] raw) {
		for (int i = 0; i < raCodeLength * RA_BITCOUNT / 8; i++) {
			for (int j = 0; j < 8; j++) {
				bits[8 * i + j] = -convert(raw[8 * i + 7 - j]);
			}
		}

		Arrays.fill(raDatawordGen, 0.0f);
		Arrays.fill(raCodewordGen, 0.0f);
		System.arraycopy(bits, 0, raCodewordGen, 0, bits.length);
		int passes = 20;

		float[] codeword;

		for (int count = 0; count < passes; count++) {
			int codeOffset = 0;
			codeword = raCodewordGen;

			for (int seqno = 0; seqno < 4; seqno++) {
				RaLfsr lfsr = raLfsrMasks[seqno];
				raImproveGen(lfsr, codeword, codeOffset, seqno == 0 ? 1 : RA_PUNCTURE_RATE, count > 0);
				lfsr.reset();
				if (seqno == 0) {
					codeOffset += raDataLength * RA_BITCOUNT;
				} else {
					codeOffset += raChckLength * RA_BITCOUNT;
				}
			}
		}

		byte[] packet = new byte[size];
		for (int i = 0; i < raDataLength * RA_BITCOUNT / 8; i++) {
			int word = 0;
			for (int j = 0; j < 8; j++) {
				float data = raDatawordGen[8 * i + j];
				int bit;
				if (data < 0.0f) {
					bit = 1;
				} else {
					bit = 0;
				}
				word |= (bit << j);
			}
			packet[i] = (byte) word;
		}
		return packet;
	}

	private void raImproveGen(RaLfsr lfsr, float[] codeword, int codeOffset, int puncture, boolean half) {
		int index = 0;
		int bit = 0;
		int pos = 0;
		float[] accu = new float[RA_BITCOUNT];
		float data;
		float left;

		Arrays.fill(accu, Float.MAX_VALUE);

		for (index = 0; index < raDataLength; index++) {
			pos = lfsr.next();

			for (bit = 0; bit < RA_BITCOUNT; bit++) {
				data = raDatawordGen[pos * RA_BITCOUNT + bit];
				raForwardGen[index * RA_BITCOUNT + bit] = accu[bit];
				accu[bit] = raLlrMin(accu[bit], data);
			}

			if ((index + 1) % puncture == 0) {
				for (bit = 0; bit < RA_BITCOUNT; bit++) {
					accu[bit] += codeword[codeOffset];
					codeOffset++;
				}
			}

			data = accu[0];
			for (bit = 0; bit < RA_BITCOUNT - 1; bit++) {
				accu[bit] = accu[bit + 1];
			}
			accu[RA_BITCOUNT - 1] = data;

		}
		if (raDataLength % puncture != 0) {
			for (bit = 0; bit < RA_BITCOUNT; bit++) {
				data = codeword[codeOffset + (bit + 1) % RA_BITCOUNT];
				accu[bit] = accu[bit] + data + data;
			}
		}

		for (index = raDataLength - 1; index >= 0; index--) {
			data = accu[RA_BITCOUNT - 1];
			for (bit = RA_BITCOUNT - 1; bit >= 1; bit--) {
				accu[bit] = accu[bit - 1];
			}
			accu[0] = data;

			if ((index + 1) % puncture == 0) {
				for (bit = RA_BITCOUNT - 1; bit >= 0; bit--) {
					codeOffset--;
					accu[bit] += codeword[codeOffset];
				}
			}

			for (bit = 0; bit < RA_BITCOUNT; bit++) {
				left = raForwardGen[index * RA_BITCOUNT + bit];
				left = raLlrMin(left, accu[bit]);

				data = raDatawordGen[pos * RA_BITCOUNT + bit];
				accu[bit] = raLlrMin(accu[bit], data);

				if (half) {
					data *= 0.5f;
				}

				left += data;
				raDatawordGen[pos * RA_BITCOUNT + bit] = left;
			}
			pos = lfsr.prev();
		}
	}

	private static float raLlrMin(float a, float b) {
		float c;

		c = a * b;
		a = Math.abs(a);
		b = Math.abs(b);

		a = a < b ? a : b;
		if (c < 0) {
			return -a;
		} else {
			return a;
		}
	}

	private static float convert(byte b) {
		float cur = b / 127.0f;
		if (cur > 1.0f) {
			cur = 1.0f;
		} else if (cur < -1.0f) {
			cur = -1.0f;
		}
		return cur;
	}

}
