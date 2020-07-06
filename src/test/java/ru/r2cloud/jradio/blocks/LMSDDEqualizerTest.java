package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class LMSDDEqualizerTest {

	@Test
	public void test() throws Exception {
		WavFileSource wav = new WavFileSource(LMSDDEqualizerTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		try (FloatInput source = new LMSDDEqualizer(wav, 2, 0.05f, 2, Constellation.BPSK)) {
			TestUtil.assertFloatInput("lmsdd.bin", source);
		}
	}

}
