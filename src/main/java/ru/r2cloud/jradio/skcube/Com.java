package ru.r2cloud.jradio.skcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Com {

	private long timestamp;
	private int fwVersion;
	private int activeCOM;
	private int digipeaterMode;
	private int numberOfReboots;
	private int outputReflectedPower;
	private int outputForwardPower;
	private int outputReflectedPowerCW;
	private int outputForwardPowerCW;
	private int rssi;
	private int rssiNoise;
	private int mcuTemperature;
	private int paTemperature;
	private int cwBeaconSent;
	private int packetsSent;
	private int correctPacketsReceived;
	private int brokenPacketReceived;
	private int comProtocolError;
	private int gsProtocolError;
	private int txDisableStatus;
	private int orbitTime;
	private int timeslotStart;
	private int timeslotEnd;

	public Com() {
		// do nothing
	}

	public Com(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		fwVersion = dis.readUnsignedShort();
		activeCOM = dis.readUnsignedByte();
		digipeaterMode = dis.readUnsignedByte();
		numberOfReboots = dis.readUnsignedShort();
		outputReflectedPower = dis.readUnsignedShort();
		outputForwardPower = dis.readUnsignedShort();
		outputReflectedPowerCW = dis.readUnsignedShort();
		outputForwardPowerCW = dis.readUnsignedShort();
		rssi = dis.readUnsignedShort();
		rssiNoise = dis.readUnsignedShort();
		mcuTemperature = dis.readByte();
		paTemperature = dis.readByte();
		cwBeaconSent = dis.readUnsignedShort();
		packetsSent = dis.readUnsignedShort();
		correctPacketsReceived = dis.readUnsignedShort();
		brokenPacketReceived = dis.readUnsignedShort();
		comProtocolError = dis.readUnsignedShort();
		gsProtocolError = dis.readUnsignedShort();
		txDisableStatus = dis.readUnsignedByte();
		orbitTime = dis.readUnsignedByte();
		timeslotStart = dis.readUnsignedByte();
		timeslotEnd = dis.readUnsignedByte();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getFwVersion() {
		return fwVersion;
	}

	public void setFwVersion(int fwVersion) {
		this.fwVersion = fwVersion;
	}

	public int getActiveCOM() {
		return activeCOM;
	}

	public void setActiveCOM(int activeCOM) {
		this.activeCOM = activeCOM;
	}

	public int getDigipeaterMode() {
		return digipeaterMode;
	}

	public void setDigipeaterMode(int digipeaterMode) {
		this.digipeaterMode = digipeaterMode;
	}

	public int getNumberOfReboots() {
		return numberOfReboots;
	}

	public void setNumberOfReboots(int numberOfReboots) {
		this.numberOfReboots = numberOfReboots;
	}

	public int getOutputReflectedPower() {
		return outputReflectedPower;
	}

	public void setOutputReflectedPower(int outputReflectedPower) {
		this.outputReflectedPower = outputReflectedPower;
	}

	public int getOutputForwardPower() {
		return outputForwardPower;
	}

	public void setOutputForwardPower(int outputForwardPower) {
		this.outputForwardPower = outputForwardPower;
	}

	public int getOutputReflectedPowerCW() {
		return outputReflectedPowerCW;
	}

	public void setOutputReflectedPowerCW(int outputReflectedPowerCW) {
		this.outputReflectedPowerCW = outputReflectedPowerCW;
	}

	public int getOutputForwardPowerCW() {
		return outputForwardPowerCW;
	}

	public void setOutputForwardPowerCW(int outputForwardPowerCW) {
		this.outputForwardPowerCW = outputForwardPowerCW;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public int getRssiNoise() {
		return rssiNoise;
	}

	public void setRssiNoise(int rssiNoise) {
		this.rssiNoise = rssiNoise;
	}

	public int getMcuTemperature() {
		return mcuTemperature;
	}

	public void setMcuTemperature(int mcuTemperature) {
		this.mcuTemperature = mcuTemperature;
	}

	public int getPaTemperature() {
		return paTemperature;
	}

	public void setPaTemperature(int paTemperature) {
		this.paTemperature = paTemperature;
	}

	public int getCwBeaconSent() {
		return cwBeaconSent;
	}

	public void setCwBeaconSent(int cwBeaconSent) {
		this.cwBeaconSent = cwBeaconSent;
	}

	public int getPacketsSent() {
		return packetsSent;
	}

	public void setPacketsSent(int packetsSent) {
		this.packetsSent = packetsSent;
	}

	public int getCorrectPacketsReceived() {
		return correctPacketsReceived;
	}

	public void setCorrectPacketsReceived(int correctPacketsReceived) {
		this.correctPacketsReceived = correctPacketsReceived;
	}

	public int getBrokenPacketReceived() {
		return brokenPacketReceived;
	}

	public void setBrokenPacketReceived(int brokenPacketReceived) {
		this.brokenPacketReceived = brokenPacketReceived;
	}

	public int getComProtocolError() {
		return comProtocolError;
	}

	public void setComProtocolError(int comProtocolError) {
		this.comProtocolError = comProtocolError;
	}

	public int getGsProtocolError() {
		return gsProtocolError;
	}

	public void setGsProtocolError(int gsProtocolError) {
		this.gsProtocolError = gsProtocolError;
	}

	public int getTxDisableStatus() {
		return txDisableStatus;
	}

	public void setTxDisableStatus(int txDisableStatus) {
		this.txDisableStatus = txDisableStatus;
	}

	public int getOrbitTime() {
		return orbitTime;
	}

	public void setOrbitTime(int orbitTime) {
		this.orbitTime = orbitTime;
	}

	public int getTimeslotStart() {
		return timeslotStart;
	}

	public void setTimeslotStart(int timeslotStart) {
		this.timeslotStart = timeslotStart;
	}

	public int getTimeslotEnd() {
		return timeslotEnd;
	}

	public void setTimeslotEnd(int timeslotEnd) {
		this.timeslotEnd = timeslotEnd;
	}

}
