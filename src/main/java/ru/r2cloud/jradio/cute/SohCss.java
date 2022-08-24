package ru.r2cloud.jradio.cute;

import java.io.DataInputStream;
import java.io.IOException;

public class SohCss {

	private float sunVectorBody1;
	private float sunVectorBody2;
	private float sunVectorBody3;
	private SunVectorStatus sunVectorStatus;
	private int sunSensorUsed;

	public SohCss() {
		// do nothing
	}

	public SohCss(DataInputStream dis) throws IOException {
		sunVectorBody1 = dis.readShort() * 0.0001f;
		sunVectorBody2 = dis.readShort() * 0.0001f;
		sunVectorBody3 = dis.readShort() * 0.0001f;

		sunVectorStatus = SunVectorStatus.valueOfCode(dis.readUnsignedByte());
		sunSensorUsed = dis.readUnsignedByte();
	}

	public float getSunVectorBody1() {
		return sunVectorBody1;
	}

	public void setSunVectorBody1(float sunVectorBody1) {
		this.sunVectorBody1 = sunVectorBody1;
	}

	public float getSunVectorBody2() {
		return sunVectorBody2;
	}

	public void setSunVectorBody2(float sunVectorBody2) {
		this.sunVectorBody2 = sunVectorBody2;
	}

	public float getSunVectorBody3() {
		return sunVectorBody3;
	}

	public void setSunVectorBody3(float sunVectorBody3) {
		this.sunVectorBody3 = sunVectorBody3;
	}

	public SunVectorStatus getSunVectorStatus() {
		return sunVectorStatus;
	}

	public void setSunVectorStatus(SunVectorStatus sunVectorStatus) {
		this.sunVectorStatus = sunVectorStatus;
	}

	public int getSunSensorUsed() {
		return sunSensorUsed;
	}

	public void setSunSensorUsed(int sunSensorUsed) {
		this.sunSensorUsed = sunSensorUsed;
	}

}
