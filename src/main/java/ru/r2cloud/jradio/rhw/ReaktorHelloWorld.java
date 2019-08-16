package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class ReaktorHelloWorld extends BeaconSource<ReaktorHelloWorldBeacon> {

	public ReaktorHelloWorld(MessageInput input) {
		super(input);
	}

	@Override
	protected ReaktorHelloWorldBeacon parseBeacon(byte[] raw) throws UncorrectableException, IOException {
		ReaktorHelloWorldBeacon result = new ReaktorHelloWorldBeacon();
		result.readExternal(raw);
		return result;
	}

}
