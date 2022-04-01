package ru.r2cloud.jradio.itasat1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax25BeaconSource;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Itasat1Test {

	private Ax25BeaconSource<Itasat1Beacon> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Itasat1Test.class.getClassLoader().getResourceAsStream("itasat1.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		BpskDemodulator demodulator = new BpskDemodulator(fc, 1200, 1, 1700, false);
		input = new Ax25BeaconSource<>(demodulator, Itasat1Beacon.class, true, null);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Itasat1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
