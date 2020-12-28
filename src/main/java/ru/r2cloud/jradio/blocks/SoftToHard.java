package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class SoftToHard implements ByteInput {

	private final ByteInput input;

	public SoftToHard(ByteInput input) {
		this.input = input;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public byte readByte() throws IOException {
		byte f = input.readByte();
		if (f >= 0) {
			return (byte) 1;
		} else {
			return (byte) 0;
		}
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

	public static void convertToHard(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] >= 0) {
				data[i] = 1;
			} else {
				data[i] = 0;
			}
		}
	}

}
