package ru.r2cloud.jradio.amical1;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class M1Type {

	private long timestamp;
	private Long bootNumber;
	private Long uptime;
	private Integer cpuVoltage;
	private Integer cpuTemperature;

	private M1Flags flags;

	public M1Type() {
		// do nothing
	}

	public M1Type(String[] parts) throws UncorrectableException {
		if (parts[1].equalsIgnoreCase("LOG")) {
			timestamp = Long.valueOf(parts[2]);
			bootNumber = Long.valueOf(parts[3]);
			uptime = Long.valueOf(parts[4]);
			cpuVoltage = Integer.valueOf(parts[5]);
			cpuTemperature = Integer.valueOf(parts[6]);
		} else if (parts[1].equalsIgnoreCase("FLAGS")) {
			timestamp = Long.valueOf(parts[2]);
			flags = new M1Flags(Long.parseLong(parts[3].substring(2), 16));
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

	public Long getBootNumber() {
		return bootNumber;
	}

	public void setBootNumber(Long bootNumber) {
		this.bootNumber = bootNumber;
	}

	public Long getUptime() {
		return uptime;
	}

	public void setUptime(Long uptime) {
		this.uptime = uptime;
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

	public M1Flags getFlags() {
		return flags;
	}

	public void setFlags(M1Flags flags) {
		this.flags = flags;
	}

}
