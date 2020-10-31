package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class MemoryBuffer implements FloatInput {

	private final FloatInput source;
	private final Context context;
	private final List<Float> buffer = new ArrayList<>();
	private int current = 0;

	public MemoryBuffer(FloatInput source) {
		this.source = source;
		while (!Thread.currentThread().isInterrupted()) {
			try {
				buffer.add(source.readFloat());
			} catch (EOFException e) {
				break;
			} catch (IOException e) {
				throw new RuntimeException("unable to buffer input", e);
			}
		}
		context = new Context(source.getContext());
		context.setTotalSamples((long) (buffer.size() / context.getChannels()));
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		if (current >= buffer.size()) {
			throw new EOFException();
		}
		float result = buffer.get(current);
		current++;
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}

}
