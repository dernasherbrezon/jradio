package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;

public class ArrayByteInput implements ByteInput {

	private final Context context;
	private final boolean repeat;
	private int[] result;
	private int index;

	public ArrayByteInput(byte... arr) {
		this.result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
		this.repeat = false;
		context = new Context();
		context.setChannels(1);
		context.setTotalSamples((long) result.length);
	}

	public ArrayByteInput(boolean repeat, int... result) {
		this.result = result;
		this.repeat = repeat;
		context = new Context();
		context.setChannels(1);
		context.setTotalSamples((long) result.length);
	}

	public ArrayByteInput(int... result) {
		this(false, result);
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public byte readByte() throws IOException {
		if (index >= result.length) {
			if (!repeat) {
				throw new EOFException();
			} else {
				index = 0;
			}
		}
		int cur = result[index];
		index++;
		return (byte) cur;
	}

	@Override
	public Context getContext() {
		return context;
	}
	
	public int getIndex() {
		return index;
	}

}
