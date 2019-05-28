package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class ChunksToSymbols implements FloatInput {

	private final ByteInput input;
	private final float[] symbols;
	private final Context context;

	private int currentIndex = 0;
	private boolean real = true;

	public ChunksToSymbols(ByteInput input, float[] symbols) {
		this.input = input;
		this.symbols = symbols;
		context = new Context(input.getContext());
		context.setChannels(2);
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (real) {
			currentIndex = input.readByte();
			result = symbols[2 * currentIndex];
		} else {
			result = symbols[2 * currentIndex + 1];
		}
		real = !real;
		return result;
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
