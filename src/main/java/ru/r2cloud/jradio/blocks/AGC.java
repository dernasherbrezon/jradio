package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class AGC implements FloatInput {

	private final FloatInput source;
	private final float rate;
	private final float reference;
	private float gain;
	private final float maxGain;

	private boolean outputReal = true;
	private float img;

	public AGC(FloatInput source, float rate, float reference, float gain, float maxGain) {
		this.source = source;
		this.rate = rate;
		this.reference = reference;
		this.gain = gain;
		this.maxGain = maxGain;
	}

	@Override
	public float readFloat() throws IOException {
		if (outputReal) {
			float real = source.readFloat() * gain;
			img = source.readFloat() * gain;
			gain += rate * (reference - Math.sqrt(real * real + img * img));
			if (gain > maxGain) {
				gain = maxGain;
			}
			outputReal = !outputReal;
			return real;
		}
		outputReal = !outputReal;
		return img;
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
