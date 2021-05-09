package ru.r2cloud.jradio.tausat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Tausat1Telemetry {

	private int batteryVoltage;
	private int batteryCurrent;
	private int current3v3;
	private int current5v;
	private float loTrxvuTemperature;
	private float paTrxvuTemperature;
	private int eps0Temperature;
	private int eps1Temperature;
	private int eps2Temperature;
	private int eps3Temperature;
	private int battery0Temperature;
	private int battery1Temperature;
	private float rxDoppler;
	private float rxRssi;
	private float txRefl;
	private float txFlow;
	private int fileSystemLastError;
	private int numberOfDelayedCommands;
	private long numberOfResets;
	private long lastResetTime;
	private int state;

	public Tausat1Telemetry() {
		// do nothing
	}

	public Tausat1Telemetry(DataInputStream dis) throws IOException {
		batteryVoltage = dis.readUnsignedShort();
		batteryCurrent = dis.readUnsignedShort();
		current3v3 = dis.readUnsignedShort();
		current5v = dis.readUnsignedShort();
		loTrxvuTemperature = dis.readUnsignedShort() * -0.07669F + 195.6037F;
		paTrxvuTemperature = dis.readUnsignedShort() * -0.07669F + 195.6037F;
		eps0Temperature = dis.readUnsignedShort();
		eps1Temperature = dis.readUnsignedShort();
		eps2Temperature = dis.readUnsignedShort();
		eps3Temperature = dis.readUnsignedShort();
		battery0Temperature = dis.readUnsignedShort();
		battery1Temperature = dis.readUnsignedShort();
		rxDoppler = dis.readUnsignedShort() * 13.352F - 22300F;
		rxRssi = dis.readUnsignedShort() * 0.03F - 152F;
		txRefl = (float) Math.pow(dis.readUnsignedShort(), 2) * 5.887E-5F;
		txFlow = (float) Math.pow(dis.readUnsignedShort(), 2) * 5.887E-5F;
		dis.skipBytes(2 * 3);
		fileSystemLastError = dis.readByte();
		dis.skipBytes(1);
		numberOfDelayedCommands = dis.readByte();
		numberOfResets = StreamUtils.readUnsignedInt(dis);
		lastResetTime = StreamUtils.readUnsignedInt(dis);
		state = dis.readByte();
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(int batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public int getCurrent3v3() {
		return current3v3;
	}

	public void setCurrent3v3(int current3v3) {
		this.current3v3 = current3v3;
	}

	public int getCurrent5v() {
		return current5v;
	}

	public void setCurrent5v(int current5v) {
		this.current5v = current5v;
	}

	public float getLoTrxvuTemperature() {
		return loTrxvuTemperature;
	}

	public void setLoTrxvuTemperature(float loTrxvuTemperature) {
		this.loTrxvuTemperature = loTrxvuTemperature;
	}

	public float getPaTrxvuTemperature() {
		return paTrxvuTemperature;
	}

	public void setPaTrxvuTemperature(float paTrxvuTemperature) {
		this.paTrxvuTemperature = paTrxvuTemperature;
	}

	public int getEps0Temperature() {
		return eps0Temperature;
	}

	public void setEps0Temperature(int eps0Temperature) {
		this.eps0Temperature = eps0Temperature;
	}

	public int getEps1Temperature() {
		return eps1Temperature;
	}

	public void setEps1Temperature(int eps1Temperature) {
		this.eps1Temperature = eps1Temperature;
	}

	public int getEps2Temperature() {
		return eps2Temperature;
	}

	public void setEps2Temperature(int eps2Temperature) {
		this.eps2Temperature = eps2Temperature;
	}

	public int getEps3Temperature() {
		return eps3Temperature;
	}

	public void setEps3Temperature(int eps3Temperature) {
		this.eps3Temperature = eps3Temperature;
	}

	public int getBattery0Temperature() {
		return battery0Temperature;
	}

	public void setBattery0Temperature(int battery0Temperature) {
		this.battery0Temperature = battery0Temperature;
	}

	public int getBattery1Temperature() {
		return battery1Temperature;
	}

	public void setBattery1Temperature(int battery1Temperature) {
		this.battery1Temperature = battery1Temperature;
	}

	public float getRxDoppler() {
		return rxDoppler;
	}

	public void setRxDoppler(float rxDoppler) {
		this.rxDoppler = rxDoppler;
	}

	public float getRxRssi() {
		return rxRssi;
	}

	public void setRxRssi(float rxRssi) {
		this.rxRssi = rxRssi;
	}

	public float getTxRefl() {
		return txRefl;
	}

	public void setTxRefl(float txRefl) {
		this.txRefl = txRefl;
	}

	public float getTxFlow() {
		return txFlow;
	}

	public void setTxFlow(float txFlow) {
		this.txFlow = txFlow;
	}

	public int getFileSystemLastError() {
		return fileSystemLastError;
	}

	public void setFileSystemLastError(int fileSystemLastError) {
		this.fileSystemLastError = fileSystemLastError;
	}

	public int getNumberOfDelayedCommands() {
		return numberOfDelayedCommands;
	}

	public void setNumberOfDelayedCommands(int numberOfDelayedCommands) {
		this.numberOfDelayedCommands = numberOfDelayedCommands;
	}

	public long getNumberOfResets() {
		return numberOfResets;
	}

	public void setNumberOfResets(long numberOfResets) {
		this.numberOfResets = numberOfResets;
	}

	public long getLastResetTime() {
		return lastResetTime;
	}

	public void setLastResetTime(long lastResetTime) {
		this.lastResetTime = lastResetTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
