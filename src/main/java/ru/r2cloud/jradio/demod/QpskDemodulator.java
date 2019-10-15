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
import ru.r2cloud.jradio.blocks.RmsAgc;

public class QpskDemodulator implements ByteInput {

	private final FloatToChar f2char;

	public QpskDemodulator(FloatInput source, int symbolRate, Constellation constel) {
		RmsAgc agc = new RmsAgc(source, 1e-2f, 0.5f);
		float omega = agc.getContext().getSampleRate() / symbolRate;
		int nfilts = 16;
		float[] rrcTaps = Firdes.rootRaisedCosine(nfilts, nfilts, 1.0f / omega, 0.5f, (int) (11 * omega * nfilts));
		PolyphaseClockSyncComplex clockmm = new PolyphaseClockSyncComplex(agc, omega, 0.1f, rrcTaps, nfilts, nfilts / 2, 1.5f, 1);
		CostasLoop costas = new CostasLoop(clockmm, 0.006f, 4, false);
		ConstellationSoftDecoder constelDecoder = new ConstellationSoftDecoder(costas, constel);
		Rail rail = new Rail(constelDecoder, -1.0f, 1.0f);
		f2char = new FloatToChar(rail, 127.0f);
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
