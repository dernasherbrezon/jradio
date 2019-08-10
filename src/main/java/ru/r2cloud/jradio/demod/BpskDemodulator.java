package ru.r2cloud.jradio.demod;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ComplexToReal;
import ru.r2cloud.jradio.blocks.Constellation;
import ru.r2cloud.jradio.blocks.CostasLoop;
import ru.r2cloud.jradio.blocks.DelayOne;
import ru.r2cloud.jradio.blocks.FLLBandEdge;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.LMSDDEqualizer;
import ru.r2cloud.jradio.blocks.PolyphaseClockSyncComplex;
import ru.r2cloud.jradio.blocks.RmsAgc;
import ru.r2cloud.jradio.blocks.Window;

public class BpskDemodulator implements ByteInput {

	private static final float LOW_PASS_THRESHOLD = 1.1f; // 10%

	private final FloatToChar f2char;

	// produces soft stream of bytes
	public BpskDemodulator(FloatInput input, int symbolRate, int decimation, double centerFrequency, boolean differential) {
		int nfilts = 16;
		float[] taps = Firdes.lowPass(1.0, input.getContext().getSampleRate(), symbolRate * LOW_PASS_THRESHOLD, 1000, Window.WIN_HAMMING, 6.76);
		FrequencyXlatingFIRFilter xlating = new FrequencyXlatingFIRFilter(input, taps, decimation, centerFrequency);
		RmsAgc agc = new RmsAgc(xlating, 1e-2f, 0.5f);
		float samplesPerSymbol = agc.getContext().getSampleRate() / symbolRate;
		FLLBandEdge fll = new FLLBandEdge(agc, samplesPerSymbol, 0.35f, 100, 0.01f);
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f / samplesPerSymbol, 0.35f, (int) (11 * samplesPerSymbol * nfilts));
		PolyphaseClockSyncComplex clock = new PolyphaseClockSyncComplex(fll, samplesPerSymbol, 0.1f, rrcTaps, nfilts, nfilts / 2, 1.5f, 2);
		CostasLoop costas = new CostasLoop(clock, 0.4f, 2, false);
		Constellation constellation = new Constellation(new float[] { -1.0f, 0.0f, 1.0f, 0.0f }, new int[] { 0, 1 }, 2, 1);
		FloatInput next = new LMSDDEqualizer(costas, 2, 0.3f, 2, constellation);
		if (differential) {
			next = new DelayOne(next);
		}
		ComplexToReal complexToReal = new ComplexToReal(next);
		f2char = new FloatToChar(complexToReal, 127.0f);
	}

	@Override
	public byte readByte() throws IOException {
		return f2char.readByte();
	}

	@Override
	public Context getContext() {
		return f2char.getContext();
	}

	@Override
	public void close() throws IOException {
		f2char.close();
	}
}
