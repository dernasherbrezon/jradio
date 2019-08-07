package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;

public class FIRFilterBlockComplex implements FloatInput {

	private final FloatInput input;
	private final FIRFilterComplex filter;

	private final CircularComplexArray complexArray;
	private final float[] currentComplex = new float[2];
	private boolean real = true;

	public FIRFilterBlockComplex(FloatInput input, float[] complexTaps) {
		if (input.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("unsupported number of channels: " + input.getContext().getChannels());
		}
		this.complexArray = new CircularComplexArray(complexTaps.length / 2);
		this.input = input;
		this.filter = new FIRFilterComplex(complexTaps);
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			complexArray.add(input.readFloat(), input.readFloat());
			filter.filterComplex(currentComplex, complexArray);
			result = currentComplex[0];
		} else {
			result = currentComplex[1];
		}
		real = !real;
		return result;
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
