package ru.r2cloud.jradio.delphini;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax100BeaconSource;
import ru.r2cloud.jradio.csp.CspBeacon;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Delphini1Test {

	private Ax100BeaconSource<CspBeacon> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Delphini1Test.class.getClassLoader().getResourceAsStream("delphini1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		input = new Ax100BeaconSource<>(demod, 255, CspBeacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Delphini1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
