package ru.r2cloud.jradio.blocks;

public class FIRFilter {

	private float[] taps;
	private float[] filterComplex = new float[2];

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

	public float[] filterComplex(float[] input, float[] inputImg) {
		float dotProductReal = 0;
		float dotProductImg = 0;
		for (int i = 0; i < input.length; i++) {
			dotProductReal = dotProductReal + input[i] * taps[i];
			dotProductImg = dotProductImg + inputImg[i] * taps[i];
		}
		filterComplex[0] = dotProductReal;
		filterComplex[1] = dotProductImg;
		return filterComplex;
	}

}
