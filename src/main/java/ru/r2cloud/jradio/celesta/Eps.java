package ru.r2cloud.jradio.celesta;

import java.io.DataInputStream;
import java.io.IOException;

public class Eps {

	private EpsMode epsMode;
	private int batteryVoltage;
	private byte batteryTemperature;
	private int minBatteryVoltage;
	private int maxBatteryVoltage;
	private int avgBatteryVoltage;
	private int avgChargeCurrent;
	private int maxChargeCurrent;
	private byte zMinuFaceTemperature;
	private int obdhCurrent;
	private int epsCurrent;
	private int ttcMicroCCurrent;
	private int ttcPaCurrent;
	private int dosiCurrent;
	private int chargeCurrent;

	public Eps() {
		// do nothing
	}

	public Eps(DataInputStream dis) throws IOException {
		epsMode = EpsMode.valueOfCode(dis.readUnsignedByte());
		batteryVoltage = dis.readUnsignedByte() * 20;
		batteryTemperature = dis.readByte();
		minBatteryVoltage = dis.readUnsignedByte() * 20;
		maxBatteryVoltage = dis.readUnsignedByte() * 20;
		avgBatteryVoltage = dis.readUnsignedByte() * 20;
		avgChargeCurrent = dis.readUnsignedByte() * 12;
		maxChargeCurrent = dis.readUnsignedByte() * 12;
		zMinuFaceTemperature = dis.readByte();
		obdhCurrent = dis.readUnsignedByte();
		epsCurrent = dis.readUnsignedByte();
		ttcMicroCCurrent = dis.readUnsignedByte();
		ttcPaCurrent = dis.readUnsignedByte() * 5;
		dosiCurrent = dis.readUnsignedByte();
		chargeCurrent = dis.readUnsignedByte() * 12;
		dis.skipBytes(1);
	}

	public EpsMode getEpsMode() {
		return epsMode;
	}

	public void setEpsMode(EpsMode epsMode) {
		this.epsMode = epsMode;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public byte getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(byte batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public int getMinBatteryVoltage() {
		return minBatteryVoltage;
	}

	public void setMinBatteryVoltage(int minBatteryVoltage) {
		this.minBatteryVoltage = minBatteryVoltage;
	}

	public int getMaxBatteryVoltage() {
		return maxBatteryVoltage;
	}

	public void setMaxBatteryVoltage(int maxBatteryVoltage) {
		this.maxBatteryVoltage = maxBatteryVoltage;
	}

	public int getAvgBatteryVoltage() {
		return avgBatteryVoltage;
	}

	public void setAvgBatteryVoltage(int avgBatteryVoltage) {
		this.avgBatteryVoltage = avgBatteryVoltage;
	}

	public int getAvgChargeCurrent() {
		return avgChargeCurrent;
	}

	public void setAvgChargeCurrent(int avgChargeCurrent) {
		this.avgChargeCurrent = avgChargeCurrent;
	}

	public int getMaxChargeCurrent() {
		return maxChargeCurrent;
	}

	public void setMaxChargeCurrent(int maxChargeCurrent) {
		this.maxChargeCurrent = maxChargeCurrent;
	}

	public byte getzMinuFaceTemperature() {
		return zMinuFaceTemperature;
	}

	public void setzMinuFaceTemperature(byte zMinuFaceTemperature) {
		this.zMinuFaceTemperature = zMinuFaceTemperature;
	}

	public int getObdhCurrent() {
		return obdhCurrent;
	}

	public void setObdhCurrent(int obdhCurrent) {
		this.obdhCurrent = obdhCurrent;
	}

	public int getEpsCurrent() {
		return epsCurrent;
	}

	public void setEpsCurrent(int epsCurrent) {
		this.epsCurrent = epsCurrent;
	}

	public int getTtcMicroCCurrent() {
		return ttcMicroCCurrent;
	}

	public void setTtcMicroCCurrent(int ttcMicroCCurrent) {
		this.ttcMicroCCurrent = ttcMicroCCurrent;
	}

	public int getTtcPaCurrent() {
		return ttcPaCurrent;
	}

	public void setTtcPaCurrent(int ttcPaCurrent) {
		this.ttcPaCurrent = ttcPaCurrent;
	}

	public int getDosiCurrent() {
		return dosiCurrent;
	}

	public void setDosiCurrent(int dosiCurrent) {
		this.dosiCurrent = dosiCurrent;
	}

	public int getChargeCurrent() {
		return chargeCurrent;
	}

	public void setChargeCurrent(int chargeCurrent) {
		this.chargeCurrent = chargeCurrent;
	}

}
