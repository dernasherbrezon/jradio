package ru.r2cloud.jradio.at03;

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

public class At03Test {

	private At03 input;

	@Test
	public void testSucess() throws Exception {
		float gainMu = 0.175f;
		WavFileSource source = new WavFileSource(At03Test.class.getClassLoader().getResourceAsStream("at03.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 5000, 1000, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 9600, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 1, "0010110111010100", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 64 * 8), 1, Endianness.GR_MSB_FIRST, Byte.class));
		input = new At03(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("At03Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
