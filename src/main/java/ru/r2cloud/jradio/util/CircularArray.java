package ru.r2cloud.jradio.util;

public class CircularArray {

	private final float[] array;
	private int currentPos;

	public CircularArray(int size) {
		array = new float[size];
		currentPos = array.length;
	}

	public void add(float value) {
		currentPos--;
		if (currentPos < 0) {
			currentPos = array.length - 1;
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

	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;		
	}

}
