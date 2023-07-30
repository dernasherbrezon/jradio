package ru.r2cloud.jradio.mrc100;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Mrc100Test {

	private Mrc100 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Mrc100Test.class.getClassLoader().getResourceAsStream("mrc100.wav"));
		FskDemodulator demod = new FskDemodulator(source, 12500, 3125.0f, 1, 2000.0f, true);
		input = new Mrc100(demod, 126, 260);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Mrc100Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
