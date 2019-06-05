package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;

public class PolyphaseArbResamplerComplex implements FloatInput {

	private final FloatInput input;
	private final int d_taps_per_filter;
	private final int d_int_rate;
	private final int d_dec_rate;
	private final float d_flt_rate;

	private final CircularComplexArray array;
	private final FIRFilter[] d_filters;
	private final FIRFilter[] d_diff_filters;

	private final float[] currentComplex = new float[2];
	private final float[] currentComplexDiff = new float[2];
	private final float[] currentBatch;
	private final Context context;
	private int currentBatchIndex;
	private int currentBatchSize;
	private float d_acc;
	private int d_last_filter;

	public PolyphaseArbResamplerComplex(FloatInput input, float rate, float[] taps, int filter_size) {
		if (input.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + input.getContext().getChannels());
		}
		this.input = input;
		d_acc = 0;
		d_int_rate = filter_size;
		d_dec_rate = (int) Math.floor(d_int_rate / rate);
		d_flt_rate = (d_int_rate / rate) - d_dec_rate;
		d_last_filter = (taps.length / 2) % filter_size;
		this.d_taps_per_filter = (int) Math.ceil((double) taps.length / filter_size);
		this.d_filters = createFilters(taps);
		this.d_diff_filters = createFilters(createDiffTaps(taps));
		this.currentBatch = new float[(int) Math.floor(rate) * 2];
		this.array = new CircularComplexArray(d_taps_per_filter);
		this.context = new Context(input.getContext());
		this.context.setSampleRate(input.getContext().getSampleRate() * rate);
		if (this.context.getTotalSamples() != null) {
			this.context.setTotalSamples((long) (this.context.getTotalSamples() * rate));
		}
		this.currentBatchIndex = currentBatch.length; 
	}

	private FIRFilter[] createFilters(float[] taps) {
		float[] tmp_taps = new float[d_int_rate * d_taps_per_filter];
		for (int i = 0; i < taps.length; i++) {
			tmp_taps[i] = taps[i];
		}
		for (int i = taps.length; i < tmp_taps.length; i++) {
			tmp_taps[i] = 0.0f;
		}

		FIRFilter[] result = new FIRFilter[d_int_rate];
		for (int i = 0; i < d_int_rate; i++) {
			float[] curTaps = new float[d_taps_per_filter];
			for (int j = 0; j < d_taps_per_filter; j++) {
				curTaps[j] = tmp_taps[i + j * d_int_rate];
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
		int j = d_last_filter;
		int i = 0;
		
		// Take the current filter and derivative filter output
		array.add(input.readFloat(), input.readFloat());

		while (j < d_int_rate) {
			d_filters[j].filterComplex(currentComplex, array);
			d_diff_filters[j].filterComplex(currentComplexDiff, array);

			currentComplexDiff[0] = currentComplexDiff[0] * d_acc;
			currentComplexDiff[1] = currentComplexDiff[1] * d_acc;
			currentBatch[i] = currentComplex[0] + currentComplexDiff[0]; // linearly interpolate between samples
			currentBatch[i + 1] = currentComplex[1] + currentComplexDiff[1];
			i += 2;

			// Adjust accumulator and index into filterbank
			d_acc += d_flt_rate;
			j += d_dec_rate + (int) Math.floor(d_acc);
			d_acc = d_acc % 1.0f;
		}
		currentBatchSize = i;
		j = j % d_int_rate;
		d_last_filter = j;
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
