package ru.r2cloud.jradio.demod;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.Constellation;
import ru.r2cloud.jradio.blocks.ConstellationSoftDecoder;
import ru.r2cloud.jradio.blocks.CostasLoop;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.PolyphaseClockSyncComplex;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.RmsAgcComplex;

public class QpskDemodulator implements ByteInput {

	private final FloatToChar f2char;

	public QpskDemodulator(FloatInput source, int symbolRate, Constellation constel) {
		FloatInput next = source;
		next = new RmsAgcComplex(source, 1e-2f, 0.5f);
		float samplesPerSymbol = next.getContext().getSampleRate() / symbolRate;
		int nfilts = 16;
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f / samplesPerSymbol, 0.5f, (int) (11 * samplesPerSymbol * nfilts));
		next = new PolyphaseClockSyncComplex(next, samplesPerSymbol, 0.1f, rrcTaps, nfilts, nfilts / 2, 1.5f, 1);
		next = new CostasLoop(next, 0.006f, 4, false);
		next = new ConstellationSoftDecoder(next, constel);
		next = new Rail(next, -1.0f, 1.0f);
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
