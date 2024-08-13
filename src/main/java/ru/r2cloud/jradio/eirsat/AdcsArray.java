package ru.r2cloud.jradio.eirsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class AdcsArray {

	private float temperature1Plusx;
	private float temperature4Minusx;
	private float temperature3Plusy;
	private float temperature2Minusy;

	public AdcsArray() {
		// do nothing
	}

	public AdcsArray(BitInputStream dis) throws IOException {
		temperature1Plusx = dis.readUnsignedShort() * 0.007629f - 272.15f;
		temperature4Minusx = dis.readUnsignedShort() * 0.007629f - 272.15f;
		temperature3Plusy = dis.readUnsignedShort() * 0.007629f - 272.15f;
		temperature2Minusy = dis.readUnsignedShort() * 0.007629f - 272.15f;
	}

	public float getTemperature1Plusx() {
		return temperature1Plusx;
	}

	public void setTemperature1Plusx(float temperature1Plusx) {
		this.temperature1Plusx = temperature1Plusx;
	}

	public float getTemperature4Minusx() {
		return temperature4Minusx;
	}

	public void setTemperature4Minusx(float temperature4Minusx) {
		this.temperature4Minusx = temperature4Minusx;
	}

	public float getTemperature3Plusy() {
		return temperature3Plusy;
	}

	public void setTemperature3Plusy(float temperature3Plusy) {
		this.temperature3Plusy = temperature3Plusy;
	}

	public float getTemperature2Minusy() {
		return temperature2Minusy;
	}

	public void setTemperature2Minusy(float temperature2Minusy) {
		this.temperature2Minusy = temperature2Minusy;
	}

}
