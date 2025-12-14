package ru.r2cloud.jradio.hunity;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Pcu {

	private SampleTime bootCounterTime;
	private int bootCounter;
	private SampleTime uptimeTime;
	private long uptime;

	private SampleTime unRegBusVoltageTime;
	private int unRegBusVoltage;

	private SampleTime regBusVoltageTime;
	private int regBusVoltage;

	public Pcu() {
		// do nothing
	}

	public Pcu(LittleEndianDataInputStream ldis) throws IOException {
		bootCounterTime = SampleTime.valueOfByte(ldis.readUnsignedByte());
		bootCounter = ldis.readUnsignedShort();
		uptimeTime = SampleTime.valueOfByte(ldis.readUnsignedByte());
		uptime = ldis.readUnsignedInt();
	}

	public int getBootCounter() {
		return bootCounter;
	}

	public void setBootCounter(int bootCounter) {
		this.bootCounter = bootCounter;
	}

	public SampleTime getBootCounterTime() {
		return bootCounterTime;
	}

	public void setBootCounterTime(SampleTime bootCounterTime) {
		this.bootCounterTime = bootCounterTime;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public SampleTime getUptimeTime() {
		return uptimeTime;
	}

	public void setUptimeTime(SampleTime uptimeTime) {
		this.uptimeTime = uptimeTime;
	}

	public int getUnRegBusVoltage() {
		return unRegBusVoltage;
	}

	public void setUnRegBusVoltage(int unRegBusVoltage) {
		this.unRegBusVoltage = unRegBusVoltage;
	}

	public SampleTime getUnRegBusVoltageTime() {
		return unRegBusVoltageTime;
	}

	public void setUnRegBusVoltageTime(SampleTime unRegBusVoltageTime) {
		this.unRegBusVoltageTime = unRegBusVoltageTime;
	}

	public int getRegBusVoltage() {
		return regBusVoltage;
	}

	public void setRegBusVoltage(int regBusVoltage) {
		this.regBusVoltage = regBusVoltage;
	}

	public SampleTime getRegBusVoltageTime() {
		return regBusVoltageTime;
	}

	public void setRegBusVoltageTime(SampleTime regBusVoltageTime) {
		this.regBusVoltageTime = regBusVoltageTime;
	}

}
