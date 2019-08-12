package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.util.Lfsr;

public class Scrambler implements ByteInput {

	private final ByteInput source;
	private final Lfsr lfsr;

	public Scrambler(ByteInput source, int mask, int seed, int length) {
		this.source = source;
		this.lfsr = new Lfsr(mask, seed, length);
	}

	@Override
	public byte readByte() throws IOException {
		return lfsr.nextBitScramble(source.readByte());
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
