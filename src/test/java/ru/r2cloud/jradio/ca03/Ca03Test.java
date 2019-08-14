package ru.r2cloud.jradio.ca03;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.Descrambler;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class Ca03Test {

	private Ca03 input;

	@Test
	public void testSucess9600() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(Ca03Test.class.getClassLoader().getResourceAsStream("ca03_9k6.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 4800, 1000, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 9600, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		Descrambler des = new Descrambler(bs, 0x21, 0x00, 16);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(des, 6, "10010011000010110101000111011110", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 180 * 8), 1, Endianness.GR_MSB_FIRST));
		input = new Ca03(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Ca03Beacon.json", input.next());
	}
	
	@Test
	public void testSucess4800() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(Ca03Test.class.getClassLoader().getResourceAsStream("ca03.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 2400, 1000, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 4800, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		Descrambler des = new Descrambler(bs, 0x21, 0x00, 16);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(des, 6, "10010011000010110101000111011110", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 180 * 8), 1, Endianness.GR_MSB_FIRST));
		input = new Ca03(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Ca03Beacon-4800.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
