package ru.r2cloud.jradio.util;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class BufferedFloatInput implements FloatInput {

	private final FloatInput source;
	private final float[] buffer;

	private int currentPos;
	private int currentCursorPos;

	public BufferedFloatInput(FloatInput source, int bufferSize) {
		this.source = source;
		this.buffer = new float[bufferSize];
		currentPos = buffer.length;
		currentCursorPos = buffer.length;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		if (currentCursorPos == currentPos) {
			currentPos--;
			if (currentPos < 0) {
				currentPos = buffer.length - 1;
			}
			float result = source.readFloat();
			buffer[currentPos] = result;
		}
		currentCursorPos--;
		if (currentCursorPos < 0) {
			currentCursorPos = buffer.length - 1;
		}
		return buffer[currentCursorPos];
	}

	public void resetBack(int numPosition) {
		if (numPosition > buffer.length) {
			throw new IllegalArgumentException("invalid reset " + numPosition);
		}
		currentCursorPos += numPosition;
		if (currentCursorPos >= buffer.length) {
			currentCursorPos -= buffer.length;
		}
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

}
