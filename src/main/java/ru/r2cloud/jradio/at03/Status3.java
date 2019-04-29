package ru.r2cloud.jradio.at03;

public class Status3 {

	private boolean is3V3BurstModeOn;
	private boolean is5VBurstModeOn;
	private boolean bat1ConnectedToPV2;
	private boolean bat2ConnectedToPV1;
	private boolean temperatureWarningFlag;
	private boolean cc1ConnectionOkayFlag;
	private boolean cc2ConnectionOkayFlag;
	private boolean rbf;

	public Status3(int b) {
		is3V3BurstModeOn = ((b >> 7) & 1) > 0;
		is5VBurstModeOn = ((b >> 6) & 1) > 0;
		bat1ConnectedToPV2 = ((b >> 5) & 1) > 0;
		bat2ConnectedToPV1 = ((b >> 4) & 1) > 0;
		temperatureWarningFlag = ((b >> 3) & 1) > 0;
		cc1ConnectionOkayFlag = ((b >> 2) & 1) > 0;
		cc2ConnectionOkayFlag = ((b >> 1) & 1) > 0;
		rbf = (b & 1) > 0;
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
		return bat1ConnectedToPV2;
	}

	public void setBat1ConnectedToPV2(boolean bat1ConnectedToPV2) {
		this.bat1ConnectedToPV2 = bat1ConnectedToPV2;
	}

	public boolean isBat2ConnectedToPV1() {
		return bat2ConnectedToPV1;
	}

	public void setBat2ConnectedToPV1(boolean bat2ConnectedToPV1) {
		this.bat2ConnectedToPV1 = bat2ConnectedToPV1;
	}

	public boolean isTemperatureWarningFlag() {
		return temperatureWarningFlag;
	}

	public void setTemperatureWarningFlag(boolean temperatureWarningFlag) {
		this.temperatureWarningFlag = temperatureWarningFlag;
	}

	public boolean isCc1ConnectionOkayFlag() {
		return cc1ConnectionOkayFlag;
	}

	public void setCc1ConnectionOkayFlag(boolean cc1ConnectionOkayFlag) {
		this.cc1ConnectionOkayFlag = cc1ConnectionOkayFlag;
	}

	public boolean isCc2ConnectionOkayFlag() {
		return cc2ConnectionOkayFlag;
	}

	public void setCc2ConnectionOkayFlag(boolean cc2ConnectionOkayFlag) {
		this.cc2ConnectionOkayFlag = cc2ConnectionOkayFlag;
	}

	public boolean isRbf() {
		return rbf;
	}

	public void setRbf(boolean rbf) {
		this.rbf = rbf;
	}

}
