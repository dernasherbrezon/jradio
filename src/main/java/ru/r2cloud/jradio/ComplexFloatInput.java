package ru.r2cloud.jradio;

import java.io.IOException;

public abstract class ComplexFloatInput implements FloatInput {

	private boolean real = true;
	private float[] complex;

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			complex = readComplex();
			result = complex[0];
		} else {
			result = complex[1];
		}
		real = !real;
		return result;
	}

	public abstract float[] readComplex() throws IOException;

}
