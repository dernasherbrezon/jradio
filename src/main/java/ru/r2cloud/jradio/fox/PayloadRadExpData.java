package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class PayloadRadExpData {

	private byte[] payload;

	public PayloadRadExpData() {
		// do nothing
	}

	public PayloadRadExpData(LsbBitInputStream dis) throws IOException {
		payload = new byte[58];
		for (int i = 0; i < payload.length; i++) {
			payload[i] = (byte)dis.readBitsAsInt(8);
		}
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
