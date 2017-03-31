package ru.r2cloud.jradio.blocks;

public class FIRFilter {

	private float[] taps;

	public FIRFilter(float[] taps) {
		this.taps = new float[taps.length];
		for( int  i = taps.length - 1, j = 0;i>=0;i--,j++ ) {
			this.taps[j] = taps[i];
		}
	}

	public float filter(float[] input) {
		float dotProduct = 0;
		for (int i = 0; i < input.length; i++) {
			dotProduct += (input[i] * taps[i]);
		}
		return dotProduct;
	}

}
