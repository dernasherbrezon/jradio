package ru.r2cloud.jradio.randev;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Hstx {

	private float rfOutputVolts;
	private float paTemp;
	private float boardTempTop;
	private float boardTempBottom;
	private float batCurrent;
	private float batVoltage;
	private float paCurrent;
	private float paVoltage;

	public Hstx() {
		// do nothing
	}

	public Hstx(LittleEndianDataInputStream dis) throws IOException {
		rfOutputVolts = dis.readUnsignedShort() * 0.001139f;
		paTemp = dis.readUnsignedShort() * 0.073242f - 50;
		boardTempTop = dis.readUnsignedShort() * 0.0625f;
		boardTempBottom = dis.readUnsignedShort() * 0.0625f;
		batCurrent = dis.readUnsignedShort() * 0.000004f;
		batVoltage = dis.readUnsignedShort() * 0.004f;
		paCurrent = dis.readUnsignedShort() * 0.000004f;
		paVoltage = dis.readUnsignedShort() * 0.004f;
	}

	public float getRfOutputVolts() {
		return rfOutputVolts;
	}

	public void setRfOutputVolts(float rfOutputVolts) {
		this.rfOutputVolts = rfOutputVolts;
	}

	public float getPaTemp() {
		return paTemp;
	}

	public void setPaTemp(float paTemp) {
		this.paTemp = paTemp;
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

	public float getBatCurrent() {
		return batCurrent;
	}

	public void setBatCurrent(float batCurrent) {
		this.batCurrent = batCurrent;
	}

	public float getBatVoltage() {
		return batVoltage;
	}

	public void setBatVoltage(float batVoltage) {
		this.batVoltage = batVoltage;
	}

	public float getPaCurrent() {
		return paCurrent;
	}

	public void setPaCurrent(float paCurrent) {
		this.paCurrent = paCurrent;
	}

	public float getPaVoltage() {
		return paVoltage;
	}

	public void setPaVoltage(float paVoltage) {
		this.paVoltage = paVoltage;
	}

}
