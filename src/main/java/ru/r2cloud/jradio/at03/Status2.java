package ru.r2cloud.jradio.at03;

public class Status2 {

	private boolean PowerLowWarning;
	private boolean Bat1ConnectedToPV1;
	private boolean Bat2ConnectedToPV2;
	private boolean is3V3On;
	private boolean is5VOn;
	private Status2Mode mode;

	public Status2(int b) {
		PowerLowWarning = ((b >> 7) & 1) > 0;
		Bat1ConnectedToPV1 = ((b >> 6) & 1) > 0;
		Bat2ConnectedToPV2 = ((b >> 5) & 1) > 0;
		is3V3On = ((b >> 4) & 1) > 0;
		is5VOn = ((b >> 3) & 1) > 0;
		mode = Status2Mode.valueOfCode(b & 0b111);
	}

	public boolean isPowerLowWarning() {
		return PowerLowWarning;
	}

	public void setPowerLowWarning(boolean powerLowWarning) {
		PowerLowWarning = powerLowWarning;
	}

	public boolean isBat1ConnectedToPV1() {
		return Bat1ConnectedToPV1;
	}

	public void setBat1ConnectedToPV1(boolean bat1ConnectedToPV1) {
		Bat1ConnectedToPV1 = bat1ConnectedToPV1;
	}

	public boolean isBat2ConnectedToPV2() {
		return Bat2ConnectedToPV2;
	}

	public void setBat2ConnectedToPV2(boolean bat2ConnectedToPV2) {
		Bat2ConnectedToPV2 = bat2ConnectedToPV2;
	}

	public boolean isIs3V3On() {
		return is3V3On;
	}

	public void setIs3V3On(boolean is3v3On) {
		is3V3On = is3v3On;
	}

	public boolean isIs5VOn() {
		return is5VOn;
	}

	public void setIs5VOn(boolean is5vOn) {
		is5VOn = is5vOn;
	}

	public Status2Mode getMode() {
		return mode;
	}

	public void setMode(Status2Mode mode) {
		this.mode = mode;
	}

}
