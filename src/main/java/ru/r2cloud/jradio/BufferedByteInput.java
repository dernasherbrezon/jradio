package ru.r2cloud.jradio;

import java.io.EOFException;
import java.io.IOException;

public class BufferedByteInput implements ByteInput {

	private final ByteInput impl;
	private final byte[] buf;
	private int pos = 0;
	private int count = 0;
	private final int bufferSize;
	private final int maxResetSize;

	public BufferedByteInput(ByteInput impl, int bufferSize, int maxResetSize) {
		this.impl = impl;
		if (bufferSize >= Integer.MAX_VALUE - 8) {
			throw new OutOfMemoryError("Required array size too large");
		}
		this.bufferSize = bufferSize;
		this.maxResetSize = maxResetSize;
		buf = new byte[bufferSize + maxResetSize];
	}

	@Override
	public byte readByte() throws IOException {
		if (pos >= count) {
			fill();
			if (pos >= count) {
				throw new EOFException();
			}
		}
		return buf[pos++];
	}

	private void fill() throws IOException {
		if (pos > bufferSize) {
			//keep last maxResetSize data
			System.arraycopy(buf, bufferSize, buf, 0, maxResetSize);
			pos = maxResetSize;
			count = maxResetSize;
		}
		for (int i = pos; i < buf.length; i++) {
			try {
				buf[i] = impl.readByte();
				count++;
			} catch (EOFException e) {
				break;
			}
		}
	}

	public void reset(int resetSize) {
		if (resetSize > maxResetSize) {
			throw new IllegalArgumentException("reset size is more than max reset size: " + resetSize + " max: " + maxResetSize);
		}
		if (pos <= resetSize) {
			pos = 0;
		} else {
			pos = pos - resetSize;
		}
	}

	@Override
	public void close() throws IOException {
		impl.close();
	}

}
