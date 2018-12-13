package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class Multiply implements FloatInput {

	private final FloatInput source1;
	private final FloatInput source2;

	private final float[] complex = new float[2];
	private boolean real = true;

	public Multiply(FloatInput source1, FloatInput source2) {
		if (source1.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("input is not a complex number: " + source1.getContext().getChannels());
		}
		if (source2.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("input is not a complex number: " + source2.getContext().getChannels());
		}
		this.source1 = source1;
		this.source2 = source2;
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
			MathUtils.multiply(complex, a, b, c, d);
			result = complex[0];
		} else {
			result = complex[1];
		}
		real = !real;
		return result;
	}

	@Override
	public Context getContext() {
		return source1.getContext();
	}
}
