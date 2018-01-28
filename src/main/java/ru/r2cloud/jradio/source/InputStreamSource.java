package ru.r2cloud.jradio.source;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import ru.r2cloud.jradio.ByteInput;
import ru.r2cloud.jradio.FloatInput;

public class InputStreamSource implements FloatInput, ByteInput {

	private final InputStream is;

	public InputStreamSource(InputStream is) {
		if (!(is instanceof BufferedInputStream)) {
			this.is = new BufferedInputStream(is);
		} else {
			this.is = is;
		}
	}

	@Override
	public float readFloat() throws IOException {
		return Float.intBitsToFloat(readInt(is));
	}

	@Override
	public byte readByte() throws IOException {
		return (byte) is.read();
	}

	@Override
	public void close() throws IOException {
		is.close();
	}

	// little endian
	private static int readInt(InputStream is) throws IOException {
		int ch1 = is.read();
		int ch2 = is.read();
		int ch3 = is.read();
		int ch4 = is.read();
		if ((ch1 | ch2 | ch3 | ch4) < 0) {
			throw new EOFException();
		}
		return ((ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0));
	}
}
