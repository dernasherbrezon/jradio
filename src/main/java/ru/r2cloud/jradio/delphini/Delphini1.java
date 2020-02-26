package ru.r2cloud.jradio.delphini;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Delphini1 extends BeaconSource<Delphini1Beacon> {

	public Delphini1(MessageInput input) {
		super(input);
	}

	@Override
	protected Delphini1Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Delphini1Beacon result = new Delphini1Beacon();
		result.readExternal(raw);
		return result;
	}

}
