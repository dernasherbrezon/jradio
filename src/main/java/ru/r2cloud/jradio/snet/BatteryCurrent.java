package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class BatteryCurrent {

	private float chargeCurrent;
	private float dischargeCurrent;

	public BatteryCurrent(BitInputStream bis) throws IOException {
		chargeCurrent = bis.readShort() / 12.0f;
		dischargeCurrent = bis.readShort() / 12.0f;
	}

	public float getChargeCurrent() {
		return chargeCurrent;
	}

	public void setChargeCurrent(float chargeCurrent) {
		this.chargeCurrent = chargeCurrent;
	}

	public float getDischargeCurrent() {
		return dischargeCurrent;
	}

	public void setDischargeCurrent(float dischargeCurrent) {
		this.dischargeCurrent = dischargeCurrent;
	}

}
