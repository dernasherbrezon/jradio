package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;

public class FastNoiseSourceComplexTest {

	@Test
	public void test() throws Exception {
		try (FloatInput source = new FastNoiseSourceComplex(1.1f, 42L, 8192)) {
			TestUtil.assertFloatInput("noise.bin", source);
		}
	}
	
}
