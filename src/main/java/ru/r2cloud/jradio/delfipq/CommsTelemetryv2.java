package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class CommsTelemetryv2 {

	private float mcuTemperature;
	private CommsSensorStatus sensorsStatus;
	private float voltage;
	private float current;
	private float temperature;
	private float receiverRSSI;
	private float transmitVoltage;
	private float transmitCurrent;
	private float amplifierVoltage;
	private float amplifierCurrent;
	private float phasingBoardTemperature;

	public CommsTelemetryv2() {
		// do nothing
	}

	public CommsTelemetryv2(DataInputStream dis) throws IOException {
		mcuTemperature = dis.readShort() / 10.0f;
		sensorsStatus = new CommsSensorStatus(dis);
		voltage = dis.readUnsignedShort() / 1000.0f;
		current = dis.readShort() / 1000.0f;
		temperature = dis.readShort() / 10.0f;
		receiverRSSI = dis.readShort() - 21.0f;
		transmitVoltage = dis.readUnsignedShort() / 1000.0f;
		transmitCurrent = dis.readShort() / 1000.0f;
		amplifierVoltage = dis.readUnsignedShort() / 1000.0f;
		amplifierCurrent = dis.readShort() / 1000.0f;
		phasingBoardTemperature = dis.readShort() / 10.0f;
	}

	public float getMcuTemperature() {
		return mcuTemperature;
	}

	public void setMcuTemperature(float mcuTemperature) {
		this.mcuTemperature = mcuTemperature;
	}

	public CommsSensorStatus getSensorsStatus() {
		return sensorsStatus;
	}

	public void setSensorsStatus(CommsSensorStatus sensorsStatus) {
		this.sensorsStatus = sensorsStatus;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getReceiverRSSI() {
		return receiverRSSI;
	}

	public void setReceiverRSSI(float receiverRSSI) {
		this.receiverRSSI = receiverRSSI;
	}

	public float getTransmitVoltage() {
		return transmitVoltage;
	}

	public void setTransmitVoltage(float transmitVoltage) {
		this.transmitVoltage = transmitVoltage;
	}

	public float getTransmitCurrent() {
		return transmitCurrent;
	}

	public void setTransmitCurrent(float transmitCurrent) {
		this.transmitCurrent = transmitCurrent;
	}

	public float getAmplifierVoltage() {
		return amplifierVoltage;
	}

	public void setAmplifierVoltage(float amplifierVoltage) {
		this.amplifierVoltage = amplifierVoltage;
	}

	public float getAmplifierCurrent() {
		return amplifierCurrent;
	}

	public void setAmplifierCurrent(float amplifierCurrent) {
		this.amplifierCurrent = amplifierCurrent;
	}

	public float getPhasingBoardTemperature() {
		return phasingBoardTemperature;
	}

	public void setPhasingBoardTemperature(float phasingBoardTemperature) {
		this.phasingBoardTemperature = phasingBoardTemperature;
	}

}
