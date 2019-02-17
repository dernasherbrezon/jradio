package ru.r2cloud.jradio.at03;

public class CCStatus {

	private CCMode mode;
	private boolean mcTimeoutFlag;
	private boolean rbf;
	private boolean enI2c;
	private boolean bat1ConnectedToPV1;
	private boolean bat2ConnectedToPV2;
	private boolean b3V3BackupOn;

	public CCStatus(int b) {
		mode = CCMode.valueOfCode((b >> 6) & 0b11);
		mcTimeoutFlag = ((b >> 5) & 1) > 0;
		rbf = ((b >> 4) & 1) > 0;
		enI2c = ((b >> 3) & 1) > 0;
		bat1ConnectedToPV1 = ((b >> 2) & 1) > 0;
		bat2ConnectedToPV2 = ((b >> 1) & 1) > 0;
		b3V3BackupOn = (b & 1) > 0;
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

	public boolean isRbf() {
		return rbf;
	}

	public void setRbf(boolean rbf) {
		this.rbf = rbf;
	}

	public boolean isEnI2c() {
		return enI2c;
	}

	public void setEnI2c(boolean enI2c) {
		this.enI2c = enI2c;
	}

	public boolean isBat1ConnectedToPV1() {
		return bat1ConnectedToPV1;
	}

	public void setBat1ConnectedToPV1(boolean bat1ConnectedToPV1) {
		this.bat1ConnectedToPV1 = bat1ConnectedToPV1;
	}

	public boolean isBat2ConnectedToPV2() {
		return bat2ConnectedToPV2;
	}

	public void setBat2ConnectedToPV2(boolean bat2ConnectedToPV2) {
		this.bat2ConnectedToPV2 = bat2ConnectedToPV2;
	}

	public boolean isB3V3BackupOn() {
		return b3V3BackupOn;
	}

	public void setB3V3BackupOn(boolean b3v3BackupOn) {
		b3V3BackupOn = b3v3BackupOn;
	}

}
