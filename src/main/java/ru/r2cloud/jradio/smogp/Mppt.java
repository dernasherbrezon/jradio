package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Mppt {

	private long timestamp;
	private float temperature;
	private float lightSensor;
	private int inputCurrent;
	private float inputVoltage;
	private int outputCurrent;
	private float outputVoltage;
	private int panelNumber;
	private AntennaStatus panelStatus;

	public Mppt() {
		// do nothing
	}

	public Mppt(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		temperature = dis.readShort() / 10.0f;
		lightSensor = dis.readUnsignedShort() / 1000.0f;
		inputCurrent = dis.readUnsignedShort();
		inputVoltage = dis.readUnsignedShort() / 1000.0f;
		outputCurrent = dis.readUnsignedShort();
		outputVoltage = dis.readUnsignedShort() / 1000.0f;
		int status = dis.readUnsignedByte();
		panelNumber = (status >> 5) & 0b111;
		panelStatus = AntennaStatus.valueOfId((status >> 3) & 0b11);
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

	public float getLightSensor() {
		return lightSensor;
	}

	public void setLightSensor(float lightSensor) {
		this.lightSensor = lightSensor;
	}

	public int getInputCurrent() {
		return inputCurrent;
	}

	public void setInputCurrent(int inputCurrent) {
		this.inputCurrent = inputCurrent;
	}

	public float getInputVoltage() {
		return inputVoltage;
	}

	public void setInputVoltage(float inputVoltage) {
		this.inputVoltage = inputVoltage;
	}

	public int getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(int outputCurrent) {
		this.outputCurrent = outputCurrent;
	}

	public float getOutputVoltage() {
		return outputVoltage;
	}

	public void setOutputVoltage(float outputVoltage) {
		this.outputVoltage = outputVoltage;
	}

	public int getPanelNumber() {
		return panelNumber;
	}

	public void setPanelNumber(int panelNumber) {
		this.panelNumber = panelNumber;
	}

	public AntennaStatus getPanelStatus() {
		return panelStatus;
	}

	public void setPanelStatus(AntennaStatus panelStatus) {
		this.panelStatus = panelStatus;
	}

}
