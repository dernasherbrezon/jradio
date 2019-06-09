package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class Rms implements FloatInput {

	private final FloatInput source;
	private final Context context;
	private final float alpha;
	private final float beta;
	private float average;

	public Rms(FloatInput source, float alpha) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("not a complex input: " + source.getContext().getChannels());
		}
		this.source = source;
		this.alpha = alpha;
		beta = 1 - alpha;
		average = 0;
		context = new Context(source.getContext());
		context.setChannels(1);
	}

	@Override
	public float readFloat() throws IOException {
		float inReal = source.readFloat();
		float inImg = source.readFloat();
		double magnitudeSquared = inReal * inReal + inImg * inImg;
		average = (float) (beta * average + alpha * magnitudeSquared);
		return (float)Math.sqrt(average);
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return context;
	}

}
