package ru.r2cloud.jradio.lume1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.gomx1.AX100Decoder;
import ru.r2cloud.jradio.source.WavFileSource;

public class Lume1Test {

	private Lume1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Lume1Test.class.getClassLoader().getResourceAsStream("lume1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 4800);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(demod, 6, "10010011000010110101000111011110", true);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, 255 * 8));
		AX100Decoder ax100 = new AX100Decoder(pdu, false, true, true);
		input = new Lume1(ax100);
		assertTrue(input.hasNext()); // id = 11. Looks like corrupted
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Lume1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
