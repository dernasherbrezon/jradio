package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.CircularComplexArray;

public class FractionalResampler implements FloatInput {

	private final FloatInput input;
	private final float[] currentComplex = new float[2];
	private final CircularComplexArray array;
	private final MMSEFIRInterpolator interp;
	private final Context context;
	private float mu;
	private float muInc;
	private int skip;
	private boolean outputReal = true;

	public FractionalResampler(FloatInput input, float phaseShift, float resampleRatio) {
		this.input = input;
		this.mu = phaseShift;
		this.muInc = resampleRatio;
		interp = new MMSEFIRInterpolator();
		array = new CircularComplexArray(interp.getNumberOfTaps());
		this.skip = interp.getNumberOfTaps();
		this.context = new Context(input.getContext());
		this.context.setSampleRate(input.getContext().getSampleRate() / resampleRatio);
		if (this.context.getTotalSamples() != null) {
			this.context.setTotalSamples((long) (input.getContext().getTotalSamples() / resampleRatio));
		}
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			for (int i = 0; i < skip; i++) {
				array.add(input.readFloat(), input.readFloat());
			}

			interp.interpolateComplex(currentComplex, array, mu);

			double s = mu + muInc;
			double f = Math.floor(s);
			skip = (int) f;
			mu = (float) (s - f);

			if (skip < 0) {// clamp it. This should only happen with bogus input
				skip = 0;
			}

			outputReal = !outputReal;
			return currentComplex[0];
		}

		outputReal = !outputReal;
		return currentComplex[1];
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
