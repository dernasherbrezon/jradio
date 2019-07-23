package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.InputStreamSource;

public class AGCTest {

	@Test
	public void test() throws Exception {
		try (FloatInput source = new AGC(new InputStreamSource(AGCTest.class.getClassLoader().getResourceAsStream("LowPassFilter.bin")), 1000e-4f, 0.5f, 1.0f, 4000.0f)) {
			TestUtil.assertFloatInput("AGC.bin", source);
		}
	}

}
