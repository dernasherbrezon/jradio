package ru.r2cloud.jradio.bobcat1;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class LeopFrame {

	private String callsign;
	private String bobcat1;
	private int batteryVoltage;
	private int batteryCurrentOut;
	private int batteryCurrentIn;
	private int bootCountA32;
	private int resetCauseA32;
	private int bootCauseA32;
	private long uptimeA32;
	private int bootCountAx100;
	private long bootCauseAx100;
	private int currentPwm;
	private int fsMounted;
	private int antennasDeployed;
	private int[] deployAttempts;
	private short gyroX;
	private short gyroY;
	private short gyroZ;
	private long timestamp;

	public LeopFrame() {
		// do nothing
	}

	public LeopFrame(DataInputStream dis) throws IOException {
		callsign = StreamUtils.readString(dis, 6);
		bobcat1 = StreamUtils.readString(dis, 9);
		batteryVoltage = dis.readUnsignedShort();
		batteryCurrentOut = dis.readUnsignedShort();
		batteryCurrentIn = dis.readUnsignedShort();
		bootCountA32 = dis.readUnsignedShort();
		resetCauseA32 = dis.readUnsignedByte();
		bootCauseA32 = dis.readUnsignedByte();
		uptimeA32 = StreamUtils.readUnsignedInt(dis);
		bootCountAx100 = dis.readUnsignedShort();
		bootCauseAx100 = StreamUtils.readUnsignedInt(dis);
		currentPwm = dis.readUnsignedShort();
		fsMounted = dis.readUnsignedByte();
		antennasDeployed = dis.readUnsignedByte();
		deployAttempts = StreamUtils.readUnsignedShortArray(dis, 4);
		gyroX = dis.readShort();
		gyroY = dis.readShort();
		gyroZ = dis.readShort();
		timestamp = StreamUtils.readUnsignedInt(dis);
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public String getBobcat1() {
		return bobcat1;
	}

	public void setBobcat1(String bobcat1) {
		this.bobcat1 = bobcat1;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getBatteryCurrentOut() {
		return batteryCurrentOut;
	}

	public void setBatteryCurrentOut(int batteryCurrentOut) {
		this.batteryCurrentOut = batteryCurrentOut;
	}

	public int getBatteryCurrentIn() {
		return batteryCurrentIn;
	}

	public void setBatteryCurrentIn(int batteryCurrentIn) {
		this.batteryCurrentIn = batteryCurrentIn;
	}

	public int getBootCountA32() {
		return bootCountA32;
	}

	public void setBootCountA32(int bootCountA32) {
		this.bootCountA32 = bootCountA32;
	}

	public int getResetCauseA32() {
		return resetCauseA32;
	}

	public void setResetCauseA32(int resetCauseA32) {
		this.resetCauseA32 = resetCauseA32;
	}

	public int getBootCauseA32() {
		return bootCauseA32;
	}

	public void setBootCauseA32(int bootCauseA32) {
		this.bootCauseA32 = bootCauseA32;
	}

	public long getUptimeA32() {
		return uptimeA32;
	}

	public void setUptimeA32(long uptimeA32) {
		this.uptimeA32 = uptimeA32;
	}

	public int getBootCountAx100() {
		return bootCountAx100;
	}

	public void setBootCountAx100(int bootCountAx100) {
		this.bootCountAx100 = bootCountAx100;
	}

	public long getBootCauseAx100() {
		return bootCauseAx100;
	}

	public void setBootCauseAx100(long bootCauseAx100) {
		this.bootCauseAx100 = bootCauseAx100;
	}

	public int getCurrentPwm() {
		return currentPwm;
	}

	public void setCurrentPwm(int currentPwm) {
		this.currentPwm = currentPwm;
	}

	public int getFsMounted() {
		return fsMounted;
	}

	public void setFsMounted(int fsMounted) {
		this.fsMounted = fsMounted;
	}

	public int getAntennasDeployed() {
		return antennasDeployed;
	}

	public void setAntennasDeployed(int antennasDeployed) {
		this.antennasDeployed = antennasDeployed;
	}

	public int[] getDeployAttempts() {
		return deployAttempts;
	}

	public void setDeployAttempts(int[] deployAttempts) {
		this.deployAttempts = deployAttempts;
	}

	public short getGyroX() {
		return gyroX;
	}

	public void setGyroX(short gyroX) {
		this.gyroX = gyroX;
	}

	public short getGyroY() {
		return gyroY;
	}

	public void setGyroY(short gyroY) {
		this.gyroY = gyroY;
	}

	public short getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(short gyroZ) {
		this.gyroZ = gyroZ;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
