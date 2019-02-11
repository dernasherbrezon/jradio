package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class BeaconSource<T> implements Iterator<T>, Closeable {

	protected final MessageInput input;
	private T current = null;

	public BeaconSource(MessageInput input) {
		this.input = input;
	}

	@Override
	public boolean hasNext() {
		while (true) {
			try {
				byte[] raw = input.readBytes();
				if (raw == null || raw.length == 0) {
					continue;
				}
				T beacon = parseBeacon(raw);
				if (beacon != null) {
					current = beacon;
					return true;
				}
			} catch (IOException e) {
				current = null;
				return false;
			}
		}
	}

	protected abstract T parseBeacon(byte[] raw);

	@Override
	public T next() {
		if (current == null) {
			throw new NoSuchElementException();
		}
		return current;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

}
