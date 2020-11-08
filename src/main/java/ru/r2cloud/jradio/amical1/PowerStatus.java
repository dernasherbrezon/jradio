package ru.r2cloud.jradio.amical1;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class PowerStatus {

	private long timestamp;
	private Integer voltageIn;
	private Integer voltageSolar;
	private Integer currentIn;
	private Integer powerIn;
	private Integer powerPeak;
	private Integer temperatureCpu;
	private Integer voltageCpu;

	private Long bootNumber;
	private Integer inputVoltage;
	private Integer inputCurrent;
	private Integer inputPower;
	private Integer peakPower;
	private Integer solarVoltage;

	public PowerStatus() {
		// do nothing
	}

	public PowerStatus(String[] parts) throws UncorrectableException {
		if (parts[1].equalsIgnoreCase("MN")) {
			timestamp = Long.valueOf(parts[2]);
			voltageIn = Integer.parseInt(parts[3]);
			voltageSolar = Integer.parseInt(parts[4]);
			currentIn = Integer.parseInt(parts[5]);
			powerIn = Integer.parseInt(parts[6]);
			powerPeak = Integer.parseInt(parts[7]);
			temperatureCpu = Integer.parseInt(parts[8]);
			voltageCpu = Integer.parseInt(parts[9]);
		} else if (parts[1].equalsIgnoreCase("LOG")) {
			timestamp = Long.valueOf(parts[2]);
			bootNumber = Long.valueOf(parts[3]);
			inputVoltage = Integer.parseInt(parts[4]);
			inputCurrent = Integer.parseInt(parts[5]);
			inputPower = Integer.parseInt(parts[6]);
			peakPower = Integer.parseInt(parts[7]);
			solarVoltage = Integer.parseInt(parts[8]);
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

	public Integer getVoltageIn() {
		return voltageIn;
	}

	public void setVoltageIn(Integer voltageIn) {
		this.voltageIn = voltageIn;
	}

	public Integer getVoltageSolar() {
		return voltageSolar;
	}

	public void setVoltageSolar(Integer voltageSolar) {
		this.voltageSolar = voltageSolar;
	}

	public Integer getCurrentIn() {
		return currentIn;
	}

	public void setCurrentIn(Integer currentIn) {
		this.currentIn = currentIn;
	}

	public Integer getPowerIn() {
		return powerIn;
	}

	public void setPowerIn(Integer powerIn) {
		this.powerIn = powerIn;
	}

	public Integer getPowerPeak() {
		return powerPeak;
	}

	public void setPowerPeak(Integer powerPeak) {
		this.powerPeak = powerPeak;
	}

	public Integer getTemperatureCpu() {
		return temperatureCpu;
	}

	public void setTemperatureCpu(Integer temperatureCpu) {
		this.temperatureCpu = temperatureCpu;
	}

	public Integer getVoltageCpu() {
		return voltageCpu;
	}

	public void setVoltageCpu(Integer voltageCpu) {
		this.voltageCpu = voltageCpu;
	}

	public Long getBootNumber() {
		return bootNumber;
	}

	public void setBootNumber(Long bootNumber) {
		this.bootNumber = bootNumber;
	}

	public Integer getInputVoltage() {
		return inputVoltage;
	}

	public void setInputVoltage(Integer inputVoltage) {
		this.inputVoltage = inputVoltage;
	}

	public Integer getInputCurrent() {
		return inputCurrent;
	}

	public void setInputCurrent(Integer inputCurrent) {
		this.inputCurrent = inputCurrent;
	}

	public Integer getInputPower() {
		return inputPower;
	}

	public void setInputPower(Integer inputPower) {
		this.inputPower = inputPower;
	}

	public Integer getPeakPower() {
		return peakPower;
	}

	public void setPeakPower(Integer peakPower) {
		this.peakPower = peakPower;
	}

	public Integer getSolarVoltage() {
		return solarVoltage;
	}

	public void setSolarVoltage(Integer solarVoltage) {
		this.solarVoltage = solarVoltage;
	}

}
