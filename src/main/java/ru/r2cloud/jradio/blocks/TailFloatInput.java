package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class TailFloatInput implements FloatInput {

	private final FloatInput input;
	private final int tailSize;
	private final Context ctx;

	private boolean outputTail = false;
	private int current = 0;

	public TailFloatInput(FloatInput input, int tailSamples) {
		this.input = input;
		this.tailSize = tailSamples;
		this.ctx = new Context(input.getContext());
		if (this.ctx.getTotalSamples() != null) {
			this.ctx.setTotalSamples(this.ctx.getTotalSamples() + this.ctx.getChannels() * tailSamples);
		}
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public float readFloat() throws IOException {
		if (!outputTail) {
			try {
				return input.readFloat();
			} catch (EOFException e) {
				outputTail = true;
			}
		}
		if (current >= tailSize) {
			throw new EOFException();
		}
		current++;
		return 0.0f;
	}

	@Override
	public Context getContext() {
		return ctx;
	}

}
