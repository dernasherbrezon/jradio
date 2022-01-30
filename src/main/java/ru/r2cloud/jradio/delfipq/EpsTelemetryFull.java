package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class EpsTelemetryFull extends EpsTelemetry {

	private float cellXmVoltage;

	public EpsTelemetryFull() {
		// do nothing
	}

	public EpsTelemetryFull(DataInputStream dis) throws IOException {
		super(dis);
		cellXmVoltage = dis.readUnsignedShort() / 1000.0f;
	}

	public float getCellXmVoltage() {
		return cellXmVoltage;
	}

	public void setCellXmVoltage(float cellXmVoltage) {
		this.cellXmVoltage = cellXmVoltage;
	}

}
