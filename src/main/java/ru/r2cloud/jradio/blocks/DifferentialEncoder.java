package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class DifferentialEncoder implements ByteInput {

	private final ByteInput input;
	private final int modulus;

	private byte lastOut;

	public DifferentialEncoder(ByteInput input, int modulus) {
		this.input = input;
		this.modulus = modulus;
	}

	@Override
	public byte readByte() throws IOException {
		byte result = (byte) ((input.readByte() + lastOut) % modulus);
		lastOut = result;
		return result;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
