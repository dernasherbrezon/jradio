package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class HerciPayload {

	private static final int MAX_PAYLOAD_SIZE = 868;

	private byte[] data;

	public HerciPayload() {
		// do nothing
	}

	public HerciPayload(LsbBitInputStream dis) throws IOException {
		data = new byte[MAX_PAYLOAD_SIZE];
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) dis.readBitsAsInt(8);
		}
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
