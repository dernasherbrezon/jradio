package ru.r2cloud.jradio.spooqy1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax100BeaconSource;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Spooqy1Test {

	private Ax100BeaconSource<Spooqy1Beacon> input;

	@Test
	public void testDemodulate() throws Exception {
		WavFileSource source = new WavFileSource(Spooqy1Test.class.getClassLoader().getResourceAsStream("spooqy_1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 4800);
		input = new Ax100BeaconSource<>(demod, 512, Spooqy1Beacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Spooqy1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
