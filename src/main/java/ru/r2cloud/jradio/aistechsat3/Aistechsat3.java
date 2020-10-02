package ru.r2cloud.jradio.aistechsat3;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Aistechsat3 extends BeaconSource<Aistechsat3Beacon> {

	public Aistechsat3(MessageInput input) {
		super(input);
	}

	@Override
	protected Aistechsat3Beacon parseBeacon(byte[] data) throws UncorrectableException, IOException {
		Aistechsat3Beacon result = new Aistechsat3Beacon();
		result.readExternal(data);
		return result;
	}

}
