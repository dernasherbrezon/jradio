package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class RmsAgc implements FloatInput {

	private final FloatInput source;
	private final float[] currentComplex = new float[2];
	private boolean real = true;

	private final float alpha;
	private final float beta;
	private final float reference;
	private float average;

	public RmsAgc(FloatInput source, float alpha, float reference) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		this.source = source;
		this.alpha = alpha;
		this.beta = 1 - alpha;
		this.average = 0;
		this.reference = 1.0f / reference;
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			float inReal = source.readFloat();
			float inImg = source.readFloat();
			double mag_sqrd = inReal * inReal + inImg * inImg;
			average = (float) (beta * average + alpha * mag_sqrd);
			float rms = (float) Math.sqrt(average);
			rms = rms * reference + 1e-20f;
			MathUtils.divide(currentComplex, inReal, inImg, rms, 0.0f);
			result = currentComplex[0];
		} else {
			result = currentComplex[1];
		}
		real = !real;
		return result;
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
