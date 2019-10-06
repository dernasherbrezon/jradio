package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmFor {

	private float forXRate; // angular rate X
	private float forYRate; // angular rate Y
	private float forZRate; // angular rate Z
	private boolean forHealthy; // healthy

	public TmFor(DataInputStream dis) throws IOException {
		forXRate = dis.readInt() * 0.000001f;
		forYRate = dis.readInt() * 0.000001f;
		forZRate = dis.readInt() * 0.000001f;
		int raw = dis.readUnsignedByte();
		forHealthy = ((raw >> 7) & 0x1) > 0;
	}

	public float getForXRate() {
		return forXRate;
	}

	public void setForXRate(float forXRate) {
		this.forXRate = forXRate;
	}

	public float getForYRate() {
		return forYRate;
	}

	public void setForYRate(float forYRate) {
		this.forYRate = forYRate;
	}

	public float getForZRate() {
		return forZRate;
	}

	public void setForZRate(float forZRate) {
		this.forZRate = forZRate;
	}

	public boolean isForHealthy() {
		return forHealthy;
	}

	public void setForHealthy(boolean forHealthy) {
		this.forHealthy = forHealthy;
	}

}
