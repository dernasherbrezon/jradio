package ru.r2cloud.jradio.ao73;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.ao40.Ao40BeaconSource;

public class Ao73 extends Ao40BeaconSource<Ao73Beacon> {

	private static final Logger LOG = LoggerFactory.getLogger(Ao73.class);

	public Ao73(MessageInput input) {
		super(input);
	}

	@Override
	protected Ao73Beacon parseAo40Beacon(byte[] raw) {
		Ao73Beacon result = new Ao73Beacon();
		try {
			result.readExternal(raw);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
		return result;
	}

}
