package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class HardToSoft implements ByteInput {

	private final ByteInput input;
	private int curByte = 0;
	private int index = 8;

	public HardToSoft(ByteInput input) {
		this.input = input;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public byte readByte() throws IOException {
		if (index > 7) {
			index = 0;
			curByte = input.readByte() & 0xFF;
		}
		int bit = (curByte >> (7 - index)) & 0x1;
		index++;
		if (bit == 0) {
			return Byte.MIN_VALUE + 1;
		} else {
			return Byte.MAX_VALUE - 1;
		}
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
