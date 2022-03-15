package ru.r2cloud.jradio.demod;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.blocks.ClockRecoveryMM;
import ru.r2cloud.jradio.blocks.DcBlocker;
import ru.r2cloud.jradio.blocks.FloatToChar;
import ru.r2cloud.jradio.blocks.LowPassFilter;
import ru.r2cloud.jradio.blocks.LowPassFilterComplex;
import ru.r2cloud.jradio.blocks.QuadratureDemodulation;
import ru.r2cloud.jradio.blocks.Rail;
import ru.r2cloud.jradio.blocks.Window;
import ru.r2cloud.jradio.trace.StateProvider;
import ru.r2cloud.jradio.trace.TraceContext;
import ru.r2cloud.jradio.util.CircularByteArray;

public class FskDemodulator implements ByteInput, StateProvider {

	private final ByteInput source;
	private final Context context;

	private CircularByteArray buffer;

	public FskDemodulator(FloatInput source, int baudRate) {
		this(source, baudRate, 5000.0f, 1, 2000, true);
	}

	public FskDemodulator(FloatInput source, int baudRate, float deviation, int decimation, double transitionWidth) {
		this(source, baudRate, deviation, decimation, transitionWidth, true);
	}

	public FskDemodulator(FloatInput source, int baudRate, float deviation, int decimation, double transitionWidth, boolean useDcBlock) {
		FloatInput next = source;
		if (next.getContext().getChannels() == 2) {
			float carsonCutoff = Math.abs(deviation) + baudRate / 2.0f;
			next = new LowPassFilterComplex(next, 1.0, carsonCutoff, 0.1 * carsonCutoff, Window.WIN_HAMMING, 6.76);
			next = new QuadratureDemodulation(next, (float) (next.getContext().getSampleRate() / (2 * Math.PI * deviation)));
		}
		next = new LowPassFilter(next, decimation, 1.0, (double) baudRate / 2, transitionWidth, Window.WIN_HAMMING, 6.76);
		float samplesPerSymbol = next.getContext().getSampleRate() / baudRate;
		if (useDcBlock) {
			next = new DcBlocker(next, (int) (Math.ceil(samplesPerSymbol * 32)), true);
		}
		next = new ClockRecoveryMM(next, samplesPerSymbol, (float) ((samplesPerSymbol * Math.PI) / 100), 0.5f, 0.5f / 8.0f, 0.01f);
		next = new Rail(next, -1.0f, 1.0f);
		this.source = new FloatToChar(next, 127.0f);
		this.context = new Context(this.source.getContext());
		this.context.setSoftBits(true);
		if (TraceContext.instance.getDemodTrace() != null) {
			buffer = new CircularByteArray(TraceContext.instance.getDemodTrace().getWindowSize());
			this.context.addStateProvider(this);
		}
	}

	@Override
	public byte readByte() throws IOException {
		byte result = source.readByte();
		if (buffer != null) {
			buffer.add(result >= 0 ? (byte) 1 : 0);
		}
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public void snapState(Map<String, String> state) {
		state.put("demod", Arrays.toString(buffer.getCopy()));
	}
}
