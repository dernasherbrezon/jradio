package ru.r2cloud.jradio.technosat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.kunspf.KunsPfTest;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.technosat.Technosat;
import ru.r2cloud.jradio.technosat.TechnosatBeacon;
import ru.r2cloud.jradio.tubix20.CMX909bBeacon;

public class TechnosatTest {

	private Technosat input;
	
	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f;
		WavFileSource source = new WavFileSource(KunsPfTest.class.getClassLoader().getResourceAsStream("technosat.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 3000, 100, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 4800, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 4, "111011110000111011110000", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, CMX909bBeacon.MAX_SIZE * 8), 1, Endianness.GR_MSB_FIRST, Byte.class));
		input = new Technosat(pdu);
		assertTrue(input.hasNext());
		TechnosatBeacon beacon = input.next();
		assertNotNull(beacon);
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
