package ru.r2cloud.jradio.aistechsat3;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.entrysat.EntrysatTest;
import ru.r2cloud.jradio.gomx1.AX100Decoder;
import ru.r2cloud.jradio.source.WavFileSource;

public class Aistechsat3Test {

	private Aistechsat3 input;

	@Test
	public void testSuccess() throws Exception {
		int symbolRate = 9600;
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(EntrysatTest.class.getClassLoader().getResourceAsStream("aistechsat3.wav"));
		FskDemodulator demod = new FskDemodulator(source, symbolRate, gainMu);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(demod, 4, "10010011000010110101000111011110", true);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, (255 + 3) * 8));
		AX100Decoder ax100 = new AX100Decoder(pdu, false, true, true);
		input = new Aistechsat3(ax100);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Aistechsat3Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
