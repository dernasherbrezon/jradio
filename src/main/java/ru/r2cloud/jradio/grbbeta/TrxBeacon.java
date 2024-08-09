package ru.r2cloud.jradio.grbbeta;

import java.util.regex.Pattern;

public class TrxBeacon {
	private static final Pattern COMMA = Pattern.compile(",");

	private long uptimeSinceReset;
	private long uptime;
	private long radioBootCount;
	private long rfSegmentResetCount;
	private float radioMcuTemperature;
	private float rfChipTemperature;
	private float rfPowerAmplifierTemperature;
	private long digipeaterMessageCount;
	private String lastDigipeaterCallsign;
	private long rxDataPackets;
	private long txDataPackets;
	private float actualRssi;
	private float rssiPreamble;

	public TrxBeacon() {
		// do nothing
	}

	public TrxBeacon(String message) {
		String[] parts = COMMA.split(message.trim());
		int i = 1;
		uptimeSinceReset = Long.valueOf(parts[i++]);
		uptime = Long.valueOf(parts[i++]);
		radioBootCount = Long.valueOf(parts[i++]);
		rfSegmentResetCount = Long.valueOf(parts[i++]);
		radioMcuTemperature = Long.valueOf(parts[i++]) * 0.01f;
		rfChipTemperature = Long.valueOf(parts[i++]) * 0.01f;
		rfPowerAmplifierTemperature = Long.valueOf(parts[i++]) * 0.01f;
		digipeaterMessageCount = Long.valueOf(parts[i++]);
		lastDigipeaterCallsign = parts[i++].trim();
		if (lastDigipeaterCallsign.length() == 0) {
			lastDigipeaterCallsign = null;
		}
		rxDataPackets = Long.valueOf(parts[i++]);
		txDataPackets = Long.valueOf(parts[i++]);
		actualRssi = Long.valueOf(parts[i++]) / 2.0f - 134;
		rssiPreamble = Long.valueOf(parts[i++]) / 2.0f - 134;
	}

	public long getUptimeSinceReset() {
		return uptimeSinceReset;
	}

	public void setUptimeSinceReset(long uptimeSinceReset) {
		this.uptimeSinceReset = uptimeSinceReset;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
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

	public float getRfChipTemperature() {
		return rfChipTemperature;
	}

	public void setRfChipTemperature(float rfChipTemperature) {
		this.rfChipTemperature = rfChipTemperature;
	}

	public float getRfPowerAmplifierTemperature() {
		return rfPowerAmplifierTemperature;
	}

	public void setRfPowerAmplifierTemperature(float rfPowerAmplifierTemperature) {
		this.rfPowerAmplifierTemperature = rfPowerAmplifierTemperature;
	}

	public long getDigipeaterMessageCount() {
		return digipeaterMessageCount;
	}

	public void setDigipeaterMessageCount(long digipeaterMessageCount) {
		this.digipeaterMessageCount = digipeaterMessageCount;
	}

	public String getLastDigipeaterCallsign() {
		return lastDigipeaterCallsign;
	}

	public void setLastDigipeaterCallsign(String lastDigipeaterCallsign) {
		this.lastDigipeaterCallsign = lastDigipeaterCallsign;
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

	public float getActualRssi() {
		return actualRssi;
	}

	public void setActualRssi(float actualRssi) {
		this.actualRssi = actualRssi;
	}

	public float getRssiPreamble() {
		return rssiPreamble;
	}

	public void setRssiPreamble(float rssiPreamble) {
		this.rssiPreamble = rssiPreamble;
	}

}
