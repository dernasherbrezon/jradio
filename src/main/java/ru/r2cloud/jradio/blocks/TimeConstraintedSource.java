package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class TimeConstraintedSource implements FloatInput {

	private final FloatInput input;
	private final long outputsToProduce;
	private long current = 0;
	private final Context ctx;

	public TimeConstraintedSource(FloatInput input, Context ctx) {
		if (ctx.getTotalSamples() == null) {
			throw new IllegalArgumentException("total samples should be specified");
		}
		this.input = input;
		this.outputsToProduce = ctx.getChannels() * ctx.getTotalSamples();
		this.ctx = ctx;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public float readFloat() throws IOException {
		if (current >= outputsToProduce) {
			throw new EOFException();
		}
		float result = input.readFloat();
		current++;
		return result;
	}

	@Override
	public Context getContext() {
		return ctx;
	}

}
