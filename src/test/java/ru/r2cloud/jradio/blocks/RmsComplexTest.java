package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class RmsComplexTest {

	@Test
	public void test() throws Exception {
		WavFileSource stereo = new WavFileSource(RmsComplexTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		try (FloatInput source = new RmsComplex(stereo, 1e-2f)) {
			TestUtil.assertFloatInput("rms.bin", source);
		}
	}

}
