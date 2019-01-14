package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;

public class ArrayFloatInput implements FloatInput {

	private final Context context;
	private float[] result;
	private int index;

	public ArrayFloatInput(float... result) {
		this.result = result;
		context = new Context();
		context.setChannels(2);
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public float readFloat() throws IOException {
		if (index >= result.length) {
			throw new EOFException();
		}
		float cur = result[index];
		index++;
		return cur;
	}

	@Override
	public Context getContext() {
		return context;
	}

}
