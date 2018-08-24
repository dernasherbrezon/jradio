package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.FloatInput;

public class Multiply implements FloatInput {

	private final FloatInput source1;
	private final FloatInput source2;

	private final float[] complex = new float[2];
	private boolean real = true;

	public Multiply(FloatInput source1, FloatInput source2, boolean complexInput) {
		this.source1 = source1;
		this.source2 = source2;
		if (!complexInput) {
			throw new IllegalArgumentException("non complex input is not supported");
		}
	}

	@Override
	public void close() throws IOException {
		Exception lastException = null;
		try {
			source1.close();
		} catch (Exception e) {
			lastException = e;
		}
		try {
			source2.close();
		} catch (Exception e) {
			lastException = e;
		}
		if (lastException != null) {
			throw new IOException(lastException);
		}
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			float a = source1.readFloat();
			float b = source1.readFloat();
			float c = source2.readFloat();
			float d = source2.readFloat();
			complex[0] = (a * c - b * d);
			complex[1] = (a * d + c * b);
			result = complex[0];
		} else {
			result = complex[1];
		}
		real = !real;
		return result;
	}

}
