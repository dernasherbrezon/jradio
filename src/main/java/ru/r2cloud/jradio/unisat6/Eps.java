package ru.r2cloud.jradio.unisat6;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Eps {

	private int vbat; // Battery voltage in mV
	private int currentSun; // Current from solar panels in mA
	private int currentOut; // Current comsuption by the satellite
	private int vpanel01; // Voltage in mV from bottom panels
	private int vpanel02; // Voltage in mV from panels B & D
	private int vpanel03; // Voltage in mV from panels A & C
	private int current01; // Current in mA from bottom panels
	private int current02; // Current in mA from panels B & D
	private int current03; // Current in mA from panels A & C
	private int batTemperature; // Temperature of the batteries

	public Eps() {
		// do nothing
	}

	public Eps(LittleEndianDataInputStream dis) throws IOException {
		vbat = dis.readUnsignedShort();
		currentSun = dis.readUnsignedShort();
		currentOut = dis.readUnsignedShort();
		vpanel01 = dis.readUnsignedShort();
		vpanel02 = dis.readUnsignedShort();
		vpanel03 = dis.readUnsignedShort();
		current01 = dis.readUnsignedShort();
		current02 = dis.readUnsignedShort();
		current03 = dis.readUnsignedShort();
		batTemperature = dis.readUnsignedShort();
	}

	public int getVbat() {
		return vbat;
	}

	public void setVbat(int vbat) {
		this.vbat = vbat;
	}

	public int getCurrentSun() {
		return currentSun;
	}

	public void setCurrentSun(int currentSun) {
		this.currentSun = currentSun;
	}

	public int getCurrentOut() {
		return currentOut;
	}

	public void setCurrentOut(int currentOut) {
		this.currentOut = currentOut;
	}

	public int getVpanel01() {
		return vpanel01;
	}

	public void setVpanel01(int vpanel01) {
		this.vpanel01 = vpanel01;
	}

	public int getVpanel02() {
		return vpanel02;
	}

	public void setVpanel02(int vpanel02) {
		this.vpanel02 = vpanel02;
	}

	public int getVpanel03() {
		return vpanel03;
	}

	public void setVpanel03(int vpanel03) {
		this.vpanel03 = vpanel03;
	}

	public int getCurrent01() {
		return current01;
	}

	public void setCurrent01(int current01) {
		this.current01 = current01;
	}

	public int getCurrent02() {
		return current02;
	}

	public void setCurrent02(int current02) {
		this.current02 = current02;
	}

	public int getCurrent03() {
		return current03;
	}

	public void setCurrent03(int current03) {
		this.current03 = current03;
	}

	public int getBatTemperature() {
		return batTemperature;
	}

	public void setBatTemperature(int batTemperature) {
		this.batTemperature = batTemperature;
	}

}
