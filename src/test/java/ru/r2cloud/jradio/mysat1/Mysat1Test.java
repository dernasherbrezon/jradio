package ru.r2cloud.jradio.mysat1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Mysat1Test {

	private Mysat1 input;

	@Test
	public void testSuccess1200() throws Exception {
		WavFileSource source = new WavFileSource(Mysat1Test.class.getClassLoader().getResourceAsStream("mysat1.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		BpskDemodulator bpsk = new BpskDemodulator(fc, 1200, 1, 2000, 1300.0f, false);
		input = new Mysat1(bpsk);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Mysat1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
