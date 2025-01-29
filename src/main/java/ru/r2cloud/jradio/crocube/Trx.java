package ru.r2cloud.jradio.crocube;

public class Trx {

	private long uptimeSinceReset; // seconds
	private long uptimeTotal; // seconds
	private long radioBootCount;
	private long rfSegmentResetCount;
	private float radioMcuTemperature; // deg Celsius
	private float frChipTemperature; // deg Celsius
	private float rfPowerAmplifierTemperature; // deg Celsius
	private long forwardedMessageCount;
	private String lastCallsign;
	private long rxPackets;
	private long txPackets;
	private float actualRssi;
	private float valueRssi;

	public Trx() {
		// do nothing
	}

	public Trx(String[] parts) {
		uptimeSinceReset = Long.valueOf(parts[1]);
		uptimeTotal = Long.valueOf(parts[2]);
		radioBootCount = Long.valueOf(parts[3]);
		rfSegmentResetCount = Long.valueOf(parts[4]);
		radioMcuTemperature = Long.valueOf(parts[5]) * 0.01f;
		frChipTemperature = Long.valueOf(parts[6]) * 0.01f;
		rfPowerAmplifierTemperature = Long.valueOf(parts[7]) * 0.01f;
		forwardedMessageCount = Long.valueOf(parts[8]);
		lastCallsign = parts[9].trim();
		if (lastCallsign.length() == 0) {
			lastCallsign = null;
		}
		rxPackets = Long.valueOf(parts[10]);
		txPackets = Long.valueOf(parts[11]);
		actualRssi = Long.valueOf(parts[12]) / 2.0f - 134.0f;
		valueRssi = Long.valueOf(parts[13]) / 2.0f - 134.0f;
	}

	public long getUptimeSinceReset() {
		return uptimeSinceReset;
	}

	public void setUptimeSinceReset(long uptimeSinceReset) {
		this.uptimeSinceReset = uptimeSinceReset;
	}

	public long getUptimeTotal() {
		return uptimeTotal;
	}

	public void setUptimeTotal(long uptimeTotal) {
		this.uptimeTotal = uptimeTotal;
	}

	public long getRadioBootCount() {
		return radioBootCount;
	}

	public void setRadioBootCount(long radioBootCount) {
		this.radioBootCount = radioBootCount;
	}

	public long getRfSegmentResetCount() {
		return rfSegmentResetCount;
	}

	public void setRfSegmentResetCount(long rfSegmentResetCount) {
		this.rfSegmentResetCount = rfSegmentResetCount;
	}

	public float getRadioMcuTemperature() {
		return radioMcuTemperature;
	}

	public void setRadioMcuTemperature(float radioMcuTemperature) {
		this.radioMcuTemperature = radioMcuTemperature;
	}

	public float getFrChipTemperature() {
		return frChipTemperature;
	}

	public void setFrChipTemperature(float frChipTemperature) {
		this.frChipTemperature = frChipTemperature;
	}

	public float getRfPowerAmplifierTemperature() {
		return rfPowerAmplifierTemperature;
	}

	public void setRfPowerAmplifierTemperature(float rfPowerAmplifierTemperature) {
		this.rfPowerAmplifierTemperature = rfPowerAmplifierTemperature;
	}

	public long getForwardedMessageCount() {
		return forwardedMessageCount;
	}

	public void setForwardedMessageCount(long forwardedMessageCount) {
		this.forwardedMessageCount = forwardedMessageCount;
	}

	public String getLastCallsign() {
		return lastCallsign;
	}

	public void setLastCallsign(String lastCallsign) {
		this.lastCallsign = lastCallsign;
	}

	public long getRxPackets() {
		return rxPackets;
	}

	public void setRxPackets(long rxPackets) {
		this.rxPackets = rxPackets;
	}

	public long getTxPackets() {
		return txPackets;
	}

	public void setTxPackets(long txPackets) {
		this.txPackets = txPackets;
	}

	public float getActualRssi() {
		return actualRssi;
	}

	public void setActualRssi(float actualRssi) {
		this.actualRssi = actualRssi;
	}

	public float getValueRssi() {
		return valueRssi;
	}

	public void setValueRssi(float valueRssi) {
		this.valueRssi = valueRssi;
	}

}
