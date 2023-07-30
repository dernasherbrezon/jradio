package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TelemetryCustom {

	private long timestamp;

	public TelemetryCustom() {
		// do nothing
	}

	public TelemetryCustom(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		// that's it. I guess other telemetry is proprietary
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
