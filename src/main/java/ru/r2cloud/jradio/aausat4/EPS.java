package ru.r2cloud.jradio.aausat4;

import java.io.DataInputStream;
import java.io.IOException;

public class EPS {

	private int bootCount;
	private long uptime;
	private long realtimeClock;
	private int pingStatus;
	private int subsystem_status;
	private int batteryVoltage;
	private byte cellDiff;
	private byte batteryCurrent;
	private int solarPower;
	private byte temperature;
	private byte paTemperature;
	private byte mainVoltage;

	public EPS(DataInputStream data) throws IOException {
		bootCount = data.readUnsignedShort();
		uptime = data.readInt() & 0xffffffffl;
		realtimeClock = data.readInt() & 0xffffffffl;
		pingStatus = data.readUnsignedByte();
		subsystem_status = data.readUnsignedShort();
		batteryVoltage = data.readUnsignedByte();
		cellDiff = data.readByte();
		batteryCurrent = data.readByte();
		solarPower = data.readUnsignedByte();
		temperature = data.readByte();
		paTemperature = data.readByte();
		mainVoltage = data.readByte();
		
        batteryVoltage *= 40;
        cellDiff *= 4;
        batteryCurrent *= 10;
        solarPower *= 20;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getRealtimeClock() {
		return realtimeClock;
	}

	public void setRealtimeClock(long realtimeClock) {
		this.realtimeClock = realtimeClock;
	}

	public int getPingStatus() {
		return pingStatus;
	}

	public void setPingStatus(int pingStatus) {
		this.pingStatus = pingStatus;
	}

	public int getSubsystem_status() {
		return subsystem_status;
	}

	public void setSubsystem_status(int subsystem_status) {
		this.subsystem_status = subsystem_status;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}
	
	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public byte getCellDiff() {
		return cellDiff;
	}

	public void setCellDiff(byte cellDiff) {
		this.cellDiff = cellDiff;
	}

	public byte getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(byte batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public int getSolarPower() {
		return solarPower;
	}

	public void setSolarPower(int solarPower) {
		this.solarPower = solarPower;
	}

	public byte getTemperature() {
		return temperature;
	}

	public void setTemperature(byte temperature) {
		this.temperature = temperature;
	}

	public byte getPaTemperature() {
		return paTemperature;
	}

	public void setPaTemperature(byte paTemperature) {
		this.paTemperature = paTemperature;
	}

	public byte getMainVoltage() {
		return mainVoltage;
	}

	public void setMainVoltage(byte mainVoltage) {
		this.mainVoltage = mainVoltage;
	}

}
