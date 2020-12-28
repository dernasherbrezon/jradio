package ru.r2cloud.jradio.nusat;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class NusatTest {

	private Nusat input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(NusatTest.class.getClassLoader().getResourceAsStream("nusat.wav"));
		float[] taps = Firdes.lowPass(1.0, source.getContext().getSampleRate(), 40000, 1000, Window.WIN_HAMMING, 6.76);
		FrequencyXlatingFIRFilter xlating = new FrequencyXlatingFIRFilter(source, taps, 1, 5760);
		FskDemodulator demod = new FskDemodulator(xlating, 40_000);
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 4, "00000001111001011010101011001100", 64 * 8, false);
		input = new Nusat(correlate);
		assertTrue(input.hasNext());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
