package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.AbstractTaggedStream;
import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;
import ru.r2cloud.jradio.TaggedStream;

public class TaggedStreamToPdu extends AbstractTaggedStream implements MessageInput {

	private ByteInput input;
	private String lengthtagname;

	private long read = 0;

	public TaggedStreamToPdu(ByteInput input, String lengthtagname) {
		this.input = input;
		this.lengthtagname = lengthtagname;
	}

	@Override
	public byte[] readBytes() throws IOException {
		Tag tag = null;
		byte firstByte;
		do {
			// discard bytes
			firstByte = input.readByte();
			tag = getTag(read);
			read++;
			if (tag != null && tag.getKey().equals(lengthtagname)) {
				break;
			}
		} while (true);
		if (tag == null) {
			// shouldnt happen actually see above
			throw new IOException("no tag found");
		}

		int length = Integer.valueOf(tag.getValue());
		byte[] result = new byte[length];
		result[0] = firstByte;
		for (int i = 1; i < length; i++) {
			result[i] = input.readByte();
			read++;
		}
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
