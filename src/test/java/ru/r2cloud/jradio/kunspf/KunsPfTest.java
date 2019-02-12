package ru.r2cloud.jradio.kunspf;

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
import ru.r2cloud.jradio.gomx1.AX100Decoder;
import ru.r2cloud.jradio.source.WavFileSource;

public class KunsPfTest {

	private KunsPf input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(KunsPfTest.class.getClassLoader().getResourceAsStream("1kuns_pf.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 1000, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 1200, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 4, "10010011000010110101000111011110", false);
		// 73 choosen as an estimated packet length in test.
		// in real prod, it better to have max - 255
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 73 * 8), 1, Endianness.GR_MSB_FIRST, Byte.class));
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
