package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BeaconInputStream<T extends Beacon> implements Iterator<T>, Closeable {

	private final DataInputStream is;
	private final Class<T> clazz;

	private T current = null;

	public BeaconInputStream(InputStream is, Class<T> clazz) {
		this.is = new DataInputStream(is);
		this.clazz = clazz;
	}

	@Override
	public boolean hasNext() {
		try {
			int length = is.readInt();
			byte[] raw = new byte[length];
			is.readFully(raw);
			T result = clazz.newInstance();
			result.readExternal(raw);
			result.setBeginMillis(is.readLong());
			result.setBeginSample(is.readLong());
			current = result;
			return true;
		} catch (Exception e) {
			current = null;
			return false;
		}
	}

	@Override
	public T next() {
		if (current == null) {
			throw new NoSuchElementException();
		}
		return current;
	}

	@Override
	public void close() throws IOException {
		is.close();
	}
}
