package ru.r2cloud.jradio.ccsds;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class CcsdsBeaconSourceTest {

	private CcsdsBeaconSource<?> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(CcsdsBeaconSourceTest.class.getClassLoader().getResourceAsStream("trisat.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9766);
		input = new CcsdsBeaconSource<>(demod, CcsdsTelemetryBeacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("CcsdsTelemetryBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
	
}
