package ru.r2cloud.jradio.amical1;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class ComputingUnitStatus {

	private long timestamp;
	private Integer cpuVoltage;
	private Integer cpuTemperature;
	private ComputingUnitFlags flags;

	public ComputingUnitStatus() {
		// do nothing
	}

	public ComputingUnitStatus(String[] parts) throws UncorrectableException {
		if (parts[1].equalsIgnoreCase("LOG")) {
			timestamp = Long.valueOf(parts[2]);
			cpuVoltage = Integer.parseInt(parts[3]);
			cpuTemperature = Integer.parseInt(parts[4]);
			flags = new ComputingUnitFlags(Integer.parseInt(parts[5].substring(2), 16));
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

	public Integer getCpuTemperature() {
		return cpuTemperature;
	}

	public void setCpuTemperature(Integer cpuTemperature) {
		this.cpuTemperature = cpuTemperature;
	}

	public ComputingUnitFlags getFlags() {
		return flags;
	}

	public void setFlags(ComputingUnitFlags flags) {
		this.flags = flags;
	}

}
