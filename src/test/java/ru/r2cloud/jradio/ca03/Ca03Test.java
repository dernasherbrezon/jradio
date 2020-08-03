package ru.r2cloud.jradio.ca03;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Ca03Test {

	private Ca03 input;

	@Test
	public void testSucess9600() throws Exception {
		WavFileSource source = new WavFileSource(Ca03Test.class.getClassLoader().getResourceAsStream("ca03_9k6.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		SoftToHard s2h = new SoftToHard(demod);
		Descrambler des = new Descrambler(s2h, 0x21, 0x00, 16);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(des, 6, "10010011000010110101000111011110", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 180 * 8), 1, Endianness.GR_MSB_FIRST));
		input = new Ca03(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Ca03Beacon.json", input.next());
	}

	@Test
	public void testSucess4800() throws Exception {
		WavFileSource source = new WavFileSource(Ca03Test.class.getClassLoader().getResourceAsStream("ca03.wav"));
		FskDemodulator demod = new FskDemodulator(source, 4800, 5000.0f, 2, 2000);
		SoftToHard s2h = new SoftToHard(demod);
		Descrambler des = new Descrambler(s2h, 0x21, 0x00, 16);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(des, 6, "10010011000010110101000111011110", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 180 * 8), 1, Endianness.GR_MSB_FIRST));
		input = new Ca03(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Ca03Beacon-4800.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
