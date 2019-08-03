package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class Add implements FloatInput {

	private final FloatInput source1;
	private final FloatInput source2;

	private final float[] currentComplex = new float[2];
	private boolean real = true;

	public Add(FloatInput source1, FloatInput source2) {
		if (source1.getContext().getChannels() != source2.getContext().getChannels()) {
			throw new IllegalArgumentException("mismatch channels: " + source1.getContext().getChannels() + " " + source2.getContext().getChannels());
		}
		this.source1 = source1;
		this.source2 = source2;
	}

	@Override
	public float readFloat() throws IOException {
		if (source1.getContext().getChannels() == 1) {
			return source1.readFloat() + source2.readFloat();
		}
		float result;
		if (real) {
			currentComplex[0] = source1.readFloat() + source2.readFloat();
			currentComplex[1] = source1.readFloat() + source2.readFloat();
			result = currentComplex[0];
		} else {
			result = currentComplex[1];
		}

		real = !real;
		return result;
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
	public Context getContext() {
		return source1.getContext();
	}

}
