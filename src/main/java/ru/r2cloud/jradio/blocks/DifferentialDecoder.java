package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class DifferentialDecoder implements ByteInput {

	private final ByteInput input;
	private final int modulus;

	private int lastOut;

	public DifferentialDecoder(ByteInput input, int modulus) {
		this.input = input;
		this.modulus = modulus;
	}

	@Override
	public byte readByte() throws IOException {
		int next = (input.readByte() & 0xFF);
		byte result = (byte) Math.abs(((next - lastOut) % modulus));
		lastOut = next;
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