package ru.r2cloud.jradio.sanosat;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class SanosatTest {

	private Sanosat input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(SanosatTest.class.getClassLoader().getResourceAsStream("sanosat1.wav"));
		ByteInput demod = new FskDemodulator(source, 500, 1000.0f, 48, 2000, true);
		input = new Sanosat(demod);
		assertTrue(input.hasNext());
		SanosatBeacon next = input.next();
		AssertJson.assertObjectsEqual("SanosatBeacon.json", next);
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
