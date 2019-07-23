package ru.r2cloud.jradio;

import org.junit.Ignore;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.ClockRecoveryMMComplex;
import ru.r2cloud.jradio.source.InputStreamSource;

public class ClockRecoveryMMComplexTest {

	@Test
	// ignore since rounding error for d_mu accumulates
	@Ignore
	public void testLRPT() throws Exception {
		// float omega = (float) ((222222 * 1.0) / (72000 * 1.0));
		float omega = 3.08642f;
		try (FloatInput source = new ClockRecoveryMMComplex(new InputStreamSource(ClockRecoveryMMComplexTest.class.getClassLoader().getResourceAsStream("costas.bin")), omega, (float) (0.001 * 0.001 / 4), 0.5f, 0.001f, 0.005f)) {
			TestUtil.assertFloatInput("clockmm.bin", source);
		}
	}

}
