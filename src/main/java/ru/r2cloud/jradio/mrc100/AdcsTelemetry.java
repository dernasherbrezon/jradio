package ru.r2cloud.jradio.mrc100;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AdcsTelemetry {

	private long timestamp;
	private String telemetry;

	public AdcsTelemetry() {
		// do nothing
	}

	public AdcsTelemetry(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		byte[] telemetryBytes = new byte[112];
		dis.readFully(telemetryBytes);
		telemetry = new String(telemetryBytes, StandardCharsets.US_ASCII).trim();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(String telemetry) {
		this.telemetry = telemetry;
	}

}
