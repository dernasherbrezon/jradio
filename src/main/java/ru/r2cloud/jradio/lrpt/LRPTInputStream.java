package ru.r2cloud.jradio.lrpt;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ru.r2cloud.jradio.util.IOUtils;

public class LRPTInputStream implements Iterator<Vcdu>, Closeable {

	private final InputStream input;
	private Vcdu currentVcdu = null;

	public LRPTInputStream(InputStream is) {
		this.input = is;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public boolean hasNext() {
		byte[] current = new byte[Vcdu.SIZE];
		try {
			IOUtils.readFully(input, current);
			currentVcdu = new Vcdu();
			currentVcdu.readExternal(current);
			return true;
		} catch (Exception e) {
			currentVcdu = null;
			return false;
		}
	}

	@Override
	public Vcdu next() {
		if (currentVcdu == null) {
			throw new NoSuchElementException();
		}
		return currentVcdu;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
