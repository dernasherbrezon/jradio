package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class EpsTelemetry extends SubsystemTelemetry {

	private EpsTelemetryv2 telemetry;

	public EpsTelemetry() {
		// do nothing
	}

	public EpsTelemetry(DataInputStream dis) throws IOException {
		super(dis);
		if (getTlmVersion() == 2) {
			telemetry = new EpsTelemetryv2(dis);
		}
	}

	public EpsTelemetryv2 getTelemetry() {
		return telemetry;
	}

	public void setTelemetry(EpsTelemetryv2 telemetry) {
		this.telemetry = telemetry;
	}

}
