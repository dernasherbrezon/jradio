package ru.r2cloud.jradio.eseo;

import static org.junit.Assert.assertTrue;

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

public class EseoTest {

	private Eseo input;

	@Test
	public void testSuccess() throws Exception {
		WavFileSource source = new WavFileSource(EseoTest.class.getClassLoader().getResourceAsStream("eseo.wav"));
		FskDemodulator demod = new FskDemodulator(source, 9600);
		SoftToHard s2h = new SoftToHard(demod);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(s2h, 1, EseoBeacon.FLAG, false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 257 * 8), 1, Endianness.GR_MSB_FIRST));
		input = new Eseo(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Eseo.json", input.next());
	}

}
