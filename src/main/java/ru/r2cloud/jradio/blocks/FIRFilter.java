package ru.r2cloud.jradio.blocks;

public class FIRFilter {

	private final float[] taps;

	public FIRFilter(float[] taps) {
		this.taps = new float[taps.length];
		for (int i = taps.length - 1, j = 0; i >= 0; i--, j++) {
			this.taps[j] = taps[i];
		}
	}

	public float filter(float[] input) {
		float dotProduct = 0;
		for (int i = 0; i < input.length; i++) {
			dotProduct = dotProduct + input[i] * taps[i];
		}
		return dotProduct;
	}

	public void filterComplex(float[] output, float[] input, float[] inputImg) {
		float dotProductReal = 0;
		float dotProductImg = 0;
		for (int i = 0; i < input.length; i++) {
			dotProductReal = dotProductReal + input[i] * taps[i];
			dotProductImg = dotProductImg + inputImg[i] * taps[i];
		}
		output[0] = dotProductReal;
		output[1] = dotProductImg;
	}

}
