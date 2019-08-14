package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.source.SigSource;
import ru.r2cloud.jradio.source.Waveform;

public class ChannelModel implements FloatInput {

	private final FloatInput input;

	public ChannelModel(FloatInput input, float noiseVoltage, float frequencyOffsetValue, float epsilon, float[] taps, long noiseSeed) {
		FloatInput next = new FractionalResampler(input, 0.0f, epsilon);
		if (taps != null) {
			next = new FIRFilterBlockComplex(next, taps);
		}
		SigSource frequencyOffset = new SigSource(Waveform.COMPLEX, 1, frequencyOffsetValue, 1.0);
		Multiply multiply = new Multiply(next, frequencyOffset);

		FastNoiseSourceComplex noise = new FastNoiseSourceComplex(noiseVoltage, noiseSeed, 1024 * 16);
		this.input = new Add(multiply, noise);
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
