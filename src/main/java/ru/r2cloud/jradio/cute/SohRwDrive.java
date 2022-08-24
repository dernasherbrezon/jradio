package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

public class SohRwDrive {

	private float filteredSpeedRpm1;
	private float filteredSpeedRpm2;
	private float filteredSpeedRpm3;

	public SohRwDrive() {
		// do nothing
	}

	public SohRwDrive(DataInputStream dis) throws IOException {
		filteredSpeedRpm1 = dis.readShort() * 0.4f;
		filteredSpeedRpm2 = dis.readShort() * 0.4f;
		filteredSpeedRpm3 = dis.readShort() * 0.4f;
	}

	public float getFilteredSpeedRpm1() {
		return filteredSpeedRpm1;
	}

	public void setFilteredSpeedRpm1(float filteredSpeedRpm1) {
		this.filteredSpeedRpm1 = filteredSpeedRpm1;
	}

	public float getFilteredSpeedRpm2() {
		return filteredSpeedRpm2;
	}

	public void setFilteredSpeedRpm2(float filteredSpeedRpm2) {
		this.filteredSpeedRpm2 = filteredSpeedRpm2;
	}

	public float getFilteredSpeedRpm3() {
		return filteredSpeedRpm3;
	}

	public void setFilteredSpeedRpm3(float filteredSpeedRpm3) {
		this.filteredSpeedRpm3 = filteredSpeedRpm3;
	}

}
