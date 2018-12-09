package ru.r2cloud.jradio.kunspf;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.Golay;
import ru.r2cloud.jradio.fec.ccsds.Randomize;
import ru.r2cloud.jradio.fec.ccsds.ReedSolomon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class KunsPf extends BeaconSource<KunsPfBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(KunsPf.class);

	private final Golay golay = new Golay();

	public KunsPf(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected KunsPfBeacon parseBeacon(byte[] raw) {
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
			if (3 + frameLength > raw.length) {
				LOG.info("not enough data: {}", raw.length);
				return null;
			}
			byte[] packet = new byte[frameLength];
			System.arraycopy(raw, 3, packet, 0, frameLength);
			Randomize.shuffle(packet);
			byte[] data = ReedSolomon.decode(packet);
			KunsPfBeacon current = new KunsPfBeacon();
			current.readExternal(data);
			Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
			if (beginSample != null) {
				current.setBeginSample(beginSample.longValue());
			}
			return current;
		} catch (UncorrectableException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode reed solomon: " + e.getMessage());
			}
			return null;
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
	}

}
