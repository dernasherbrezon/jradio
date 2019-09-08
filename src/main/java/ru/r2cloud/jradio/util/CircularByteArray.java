package ru.r2cloud.jradio.util;

public class CircularByteArray {

	private final byte[] array;
	private int currentPos;

	public CircularByteArray(int size) {
		array = new byte[size];
		currentPos = array.length;
	}

	public void add(byte value) {
		currentPos--;
		if (currentPos < 0) {
			currentPos = array.length - 1;
		}
		array[currentPos] = value;
	}

	public byte get(int index) {
		if (index >= array.length) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		int resultIndex = currentPos - index - 1;
		if (resultIndex < 0) {
			resultIndex = resultIndex + array.length;
		}
		return array[resultIndex];
	}

	public int getSize() {
		return array.length;
	}

}
