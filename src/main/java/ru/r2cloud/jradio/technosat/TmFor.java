package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmFor {

	private float FOR_X_RATE;        // angular rate X
	private float FOR_Y_RATE;        // angular rate Y
	private float FOR_Z_RATE;        // angular rate Z
	private boolean FOR_HEALTHY;     // healthy

	public TmFor(DataInputStream dis) throws IOException {
		FOR_X_RATE = dis.readInt() * 0.000001f;
		FOR_Y_RATE = dis.readInt() * 0.000001f;
		FOR_Z_RATE = dis.readInt() * 0.000001f;
		int raw = dis.readUnsignedByte();
		FOR_HEALTHY = ((raw >> 7) & 0x1) > 0;
	}

	public float getFOR_X_RATE() {
		return FOR_X_RATE;
	}

	public void setFOR_X_RATE(float fOR_X_RATE) {
		FOR_X_RATE = fOR_X_RATE;
	}

	public float getFOR_Y_RATE() {
		return FOR_Y_RATE;
	}

	public void setFOR_Y_RATE(float fOR_Y_RATE) {
		FOR_Y_RATE = fOR_Y_RATE;
	}

	public float getFOR_Z_RATE() {
		return FOR_Z_RATE;
	}

	public void setFOR_Z_RATE(float fOR_Z_RATE) {
		FOR_Z_RATE = fOR_Z_RATE;
	}

	public boolean isFOR_HEALTHY() {
		return FOR_HEALTHY;
	}

	public void setFOR_HEALTHY(boolean fOR_HEALTHY) {
		FOR_HEALTHY = fOR_HEALTHY;
	}

}
