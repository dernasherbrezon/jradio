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
			if (length != 0) {
				current = readProtocolv1(length);
			} else {
				int protocolVersion = is.readInt();
				if (protocolVersion == BeaconOutputStream.PROTOCOL_V2) {
					current = readProtocolv2();
				} else {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			current = null;
			return false;
		}
	}

	private T readProtocolv1(int length) throws Exception {
		byte[] raw = new byte[length];
		is.readFully(raw);
		T result = clazz.getDeclaredConstructor().newInstance();
		result.readExternal(raw);
		result.setBeginMillis(is.readLong());
		result.setBeginSample(is.readLong());
		return result;
	}

	private T readProtocolv2() throws Exception {
		int length = is.readInt();
		byte[] raw = new byte[length];
		is.readFully(raw);
		T result = clazz.getDeclaredConstructor().newInstance();
		result.readExternal(raw);
		result.setBeginMillis(is.readLong());
		result.setBeginSample(is.readLong());
		RxMetadata meta = new RxMetadata();
		meta.setRssi(is.readFloat());
		meta.setSnr(is.readFloat());
		meta.setFrequencyError(is.readLong());
		result.setRxMeta(meta);
		return result;
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
