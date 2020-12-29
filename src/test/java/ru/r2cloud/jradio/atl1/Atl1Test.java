package ru.r2cloud.jradio.atl1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Atl1Test {

	private Atl1RaCoded input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Atl1Test.class.getClassLoader().getResourceAsStream("atl_1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 1250, 5000.0f, 1, 1000, false);
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 0, "0010110111010100", 260 * 8);
		input = new Atl1RaCoded(correlate, 128);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Atl1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
