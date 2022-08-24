package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

public class SohRefs {

	private float positionWrtEci1;
	private float positionWrtEci2;
	private float positionWrtEci3;
	private float velocityWrtEci1;
	private float velocityWrtEci2;
	private float velocityWrtEci3;
	private boolean refsValid;

	public SohRefs() {
		// do nothing
	}

	public SohRefs(DataInputStream dis) throws IOException {
		positionWrtEci1 = dis.readInt() * 1.9999999e-05f;
		positionWrtEci2 = dis.readInt() * 1.9999999e-05f;
		positionWrtEci3 = dis.readInt() * 1.9999999e-05f;
		velocityWrtEci1 = dis.readInt() * 5.0e-09f;
		velocityWrtEci2 = dis.readInt() * 5.0e-09f;
		velocityWrtEci3 = dis.readInt() * 5.0e-09f;
		refsValid = dis.readBoolean();
	}

	public float getPositionWrtEci1() {
		return positionWrtEci1;
	}

	public void setPositionWrtEci1(float positionWrtEci1) {
		this.positionWrtEci1 = positionWrtEci1;
	}

	public float getPositionWrtEci2() {
		return positionWrtEci2;
	}

	public void setPositionWrtEci2(float positionWrtEci2) {
		this.positionWrtEci2 = positionWrtEci2;
	}

	public float getPositionWrtEci3() {
		return positionWrtEci3;
	}

	public void setPositionWrtEci3(float positionWrtEci3) {
		this.positionWrtEci3 = positionWrtEci3;
	}

	public float getVelocityWrtEci1() {
		return velocityWrtEci1;
	}

	public void setVelocityWrtEci1(float velocityWrtEci1) {
		this.velocityWrtEci1 = velocityWrtEci1;
	}

	public float getVelocityWrtEci2() {
		return velocityWrtEci2;
	}

	public void setVelocityWrtEci2(float velocityWrtEci2) {
		this.velocityWrtEci2 = velocityWrtEci2;
	}

	public float getVelocityWrtEci3() {
		return velocityWrtEci3;
	}

	public void setVelocityWrtEci3(float velocityWrtEci3) {
		this.velocityWrtEci3 = velocityWrtEci3;
	}

	public boolean isRefsValid() {
		return refsValid;
	}

	public void setRefsValid(boolean refsValid) {
		this.refsValid = refsValid;
	}

}
