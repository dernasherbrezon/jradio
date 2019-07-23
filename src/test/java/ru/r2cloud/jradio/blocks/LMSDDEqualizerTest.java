package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class LMSDDEqualizerTest {

	@Test
	public void test() throws Exception {
		WavFileSource wav = new WavFileSource(LMSDDEqualizerTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		Constellation constellation = new Constellation(new float[] { -1.0f, 0.0f, 1.0f, 0.0f }, new int[] { 0, 1 }, 2, 1);
		try (FloatInput source = new LMSDDEqualizer(wav, 2, 0.05f, 2, constellation)) {
			TestUtil.assertFloatInput("lmsdd.bin", source);
		}
	}

}
