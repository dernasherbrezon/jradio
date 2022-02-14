package ru.r2cloud.jradio.source;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class SequentialSource implements FloatInput {

	private final List<FloatInput> packets;
	private int current = 0;
	private final Context ctx;

	public SequentialSource(List<FloatInput> packets, Context ctx) {
		this.packets = packets;
		this.ctx = ctx;
	}

	@Override
	public void close() throws IOException {
		for (FloatInput cur : packets) {
			cur.close();
		}
	}

	@Override
	public float readFloat() throws IOException {
		if (current >= packets.size()) {
			throw new EOFException();
		}
		FloatInput cur = packets.get(current);
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

}
