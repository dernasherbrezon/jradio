package ru.r2cloud.jradio.kunspf;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.Golay;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class KunsPf implements Iterable<KunsPfBeacon>, Iterator<KunsPfBeacon>, Closeable {

	private static final Logger LOG = LoggerFactory.getLogger(KunsPf.class);

	private final TaggedStreamToPdu input;
	private final Golay golay = new Golay();
	private KunsPfBeacon current;

	public KunsPf(TaggedStreamToPdu input) {
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
				try {
					int lengthField = (raw[0] << 16) | (raw[1] << 8) | raw[2];
					lengthField = golay.decode(lengthField);
					int frameLength = lengthField & 0xFF;
					int viterbiFlag = lengthField & 0x100;
					int scramblerFlag = lengthField & 0x200;
					int rsFlag = lengthField & 0x400;
					if (LOG.isDebugEnabled()) {
						LOG.debug("golay decoded. frameLength: {} viterbiFlag: {}, scramblerFlag: {}, rsFlag: {}", frameLength, viterbiFlag, scramblerFlag, rsFlag);
					}
					byte[] packet = new byte[frameLength];
					System.arraycopy(raw, 3, packet, 0, frameLength);
					Randomize.shuffle(packet);
					byte[] data = ReedSolomon.decode(packet);
					current = new KunsPfBeacon();
					current.readExternal(data);
					Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
					if (beginSample != null) {
						current.setBeginSample(beginSample.longValue());
					}
					return true;
				} catch (UncorrectableException e) {
					continue;
				}
			} catch (IOException e) {
				return false;
			}
		}
	}

	@Override
	public KunsPfBeacon next() {
		return current;
	}

	@Override
	public Iterator<KunsPfBeacon> iterator() {
		return this;
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
