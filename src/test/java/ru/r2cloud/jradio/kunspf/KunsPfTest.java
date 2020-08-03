package ru.r2cloud.jradio.kunspf;

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

public class KunsPfTest {

	private KunsPf input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(KunsPfTest.class.getClassLoader().getResourceAsStream("1kuns_pf.wav"));
		FskDemodulator demod = new FskDemodulator(source, 1200, 5000.0f, 5, 2000);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(demod, 4, "10010011000010110101000111011110", true);
		// 73 choosen as an estimated packet length in test.
		// in real prod, it better to have max - 255
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, 73 * 8));
		AX100Decoder ax100 = new AX100Decoder(pdu, false, true, true);
		input = new KunsPf(ax100);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("KunsPfBeacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
