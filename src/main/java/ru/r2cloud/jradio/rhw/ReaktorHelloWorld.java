package ru.r2cloud.jradio.rhw;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.r2cloud.jradio.BeaconSource;
import ru.r2cloud.jradio.MessageInput;

public class ReaktorHelloWorld extends BeaconSource<ReaktorHelloWorldBeacon> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReaktorHelloWorld.class);

	public ReaktorHelloWorld(MessageInput input) {
		super(input);
	}

	@Override
	protected ReaktorHelloWorldBeacon parseBeacon(byte[] raw) {
		ReaktorHelloWorldBeacon result = new ReaktorHelloWorldBeacon();
		try {
			result.readExternal(raw);
		} catch (IOException e) {
			LOG.error("unable to parse beacon", e);
			return null;
		}
		return result;
	}

}
