package ru.r2cloud.jradio.enso;

import java.io.IOException;

import ru.r2cloud.jradio.celesta.EpsMode;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

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
	private int ttcTxCur;
	private int ttcTxCurMax;
	private int plCur;
	private int chargeCur;
	private byte tempXPlus;
	private byte tempXMinus;
	private byte tempYPlus;
	private byte tempYMinus;
	private byte tempZPlus;
	private int obdhVolt;
	private int ttcVolt;
	private int plVolt;
	private float mos1Volt;
	private float mos2Volt;
	private float mos3Volt;
	private float refVolt;
	private byte temp5vReg;
	private byte temp6vReg;
	private int ttcMcuVolt;

	public Eps() {
		// do nothing
	}

	public Eps(LittleEndianDataInputStream dis) throws IOException {
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
		ttcTxCur = dis.readUnsignedByte() * 5;
		ttcTxCurMax = dis.readUnsignedByte() * 5;
		plCur = dis.readUnsignedByte() * 5;
		chargeCur = dis.readUnsignedByte() * 12;
		tempXPlus = dis.readByte();
		tempXMinus = dis.readByte();
		tempYPlus = dis.readByte();
		tempYMinus = dis.readByte();
		tempZPlus = dis.readByte();
		obdhVolt = dis.readUnsignedByte() * 10 + 4000;
		ttcVolt = dis.readUnsignedByte() * 10 + 4000;
		plVolt = dis.readUnsignedByte() * 10 + 4000;
		mos1Volt = (dis.readUnsignedByte() + 2200) * 0.805f;
		mos2Volt = (dis.readUnsignedByte() + 2200) * 0.805f;
		mos3Volt = (dis.readUnsignedByte() + 2200) * 0.805f;
		refVolt = dis.readUnsignedShort() * 0.805f;
		temp5vReg = dis.readByte();
		temp6vReg = dis.readByte();
		ttcMcuVolt = dis.readUnsignedByte() * 10 + 4000;
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

	public int getTtcTxCur() {
		return ttcTxCur;
	}

	public void setTtcTxCur(int ttcTxCur) {
		this.ttcTxCur = ttcTxCur;
	}

	public int getTtcTxCurMax() {
		return ttcTxCurMax;
	}

	public void setTtcTxCurMax(int ttcTxCurMax) {
		this.ttcTxCurMax = ttcTxCurMax;
	}

	public int getPlCur() {
		return plCur;
	}

	public void setPlCur(int plCur) {
		this.plCur = plCur;
	}

	public int getChargeCur() {
		return chargeCur;
	}

	public void setChargeCur(int chargeCur) {
		this.chargeCur = chargeCur;
	}

	public byte getTempXPlus() {
		return tempXPlus;
	}

	public void setTempXPlus(byte tempXPlus) {
		this.tempXPlus = tempXPlus;
	}

	public byte getTempXMinus() {
		return tempXMinus;
	}

	public void setTempXMinus(byte tempXMinus) {
		this.tempXMinus = tempXMinus;
	}

	public byte getTempYPlus() {
		return tempYPlus;
	}

	public void setTempYPlus(byte tempYPlus) {
		this.tempYPlus = tempYPlus;
	}

	public byte getTempYMinus() {
		return tempYMinus;
	}

	public void setTempYMinus(byte tempYMinus) {
		this.tempYMinus = tempYMinus;
	}

	public byte getTempZPlus() {
		return tempZPlus;
	}

	public void setTempZPlus(byte tempZPlus) {
		this.tempZPlus = tempZPlus;
	}

	public int getObdhVolt() {
		return obdhVolt;
	}

	public void setObdhVolt(int obdhVolt) {
		this.obdhVolt = obdhVolt;
	}

	public int getTtcVolt() {
		return ttcVolt;
	}

	public void setTtcVolt(int ttcVolt) {
		this.ttcVolt = ttcVolt;
	}

	public int getPlVolt() {
		return plVolt;
	}

	public void setPlVolt(int plVolt) {
		this.plVolt = plVolt;
	}

	public float getMos1Volt() {
		return mos1Volt;
	}

	public void setMos1Volt(float mos1Volt) {
		this.mos1Volt = mos1Volt;
	}

	public float getMos2Volt() {
		return mos2Volt;
	}

	public void setMos2Volt(float mos2Volt) {
		this.mos2Volt = mos2Volt;
	}

	public float getMos3Volt() {
		return mos3Volt;
	}

	public void setMos3Volt(float mos3Volt) {
		this.mos3Volt = mos3Volt;
	}

	public float getRefVolt() {
		return refVolt;
	}

	public void setRefVolt(float refVolt) {
		this.refVolt = refVolt;
	}

	public byte getTemp5vReg() {
		return temp5vReg;
	}

	public void setTemp5vReg(byte temp5vReg) {
		this.temp5vReg = temp5vReg;
	}

	public byte getTemp6vReg() {
		return temp6vReg;
	}

	public void setTemp6vReg(byte temp6vReg) {
		this.temp6vReg = temp6vReg;
	}

	public int getTtcMcuVolt() {
		return ttcMcuVolt;
	}

	public void setTtcMcuVolt(int ttcMcuVolt) {
		this.ttcMcuVolt = ttcMcuVolt;
	}

}
