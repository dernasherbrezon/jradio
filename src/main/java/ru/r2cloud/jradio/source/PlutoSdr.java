package ru.r2cloud.jradio.source;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class PlutoSdr implements FloatInput {

	private final DataInputStream iqStream;
	private final Context context;
	private final float[] lookupTable;
	private long framePos = 0;
	private boolean outputReal = true;

	public PlutoSdr(InputStream iqStream, float sampleRate, Long totalSamples) {
		if (iqStream == null) {
			throw new IllegalArgumentException("iqstream cannot be null");
		}
		this.iqStream = new DataInputStream(new BufferedInputStream(iqStream));
		lookupTable = new float[(int) Math.pow(2, 16)];
		for (int i = 0; i < lookupTable.length; ++i) {
			lookupTable[i] = (i - 32767.5f) / 32768;
		}
		context = new Context();
		context.setChannels(2);
		context.setSampleSizeInBits(16);
		context.setSampleRate(sampleRate);
		context.setTotalSamples(totalSamples);
		context.setCurrentSample(() -> framePos);
	}

	@Override
	public float readFloat() throws IOException {
		float result = lookupTable[iqStream.readUnsignedShort()];
		if (!outputReal) {
			framePos++;
		}
		outputReal = !outputReal;
		return result;
	}

	@Override
	public void close() throws IOException {
		iqStream.close();
	}

	@Override
	public Context getContext() {
		return context;
	}

}
