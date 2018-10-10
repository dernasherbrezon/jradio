package ru.r2cloud.jradio.blocks;

public class ComplexFIRFilter {

	private final float[] taps;

	public ComplexFIRFilter(float[] taps) {
		this.taps = new float[taps.length];
		for (int i = taps.length - 2, j = 0; i >= 0; i -= 2, j += 2) {
			this.taps[j] = taps[i];
			this.taps[j + 1] = taps[i + 1];
		}
	}

	public void filterComplexTaps(float[] output, float[] input, float[] inputImg, int inputPos) {
		int j = 0;
		int limit = input.length;
		
		output[0] = 0.0f;
		output[1] = 0.0f;
		
		for (int i = inputPos; i < limit; j += 2) {
			output[0] += input[i] * taps[j] - inputImg[i] * taps[j + 1];
			output[1] += input[i] * taps[j + 1] + inputImg[i] * taps[j];
			i++;
			if (i >= input.length) {
				i = 0;
				limit = inputPos;
			}
		}
	}
	
}
