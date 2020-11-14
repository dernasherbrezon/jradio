package ru.r2cloud.jradio.spooqy1;

import java.io.IOException;

import ru.r2cloud.jradio.Beacon;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Spooqy1Beacon extends Beacon {

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		// protocol is unknown
	}

}
