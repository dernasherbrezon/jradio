package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class PolyphaseArbResamplerComplexTest {

	@Test
	public void test() throws Exception {
		int samplesPerSymbol = 5;
		int nfilts = 32;
		float[] taps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f, 0.35f, 11 * samplesPerSymbol * nfilts);
		WavFileSource wav = new WavFileSource(PolyphaseArbResamplerComplexTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		try (FloatInput source = new PolyphaseArbResamplerComplex(wav, samplesPerSymbol, taps, 32)) {
			TestUtil.assertFloatInput("pfb_arb.bin", source);
		}
	}

}
