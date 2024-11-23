package ru.r2cloud.jradio.dora;

import java.io.DataInputStream;
import java.io.IOException;

public class SecondPacket {

	private Payload2 payload2;

	public SecondPacket() {
		// do nothing
	}

	public SecondPacket(DataInputStream dis) throws IOException {
		payload2 = new Payload2(dis);
	}

	public Payload2 getPayload2() {
		return payload2;
	}

	public void setPayload2(Payload2 payload2) {
		this.payload2 = payload2;
	}

}
