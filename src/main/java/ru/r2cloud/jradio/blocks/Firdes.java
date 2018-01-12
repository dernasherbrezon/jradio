package ru.r2cloud.jradio.blocks;

public class Firdes {

	public static float[] lowPass(double gain, double sampling_freq, double cutoff_freq, double transition_width, Window window_type, double beta) {
		sanity_check_1f(sampling_freq, cutoff_freq, transition_width);

		int ntaps = compute_ntaps(sampling_freq, transition_width, window_type, beta);

		// construct the truncated ideal impulse response
		// [sin(x)/x for the low pass case]
		float[] taps = new float[ntaps];
		float[] w = window_type.build(ntaps, beta);

		int M = (ntaps - 1) / 2;
		double fwT0 = 2 * Math.PI * cutoff_freq / sampling_freq;

		for (int n = -M; n <= M; n++) {
			if (n == 0)
				taps[n + M] = (float) (fwT0 / Math.PI * w[n + M]);
			else {
				// a little algebra gets this into the more familiar sin(x)/x
				// form
				taps[n + M] = (float) (Math.sin(n * fwT0) / (n * Math.PI) * w[n + M]);
			}
		}

		// find the factor to normalize the gain, fmax.
		// For low-pass, gain @ zero freq = 1.0

		double fmax = taps[0 + M];
		for (int n = 1; n <= M; n++) {
			fmax += 2 * taps[n + M];
		}

		gain /= fmax; // normalize

		for (int i = 0; i < ntaps; i++) {
			taps[i] *= gain;
		}

		return taps;
	}

	private static void sanity_check_1f(double sampling_freq, double fa, double transition_width) {
		if (sampling_freq <= 0.0) {
			throw new IllegalArgumentException("firdes check failed: sampling_freq > 0");
		}

		if (fa <= 0.0 || fa > sampling_freq / 2) {
			throw new IllegalArgumentException("firdes check failed: 0 < fa <= sampling_freq / 2");
		}

		if (transition_width <= 0) {
			throw new IllegalArgumentException("firdes check failed: transition_width > 0");
		}
	}

	private static int compute_ntaps(double sampling_freq, double transition_width, Window window_type, double beta) {
		double a = window_type.max_attenuation(beta);
		int ntaps = (int) (a * sampling_freq / (22.0 * transition_width));
		if ((ntaps & 1) == 0) { // if even...
			ntaps++; // ...make odd
		}
		return ntaps;
	}
}
