package ru.r2cloud.jradio.dstar1;

import static org.junit.Assert.assertEquals;
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
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.tubix20.CMX909bBeacon;

public class Dstar1Test {

	private Dstar1 input;

	@Test
	public void testSucess() throws Exception {
		float gainMu = 0.175f;
		WavFileSource source = new WavFileSource(Dstar1Test.class.getClassLoader().getResourceAsStream("dstar_one.wav"));
		MultiplyConst mc = new MultiplyConst(source, -10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 3000, 100, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 4800, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 6, "11001100110011000101011101100101", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, CMX909bBeacon.MAX_SIZE * 8), 1, Endianness.GR_MSB_FIRST, Byte.class));
		input = new Dstar1(pdu);
		assertTrue(input.hasNext());
		Dstar1Beacon beacon = input.next();
		assertNotNull(beacon);
		PayloadData payload = beacon.getPayload();
		assertEquals(0.0036621094f, payload.getSolarZP(), 0.0f);
		assertEquals(0.0015258789f, payload.getSSTotalCurrent(), 0.0f);
		assertEquals(3.3203125f, payload.getSupply3v3(), 0.0f);
		assertEquals(-28586, payload.getCrc16());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
