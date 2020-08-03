package ru.r2cloud.jradio.delphini;

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

public class Delphini1Test {

	private Delphini1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Delphini1Test.class.getClassLoader().getResourceAsStream("delphini1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(demod, 4, "10010011000010110101000111011110", true);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, (255 + 3) * 8));
		AX100Decoder ax100 = new AX100Decoder(pdu, false, true, true);
		input = new Delphini1(ax100);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Delphini1Beacon.json", input.next());
	}
	
	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
