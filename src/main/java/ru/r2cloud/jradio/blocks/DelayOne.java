package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class DelayOne implements FloatInput {

	private final FloatInput source;
	private final float[] currentComplex = new float[2];
	private final float[] previousInput = new float[2];

	private boolean real = true;

	public DelayOne(FloatInput source) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		this.source = source;
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			float sourceReal = source.readFloat();
			float sourceImag = source.readFloat();
			//multiply conjugate
			MathUtils.multiply(currentComplex, sourceReal, sourceImag, previousInput[0], -previousInput[1]);
			result = currentComplex[0];
			previousInput[0] = sourceReal;
			previousInput[1] = sourceImag;
		} else {
			result = currentComplex[1];
		}
		real = !real;
		return result;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

}
