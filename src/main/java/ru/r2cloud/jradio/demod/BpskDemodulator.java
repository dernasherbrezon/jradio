package ru.r2cloud.jradio.demod;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ComplexToReal;
import ru.r2cloud.jradio.blocks.CostasLoop;
import ru.r2cloud.jradio.blocks.DelayOne;
import ru.r2cloud.jradio.blocks.FLLBandEdge;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.LowPassFilterComplex;
import ru.r2cloud.jradio.blocks.PolyphaseClockSyncComplex;
import ru.r2cloud.jradio.blocks.RmsAgc;
import ru.r2cloud.jradio.blocks.Window;

public class BpskDemodulator implements ByteInput {

	private static final float LOW_PASS_THRESHOLD = 1.1f; // 10%

	private final FloatToChar f2char;

	// produces soft stream of bytes
	public BpskDemodulator(FloatInput input, int symbolRate, int decimation, double centerFrequency, float bandwidth, boolean differential) {
		FloatInput next = input;
		if (centerFrequency != 0.0) {
			float[] taps = Firdes.lowPass(1.0, next.getContext().getSampleRate(), symbolRate * LOW_PASS_THRESHOLD, 1000, Window.WIN_HAMMING, 6.76);
			next = new FrequencyXlatingFIRFilter(next, taps, decimation, centerFrequency);
		}
		RmsAgc agc = new RmsAgc(next, 1e-2f, 0.5f);
		float samplesPerSymbol = agc.getContext().getSampleRate() / symbolRate;
		FLLBandEdge fll = new FLLBandEdge(agc, samplesPerSymbol, 0.35f, 100, 0.01f);
		LowPassFilterComplex lpf = new LowPassFilterComplex(fll, 1.0, bandwidth, 500, Window.WIN_HAMMING, 6.76);
		int nfilts = 16;
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f / samplesPerSymbol, 0.35f, (int) (11 * samplesPerSymbol * nfilts));
		PolyphaseClockSyncComplex clock = new PolyphaseClockSyncComplex(lpf, samplesPerSymbol, 0.1f, rrcTaps, nfilts, nfilts / 2, 1.5f, 1);
		next = new CostasLoop(clock, 0.1f, 2, false);
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
