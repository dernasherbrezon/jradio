package ru.r2cloud.jradio.aausat4;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.Viterbi;

public class AAUSAT4 implements Iterable<AAUSAT4Beacon>, Iterator<AAUSAT4Beacon>, Closeable {

	private final TaggedStreamToPdu input;
	private byte[] current;

	public AAUSAT4(TaggedStreamToPdu input) {
		this.input = input;
	}

	@Override
	public Iterator<AAUSAT4Beacon> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		try {
			current = input.readBytes();
			if (current != null && current.length != 0) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public AAUSAT4Beacon next() {
		if (current[0] == 0x59) {
			// long frame
			byte[] viterbi = Viterbi.decode(Arrays.copyOfRange(current, 1, 250));
			byte[] deShuffled = Randomize.shuffle(viterbi);
			byte[] rawData = ReedSolomon.decode(deShuffled);
			try {
				AAUSAT4Beacon result = new AAUSAT4Beacon();
				result.readExternal(rawData);
				return result;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else if (current[0] == 0xA6) {
			// short frame
		} else {
			throw new IllegalArgumentException("invalid frame start: " + current[0]);
		}
		return null;
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
