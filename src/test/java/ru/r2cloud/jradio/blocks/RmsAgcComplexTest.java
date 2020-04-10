package ru.r2cloud.jradio.blocks;

import java.io.BufferedInputStream;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class RmsAgcComplexTest {

	@Test
	public void testSucces() throws Exception {
		WavFileSource wav = new WavFileSource(new BufferedInputStream(RmsAgcComplexTest.class.getClassLoader().getResourceAsStream("stereo.wav")));
		try (FloatInput source = new RmsAgcComplex(wav, 1e-2f, 0.5f)) {
			TestUtil.assertFloatInput("rmsagc.bin", source);
		}
	}

}
