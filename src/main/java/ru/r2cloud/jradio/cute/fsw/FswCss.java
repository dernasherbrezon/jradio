package ru.r2cloud.jradio.cute.fsw;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.SunVectorStatus;
import ru.r2cloud.jradio.util.StreamUtils;

public class FswCss {

	private float sunVectorBody1;
	private float sunVectorBody2;
	private float sunVectorBody3;
	private SunVectorStatus sunVectorStatus;
	private int numDiodesUsed1;
	private int numDiodesUsed2;
	private int numDiodesUsed3;
	private int[] rawSunSensorData;
	private boolean sunSensorEnabled;
	private int sunSensorUsed;
	private boolean cssTestMode;

	public FswCss() {
		// do nothing
	}

	public FswCss(DataInputStream dis) throws IOException {
		sunVectorBody1 = dis.readShort() * 0.0001f;
		sunVectorBody2 = dis.readShort() * 0.0001f;
		sunVectorBody3 = dis.readShort() * 0.0001f;
		sunVectorStatus = SunVectorStatus.valueOfCode(dis.readUnsignedByte());
		numDiodesUsed1 = dis.readUnsignedByte();
		numDiodesUsed2 = dis.readUnsignedByte();
		numDiodesUsed3 = dis.readUnsignedByte();
		rawSunSensorData = StreamUtils.readUnsignedShortArray(dis, 12);
		sunSensorEnabled = dis.readBoolean();
		sunSensorUsed = dis.readUnsignedByte();
		cssTestMode = dis.readBoolean();
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

	public int getNumDiodesUsed1() {
		return numDiodesUsed1;
	}

	public void setNumDiodesUsed1(int numDiodesUsed1) {
		this.numDiodesUsed1 = numDiodesUsed1;
	}

	public int getNumDiodesUsed2() {
		return numDiodesUsed2;
	}

	public void setNumDiodesUsed2(int numDiodesUsed2) {
		this.numDiodesUsed2 = numDiodesUsed2;
	}

	public int getNumDiodesUsed3() {
		return numDiodesUsed3;
	}

	public void setNumDiodesUsed3(int numDiodesUsed3) {
		this.numDiodesUsed3 = numDiodesUsed3;
	}

	public int[] getRawSunSensorData() {
		return rawSunSensorData;
	}

	public void setRawSunSensorData(int[] rawSunSensorData) {
		this.rawSunSensorData = rawSunSensorData;
	}

	public boolean isSunSensorEnabled() {
		return sunSensorEnabled;
	}

	public void setSunSensorEnabled(boolean sunSensorEnabled) {
		this.sunSensorEnabled = sunSensorEnabled;
	}

	public int getSunSensorUsed() {
		return sunSensorUsed;
	}

	public void setSunSensorUsed(int sunSensorUsed) {
		this.sunSensorUsed = sunSensorUsed;
	}

	public boolean isCssTestMode() {
		return cssTestMode;
	}

	public void setCssTestMode(boolean cssTestMode) {
		this.cssTestMode = cssTestMode;
	}

}
