package ru.r2cloud.jradio.jy1sat;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;

public class Jy1sat extends Ao40BeaconSource<Jy1satBeacon> {
	
	private static final Logger LOG = LoggerFactory.getLogger(Jy1sat.class);

	public Jy1sat(MessageInput input) {
		super(input);
	}

	@Override
	protected Jy1satBeacon parseAo40Beacon(byte[] raw) {
		Jy1satBeacon result = new Jy1satBeacon();
		try {
			result.readExternal(raw);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
		return result;
	}

}
