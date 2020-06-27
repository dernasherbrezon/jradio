package ru.r2cloud.jradio.bsusat1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class EpsShortTelemetry {

	private int systemTime;
	private int adcCorrectness;
	private float adc1Temperature;
	private float adc2Temperature;
	private float stepUpCurrent;
	private float stepUpVoltage;
	private float afterBqCurrent;
	private float batteryVoltage;
	private float system5vVoltage;
	private float system3v3Voltage;
	private float epsCurrent;
	private float obcCurrent;
	private float modem1Current;
	private float modem2Current;
	private float solarPanelsVoltage;
	private float sideXCurrent;
	private float sidePyCurrent;
	private float sideNyCurrent;
	private float sidePzCurrent;
	private float sideNzCurrent;

	public EpsShortTelemetry() {
		// do nothing
	}

	public EpsShortTelemetry(LittleEndianDataInputStream dis) throws IOException {
		LittleEndianBitInputStream bis = new LittleEndianBitInputStream(dis);
		systemTime = bis.readUnsignedShort();
		adcCorrectness = bis.readUnsignedInt(2);
		int raw = bis.readUnsignedInt(12);
		if (raw > 1500) {
			raw = -1 * (0x0fff - raw);
		}
		adc1Temperature = raw * 0.125f;
		raw = bis.readUnsignedInt(12);
		if (raw > 1500) {
			raw = -1 * (0x0fff - raw);
		}
		adc2Temperature = raw * 0.125f;
		stepUpCurrent = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.3f / 20 * 1000;
		stepUpVoltage = bis.readUnsignedInt(12) * 2.5f / 4096 * 4;
		afterBqCurrent = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.05f / 50 * 1000;
		batteryVoltage = bis.readUnsignedInt(12) * 2.5f / 4096 * 3.75f;
		system5vVoltage = bis.readUnsignedInt(12) * 2.5f / 4096 * 2.5f;
		system3v3Voltage = bis.readUnsignedInt(12) * 2.5f / 4096 * 2f;
		epsCurrent = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.3f / 50 * 1000;
		obcCurrent = bis.readUnsignedInt(12) * 2.5f / 1024 / 0.3f / 50 * 1000;
		modem1Current = bis.readUnsignedInt(12) * 2.5f / 1024 / 0.1f / 20 * 1000;
		modem2Current = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.1f / 20 * 1000;
		solarPanelsVoltage = bis.readUnsignedInt(12) * 2.5f / 4096 * 2.2f;
		sideXCurrent = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.05f / 50 * 1000;
		sidePyCurrent = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.05f / 50 * 1000;
		sideNyCurrent = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.05f / 50 * 1000;
		sidePzCurrent = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.05f / 50 * 1000;
		sideNzCurrent = bis.readUnsignedInt(12) * 2.5f / 4096 / 0.05f / 50 * 1000;
	}

	public int getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(int systemTime) {
		this.systemTime = systemTime;
	}

	public int getAdcCorrectness() {
		return adcCorrectness;
	}

	public void setAdcCorrectness(int adcCorrectness) {
		this.adcCorrectness = adcCorrectness;
	}

	public float getAdc1Temperature() {
		return adc1Temperature;
	}

	public void setAdc1Temperature(float adc1Temperature) {
		this.adc1Temperature = adc1Temperature;
	}

	public float getAdc2Temperature() {
		return adc2Temperature;
	}

	public void setAdc2Temperature(float adc2Temperature) {
		this.adc2Temperature = adc2Temperature;
	}

	public float getStepUpCurrent() {
		return stepUpCurrent;
	}

	public void setStepUpCurrent(float stepUpCurrent) {
		this.stepUpCurrent = stepUpCurrent;
	}

	public float getStepUpVoltage() {
		return stepUpVoltage;
	}

	public void setStepUpVoltage(float stepUpVoltage) {
		this.stepUpVoltage = stepUpVoltage;
	}

	public float getAfterBqCurrent() {
		return afterBqCurrent;
	}

	public void setAfterBqCurrent(float afterBqCurrent) {
		this.afterBqCurrent = afterBqCurrent;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getSystem5vVoltage() {
		return system5vVoltage;
	}

	public void setSystem5vVoltage(float system5vVoltage) {
		this.system5vVoltage = system5vVoltage;
	}

	public float getSystem3v3Voltage() {
		return system3v3Voltage;
	}

	public void setSystem3v3Voltage(float system3v3Voltage) {
		this.system3v3Voltage = system3v3Voltage;
	}

	public float getEpsCurrent() {
		return epsCurrent;
	}

	public void setEpsCurrent(float epsCurrent) {
		this.epsCurrent = epsCurrent;
	}

	public float getObcCurrent() {
		return obcCurrent;
	}

	public void setObcCurrent(float obcCurrent) {
		this.obcCurrent = obcCurrent;
	}

	public float getModem1Current() {
		return modem1Current;
	}

	public void setModem1Current(float modem1Current) {
		this.modem1Current = modem1Current;
	}

	public float getModem2Current() {
		return modem2Current;
	}

	public void setModem2Current(float modem2Current) {
		this.modem2Current = modem2Current;
	}

	public float getSolarPanelsVoltage() {
		return solarPanelsVoltage;
	}

	public void setSolarPanelsVoltage(float solarPanelsVoltage) {
		this.solarPanelsVoltage = solarPanelsVoltage;
	}

	public float getSideXCurrent() {
		return sideXCurrent;
	}

	public void setSideXCurrent(float sideXCurrent) {
		this.sideXCurrent = sideXCurrent;
	}

	public float getSidePyCurrent() {
		return sidePyCurrent;
	}

	public void setSidePyCurrent(float sidePyCurrent) {
		this.sidePyCurrent = sidePyCurrent;
	}

	public float getSideNyCurrent() {
		return sideNyCurrent;
	}

	public void setSideNyCurrent(float sideNyCurrent) {
		this.sideNyCurrent = sideNyCurrent;
	}

	public float getSidePzCurrent() {
		return sidePzCurrent;
	}

	public void setSidePzCurrent(float sidePzCurrent) {
		this.sidePzCurrent = sidePzCurrent;
	}

	public float getSideNzCurrent() {
		return sideNzCurrent;
	}

	public void setSideNzCurrent(float sideNzCurrent) {
		this.sideNzCurrent = sideNzCurrent;
	}
	
}
