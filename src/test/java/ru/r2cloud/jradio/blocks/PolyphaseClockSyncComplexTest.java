package ru.r2cloud.jradio.blocks;

import org.junit.Test;

import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.TestUtil;
import ru.r2cloud.jradio.source.WavFileSource;

public class PolyphaseClockSyncComplexTest {

	@Test
	public void test() throws Exception {
		int samplesPerSymbol = 8;
		int nfilts = 16;
		float[] taps = Firdes.rootRaisedCosine(nfilts, nfilts, (float) 1 / samplesPerSymbol, 0.35f, 11 * samplesPerSymbol * nfilts);
		WavFileSource wav = new WavFileSource(PolyphaseClockSyncComplex.class.getClassLoader().getResourceAsStream("stereo.wav"));
		try (FloatInput source = new PolyphaseClockSyncComplex(wav, samplesPerSymbol, 0.05f, taps, nfilts, nfilts / 2, 0.05f, 1)) {
			// skip first 2 complex values. This is due to weird history setting in gnuradio block:
			// set_history(d_taps_per_filter + d_sps + d_sps);
			TestUtil.assertFloatInput(4, "polyphase.bin", source);
		}
	}

	@Test
	public void testOutput2SamplesPerSymbol() throws Exception {
		int samplesPerSymbol = 8;
		int nfilts = 16;
		float[] taps = Firdes.rootRaisedCosine(nfilts, nfilts, (float) 1 / samplesPerSymbol, 0.35f, 11 * samplesPerSymbol * nfilts);
		WavFileSource wav = new WavFileSource(PolyphaseClockSyncComplex.class.getClassLoader().getResourceAsStream("stereo.wav"));
		try (FloatInput source = new PolyphaseClockSyncComplex(wav, samplesPerSymbol, 0.05f, taps, nfilts, nfilts / 2, 0.05f, 2)) {
			// skip first 2 complex values. This is due to weird history setting in gnuradio block:
			// set_history(d_taps_per_filter + d_sps + d_sps);
			TestUtil.assertFloatInput(8, "polyphase_2.bin", source);
		}
	}
}
