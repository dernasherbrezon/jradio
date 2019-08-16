package ru.r2cloud.jradio.gomx1;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Gomx1 extends BeaconSource<Gomx1Beacon> {

	public Gomx1(MessageInput input) {
		super(input);
	}

	@Override
	protected Gomx1Beacon parseBeacon(byte[] data) throws UncorrectableException, IOException {
		Gomx1Beacon beacon = new Gomx1Beacon();
		beacon.readExternal(data);
		return beacon;
	}

}
