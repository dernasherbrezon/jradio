package ru.r2cloud.jradio.itasat1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Itasat1Test {

	private Itasat1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Itasat1Test.class.getClassLoader().getResourceAsStream("itasat1.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		BpskDemodulator demod = new BpskDemodulator(fc, 1200, 1, 1200, false);
		SoftToHard s2h = new SoftToHard(demod);
		input = new Itasat1(s2h);
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
