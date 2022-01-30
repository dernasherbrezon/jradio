package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class CommsTelemetry extends SubsystemTelemetry {

	private CommsTelemetryv2 telemetry;

	public CommsTelemetry() {
		// do nothing
	}

	public CommsTelemetry(DataInputStream dis) throws IOException {
		super(dis);
		if (getTlmVersion() == 2) {
			telemetry = new CommsTelemetryv2(dis);
		}
	}

	public CommsTelemetryv2 getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(CommsTelemetryv2 telemetry) {
		this.telemetry = telemetry;
	}

}
