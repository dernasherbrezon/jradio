package ru.r2cloud.jradio.aistechsat3;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.entrysat.EntrysatTest;
import ru.r2cloud.jradio.gomx1.AX100Decoder;
import ru.r2cloud.jradio.source.WavFileSource;

public class Aistechsat3Test {

	private Aistechsat3 input;

	@Test
	public void testSuccess() throws Exception {
		float symbolRate = 9600.0f;
		WavFileSource source = new WavFileSource(EntrysatTest.class.getClassLoader().getResourceAsStream("aistechsat3.wav"));
		MultiplyConst mc = new MultiplyConst(source, 10.0f);
		LowPassFilter lpf = new LowPassFilter(mc, 1.0, 4800.0, 2000, Window.WIN_HAMMING, 6.76);
		float gainMu = 0.175f * 3;
		ClockRecoveryMM clock = new ClockRecoveryMM(lpf, lpf.getContext().getSampleRate() / symbolRate, 0.25f * gainMu * gainMu, 0.5f, gainMu, 0.005f);
		Rail rail = new Rail(clock, -1.0f, 1.0f);
		FloatToChar f2char = new FloatToChar(rail, 127.0f);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(f2char, 4, "10010011000010110101000111011110", true);
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, (255 + 3) * 8));
		AX100Decoder ax100 = new AX100Decoder(pdu, false, true, true);
		input = new Aistechsat3(ax100);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Aistechsat3Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
