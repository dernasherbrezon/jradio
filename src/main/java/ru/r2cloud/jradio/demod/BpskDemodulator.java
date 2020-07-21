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
import ru.r2cloud.jradio.blocks.RmsAgcComplex;
import ru.r2cloud.jradio.blocks.Window;

public class BpskDemodulator implements ByteInput {

	private final FloatToChar f2char;

	// produces soft stream of bytes
	public BpskDemodulator(FloatInput source, int baudRate, int decimation, double centerFrequency, boolean differential) {
		FloatInput next = source;
		if (centerFrequency != 0.0 || decimation != 1) {
			float[] taps = Firdes.lowPass(1.0, next.getContext().getSampleRate(), baudRate * 2.0, baudRate * 0.2, Window.WIN_HAMMING, 6.76);
			next = new FrequencyXlatingFIRFilter(next, taps, decimation, centerFrequency);
		}
		float samplesPerSymbol = next.getContext().getSampleRate() / baudRate;
		next = new RmsAgcComplex(next, 2e-2f / samplesPerSymbol, 1.0f);
		next = new FLLBandEdge(next, samplesPerSymbol, 0.35f, 100, (float) (2 * Math.PI / next.getContext().getSampleRate() * 20));
		next = new LowPassFilterComplex(next, 1.0, baudRate, baudRate * 0.1, Window.WIN_HAMMING, 6.76);
		int nfilts = 16;
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f / samplesPerSymbol, 0.35f, (int) (11 * samplesPerSymbol * nfilts));
		next = new PolyphaseClockSyncComplex(next, samplesPerSymbol, 0.1f, rrcTaps, nfilts, nfilts / 2, 1.5f, 1);
		next = new CostasLoop(next, (float) (2 * Math.PI / baudRate * 50), 2, false);
		if (differential) {
			next = new DelayOne(next);
		}
		next = new ComplexToReal(next);
		f2char = new FloatToChar(next, 127.0f);
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
