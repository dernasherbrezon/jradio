package ru.r2cloud.jradio.au02;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax100BeaconSource;
import ru.r2cloud.jradio.demod.AfskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Au02Test {

	private Ax100BeaconSource<?> input;

	@Test
	public void testSucess() throws Exception {
		WavFileSource source = new WavFileSource(Au02Test.class.getClassLoader().getResourceAsStream("au02.wav"));
		AfskDemodulator demod = new AfskDemodulator(source, 4800, -1200, 3600, 2);
		// in actual decoder should be 255 instead of 120
		input = new Ax100BeaconSource<>(demod, 120, "11000011101010100110011001010101", Au02Beacon.class, false, true, true);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Au02Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
