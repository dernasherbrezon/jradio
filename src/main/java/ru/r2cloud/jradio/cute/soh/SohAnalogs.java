package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

public class SohAnalogs {

	private float box1Temp;
	private float busVoltage;
	private float batteryVoltage;
	private float batteryCurrent;

	public SohAnalogs() {
		// do nothing
	}

	public SohAnalogs(DataInputStream dis) throws IOException {
		box1Temp = dis.readShort() * 0.005f;
		busVoltage = dis.readUnsignedShort() * 0.001f;
		batteryVoltage = dis.readUnsignedShort() * 0.002f;
		batteryCurrent = dis.readShort() * 0.002f;
	}

	public float getBox1Temp() {
		return box1Temp;
	}

	public void setBox1Temp(float box1Temp) {
		this.box1Temp = box1Temp;
	}

	public float getBusVoltage() {
		return busVoltage;
	}

	public void setBusVoltage(float busVoltage) {
		this.busVoltage = busVoltage;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(float batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

}
