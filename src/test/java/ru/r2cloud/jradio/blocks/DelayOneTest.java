package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class DelayOneTest {

	@Test
	public void test() throws Exception {
		WavFileSource wav = new WavFileSource(DelayOneTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		try (FloatInput source = new DelayOne(wav)) {
			TestUtil.assertFloatInput("delay1.bin", source);
		}
	}

}
