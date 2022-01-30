package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class AdbTelemetryFull extends AdbTelemetry {

	private float temperature;

	public AdbTelemetryFull() {
		// do nothing
	}

	public AdbTelemetryFull(DataInputStream dis) throws IOException {
		super(dis);
		temperature = dis.readShort() / 10.0f;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
}
