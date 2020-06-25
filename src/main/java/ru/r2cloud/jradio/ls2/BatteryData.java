package ru.r2cloud.jradio.ls2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BatteryData {

	private float current;
	private float voltage;
	private float temperature;

	private boolean overVoltage;
	private boolean underVoltage;
	private boolean chargeOverCurrent;
	private boolean dischargeOverCurrent;
	private boolean chargingActive;
	private boolean dischargingActive;
	private boolean chargingEnabled;
	private boolean dischargingEnabled;

	private boolean overVolt;
	private boolean underVolt;
	private boolean dcCommand;
	private boolean ddCommand;
	private boolean overVoltRecovery;
	private boolean cfgDisabled;
	private boolean cmdDisabled;
	private boolean ignored;

	public BatteryData() {
		// do nothing
	}

	public BatteryData(LittleEndianDataInputStream dis) throws IOException {
		current = dis.readByte() / 128f;
		voltage = dis.readUnsignedByte() / 32f;
		temperature = dis.readUnsignedByte() * 0.5f - 75;

		int raw = dis.readUnsignedByte();
		overVoltage = (raw & 0x1) > 0;
		underVoltage = ((raw >> 1) & 0x1) > 0;
		chargeOverCurrent = ((raw >> 2) & 0x1) > 0;
		dischargeOverCurrent = ((raw >> 3) & 0x1) > 0;
		chargingActive = ((raw >> 4) & 0x1) > 0;
		dischargingActive = ((raw >> 5) & 0x1) > 0;
		chargingEnabled = ((raw >> 6) & 0x1) > 0;
		dischargingEnabled = ((raw >> 7) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		overVolt = (raw & 0x1) > 0;
		underVolt = ((raw >> 1) & 0x1) > 0;
		dcCommand = ((raw >> 2) & 0x1) > 0;
		ddCommand = ((raw >> 3) & 0x1) > 0;
		overVoltRecovery = ((raw >> 4) & 0x1) > 0;
		cfgDisabled = ((raw >> 5) & 0x1) > 0;
		cmdDisabled = ((raw >> 6) & 0x1) > 0;
		ignored = ((raw >> 7) & 0x1) > 0;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public boolean isOverVoltage() {
		return overVoltage;
	}

	public void setOverVoltage(boolean overVoltage) {
		this.overVoltage = overVoltage;
	}

	public boolean isUnderVoltage() {
		return underVoltage;
	}

	public void setUnderVoltage(boolean underVoltage) {
		this.underVoltage = underVoltage;
	}

	public boolean isChargeOverCurrent() {
		return chargeOverCurrent;
	}

	public void setChargeOverCurrent(boolean chargeOverCurrent) {
		this.chargeOverCurrent = chargeOverCurrent;
	}

	public boolean isDischargeOverCurrent() {
		return dischargeOverCurrent;
	}

	public void setDischargeOverCurrent(boolean dischargeOverCurrent) {
		this.dischargeOverCurrent = dischargeOverCurrent;
	}

	public boolean isChargingActive() {
		return chargingActive;
	}

	public void setChargingActive(boolean chargingActive) {
		this.chargingActive = chargingActive;
	}

	public boolean isDischargingActive() {
		return dischargingActive;
	}

	public void setDischargingActive(boolean dischargingActive) {
		this.dischargingActive = dischargingActive;
	}

	public boolean isChargingEnabled() {
		return chargingEnabled;
	}

	public void setChargingEnabled(boolean chargingEnabled) {
		this.chargingEnabled = chargingEnabled;
	}

	public boolean isDischargingEnabled() {
		return dischargingEnabled;
	}

	public void setDischargingEnabled(boolean dischargingEnabled) {
		this.dischargingEnabled = dischargingEnabled;
	}

	public boolean isOverVolt() {
		return overVolt;
	}

	public void setOverVolt(boolean overVolt) {
		this.overVolt = overVolt;
	}

	public boolean isUnderVolt() {
		return underVolt;
	}

	public void setUnderVolt(boolean underVolt) {
		this.underVolt = underVolt;
	}

	public boolean isDcCommand() {
		return dcCommand;
	}

	public void setDcCommand(boolean dcCommand) {
		this.dcCommand = dcCommand;
	}

	public boolean isDdCommand() {
		return ddCommand;
	}

	public void setDdCommand(boolean ddCommand) {
		this.ddCommand = ddCommand;
	}

	public boolean isOverVoltRecovery() {
		return overVoltRecovery;
	}

	public void setOverVoltRecovery(boolean overVoltRecovery) {
		this.overVoltRecovery = overVoltRecovery;
	}

	public boolean isCfgDisabled() {
		return cfgDisabled;
	}

	public void setCfgDisabled(boolean cfgDisabled) {
		this.cfgDisabled = cfgDisabled;
	}

	public boolean isCmdDisabled() {
		return cmdDisabled;
	}

	public void setCmdDisabled(boolean cmdDisabled) {
		this.cmdDisabled = cmdDisabled;
	}

	public boolean isIgnored() {
		return ignored;
	}

	public void setIgnored(boolean ignored) {
		this.ignored = ignored;
	}

}
