package ru.r2cloud.jradio.blocks;

public class Firdes {

	// based on
	// http://w7ay.net/site/Technical/Extended%20Nyquist%20Filters/index.html
	public static float[] extendedRaisedCosine(double gain, double samplingFrequency, double symbolRate, double alpha, int ntaps, int order) {
		ntaps |= 1; // ensure that ntaps is odd
		double spb = samplingFrequency / symbolRate; // samples per bit/symbol
		float[] taps = new float[ntaps];
		double scale = 0;
		for (int i = (-ntaps / 2), j = 0; i <= (ntaps / 2 + 1) && j < taps.length; i++, j++) {
			taps[j] = calculateExtendedRaisedCosineTap(i, spb, alpha, order);
			scale += taps[j];
		}

		// normalize
		if (scale != 0.0f) {
			for (int i = 0; i < ntaps; i++) {
				taps[i] = (float) (taps[i] * gain / scale);
			}
		}
		return taps;
	}

	private static float calculateExtendedRaisedCosineTap(double index, double spb, double alpha, int order) {
		if (order > 1) {
			return calculateExtendedRaisedCosineTap(index - spb / 4, spb / 2, alpha, order - 1) + calculateExtendedRaisedCosineTap(index + spb / 4, spb / 2, alpha, order - 1);
		}
		if (Math.abs(index) == (spb / (2 * alpha))) {
			return (float) (Math.PI / (4 * spb) * normalizedSinc(1 / (2 * alpha)));
		}
		return (float) (1 / spb * normalizedSinc(index / spb) * Math.cos(Math.PI * alpha * index / spb) / (1 - Math.pow(2 * alpha * index / spb, 2)));
	}

	private static double normalizedSinc(double x) {
		x *= Math.PI;
		if (x == 0) {
			return 1.0;
		}
		return Math.sin(x) / x;
	}

	public static float[] gaussian(double gain, double spb, double bt, int ntaps) {
		float[] result = new float[ntaps];
		double scale = 0;
		double dt = 1.0 / spb;
		double s = 1.0 / (Math.sqrt(Math.log(2.0)) / (2 * Math.PI * bt));
		double t0 = -0.5 * ntaps;
		double ts;
		for (int i = 0; i < ntaps; i++) {
			t0++;
			ts = s * dt * t0;
			result[i] = (float) Math.exp(-0.5 * ts * ts);
			scale += result[i];
		}
		for (int i = 0; i < ntaps; i++) {
			result[i] = (float) (result[i] / scale * gain);
		}
		return result;
	}

	public static float[] lowPass(double gain, double samplingFrequency, double cutoffFrequency, double transitionWidth, Window windowType, double beta) {
		sanityCheck1f(samplingFrequency, cutoffFrequency, transitionWidth);

		int ntaps = computeNtaps(samplingFrequency, transitionWidth, windowType, beta);

		// construct the truncated ideal impulse response
		// [sin(x)/x for the low pass case]
		float[] taps = new float[ntaps];
		float[] w = windowType.build(ntaps, beta);

		int m = (ntaps - 1) / 2;
		double fwT0 = 2 * Math.PI * cutoffFrequency / samplingFrequency;

		for (int n = -m; n <= m; n++) {
			if (n == 0)
				taps[n + m] = (float) (fwT0 / Math.PI * w[n + m]);
			else {
				// a little algebra gets this into the more familiar sin(x)/x
				// form
				taps[n + m] = (float) (Math.sin(n * fwT0) / (n * Math.PI) * w[n + m]);
			}
		}

		// find the factor to normalize the gain, fmax.
		// For low-pass, gain @ zero freq = 1.0

		double fmax = taps[0 + m];
		for (int n = 1; n <= m; n++) {
			fmax += 2 * taps[n + m];
		}

		gain /= fmax; // normalize

		for (int i = 0; i < ntaps; i++) {
			taps[i] *= gain;
		}

		return taps;
	}

	private static void sanityCheck1f(double samplingFrequency, double fa, double transitionWidth) {
		if (samplingFrequency <= 0.0) {
			throw new IllegalArgumentException("firdes check failed: sampling_freq > 0");
		}

		if (fa <= 0.0 || fa > samplingFrequency / 2) {
			throw new IllegalArgumentException("firdes check failed: 0 < fa <= sampling_freq / 2");
		}

		if (transitionWidth <= 0) {
			throw new IllegalArgumentException("firdes check failed: transition_width > 0");
		}
	}

	private static int computeNtaps(double samplingFrequency, double transitionWidth, Window windowType, double beta) {
		double a = windowType.maxAttenuation(beta);
		int ntaps = (int) (a * samplingFrequency / (22.0 * transitionWidth));
		if ((ntaps & 1) == 0) { // if even...
			ntaps++; // ...make odd
		}
		return ntaps;
	}

	public static float[] rootRaisedCosine(double gain, double samplingFrequency, double symbolRate, double alpha, int ntaps) {
		ntaps |= 1; // ensure that ntaps is odd

		double spb = samplingFrequency / symbolRate; // samples per bit/symbol
		float[] taps = new float[ntaps];
		double scale = 0;
		for (int i = 0; i < ntaps; i++) {
			double x1;
			double x2;
			double x3;
			double num;
			double den;
			double xindx = (double) i - ntaps / 2; // ntaps are expected to round here
			x1 = Math.PI * xindx / spb;
			x2 = 4 * alpha * xindx / spb;
			x3 = x2 * x2 - 1;

			if (Math.abs(x3) >= 0.000001) { // Avoid Rounding errors...
				if (i != ntaps / 2) {
					num = Math.cos((1 + alpha) * x1) + Math.sin((1 - alpha) * x1) / (4 * alpha * xindx / spb);
				} else {
					num = Math.cos((1 + alpha) * x1) + (1 - alpha) * Math.PI / (4 * alpha);
				}
				den = x3 * Math.PI;
			} else {
				if (alpha == 1) {
					taps[i] = -1;
					continue;
				}
				x3 = (1 - alpha) * x1;
				x2 = (1 + alpha) * x1;
				num = (Math.sin(x2) * (1 + alpha) * Math.PI - Math.cos(x3) * ((1 - alpha) * Math.PI * spb) / (4 * alpha * xindx) + Math.sin(x3) * spb * spb / (4 * alpha * xindx * xindx));
				den = -32 * Math.PI * alpha * alpha * xindx / spb;
			}
			taps[i] = (float) (4 * alpha * num / den);
			scale += taps[i];
		}

		if (scale != 0.0f) {
			for (int i = 0; i < ntaps; i++) {
				taps[i] = (float) (taps[i] * gain / scale);
			}
		}

		return taps;
	}

	public static float[] convertToComplex(float[] realTaps) {
		float[] complexTaps = new float[realTaps.length * 2];
		for (int i = 0; i < realTaps.length; i++) {
			complexTaps[2 * i] = realTaps[i];
			complexTaps[2 * i + 1] = 0.0f;
		}
		return complexTaps;
	}

	private Firdes() {
		// do nothing
	}
}
