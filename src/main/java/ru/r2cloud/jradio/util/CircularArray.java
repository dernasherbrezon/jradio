package ru.r2cloud.jradio.util;

public class CircularArray {

	private final float[] array;
	private int currentPos;

	public CircularArray(int size) {
		array = new float[size];
		currentPos = -1;
	}

	public void add(float value) {
		currentPos++;
		if (currentPos >= array.length) {
			currentPos = 0;
		}
		array[currentPos] = value;
	}

	public float[] getArray() {
		return array;
	}

	public int getCurrentPos() {
		return currentPos;
	}

	public int getSize() {
		return array.length;
	}

}
