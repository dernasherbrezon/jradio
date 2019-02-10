package ru.r2cloud.jradio.blocks;

import java.io.IOException;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.Context;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.Tag;

public class TaggedStreamToPdu implements MessageInput {

	private final ByteInput input;

	public TaggedStreamToPdu(ByteInput input) {
		this.input = input;
	}

	@Override
	public byte[] readBytes() throws IOException {
		Tag tag = null;
		byte firstByte;
		do {
			// discard bytes
			firstByte = input.readByte();
			tag = getContext().getCurrent();
			if (tag != null) {
				break;
			}
		} while (true);

		@SuppressWarnings("null")
		int length = (Integer) tag.get(FixedLengthTagger.LENGTH);
		byte[] result = new byte[length];
		result[0] = firstByte;
		for (int i = 1; i < length; i++) {
			result[i] = input.readByte();
		}
		getContext().setCurrent(tag);
		return result;
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
