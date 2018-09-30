package ru.r2cloud.jradio;

import java.io.IOException;

public class SingleResultInput implements FloatInput {

	private float result;

	public SingleResultInput(float result) {
		this.result = result;
	}

	@Override
	public void close() throws IOException {
		//do nothing
	}

	@Override
	public float readFloat() throws IOException {
		return result;
	}

	@Override
	public Context getContext() {
		return new Context();
	}

}
