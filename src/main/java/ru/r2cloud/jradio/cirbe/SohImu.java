package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohImu {

	private float imuAvgVectorBody1;
	private float imuAvgVectorBody2;
	private float imuAvgVectorBody3;
	private int imuInvalidCount;
	private boolean imuValidPackets;
	private boolean imuTestMode;
	private boolean imuVectorEnabled;
	private boolean imuVectorValid;
	private boolean imuPowerState;

	public SohImu() {
		// do nothing
	}

	public SohImu(DataInputStream dis) throws IOException {
		imuAvgVectorBody1 = dis.readShort() / 100000.0030000001f;
		imuAvgVectorBody2 = dis.readShort() / 100000.0030000001f;
		imuAvgVectorBody3 = dis.readShort() / 100000.0030000001f;
		imuInvalidCount = dis.readUnsignedShort();
		int raw = dis.readUnsignedByte();
		imuValidPackets = ((raw >> 4) & 0x1) > 0;
		imuTestMode = ((raw >> 3) & 0x1) > 0;
		imuVectorEnabled = ((raw >> 2) & 0x1) > 0;
		imuVectorValid = ((raw >> 1) & 0x1) > 0;
		imuPowerState = (raw & 0x1) > 0;
	}

	public float getImuAvgVectorBody1() {
		return imuAvgVectorBody1;
	}

	public void setImuAvgVectorBody1(float imuAvgVectorBody1) {
		this.imuAvgVectorBody1 = imuAvgVectorBody1;
	}

	public float getImuAvgVectorBody2() {
		return imuAvgVectorBody2;
	}

	public void setImuAvgVectorBody2(float imuAvgVectorBody2) {
		this.imuAvgVectorBody2 = imuAvgVectorBody2;
	}

	public float getImuAvgVectorBody3() {
		return imuAvgVectorBody3;
	}

	public void setImuAvgVectorBody3(float imuAvgVectorBody3) {
		this.imuAvgVectorBody3 = imuAvgVectorBody3;
	}

	public int getImuInvalidCount() {
		return imuInvalidCount;
	}

	public void setImuInvalidCount(int imuInvalidCount) {
		this.imuInvalidCount = imuInvalidCount;
	}

	public boolean isImuValidPackets() {
		return imuValidPackets;
	}

	public void setImuValidPackets(boolean imuValidPackets) {
		this.imuValidPackets = imuValidPackets;
	}

	public boolean isImuTestMode() {
		return imuTestMode;
	}

	public void setImuTestMode(boolean imuTestMode) {
		this.imuTestMode = imuTestMode;
	}

	public boolean isImuVectorEnabled() {
		return imuVectorEnabled;
	}

	public void setImuVectorEnabled(boolean imuVectorEnabled) {
		this.imuVectorEnabled = imuVectorEnabled;
	}

	public boolean isImuVectorValid() {
		return imuVectorValid;
	}

	public void setImuVectorValid(boolean imuVectorValid) {
		this.imuVectorValid = imuVectorValid;
	}

	public boolean isImuPowerState() {
		return imuPowerState;
	}

	public void setImuPowerState(boolean imuPowerState) {
		this.imuPowerState = imuPowerState;
	}

}
