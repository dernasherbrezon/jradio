package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class InvertBits implements ByteInput {

	private final ByteInput input;

	public InvertBits(ByteInput input) {
		this.input = input;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public byte readByte() throws IOException {
		return (byte) (input.readByte() ^ 0xFF);
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
