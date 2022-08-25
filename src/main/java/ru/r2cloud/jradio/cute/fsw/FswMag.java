package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class FswMag {

	private float magVectorBody1;
	private float magVectorBody2;
	private float magVectorBody3;
	private int[] rawMagnetometerData;
	private boolean magVectorValid;
	private int magSensorUsed;
	private boolean magTestMode;

	public FswMag() {
		// do nothing
	}

	public FswMag(DataInputStream dis) throws IOException {
		magVectorBody1 = dis.readShort() * 5e-09f;
		magVectorBody2 = dis.readShort() * 5e-09f;
		magVectorBody3 = dis.readShort() * 5e-09f;
		rawMagnetometerData = StreamUtils.readUnsignedShortArray(dis, 12);
		magVectorValid = dis.readBoolean();
		magSensorUsed = dis.readUnsignedByte();
		magTestMode = dis.readBoolean();
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

	public int[] getRawMagnetometerData() {
		return rawMagnetometerData;
	}

	public void setRawMagnetometerData(int[] rawMagnetometerData) {
		this.rawMagnetometerData = rawMagnetometerData;
	}

	public boolean isMagVectorValid() {
		return magVectorValid;
	}

	public void setMagVectorValid(boolean magVectorValid) {
		this.magVectorValid = magVectorValid;
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

}
