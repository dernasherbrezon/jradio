package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.AbstractTaggedStream;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.TaggedStream;

public class TaggedStreamMultiplyLength extends AbstractTaggedStream implements ByteInput {

	private ByteInput input;
	private double scalar;
	private long written = 0;
	private String d_lengthtag;

	public TaggedStreamMultiplyLength(ByteInput input, String d_lengthtag, double scalar) {
		this.input = input;
		this.d_lengthtag = d_lengthtag;
		this.scalar = scalar;
	}

	@Override
	public byte readByte() throws IOException {
		byte result = input.readByte();
		Tag tag = getTag(written);
		if (tag != null && tag.getKey().equals(d_lengthtag)) {
			tag.setValue(String.valueOf((long) (Double.valueOf(tag.getValue()) * scalar)));
		}
		written++;
		return result;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public Tag getTag(long sampleId) {
		Tag result = super.getTag(sampleId);
		if (result != null) {
			return result;
		}
		if (input instanceof TaggedStream) {
			TaggedStream tg = (TaggedStream) input;
			return tg.getTag(sampleId);
		}
		return null;
	}
}
