package ru.r2cloud.jradio.nusat;

import java.io.IOException;

import ru.r2cloud.jradio.Beacon;

public class NusatBeacon extends Beacon {
	
	@Override
	public void readBeacon(byte[] data) throws IOException {
		//do nothing
		// telemetry format is unknown.
	}

}
