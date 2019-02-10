package ru.r2cloud.jradio.blocks;

import ru.r2cloud.jradio.util.CircularComplexArray;

public class ComplexFIRFilter {

	private final float[] tapsReal;
	private final float[] tapsImg;

	public ComplexFIRFilter(float[] tapsReal, float[] tapsImg) {
		this.tapsReal = tapsReal;
		this.tapsImg = tapsImg;
	}

	public void filterComplex(float[] output, CircularComplexArray array) {
		int j = 0;

		output[0] = 0.0f;
		output[1] = 0.0f;

		for (int i = array.getCurrentPos(); i < array.getSize(); i++, j++) {
			output[0] += array.getHistoryReal()[i] * tapsReal[j] - array.getHistoryImg()[i] * tapsImg[j];
			output[1] += array.getHistoryReal()[i] * tapsImg[j] + array.getHistoryImg()[i] * tapsReal[j];
		}
		for (int i = 0; i < array.getCurrentPos(); i++, j++) {
			output[0] += array.getHistoryReal()[i] * tapsReal[j] - array.getHistoryImg()[i] * tapsImg[j];
			output[1] += array.getHistoryReal()[i] * tapsImg[j] + array.getHistoryImg()[i] * tapsReal[j];
		}
	}
	
	public int getNumTaps() {
		return tapsReal.length;
	}
	
	public float[] getTapsReal() {
		return tapsReal;
	}
	
	public float[] getTapsImg() {
		return tapsImg;
	}

}
