package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;

public abstract class BeaconSource<T> implements Iterable<T>, Iterator<T>, Closeable {

	protected final TaggedStreamToPdu input;
	private T current;

	public BeaconSource(TaggedStreamToPdu input) {
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
				continue;
			} catch (IOException e) {
				return false;
			}
		}
	}

	protected abstract T parseBeacon(byte[] raw);

	@Override
	public T next() {
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

	@Override
	public Iterator<T> iterator() {
		return this;
	}

}
