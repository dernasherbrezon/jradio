package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.BufferedFloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.MathUtils;

public class PolyphaseClockSyncComplex implements FloatInput {

	private final BufferedFloatInput source;
	private final Context context;

	private float samplesSymbol;
	private int numberOfFilters;
	private int damping;
	private float alpha;
	private float beta;
	private float k;
	private float rate;
	private int rateI;
	private float rateF;
	private int filterNumber;
	private int tapsPerFilter;
	private final FIRFilter[] filters;
	private final FIRFilter[] diffFilters;
	private float maxDeviation;
	private int skip;
	private final CircularComplexArray array;
	private final CircularComplexArray arrayDiff;
	private final int outputSamplesPerSymbol;

	private final float[] tempComplex = new float[2];
	private final float[] currentComplex;
	private final float[] currentComplexDiff = new float[2];
	private int currentComplexIndex;

	public PolyphaseClockSyncComplex(FloatInput source, float samplesSymbol, float loopBandwidth, float[] taps, int numberOfFilters, int initialPhase, float maximumRateDeviation, int outputSamplesPerSymbol) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		if (outputSamplesPerSymbol < 1) {
			throw new IllegalArgumentException("invalid output samples per symbol: " + outputSamplesPerSymbol);
		}
		this.outputSamplesPerSymbol = outputSamplesPerSymbol;
		this.currentComplex = new float[2 * outputSamplesPerSymbol];
		this.currentComplexIndex = currentComplex.length; // kick off calculation on the first iteration
		context = new Context(source.getContext());
		// unpredictable number of samples will be dropped
		context.setSampleRate(0.0f);
		context.setTotalSamples(null);

		this.numberOfFilters = numberOfFilters;
		this.samplesSymbol = (float) Math.floor(samplesSymbol);
		this.damping = 2 * numberOfFilters;
		float denom = (1.0f + 2.0f * damping * loopBandwidth + loopBandwidth * loopBandwidth);
		alpha = (4 * damping * loopBandwidth) / denom;
		beta = (4 * loopBandwidth * loopBandwidth) / denom;

		// Store the last filter between calls to work
		// The accumulator keeps track of overflow to increment the stride correctly.
		// set it here to the fractional difference based on the initial phaes
		this.k = initialPhase;
		this.rate = (samplesSymbol - (float) Math.floor(samplesSymbol)) * numberOfFilters;
		this.rateI = (int) Math.floor(rate);
		this.rateF = rate - rateI;
		this.filterNumber = (int) Math.floor(k);
		this.tapsPerFilter = (int) Math.ceil((double) taps.length / numberOfFilters);
		this.skip = 1;
		this.source = new BufferedFloatInput(source, 2 * (tapsPerFilter + outputSamplesPerSymbol - 1));
		this.array = new CircularComplexArray(tapsPerFilter);
		this.arrayDiff = new CircularComplexArray(tapsPerFilter); // keep original input for diff filter. do not advance for outputSamplesPerSymbol
		this.filters = createFilters(taps);
		this.diffFilters = createFilters(createDiffTaps(taps));
		this.maxDeviation = maximumRateDeviation;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		if (currentComplexIndex >= currentComplex.length) {
			calculate();
			currentComplexIndex = 0;
		}
		float result = currentComplex[currentComplexIndex];
		currentComplexIndex++;
		return result;
	}

	private void calculate() throws IOException {
		float errorR;
		float errorI;
		int toSkip = 0;

		for (int curOutputSample = 0; curOutputSample < outputSamplesPerSymbol; curOutputSample++) {
			filterNumber = (int) Math.floor(k);

			// Keep the current filter number in [0, d_nfilters]
			// If we've run beyond the last filter, wrap around and go to next sample
			// If we've gone below 0, wrap around and go to previous sample
			while (filterNumber >= numberOfFilters) {
				k -= numberOfFilters;
				filterNumber -= numberOfFilters;
				toSkip++;
			}
			while (filterNumber < 0) {
				k += numberOfFilters;
				filterNumber += numberOfFilters;
				toSkip--;
			}

			if (curOutputSample == 0) {
				for (int i = 0; i < skip; i++) {
					float real = source.readFloat();
					float imag = source.readFloat();
					array.add(real, imag);
					arrayDiff.add(real, imag);
				}
			} else {
				array.add(source.readFloat(), source.readFloat());
			}

			filters[filterNumber].filterComplex(tempComplex, array);
			k = k + rateI + rateF; // update phase
			currentComplex[2 * curOutputSample] = tempComplex[0];
			currentComplex[2 * curOutputSample + 1] = tempComplex[1];
		}

		// Update the phase and rate estimates for this symbol
		diffFilters[filterNumber].filterComplex(currentComplexDiff, arrayDiff);
		errorR = currentComplex[0] * currentComplexDiff[0];
		errorI = currentComplex[1] * currentComplexDiff[1];
		float error = (errorI + errorR) / 2.0f; // average error from I&Q channel

		// Run the control loop to update the current phase (k) and
		// tracking rate estimates based on the error value
		// Interpolating here to update rates for ever sps.
		for (int s = 0; s < samplesSymbol; s++) {
			rateF = rateF + beta * error;
			k = k + rateF + alpha * error;
		}

		// Keep our rate within a good range
		rateF = MathUtils.branchless_clip(rateF, maxDeviation);

		toSkip += (int) Math.floor(samplesSymbol);
		skip = toSkip;

		if (outputSamplesPerSymbol > 1) {
			source.resetBack(2 * (outputSamplesPerSymbol - 1));
			// reset array to the beginning of the batch
			// arrayDiff holds the current batch
			System.arraycopy(arrayDiff.getHistoryImg(), 0, array.getHistoryImg(), 0, arrayDiff.getHistoryImg().length);
			System.arraycopy(arrayDiff.getHistoryReal(), 0, array.getHistoryReal(), 0, arrayDiff.getHistoryReal().length);
			array.setCurrentPos(arrayDiff.getCurrentPos());
		}
	}

	private float[] createDiffTaps(float[] taps) {
		float[] diffFilter = new float[3];
		diffFilter[0] = -1;
		diffFilter[1] = 0;
		diffFilter[2] = 1;

		float power = 0;
		float[] result = new float[taps.length];
		result[0] = 0;
		for (int i = 0, ii = 1; i < taps.length - 2; i++, ii++) {
			float tap = 0;
			for (int j = 0; j < diffFilter.length; j++) {
				tap += diffFilter[j] * taps[i + j];
			}
			result[ii] = tap;
			power += Math.abs(tap);
		}

		if (power == 0.0f) {
			throw new IllegalArgumentException("invalid power: " + power);
		}
		result[result.length - 1] = 0;

		// Normalize the taps
		for (int i = 0; i < result.length; i++) {
			result[i] *= numberOfFilters / power;
			if (result[i] != result[i]) {
				throw new IllegalArgumentException("create_diff_taps produced NaN");
			}
		}

		return result;
	}

	private FIRFilter[] createFilters(float[] taps) {
		// Create d_numchan vectors to store each channel's taps
		float[][] curBatch = new float[numberOfFilters][tapsPerFilter];

		// Make a vector of the taps plus fill it out with 0's to fill
		// each polyphase filter with exactly d_taps_per_filter
		float[] tmpTaps = new float[numberOfFilters * tapsPerFilter];
		for (int i = 0; i < taps.length; i++) {
			tmpTaps[i] = taps[i];
		}
		for (int i = taps.length; i < tmpTaps.length; i++) {
			tmpTaps[i] = 0.0f;
		}
		FIRFilter[] result = new FIRFilter[numberOfFilters];

		// Partition the filter
		for (int i = 0; i < numberOfFilters; i++) {
			// Each channel uses all d_taps_per_filter with 0's if not enough taps to fill out
			curBatch[i] = new float[tapsPerFilter];
			for (int j = 0; j < tapsPerFilter; j++) {
				curBatch[i][j] = tmpTaps[i + j * numberOfFilters];
			}

			// Build a filter for each channel and add it's taps to it
			result[i] = new FIRFilter(curBatch[i]);
		}
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}

}
