package ru.r2cloud.jradio.at03;

public class Status3 {

	private boolean is3V3BurstModeOn;
	private boolean is5VBurstModeOn;
	private boolean Bat1ConnectedToPV2;
	private boolean Bat2ConnectedToPV1;
	private boolean TemperatureWarningFlag;
	private boolean CC1ConnectionOkayFlag;
	private boolean CC2ConnectionOkayFlag;
	private boolean RBF;

	public Status3(int b) {
		is3V3BurstModeOn = ((b >> 7) & 1) > 0;
		is5VBurstModeOn = ((b >> 6) & 1) > 0;
		Bat1ConnectedToPV2 = ((b >> 5) & 1) > 0;
		Bat2ConnectedToPV1 = ((b >> 4) & 1) > 0;
		TemperatureWarningFlag = ((b >> 3) & 1) > 0;
		CC1ConnectionOkayFlag = ((b >> 2) & 1) > 0;
		CC2ConnectionOkayFlag = ((b >> 1) & 1) > 0;
		RBF = (b & 1) > 0;
	}

	public boolean isIs3V3BurstModeOn() {
		return is3V3BurstModeOn;
	}

	public void setIs3V3BurstModeOn(boolean is3v3BurstModeOn) {
		is3V3BurstModeOn = is3v3BurstModeOn;
	}

	public boolean isIs5VBurstModeOn() {
		return is5VBurstModeOn;
	}

	public void setIs5VBurstModeOn(boolean is5vBurstModeOn) {
		is5VBurstModeOn = is5vBurstModeOn;
	}

	public boolean isBat1ConnectedToPV2() {
		return Bat1ConnectedToPV2;
	}

	public void setBat1ConnectedToPV2(boolean bat1ConnectedToPV2) {
		Bat1ConnectedToPV2 = bat1ConnectedToPV2;
	}

	public boolean isBat2ConnectedToPV1() {
		return Bat2ConnectedToPV1;
	}

	public void setBat2ConnectedToPV1(boolean bat2ConnectedToPV1) {
		Bat2ConnectedToPV1 = bat2ConnectedToPV1;
	}

	public boolean isTemperatureWarningFlag() {
		return TemperatureWarningFlag;
	}

	public void setTemperatureWarningFlag(boolean temperatureWarningFlag) {
		TemperatureWarningFlag = temperatureWarningFlag;
	}

	public boolean isCC1ConnectionOkayFlag() {
		return CC1ConnectionOkayFlag;
	}

	public void setCC1ConnectionOkayFlag(boolean cC1ConnectionOkayFlag) {
		CC1ConnectionOkayFlag = cC1ConnectionOkayFlag;
	}

	public boolean isCC2ConnectionOkayFlag() {
		return CC2ConnectionOkayFlag;
	}

	public void setCC2ConnectionOkayFlag(boolean cC2ConnectionOkayFlag) {
		CC2ConnectionOkayFlag = cC2ConnectionOkayFlag;
	}

	public boolean isRBF() {
		return RBF;
	}

	public void setRBF(boolean rBF) {
		RBF = rBF;
	}

}
