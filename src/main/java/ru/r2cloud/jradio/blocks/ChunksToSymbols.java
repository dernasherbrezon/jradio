package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class ChunksToSymbols implements FloatInput {

	private final ByteInput input;
	private final float[] symbols;

	public ChunksToSymbols(ByteInput input, float[] symbols) {
		this.input = input;
		this.symbols = symbols;
	}

	@Override
	public float readFloat() throws IOException {
		return symbols[input.readByte()];
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
