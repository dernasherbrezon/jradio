package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;

public class PolyphaseArbResamplerComplex implements FloatInput {

	private final FloatInput input;
	private final int tapsPerFilter;
	private final int intRate;
	private final int decRate;
	private final float fltRate;

	private final CircularComplexArray array;
	private final FIRFilter[] filters;
	private final FIRFilter[] diffFilters;

	private final float[] currentComplex = new float[2];
	private final float[] currentComplexDiff = new float[2];
	private final float[] currentBatch;
	private final Context context;
	private int currentBatchIndex;
	private int currentBatchSize;
	private float acc;
	private int lastFilter;

	public PolyphaseArbResamplerComplex(FloatInput input, float rate, float[] taps, int filterSize) {
		if (input.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + input.getContext().getChannels());
		}
		this.input = input;
		acc = 0;
		intRate = filterSize;
		decRate = (int) Math.floor(intRate / rate);
		fltRate = (intRate / rate) - decRate;
		lastFilter = (taps.length / 2) % filterSize;
		this.tapsPerFilter = (int) Math.ceil((double) taps.length / filterSize);
		this.filters = createFilters(taps);
		this.diffFilters = createFilters(createDiffTaps(taps));
		this.currentBatch = new float[(int) Math.floor(rate) * 2];
		this.array = new CircularComplexArray(tapsPerFilter);
		this.context = new Context(input.getContext());
		this.context.setSampleRate(input.getContext().getSampleRate() * rate);
		if (this.context.getTotalSamples() != null) {
			this.context.setTotalSamples((long) (this.context.getTotalSamples() * rate));
		}
		this.currentBatchIndex = currentBatch.length; 
	}

	private FIRFilter[] createFilters(float[] taps) {
		float[] tmpTaps = new float[intRate * tapsPerFilter];
		System.arraycopy(taps, 0, tmpTaps, 0, taps.length);
		for (int i = taps.length; i < tmpTaps.length; i++) {
			tmpTaps[i] = 0.0f;
		}

		FIRFilter[] result = new FIRFilter[intRate];
		for (int i = 0; i < intRate; i++) {
			float[] curTaps = new float[tapsPerFilter];
			for (int j = 0; j < tapsPerFilter; j++) {
				curTaps[j] = tmpTaps[i + j * intRate];
			}
			// Build a filter for each channel and add it's taps to it
			result[i] = new FIRFilter(curTaps);
		}
		return result;
	}

	private static float[] createDiffTaps(float[] taps) {
		float[] diffFilter = new float[2];
		diffFilter[0] = -1;
		diffFilter[1] = 1;
		
		float[] result = new float[taps.length];
		for (int i = 0; i < taps.length - 1; i++) {
			float tap = 0;
			for (int j = 0; j < diffFilter.length; j++) {
				tap += diffFilter[j] * taps[i + j];
			}
			result[i] = tap;
		}
		result[taps.length - 1] = 0;
		return result;
	}

	@Override
	public float readFloat() throws IOException {
		if (currentBatchIndex >= currentBatchSize) {
			calculate();
			currentBatchIndex = 0;
		}
		float result = currentBatch[currentBatchIndex];
		currentBatchIndex++;
		return result;
	}

	private void calculate() throws IOException {
		int j = lastFilter;
		int i = 0;
		
		// Take the current filter and derivative filter output
		array.add(input.readFloat(), input.readFloat());

		while (j < intRate) {
			filters[j].filterComplex(currentComplex, array);
			diffFilters[j].filterComplex(currentComplexDiff, array);

			currentComplexDiff[0] = currentComplexDiff[0] * acc;
			currentComplexDiff[1] = currentComplexDiff[1] * acc;
			currentBatch[i] = currentComplex[0] + currentComplexDiff[0]; // linearly interpolate between samples
			currentBatch[i + 1] = currentComplex[1] + currentComplexDiff[1];
			i += 2;

			// Adjust accumulator and index into filterbank
			acc += fltRate;
			j += decRate + (int) Math.floor(acc);
			acc = acc % 1.0f;
		}
		currentBatchSize = i;
		j = j % intRate;
		lastFilter = j;
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
