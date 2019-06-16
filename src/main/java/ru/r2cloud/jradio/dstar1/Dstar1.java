package ru.r2cloud.jradio.dstar1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Dstar1 extends BeaconSource<Dstar1Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Dstar1.class);

	public Dstar1(MessageInput input) {
		super(input);
	}

	@Override
	protected Dstar1Beacon parseBeacon(byte[] raw) {
		Dstar1Beacon beacon = new Dstar1Beacon();
		try {
			beacon.readExternal(raw);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		} catch (UncorrectableException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode: {}", e.getMessage());
			}
			return null;
		}
		return beacon;
	}

}
