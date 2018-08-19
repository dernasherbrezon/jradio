package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.FloatInput;

public class Multiply implements FloatInput {

	private final FloatInput source1;
	private final FloatInput source2;

	public Multiply(FloatInput source1, FloatInput source2) {
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
		return source1.readFloat() * source2.readFloat();
	}

}
