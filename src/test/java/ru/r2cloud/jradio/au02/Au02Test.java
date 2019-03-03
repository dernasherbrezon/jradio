package ru.r2cloud.jradio.au02;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.ComplexConjugate;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.FixedLengthTagger;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.LowPassFilterComplex;
import ru.r2cloud.jradio.blocks.Multiply;
import ru.r2cloud.jradio.blocks.QuadratureDemodulation;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.gomx1.AX100Decoder;
import ru.r2cloud.jradio.source.SigSource;
import ru.r2cloud.jradio.source.WavFileSource;
import ru.r2cloud.jradio.source.Waveform;

public class Au02Test {

	private Au02 input;

	@Test
	public void testSucess() throws Exception {
		float gainMu = 0.175f;
		WavFileSource source = new WavFileSource(Au02Test.class.getClassLoader().getResourceAsStream("au02.wav"));
		FloatToComplex f2c = new FloatToComplex(source);
		SigSource sigSource = new SigSource(Waveform.COMPLEX, (long) source.getContext().getSampleRate(), -3600.0f, 1.0);
		Multiply multiply = new Multiply(f2c, sigSource);
		ComplexConjugate cc = new ComplexConjugate(multiply);
		LowPassFilterComplex lpf = new LowPassFilterComplex(cc, 1.0, 2600, 1000, Window.WIN_HAMMING, 6.76);

		float samplesPerSymbol = lpf.getContext().getSampleRate() / 4800;
		float sensitivity = (float) ((Math.PI / 2) / samplesPerSymbol);
		float demodGain = (float) (1.0 / sensitivity);
		QuadratureDemodulation qd = new QuadratureDemodulation(lpf, demodGain);
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(qd, samplesPerSymbol, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		Rail rail = new Rail(clockRecovery, -1.0f, 1.0f);
		FloatToChar f2char = new FloatToChar(rail, 127.0f);
		CorrelateAccessCodeTag correlateTag = new CorrelateAccessCodeTag(f2char, 4, "11000011101010100110011001010101", true);
		// in actual decoder should be 255 instead of 120
		TaggedStreamToPdu pdu = new TaggedStreamToPdu(new FixedLengthTagger(correlateTag, (120 + 3) * 8));
		AX100Decoder ax100 = new AX100Decoder(pdu, false, true, true);
		input = new Au02(ax100);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Au02Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
