package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Com {

	private long timestamp;
	private int swrBridge;
	private byte lastRxRssi;
	private int spectrumAnalyzerStatus;
	private float activeComVoltage;
	private float activeComTemperature;
	private float activeComSpectrumAnalyzerTemperature;

	public Com() {
		// do nothing
	}

	public Com(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		swrBridge = dis.readUnsignedByte();
		lastRxRssi = dis.readByte();
		spectrumAnalyzerStatus = dis.readUnsignedByte();
		activeComVoltage = dis.readUnsignedShort() / 1000.0f;
		activeComTemperature = dis.readShort() / 10.0f;
		activeComSpectrumAnalyzerTemperature = dis.readShort() / 10.0f;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getSwrBridge() {
		return swrBridge;
	}

	public void setSwrBridge(int swrBridge) {
		this.swrBridge = swrBridge;
	}

	public byte getLastRxRssi() {
		return lastRxRssi;
	}

	public void setLastRxRssi(byte lastRxRssi) {
		this.lastRxRssi = lastRxRssi;
	}

	public int getSpectrumAnalyzerStatus() {
		return spectrumAnalyzerStatus;
	}

	public void setSpectrumAnalyzerStatus(int spectrumAnalyzerStatus) {
		this.spectrumAnalyzerStatus = spectrumAnalyzerStatus;
	}

	public float getActiveComVoltage() {
		return activeComVoltage;
	}

	public void setActiveComVoltage(float activeComVoltage) {
		this.activeComVoltage = activeComVoltage;
	}

	public float getActiveComTemperature() {
		return activeComTemperature;
	}

	public void setActiveComTemperature(float activeComTemperature) {
		this.activeComTemperature = activeComTemperature;
	}

	public float getActiveComSpectrumAnalyzerTemperature() {
		return activeComSpectrumAnalyzerTemperature;
	}

	public void setActiveComSpectrumAnalyzerTemperature(float activeComSpectrumAnalyzerTemperature) {
		this.activeComSpectrumAnalyzerTemperature = activeComSpectrumAnalyzerTemperature;
	}

}
