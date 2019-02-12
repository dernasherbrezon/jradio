package ru.r2cloud.jradio.gomx1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;

public class Gomx1 extends BeaconSource<Gomx1Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Gomx1.class);

	public Gomx1(MessageInput input) {
		super(input);
	}

	@Override
	protected Gomx1Beacon parseBeacon(byte[] data) {
		try {
			Gomx1Beacon beacon = new Gomx1Beacon();
			beacon.readExternal(data);
			return beacon;
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
	}

}
