package ru.r2cloud.jradio.smogp;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class SmogPSignallingTest {

	private SmogPSignalling input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(SmogPSignallingTest.class.getClassLoader().getResourceAsStream("smog_p_signalling.wav"));
		FskDemodulator demod = new FskDemodulator(source, 1250);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateSyncword correlate = new CorrelateSyncword(s2h, 8, "0010110111010100100101111111110111010011011110110000111100011111", 64 * 8, false);
		input = new SmogPSignalling(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("SmogPSignallingBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
