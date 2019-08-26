package ru.r2cloud.jradio.demod;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.AGC;
import ru.r2cloud.jradio.blocks.ClockRecoveryMMComplex;
import ru.r2cloud.jradio.blocks.Constellation;
import ru.r2cloud.jradio.blocks.ConstellationSoftDecoder;
import ru.r2cloud.jradio.blocks.CostasLoop;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.RootRaisedCosineFilter;

public class QpskDemodulator implements ByteInput {

	private final FloatToChar f2char;

	public QpskDemodulator(FloatInput source, int symbolRate, Constellation constel) {
		float clockAlpha = 0.01f;
		AGC agc = new AGC(source, 1000e-4f, 0.5f, 2.0f, 4000.0f);
		RootRaisedCosineFilter rrcf = new RootRaisedCosineFilter(agc, 1.0f, symbolRate, 0.6f, 361);
		float omega = rrcf.getContext().getSampleRate() / symbolRate;
		ClockRecoveryMMComplex clockmm = new ClockRecoveryMMComplex(rrcf, omega, clockAlpha * clockAlpha / 4, 0.5f, clockAlpha, 0.005f);
		CostasLoop costas = new CostasLoop(clockmm, 0.008f, 4, false);
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
