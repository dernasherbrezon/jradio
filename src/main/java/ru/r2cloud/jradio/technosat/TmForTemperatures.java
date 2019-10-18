package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmForTemperatures {

	private float forTemp0; // temp FOR 0
	private float forTemp1; // temp FOR 1
	private float forTemp2; // temp FOR 2

	public TmForTemperatures(DataInputStream dis) throws IOException {
		forTemp0 = dis.readShort() * 0.1f;
		forTemp1 = dis.readShort() * 0.1f;
		forTemp2 = dis.readShort() * 0.1f;
	}

	public float getForTemp0() {
		return forTemp0;
	}

	public void setForTemp0(float forTemp0) {
		this.forTemp0 = forTemp0;
	}

	public float getForTemp1() {
		return forTemp1;
	}

	public void setForTemp1(float forTemp1) {
		this.forTemp1 = forTemp1;
	}

	public float getForTemp2() {
		return forTemp2;
	}

	public void setForTemp2(float forTemp2) {
		this.forTemp2 = forTemp2;
	}

}
