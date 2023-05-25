package ru.r2cloud.jradio.cirbe;

import java.io.DataInputStream;
import java.io.IOException;

public class SohAnalogs {

	private float batteryVoltage;

	public SohAnalogs() {
		// do nothing
	}

	public SohAnalogs(DataInputStream dis) throws IOException {
		batteryVoltage = dis.readUnsignedShort() / 499.99997500000126f;
	}
	
	public float getBatteryVoltage() {
		return batteryVoltage;
	}
	
	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

}
