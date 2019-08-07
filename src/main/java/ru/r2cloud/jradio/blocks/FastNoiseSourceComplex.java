package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;
import ru.r2cloud.jradio.util.RayleighRandom;
import ru.r2cloud.jradio.util.Xoroshiro128p;

public class FastNoiseSourceComplex implements FloatInput {

	private final Context context;
	private final Xoroshiro128p xoroshiroRandom;
	private final float[] samples;
	private final RayleighRandom rayleighRandom;
	private final int numberOfSamples;
	private final long halfLongRemainder;

	private int outputIndex = 0;
	private boolean real = true;

	public FastNoiseSourceComplex(float amplitude, long seed, int numberOfSamples) {
		context = new Context();
		context.setChannels(2);
		this.numberOfSamples = numberOfSamples;
		this.halfLongRemainder = Math.abs(Long.MIN_VALUE % numberOfSamples);
		xoroshiroRandom = new Xoroshiro128p(seed);
		rayleighRandom = new RayleighRandom(5489);
		float adjustedAmplitude = (float) (amplitude / Math.sqrt(2.0f));
		samples = new float[numberOfSamples * 2];
		for (int i = 0; i < samples.length; i++) {
			samples[i] = rayleighRandom.nextFloat() * adjustedAmplitude;
		}
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			// java doesn't support uint64
			// so I divide half of Long by the number of samples and calculate remainder
			// each time I'm getting positive i'm adding this remainder, so I can
			// divide by number of samples one more time and find remainder as if I would divide full uint64
			long next = xoroshiroRandom.next();
			if (next > 0) {
				next = next + halfLongRemainder;
			} else {
				// calculate unsigned, afterwards should fit into the Long
				next = next - Long.MIN_VALUE;
			}
			outputIndex = (int) (next % numberOfSamples);
			result = samples[outputIndex * 2];
		} else {
			result = samples[outputIndex * 2 + 1];
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
