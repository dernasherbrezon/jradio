package ru.r2cloud.jradio.at03;

public class CCStatus {

	private CCMode mode;
	private boolean mcTimeoutFlag;
	private boolean RBF;
	private boolean EN_I2C;
	private boolean Bat1ConnectedToPV1;
	private boolean Bat2ConnectedToPV2;
	private boolean b3V3BackupOn;

	public CCStatus(int b) {
		mode = CCMode.valueOfCode((b >> 6) & 0b11);
		mcTimeoutFlag = ((b >> 5) & 1) > 0;
		RBF = ((b >> 4) & 1) > 0;
		EN_I2C = ((b >> 3) & 1) > 0;
		Bat1ConnectedToPV1 = ((b >> 2) & 1) > 0;
		Bat2ConnectedToPV2 = ((b >> 1) & 1) > 0;
		b3V3BackupOn = ((b >> 0) & 1) > 0;
	}

	public CCMode getMode() {
		return mode;
	}

	public void setMode(CCMode mode) {
		this.mode = mode;
	}

	public boolean isMcTimeoutFlag() {
		return mcTimeoutFlag;
	}

	public void setMcTimeoutFlag(boolean mcTimeoutFlag) {
		this.mcTimeoutFlag = mcTimeoutFlag;
	}

	public boolean isRBF() {
		return RBF;
	}

	public void setRBF(boolean rBF) {
		RBF = rBF;
	}

	public boolean isEN_I2C() {
		return EN_I2C;
	}

	public void setEN_I2C(boolean eN_I2C) {
		EN_I2C = eN_I2C;
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

	public boolean isB3V3BackupOn() {
		return b3V3BackupOn;
	}

	public void setB3V3BackupOn(boolean b3v3BackupOn) {
		b3V3BackupOn = b3v3BackupOn;
	}

}
