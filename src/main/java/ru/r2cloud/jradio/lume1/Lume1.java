package ru.r2cloud.jradio.lume1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Lume1 extends BeaconSource<Lume1Beacon> {
	
	private static final Logger LOG = LoggerFactory.getLogger(Lume1.class);

	public Lume1(MessageInput input) {
		super(input);
	}

	@Override
	protected Lume1Beacon parseBeacon(byte[] data) {
		try {
			Lume1Beacon beacon = new Lume1Beacon();
			beacon.readExternal(data);
			return beacon;
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		} catch (UncorrectableException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode: " + e.getMessage());
			}
			return null;
		}
	}

}
