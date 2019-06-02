package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Endianness;

public class PackedToUnpacked implements ByteInput {

	private final ByteInput input;
	private final int bitsPerChunk;
	private int index = 0;
	private int curByte = 0;
	private final Endianness endianness;
	private final Context context;

	public PackedToUnpacked(ByteInput input, int bitsPerChunk, Endianness endianness) {
		if (endianness != Endianness.GR_MSB_FIRST && endianness != Endianness.GR_LSB_FIRST) {
			throw new IllegalArgumentException("unsupported endianness: " + endianness);
		}
		// 8 bits in 1 byte
		if (bitsPerChunk < 1 || bitsPerChunk > 8 || (8 % bitsPerChunk != 0)) {
			throw new IllegalArgumentException("unsupported bits per chunk: " + bitsPerChunk);
		}
		this.input = input;
		this.bitsPerChunk = bitsPerChunk;
		this.endianness = endianness;
		this.context = new Context(input.getContext());
		context.setSampleRate(context.getSampleRate() * (8 / (float) bitsPerChunk));
		if (context.getTotalSamples() != null) {
			context.setTotalSamples(context.getTotalSamples() * (8 / bitsPerChunk));
		}
	}

	@Override
	public byte readByte() throws IOException {
		byte result = 0;
		if (index == 0) {
			curByte = input.readByte() & 0xFF;
		}
		switch (endianness) {
		case GR_MSB_FIRST:
			int tmp = 0;
			for (int j = 0; j < bitsPerChunk; j++, index++) {
				tmp = (tmp << 1) | ((curByte >> (7 - index)) & 1);
			}
			result = (byte) tmp;
			break;
		case GR_LSB_FIRST:
			int tmp2 = 0;
			for (int j = 0; j < bitsPerChunk; j++, index++) {
				tmp2 = (tmp2 << 1) | ((curByte >> index) & 1);
			}
			result = (byte) tmp2;
			break;
		default:
			throw new IllegalArgumentException("unsupported endianness: " + endianness);
		}
		if (index >= 7) {
			index = 0;
		}
		return result;
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
