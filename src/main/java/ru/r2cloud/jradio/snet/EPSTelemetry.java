package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;

public class EPSTelemetry {

	private float[] solarPanelCurrent;
	private int mainSolarVoltage;
	private Battery[] battery;
	private float sumVoltage;
	private float voltage3V3;
	private float voltage5V;
	private float[] temperatureOfBattery;
	private float temperatureOfObc;
	private float currentOfObc;
	private float voltageOfObc;
	private BatteryCurrent[] batteryCurrents;

	public EPSTelemetry(LittleEndianBitInputStream bis) throws IOException {
		solarPanelCurrent = new float[6];
		for (int i = 0; i < solarPanelCurrent.length; i++) {
			solarPanelCurrent[i] = bis.readShort() / 50.0f;
		}
		mainSolarVoltage = bis.readShort();
		battery = new Battery[2];
		for (int i = 0; i < battery.length; i++) {
			battery[i] = new Battery(bis);
		}
		sumVoltage = bis.readShort() / 2.0f;
		voltage3V3 = bis.readShort() / 8.0f;
		voltage5V = bis.readShort() / 5.0f;
		temperatureOfBattery = new float[2];
		for (int i = 0; i < temperatureOfBattery.length; i++) {
			temperatureOfBattery[i] = bis.readShort() / 256.0f;
		}
		temperatureOfObc = bis.readShort();
		currentOfObc = bis.readShort();
		voltageOfObc = bis.readShort();
		batteryCurrents = new BatteryCurrent[2];
		for (int i = 0; i < batteryCurrents.length; i++) {
			batteryCurrents[i] = new BatteryCurrent(bis);
		}
	}

	public float[] getSolarPanelCurrent() {
		return solarPanelCurrent;
	}

	public void setSolarPanelCurrent(float[] solarPanelCurrent) {
		this.solarPanelCurrent = solarPanelCurrent;
	}

	public int getMainSolarVoltage() {
		return mainSolarVoltage;
	}

	public void setMainSolarVoltage(int mainSolarVoltage) {
		this.mainSolarVoltage = mainSolarVoltage;
	}

	public Battery[] getBattery() {
		return battery;
	}

	public void setBattery(Battery[] battery) {
		this.battery = battery;
	}

	public float getSumVoltage() {
		return sumVoltage;
	}

	public void setSumVoltage(float sumVoltage) {
		this.sumVoltage = sumVoltage;
	}

	public float getVoltage3V3() {
		return voltage3V3;
	}

	public void setVoltage3V3(float voltage3v3) {
		voltage3V3 = voltage3v3;
	}

	public float getVoltage5V() {
		return voltage5V;
	}

	public void setVoltage5V(float voltage5v) {
		voltage5V = voltage5v;
	}

	public float[] getTemperatureOfBattery() {
		return temperatureOfBattery;
	}

	public void setTemperatureOfBattery(float[] temperatureOfBattery) {
		this.temperatureOfBattery = temperatureOfBattery;
	}

	public float getTemperatureOfObc() {
		return temperatureOfObc;
	}

	public void setTemperatureOfObc(float temperatureOfObc) {
		this.temperatureOfObc = temperatureOfObc;
	}

	public float getCurrentOfObc() {
		return currentOfObc;
	}

	public void setCurrentOfObc(float currentOfObc) {
		this.currentOfObc = currentOfObc;
	}

	public float getVoltageOfObc() {
		return voltageOfObc;
	}

	public void setVoltageOfObc(float voltageOfObc) {
		this.voltageOfObc = voltageOfObc;
	}

	public BatteryCurrent[] getBatteryCurrents() {
		return batteryCurrents;
	}

	public void setBatteryCurrents(BatteryCurrent[] batteryCurrents) {
		this.batteryCurrents = batteryCurrents;
	}

}
