package ru.r2cloud.jradio.blocks;

import java.io.EOFException;
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
		Integer length = null;
		do {
			// discard bytes
			firstByte = input.readByte();
			tag = getContext().getCurrent();
			if (tag != null) {
				length = (Integer) tag.get(FixedLengthTagger.LENGTH);
				if (length != null) {
					break;
				}
			}
		} while (true);

		// length cannot be null due to while(true) above
		if (length == null) {
			throw new EOFException();
		}

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
