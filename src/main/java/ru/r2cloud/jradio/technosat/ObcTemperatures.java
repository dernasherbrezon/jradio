package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class ObcTemperatures {

	private int tmpAS; // Temp Sensor Set
	private float tmpA1; // Deck A Temp 1
	private float tmpB1; // Deck B Temp 1
	private float tmpB2; // Deck B Temp 2
	private float tmpB3; // Deck B Temp 3
	private float tmpC1; // Deck C Temp 1
	private float tmpC2; // Deck C Temp 2
	private float tmpC3; // Deck C Temp 3
	private float tmpC4; // Deck C Temp 4

	public ObcTemperatures(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		tmpAS = raw >> 6;
		tmpA1 = dis.readByte() * 0.787401575f;
		tmpB1 = dis.readByte() * 0.787401575f;
		tmpB2 = dis.readByte() * 0.787401575f;
		tmpB3 = dis.readByte() * 0.787401575f;
		tmpC1 = dis.readByte() * 0.787401575f;
		tmpC2 = dis.readByte() * 0.787401575f;
		tmpC3 = dis.readByte() * 0.787401575f;
		tmpC4 = dis.readByte() * 0.787401575f;
	}

	public int getTmpAS() {
		return tmpAS;
	}

	public void setTmpAS(int tmpAS) {
		this.tmpAS = tmpAS;
	}

	public float getTmpA1() {
		return tmpA1;
	}

	public void setTmpA1(float tmpA1) {
		this.tmpA1 = tmpA1;
	}

	public float getTmpB1() {
		return tmpB1;
	}

	public void setTmpB1(float tmpB1) {
		this.tmpB1 = tmpB1;
	}

	public float getTmpB2() {
		return tmpB2;
	}

	public void setTmpB2(float tmpB2) {
		this.tmpB2 = tmpB2;
	}

	public float getTmpB3() {
		return tmpB3;
	}

	public void setTmpB3(float tmpB3) {
		this.tmpB3 = tmpB3;
	}

	public float getTmpC1() {
		return tmpC1;
	}

	public void setTmpC1(float tmpC1) {
		this.tmpC1 = tmpC1;
	}

	public float getTmpC2() {
		return tmpC2;
	}

	public void setTmpC2(float tmpC2) {
		this.tmpC2 = tmpC2;
	}

	public float getTmpC3() {
		return tmpC3;
	}

	public void setTmpC3(float tmpC3) {
		this.tmpC3 = tmpC3;
	}

	public float getTmpC4() {
		return tmpC4;
	}

	public void setTmpC4(float tmpC4) {
		this.tmpC4 = tmpC4;
	}

}
