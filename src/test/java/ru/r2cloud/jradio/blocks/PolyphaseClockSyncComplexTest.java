package ru.r2cloud.jradio.blocks;

import static org.junit.Assert.assertEquals;

import java.io.EOFException;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.source.InputStreamSource;
import ru.r2cloud.jradio.source.WavFileSource;

public class PolyphaseClockSyncComplexTest {

	private PolyphaseClockSyncComplex source;

	@Test
	public void test() throws Exception {
		int samplesPerSymbol = 8;
		int nfilts = 16;
		float[] taps = Firdes.rootRaisedCosine(nfilts, nfilts, (float)1/samplesPerSymbol, 0.35f, 11 * samplesPerSymbol * nfilts);
		WavFileSource wav = new WavFileSource(PolyphaseClockSyncComplex.class.getClassLoader().getResourceAsStream("stereo.wav"));
		source = new PolyphaseClockSyncComplex(wav, samplesPerSymbol, 0.05f, taps, nfilts, nfilts / 2, 0.05f);
		try (InputStreamSource is = new InputStreamSource(CostasLoopTest.class.getClassLoader().getResourceAsStream("polyphase.bin"))) {
			int index = 0;
			//skip first 2 complex values. This is due to weird history setting in gnuradio block:
			//set_history(d_taps_per_filter + d_sps + d_sps);
			is.readFloat();
			is.readFloat();
			is.readFloat();
			is.readFloat();
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
