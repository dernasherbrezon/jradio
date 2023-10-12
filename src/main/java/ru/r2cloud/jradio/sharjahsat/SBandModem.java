package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SBandModem {

	private int batteryCurrent;
	private int paCurrent;
	private int batteryVoltage;
	private int paVoltage;
	private float paTemperature;
	private float rfOutputPower;
	private float boardTempTop;
	private float boardTempBottom;

	public SBandModem() {
		// do nothing
	}

	public SBandModem(LittleEndianDataInputStream dis) throws IOException {
		batteryCurrent = dis.readUnsignedShort() * 40;
		paCurrent = dis.readUnsignedShort() * 40;
		batteryVoltage = dis.readUnsignedShort() * 4;
		paVoltage = dis.readUnsignedShort() * 4;
		paTemperature = ((dis.readUnsignedShort() * 3.0f / 4096) - 0.5f) * 100;
		rfOutputPower = dis.readUnsignedShort() * 0.00113932291f;
		boardTempTop = dis.readShort() * 0.00390625f;
		boardTempBottom = dis.readShort() * 0.00390625f;
	}

	public int getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(int batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public int getPaCurrent() {
		return paCurrent;
	}

	public void setPaCurrent(int paCurrent) {
		this.paCurrent = paCurrent;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getPaVoltage() {
		return paVoltage;
	}

	public void setPaVoltage(int paVoltage) {
		this.paVoltage = paVoltage;
	}

	public float getPaTemperature() {
		return paTemperature;
	}

	public void setPaTemperature(float paTemperature) {
		this.paTemperature = paTemperature;
	}

	public float getRfOutputPower() {
		return rfOutputPower;
	}

	public void setRfOutputPower(float rfOutputPower) {
		this.rfOutputPower = rfOutputPower;
	}

	public float getBoardTempTop() {
		return boardTempTop;
	}

	public void setBoardTempTop(float boardTempTop) {
		this.boardTempTop = boardTempTop;
	}

	public float getBoardTempBottom() {
		return boardTempBottom;
	}

	public void setBoardTempBottom(float boardTempBottom) {
		this.boardTempBottom = boardTempBottom;
	}

}
