package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class FrequencyXlatingFIRFilterTest {

	@Test
	public void test() throws Exception {
		float[] taps = Firdes.lowPass(1.0, 222222.0f, 222222.0f / 2, 1000, Window.WIN_HAMMING, 6.76);
		try (FloatInput source = new FrequencyXlatingFIRFilter(new WavFileSource(FrequencyXlatingFIRFilterTest.class.getClassLoader().getResourceAsStream("meteor_small.wav")), taps, 10, -10000)) {
			TestUtil.assertFloatInput("xlating.bin", source);
		}
	}

}
