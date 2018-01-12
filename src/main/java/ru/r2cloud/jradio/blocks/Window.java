package ru.r2cloud.jradio.blocks;

public enum Window {

	WIN_HAMMING, WIN_HANN, WIN_BLACKMAN, WIN_RECTANGULAR, WIN_KAISER, WIN_BLACKMAN_hARRIS, WIN_BARTLETT, WIN_FLATTOP;

	public float[] build(int ntaps, double beta) {
		float[] taps;
		switch (this) {
		case WIN_HAMMING:
			taps = new float[ntaps];
			float M = ntaps - 1;

			for (int n = 0; n < ntaps; n++) {
				taps[n] = (float) (0.54 - 0.46 * Math.cos((2 * Math.PI * n) / M));
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

	public double max_attenuation(double beta) {
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
		case WIN_BLACKMAN_hARRIS:
			return 92;
		case WIN_BARTLETT:
			return 27;
		case WIN_FLATTOP:
			return 93;
		default:
			throw new IllegalArgumentException("unknown window type: " + this);
		}
	}
}
