package ru.r2cloud.jradio.sat3cat1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.AdditiveScrambler;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.fec.rs.bch.ReedSolomon;

public class Sat3Cat1 extends BeaconSource<Sat3Cat1Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Sat3Cat1.class);
	private final AdditiveScrambler scrambler;
	private final ReedSolomon rs = new ReedSolomon(32);

	public Sat3Cat1(TaggedStreamToPdu input) {
		super(input);
		scrambler = new AdditiveScrambler(0x21, 0x1ff, 8, 8);
	}

	@Override
	protected Sat3Cat1Beacon parseBeacon(byte[] raw) {
		scrambler.shuffle(raw);
		try {
			byte[] data = rs.decode(raw);
			Sat3Cat1Beacon beacon = new Sat3Cat1Beacon();
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
