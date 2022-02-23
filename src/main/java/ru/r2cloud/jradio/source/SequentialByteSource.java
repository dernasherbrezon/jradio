package ru.r2cloud.jradio.source;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class SequentialByteSource implements ByteInput {

	private final List<ByteInput> inputs;
	private int current = 0;
	private final Context ctx;
	private long totalOutputProduced = 0;

	public SequentialByteSource(List<ByteInput> inputs, Context ctx) {
		this.inputs = inputs;
		this.ctx = new Context(ctx);
		this.ctx.setTotalSamples(calculateTotalSamples());
		this.ctx.setCurrentSample(() -> totalOutputProduced / this.ctx.getChannels());
	}

	@Override
	public void close() throws IOException {
		for (ByteInput cur : inputs) {
			cur.close();
		}
	}
	
	@Override
	public byte readByte() throws IOException {
		if (current >= inputs.size()) {
			throw new EOFException();
		}
		ByteInput cur = inputs.get(current);
		try {
			byte result = cur.readByte();
			totalOutputProduced++;
			return result;
		} catch (EOFException e) {
			current++;
			return readByte();
		}
	}

	@Override
	public Context getContext() {
		return ctx;
	}

	private Long calculateTotalSamples() {
		long totalSamples = 0;
		for (ByteInput cur : inputs) {
			Long curTotal = cur.getContext().getTotalSamples();
			if (curTotal == null) {
				return null;
			}
			totalSamples += curTotal;
		}
		return totalSamples;
	}

}
