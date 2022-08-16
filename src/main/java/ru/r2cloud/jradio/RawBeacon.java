package ru.r2cloud.jradio;

import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class RawBeacon extends Beacon {

	private byte[] payload;

	@Override
	public void readBeacon(byte[] data) throws IOException, UncorrectableException {
		payload = new byte[data.length];
		System.arraycopy(data, 0, payload, 0, data.length);
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
