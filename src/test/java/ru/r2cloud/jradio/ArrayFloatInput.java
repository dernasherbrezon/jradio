package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;

public class ArrayFloatInput implements FloatInput {

	private final Context context;
	private final boolean repeat;
	private final float[] result;
	private int index;

	public ArrayFloatInput(Context context, float... result) {
		this.context = context;
		this.result = result;
		this.repeat = false;
	}

	public ArrayFloatInput(boolean repeat, float... result) {
		this.result = result;
		this.repeat = repeat;
		context = new Context();
		context.setChannels(2);
	}

	public ArrayFloatInput(float... result) {
		this(false, result);
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public float readFloat() throws IOException {
		if (index >= result.length) {
			if (!repeat) {
				throw new EOFException();
			} else {
				index = 0;
			}
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
