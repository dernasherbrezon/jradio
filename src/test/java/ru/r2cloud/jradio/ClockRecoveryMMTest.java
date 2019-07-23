package ru.r2cloud.jradio;

import org.junit.Ignore;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.source.WavFileSource;

public class ClockRecoveryMMTest {

	@Test
	// ignore since rounding error for d_mu accumulates
	@Ignore
	public void test() throws Exception {

		try (FloatInput source = new ClockRecoveryMM(new WavFileSource(ClockRecoveryMMTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 20.0f, (float) (0.25 * 0.175 * 0.175), 0.005f, 0.175f, 0.005f)) {
			TestUtil.assertFloatInput("ClockRecoveryMM.bin", source);
		}
	}

}
