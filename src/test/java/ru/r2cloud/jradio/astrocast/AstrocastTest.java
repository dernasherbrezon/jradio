package ru.r2cloud.jradio.astrocast;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.CorrelateSyncword;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.NrziDecode;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.demod.FskDemodulator;
import ru.r2cloud.jradio.source.WavFileSource;

public class AstrocastTest {

	private Astrocast input;
	private Astrocast9k6 input9k6;

	@Test
	public void testDecodeSafemodeTelemetry() throws Exception {
		float gainMu = 0.175f * 5;
		WavFileSource source = new WavFileSource(AstrocastTest.class.getClassLoader().getResourceAsStream("astrocast.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 5, 1.0, 1000, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 1200, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.05f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 8, "0111010111111010110000011010001101011000110100000110010001110110", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 255 * 8), 1, Endianness.GR_MSB_FIRST));
		input = new Astrocast(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("AstrocastBeacon.json", input.next());
	}

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 5;
		WavFileSource source = new WavFileSource(AstrocastTest.class.getClassLoader().getResourceAsStream("astrocast_nrzi.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 5, 1.0, 1000, 600, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 1200, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.05f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		NrziDecode nrzi = new NrziDecode(bs);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(nrzi, 8, "0111010111111010110000011010001101011000110100000110010001110110", false);
		UnpackedToPacked packed = new UnpackedToPacked(new FixedLengthTagger(correlateTag, 255 * 8), 1, Endianness.GR_MSB_FIRST);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(packed);
		input = new Astrocast(pdu);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("AstrocastNrziBeacon.json", input.next());
	}

	@Test
	public void testDecode9k6() throws Exception {
		WavFileSource source = new WavFileSource(AstrocastTest.class.getClassLoader().getResourceAsStream("astrocast_9k6.wav"));
		MultiplyConst mc = new MultiplyConst(source, -10.0f);
		FskDemodulator demod = new FskDemodulator(mc, 9600, 5000.0f, 1, 2000.0f, false);
		CorrelateSyncword correlate = new CorrelateSyncword(demod, 4, "00011010110011111111110000011101", 255 * 8 * 5, false);
		input9k6 = new Astrocast9k6(correlate);
		assertTrue(input9k6.hasNext());
		AssertJson.assertObjectsEqual("Astrocast9k6Beacon.json", input9k6.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
		if (input9k6 != null) {
			input9k6.close();
		}
	}
}
