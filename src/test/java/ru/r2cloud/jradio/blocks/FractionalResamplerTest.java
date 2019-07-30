package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class FractionalResamplerTest {

	@Test
	public void test() throws Exception {
		try (FloatInput source = new FractionalResampler(new InputStreamSource(FractionalResamplerTest.class.getClassLoader().getResourceAsStream("constellation_modulator_diff.bin")), 1.0f, 1.1f)) {
			TestUtil.assertFloatInput("fractional_resampler.bin", source);
		}
	}
}
