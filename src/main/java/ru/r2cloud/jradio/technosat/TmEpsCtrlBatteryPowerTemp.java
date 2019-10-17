package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmEpsCtrlBatteryPowerTemp {

	private short i2c0Current; // Current drawn by all sensors of the solar panels.
	private float batVoltA; // Battery A voltage measured by its battery monitor.
	private short batCurrA; // Battery A voltage measured by its battery monitor.
	private int batCapca; // Battery A voltage measured by its battery monitor.
	private float batVoltAAdc; // Battery A voltage measured via an external voltage divider and ADC of the µC.
	private float batVoltB; // Battery B voltage measured by its battery monitor.
	private short batCurrB; // Battery B voltage measured by its battery monitor.
	private int batCapB; // Battery B voltage measured by its battery monitor.
	private float batVoltBAdc; // Battery B voltage measured via an external voltage divider and ADC of the µC.
	private byte batTmpA; // Battery A voltage measured by its battery monitor.
	private byte batTmpB; // Battery B voltage measured by its battery monitor.

	public TmEpsCtrlBatteryPowerTemp(DataInputStream dis) throws IOException {
		i2c0Current = dis.readShort();
		batVoltA = dis.readUnsignedShort() * 0.001f;
		batCurrA = dis.readShort();
		batCapca = dis.readUnsignedShort();
		batVoltAAdc = dis.readUnsignedShort() * 0.001f;
		batVoltB = dis.readUnsignedShort() * 0.001f;
		batCurrB = dis.readShort();
		batCapB = dis.readUnsignedShort();
		batVoltBAdc = dis.readUnsignedShort() * 0.001f;
		batTmpA = dis.readByte();
		batTmpB = dis.readByte();
	}

	public short getI2c0Current() {
		return i2c0Current;
	}

	public void setI2c0Current(short i2c0Current) {
		this.i2c0Current = i2c0Current;
	}

	public float getBatVoltA() {
		return batVoltA;
	}

	public void setBatVoltA(float batVoltA) {
		this.batVoltA = batVoltA;
	}

	public short getBatCurrA() {
		return batCurrA;
	}

	public void setBatCurrA(short batCurrA) {
		this.batCurrA = batCurrA;
	}

	public int getBatCapca() {
		return batCapca;
	}

	public void setBatCapca(int batCapca) {
		this.batCapca = batCapca;
	}

	public float getBatVoltAAdc() {
		return batVoltAAdc;
	}

	public void setBatVoltAAdc(float batVoltAAdc) {
		this.batVoltAAdc = batVoltAAdc;
	}

	public float getBatVoltB() {
		return batVoltB;
	}

	public void setBatVoltB(float batVoltB) {
		this.batVoltB = batVoltB;
	}

	public short getBatCurrB() {
		return batCurrB;
	}

	public void setBatCurrB(short batCurrB) {
		this.batCurrB = batCurrB;
	}

	public int getBatCapB() {
		return batCapB;
	}

	public void setBatCapB(int batCapB) {
		this.batCapB = batCapB;
	}

	public float getBatVoltBAdc() {
		return batVoltBAdc;
	}

	public void setBatVoltBAdc(float batVoltBAdc) {
		this.batVoltBAdc = batVoltBAdc;
	}

	public byte getBatTmpA() {
		return batTmpA;
	}

	public void setBatTmpA(byte batTmpA) {
		this.batTmpA = batTmpA;
	}

	public byte getBatTmpB() {
		return batTmpB;
	}

	public void setBatTmpB(byte batTmpB) {
		this.batTmpB = batTmpB;
	}

}
