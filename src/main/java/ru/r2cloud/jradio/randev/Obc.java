package ru.r2cloud.jradio.randev;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Obc {

	private float temp1Celcius;
	private float temp2Celcius;
	private byte[] rawGps;

	public Obc() {
		// do nothing
	}

	public Obc(LittleEndianDataInputStream dis) throws IOException {
		temp1Celcius = dis.readShort() * 0.1f;
		temp2Celcius = dis.readShort() * 0.1f;
		rawGps = new byte[6];
		dis.readFully(rawGps);
	}

	public float getTemp1Celcius() {
		return temp1Celcius;
	}

	public void setTemp1Celcius(float temp1Celcius) {
		this.temp1Celcius = temp1Celcius;
	}

	public float getTemp2Celcius() {
		return temp2Celcius;
	}

	public void setTemp2Celcius(float temp2Celcius) {
		this.temp2Celcius = temp2Celcius;
	}

	public byte[] getRawGps() {
		return rawGps;
	}

	public void setRawGps(byte[] rawGps) {
		this.rawGps = rawGps;
	}

}
