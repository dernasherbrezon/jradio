package ru.r2cloud.jradio.geoscan;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class GeoscanTest {

	private Geoscan<GeoscanBeacon> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(GeoscanTest.class.getClassLoader().getResourceAsStream("geoscan.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600, 5000.0f, 1, 2000.0f, true);
		input = new Geoscan<>(demod, GeoscanBeacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("GeoscanBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
