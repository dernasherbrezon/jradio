package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

public class SohMag {

	private float magVectorBody1;
	private float magVectorBody2;
	private float magVectorBody3;

	private boolean magVectorValid;

	public SohMag() {
		// do nothing
	}

	public SohMag(DataInputStream dis) throws IOException {
		magVectorBody1 = dis.readShort() * 5e-09f;
		magVectorBody2 = dis.readShort() * 5e-09f;
		magVectorBody3 = dis.readShort() * 5e-09f;
		magVectorValid = dis.readBoolean();
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

}
