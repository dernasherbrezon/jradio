package ru.r2cloud.jradio.util;

import java.io.IOException;
import java.io.InputStream;

public class ThrottledInputStream extends InputStream {

	private final InputStream source;

	private final byte[] tempBuffer;

	public ThrottledInputStream(int bandwidth, InputStream source) {
		this.source = source;
		this.tempBuffer = new byte[bandwidth];
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		if( tempBuffer.length >= len ) {
			//read directly into the target buffer
			return source.read(b, off, len);
		}
		int bytesRead = source.read(tempBuffer);
		if (bytesRead < 0) {
			return -1;
		}
		System.arraycopy(tempBuffer, 0, b, off, bytesRead);
		return bytesRead;
	}

	@Override
	public int read() throws IOException {
		return source.read();
	}

	@Override
	public void close() throws IOException {
		source.close();
	}

}
