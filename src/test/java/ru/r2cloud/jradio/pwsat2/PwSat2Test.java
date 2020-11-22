package ru.r2cloud.jradio.pwsat2;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax25G3ruhBeaconSource;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class PwSat2Test {

	private Ax25G3ruhBeaconSource<PwSat2Beacon> input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(PwSat2Test.class.getClassLoader().getResourceAsStream("pwsat2.wav"));
		FloatToComplex f2c = new FloatToComplex(source);
		BpskDemodulator bpsk = new BpskDemodulator(f2c, 1200, 5, 200, false);
		input = new Ax25G3ruhBeaconSource<>(bpsk, PwSat2Beacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("PwSat2Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
