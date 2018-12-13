package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class FloatToComplex implements FloatInput {

	private final FloatInput source;
	private boolean outputReal = true;
	private Context context;

	public FloatToComplex(FloatInput source) {
		if (source.getContext().getChannels() != 1) {
			throw new IllegalArgumentException("input should be float: " + source.getContext().getChannels());
		}
		this.source = source;
		context = new Context(source.getContext());
		context.setChannels(2);
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public float readFloat() throws IOException {
		float result;
		if (outputReal) {
			result = source.readFloat();
		} else {
			result = 0.0f;
		}
		outputReal = !outputReal;
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}

}
