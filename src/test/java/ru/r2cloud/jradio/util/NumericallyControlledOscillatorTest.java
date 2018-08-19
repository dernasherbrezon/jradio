package ru.r2cloud.jradio.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.r2cloud.jradio.FloatValueSource;

public class NumericallyControlledOscillatorTest {

	private final float SIN_COS_TOLERANCE = 1e-5f;

	@Test
	public void test() {
		final float freq = 1.0f;
		final float samplingRate = 4.0f;
		NumericallyControlledOscillator nco = new NumericallyControlledOscillator(new FloatValueSource() {

			@Override
			public float getValue() {
				return freq / samplingRate;
			}
		}, 1.0);

		float[] complex = new float[2];
		nco.sincos(complex);
		assertEquals(1.0, complex[0], SIN_COS_TOLERANCE);
		assertEquals(0.0, complex[1], SIN_COS_TOLERANCE);
		nco.sincos(complex);
		assertEquals(0.0, complex[0], SIN_COS_TOLERANCE);
		assertEquals(1.0, complex[1], SIN_COS_TOLERANCE);
		nco.sincos(complex);
		assertEquals(-1.0, complex[0], SIN_COS_TOLERANCE);
		assertEquals(0.0, complex[1], SIN_COS_TOLERANCE);
		nco.sincos(complex);
		assertEquals(0.0, complex[0], SIN_COS_TOLERANCE);
		assertEquals(-1.0, complex[1], SIN_COS_TOLERANCE);
	}

}
