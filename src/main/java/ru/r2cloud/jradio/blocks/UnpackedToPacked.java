package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.Tag;

public class UnpackedToPacked implements ByteInput {

	private final ByteInput input;
	private final int bitsPerChunk;
	private int index = 0;
	private final Endianness endianness;
	private final Class<?> outputType;

	public UnpackedToPacked(ByteInput input, int bitsPerChunk, Endianness endianness, Class<?> outputType) {
		this.input = input;
		this.bitsPerChunk = bitsPerChunk;
		if (endianness != Endianness.GR_MSB_FIRST && endianness != Endianness.GR_LSB_FIRST) {
			throw new IllegalArgumentException("unsupported endianness: " + endianness);
		}
		this.endianness = endianness;
		this.outputType = outputType;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	private static int getBitBe1(byte x, int bitAddr, int bitsPerChunk) {
		int byteAddr = bitAddr / bitsPerChunk;
		int residue = bitAddr - byteAddr * bitsPerChunk;
		return (x >> (bitsPerChunk - 1 - residue)) & 1;
	}

	@Override
	public byte readByte() throws IOException {
		if (!outputType.equals(Byte.class)) {
			throw new IOException("invalid type: " + outputType);
		}
		int index_tmp = index;
		byte result = 0;
		Tag tag = null;
		switch (endianness) {
		case GR_MSB_FIRST:
			byte tmp = 0;
			for (int j = 0; j < 8; j++) {
				tmp = (byte) ((tmp << 1) | getBitBe1(input.readByte(), index_tmp, bitsPerChunk));
				index_tmp++;
				// reduce length of message
				if (j == 0) {
					tag = getContext().getCurrent();
					if (tag != null) {
						tag.put(FixedLengthTagger.LENGTH, (Integer) tag.get(FixedLengthTagger.LENGTH) / 8);
					}
				}
			}
			result = tmp;
			break;
		case GR_LSB_FIRST:
			long tmp2 = 0;
			for (int j = 0; j < 8; j++) {
				tmp2 = (tmp2 >> 1) | (getBitBe1(input.readByte(), index_tmp, bitsPerChunk) << (8 - 1));
				index_tmp++;
				// reduce length of message
				if (j == 0) {
					tag = getContext().getCurrent();
					if (tag != null) {
						tag.put(FixedLengthTagger.LENGTH, (Integer) tag.get(FixedLengthTagger.LENGTH) / 8);
					}
				}
			}
			result = (byte) tmp2;
			break;
		default:
			throw new IllegalArgumentException("unsupported endianness: " + endianness);
		}
		getContext().setCurrent(tag);
		index = index_tmp;
		return result;
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}

}
