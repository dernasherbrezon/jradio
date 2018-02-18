package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.Endianness;
import ru.r2cloud.jradio.Tag;

public class UnpackedToPacked implements ByteInput {

	private final ByteInput input;
	private final Context context;
	private final int d_bits_per_chunk;
	private int d_index = 0;
	private final Endianness d_endianness;
	private final Class<?> outputType;

	public UnpackedToPacked(Context context, ByteInput input, int bits_per_chunk, Endianness endianness, Class<?> outputType) {
		this.input = input;
		this.context = context;
		this.d_bits_per_chunk = bits_per_chunk;
		this.d_endianness = endianness;
		this.outputType = outputType;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	private static int get_bit_be1(byte x, int bit_addr, int bits_per_chunk) {
		int byte_addr = bit_addr / bits_per_chunk;
		int residue = bit_addr - byte_addr * bits_per_chunk;
		return (x >> (bits_per_chunk - 1 - residue)) & 1;
	}

	@Override
	public byte readByte() throws IOException {
		if (!outputType.equals(Byte.class)) {
			throw new IOException("invalid type: " + outputType);
		}
		int index_tmp = d_index;
		byte result = 0;
		Tag tag = null;
		switch (d_endianness) {

		case GR_MSB_FIRST:
			byte tmp = 0;
			for (int j = 0; j < 8; j++) {
				tmp = (byte) ((tmp << 1) | get_bit_be1(input.readByte(), index_tmp, d_bits_per_chunk));
				index_tmp++;
				//reduce length of message
				if (j == 0) {
					tag = context.getCurrent();
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
				tmp2 = (tmp2 >> 1) | (get_bit_be1(input.readByte(), index_tmp, d_bits_per_chunk) << (8 - 1));
				index_tmp++;
				//reduce length of message
				if (j == 0) {
					tag = context.getCurrent();
					if (tag != null) {
						tag.put(FixedLengthTagger.LENGTH, (Integer) tag.get(FixedLengthTagger.LENGTH) / 8);
					}
				}
			}
			result = (byte) tmp2;
			break;
		}
		context.setCurrent(tag);
		d_index = index_tmp;
		return result;
	}

}
