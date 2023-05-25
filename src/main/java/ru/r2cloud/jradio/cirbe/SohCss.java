package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.SunVectorStatus;

public class SohCss {

	private float sunVectorBody1;
	private float sunVectorBody2;
	private float sunVectorBody3;
	private SunVectorStatus sunVectorStatus;
	private int cssInvalidCount;
	private int sunSensorUsed;
	private boolean cssTestMode;
	private boolean sunVectorEnabled;
	private boolean measSunValid;
	private boolean cssPowerState;

	public SohCss() {
		// do nothing
	}

	public SohCss(DataInputStream dis) throws IOException {
		sunVectorBody1 = dis.readShort() * 0.0001f;
		sunVectorBody2 = dis.readShort() * 0.0001f;
		sunVectorBody3 = dis.readShort() * 0.0001f;

		sunVectorStatus = SunVectorStatus.valueOfCode(dis.readUnsignedByte());
		cssInvalidCount = dis.readUnsignedShort();
		int raw = dis.readUnsignedByte();
		sunSensorUsed = ((raw >> 4) & 0x3);
		cssTestMode = ((raw >> 3) & 0x1) > 0;
		sunVectorEnabled = ((raw >> 2) & 0x1) > 0;
		measSunValid = ((raw >> 1) & 0x1) > 0;
		cssPowerState = (raw & 0x1) > 0;
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

	public int getCssInvalidCount() {
		return cssInvalidCount;
	}

	public void setCssInvalidCount(int cssInvalidCount) {
		this.cssInvalidCount = cssInvalidCount;
	}

	public boolean isCssTestMode() {
		return cssTestMode;
	}

	public void setCssTestMode(boolean cssTestMode) {
		this.cssTestMode = cssTestMode;
	}

	public boolean isSunVectorEnabled() {
		return sunVectorEnabled;
	}

	public void setSunVectorEnabled(boolean sunVectorEnabled) {
		this.sunVectorEnabled = sunVectorEnabled;
	}

	public boolean isMeasSunValid() {
		return measSunValid;
	}

	public void setMeasSunValid(boolean measSunValid) {
		this.measSunValid = measSunValid;
	}

	public boolean isCssPowerState() {
		return cssPowerState;
	}

	public void setCssPowerState(boolean cssPowerState) {
		this.cssPowerState = cssPowerState;
	}

}
