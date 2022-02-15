package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class TailByteInput implements ByteInput {

	private final ByteInput input;
	private final int tailSize;
	private final Context ctx;

	private boolean outputTail = false;
	private int current = 0;

	public TailByteInput(ByteInput input, int tailSamples) {
		this.input = input;
		this.tailSize = tailSamples;
		this.ctx = new Context(input.getContext());
		if (this.ctx.getTotalSamples() != null) {
			this.ctx.setTotalSamples(this.ctx.getTotalSamples() + tailSamples);
		}
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public byte readByte() throws IOException {
		if (!outputTail) {
			try {
				return input.readByte();
			} catch (EOFException e) {
				outputTail = true;
			}
		}
		if (current >= tailSize) {
			throw new EOFException();
		}
		current++;
		return 0;
	}

	@Override
	public Context getContext() {
		return ctx;
	}

}
