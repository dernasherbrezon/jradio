package ru.r2cloud.jradio.at03;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.SoftToHard;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class At03Test {

	private At03 input;

	@Test
	public void testSucess() throws Exception {
		WavFileSource source = new WavFileSource(At03Test.class.getClassLoader().getResourceAsStream("at03.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(s2h, 1, "0010110111010100", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 64 * 8), 1, Endianness.GR_MSB_FIRST));
		input = new At03(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("At03Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
