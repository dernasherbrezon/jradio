package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.source.SigSource;
import ru.r2cloud.jradio.source.Waveform;

public class ChannelModel implements FloatInput {

	private final FloatInput input;

	public ChannelModel(FloatInput input, float noiseVoltage, float frequencyOffsetValue, float epsilon, float[] taps, long noiseSeed) {
		FloatInput next = input;
		if (epsilon != 1.0) {
			next = new FractionalResampler(next, 0.0f, epsilon);
		}
		if (taps != null && taps.length != 0) {
			next = new FIRFilterBlockComplex(next, taps);
		}
		if (frequencyOffsetValue != 0.0) {
			SigSource frequencyOffset = new SigSource(Waveform.COMPLEX, 1, frequencyOffsetValue, 1.0);
			next = new Multiply(next, frequencyOffset);
		}
		if (noiseVoltage != 0.0) {
			FastNoiseSourceComplex noise = new FastNoiseSourceComplex(noiseVoltage, noiseSeed, 1024 * 16);
			next = new Add(next, noise);
		}
		this.input = next;
	}

	@Override
	public float readFloat() throws IOException {
		return input.readFloat();
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
