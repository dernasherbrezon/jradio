package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PcuBat {

	private long timestamp;
	private float batteryVoltage;
	private int batteryChargeCurrent;
	private int batteryDischargeCurrent;

	private boolean batteryChargeOvercurrent;
	private boolean batteryChargeOvervoltage;
	private boolean batteryDischargeOvercurrent;
	private boolean batteryDischargeOvervoltage;
	private boolean batteryChargeEnabled;
	private boolean batteryDischargeEnabled;

	public PcuBat() {
		// do nothing
	}

	public PcuBat(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		batteryVoltage = dis.readUnsignedShort() / 1000.0f;
		batteryChargeCurrent = dis.readUnsignedShort();
		batteryDischargeCurrent = dis.readUnsignedShort();
		int b = dis.readUnsignedByte();
		batteryChargeOvercurrent = ((b >> 7) & 0x1) > 0;
		batteryChargeOvervoltage = ((b >> 6) & 0x1) > 0;
		batteryDischargeOvercurrent = ((b >> 5) & 0x1) > 0;
		batteryDischargeOvervoltage = ((b >> 4) & 0x1) > 0;
		batteryChargeEnabled = ((b >> 3) & 0x1) > 0;
		batteryDischargeEnabled = ((b >> 2) & 0x1) > 0;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getBatteryChargeCurrent() {
		return batteryChargeCurrent;
	}

	public void setBatteryChargeCurrent(int batteryChargeCurrent) {
		this.batteryChargeCurrent = batteryChargeCurrent;
	}

	public int getBatteryDischargeCurrent() {
		return batteryDischargeCurrent;
	}

	public void setBatteryDischargeCurrent(int batteryDischargeCurrent) {
		this.batteryDischargeCurrent = batteryDischargeCurrent;
	}

	public boolean isBatteryChargeOvercurrent() {
		return batteryChargeOvercurrent;
	}

	public void setBatteryChargeOvercurrent(boolean batteryChargeOvercurrent) {
		this.batteryChargeOvercurrent = batteryChargeOvercurrent;
	}

	public boolean isBatteryChargeOvervoltage() {
		return batteryChargeOvervoltage;
	}

	public void setBatteryChargeOvervoltage(boolean batteryChargeOvervoltage) {
		this.batteryChargeOvervoltage = batteryChargeOvervoltage;
	}

	public boolean isBatteryDischargeOvercurrent() {
		return batteryDischargeOvercurrent;
	}

	public void setBatteryDischargeOvercurrent(boolean batteryDischargeOvercurrent) {
		this.batteryDischargeOvercurrent = batteryDischargeOvercurrent;
	}

	public boolean isBatteryDischargeOvervoltage() {
		return batteryDischargeOvervoltage;
	}

	public void setBatteryDischargeOvervoltage(boolean batteryDischargeOvervoltage) {
		this.batteryDischargeOvervoltage = batteryDischargeOvervoltage;
	}

	public boolean isBatteryChargeEnabled() {
		return batteryChargeEnabled;
	}

	public void setBatteryChargeEnabled(boolean batteryChargeEnabled) {
		this.batteryChargeEnabled = batteryChargeEnabled;
	}

	public boolean isBatteryDischargeEnabled() {
		return batteryDischargeEnabled;
	}

	public void setBatteryDischargeEnabled(boolean batteryDischargeEnabled) {
		this.batteryDischargeEnabled = batteryDischargeEnabled;
	}

}
