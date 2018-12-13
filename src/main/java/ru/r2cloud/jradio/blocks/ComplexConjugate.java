package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class ComplexConjugate implements FloatInput {

	private final FloatInput source;
	private boolean outputReal = true;

	public ComplexConjugate(FloatInput source) {
		if (source.getContext().getChannels() != 2) {
			throw new IllegalArgumentException("input is not a complex number: " + source.getContext().getChannels());
		}
		this.source = source;
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		float result = source.readFloat();
		if (!outputReal) {
			result = -result;
		}
		outputReal = !outputReal;
		return result;
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

}
