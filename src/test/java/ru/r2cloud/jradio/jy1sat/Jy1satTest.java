package ru.r2cloud.jradio.jy1sat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import ru.r2cloud.jradio.ao40.Ao40CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.ComplexToReal;
import ru.r2cloud.jradio.blocks.DelayOne;
import ru.r2cloud.jradio.blocks.FLLBandEdge;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.MultiplyConst;
import ru.r2cloud.jradio.blocks.PolyphaseClockSyncComplex;
import ru.r2cloud.jradio.blocks.RmsAgc;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.source.WavFileSource;

public class Jy1satTest {

	private Jy1sat input;

	@Test
	public void testDecodeTelemetry() throws Exception {
		int nfilts = 16;
		WavFileSource source = new WavFileSource(Jy1satTest.class.getClassLoader().getResourceAsStream("jy1sat.wav"));
		float[] taps = Firdes.lowPass(1.0, source.getContext().getSampleRate(), 1300, 500, Window.WIN_HAMMING, 6.76);
		FloatToComplex fc = new FloatToComplex(source);
		FrequencyXlatingFIRFilter xlating = new FrequencyXlatingFIRFilter(fc, taps, 5, 1300);
		RmsAgc agc = new RmsAgc(xlating, 1e-2f, 0.5f);
		float samplesPerSymbol = agc.getContext().getSampleRate() / 1200.0f;
		FLLBandEdge fll = new FLLBandEdge(agc, samplesPerSymbol, 0.35f, 100, 0.01f);
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f / samplesPerSymbol, 0.35f, (int) (11 * samplesPerSymbol * nfilts));
		PolyphaseClockSyncComplex clock = new PolyphaseClockSyncComplex(fll, samplesPerSymbol, 0.1f, rrcTaps, nfilts, nfilts / 2, 0.05f);
		DelayOne delay = new DelayOne(clock);
		ComplexToReal complexToReal = new ComplexToReal(delay);
		MultiplyConst mc = new MultiplyConst(complexToReal, 1.0f);
		FloatToChar f2char = new FloatToChar(mc, 127.0f);
		Ao40CorrelateAccessCodeTag tag = new Ao40CorrelateAccessCodeTag(f2char, 8);
		input = new Jy1sat(tag);
		assertTrue(input.hasNext());
		Jy1satBeacon beacon = input.next();
		assertNotNull(beacon);
		RealtimeTelemetry telemetry = beacon.getRealtimeTelemetry();
		assertEquals(-7.5131965f, telemetry.getAntennaTemp0(), 0.0f);
		assertEquals(142.6915f, telemetry.getBusCurrent(), 0.0f);
		assertEquals(33.920418f, telemetry.getForwardPower(), 0.0f);
		assertEquals(85, telemetry.getImtqMcuTemp());
	}

	@After
	public void stop() throws Exception {
		if (input != null) {
			input.close();
		}
	}

}
