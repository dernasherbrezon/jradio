package ru.r2cloud.jradio.snet;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.demod.AfskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class SnetTest {

	private Snet input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(SnetTest.class.getClassLoader().getResourceAsStream("snet_a.wav"));
		AfskDemodulator demod = new AfskDemodulator(source, 1200, -600, 1500, 8);
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 4, "00000100110011110101111111001000", 512 * 8, false);
		input = new Snet(correlate);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("SnetBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
