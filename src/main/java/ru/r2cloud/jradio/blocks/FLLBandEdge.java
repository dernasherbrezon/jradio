package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.MathUtils;

public class FLLBandEdge implements FloatInput {

	private final FloatInput source;
	private ComplexFIRFilter d_filter_upper;
	private ComplexFIRFilter d_filter_lower;
	private ControlLoop controlLoop;
	private final CircularComplexArray array;

	private boolean outputReal = true;
	private final float[] currentComplex = new float[2];
	private final float[] currentUpperComplex = new float[2];
	private final float[] currentLowerComplex = new float[2];

	public FLLBandEdge(FloatInput source, float samplesPerSymbol, float rolloff, int filterSize, float bandwidth) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		if (samplesPerSymbol <= 0) {
			throw new IllegalArgumentException("invalid samples per symbol. Must be positive: " + samplesPerSymbol);
		}
		if (rolloff < 0 || rolloff > 1.0) {
			throw new IllegalArgumentException("invalid rolloff factor. Must be in [0,1]: " + samplesPerSymbol);
		}
		if (filterSize <= 0) {
			throw new IllegalArgumentException("invalid filter size. Must be positive: " + filterSize);
		}
		this.source = source;
		this.array = new CircularComplexArray(filterSize);
		this.controlLoop = new ControlLoop(bandwidth, (float) ((2 * Math.PI) * (2.0 / samplesPerSymbol)), (float) (-(2 * Math.PI) * (2.0 / samplesPerSymbol)));
		design_filter(samplesPerSymbol, rolloff, filterSize);
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			float sourceReal = source.readFloat();
			float sourceImg = source.readFloat();

			double phaseImg = Math.sin(controlLoop.getPhase());
			float phaseReal = (float) MathUtils.fastCos(controlLoop.getPhase(), phaseImg);

			MathUtils.multiply(currentComplex, sourceReal, sourceImg, phaseReal, (float) phaseImg);

			array.add(currentComplex[0], currentComplex[1]);
			// Perform the dot product of the output with the filters
			d_filter_lower.filterComplex(currentUpperComplex, array);
			d_filter_upper.filterComplex(currentLowerComplex, array);

			float error = MathUtils.norm(currentLowerComplex) - MathUtils.norm(currentUpperComplex);

			controlLoop.advanceLoop(error);

			outputReal = !outputReal;
			return currentComplex[0];
		}
		outputReal = !outputReal;
		return currentComplex[1];
	}

	private void design_filter(float samps_per_sym, float rolloff, int filter_size) {
		int M = (int) Math.rint(filter_size / samps_per_sym);
		float power = 0;

		// Create the baseband filter by adding two sincs together
		float[] bb_taps = new float[filter_size];
		for (int i = 0; i < bb_taps.length; i++) {
			float k = -M + i * 2.0f / samps_per_sym;
			float tap = MathUtils.sinc(rolloff * k - 0.5f) + MathUtils.sinc(rolloff * k + 0.5f);
			power += tap;
			bb_taps[i] = tap;
		}

		float[] d_taps_lowerReal = new float[filter_size];
		float[] d_taps_lowerImg = new float[filter_size];
		float[] d_taps_upperReal = new float[filter_size];
		float[] d_taps_upperImg = new float[filter_size];

		// Create the band edge filters by spinning the baseband
		// filter up and down to the right places in frequency.
		// Also, normalize the power in the filters
		float[] tempComplex = new float[2];
		int N = (int) ((bb_taps.length - 1.0) / 2.0);
		for (int i = 0; i < filter_size; i++) {
			float tap = bb_taps[i] / power;

			float k = (-N + i) / (2.0f * samps_per_sym);

			MathUtils.expj(tempComplex, -(2 * (float) Math.PI) * (1 + rolloff) * k);
			MathUtils.multiply(tap, tempComplex);
			d_taps_lowerReal[filter_size - i - 1] = tempComplex[0];
			d_taps_lowerImg[filter_size - i - 1] = tempComplex[1];
			MathUtils.expj(tempComplex, (2 * (float) Math.PI) * (1 + rolloff) * k);
			MathUtils.multiply(tap, tempComplex);
			d_taps_upperReal[filter_size - i - 1] = tempComplex[0];
			d_taps_upperImg[filter_size - i - 1] = tempComplex[1];
		}

		d_filter_upper = new ComplexFIRFilter(d_taps_upperReal, d_taps_upperImg);
		d_filter_lower = new ComplexFIRFilter(d_taps_lowerReal, d_taps_lowerImg);
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
