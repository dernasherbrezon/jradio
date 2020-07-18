package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularArray;

public class InterpFIRFilter implements FloatInput {

	private final FloatInput input;
	private final FIRFilter[] filters;
	private final CircularArray buffer;
	private final Context context;

	private int currentFilter;

	public InterpFIRFilter(FloatInput input, int interpolation, float[] taps) {
		if (interpolation < 1) {
			throw new IllegalArgumentException("interpolation should be positive");
		}
		if (taps == null || taps.length == 0) {
			throw new IllegalArgumentException("empty taps");
		}
		this.input = input;
		int n = taps.length % interpolation;
		float[] newTaps;
		if (n > 0) {
			n = interpolation - n;
			newTaps = new float[taps.length + n];
			System.arraycopy(taps, 0, newTaps, 0, taps.length);
		} else {
			newTaps = taps;
		}

		float[][] xtaps = new float[interpolation][newTaps.length / interpolation];
		for (int i = 0; i < taps.length; i++) {
			xtaps[i % interpolation][i / interpolation] = taps[i];
		}
		filters = new FIRFilter[interpolation];
		for (int i = 0; i < filters.length; i++) {
			filters[i] = new FIRFilter(xtaps[i]);
		}
		buffer = new CircularArray(newTaps.length / interpolation);
		currentFilter = filters.length;
		context = new Context(input.getContext());
		context.setSampleRate(context.getSampleRate() * interpolation);
		if (context.getTotalSamples() != null) {
			context.setTotalSamples(context.getTotalSamples() * interpolation);
		}
	}

	@Override
	public float readFloat() throws IOException {
		if (currentFilter >= filters.length) {
			currentFilter = 0;
			buffer.add(input.readFloat());
		}
		float result = filters[currentFilter].filter(buffer);
		currentFilter++;
		return result;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return context;
	}

}
