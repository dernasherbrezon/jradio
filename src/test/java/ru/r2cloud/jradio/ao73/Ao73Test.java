package ru.r2cloud.jradio.ao73;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Ao73Test {

	private Ao73 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Ao73Test.class.getClassLoader().getResourceAsStream("ao73.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		BpskDemodulator bpsk = new BpskDemodulator(fc, 1200, 5, 1500, true);
		input = new Ao73(bpsk);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Ao73Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
