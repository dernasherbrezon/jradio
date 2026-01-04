package ru.r2cloud.jradio.blocks;

public enum Window {

	WIN_HAMMING, WIN_HANN, WIN_BLACKMAN, WIN_RECTANGULAR, WIN_KAISER, WIN_BLACKMAN_HARRIS, WIN_BARTLETT, WIN_FLATTOP, WIN_GUASSIAN;

	public float[] build(int ntaps, double beta) {
		float[] taps;
		switch (this) {
		case WIN_HAMMING:
			taps = new float[ntaps];
			int m = ntaps - 1;

			for (int n = 0; n < ntaps; n++) {
				taps[n] = (float) (0.54 - 0.46 * Math.cos((2 * Math.PI * n) / m));
			}
			return taps;
		case WIN_GUASSIAN:
			taps = new float[ntaps];
			float c = 0.5f * (ntaps - 1);
			float d = (float) (2.0f / (beta * ntaps));
			for (int i = 0; i < (ntaps + 1) / 2; i++) {
				float a = (i - c) * d;
				float b = (float) Math.exp(-0.5f * a * a);
				taps[i] = b;
				taps[ntaps - 1 - i] = b;
			}
			return taps;
//		case WIN_HANN:
//		case WIN_BLACKMAN:
//		case WIN_RECTANGULAR:
//		case WIN_KAISER:
//		case WIN_BLACKMAN_hARRIS:
//		case WIN_BARTLETT:
//		case WIN_FLATTOP:
		default:
			throw new IllegalArgumentException("unknown window type: " + this);
		}
	}

	public double maxAttenuation(double beta) {
		switch (this) {
		case WIN_HAMMING:
			return 53;
		case WIN_HANN:
			return 44;
		case WIN_BLACKMAN:
			return 74;
		case WIN_RECTANGULAR:
			return 21;
		case WIN_KAISER:
			return (beta / 0.1102 + 8.7);
		case WIN_BLACKMAN_HARRIS:
			return 92;
		case WIN_BARTLETT:
			return 27;
		case WIN_FLATTOP:
			return 93;
		case WIN_GUASSIAN:
			return 100;
		default:
			throw new IllegalArgumentException("unknown window type: " + this);
		}
	}
}
