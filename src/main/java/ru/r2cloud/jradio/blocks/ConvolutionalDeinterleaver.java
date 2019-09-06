package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.util.CircularByteArray;

public class ConvolutionalDeinterleaver implements ByteInput {

	private final ByteInput input;
	private final CircularByteArray buffer;
	private final int delay;
	private final int branches;

	private int currentIndex;

	public ConvolutionalDeinterleaver(ByteInput input, int delay, int branches) {
		this.input = input;
		this.delay = delay;
		this.branches = branches;
		// see https://en.wikipedia.org/wiki/Burst_error-correcting_code#Convolutional_interleaver
		// memory footprint could be optimized to n * (n-1) * d / 2
		this.buffer = new CircularByteArray(branches * (branches - 1) * delay + branches);
		this.currentIndex = branches;
	}

	@Override
	public byte readByte() throws IOException {
		if (currentIndex >= branches) {
			for (int i = 0; i < branches; i++) {
				buffer.add(input.readByte());
			}
			currentIndex = 0;
		}

		int position = currentIndex + currentIndex * delay * branches;
		currentIndex++;
		return buffer.get(position);
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
