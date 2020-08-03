package ru.r2cloud.jradio.demod;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.Firdes;
import ru.r2cloud.jradio.blocks.FloatToComplex;
import ru.r2cloud.jradio.blocks.FrequencyXlatingFIRFilter;
import ru.r2cloud.jradio.blocks.QuadratureDemodulation;
import ru.r2cloud.jradio.blocks.Window;

public class AfskDemodulator implements ByteInput {

	private final ByteInput source;

	public AfskDemodulator(FloatInput source, int baudRate, float deviation, float afCarrier, int decimation) {
		FloatInput next = source;
		if (next.getContext().getChannels() == 2) {
			next = new QuadratureDemodulation(next, (float) (next.getContext().getSampleRate() / (2 * Math.PI * deviation)));
		}
		float filterCutoff = 2 * Math.abs(deviation);
		float filterTransition = 0.1f * Math.abs(deviation);
		float[] taps = Firdes.lowPass(1, next.getContext().getSampleRate(), filterCutoff, filterTransition, Window.WIN_HAMMING, 6.76);
		next = new FloatToComplex(next);
		next = new FrequencyXlatingFIRFilter(next, taps, decimation, afCarrier);
		this.source = new FskDemodulator(next, baudRate);
	}

	@Override
	public byte readByte() throws IOException {
		return source.readByte();
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

}
