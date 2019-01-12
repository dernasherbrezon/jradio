package ru.r2cloud.jradio.blocks;

import ru.r2cloud.jradio.util.CircularArray;
import ru.r2cloud.jradio.util.CircularComplexArray;

public class FIRFilter {

	private final float[] taps;

	public FIRFilter(float[] taps) {
		this.taps = taps;
	}

	public float filter(CircularArray circularArray) {
		float dotProduct = 0;
		int j = 0;
		for (int i = circularArray.getCurrentPos(); i < circularArray.getSize(); i++, j++) {
			dotProduct = dotProduct + circularArray.getArray()[i] * taps[j];
		}
		for (int i = 0; i < circularArray.getCurrentPos(); i++, j++) {
			dotProduct = dotProduct + circularArray.getArray()[i] * taps[j];
		}
		return dotProduct;
	}

	public void filterComplex(float[] output, CircularComplexArray input) {
		float dotProductReal = 0;
		float dotProductImg = 0;
		int j = 0;
		for (int i = input.getCurrentPos(); i < input.getSize(); i++, j++) {
			dotProductReal = dotProductReal + input.getHistoryReal()[i] * taps[j];
			dotProductImg = dotProductImg + input.getHistoryImg()[i] * taps[j];
		}
		for (int i = 0; i < input.getCurrentPos(); i++, j++) {
			dotProductReal = dotProductReal + input.getHistoryReal()[i] * taps[j];
			dotProductImg = dotProductImg + input.getHistoryImg()[i] * taps[j];
		}
		output[0] = dotProductReal;
		output[1] = dotProductImg;
	}

}
