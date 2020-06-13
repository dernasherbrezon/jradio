package ru.r2cloud.jradio.polyitan1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Beacon2 {

	private int deviceStatus;
	private int rtcSeconds;
	private float temperature2;
	private float temperature1;
	private int u3v3Digi;
	private int u3v3Rf;
	private int u3v3RfAmp;

	public Beacon2() {
		// do nothing
	}

	public Beacon2(LittleEndianDataInputStream dis) throws IOException {
		deviceStatus = dis.readUnsignedShort();
		rtcSeconds = dis.readInt();
		temperature2 = dis.readShort() * 0.1f;
		temperature1 = dis.readShort() * 0.1f;
		u3v3Digi = dis.readShort();
		u3v3Rf = dis.readShort();
		u3v3RfAmp = dis.readShort();
	}

	public int getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public int getRtcSeconds() {
		return rtcSeconds;
	}

	public void setRtcSeconds(int rtcSeconds) {
		this.rtcSeconds = rtcSeconds;
	}

	public float getTemperature2() {
		return temperature2;
	}

	public void setTemperature2(float temperature2) {
		this.temperature2 = temperature2;
	}

	public float getTemperature1() {
		return temperature1;
	}

	public void setTemperature1(float temperature1) {
		this.temperature1 = temperature1;
	}

	public int getU3v3Digi() {
		return u3v3Digi;
	}

	public void setU3v3Digi(int u3v3Digi) {
		this.u3v3Digi = u3v3Digi;
	}

	public int getU3v3Rf() {
		return u3v3Rf;
	}

	public void setU3v3Rf(int u3v3Rf) {
		this.u3v3Rf = u3v3Rf;
	}

	public int getU3v3RfAmp() {
		return u3v3RfAmp;
	}

	public void setU3v3RfAmp(int u3v3RfAmp) {
		this.u3v3RfAmp = u3v3RfAmp;
	}

}
