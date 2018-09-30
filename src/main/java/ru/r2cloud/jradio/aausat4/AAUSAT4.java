package ru.r2cloud.jradio.aausat4;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ViterbiSoft;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class AAUSAT4 implements Iterable<AAUSAT4Beacon>, Iterator<AAUSAT4Beacon>, Closeable {

	private static final Logger LOG = LoggerFactory.getLogger(AAUSAT4.class);

	public static final int SIZE = 2 + 4 + 84 + 2;
	public static final int VITERBI_SIZE = SIZE + 32;
	public static final int VITERBI_TAIL_SIZE = (VITERBI_SIZE + 1) * 2 * 8;

	private final TaggedStreamToPdu input;
	private final ViterbiSoft viterbiSoft;
	private AAUSAT4Beacon current;

	public AAUSAT4(TaggedStreamToPdu input) {
		this.input = input;
		this.viterbiSoft = new ViterbiSoft((byte) 0x4f, (byte) 0x6d, true, AAUSAT4.VITERBI_TAIL_SIZE);
	}

	@Override
	public Iterator<AAUSAT4Beacon> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		while (true) {
			try {
				byte[] raw = input.readBytes();
				if (raw == null || raw.length == 0) {
					continue;
				}
				byte fsm = hardDecode(raw);
				if (fsm == 0xA6) {
					// short frame
					LOG.info("short frame detected");
					continue;
				}
				if (fsm == 0x59) {
					// long frame
					byte[] viterbi = viterbiSoft.decode(Arrays.copyOfRange(raw, 8, raw.length));
					Randomize.shuffle(viterbi);
					try {
						byte[] data = ReedSolomon.decode(viterbi);
						current = new AAUSAT4Beacon();
						current.readExternal(data);
						return true;
					} catch (UncorrectableException e) {
						continue;
					}
				}
				continue;
			} catch (IOException e) {
				return false;
			}
		}
	}

	private static byte hardDecode(byte[] raw) {
		int result = 0;
		for (int i = 0; i < 8; i++) {
			byte toCheck;
			if (raw[i] > 0) {
				toCheck = 1;
			} else {
				toCheck = 0;
			}
			result = (result << 1) | (toCheck & 0x1);
		}
		return (byte) result;
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
