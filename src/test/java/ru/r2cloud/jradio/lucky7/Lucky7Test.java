package ru.r2cloud.jradio.lucky7;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class Lucky7Test {

	private Lucky7 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f;
		WavFileSource source = new WavFileSource(Lucky7Test.class.getClassLoader().getResourceAsStream("lucky_7.wav"));
		MultiplyConst mc = new MultiplyConst(source, 1.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 5000, 100, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 4800, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 3, "0010110111010100", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, 37 * 8));
		input = new Lucky7(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Lucky7Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
