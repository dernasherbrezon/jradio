package ru.r2cloud.jradio.dstar1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Dstar1 extends BeaconSource<Dstar1Beacon> {

	public Dstar1(MessageInput input) {
		super(input);
	}

	@Override
	protected Dstar1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Dstar1Beacon beacon = new Dstar1Beacon();
		beacon.readExternal(raw);
		return beacon;
	}

}
