package ru.r2cloud.jradio.fmn1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Ax25G3ruhBeaconSource;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Fmn1Test {

	private Ax25G3ruhBeaconSource<Fmn1Beacon> input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(Fmn1Test.class.getClassLoader().getResourceAsStream("fmn1.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		BpskDemodulator bpsk = new BpskDemodulator(fc, 9600, 1, 12000, false);
		input = new Ax25G3ruhBeaconSource<>(bpsk, Fmn1Beacon.class);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Fmn1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
	
}
