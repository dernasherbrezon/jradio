package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class LowPassFilterComplexTest {

	@Test
	public void test() throws Exception {
		try (FloatInput source = new LowPassFilterComplex(new WavFileSource(LowPassFilterComplexTest.class.getClassLoader().getResourceAsStream("meteor_small.wav")), 1.0, 60000.0, 100.0, Window.WIN_HAMMING, 6.76)) {
			TestUtil.assertFloatInput("LowPassFilter.bin", source);
		}
	}

}
