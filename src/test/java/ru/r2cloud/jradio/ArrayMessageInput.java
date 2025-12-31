package ru.r2cloud.jradio;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class ArrayMessageInput implements MessageInput {

	private final ByteInput input;
	private boolean read = false;

	public ArrayMessageInput(ByteInput input) {
		this.input = input;
	}

	@Override
	public byte[] readBytes() throws IOException {
		if (read) {
			throw new EOFException();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (true) {
			try {
				baos.write(input.readByte());
			} catch (EOFException e) {
				break;
			}
		}
		read = true;
		return baos.toByteArray();
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public Context getContext() {
		return input.getContext();
	}
}
