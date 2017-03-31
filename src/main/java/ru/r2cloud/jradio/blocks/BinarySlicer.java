package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.FloatInput;

public class BinarySlicer implements ByteInput {

	private FloatInput input;

	public BinarySlicer(FloatInput input) {
		this.input = input;
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

}
