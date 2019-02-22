package ru.r2cloud.jradio.snet;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.QuadratureDemodulation;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class SnetTest {
	
	private Snet input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f * 3;
		WavFileSource source = new WavFileSource(SnetTest.class.getClassLoader().getResourceAsStream("snet_a.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		float[] taps = Firdes.lowPass(1.0, source.getContext().getSampleRate(), 1200, 400, Window.WIN_HAMMING, 6.76);
		FrequencyXlatingFIRFilter fir = new FrequencyXlatingFIRFilter(fc, taps, 1, 1500);
		QuadratureDemodulation qd = new QuadratureDemodulation(fir, -5.0f);
		LowPassFilter lpf = new LowPassFilter(qd, 1, 800, 400, Window.WIN_HAMMING, 6.76);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / 1200, (0.1f*0.25f*0.175f*0.175f), 0.5f, gainMu, 0.5f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 4, "00000100110011110101111111001000", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, 512 * 8));
		input = new Snet(pdu);
		assertTrue(input.hasNext());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
