package ru.r2cloud.jradio.itasat1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.AssertJson;
import ru.r2cloud.jradio.blocks.BinarySlicer;
import ru.r2cloud.jradio.blocks.ComplexToReal;
import ru.r2cloud.jradio.blocks.Constellation;
import ru.r2cloud.jradio.blocks.CostasLoop;
import ru.r2cloud.jradio.blocks.FLLBandEdge;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.LMSDDEqualizer;
import ru.r2cloud.jradio.blocks.PolyphaseClockSyncComplex;
import ru.r2cloud.jradio.blocks.RmsAgc;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class Itasat1Test {

	private Itasat1 input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		WavFileSource source = new WavFileSource(Itasat1Test.class.getClassLoader().getResourceAsStream("itasat1.wav"));
		FloatToComplex fc = new FloatToComplex(source);
		float[] taps = Firdes.lowPass(1.0, fc.getContext().getSampleRate(), 1300, 500, Window.WIN_HAMMING, 6.76);
		FrequencyXlatingFIRFilter xlating = new FrequencyXlatingFIRFilter(fc, taps, 5, 1700);
		RmsAgc rmsAgc = new RmsAgc(xlating, 1e-2f, 0.5f);
		float samplesPerSymbol = rmsAgc.getContext().getSampleRate() / 1200;
		FLLBandEdge fll = new FLLBandEdge(rmsAgc, samplesPerSymbol, 0.35f, 100, 0.01f);
		int nfilts = 16;
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f / samplesPerSymbol, 0.35f, (int) (11 * samplesPerSymbol * nfilts));
		PolyphaseClockSyncComplex clockSync = new PolyphaseClockSyncComplex(fll, samplesPerSymbol, 0.1f, rrcTaps, nfilts, nfilts / 2, 0.05f, 2);
		CostasLoop costas = new CostasLoop(clockSync, 0.04f, 2, false);
		Constellation constellation = new Constellation(new float[] { -1, 0, 1, 0 }, new int[] { 0, 1 }, 2, 1);
		LMSDDEqualizer eq = new LMSDDEqualizer(costas, 2, 0.03f, 2, constellation);
		ComplexToReal c2real = new ComplexToReal(eq);
		BinarySlicer bs = new BinarySlicer(c2real);
		input = new Itasat1(bs);
		assertTrue(input.hasNext());
		AssertJson.assertObjectsEqual("Itasat1Beacon.json", input.next());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
