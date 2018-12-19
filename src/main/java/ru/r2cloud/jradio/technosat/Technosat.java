package ru.r2cloud.jradio.technosat;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.CorrelateAccessCodeTag;
import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.tubix20.TUBiX20;

public class Technosat extends TUBiX20<TechnosatBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Technosat.class);

	public Technosat(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected TechnosatBeacon parseBeacon(byte[] raw) {
		TechnosatBeacon beacon = new TechnosatBeacon();
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
