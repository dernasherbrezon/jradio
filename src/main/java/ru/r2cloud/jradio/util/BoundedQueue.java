package ru.r2cloud.jradio.util;

public class BoundedQueue {

	private final float[] buffer;
	private int tailIndex;
	private int headIndex;
	private int storedElements;

	public BoundedQueue(int size) {
		this.buffer = new float[size];
		tailIndex = 0;
		headIndex = 0;
	}

	public void add(float elem) {
		if (storedElements == buffer.length) {
			throw new IllegalStateException();
		}
		buffer[tailIndex] = elem;
		tailIndex++;
		if (tailIndex >= buffer.length) {
			tailIndex = 0;
		}
		storedElements++;
	}

	public float poll() {
		if (storedElements == 0) {
			throw new IllegalStateException();
		}
		float result = buffer[headIndex];
		headIndex++;
		if (headIndex >= buffer.length) {
			headIndex = 0;
		}
		storedElements--;
		return result;
	}

	public int getSize() {
		return buffer.length;
	}

}
