package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class CommsTelemetryFull extends CommsTelemetry {

	private float amplifierTemperature;

	public CommsTelemetryFull() {
		// do nothing
	}

	public CommsTelemetryFull(DataInputStream dis) throws IOException {
		super(dis);
		amplifierTemperature = dis.readShort() / 10.0f;
	}

	public float getAmplifierTemperature() {
		return amplifierTemperature;
	}

	public void setAmplifierTemperature(float amplifierTemperature) {
		this.amplifierTemperature = amplifierTemperature;
	}

}
