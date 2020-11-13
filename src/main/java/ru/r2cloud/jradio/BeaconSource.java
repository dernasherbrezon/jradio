package ru.r2cloud.jradio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public abstract class BeaconSource<T> implements Iterator<T>, Closeable {

	private static final Logger LOG = LoggerFactory.getLogger(BeaconSource.class);

	protected MessageInput input;
	private T current = null;

	protected BeaconSource() {
		// sometimes checks should be made before instantiating input
	}

	public BeaconSource(MessageInput input) {
		this.input = input;
	}

	@Override
	public boolean hasNext() {
		while (!Thread.currentThread().isInterrupted()) {
			byte[] raw;
			try {
				raw = input.readBytes();
				if (raw == null || raw.length == 0) {
					continue;
				}
			} catch (EOFException e) {
				current = null;
				return false;
			} catch (IOException e) {
				LOG.error("unable to read input", e);
				current = null;
				return false;
			}

			T beacon = null;
			try {
				beacon = parseBeacon(raw);
			} catch (UncorrectableException e) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("unable to decode beacon: {}", e.getMessage());
				}
				continue;
			} catch (Exception e) {
				// corrupted input might cause IllegalArgumentException or similar
				// make sure only current byte[] fails
				// and decoder continue to parse input
				LOG.error("unable to parse beacon", e);
				continue;
			}

			if (beacon == null) {
				continue;
			}

			if (beacon instanceof Beacon) {
				Beacon realBeacon = (Beacon) beacon;
				Long beginSample = (Long) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
				if (beginSample != null) {
					realBeacon.setBeginSample(beginSample.longValue());
				}
			}
			current = beacon;
			return true;
		}
		LOG.info("thread interrupted. stopping...");
		return false;
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
