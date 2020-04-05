package ru.r2cloud.jradio.swampsat2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Vutrx {

	private int rxFailedpackage;
	private int rxCrcfailedpackage;
	private int rxPackagecounter;

	private int rxFrequentlock;
	private int txFrequentlock;

	private double rssi;
	private int smpsTemperature;
	private int poweramplifierTemperature;
	private int poweramplifierPower;
	private int frequencyoffsetTx;
	private int frequencyoffsetRx;

	private int dtmfTone;
	private int dtmfCounter;

	private double current3v3;
	private double current5v;
	private double voltage3v3;
	private double voltage5v;

	private double poweramplifierForwardpower;
	private double poweramplifierReversepower;

	public Vutrx() {
		// do nothing
	}

	public Vutrx(LittleEndianDataInputStream dis) throws IOException {
		rxFailedpackage = dis.readUnsignedByte();
		rxCrcfailedpackage = dis.readUnsignedShort();
		rxPackagecounter = dis.readUnsignedShort();
		int frequentlock = dis.readUnsignedByte();
		rxFrequentlock = ((frequentlock >> 0) & 0x1);
		txFrequentlock = ((frequentlock >> 1) & 0x1);
		rssi = (dis.readUnsignedShort() * 3.0) / 4096;
		smpsTemperature = dis.readByte();
		poweramplifierTemperature = dis.readByte();
		poweramplifierPower = dis.readUnsignedByte();
		frequencyoffsetTx = dis.readUnsignedShort();
		frequencyoffsetRx = dis.readUnsignedShort();
		int dtmf = dis.readUnsignedByte();
		dtmfTone = ((dtmf) & 0b1111);
		dtmfCounter = ((dtmf >> 4) & 0b1111);
		current3v3 = dis.readShort() * 3e-6;
		current5v = dis.readShort() * 62e-6;
		voltage3v3 = dis.readShort() * 4e-3;
		voltage5v = dis.readShort() * 4e-3;
		poweramplifierForwardpower = (dis.readUnsignedShort() * 3.0) / 4096;
		poweramplifierReversepower = (dis.readUnsignedShort() * 3.0) / 4096;
	}

	public int getRxFailedpackage() {
		return rxFailedpackage;
	}

	public void setRxFailedpackage(int rxFailedpackage) {
		this.rxFailedpackage = rxFailedpackage;
	}

	public int getRxCrcfailedpackage() {
		return rxCrcfailedpackage;
	}

	public void setRxCrcfailedpackage(int rxCrcfailedpackage) {
		this.rxCrcfailedpackage = rxCrcfailedpackage;
	}

	public int getRxPackagecounter() {
		return rxPackagecounter;
	}

	public void setRxPackagecounter(int rxPackagecounter) {
		this.rxPackagecounter = rxPackagecounter;
	}

	public int getRxFrequentlock() {
		return rxFrequentlock;
	}

	public void setRxFrequentlock(int rxFrequentlock) {
		this.rxFrequentlock = rxFrequentlock;
	}

	public int getTxFrequentlock() {
		return txFrequentlock;
	}

	public void setTxFrequentlock(int txFrequentlock) {
		this.txFrequentlock = txFrequentlock;
	}

	public double getRssi() {
		return rssi;
	}

	public void setRssi(double rssi) {
		this.rssi = rssi;
	}

	public int getSmpsTemperature() {
		return smpsTemperature;
	}

	public void setSmpsTemperature(int smpsTemperature) {
		this.smpsTemperature = smpsTemperature;
	}

	public int getPoweramplifierTemperature() {
		return poweramplifierTemperature;
	}

	public void setPoweramplifierTemperature(int poweramplifierTemperature) {
		this.poweramplifierTemperature = poweramplifierTemperature;
	}

	public int getPoweramplifierPower() {
		return poweramplifierPower;
	}

	public void setPoweramplifierPower(int poweramplifierPower) {
		this.poweramplifierPower = poweramplifierPower;
	}

	public int getFrequencyoffsetTx() {
		return frequencyoffsetTx;
	}

	public void setFrequencyoffsetTx(int frequencyoffsetTx) {
		this.frequencyoffsetTx = frequencyoffsetTx;
	}

	public int getFrequencyoffsetRx() {
		return frequencyoffsetRx;
	}

	public void setFrequencyoffsetRx(int frequencyoffsetRx) {
		this.frequencyoffsetRx = frequencyoffsetRx;
	}

	public int getDtmfTone() {
		return dtmfTone;
	}

	public void setDtmfTone(int dtmfTone) {
		this.dtmfTone = dtmfTone;
	}

	public int getDtmfCounter() {
		return dtmfCounter;
	}

	public void setDtmfCounter(int dtmfCounter) {
		this.dtmfCounter = dtmfCounter;
	}

	public double getCurrent3v3() {
		return current3v3;
	}

	public void setCurrent3v3(double current3v3) {
		this.current3v3 = current3v3;
	}

	public double getCurrent5v() {
		return current5v;
	}

	public void setCurrent5v(double current5v) {
		this.current5v = current5v;
	}

	public double getVoltage3v3() {
		return voltage3v3;
	}

	public void setVoltage3v3(double voltage3v3) {
		this.voltage3v3 = voltage3v3;
	}

	public double getVoltage5v() {
		return voltage5v;
	}

	public void setVoltage5v(double voltage5v) {
		this.voltage5v = voltage5v;
	}

	public double getPoweramplifierForwardpower() {
		return poweramplifierForwardpower;
	}

	public void setPoweramplifierForwardpower(double poweramplifierForwardpower) {
		this.poweramplifierForwardpower = poweramplifierForwardpower;
	}

	public double getPoweramplifierReversepower() {
		return poweramplifierReversepower;
	}

	public void setPoweramplifierReversepower(double poweramplifierReversepower) {
		this.poweramplifierReversepower = poweramplifierReversepower;
	}

}
