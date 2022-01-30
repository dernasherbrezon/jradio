package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class ObcTelemetryFull extends ObcTelemetry {

	private float boardTemperature;

	public ObcTelemetryFull() {
		// do nothing
	}

	public ObcTelemetryFull(DataInputStream dis) throws IOException {
		super(dis);
		boardTemperature = dis.readShort() / 10.0f;
	}

	public float getBoardTemperature() {
		return boardTemperature;
	}

	public void setBoardTemperature(float boardTemperature) {
		this.boardTemperature = boardTemperature;
	}
}
