package ru.r2cloud.jradio.demod;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.DcBlocker;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.QuadratureDemodulation;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.Window;

public class FskDemodulator implements ByteInput {

	private final ByteInput source;

	public FskDemodulator(FloatInput source, int baudRate, float gainMu) {
		this(source, baudRate, 5000.0f, gainMu, 1, 2000);
	}

	public FskDemodulator(FloatInput source, int baudRate, float deviation, float gainMu, int decimation, double transitionWidth) {
		FloatInput next = source;
		if (next.getContext().getChannels() == 2) {
			next = new QuadratureDemodulation(next, (float) (next.getContext().getSampleRate() / (2 * Math.PI * deviation)));
		}
		LowPassFilter lpf2 = new LowPassFilter(next, decimation, 1.0, (double) baudRate / 2, transitionWidth, Window.WIN_HAMMING, 6.76);
		float samplesPerSymbol = lpf2.getContext().getSampleRate() / baudRate;
		DcBlocker dc = new DcBlocker(lpf2, (int) (Math.ceil(samplesPerSymbol * 32)), true);		
		ClockRecoveryMM clockRecovery = new ClockRecoveryMM(dc, samplesPerSymbol, (float) (0.25 * gainMu * gainMu), 0.5f, gainMu, 0.005f);
		Rail rail = new Rail(clockRecovery, -1.0f, 1.0f);
		this.source = new FloatToChar(rail, 127.0f);
	}

	@Override
	public byte readByte() throws IOException {
		return source.readByte();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

}
