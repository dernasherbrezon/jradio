package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public abstract class BeaconSource<T> implements Iterator<T>, Closeable {

	private static final Logger LOG = LoggerFactory.getLogger(BeaconSource.class);

	protected final MessageInput input;
	private T current = null;

	public BeaconSource(MessageInput input) {
		this.input = input;
	}

	@Override
	public boolean hasNext() {
		while (true) {
			byte[] raw;
			try {
				raw = input.readBytes();
				if (raw == null || raw.length == 0) {
					continue;
				}
			} catch (IOException e) {
				current = null;
				return false;
			}

			T beacon = null;
			try {
				beacon = parseBeacon(raw);
			} catch (IOException e) {
				LOG.error("unable to parse beacon", e);
				continue;
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to decode reed solomon: {}", e.getMessage());
				}
				continue;
			}

			if (beacon == null) {
				continue;
			}
			
			if (beacon instanceof Beacon) {
				Beacon realBeacon = (Beacon) beacon;
				Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
				if (beginSample != null) {
					realBeacon.setBeginSample(beginSample.longValue());
				}
			}
			current = beacon;
			return true;
		}
	}

	protected abstract T parseBeacon(byte[] raw) throws UncorrectableException, IOException;

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
