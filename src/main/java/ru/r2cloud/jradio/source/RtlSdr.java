package ru.r2cloud.jradio.source;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class RtlSdr implements FloatInput {

	private static final int BUFFER_SIZE = 2048;

	private final InputStream iqStream;
	private final Context context;

	private float[] lookupTable;
	private byte[] buffer;
	private int currentBufIndex = 0;
	private int maxBytes;
	private long framePos = 0;

	public RtlSdr(File source, float sampleRate) throws FileNotFoundException {
		// 2 channels each sample is 8bits
		this(new FileInputStream(source), sampleRate, source.length() / 2);
	}

	public RtlSdr(InputStream iqStream, float sampleRate, Long totalSamples) {
		if (iqStream == null) {
			throw new IllegalArgumentException("iqstream cannot be null");
		}
		this.iqStream = iqStream;
		buffer = new byte[BUFFER_SIZE];
		maxBytes = buffer.length;
		lookupTable = new float[0x100];
		for (int i = 0; i < 0x100; ++i) {
			lookupTable[i] = ((i & 0xff) - 127.4f) * (1.0f / 128.0f);
		}
		context = new Context();
		context.setChannels(2);
		context.setSampleSizeInBits(8);
		context.setSampleRate(sampleRate);
		context.setTotalSamples(totalSamples);
		context.setCurrentSample(() -> framePos);
	}

	public RtlSdr(InputStream iqStream, float sampleRate) {
		this(iqStream, sampleRate, null);
	}

	@Override
	public float readFloat() throws IOException {
		if (currentBufIndex == 0 || currentBufIndex >= maxBytes) {
			currentBufIndex = 0;
			maxBytes = iqStream.read(buffer);
			if (maxBytes == -1) {
				throw new EOFException();
			}
		}
		float result = lookupTable[buffer[currentBufIndex] & 0xFF];
		currentBufIndex++;
		if (currentBufIndex % context.getChannels() == 0) {
			framePos++;
		}
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
