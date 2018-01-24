package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.FloatInput;

public class ConstellationSoftDecoder implements FloatInput {

	private final FloatInput source;
	private final Constellation constellation;

	private float[] sample = new float[2];
	private float[] currentBits;
	private int currentBitsIndex = 0;

	public ConstellationSoftDecoder(FloatInput source, Constellation constellation) {
		this.source = source;
		this.constellation = constellation;
	}

	@Override
	public float readFloat() throws IOException {
		if (currentBits == null || currentBitsIndex >= currentBits.length) {
			currentBitsIndex = 0;
			sample[0] = source.readFloat();
			sample[1] = source.readFloat();
			currentBits = constellation.softDecisionMaker(sample);
		}
		float result = currentBits[currentBitsIndex];
		currentBitsIndex++;
		return result;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}
}
