package ru.r2cloud.jradio.util;

public class RayleighRandom {

	private static final long MAX = (long) Math.pow(2, 32);
	private boolean imag = false;
	private float imagValue;
	private MTRandom mt19937Random;

	public RayleighRandom(int seed) {
		mt19937Random = new MTRandom(true);
		mt19937Random.setSeed(seed);
	}
	
	public float nextFloat() {
		return gasdev();
	}

	private float ran1() {
		long value = mt19937Random.next(32) & 0xFFFFFFFFL;
		return (float) ((double) value / MAX);
	}

	private float gasdev() {
		if (imag) { // just return the stored value if available
			imag = false;
			return imagValue;
		} else { // generate a pair of gaussian distributed numbers
			float x, y, s;
			do {
				x = 2.0f * ran1() - 1.0f;
				y = 2.0f * ran1() - 1.0f;
				s = x * x + y * y;
			} while (s >= 1.0f || s == 0.0f);
			imag = true;
			float result = (float) (x * Math.sqrt(-2.0 * Math.log(s) / s));
			imagValue = (float) (y * Math.sqrt(-2.0 * Math.log(s) / s));
			return result;
		}
	}

}
