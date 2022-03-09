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

	public byte[] getCopy() {
		byte[] result = new byte[array.length];
		int j = 0;
		for (int i = currentPos - 1; i >= 0; i--, j++) {
			result[j] = array[i];
		}
		for (int i = array.length - 1; i >= currentPos; i--, j++) {
			result[j] = array[i];
		}
		return result;
	}

}
