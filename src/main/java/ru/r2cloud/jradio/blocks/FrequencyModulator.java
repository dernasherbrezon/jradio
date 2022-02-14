package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ComplexFloatInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class FrequencyModulator extends ComplexFloatInput {

	private final FloatInput input;
	private final float sensitivity;
	private final Context context;

	private final float[] complex = new float[2];
	private float phase = 0;

	public FrequencyModulator(FloatInput input, float sensitivity) {
		this.input = input;
		this.sensitivity = sensitivity;
		this.context = new Context(input.getContext());
		this.context.setChannels(2);
	}

	@Override
	public float[] readComplex() throws IOException {
		phase = phase + sensitivity * input.readFloat();
		phase = (float) ((phase + Math.PI) % (2 * Math.PI) - Math.PI);

		int angle = MathUtils.floatToFixed(phase);
		// for angle=0, it will produce: [1.0000024, 7.2191946E-9]
		// outside [-1.0f;1.0f] interval
		MathUtils.sincos(angle, complex);
		return complex;
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
