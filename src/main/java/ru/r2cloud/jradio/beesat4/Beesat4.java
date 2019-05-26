package ru.r2cloud.jradio.beesat4;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.blocks.TaggedStreamToPdu;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.tubix20.TUBiX20;

public class Beesat4 extends TUBiX20<Beesat4Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Beesat4.class);

	public Beesat4(TaggedStreamToPdu input) {
		super(input);
	}

	@Override
	protected Beesat4Beacon parseBeacon(byte[] raw) {
		Beesat4Beacon beacon = new Beesat4Beacon();
		try {
			beacon.readExternal(raw);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		} catch (UncorrectableException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode: " + e.getMessage());
			}
			return null;
		}
		return beacon;
	}

}
