package ru.r2cloud.jradio.jy1sat;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Jy1satTest {

	private Jy1sat input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Jy1satTest.class.getClassLoader().getResourceAsStream("jy1sat.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		BpskDemodulator bpsk = new BpskDemodulator(fc, 1200, 5, 1300, true);
		Ao40CorrelateAccessCodeTag tag = new Ao40CorrelateAccessCodeTag(bpsk, 8);
		input = new Jy1sat(tag);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Jy1satBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
