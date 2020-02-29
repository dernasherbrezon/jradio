package ru.r2cloud.jradio.florsat;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
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

public class Floripasat1Test {

	private Floripasat1 input;
	private Floripasat1Downlink downlink;

	@Test
	public void testSuccess() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(Floripasat1Test.class.getClassLoader().getResourceAsStream("floripasat_1.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 5, 1.0, 1000, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 1200, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 5, "01011101111001100010101001111110", false);
		// 80 is for test packet only. in prod should be 255
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, (80 + 3) * 8), 1, Endianness.GR_MSB_FIRST));
		input = new Floripasat1(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Floripasat1Beacon.json", input.next());
	}

	@Test
	public void testDownlink() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(Floripasat1Test.class.getClassLoader().getResourceAsStream("floripasatDownlink.wav"));
		MultiplyConst mc = new MultiplyConst(source, 1.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 5, 1.0, 1200, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 2400, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 5, "01011101111001100010101001111110", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, (255 + 3) * 8), 1, Endianness.GR_MSB_FIRST));
		downlink = new Floripasat1Downlink(pdu);
		assertTrue(downlink.hasNext());
		AssertJson.assertObjectsEqual("Floripasat1DownlinkBeacon.json", downlink.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
		if (downlink != null) {
			downlink.close();
		}
	}
}
