package ru.r2cloud.jradio.bsusat1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class RfMessage {

	private String message;

	public RfMessage() {
		// do nothing
	}

	public RfMessage(LittleEndianDataInputStream dis) throws IOException {
		byte[] data = new byte[dis.available()];
		dis.readFully(data);
		message = new String(data, StandardCharsets.US_ASCII).trim();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
