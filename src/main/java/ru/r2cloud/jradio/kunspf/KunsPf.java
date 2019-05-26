package ru.r2cloud.jradio.kunspf;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class KunsPf extends BeaconSource<KunsPfBeacon> {

	private static final Logger LOG = LoggerFactory.getLogger(KunsPf.class);

	public KunsPf(MessageInput input) {
		super(input);
	}

	@Override
	protected KunsPfBeacon parseBeacon(byte[] data) {
		try {
			KunsPfBeacon current = new KunsPfBeacon();
			current.readExternal(data);
			return current;
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
