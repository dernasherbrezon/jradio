package ru.r2cloud.jradio.nusat;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.QuadratureDemodulation;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.UnpackedToPacked;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class NusatTest {

	private Nusat input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		float gainMu = 0.175f;
		WavFileSource source = new WavFileSource(NusatTest.class.getClassLoader().getResourceAsStream("nusat.wav"));
		float[] taps = Firdes.lowPass(1.0, source.getContext().getSampleRate(), 40000, 1000, Window.WIN_HAMMING, 6.76);
		FrequencyXlatingFIRFilter xlating = new FrequencyXlatingFIRFilter(source, taps, 1, 5760);
		QuadratureDemodulation qd = new QuadratureDemodulation(xlating, 0.2f);
		MultiplyConst mc = new MultiplyConst(qd, 10.0f);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(mc, mc.getContext().getSampleRate() / 40000, (float) (0.25 * gainMu * gainMu), 0.1f, 0.300f, 0.005f);
		BinarySlicer bs = new BinarySlicer(clockRecovery);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(bs, 4, "00000001111001011010101011001100", false);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new UnpackedToPacked(new FixedLengthTagger(correlateTag, 64 * 8), 1, Endianness.GR_MSB_FIRST));
		input = new Nusat(pdu);
		assertTrue(input.hasNext());
	}
	
	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
