package ru.r2cloud.jradio.amical1;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class CommunicationModuleStatus {

	private long timestamp;
	private Integer cpuVoltage;
	private Integer batteryVoltage;
	private Integer cpuTemperature;
	private Integer amplifierTemperature;
	private CommunicationFlags flags;

	private Integer currentRssi;
	private Integer lastRssi;
	private Integer afcOffset;

	public CommunicationModuleStatus() {
		// do nothing
	}

	public CommunicationModuleStatus(String[] parts) throws UncorrectableException {
		if (parts[1].equalsIgnoreCase("RL")) {
			timestamp = Long.valueOf(parts[2]);
			cpuVoltage = Integer.valueOf(parts[3]);
			batteryVoltage = Integer.valueOf(parts[4]);
			cpuTemperature = Integer.valueOf(parts[5]);
			amplifierTemperature = Integer.valueOf(parts[6]);
			flags = new CommunicationFlags(Integer.parseInt(parts[7].substring(2), 16));
		} else if (parts[1].equalsIgnoreCase("MS")) {
			timestamp = Long.valueOf(parts[2]);
			currentRssi = Integer.valueOf(parts[3]);
			lastRssi = Integer.valueOf(parts[4]);
			afcOffset = Integer.valueOf(parts[5]);
		} else {
			throw new UncorrectableException("unknown type");
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getCpuVoltage() {
		return cpuVoltage;
	}

	public void setCpuVoltage(Integer cpuVoltage) {
		this.cpuVoltage = cpuVoltage;
	}

	public Integer getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(Integer batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Integer getCpuTemperature() {
		return cpuTemperature;
	}

	public void setCpuTemperature(Integer cpuTemperature) {
		this.cpuTemperature = cpuTemperature;
	}

	public Integer getAmplifierTemperature() {
		return amplifierTemperature;
	}

	public void setAmplifierTemperature(Integer amplifierTemperature) {
		this.amplifierTemperature = amplifierTemperature;
	}

	public CommunicationFlags getFlags() {
		return flags;
	}

	public void setFlags(CommunicationFlags flags) {
		this.flags = flags;
	}

	public Integer getCurrentRssi() {
		return currentRssi;
	}

	public void setCurrentRssi(Integer currentRssi) {
		this.currentRssi = currentRssi;
	}

	public Integer getLastRssi() {
		return lastRssi;
	}

	public void setLastRssi(Integer lastRssi) {
		this.lastRssi = lastRssi;
	}

	public Integer getAfcOffset() {
		return afcOffset;
	}

	public void setAfcOffset(Integer afcOffset) {
		this.afcOffset = afcOffset;
	}

}
