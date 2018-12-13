package ru.r2cloud.jradio.au02;

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

public class Au02 extends BeaconSource<Au02Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Au02.class);
	private final Golay golay = new Golay();

	public Au02(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected Au02Beacon parseBeacon(byte[] raw) {
		int lengthField = ((raw[0] & 0xFF) << 16) | ((raw[1] & 0xFF) << 8) | (raw[2] & 0xFF);
		try {
			lengthField = golay.decode(lengthField);
			int frameLength = lengthField & 0xFF;
			int viterbiFlag = lengthField & 0x100;
			int scramblerFlag = lengthField & 0x200;
			int rsFlag = lengthField & 0x400;
			if (LOG.isDebugEnabled()) {
				LOG.debug("golay decoded. frameLength: {} viterbiFlag: {}, scramblerFlag: {}, rsFlag: {}", frameLength, viterbiFlag, scramblerFlag, rsFlag);
			}
			// FIXME add validation
			byte[] data = new byte[frameLength];
			System.arraycopy(raw, 3, data, 0, frameLength);
			if (viterbiFlag > 0) {
				data = ru.r2cloud.jradio.fec.Viterbi.decode(data, (byte) 0x6d, (byte) 0x4f, false);
				// data = Viterbi.decode(data);
			}
			if (scramblerFlag > 0) {
				Randomize.shuffle(data);
			}
			if (rsFlag > 0) {
				data = ReedSolomon.decode(data);
			}
			Au02Beacon beacon = new Au02Beacon();
			beacon.readExternal(data);
			Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
			if (beginSample != null) {
				beacon.setBeginSample(beginSample.longValue());
			}
			return beacon;
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
