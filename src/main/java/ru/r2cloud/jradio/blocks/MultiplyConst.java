package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class MultiplyConst implements FloatInput {
	
	private final FloatInput source;
	private final float constant;
	
	public MultiplyConst(FloatInput source, float constant) {
		this.source = source;
		this.constant = constant;
	}

	@Override
	public float readFloat() throws IOException {
		return source.readFloat() * constant;
	}
	
	@Override
	public void close() throws IOException {
		source.close();
	}

	@Override
	public Context getContext() {
		return source.getContext();
	}

}
