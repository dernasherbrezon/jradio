package ru.r2cloud.jradio.smogp;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class SmogPTest {

	private SmogP input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(SmogPTest.class.getClassLoader().getResourceAsStream("smog_p_long.wav"));
		FskDemodulator demod = new FskDemodulator(source, 1250);
		input = new SmogP(demod);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("SmogPBeaconSpectrumResult.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
