package ru.r2cloud.jradio.delfic3;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class AwssBlock {

	private int sunPresence;
	private int sampleNumber;
	private int receiverStatus;
	private int sensorStatus;
	private int sensorId;
	private int transmissionErrors;
	private int supplyVoltage;
	private int temperature;
	private int adcValueQ1;
	private int adcValueQ2;
	private int adcValueQ3;
	private int adcValueQ4;

	public AwssBlock() {
		// do nothing
	}

	public AwssBlock(LsbBitInputStream bis) throws IOException {
		sunPresence = bis.readBitsAsInt(1);
		sampleNumber = bis.readBitsAsInt(2);
		receiverStatus = bis.readBitsAsInt(1);
		sensorStatus = bis.readBitsAsInt(1);
		sensorId = bis.readBitsAsInt(3);
		transmissionErrors = bis.readBitsAsInt(8);
		supplyVoltage = bis.readBitsAsInt(8);
		temperature = bis.readBitsAsInt(8);
		adcValueQ1 = bis.readBitsAsInt(12);
		adcValueQ2 = bis.readBitsAsInt(12);
		adcValueQ3 = bis.readBitsAsInt(12);
		adcValueQ4 = bis.readBitsAsInt(12);
	}

	public int getSunPresence() {
		return sunPresence;
	}

	public void setSunPresence(int sunPresence) {
		this.sunPresence = sunPresence;
	}

	public int getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(int sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public int getReceiverStatus() {
		return receiverStatus;
	}

	public void setReceiverStatus(int receiverStatus) {
		this.receiverStatus = receiverStatus;
	}

	public int getSensorStatus() {
		return sensorStatus;
	}

	public void setSensorStatus(int sensorStatus) {
		this.sensorStatus = sensorStatus;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	public int getTransmissionErrors() {
		return transmissionErrors;
	}

	public void setTransmissionErrors(int transmissionErrors) {
		this.transmissionErrors = transmissionErrors;
	}

	public int getSupplyVoltage() {
		return supplyVoltage;
	}

	public void setSupplyVoltage(int supplyVoltage) {
		this.supplyVoltage = supplyVoltage;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getAdcValueQ1() {
		return adcValueQ1;
	}

	public void setAdcValueQ1(int adcValueQ1) {
		this.adcValueQ1 = adcValueQ1;
	}

	public int getAdcValueQ2() {
		return adcValueQ2;
	}

	public void setAdcValueQ2(int adcValueQ2) {
		this.adcValueQ2 = adcValueQ2;
	}

	public int getAdcValueQ3() {
		return adcValueQ3;
	}

	public void setAdcValueQ3(int adcValueQ3) {
		this.adcValueQ3 = adcValueQ3;
	}

	public int getAdcValueQ4() {
		return adcValueQ4;
	}

	public void setAdcValueQ4(int adcValueQ4) {
		this.adcValueQ4 = adcValueQ4;
	}

}
