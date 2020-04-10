package ru.r2cloud.jradio.smogp;

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

public class SmogPSignallingTest {

	private SmogPSignalling input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(SmogPSignallingTest.class.getClassLoader().getResourceAsStream("smog_p_signalling.wav"));
		FskDemodulator demod = new FskDemodulator(source, 1250, gainMu);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(s2h, 8, "0010110111010100100101111111110111010011011110110000111100011111", false);
		UnpackedToPacked packed = new UnpackedToPacked(new FixedLengthTagger(correlateTag, 64 * 8), 1, Endianness.GR_MSB_FIRST);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(packed);
		input = new SmogPSignalling(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("SmogPSignallingBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
