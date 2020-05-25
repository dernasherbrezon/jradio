package ru.r2cloud.jradio.painani1;

import java.io.DataInputStream;
import java.io.IOException;

public class ShortTelemetry {

	private int internalTemperature;
	private int paTemperature;
	private float current3V3;
	private float voltage3V3;
	private float current5V;
	private float voltage5V;

	public ShortTelemetry() {
		// do nothing
	}

	public ShortTelemetry(DataInputStream dis) throws IOException {
		dis.skipBytes(4);
		internalTemperature = dis.readByte();
		paTemperature = dis.readByte();
		current3V3 = dis.readShort() * 3 * 0.000001f;
		voltage3V3 = dis.readUnsignedShort() * 4 * 0.001f;
		current5V = dis.readShort() * 62 * 0.000001f;
		voltage5V = dis.readUnsignedShort() * 4 * 0.001f;
	}

	public int getInternalTemperature() {
		return internalTemperature;
	}

	public void setInternalTemperature(int internalTemperature) {
		this.internalTemperature = internalTemperature;
	}

	public int getPaTemperature() {
		return paTemperature;
	}

	public void setPaTemperature(int paTemperature) {
		this.paTemperature = paTemperature;
	}

	public float getCurrent3V3() {
		return current3V3;
	}

	public void setCurrent3V3(float current3v3) {
		current3V3 = current3v3;
	}

	public float getVoltage3V3() {
		return voltage3V3;
	}

	public void setVoltage3V3(float voltage3v3) {
		voltage3V3 = voltage3v3;
	}

	public float getCurrent5V() {
		return current5V;
	}

	public void setCurrent5V(float current5v) {
		current5V = current5v;
	}

	public float getVoltage5V() {
		return voltage5V;
	}

	public void setVoltage5V(float voltage5v) {
		voltage5V = voltage5v;
	}

}
