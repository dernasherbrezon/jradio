package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class PolyphaseArbResamplerComplexTest {

	private PolyphaseArbResamplerComplex source;

	@Test
	public void test() throws Exception {
		int samplesPerSymbol = 5;
		int nfilts = 32;
		float[] taps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f, 0.35f, 11 * samplesPerSymbol * nfilts);
		WavFileSource wav = new WavFileSource(PolyphaseArbResamplerComplexTest.class.getClassLoader().getResourceAsStream("stereo.wav"));
		source = new PolyphaseArbResamplerComplex(wav, samplesPerSymbol, taps, 32);
		try (InputStreamSource is = new InputStreamSource(PolyphaseArbResamplerComplexTest.class.getClassLoader().getResourceAsStream("pfb_arb.bin"))) {
			int index = 0;
			while (true) {
				float expected = is.readFloat();
				float actual = source.readFloat();
				assertEquals("failed at index: " + index, expected, actual, 0.0001f);
				index++;
			}
		} catch (EOFException e) {
			// do nothing
		}
	}

	@After
	public void stop() throws Exception {
		if (source != null) {
			source.close();
		}
	}
}
