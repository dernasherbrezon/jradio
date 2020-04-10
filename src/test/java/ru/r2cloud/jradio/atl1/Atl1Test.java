package ru.r2cloud.jradio.atl1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class Atl1Test {

	private Atl1RaCoded input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(Atl1Test.class.getClassLoader().getResourceAsStream("atl_1.wav"));
		FskDemodulator demod = new FskDemodulator(source, 1250, gainMu);
		CorrelateAccessCodeTag correlateTag128 = new CorrelateAccessCodeTag(demod, 0, "0010110111010100", true);
		input = new Atl1RaCoded(new TaggedStreamToPdu(new FixedLengthTagger(correlateTag128, 260 * 8)), 128);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Atl1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
