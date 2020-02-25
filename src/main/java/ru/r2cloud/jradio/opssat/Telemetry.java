package ru.r2cloud.jradio.opssat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Telemetry {

	private float boardTemperature;
	private float paTemperature;
	private short lastReceivedRssi;
	private short lastReceivedRfError;
	private long numberOfTxPackets;
	private long numberOfRxPackets;
	private long numberOfTxBytes;
	private long numberOfRxBytes;
	private int activeSystemConfiguration;
	private int numberOfReboots;
	private long causeOfReboot;
	private long timestampOfTheLastValidPacket;
	private short currentBackgroundRssi;
	private int totalTxDutyTime;
	private long numberOfTxPacketsTotal;
	private long numberOfRxPacketsTotal;
	private long numberOfTxBytesTotal;
	private long numberOfRxBytesTotal;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(DataInputStream dis) throws IOException {
		boardTemperature = dis.readShort() / 10.0f;
		paTemperature = dis.readShort() / 10.0f;
		lastReceivedRssi = dis.readShort();
		lastReceivedRfError = dis.readShort();
		numberOfTxPackets = StreamUtils.readUnsignedInt(dis);
		numberOfRxPackets = StreamUtils.readUnsignedInt(dis);
		numberOfTxBytes = StreamUtils.readUnsignedInt(dis);
		numberOfRxBytes = StreamUtils.readUnsignedInt(dis);
		activeSystemConfiguration = dis.readUnsignedByte();
		numberOfReboots = dis.readUnsignedShort();
		causeOfReboot = StreamUtils.readUnsignedInt(dis);
		timestampOfTheLastValidPacket = StreamUtils.readUnsignedInt(dis);
		currentBackgroundRssi = dis.readShort();
		totalTxDutyTime = dis.readUnsignedByte();
		numberOfTxPacketsTotal = StreamUtils.readUnsignedInt(dis);
		numberOfRxPacketsTotal = StreamUtils.readUnsignedInt(dis);
		numberOfTxBytesTotal = StreamUtils.readUnsignedInt(dis);
		numberOfRxBytesTotal = StreamUtils.readUnsignedInt(dis);
	}

	public float getBoardTemperature() {
		return boardTemperature;
	}

	public void setBoardTemperature(float boardTemperature) {
		this.boardTemperature = boardTemperature;
	}

	public float getPaTemperature() {
		return paTemperature;
	}

	public void setPaTemperature(float paTemperature) {
		this.paTemperature = paTemperature;
	}

	public short getLastReceivedRssi() {
		return lastReceivedRssi;
	}

	public void setLastReceivedRssi(short lastReceivedRssi) {
		this.lastReceivedRssi = lastReceivedRssi;
	}

	public short getLastReceivedRfError() {
		return lastReceivedRfError;
	}

	public void setLastReceivedRfError(short lastReceivedRfError) {
		this.lastReceivedRfError = lastReceivedRfError;
	}

	public long getNumberOfTxPackets() {
		return numberOfTxPackets;
	}

	public void setNumberOfTxPackets(long numberOfTxPackets) {
		this.numberOfTxPackets = numberOfTxPackets;
	}

	public long getNumberOfRxPackets() {
		return numberOfRxPackets;
	}

	public void setNumberOfRxPackets(long numberOfRxPackets) {
		this.numberOfRxPackets = numberOfRxPackets;
	}

	public long getNumberOfTxBytes() {
		return numberOfTxBytes;
	}

	public void setNumberOfTxBytes(long numberOfTxBytes) {
		this.numberOfTxBytes = numberOfTxBytes;
	}

	public long getNumberOfRxBytes() {
		return numberOfRxBytes;
	}

	public void setNumberOfRxBytes(long numberOfRxBytes) {
		this.numberOfRxBytes = numberOfRxBytes;
	}

	public int getActiveSystemConfiguration() {
		return activeSystemConfiguration;
	}

	public void setActiveSystemConfiguration(int activeSystemConfiguration) {
		this.activeSystemConfiguration = activeSystemConfiguration;
	}

	public int getNumberOfReboots() {
		return numberOfReboots;
	}

	public void setNumberOfReboots(int numberOfReboots) {
		this.numberOfReboots = numberOfReboots;
	}

	public long getCauseOfReboot() {
		return causeOfReboot;
	}

	public void setCauseOfReboot(long causeOfReboot) {
		this.causeOfReboot = causeOfReboot;
	}

	public long getTimestampOfTheLastValidPacket() {
		return timestampOfTheLastValidPacket;
	}

	public void setTimestampOfTheLastValidPacket(long timestampOfTheLastValidPacket) {
		this.timestampOfTheLastValidPacket = timestampOfTheLastValidPacket;
	}

	public short getCurrentBackgroundRssi() {
		return currentBackgroundRssi;
	}

	public void setCurrentBackgroundRssi(short currentBackgroundRssi) {
		this.currentBackgroundRssi = currentBackgroundRssi;
	}

	public int getTotalTxDutyTime() {
		return totalTxDutyTime;
	}

	public void setTotalTxDutyTime(int totalTxDutyTime) {
		this.totalTxDutyTime = totalTxDutyTime;
	}

	public long getNumberOfTxPacketsTotal() {
		return numberOfTxPacketsTotal;
	}

	public void setNumberOfTxPacketsTotal(long numberOfTxPacketsTotal) {
		this.numberOfTxPacketsTotal = numberOfTxPacketsTotal;
	}

	public long getNumberOfRxPacketsTotal() {
		return numberOfRxPacketsTotal;
	}

	public void setNumberOfRxPacketsTotal(long numberOfRxPacketsTotal) {
		this.numberOfRxPacketsTotal = numberOfRxPacketsTotal;
	}

	public long getNumberOfTxBytesTotal() {
		return numberOfTxBytesTotal;
	}

	public void setNumberOfTxBytesTotal(long numberOfTxBytesTotal) {
		this.numberOfTxBytesTotal = numberOfTxBytesTotal;
	}

	public long getNumberOfRxBytesTotal() {
		return numberOfRxBytesTotal;
	}

	public void setNumberOfRxBytesTotal(long numberOfRxBytesTotal) {
		this.numberOfRxBytesTotal = numberOfRxBytesTotal;
	}

}
