package ru.r2cloud.jradio.util;

import ru.r2cloud.jradio.FloatValueSource;

public class NumericallyControlledOscillator {

	private double phase = 0.0f;
	private double amplitude;

	private final FloatValueSource frequency;

	public NumericallyControlledOscillator(FloatValueSource frequency, double amplitude) {
		this.frequency = frequency;
		this.amplitude = amplitude;
	}

	public float sin() {
		float result = (float) (Math.sin(phase) * amplitude);
		step();
		return result;
	}

	public float cos() {
		float result = (float) (Math.cos(phase) * amplitude);
		step();
		return result;
	}

	public void sincos(float[] complex) {
		complex[0] = (float) (Math.cos(phase) * amplitude);
		complex[1] = (float) (Math.sin(phase) * amplitude);
		step();
	}

	private void step() {
		phase += 2 * Math.PI * frequency.getValue();
	}
}
