package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Tid {

	private long timestamp;
	private float temperature;
	private float voltage;
	private int[] radfetVoltage;
	private int measurementSerial;

	public Tid() {
		// do nothing
	}

	public Tid(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		temperature = dis.readShort() / 10.0f;
		voltage = dis.readUnsignedShort() / 1000.0f;
		radfetVoltage = new int[2];
		for (int i = 0; i < radfetVoltage.length; i++) {
			radfetVoltage[i] = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		}
		measurementSerial = dis.readUnsignedShort();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public int[] getRadfetVoltage() {
		return radfetVoltage;
	}

	public void setRadfetVoltage(int[] radfetVoltage) {
		this.radfetVoltage = radfetVoltage;
	}

	public int getMeasurementSerial() {
		return measurementSerial;
	}

	public void setMeasurementSerial(int measurementSerial) {
		this.measurementSerial = measurementSerial;
	}

}
