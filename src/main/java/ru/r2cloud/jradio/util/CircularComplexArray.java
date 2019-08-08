package ru.r2cloud.jradio.util;

public class CircularComplexArray {

	private final float[] historyReal;
	private final float[] historyImg;
	private int currentPos;

	public CircularComplexArray(int size) {
		historyReal = new float[size];
		historyImg = new float[size];
		currentPos = historyImg.length;
	}

	public void add(float real, float img) {
		currentPos--;
		if (currentPos < 0) {
			currentPos = historyImg.length - 1;
		}
		historyReal[currentPos] = real;
		historyImg[currentPos] = img;
	}

	public float[] getHistoryImg() {
		return historyImg;
	}

	public float[] getHistoryReal() {
		return historyReal;
	}

	public int getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}

	public int getSize() {
		return historyImg.length;
	}

}
