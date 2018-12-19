package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class ObcTemperatures {

	private int TMPAS;        // Temp Sensor Set
	private float TMPA1;      // Deck A Temp 1
	private float TMPB1;      // Deck B Temp 1
	private float TMPB2;      // Deck B Temp 2
	private float TMPB3;      // Deck B Temp 3
	private float TMPC1;      // Deck C Temp 1
	private float TMPC2;      // Deck C Temp 2
	private float TMPC3;      // Deck C Temp 3
	private float TMPC4;      // Deck C Temp 4

	public ObcTemperatures(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		TMPAS = raw >> 6;
		TMPA1 = dis.readByte() * 0.787401575f;
		TMPB1 = dis.readByte() * 0.787401575f;
		TMPB2 = dis.readByte() * 0.787401575f;
		TMPB3 = dis.readByte() * 0.787401575f;
		TMPC1 = dis.readByte() * 0.787401575f;
		TMPC2 = dis.readByte() * 0.787401575f;
		TMPC3 = dis.readByte() * 0.787401575f;
		TMPC4 = dis.readByte() * 0.787401575f;
	}

	public int getTMPAS() {
		return TMPAS;
	}

	public void setTMPAS(int tMPAS) {
		TMPAS = tMPAS;
	}

	public float getTMPA1() {
		return TMPA1;
	}

	public void setTMPA1(float tMPA1) {
		TMPA1 = tMPA1;
	}

	public float getTMPB1() {
		return TMPB1;
	}

	public void setTMPB1(float tMPB1) {
		TMPB1 = tMPB1;
	}

	public float getTMPB2() {
		return TMPB2;
	}

	public void setTMPB2(float tMPB2) {
		TMPB2 = tMPB2;
	}

	public float getTMPB3() {
		return TMPB3;
	}

	public void setTMPB3(float tMPB3) {
		TMPB3 = tMPB3;
	}

	public float getTMPC1() {
		return TMPC1;
	}

	public void setTMPC1(float tMPC1) {
		TMPC1 = tMPC1;
	}

	public float getTMPC2() {
		return TMPC2;
	}

	public void setTMPC2(float tMPC2) {
		TMPC2 = tMPC2;
	}

	public float getTMPC3() {
		return TMPC3;
	}

	public void setTMPC3(float tMPC3) {
		TMPC3 = tMPC3;
	}

	public float getTMPC4() {
		return TMPC4;
	}

	public void setTMPC4(float tMPC4) {
		TMPC4 = tMPC4;
	}

}
