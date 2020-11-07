package ru.r2cloud.jradio.gomx1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.au02.Au02Test;
import ru.r2cloud.jradio.demod.AfskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Gomx1Test {

	private Gomx1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Au02Test.class.getClassLoader().getResourceAsStream("gomx_1.wav"));
		AfskDemodulator demod = new AfskDemodulator(source, 4800, -1200, 3600, 2);
		input = new Gomx1(demod);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Gomx1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
