package ru.r2cloud.jradio.source;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class SequentialSource implements FloatInput {

	private final List<FloatInput> inputs;
	private int current = 0;
	private final Context ctx;

	public SequentialSource(List<FloatInput> inputs, Context ctx) {
		this.inputs = inputs;
		this.ctx = new Context(ctx);
		this.ctx.setTotalSamples(calculateTotalSamples());
	}

	@Override
	public void close() throws IOException {
		for (FloatInput cur : inputs) {
			cur.close();
		}
	}

	@Override
	public float readFloat() throws IOException {
		if (current >= inputs.size()) {
			throw new EOFException();
		}
		FloatInput cur = inputs.get(current);
		try {
			return cur.readFloat();
		} catch (EOFException e) {
			current++;
			return readFloat();
		}
	}

	@Override
	public Context getContext() {
		return ctx;
	}

	private Long calculateTotalSamples() {
		long totalSamples = 0;
		for (FloatInput cur : inputs) {
			Long curTotal = cur.getContext().getTotalSamples();
			if (curTotal == null) {
				return null;
			}
			totalSamples += curTotal;
		}
		return totalSamples;
	}

}
