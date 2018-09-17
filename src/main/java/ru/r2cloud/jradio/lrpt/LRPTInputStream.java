package ru.r2cloud.jradio.lrpt;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class LRPTInputStream implements Iterable<VCDU>, Iterator<VCDU>, Closeable {

	private final InputStream input;
	private VCDU currentVcdu;
	// previous is used for restoring partial packets
	private VCDU previous = null;

	public LRPTInputStream(InputStream is) {
		this.input = is;
	}

	@Override
	public void close() throws IOException {
		input.close();
	}

	@Override
	public boolean hasNext() {
		byte[] current = new byte[VCDU.SIZE];
		try {
			int n = 0;
	        while (n < current.length) {
	            int count = input.read(current, n, current.length - n);
	            if (count < 0) {
	                return false;
	            }
	            n += count;
	        }
			currentVcdu = new VCDU();
			currentVcdu.readExternal(previous, current);
			previous = currentVcdu;
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public VCDU next() {
		return currentVcdu;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<VCDU> iterator() {
		return this;
	}

}
