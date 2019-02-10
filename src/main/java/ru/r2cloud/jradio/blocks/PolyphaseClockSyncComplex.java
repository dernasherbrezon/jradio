package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.MathUtils;

public class PolyphaseClockSyncComplex implements FloatInput {

	private final FloatInput source;
	private final Context context;

	private float samplesSymbol;
	private int d_nfilters;
	private int d_damping;
	private float d_loop_bw;
	private float d_alpha;
	private float d_beta;
	private float d_k;
	private float d_rate;
	private int d_rate_i;
	private float d_rate_f;
	private int d_filtnum;
	private int d_taps_per_filter;
	private final FIRFilter[] d_filters;
	private final FIRFilter[] d_diff_filters;
	private float d_max_dev;
	private float d_error;
	private int skip;
	private final CircularComplexArray array;

	private final float[] currentComplex = new float[2];
	private final float[] currentComplexDiff = new float[2];
	private boolean outputReal = true;

	public PolyphaseClockSyncComplex(FloatInput source, float samplesSymbol, float loopBandwidth, float[] taps, int d_nfilters, int initialPhase, float maximumRateDeviation) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		this.source = source;
		context = new Context(source.getContext());
		// unpredictable number of samples will be dropped
		context.setSampleRate(0.0f);
		context.setTotalSamples(0L);

		this.d_nfilters = d_nfilters;
		this.samplesSymbol = (float) Math.floor(samplesSymbol);
		this.d_damping = 2 * d_nfilters;
		this.d_loop_bw = loopBandwidth;
		float denom = (1.0f + 2.0f * d_damping * d_loop_bw + d_loop_bw * d_loop_bw);
		d_alpha = (4 * d_damping * d_loop_bw) / denom;
		d_beta = (4 * d_loop_bw * d_loop_bw) / denom;

		// Store the last filter between calls to work
		// The accumulator keeps track of overflow to increment the stride correctly.
		// set it here to the fractional difference based on the initial phaes
		this.d_k = initialPhase;
		this.d_rate = (samplesSymbol - (float) Math.floor(samplesSymbol)) * d_nfilters;
		this.d_rate_i = (int) Math.floor(d_rate);
		this.d_rate_f = d_rate - d_rate_i;
		this.d_filtnum = (int) Math.floor(d_k);
		this.d_taps_per_filter = (int) Math.ceil((double) taps.length / d_nfilters);
		this.skip = 1;
		this.array = new CircularComplexArray(d_taps_per_filter);
		this.d_filters = createFilters(taps);
		this.d_diff_filters = createFilters(createDiffTaps(taps));
		this.d_max_dev = maximumRateDeviation;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			calculate();

			outputReal = !outputReal;
			return currentComplex[0];
		}

		outputReal = !outputReal;
		return currentComplex[1];
	}

	private void calculate() throws IOException {
		float error_r, error_i;
		int toSkip = 0;

		d_filtnum = (int) Math.floor(d_k);

		// Keep the current filter number in [0, d_nfilters]
		// If we've run beyond the last filter, wrap around and go to next sample
		// If we've gone below 0, wrap around and go to previous sample
		while (d_filtnum >= d_nfilters) {
			d_k -= d_nfilters;
			d_filtnum -= d_nfilters;
			toSkip++;
		}
		while (d_filtnum < 0) {
			d_k += d_nfilters;
			d_filtnum += d_nfilters;
			toSkip--;
		}

		for (int i = 0; i < skip; i++) {
			array.add(source.readFloat(), source.readFloat());
		}

		d_filters[d_filtnum].filterComplex(currentComplex, array);
		d_k = d_k + d_rate_i + d_rate_f; // update phase

		// Update the phase and rate estimates for this symbol
		d_diff_filters[d_filtnum].filterComplex(currentComplexDiff, array);
		error_r = currentComplex[0] * currentComplexDiff[0];
		error_i = currentComplex[1] * currentComplexDiff[1];
		d_error = (error_i + error_r) / 2.0f; // average error from I&Q channel

		// Run the control loop to update the current phase (k) and
		// tracking rate estimates based on the error value
		// Interpolating here to update rates for ever sps.
		for (int s = 0; s < samplesSymbol; s++) {
			d_rate_f = d_rate_f + d_beta * d_error;
			d_k = d_k + d_rate_f + d_alpha * d_error;
		}

		// Keep our rate within a good range
		d_rate_f = MathUtils.branchless_clip(d_rate_f, d_max_dev);

		toSkip += (int) Math.floor(samplesSymbol);
		skip = toSkip;
	}

	private float[] createDiffTaps(float[] taps) {
		float[] diffFilter = new float[3];
		diffFilter[0] = -1;
		diffFilter[1] = 0;
		diffFilter[2] = 1;

		float power = 0;
		float[] result = new float[taps.length];
		result[0] = 0;
		for (int i = 0, k = 1; i < taps.length - 2; i++, k++) {
			float tap = 0;
			for (int j = 0; j < diffFilter.length; j++) {
				tap += diffFilter[j] * taps[i + j];
			}
			result[k] = tap;
			power += Math.abs(tap);
		}

		if (power == 0.0f) {
			throw new IllegalArgumentException("invalid power: " + power);
		}
		result[result.length - 1] = 0;

		// Normalize the taps
		for (int i = 0; i < result.length; i++) {
			result[i] *= d_nfilters / power;
			if (result[i] != result[i]) {
				throw new IllegalArgumentException("create_diff_taps produced NaN");
			}
		}

		return result;
	}

	private FIRFilter[] createFilters(float[] taps) {
		// Create d_numchan vectors to store each channel's taps
		float[][] result = new float[d_nfilters][d_taps_per_filter];

		// Make a vector of the taps plus fill it out with 0's to fill
		// each polyphase filter with exactly d_taps_per_filter
		float[] tmp_taps = new float[d_nfilters * d_taps_per_filter];
		for (int i = 0; i < taps.length; i++) {
			tmp_taps[i] = taps[i];
		}
		for (int i = taps.length; i < tmp_taps.length; i++) {
			tmp_taps[i] = 0.0f;
		}
		FIRFilter[] filters = new FIRFilter[d_nfilters];

		// Partition the filter
		for (int i = 0; i < d_nfilters; i++) {
			// Each channel uses all d_taps_per_filter with 0's if not enough taps to fill out
			result[i] = new float[d_taps_per_filter];
			for (int j = 0; j < d_taps_per_filter; j++) {
				result[i][j] = tmp_taps[i + j * d_nfilters];
			}

			// Build a filter for each channel and add it's taps to it
			filters[i] = new FIRFilter(result[i]);
		}
		return filters;
	}

	@Override
	public Context getContext() {
		return context;
	}

}
