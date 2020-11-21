package ru.r2cloud.jradio.lume1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax100BeaconSource;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Lume1Test {

	private Ax100BeaconSource<?> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Lume1Test.class.getClassLoader().getResourceAsStream("lume1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 4800);
		input = new Ax100BeaconSource<>(demod, 255, Lume1Beacon.class);
		assertTrue(input.hasNext()); // id = 11. Looks like corrupted
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Lume1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
