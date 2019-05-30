package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;
import ru.r2cloud.jradio.util.MathUtils;

public class FLLBandEdge implements FloatInput {

	private final FloatInput source;
	private ComplexFIRFilter filterUpper;
	private ComplexFIRFilter filterLower;
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
		designFilter(samplesPerSymbol, rolloff, filterSize);
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
			filterLower.filterComplex(currentUpperComplex, array);
			filterUpper.filterComplex(currentLowerComplex, array);

			float error = MathUtils.norm(currentLowerComplex) - MathUtils.norm(currentUpperComplex);

			controlLoop.advanceLoop(error);

			outputReal = !outputReal;
			return currentComplex[0];
		}
		outputReal = !outputReal;
		return currentComplex[1];
	}

	private void designFilter(float samplesPerSymbol, float rolloff, int filterSize) {
		int m = (int) Math.rint(filterSize / samplesPerSymbol);
		float power = 0;

		// Create the baseband filter by adding two sincs together
		float[] basebandTaps = new float[filterSize];
		for (int i = 0; i < basebandTaps.length; i++) {
			float k = -m + i * 2.0f / samplesPerSymbol;
			float tap = MathUtils.sinc(rolloff * k - 0.5f) + MathUtils.sinc(rolloff * k + 0.5f);
			power += tap;
			basebandTaps[i] = tap;
		}
		
		if (power == 0.0f) {
			throw new IllegalArgumentException("invalid power: " + power);
		}

		float[] tapsLowerReal = new float[filterSize];
		float[] tapsLowerImg = new float[filterSize];
		float[] tapsUpperReal = new float[filterSize];
		float[] tapsUpperImg = new float[filterSize];

		// Create the band edge filters by spinning the baseband
		// filter up and down to the right places in frequency.
		// Also, normalize the power in the filters
		float[] tempComplex = new float[2];
		int n = (int) ((basebandTaps.length - 1.0) / 2.0);
		for (int i = 0; i < filterSize; i++) {
			float tap = basebandTaps[i] / power;

			float k = (-n + i) / (2.0f * samplesPerSymbol);

			MathUtils.expj(tempComplex, -(2 * (float) Math.PI) * (1 + rolloff) * k);
			MathUtils.multiply(tap, tempComplex);
			tapsLowerReal[filterSize - i - 1] = tempComplex[0];
			tapsLowerImg[filterSize - i - 1] = tempComplex[1];
			MathUtils.expj(tempComplex, (2 * (float) Math.PI) * (1 + rolloff) * k);
			MathUtils.multiply(tap, tempComplex);
			tapsUpperReal[filterSize - i - 1] = tempComplex[0];
			tapsUpperImg[filterSize - i - 1] = tempComplex[1];
		}

		filterUpper = new ComplexFIRFilter(tapsUpperReal, tapsUpperImg);
		filterLower = new ComplexFIRFilter(tapsLowerReal, tapsLowerImg);
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
