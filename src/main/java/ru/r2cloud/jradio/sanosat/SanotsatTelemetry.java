package ru.r2cloud.jradio.sanosat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SanotsatTelemetry {

	private int comTemperature;
	private int chargingVoltage;
	private int chargingCurrent;
	private int batteryTemperature;
	private int radiation; // uSv/hr
	private int numberOfResets;
	private boolean antennaDeploymentStatus;

	public SanotsatTelemetry() {
		// do nothing
	}

	public SanotsatTelemetry(LittleEndianDataInputStream dis) throws IOException {
		comTemperature = dis.readUnsignedShort();
		chargingVoltage = dis.readUnsignedShort();
		chargingCurrent = dis.readUnsignedShort();
		batteryTemperature = dis.readUnsignedShort();
		radiation = dis.readUnsignedShort();
		numberOfResets = dis.readUnsignedShort();
		antennaDeploymentStatus = dis.readBoolean();
	}

	public int getComTemperature() {
		return comTemperature;
	}

	public void setComTemperature(int comTemperature) {
		this.comTemperature = comTemperature;
	}

	public int getChargingVoltage() {
		return chargingVoltage;
	}

	public void setChargingVoltage(int chargingVoltage) {
		this.chargingVoltage = chargingVoltage;
	}

	public int getChargingCurrent() {
		return chargingCurrent;
	}

	public void setChargingCurrent(int chargingCurrent) {
		this.chargingCurrent = chargingCurrent;
	}

	public int getBatteryTemperature() {
		return batteryTemperature;
	}

	public void setBatteryTemperature(int batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public int getRadiation() {
		return radiation;
	}

	public void setRadiation(int radiation) {
		this.radiation = radiation;
	}

	public int getNumberOfResets() {
		return numberOfResets;
	}

	public void setNumberOfResets(int numberOfResets) {
		this.numberOfResets = numberOfResets;
	}

	public boolean isAntennaDeploymentStatus() {
		return antennaDeploymentStatus;
	}

	public void setAntennaDeploymentStatus(boolean antennaDeploymentStatus) {
		this.antennaDeploymentStatus = antennaDeploymentStatus;
	}

}
