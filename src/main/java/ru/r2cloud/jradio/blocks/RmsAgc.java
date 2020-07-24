package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class RmsAgc implements FloatInput {

	private final FloatInput source;

	private final float alpha;
	private final float beta;
	private final float reference;
	private float average;

	public RmsAgc(FloatInput source, float alpha, float reference) {
		if (source.getContext().getChannels() != 1) {
			throw new IllegalArgumentException("not a float input: " + source.getContext().getChannels());
		}
		this.source = source;
		this.alpha = alpha;
		this.beta = 1 - alpha;
		this.average = 0;
		this.reference = 1.0f / reference;
	}

	@Override
	public float readFloat() throws IOException {
		float in = source.readFloat();
		float magnitudeSquared = in * in;
		average = beta * average + alpha * magnitudeSquared;
		float rms = (float) Math.sqrt(average);
		rms = rms * reference + 1e-20f;
		return in / rms;
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
