package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

public class UHFAntennaTelemetryBeacon {

	private UHFAntenna isisA;
	private UHFAntenna isisB;

	public UHFAntennaTelemetryBeacon() {
		// do nothing
	}

	public UHFAntennaTelemetryBeacon(DataInputStream dis) throws IOException {
		isisA = new UHFAntenna(dis);
		isisB = new UHFAntenna(dis);
	}

	public UHFAntenna getIsisA() {
		return isisA;
	}

	public void setIsisA(UHFAntenna isisA) {
		this.isisA = isisA;
	}

	public UHFAntenna getIsisB() {
		return isisB;
	}

	public void setIsisB(UHFAntenna isisB) {
		this.isisB = isisB;
	}

}
