package ru.r2cloud.jradio.au02;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Au02 extends BeaconSource<Au02Beacon> {

	public Au02(MessageInput input) {
		super(input);
	}

	@Override
	protected Au02Beacon parseBeacon(byte[] data) throws UncorrectableException, IOException {
		Au02Beacon beacon = new Au02Beacon();
		beacon.readExternal(data);
		return beacon;
	}

}
