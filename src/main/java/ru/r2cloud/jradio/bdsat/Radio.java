package ru.r2cloud.jradio.bdsat;

public class Radio {

	private long uptime;
	private long totalUptime;
	private long bootCount;
	private long rfSegmentResetCount;
	private float radioMcuTemp;
	private float rfChipTemp;
	private float rfPowerAmplifierTemp;
	private long digipeaterMessageCount;
	private String lastSenderCallsign;
	private long rxDataPackets;
	private long txDataPackets;
	private float rssi;
	private float rssiAferPreamble;

	public Radio() {
		// do nothing
	}

	public Radio(String[] parts) {
		int i = 1;
		uptime = Long.valueOf(parts[i++]);
		totalUptime = Long.valueOf(parts[i++]);
		bootCount = Long.valueOf(parts[i++]);
		rfSegmentResetCount = Long.valueOf(parts[i++]);
		radioMcuTemp = Long.valueOf(parts[i++]) * 0.01f;
		rfChipTemp = Long.valueOf(parts[i++]) * 0.01f;
		rfPowerAmplifierTemp = Long.valueOf(parts[i++]) * 0.01f;
		digipeaterMessageCount = Long.valueOf(parts[i++]);
		lastSenderCallsign = parts[i++];
		rxDataPackets = Long.valueOf(parts[i++]);
		txDataPackets = Long.valueOf(parts[i++]);
		rssi = Long.valueOf(parts[i++]) / 2.0f - 134;
		rssiAferPreamble = Long.valueOf(parts[i++]) / 2.0f - 134;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getTotalUptime() {
		return totalUptime;
	}

	public void setTotalUptime(long totalUptime) {
		this.totalUptime = totalUptime;
	}

	public long getBootCount() {
		return bootCount;
	}

	public void setBootCount(long bootCount) {
		this.bootCount = bootCount;
	}

	public long getRfSegmentResetCount() {
		return rfSegmentResetCount;
	}

	public void setRfSegmentResetCount(long rfSegmentResetCount) {
		this.rfSegmentResetCount = rfSegmentResetCount;
	}

	public float getRadioMcuTemp() {
		return radioMcuTemp;
	}

	public void setRadioMcuTemp(float radioMcuTemp) {
		this.radioMcuTemp = radioMcuTemp;
	}

	public float getRfChipTemp() {
		return rfChipTemp;
	}

	public void setRfChipTemp(float rfChipTemp) {
		this.rfChipTemp = rfChipTemp;
	}

	public float getRfPowerAmplifierTemp() {
		return rfPowerAmplifierTemp;
	}

	public void setRfPowerAmplifierTemp(float rfPowerAmplifierTemp) {
		this.rfPowerAmplifierTemp = rfPowerAmplifierTemp;
	}

	public long getDigipeaterMessageCount() {
		return digipeaterMessageCount;
	}

	public void setDigipeaterMessageCount(long digipeaterMessageCount) {
		this.digipeaterMessageCount = digipeaterMessageCount;
	}

	public String getLastSenderCallsign() {
		return lastSenderCallsign;
	}

	public void setLastSenderCallsign(String lastSenderCallsign) {
		this.lastSenderCallsign = lastSenderCallsign;
	}

	public long getRxDataPackets() {
		return rxDataPackets;
	}

	public void setRxDataPackets(long rxDataPackets) {
		this.rxDataPackets = rxDataPackets;
	}

	public long getTxDataPackets() {
		return txDataPackets;
	}

	public void setTxDataPackets(long txDataPackets) {
		this.txDataPackets = txDataPackets;
	}

	public float getRssi() {
		return rssi;
	}

	public void setRssi(float rssi) {
		this.rssi = rssi;
	}

	public float getRssiAferPreamble() {
		return rssiAferPreamble;
	}

	public void setRssiAferPreamble(float rssiAferPreamble) {
		this.rssiAferPreamble = rssiAferPreamble;
	}

}
