package ru.r2cloud.jradio.source;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.FloatValueSource;
import ru.r2cloud.jradio.util.NumericallyControlledOscillator;

public class SigSource implements FloatInput {

	private final Waveform waveform;
	private final float[] complex = new float[2];
	private final Context context;

	private NumericallyControlledOscillator nco;
	private boolean real = true;

	public SigSource(Waveform waveform, long sampleRate, final float frequency, double amplitude) {
		this(waveform, sampleRate, new FloatValueSource() {
			@Override
			public float getValue() {
				return frequency;
			}
		}, amplitude);
	}

	public SigSource(Waveform waveform, final long sampleRate, final FloatValueSource frequency, double amplitude) {
		this.waveform = waveform;
		if (!waveform.equals(Waveform.COMPLEX)) {
			throw new IllegalArgumentException("only complex output supported for now");
		}
		nco = new NumericallyControlledOscillator(new FloatValueSource() {

			@Override
			public float getValue() {
				return frequency.getValue() / sampleRate;
			}
		}, amplitude);
		context = new Context();
		context.setSampleRate(sampleRate);
		context.setSampleSizeInBits(32);
		switch (waveform) {
		case COMPLEX:
			context.setChannels(2);
			break;
		default:
			throw new IllegalArgumentException("unsupported waveform: " + waveform);
		}
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			switch (waveform) {
			case COMPLEX:
				nco.sincos(complex);
				result = complex[0];
				break;
			default:
				throw new IllegalArgumentException("unsupported waveform: " + waveform);
			}
		} else {
			result = complex[1];
		}
		real = !real;
		return result;
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public Context getContext() {
		return context;
	}

}
