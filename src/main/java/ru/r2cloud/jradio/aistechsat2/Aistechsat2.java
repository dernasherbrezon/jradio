package ru.r2cloud.jradio.aistechsat2;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Aistechsat2 extends BeaconSource<Aistechsat2Beacon> {
	
	public Aistechsat2(MessageInput input) {
		super(input);
	}

	@Override
	protected Aistechsat2Beacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		Aistechsat2Beacon result = new Aistechsat2Beacon();
		result.readExternal(raw);
		return result;
	}

}
