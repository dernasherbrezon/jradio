package ru.r2cloud.jradio.kunspf;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class KunsPf extends BeaconSource<KunsPfBeacon> {

	public KunsPf(MessageInput input) {
		super(input);
	}

	@Override
	protected KunsPfBeacon parseBeacon(byte[] data) throws UncorrectableException, IOException {
		KunsPfBeacon current = new KunsPfBeacon();
		current.readExternal(data);
		return current;
	}

}
