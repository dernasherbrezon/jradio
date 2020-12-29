package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.FloatInput;

public class BinarySlicer implements ByteInput {

	private final FloatInput input;
	private final Context context;

	public BinarySlicer(FloatInput input) {
		this.input = input;
		this.context = new Context(input.getContext());
		this.context.setSoftBits(false);
	}

	@Override
	public byte readByte() throws IOException {
		float f = input.readFloat();
		if (f >= 0) {
			return (byte) 1;
		} else {
			return (byte) 0;
		}
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return context;
	}

}
