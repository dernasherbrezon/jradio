package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
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
	private final Context context;
	private EOFException eofStreamReached = null;

	public UnpackedToPacked(ByteInput input, int bitsPerChunk, Endianness endianness) {
		this.input = input;
		this.bitsPerChunk = bitsPerChunk;
		if (endianness != Endianness.GR_MSB_FIRST && endianness != Endianness.GR_LSB_FIRST) {
			throw new IllegalArgumentException("unsupported endianness: " + endianness);
		}
		this.endianness = endianness;
		this.context = new Context(input.getContext());
		this.context.setSampleRate(this.context.getSampleRate() / 8);
		if (this.context.getTotalSamples() != null) {
			this.context.setTotalSamples((long) Math.ceil(this.context.getTotalSamples().doubleValue() / 8));
		}
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	private int getBitBe1(int bitAddr, int bitsPerChunk) throws IOException {
		byte x;
		if (eofStreamReached == null) {
			try {
				x = input.readByte();
			} catch (EOFException e) {
				eofStreamReached = e;
				x = 0;
			}
		} else {
			x = 0;
		}
		int byteAddr = bitAddr / bitsPerChunk;
		int residue = bitAddr - byteAddr * bitsPerChunk;
		return (x >> (bitsPerChunk - 1 - residue)) & 1;
	}

	@Override
	public byte readByte() throws IOException {
		if (eofStreamReached != null) {
			throw eofStreamReached;
		}
		int indexTmp = index;
		byte result = 0;
		Tag tag = null;
		switch (endianness) {
		case GR_MSB_FIRST:
			byte tmp = 0;
			for (int j = 0; j < 8; j++) {
				tmp = (byte) ((tmp << 1) | getBitBe1(indexTmp, bitsPerChunk));
				indexTmp++;
				// reduce length of message
				if (j == 0) {
					tag = input.getContext().getCurrent();
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
				tmp2 = (tmp2 >> 1) | (getBitBe1(indexTmp, bitsPerChunk) << (8 - 1));
				indexTmp++;
				// reduce length of message
				if (j == 0) {
					tag = input.getContext().getCurrent();
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
		if (tag != null) {
			context.put(tag.getId(), tag);
		}
		context.setCurrent(tag);
		index = indexTmp;
		return result;
	}

	@Override
	public Context getContext() {
		return context;
	}

	public static byte[] pack(byte[] hardDecisionBits, int offsetBits, int lengthBytes) {
		byte[] result = new byte[lengthBytes];
		int max = offsetBits + lengthBytes * 8;
		for (int i = offsetBits, j = 0; i < max; i++, j++) {
			result[j / 8] <<= 1;
			result[j / 8] |= hardDecisionBits[i];
		}
		return result;
	}

	public static byte[] packLittleEndian(byte[] hardDecisionBits, int offsetBits, int lengthBytes) {
		byte[] result = new byte[lengthBytes];
		for (int i = offsetBits; i < result.length; i++) {
			result[i] = 0x00;
			for (int j = 0; j < 8; j++) {
				result[i] |= (0x01 & hardDecisionBits[i * 8 + j]) << (8 - j - 1);
			}
		}
		return result;
	}

	public static byte[] packSoft(byte[] softDecisionBits, int offsetBits, int lengthBytes) {
		byte[] result = new byte[lengthBytes];
		int max = offsetBits + lengthBytes * 8;
		for (int i = offsetBits, j = 0; i < max; i++, j++) {
			result[j / 8] <<= 1;
			result[j / 8] |= softDecisionBits[i] > 0 ? 1 : 0;
		}
		return result;
	}

	public static byte[] pack(byte[] raw) {
		return pack(raw, 0, raw.length / 8);
	}

}
