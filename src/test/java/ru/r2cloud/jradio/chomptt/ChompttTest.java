package ru.r2cloud.jradio.chomptt;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.AfskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class ChompttTest {

	private Chomptt input;

	@Test
	public void testSuccess() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(ChompttTest.class.getClassLoader().getResourceAsStream("chomptt.wav"));
		AfskDemodulator demod = new AfskDemodulator(source, 1200, 500, 1700, gainMu);
		input = new Chomptt(demod);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("ChompttBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
