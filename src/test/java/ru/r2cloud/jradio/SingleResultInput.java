package ru.r2cloud.jradio;

import java.io.IOException;

public class SingleResultInput implements FloatInput {

	private final Context context;
	private float result;

	public SingleResultInput(float result) {
		this.result = result;
		context = new Context();
		context.setChannels(2);
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public float readFloat() throws IOException {
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}

}
