package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class LowPassFilterTest {

	@Test
	public void test() throws Exception {
		float[] tail = new float[] { -0.053789437f, -0.06347758f, -0.061022278f, -0.049936946f, -0.038055003f, -0.035939537f, -0.05271862f, -0.09143596f, -0.14679343f, -0.20471379f, -0.24546094f, -0.25031567f, -0.20880301f, -0.12433931f, -0.014256093f, 0.09536662f, 0.17798483f, 0.21570331f,
				0.20466295f, 0.15535244f, 0.08783463f, 0.023998192f, -0.020252353f, -0.039171867f, -0.036327414f, -0.021151701f, -0.004134231f, 0.007620448f, 0.01178051f, 0.009854839f, 0.005151304f, 7.348062E-4f, -0.0017211648f, -0.0022562158f, -0.0016780224f, -8.0523995E-4f, -2.1629481E-4f,
				4.044672E-5f, 9.174548E-6f };
		try (FloatInput source = new LowPassFilter(new WavFileSource(LowPassFilterTest.class.getClassLoader().getResourceAsStream("aausat-4.wav")), 5, 1.0, 1000.0, 600.0, Window.WIN_HAMMING, 6.76)) {
			TestUtil.assertFloatInput("low_pass_decim.bin", source);
			TestUtil.assertTail(tail, source);
		}
	}

}
