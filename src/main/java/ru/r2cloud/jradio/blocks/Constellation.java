package ru.r2cloud.jradio.blocks;

public class Constellation {

	private final float[] constell;
	private final int[] preDiffCode;
	private final int rotationalSymmetry;
	private final int dimensionality;

	private final float[] temp;
	private final float[] result;
	private final int M;
	private final int k;

	public Constellation(float[] constell, int[] preDiffCode, int rotationalSymmetry, int dimensionality) {
		this.constell = constell;
		this.preDiffCode = preDiffCode;
		this.rotationalSymmetry = rotationalSymmetry;
		this.dimensionality = dimensionality;
		// Scale constellation points so that average magnitude is 1.
		float summedMagnitude = 0;
		for (int i = 0; i < constell.length; i += 2) {
			summedMagnitude += Math.sqrt(constell[i] * constell[i] + constell[i + 1] * constell[i + 1]);
		}
		float scalefactor = (float) constell.length / 2 / summedMagnitude;
		for (int i = 0; i < constell.length; i++) {
			constell[i] = constell[i] * scalefactor;
		}
		this.M = constell.length / 2;
		this.k = (int) (Math.log(M) / Math.log(2.0));
		this.temp = new float[2 * k];
		this.result = new float[k];
	}

	public float[] softDecisionMaker(float[] sample) {
		int v;
		for (int i = 0; i < temp.length; i++) {
			temp[i] = 0.0f;
		}
		for (int i = 0; i < M; i++) {
			// Calculate the distance between the sample and the current
			// constellation point.
			float distance = (float) Math.sqrt((sample[0] - constell[2 * i]) * (sample[0] - constell[2 * i]) + (sample[1] - constell[2 * i + 1]) * (sample[1] - constell[2 * i + 1]));
			// Calculate the probability factor from the distance and
			// the scaled noise power.
			float noisePower = 1.0f;
			float d = (float) Math.exp(-distance / noisePower);

			if (preDiffCode.length > 0) {
				v = preDiffCode[i];
			} else {
				v = i;
			}

			for (int j = 0; j < k; j++) {
				// Get the bit at the jth index
				int mask = 1 << j;
				int bit = (v & mask) >> j;

				if (bit == 0) {
					// If the bit is a 0, add to the probability of a zero
					temp[2 * j + 0] += d;
				} else {
					// else, add to the probability of a one
					temp[2 * j + 1] += d;
				}
			}
		}

		// Calculate the log-likelihood ratio for all bits based on the
		// probability of ones (tmp[2*i+1]) over the probability of a zero
		// (tmp[2*i+0]).
		for (int i = 0; i < k; i++) {
			result[k - 1 - i] = (float) (Math.log(temp[2 * i + 1]) - Math.log(temp[2 * i + 0]));
		}

		return result;
	}

	public int getRotationalSymmetry() {
		return rotationalSymmetry;
	}

	public int getDimensionality() {
		return dimensionality;
	}

}
