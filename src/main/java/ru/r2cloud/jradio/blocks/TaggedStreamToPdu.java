package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;

public class TaggedStreamToPdu implements MessageInput {

	private final ByteInput input;
	private final Context context;

	public TaggedStreamToPdu(Context context, ByteInput input) {
		this.input = input;
		this.context = context;
	}

	@Override
	public byte[] readBytes() throws IOException {
		Tag tag = null;
		byte firstByte;
		do {
			// discard bytes
			firstByte = input.readByte();
			tag = context.getCurrent();
			if (tag != null) {
				break;
			}
		} while (true);
		if (tag == null) {
			// shouldnt happen actually see above
			throw new IOException("no tag found");
		}

		int length = (Integer) tag.get(FixedLengthTagger.LENGTH);
		byte[] result = new byte[length];
		result[0] = firstByte;
		for (int i = 1; i < length; i++) {
			result[i] = input.readByte();
		}
		return result;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

}
