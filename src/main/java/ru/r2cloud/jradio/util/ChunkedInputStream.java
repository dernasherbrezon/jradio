package ru.r2cloud.jradio.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ChunkedInputStream extends InputStream {

	private ByteArrayInputStream is = new ByteArrayInputStream(new byte[0]);

	public void addChunk(byte[] data) {
		int remaining = is.available();
		byte[] newArray = new byte[remaining + data.length];
		is.read(newArray, 0, is.available());
		System.arraycopy(data, 0, newArray, remaining, data.length);
		is = new ByteArrayInputStream(newArray);
	}

	@Override
	public int read() throws IOException {
		return is.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		return is.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return is.read(b, off, len);
	}

	@Override
	public int available() throws IOException {
		return is.available();
	}

	@Override
	public long skip(long n) throws IOException {
		return is.skip(n);
	}

	public void skipFully(long n) throws IOException {
		long remaining = n;
		while (remaining > 0) {
			long skipActual = skip(remaining);
			remaining -= skipActual;
		}
	}

}
