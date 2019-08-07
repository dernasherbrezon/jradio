package ru.r2cloud.jradio.blocks;

import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.MathUtils;

public class FIRFilterComplex {

	private final float[] taps;
	private final float[] currentComplex = new float[2];

	public FIRFilterComplex(float[] taps) {
		this.taps = taps;
	}

	public void filterComplex(float[] output, CircularComplexArray input) {
		float dotProductReal = 0;
		float dotProductImg = 0;
		int j = 0;
		for (int i = input.getCurrentPos(); i < input.getSize(); i++, j += 2) {
			MathUtils.multiply(currentComplex, input.getHistoryReal()[i], input.getHistoryImg()[i], taps[j], taps[j + 1]);
			dotProductReal += currentComplex[0];
			dotProductImg += currentComplex[1];
		}
		for (int i = 0; i < input.getCurrentPos(); i++, j+=2) {
			MathUtils.multiply(currentComplex, input.getHistoryReal()[i], input.getHistoryImg()[i], taps[j], taps[j + 1]);
			dotProductReal += currentComplex[0];
			dotProductImg += currentComplex[1];
		}
		output[0] = dotProductReal;
		output[1] = dotProductImg;
	}

}
