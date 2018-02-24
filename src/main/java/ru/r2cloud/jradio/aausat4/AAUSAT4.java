package ru.r2cloud.jradio.aausat4;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.ccsds.Viterbi;

public class AAUSAT4 implements Iterable<AAUSAT4Beacon>, Iterator<AAUSAT4Beacon>, Closeable {

	private final TaggedStreamToPdu input;
	private AAUSAT4Beacon current;
	private final static Set<Byte> supportedFormats = new HashSet<>();

	static {
		supportedFormats.add((byte) 0x59);
		supportedFormats.add((byte) 0xA6);
	}

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
			byte[] raw = input.readBytes();
			if (raw != null && raw.length != 0 && supportedFormats.contains(raw[0])) {
				if (raw[0] == 0x59) {
					// long frame
					byte[] viterbi = Viterbi.decode(Arrays.copyOfRange(raw, 1, 250));
					byte[] deShuffled = Randomize.shuffle(viterbi);
					try {
						byte[] data = ReedSolomon.decode(deShuffled);
						current = new AAUSAT4Beacon();
						current.readExternal(data);
					} catch (IOException e) {
						return hasNext();
					} catch (UncorrectableException e) {
						return hasNext();
					}
				} else if (raw[0] == 0xA6) {
					// short frame
				}
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
