package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ProgMap {

	private long timestamp;
	private byte[] payload;

	public ProgMap() {
		// do nothing
	}

	public ProgMap(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		payload = new byte[112];
		dis.readFully(payload);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
