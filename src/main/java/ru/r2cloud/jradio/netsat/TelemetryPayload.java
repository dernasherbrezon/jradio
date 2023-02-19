package ru.r2cloud.jradio.netsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class TelemetryPayload {

	private long beaconRate;
	private long uptime;
	private int statusFlags;
	private byte tempA;
	private byte percentageA;
	private byte tempB;
	private byte percentageB;
	private short currentA;
	private short voltageA;
	private short powerA;
	private short currentB;
	private short voltageB;
	private short powerB;
	private int powerTotal;
	private byte obcTemperature;
	private byte temperatureXp;
	private byte temperatureXm;
	private byte temperatureYp;
	private byte temperatureYm;
	private byte temperatureZm;
	private long frequency;
	private int seuRam;
	private int sueRom;

	public TelemetryPayload() {
		// do nothing
	}

	public TelemetryPayload(LittleEndianDataInputStream dis) throws IOException {
		beaconRate = dis.readUnsignedInt();
		uptime = dis.readUnsignedInt();
		statusFlags = dis.readUnsignedShort();
		tempA = dis.readByte();
		percentageA = dis.readByte();
		tempB = dis.readByte();
		percentageB = dis.readByte();
		voltageA = dis.readShort();
		currentA = dis.readShort();
		powerA = dis.readShort();
		voltageB = dis.readShort();
		currentB = dis.readShort();
		powerB = dis.readShort();
		powerTotal = dis.readInt();
		obcTemperature = dis.readByte();
		temperatureXp = dis.readByte();
		temperatureXm = dis.readByte();
		temperatureYp = dis.readByte();
		temperatureYm = dis.readByte();
		temperatureZm = dis.readByte();
		frequency = dis.readUnsignedInt();
		seuRam = dis.readUnsignedByte();
		sueRom = dis.readUnsignedByte();
	}

	public long getBeaconRate() {
		return beaconRate;
	}

	public void setBeaconRate(long beaconRate) {
		this.beaconRate = beaconRate;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getStatusFlags() {
		return statusFlags;
	}

	public void setStatusFlags(int statusFlags) {
		this.statusFlags = statusFlags;
	}

	public byte getTempA() {
		return tempA;
	}

	public void setTempA(byte tempA) {
		this.tempA = tempA;
	}

	public byte getPercentageA() {
		return percentageA;
	}

	public void setPercentageA(byte percentageA) {
		this.percentageA = percentageA;
	}

	public byte getTempB() {
		return tempB;
	}

	public void setTempB(byte tempB) {
		this.tempB = tempB;
	}

	public byte getPercentageB() {
		return percentageB;
	}

	public void setPercentageB(byte percentageB) {
		this.percentageB = percentageB;
	}

	public short getCurrentA() {
		return currentA;
	}

	public void setCurrentA(short currentA) {
		this.currentA = currentA;
	}

	public short getVoltageA() {
		return voltageA;
	}

	public void setVoltageA(short voltageA) {
		this.voltageA = voltageA;
	}

	public short getPowerA() {
		return powerA;
	}

	public void setPowerA(short powerA) {
		this.powerA = powerA;
	}

	public short getCurrentB() {
		return currentB;
	}

	public void setCurrentB(short currentB) {
		this.currentB = currentB;
	}

	public short getVoltageB() {
		return voltageB;
	}

	public void setVoltageB(short voltageB) {
		this.voltageB = voltageB;
	}

	public short getPowerB() {
		return powerB;
	}

	public void setPowerB(short powerB) {
		this.powerB = powerB;
	}

	public int getPowerTotal() {
		return powerTotal;
	}

	public void setPowerTotal(int powerTotal) {
		this.powerTotal = powerTotal;
	}

	public byte getObcTemperature() {
		return obcTemperature;
	}

	public void setObcTemperature(byte obcTemperature) {
		this.obcTemperature = obcTemperature;
	}

	public byte getTemperatureXp() {
		return temperatureXp;
	}

	public void setTemperatureXp(byte temperatureXp) {
		this.temperatureXp = temperatureXp;
	}

	public byte getTemperatureXm() {
		return temperatureXm;
	}

	public void setTemperatureXm(byte temperatureXm) {
		this.temperatureXm = temperatureXm;
	}

	public byte getTemperatureYp() {
		return temperatureYp;
	}

	public void setTemperatureYp(byte temperatureYp) {
		this.temperatureYp = temperatureYp;
	}

	public byte getTemperatureYm() {
		return temperatureYm;
	}

	public void setTemperatureYm(byte temperatureYm) {
		this.temperatureYm = temperatureYm;
	}

	public byte getTemperatureZm() {
		return temperatureZm;
	}

	public void setTemperatureZm(byte temperatureZm) {
		this.temperatureZm = temperatureZm;
	}

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	public int getSeuRam() {
		return seuRam;
	}

	public void setSeuRam(int seuRam) {
		this.seuRam = seuRam;
	}

	public int getSueRom() {
		return sueRom;
	}

	public void setSueRom(int sueRom) {
		this.sueRom = sueRom;
	}

}
