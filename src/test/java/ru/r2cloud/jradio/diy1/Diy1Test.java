package ru.r2cloud.jradio.diy1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Diy1Test {

	private Diy1 input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(Diy1Test.class.getClassLoader().getResourceAsStream("diy1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 500, 5000.0f, 24, 2000, false);
		// a bit smaller packet length due to test data
		input = new Diy1(demod, 55);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Diy1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
