package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;

public class ArrayByteInput implements ByteInput {

	private final Context context;
	private int[] result;
	private int index;

	public ArrayByteInput(int... result) {
		this.result = result;
		context = new Context();
		context.setChannels(1);
		context.setTotalSamples((long) result.length);
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public byte readByte() throws IOException {
		if (index >= result.length) {
			throw new EOFException();
		}
		int cur = result[index];
		index++;
		return (byte) cur;
	}

	@Override
	public Context getContext() {
		return context;
	}

}
