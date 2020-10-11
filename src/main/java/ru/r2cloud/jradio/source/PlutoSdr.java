package ru.r2cloud.jradio.source;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class PlutoSdr implements FloatInput {

	private final InputStream iqStream;
	private final Context context;
	private long framePos = 0;
	private boolean outputReal = true;

	public PlutoSdr(InputStream iqStream, float sampleRate, Long totalSamples) {
		if (iqStream == null) {
			throw new IllegalArgumentException("iqstream cannot be null");
		}
		this.iqStream = new BufferedInputStream(iqStream);
		context = new Context();
		context.setChannels(2);
		context.setSampleSizeInBits(16);
		context.setSampleRate(sampleRate);
		context.setTotalSamples(totalSamples);
		context.setCurrentSample(() -> framePos);
	}

	@Override
	public float readFloat() throws IOException {
		int ch1 = iqStream.read();
		int ch2 = iqStream.read();
		if ((ch1 | ch2) < 0) {
			throw new EOFException();
		}
		// hardware spec from iio: le:S12/16>>0
		short value = (short) ((ch2 << 8) + (ch1 << 0));
		float result = value / 2048.0f; // 2^12.
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
