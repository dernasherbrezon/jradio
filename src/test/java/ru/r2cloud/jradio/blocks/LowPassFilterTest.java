package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class LowPassFilterTest {

	@Test
	public void test() throws Exception {
		try (FloatInput source = new LowPassFilter(new WavFileSource(LowPassFilterTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 5, 1.0, 1000.0, 600.0, Window.WIN_HAMMING, 6.76)) {
			TestUtil.assertFloatInput("low_pass_decim.bin", source);
		}
	}

}
