package ru.r2cloud.jradio;

import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class LoraBeacon extends Beacon {

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		// do nothing
	}

}

