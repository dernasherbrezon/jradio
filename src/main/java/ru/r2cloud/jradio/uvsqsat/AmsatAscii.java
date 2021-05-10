package ru.r2cloud.jradio.uvsqsat;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AmsatAscii {

	private String message;

	public AmsatAscii() {
		// do nothing
	}

	public AmsatAscii(DataInputStream dis) throws IOException {
		byte[] data = new byte[dis.available()];
		dis.readFully(data);
		message = new String(data, StandardCharsets.UTF_8);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
