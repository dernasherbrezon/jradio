package ru.r2cloud.jradio.util;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class BufferedFloatInput implements FloatInput {

	private final FloatInput source;
	private final float[] buffer;
	private final int strideSize;
	private int current = 0;

	public BufferedFloatInput(FloatInput source, int strideSize, int bufferSize) {
		this.source = source;
		this.strideSize = strideSize;
		buffer = new float[bufferSize];
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		if (current >= strideSize) {
			int currentHistoryLength = buffer.length - strideSize;
			// shift left
			System.arraycopy(buffer, strideSize, buffer, 0, buffer.length - strideSize);
			// read new stride into the end of buffer
			for (int i = 0; i < strideSize; i++) {
				buffer[i + currentHistoryLength] = source.readFloat();
			}
			current = 0;
		}
		float result = buffer[current];
		current++;
		return result;
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

	public float[] getBuffer() {
		return buffer;
	}
}
