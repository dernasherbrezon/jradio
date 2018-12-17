package ru.r2cloud.jradio.beesat;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;

public class Beesat extends BeaconSource<BeesatBeacon> {
	
	private static final Logger LOG = LoggerFactory.getLogger(Beesat.class);

	public Beesat(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected BeesatBeacon parseBeacon(byte[] raw) {
		BeesatBeacon beacon = new BeesatBeacon();
		try {
			beacon.readExternal(raw);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
		Float beginSample = (Float) input.getContext().getCurrent().get(CorrelateAccessCodeTag.SOURCE_SAMPLE);
		if (beginSample != null) {
			beacon.setBeginSample(beginSample.longValue());
		}
		return beacon;
	}

}
