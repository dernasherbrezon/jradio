package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class EpsTelemetryv2 {

	private float mcuTemperature;
	private EpsSensorStatus status;
	private float internalINACurrent;
	private float internalINAVoltage;
	private float unregulatedINACurrent;
	private float unregulatedINAVoltage;
	private float batteryGGVoltage;
	private float batteryINAVoltage;
	private float batteryINACurrent;
	private float batteryGGCapacity;
	private float batteryGGTemperature;
	private float batteryTMP20Temperature;

	private float bus4Current;
	private float bus3Current;
	private float bus2Current;
	private float bus1Current;
	private float bus4Voltage;
	private float bus3Voltage;
	private float bus2Voltage;
	private float bus1Voltage;

	private float panelYpCurrent;
	private float panelYmCurrent;
	private float panelXpCurrent;
	private float panelXmCurrent;
	private float panelYpVoltage;
	private float panelYmVoltage;
	private float panelXpVoltage;
	private float panelXmVoltage;
	private float panelYpTemperature;
	private float panelYmTemperature;
	private float panelXpTemperature;
	private float panelXmTemperature;

	private float mpptYpCurrent;
	private float mpptYmCurrent;
	private float mpptXpCurrent;
	private float mpptXmCurrent;
	private float mpptYpVoltage;
	private float mpptYmVoltage;
	private float mpptXpVoltage;
	private float mpptXmVoltage;

	private float cellYpCurrent;
	private float cellYmCurrent;
	private float cellXpCurrent;
	private float cellXmCurrent;
	private float cellYpVoltage;
	private float cellYmVoltage;
	private float cellXpVoltage;

	public EpsTelemetryv2() {
		// do nothing
	}

	public EpsTelemetryv2(DataInputStream dis) throws IOException {
		mcuTemperature = dis.readShort() / 10.0f;
		status = new EpsSensorStatus(dis);
		internalINACurrent = dis.readShort() / 1000.0f;
		internalINAVoltage = dis.readUnsignedShort() / 1000.0f;
		unregulatedINACurrent = dis.readShort() / 1000.0f;
		unregulatedINAVoltage = dis.readUnsignedShort() / 1000.0f;
		batteryGGVoltage = dis.readUnsignedShort() / 1000.0f;
		batteryINAVoltage = dis.readUnsignedShort() / 1000.0f;
		batteryINACurrent = dis.readShort() / 1000.0f;
		batteryGGCapacity = dis.readUnsignedShort() / 10.0f;
		batteryGGTemperature = dis.readShort() / 10.0f;
		batteryTMP20Temperature = dis.readShort() / 10.0f;

		bus4Current = dis.readShort() / 1000.0f;
		bus3Current = dis.readShort() / 1000.0f;
		bus2Current = dis.readShort() / 1000.0f;
		bus1Current = dis.readShort() / 1000.0f;
		bus4Voltage = dis.readUnsignedShort() / 1000.0f;
		bus3Voltage = dis.readUnsignedShort() / 1000.0f;
		bus2Voltage = dis.readUnsignedShort() / 1000.0f;
		bus1Voltage = dis.readUnsignedShort() / 1000.0f;

		panelYpCurrent = dis.readShort() / 1000.0f;
		panelYmCurrent = dis.readShort() / 1000.0f;
		panelXpCurrent = dis.readShort() / 1000.0f;
		panelXmCurrent = dis.readShort() / 1000.0f;
		panelYpVoltage = dis.readUnsignedShort() / 1000.0f;
		panelYmVoltage = dis.readUnsignedShort() / 1000.0f;
		panelXpVoltage = dis.readUnsignedShort() / 1000.0f;
		panelXmVoltage = dis.readUnsignedShort() / 1000.0f;
		panelYpTemperature = dis.readShort() / 10.0f;
		panelYmTemperature = dis.readShort() / 10.0f;
		panelXpTemperature = dis.readShort() / 10.0f;
		panelXmTemperature = dis.readShort() / 10.0f;

		mpptYpCurrent = dis.readShort() / 1000.0f;
		mpptYmCurrent = dis.readShort() / 1000.0f;
		mpptXpCurrent = dis.readShort() / 1000.0f;
		mpptXmCurrent = dis.readShort() / 1000.0f;
		mpptYpVoltage = dis.readUnsignedShort() / 1000.0f;
		mpptYmVoltage = dis.readUnsignedShort() / 1000.0f;
		mpptXpVoltage = dis.readUnsignedShort() / 1000.0f;
		mpptXmVoltage = dis.readUnsignedShort() / 1000.0f;

		cellYpCurrent = dis.readShort() / 1000.0f;
		cellYmCurrent = dis.readShort() / 1000.0f;
		cellXpCurrent = dis.readShort() / 1000.0f;
		cellXmCurrent = dis.readShort() / 1000.0f;
		cellYpVoltage = dis.readUnsignedShort() / 1000.0f;
		cellYmVoltage = dis.readUnsignedShort() / 1000.0f;
		cellXpVoltage = dis.readUnsignedShort() / 1000.0f;
	}

	public float getMcuTemperature() {
		return mcuTemperature;
	}

	public void setMcuTemperature(float mcuTemperature) {
		this.mcuTemperature = mcuTemperature;
	}

	public EpsSensorStatus getStatus() {
		return status;
	}

	public void setStatus(EpsSensorStatus status) {
		this.status = status;
	}

	public float getInternalINACurrent() {
		return internalINACurrent;
	}

	public void setInternalINACurrent(float internalINACurrent) {
		this.internalINACurrent = internalINACurrent;
	}

	public float getInternalINAVoltage() {
		return internalINAVoltage;
	}

	public void setInternalINAVoltage(float internalINAVoltage) {
		this.internalINAVoltage = internalINAVoltage;
	}

	public float getUnregulatedINACurrent() {
		return unregulatedINACurrent;
	}

	public void setUnregulatedINACurrent(float unregulatedINACurrent) {
		this.unregulatedINACurrent = unregulatedINACurrent;
	}

	public float getUnregulatedINAVoltage() {
		return unregulatedINAVoltage;
	}

	public void setUnregulatedINAVoltage(float unregulatedINAVoltage) {
		this.unregulatedINAVoltage = unregulatedINAVoltage;
	}

	public float getBatteryGGVoltage() {
		return batteryGGVoltage;
	}

	public void setBatteryGGVoltage(float batteryGGVoltage) {
		this.batteryGGVoltage = batteryGGVoltage;
	}

	public float getBatteryINAVoltage() {
		return batteryINAVoltage;
	}

	public void setBatteryINAVoltage(float batteryINAVoltage) {
		this.batteryINAVoltage = batteryINAVoltage;
	}

	public float getBatteryINACurrent() {
		return batteryINACurrent;
	}

	public void setBatteryINACurrent(float batteryINACurrent) {
		this.batteryINACurrent = batteryINACurrent;
	}

	public float getBatteryGGCapacity() {
		return batteryGGCapacity;
	}

	public void setBatteryGGCapacity(float batteryGGCapacity) {
		this.batteryGGCapacity = batteryGGCapacity;
	}

	public float getBatteryGGTemperature() {
		return batteryGGTemperature;
	}

	public void setBatteryGGTemperature(float batteryGGTemperature) {
		this.batteryGGTemperature = batteryGGTemperature;
	}

	public float getBatteryTMP20Temperature() {
		return batteryTMP20Temperature;
	}

	public void setBatteryTMP20Temperature(float batteryTMP20Temperature) {
		this.batteryTMP20Temperature = batteryTMP20Temperature;
	}

	public float getBus4Current() {
		return bus4Current;
	}

	public void setBus4Current(float bus4Current) {
		this.bus4Current = bus4Current;
	}

	public float getBus3Current() {
		return bus3Current;
	}

	public void setBus3Current(float bus3Current) {
		this.bus3Current = bus3Current;
	}

	public float getBus2Current() {
		return bus2Current;
	}

	public void setBus2Current(float bus2Current) {
		this.bus2Current = bus2Current;
	}

	public float getBus1Current() {
		return bus1Current;
	}

	public void setBus1Current(float bus1Current) {
		this.bus1Current = bus1Current;
	}

	public float getBus4Voltage() {
		return bus4Voltage;
	}

	public void setBus4Voltage(float bus4Voltage) {
		this.bus4Voltage = bus4Voltage;
	}

	public float getBus3Voltage() {
		return bus3Voltage;
	}

	public void setBus3Voltage(float bus3Voltage) {
		this.bus3Voltage = bus3Voltage;
	}

	public float getBus2Voltage() {
		return bus2Voltage;
	}

	public void setBus2Voltage(float bus2Voltage) {
		this.bus2Voltage = bus2Voltage;
	}

	public float getBus1Voltage() {
		return bus1Voltage;
	}

	public void setBus1Voltage(float bus1Voltage) {
		this.bus1Voltage = bus1Voltage;
	}

	public float getPanelYpCurrent() {
		return panelYpCurrent;
	}

	public void setPanelYpCurrent(float panelYpCurrent) {
		this.panelYpCurrent = panelYpCurrent;
	}

	public float getPanelYmCurrent() {
		return panelYmCurrent;
	}

	public void setPanelYmCurrent(float panelYmCurrent) {
		this.panelYmCurrent = panelYmCurrent;
	}

	public float getPanelXpCurrent() {
		return panelXpCurrent;
	}

	public void setPanelXpCurrent(float panelXpCurrent) {
		this.panelXpCurrent = panelXpCurrent;
	}

	public float getPanelXmCurrent() {
		return panelXmCurrent;
	}

	public void setPanelXmCurrent(float panelXmCurrent) {
		this.panelXmCurrent = panelXmCurrent;
	}

	public float getPanelYpVoltage() {
		return panelYpVoltage;
	}

	public void setPanelYpVoltage(float panelYpVoltage) {
		this.panelYpVoltage = panelYpVoltage;
	}

	public float getPanelYmVoltage() {
		return panelYmVoltage;
	}

	public void setPanelYmVoltage(float panelYmVoltage) {
		this.panelYmVoltage = panelYmVoltage;
	}

	public float getPanelXpVoltage() {
		return panelXpVoltage;
	}

	public void setPanelXpVoltage(float panelXpVoltage) {
		this.panelXpVoltage = panelXpVoltage;
	}

	public float getPanelXmVoltage() {
		return panelXmVoltage;
	}

	public void setPanelXmVoltage(float panelXmVoltage) {
		this.panelXmVoltage = panelXmVoltage;
	}

	public float getPanelYpTemperature() {
		return panelYpTemperature;
	}

	public void setPanelYpTemperature(float panelYpTemperature) {
		this.panelYpTemperature = panelYpTemperature;
	}

	public float getPanelYmTemperature() {
		return panelYmTemperature;
	}

	public void setPanelYmTemperature(float panelYmTemperature) {
		this.panelYmTemperature = panelYmTemperature;
	}

	public float getPanelXpTemperature() {
		return panelXpTemperature;
	}

	public void setPanelXpTemperature(float panelXpTemperature) {
		this.panelXpTemperature = panelXpTemperature;
	}

	public float getPanelXmTemperature() {
		return panelXmTemperature;
	}

	public void setPanelXmTemperature(float panelXmTemperature) {
		this.panelXmTemperature = panelXmTemperature;
	}

	public float getMpptYpCurrent() {
		return mpptYpCurrent;
	}

	public void setMpptYpCurrent(float mpptYpCurrent) {
		this.mpptYpCurrent = mpptYpCurrent;
	}

	public float getMpptYmCurrent() {
		return mpptYmCurrent;
	}

	public void setMpptYmCurrent(float mpptYmCurrent) {
		this.mpptYmCurrent = mpptYmCurrent;
	}

	public float getMpptXpCurrent() {
		return mpptXpCurrent;
	}

	public void setMpptXpCurrent(float mpptXpCurrent) {
		this.mpptXpCurrent = mpptXpCurrent;
	}

	public float getMpptXmCurrent() {
		return mpptXmCurrent;
	}

	public void setMpptXmCurrent(float mpptXmCurrent) {
		this.mpptXmCurrent = mpptXmCurrent;
	}

	public float getMpptYpVoltage() {
		return mpptYpVoltage;
	}

	public void setMpptYpVoltage(float mpptYpVoltage) {
		this.mpptYpVoltage = mpptYpVoltage;
	}

	public float getMpptYmVoltage() {
		return mpptYmVoltage;
	}

	public void setMpptYmVoltage(float mpptYmVoltage) {
		this.mpptYmVoltage = mpptYmVoltage;
	}

	public float getMpptXpVoltage() {
		return mpptXpVoltage;
	}

	public void setMpptXpVoltage(float mpptXpVoltage) {
		this.mpptXpVoltage = mpptXpVoltage;
	}

	public float getMpptXmVoltage() {
		return mpptXmVoltage;
	}

	public void setMpptXmVoltage(float mpptXmVoltage) {
		this.mpptXmVoltage = mpptXmVoltage;
	}

	public float getCellYpCurrent() {
		return cellYpCurrent;
	}

	public void setCellYpCurrent(float cellYpCurrent) {
		this.cellYpCurrent = cellYpCurrent;
	}

	public float getCellYmCurrent() {
		return cellYmCurrent;
	}

	public void setCellYmCurrent(float cellYmCurrent) {
		this.cellYmCurrent = cellYmCurrent;
	}

	public float getCellXpCurrent() {
		return cellXpCurrent;
	}

	public void setCellXpCurrent(float cellXpCurrent) {
		this.cellXpCurrent = cellXpCurrent;
	}

	public float getCellXmCurrent() {
		return cellXmCurrent;
	}

	public void setCellXmCurrent(float cellXmCurrent) {
		this.cellXmCurrent = cellXmCurrent;
	}

	public float getCellYpVoltage() {
		return cellYpVoltage;
	}

	public void setCellYpVoltage(float cellYpVoltage) {
		this.cellYpVoltage = cellYpVoltage;
	}

	public float getCellYmVoltage() {
		return cellYmVoltage;
	}

	public void setCellYmVoltage(float cellYmVoltage) {
		this.cellYmVoltage = cellYmVoltage;
	}

	public float getCellXpVoltage() {
		return cellXpVoltage;
	}

	public void setCellXpVoltage(float cellXpVoltage) {
		this.cellXpVoltage = cellXpVoltage;
	}

}
