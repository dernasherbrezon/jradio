package ru.r2cloud.jradio.nayif1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.BpskDemodulator;
import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.source.WavFileSource;

public class Nayif1Test {

	private Nayif1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Nayif1Test.class.getClassLoader().getResourceAsStream("nayif1.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		BpskDemodulator bpsk = new BpskDemodulator(fc, 1200, 5, 1000, true);
		Ao40CorrelateAccessCodeTag tag = new Ao40CorrelateAccessCodeTag(bpsk, 8);
		input = new Nayif1(tag);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Nayif1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
