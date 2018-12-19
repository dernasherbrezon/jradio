package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmForTemperatures {

	private float FOR_TEMP0;
	private float FOR_TEMP1;
	private float FOR_TEMP2;

	public TmForTemperatures(DataInputStream dis) throws IOException {
		FOR_TEMP0 = dis.readShort() * 0.1f;
		FOR_TEMP1 = dis.readShort() * 0.1f;
		FOR_TEMP2 = dis.readShort() * 0.1f;
	}

	public float getFOR_TEMP0() {
		return FOR_TEMP0;
	}

	public void setFOR_TEMP0(float fOR_TEMP0) {
		FOR_TEMP0 = fOR_TEMP0;
	}

	public float getFOR_TEMP1() {
		return FOR_TEMP1;
	}

	public void setFOR_TEMP1(float fOR_TEMP1) {
		FOR_TEMP1 = fOR_TEMP1;
	}

	public float getFOR_TEMP2() {
		return FOR_TEMP2;
	}

	public void setFOR_TEMP2(float fOR_TEMP2) {
		FOR_TEMP2 = fOR_TEMP2;
	}

}
