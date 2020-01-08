package ru.r2cloud.jradio.ao73;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class HighResolutionData {

	private float sunSensorXP;
	private float sunSensorYP;
	private float sunSensorYM;
	private float sunSensorZP;
	private float sunSensorZM;

	private int totalPhotoCurrent;
	private int batteryVoltage;
	
	public HighResolutionData() {
		// do nothing
	}

	public HighResolutionData(BitInputStream dis) throws IOException {
		sunSensorXP = RealtimeTelemetry.readXPlusTemperature(dis);
		sunSensorYP = RealtimeTelemetry.readYPlusTemperature(dis);
		sunSensorYM = RealtimeTelemetry.readYMinusTemperature(dis);
		sunSensorZP = RealtimeTelemetry.readYPlusTemperature(dis);
		sunSensorZM = RealtimeTelemetry.readYMinusTemperature(dis);

		totalPhotoCurrent = dis.readUnsignedInt(15);
		batteryVoltage = dis.readUnsignedInt(15);
	}

	public float getSunSensorXP() {
		return sunSensorXP;
	}

	public void setSunSensorXP(float sunSensorXP) {
		this.sunSensorXP = sunSensorXP;
	}

	public float getSunSensorYP() {
		return sunSensorYP;
	}

	public void setSunSensorYP(float sunSensorYP) {
		this.sunSensorYP = sunSensorYP;
	}

	public float getSunSensorYM() {
		return sunSensorYM;
	}

	public void setSunSensorYM(float sunSensorYM) {
		this.sunSensorYM = sunSensorYM;
	}

	public float getSunSensorZP() {
		return sunSensorZP;
	}

	public void setSunSensorZP(float sunSensorZP) {
		this.sunSensorZP = sunSensorZP;
	}

	public float getSunSensorZM() {
		return sunSensorZM;
	}

	public void setSunSensorZM(float sunSensorZM) {
		this.sunSensorZM = sunSensorZM;
	}

	public int getTotalPhotoCurrent() {
		return totalPhotoCurrent;
	}

	public void setTotalPhotoCurrent(int totalPhotoCurrent) {
		this.totalPhotoCurrent = totalPhotoCurrent;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

}
