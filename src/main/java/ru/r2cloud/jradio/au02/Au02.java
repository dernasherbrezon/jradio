package ru.r2cloud.jradio.au02;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Au02 extends BeaconSource<Au02Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Au02.class);

	public Au02(MessageInput input) {
		super(input);
	}

	@Override
	protected Au02Beacon parseBeacon(byte[] data) {
		try {
			Au02Beacon beacon = new Au02Beacon();
			beacon.readExternal(data);
			return beacon;
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		} catch (UncorrectableException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("unable to decode: {}", e.getMessage());
			}
			return null;
		}
	}

}
