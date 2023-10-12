package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class UhfVhfModem {

	private int smpsTemp;
	private int paTemp;
	private int current3v3;
	private int voltage3v3;
	private int current5v;
	private int voltage5v;

	public UhfVhfModem() {
		// do nothing
	}

	public UhfVhfModem(LittleEndianDataInputStream dis) throws IOException {
		smpsTemp = dis.readUnsignedByte();
		paTemp = dis.readUnsignedByte();
		current3v3 = dis.readUnsignedShort() * 3;
		voltage3v3 = dis.readUnsignedShort() * 4;
		current5v = dis.readUnsignedShort() * 62;
		voltage5v = dis.readUnsignedShort() * 4;
	}

	public int getSmpsTemp() {
		return smpsTemp;
	}

	public void setSmpsTemp(int smpsTemp) {
		this.smpsTemp = smpsTemp;
	}

	public int getPaTemp() {
		return paTemp;
	}

	public void setPaTemp(int paTemp) {
		this.paTemp = paTemp;
	}

	public int getCurrent3v3() {
		return current3v3;
	}

	public void setCurrent3v3(int current3v3) {
		this.current3v3 = current3v3;
	}

	public int getVoltage3v3() {
		return voltage3v3;
	}

	public void setVoltage3v3(int voltage3v3) {
		this.voltage3v3 = voltage3v3;
	}

	public int getCurrent5v() {
		return current5v;
	}

	public void setCurrent5v(int current5v) {
		this.current5v = current5v;
	}

	public int getVoltage5v() {
		return voltage5v;
	}

	public void setVoltage5v(int voltage5v) {
		this.voltage5v = voltage5v;
	}

}
