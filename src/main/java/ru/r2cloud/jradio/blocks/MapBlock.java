package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;

public class MapBlock implements ByteInput {

	private final ByteInput input;
	private final int[] map;

	public MapBlock(ByteInput input, int[] map) {
		this.input = input;
		this.map = new int[0x100];
		for (int i = 0; i < this.map.length; i++) {
			this.map[i] = i;
		}
		int minSize = Math.min(map.length, this.map.length);
		System.arraycopy(map, 0, this.map, 0, minSize);
	}

	@Override
	public byte readByte() throws IOException {
		return (byte) map[input.readByte()];
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
