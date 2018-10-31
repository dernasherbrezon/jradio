package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class QuadratureDemodulation implements FloatInput {

	private final FloatInput source;
	private final Context context;
	private final float gain;

	private final float[] previous = new float[2];
	private final float[] temp = new float[2];

	public QuadratureDemodulation(FloatInput source, float gain) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("complex input is expected. got: " + source.getContext().getChannels());
		}
		this.gain = gain;
		this.source = source;
		context = new Context(source.getContext());
		context.setChannels(1);
		previous[0] = 0.0f;
		previous[1] = 0.0f;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		float real = source.readFloat();
		float img = source.readFloat();
		MathUtils.multiply(temp, real, img, previous[0], -previous[1]);
		previous[0] = real;
		previous[1] = img;
		return gain * MathUtils.fastAtan(temp[1], temp[0]);
	}

	@Override
	public Context getContext() {
		return context;
	}

}
