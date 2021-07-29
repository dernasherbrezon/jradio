package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class UnpackedToChunks implements ByteInput {

	private final ByteInput input;
	private final Context context;
	private final int bitsPerChunk;

	public UnpackedToChunks(ByteInput input, int bitsPerChunk) {
		this.input = input;
		this.bitsPerChunk = bitsPerChunk;
		this.context = new Context(input.getContext());
		this.context.setSampleRate(this.context.getSampleRate() / bitsPerChunk);
		if (this.context.getTotalSamples() != null) {
			this.context.setTotalSamples((long) Math.ceil(this.context.getTotalSamples().doubleValue() / bitsPerChunk));
		}
	}

	@Override
	public byte readByte() throws IOException {
		int result = 0;
		for (int i = 0; i < bitsPerChunk; i++) {
			result = (result << 1);
			result += input.readByte();
		}
		return (byte) result;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return context;
	}

}
