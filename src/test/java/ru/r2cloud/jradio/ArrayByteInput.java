package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;

public class ArrayByteInput implements ByteInput {

	private final Context context;
	private final boolean repeat;
	private int[] result;
	private int index;

	public ArrayByteInput(byte... arr) {
		this(false, false, arr);
	}
	public ArrayByteInput(boolean repeat, boolean softBits, byte... arr) {
		this.result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			result[i] = arr[i];
		}
		this.repeat = repeat;
		context = new Context();
		context.setChannels(1);
		context.setTotalSamples((long) result.length);
		context.setCurrentSample(() -> index);
		context.setSoftBits(softBits);
	}

	public ArrayByteInput(boolean repeat, int... result) {
		this(repeat, false, result);
	}

	public ArrayByteInput(boolean repeat, boolean softBits, int... result) {
		this.result = result;
		this.repeat = repeat;
		context = new Context();
		context.setChannels(1);
		context.setTotalSamples((long) result.length);
		context.setCurrentSample(() -> index);
		context.setSoftBits(softBits);
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
