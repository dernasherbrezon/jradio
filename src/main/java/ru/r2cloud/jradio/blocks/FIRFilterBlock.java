package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularArray;
import ru.r2cloud.jradio.util.CircularComplexArray;

public class FIRFilterBlock implements FloatInput {

	private final FloatInput input;
	private final FIRFilter filter;

	private final CircularArray array;
	private final CircularComplexArray complexArray;
	private final float[] currentComplex = new float[2];
	private boolean real = true;

	public FIRFilterBlock(FloatInput input, float[] taps) {
		if (input.getContext().getChannels() > 2 || input.getContext().getChannels() < 1) {
			throw new IllegalArgumentException("unsupported number of channels: " + input.getContext().getChannels());
		}
		if (input.getContext().getChannels() == 1) {
			array = new CircularArray(taps.length);
			complexArray = null;
		} else {
			array = null;
			complexArray = new CircularComplexArray(taps.length);
		}
		this.input = input;
		this.filter = new FIRFilter(taps);
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public float readFloat() throws IOException {
		if (input.getContext().getChannels() == 1) {
			array.add(input.readFloat());
			return filter.filter(array);
		}
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
