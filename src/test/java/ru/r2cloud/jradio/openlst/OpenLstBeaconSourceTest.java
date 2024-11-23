package ru.r2cloud.jradio.openlst;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.dora.DoraBeacon;
import ru.r2cloud.jradio.source.WavFileSource;

public class OpenLstBeaconSourceTest {

	private OpenLstBeaconSource<DoraBeacon> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(OpenLstBeaconSourceTest.class.getClassLoader().getResourceAsStream("dora.wav"));
		FskDemodulator demod = new FskDemodulator(source, 7416);
		input = new OpenLstBeaconSource<>(demod, DoraBeacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Dora.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
	
}
