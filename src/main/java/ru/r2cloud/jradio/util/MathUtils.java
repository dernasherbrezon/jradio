package ru.r2cloud.jradio.util;

public class MathUtils {

	public static float branchless_clip(float x, float clip) {
		float x1 = Math.abs(x + clip);
		float x2 = Math.abs(x - clip);
		x1 -= x2;
		return (float) 0.5 * x1;
	}

	private MathUtils() {
		// do nothing
	}
}
