package ru.r2cloud.jradio.huskysat1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.demod.BpskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Huskysat1Test {

	private Huskysat1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Huskysat1Test.class.getClassLoader().getResourceAsStream("huskysat1.wav"));
		FloatToComplex f2c = new FloatToComplex(source);
		BpskDemodulator bpsk = new BpskDemodulator(f2c, 1200, 1, 600, true);
		SoftToHard s2h = new SoftToHard(bpsk);
		CorrelateAccessCodeTag correlate = new CorrelateAccessCodeTag(s2h, 4, "1000111110011010010000101011101", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlate, Huskysat1.FRAME_SIZE * 10));
		input = new Huskysat1(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Huskysat1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
