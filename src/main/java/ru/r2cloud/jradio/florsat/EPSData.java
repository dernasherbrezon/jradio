package ru.r2cloud.jradio.florsat;

import java.io.DataInputStream;
import java.io.IOException;

public class EPSData {

	private double batteryVoltage1;
	private double batteryVoltage2;
	private double batteryTemperature1;
	private double batteryTemperature2;
	private double batteryCharge;
	private double[] solarPanelCurrent;
	private double[] solarPanelVoltage;
	private int energyLevel;

	public EPSData() {
		// do nothing
	}

	public EPSData(DataInputStream dis) throws IOException {
		batteryVoltage1 = batteryVoltage(dis.readUnsignedShort());
		batteryVoltage2 = batteryVoltage(dis.readUnsignedShort());
		batteryTemperature1 = batteryTemperature((dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte());
		batteryTemperature2 = batteryTemperature((dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte());
		batteryCharge = batteryCharge(dis.readUnsignedShort());
		solarPanelCurrent = new double[6];
		for (int i = 0; i < solarPanelCurrent.length; i++) {
			solarPanelCurrent[i] = solarPanelCurrent(dis.readUnsignedShort());
		}
		solarPanelVoltage = new double[3];
		for (int i = 0; i < solarPanelVoltage.length; i++) {
			solarPanelVoltage[i] = solarPanelVoltage(dis.readUnsignedShort());
		}
		energyLevel = dis.readUnsignedByte();
	}

	public double getBatteryVoltage1() {
		return batteryVoltage1;
	}

	public void setBatteryVoltage1(double batteryVoltage1) {
		this.batteryVoltage1 = batteryVoltage1;
	}

	public double getBatteryVoltage2() {
		return batteryVoltage2;
	}

	public void setBatteryVoltage2(double batteryVoltage2) {
		this.batteryVoltage2 = batteryVoltage2;
	}

	public double getBatteryTemperature1() {
		return batteryTemperature1;
	}

	public void setBatteryTemperature1(double batteryTemperature1) {
		this.batteryTemperature1 = batteryTemperature1;
	}

	public double getBatteryTemperature2() {
		return batteryTemperature2;
	}

	public void setBatteryTemperature2(double batteryTemperature2) {
		this.batteryTemperature2 = batteryTemperature2;
	}

	public double getBatteryCharge() {
		return batteryCharge;
	}

	public void setBatteryCharge(double batteryCharge) {
		this.batteryCharge = batteryCharge;
	}

	public double[] getSolarPanelCurrent() {
		return solarPanelCurrent;
	}

	public void setSolarPanelCurrent(double[] solarPanelCurrent) {
		this.solarPanelCurrent = solarPanelCurrent;
	}

	public double[] getSolarPanelVoltage() {
		return solarPanelVoltage;
	}

	public void setSolarPanelVoltage(double[] solarPanelVoltage) {
		this.solarPanelVoltage = solarPanelVoltage;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	private static double batteryVoltage(int val) {
		return (val / 32.0) * 4.883e-3;
	}

	private static double batteryTemperature(long val) {
		return (val * 0.125) / 32.0;
	}

	private static double batteryCharge(int val) {
		return val * (6.25 * 1e-4);
	}

	private static double solarPanelCurrent(int val) {
		return val * (2.5 / 4095) * (1 / (0.05 * 0.025 * 3300));
	}

	private static double solarPanelVoltage(int val) {
		return val * (2.5 / 4095) * (100e3 + 93.1e3) / 100e3;
	}
}
