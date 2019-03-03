package ru.r2cloud.jradio;

import java.io.IOException;

public class EmptyBeacon extends Beacon {

	@Override
	public void readBeacon(byte[] data) throws IOException {
		//do nothing
	}

}
