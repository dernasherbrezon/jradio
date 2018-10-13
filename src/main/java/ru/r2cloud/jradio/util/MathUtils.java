package ru.r2cloud.jradio.util;

public class MathUtils {

	public static float branchless_clip(float x, float clip) {
		float x1 = Math.abs(x + clip);
		float x2 = Math.abs(x - clip);
		x1 -= x2;
		return (float) 0.5 * x1;
	}

	public static float[] exp(float real, float img) {
		float[] result = new float[2];
		double exp = Math.exp(real);
		result[0] = (float) (exp * Math.cos(img));
		result[1] = (float) (exp * Math.sin(img));
		return result;
	}

	public static float abs(float real, float img) {
		return (float) Math.sqrt(real * real + img * img);
	}

	public static void multiply(float[] result, float real1, float img1, float real2, float img2) {
		result[0] = (real1 * real2 - img1 * img2);
		result[1] = (real1 * img2 + real2 * img1);
	}

	private MathUtils() {
		// do nothing
	}
}
