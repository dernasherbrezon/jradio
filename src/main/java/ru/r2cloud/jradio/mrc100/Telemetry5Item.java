package ru.r2cloud.jradio.mrc100;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Telemetry5Item {

	private short timestamp;
	private int index;
	private String telemetry;

	public Telemetry5Item() {
		// do nothing
	}

	public Telemetry5Item(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readShort();
		index = dis.readUnsignedByte();
		if (timestamp < 0 || index > 4) {
			// invalid
			dis.skipBytes(16);
			return;
		}
		byte[] telemetryBytes = new byte[19];
		dis.readFully(telemetryBytes);
		telemetry = new String(telemetryBytes, StandardCharsets.US_ASCII).trim();
	}

	public short getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(short timestamp) {
		this.timestamp = timestamp;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(String telemetry) {
		this.telemetry = telemetry;
	}

}
