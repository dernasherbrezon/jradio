package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohMag {

	private float magVectorBody1;
	private float magVectorBody2;
	private float magVectorBody3;
	private int magInvalidCount;
	private int magSensorUsed;
	private boolean magTestMode;
	private boolean magVectorEnabled;
	private boolean magVectorValid;
	private boolean magPowerState;

	public SohMag() {
		// do nothing
	}

	public SohMag(DataInputStream dis) throws IOException {
		magVectorBody1 = dis.readShort() * 5e-09f;
		magVectorBody2 = dis.readShort() * 5e-09f;
		magVectorBody3 = dis.readShort() * 5e-09f;
		magInvalidCount = dis.readUnsignedShort();
		int raw = dis.readUnsignedByte();
		magSensorUsed = ((raw >> 4) & 0x3);
		magTestMode = ((raw >> 3) & 0x1) > 0;
		magVectorEnabled = ((raw >> 2) & 0x1) > 0;
		magVectorValid = ((raw >> 1) & 0x1) > 0;
		magPowerState = (raw & 0x1) > 0;
	}

	public float getMagVectorBody1() {
		return magVectorBody1;
	}

	public void setMagVectorBody1(float magVectorBody1) {
		this.magVectorBody1 = magVectorBody1;
	}

	public float getMagVectorBody2() {
		return magVectorBody2;
	}

	public void setMagVectorBody2(float magVectorBody2) {
		this.magVectorBody2 = magVectorBody2;
	}

	public float getMagVectorBody3() {
		return magVectorBody3;
	}

	public void setMagVectorBody3(float magVectorBody3) {
		this.magVectorBody3 = magVectorBody3;
	}

	public boolean isMagVectorValid() {
		return magVectorValid;
	}

	public void setMagVectorValid(boolean magVectorValid) {
		this.magVectorValid = magVectorValid;
	}

	public int getMagInvalidCount() {
		return magInvalidCount;
	}

	public void setMagInvalidCount(int magInvalidCount) {
		this.magInvalidCount = magInvalidCount;
	}

	public int getMagSensorUsed() {
		return magSensorUsed;
	}

	public void setMagSensorUsed(int magSensorUsed) {
		this.magSensorUsed = magSensorUsed;
	}

	public boolean isMagTestMode() {
		return magTestMode;
	}

	public void setMagTestMode(boolean magTestMode) {
		this.magTestMode = magTestMode;
	}

	public boolean isMagVectorEnabled() {
		return magVectorEnabled;
	}

	public void setMagVectorEnabled(boolean magVectorEnabled) {
		this.magVectorEnabled = magVectorEnabled;
	}

	public boolean isMagPowerState() {
		return magPowerState;
	}

	public void setMagPowerState(boolean magPowerState) {
		this.magPowerState = magPowerState;
	}

}
