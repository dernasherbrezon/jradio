package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class ComplexToReal implements FloatInput {

	private final FloatInput source;
	private final Context context;

	public ComplexToReal(FloatInput source) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("input is not a complex number: " + source.getContext().getChannels());
		}
		this.source = source;
		this.context = new Context(source.getContext());
		this.context.setChannels(1);
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		float result = source.readFloat();
		source.readFloat();
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}
}