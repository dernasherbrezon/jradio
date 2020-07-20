package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.MathUtils;

public class FrequencyModulator implements FloatInput {

	private final FloatInput input;
	private final float sensitivity;
	private final Context context;

	private final float[] complex = new float[2];
	private int complexIndex;
	private float d_phase = 0;

	public FrequencyModulator(FloatInput input, float sensitivity) {
		this.input = input;
		this.sensitivity = sensitivity;
		this.context = new Context(input.getContext());
		this.context.setChannels(2);
		this.complexIndex = complex.length;
	}

	@Override
	public float readFloat() throws IOException {
		if (complexIndex >= complex.length) {
			complexIndex = 0;

			d_phase = d_phase + sensitivity * input.readFloat();
			d_phase = (float) ((d_phase + Math.PI) % (2 * Math.PI) - Math.PI);

			int angle = MathUtils.floatToFixed(d_phase);
			MathUtils.sincos(angle, complex);
		}

		float result = complex[complexIndex];
		complexIndex++;
		return result;
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
