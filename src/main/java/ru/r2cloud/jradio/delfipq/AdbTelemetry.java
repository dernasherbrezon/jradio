package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class AdbTelemetry extends SubsystemTelemetry {

	private AdbTelemetryv2 telemetry;

	public AdbTelemetry() {
		// do nothing
	}

	public AdbTelemetry(DataInputStream dis) throws IOException {
		super(dis);
		if (getTlmVersion() == 2) {
			telemetry = new AdbTelemetryv2(dis);
		}
	}

	public AdbTelemetryv2 getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(AdbTelemetryv2 telemetry) {
		this.telemetry = telemetry;
	}

}
