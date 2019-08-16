package ru.r2cloud.jradio.lume1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Lume1 extends BeaconSource<Lume1Beacon> {

	public Lume1(MessageInput input) {
		super(input);
	}

	@Override
	protected Lume1Beacon parseBeacon(byte[] data) throws UncorrectableException, IOException {
		Lume1Beacon beacon = new Lume1Beacon();
		beacon.readExternal(data);
		return beacon;
	}

}
