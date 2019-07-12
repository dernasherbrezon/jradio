package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class PmmError1 {

	private boolean solarPanel1Sensor1TemperatureOutOfRange;
	private boolean solarPanel1Sensor2TemperatureOutOfRange;
	private boolean solarPanel2Sensor1TemperatureOutOfRange;
	private boolean solarPanel2Sensor2TemperatureOutOfRange;
	private boolean solarPanel3Sensor1TemperatureOutOfRange;
	private boolean solarPanel3Sensor2TemperatureOutOfRange;
	private boolean batteryPack1CurrentOutOfRange;
	private boolean batteryPack2CurrentOutOfRange;
	private boolean batteryPack3CurrentOutOfRange;
	private boolean batteryPack4CurrentOutOfRange;
	private boolean batteryPack5CurrentOutOfRange;
	private boolean batteryPack6CurrentOutOfRange;
	private boolean batterypack1Sensor1TemperatureOutOfRange;
	private boolean batterypack1Sensor2TemperatureOutOfRange;
	private boolean batterypack2Sensor1TemperatureOutOfRange;
	private boolean batterypack2Sensor2TemperatureOutOfRange;
	private boolean batterypack3Sensor1TemperatureOutOfRange;
	private boolean batterypack3Sensor2TemperatureOutOfRange;
	private boolean batterypack4Sensor1TemperatureOutOfRange;
	private boolean batterypack4Sensor2TemperatureOutOfRange;
	private boolean batterypack5Sensor1TemperatureOutOfRange;
	private boolean batterypack5Sensor2TemperatureOutOfRange;
	private boolean batterypack6Sensor1TemperatureOutOfRange;
	private boolean batterypack6Sensor2TemperatureOutOfRange;

	private boolean mainBusVoltageoOutOfRange;
	private boolean powerBoardSensor1TemperatureOutOfRange;
	private boolean powerBoardSensor2TemperatureOutOfRange;

	public PmmError1(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		solarPanel1Sensor1TemperatureOutOfRange = ((raw >> 7) & 0x1) > 0;
		solarPanel1Sensor2TemperatureOutOfRange = ((raw >> 6) & 0x1) > 0;
		solarPanel2Sensor1TemperatureOutOfRange = ((raw >> 5) & 0x1) > 0;
		solarPanel2Sensor2TemperatureOutOfRange = ((raw >> 4) & 0x1) > 0;
		solarPanel3Sensor1TemperatureOutOfRange = ((raw >> 3) & 0x1) > 0;
		solarPanel3Sensor2TemperatureOutOfRange = ((raw >> 2) & 0x1) > 0;
		batteryPack1CurrentOutOfRange = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		batteryPack2CurrentOutOfRange = ((raw >> 7) & 0x1) > 0;
		batteryPack3CurrentOutOfRange = ((raw >> 6) & 0x1) > 0;
		batteryPack4CurrentOutOfRange = ((raw >> 5) & 0x1) > 0;
		batteryPack5CurrentOutOfRange = ((raw >> 4) & 0x1) > 0;
		batteryPack6CurrentOutOfRange = ((raw >> 3) & 0x1) > 0;
		batterypack1Sensor1TemperatureOutOfRange = ((raw >> 2) & 0x1) > 0;
		batterypack1Sensor2TemperatureOutOfRange = ((raw >> 1) & 0x1) > 0;
		batterypack2Sensor1TemperatureOutOfRange = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		batterypack2Sensor2TemperatureOutOfRange = ((raw >> 7) & 0x1) > 0;
		batterypack3Sensor1TemperatureOutOfRange = ((raw >> 6) & 0x1) > 0;
		batterypack3Sensor2TemperatureOutOfRange = ((raw >> 5) & 0x1) > 0;
		batterypack4Sensor1TemperatureOutOfRange = ((raw >> 4) & 0x1) > 0;
		batterypack4Sensor2TemperatureOutOfRange = ((raw >> 3) & 0x1) > 0;
		batterypack5Sensor1TemperatureOutOfRange = ((raw >> 2) & 0x1) > 0;
		batterypack5Sensor2TemperatureOutOfRange = ((raw >> 1) & 0x1) > 0;
		batterypack6Sensor1TemperatureOutOfRange = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		batterypack6Sensor2TemperatureOutOfRange = ((raw >> 7) & 0x1) > 0;

		mainBusVoltageoOutOfRange = ((raw >> 5) & 0x1) > 0;
		powerBoardSensor1TemperatureOutOfRange = ((raw >> 4) & 0x1) > 0;
		powerBoardSensor2TemperatureOutOfRange = ((raw >> 3) & 0x1) > 0;
	}

	public boolean isSolarPanel1Sensor1TemperatureOutOfRange() {
		return solarPanel1Sensor1TemperatureOutOfRange;
	}

	public void setSolarPanel1Sensor1TemperatureOutOfRange(boolean solarPanel1Sensor1TemperatureOutOfRange) {
		this.solarPanel1Sensor1TemperatureOutOfRange = solarPanel1Sensor1TemperatureOutOfRange;
	}

	public boolean isSolarPanel1Sensor2TemperatureOutOfRange() {
		return solarPanel1Sensor2TemperatureOutOfRange;
	}

	public void setSolarPanel1Sensor2TemperatureOutOfRange(boolean solarPanel1Sensor2TemperatureOutOfRange) {
		this.solarPanel1Sensor2TemperatureOutOfRange = solarPanel1Sensor2TemperatureOutOfRange;
	}

	public boolean isSolarPanel2Sensor1TemperatureOutOfRange() {
		return solarPanel2Sensor1TemperatureOutOfRange;
	}

	public void setSolarPanel2Sensor1TemperatureOutOfRange(boolean solarPanel2Sensor1TemperatureOutOfRange) {
		this.solarPanel2Sensor1TemperatureOutOfRange = solarPanel2Sensor1TemperatureOutOfRange;
	}

	public boolean isSolarPanel2Sensor2TemperatureOutOfRange() {
		return solarPanel2Sensor2TemperatureOutOfRange;
	}

	public void setSolarPanel2Sensor2TemperatureOutOfRange(boolean solarPanel2Sensor2TemperatureOutOfRange) {
		this.solarPanel2Sensor2TemperatureOutOfRange = solarPanel2Sensor2TemperatureOutOfRange;
	}

	public boolean isSolarPanel3Sensor1TemperatureOutOfRange() {
		return solarPanel3Sensor1TemperatureOutOfRange;
	}

	public void setSolarPanel3Sensor1TemperatureOutOfRange(boolean solarPanel3Sensor1TemperatureOutOfRange) {
		this.solarPanel3Sensor1TemperatureOutOfRange = solarPanel3Sensor1TemperatureOutOfRange;
	}

	public boolean isSolarPanel3Sensor2TemperatureOutOfRange() {
		return solarPanel3Sensor2TemperatureOutOfRange;
	}

	public void setSolarPanel3Sensor2TemperatureOutOfRange(boolean solarPanel3Sensor2TemperatureOutOfRange) {
		this.solarPanel3Sensor2TemperatureOutOfRange = solarPanel3Sensor2TemperatureOutOfRange;
	}

	public boolean isBatteryPack1CurrentOutOfRange() {
		return batteryPack1CurrentOutOfRange;
	}

	public void setBatteryPack1CurrentOutOfRange(boolean batteryPack1CurrentOutOfRange) {
		this.batteryPack1CurrentOutOfRange = batteryPack1CurrentOutOfRange;
	}

	public boolean isBatteryPack2CurrentOutOfRange() {
		return batteryPack2CurrentOutOfRange;
	}

	public void setBatteryPack2CurrentOutOfRange(boolean batteryPack2CurrentOutOfRange) {
		this.batteryPack2CurrentOutOfRange = batteryPack2CurrentOutOfRange;
	}

	public boolean isBatteryPack3CurrentOutOfRange() {
		return batteryPack3CurrentOutOfRange;
	}

	public void setBatteryPack3CurrentOutOfRange(boolean batteryPack3CurrentOutOfRange) {
		this.batteryPack3CurrentOutOfRange = batteryPack3CurrentOutOfRange;
	}

	public boolean isBatteryPack4CurrentOutOfRange() {
		return batteryPack4CurrentOutOfRange;
	}

	public void setBatteryPack4CurrentOutOfRange(boolean batteryPack4CurrentOutOfRange) {
		this.batteryPack4CurrentOutOfRange = batteryPack4CurrentOutOfRange;
	}

	public boolean isBatteryPack5CurrentOutOfRange() {
		return batteryPack5CurrentOutOfRange;
	}

	public void setBatteryPack5CurrentOutOfRange(boolean batteryPack5CurrentOutOfRange) {
		this.batteryPack5CurrentOutOfRange = batteryPack5CurrentOutOfRange;
	}

	public boolean isBatteryPack6CurrentOutOfRange() {
		return batteryPack6CurrentOutOfRange;
	}

	public void setBatteryPack6CurrentOutOfRange(boolean batteryPack6CurrentOutOfRange) {
		this.batteryPack6CurrentOutOfRange = batteryPack6CurrentOutOfRange;
	}

	public boolean isBatterypack1Sensor1TemperatureOutOfRange() {
		return batterypack1Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack1Sensor1TemperatureOutOfRange(boolean batterypack1Sensor1TemperatureOutOfRange) {
		this.batterypack1Sensor1TemperatureOutOfRange = batterypack1Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack1Sensor2TemperatureOutOfRange() {
		return batterypack1Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack1Sensor2TemperatureOutOfRange(boolean batterypack1Sensor2TemperatureOutOfRange) {
		this.batterypack1Sensor2TemperatureOutOfRange = batterypack1Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack2Sensor1TemperatureOutOfRange() {
		return batterypack2Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack2Sensor1TemperatureOutOfRange(boolean batterypack2Sensor1TemperatureOutOfRange) {
		this.batterypack2Sensor1TemperatureOutOfRange = batterypack2Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack2Sensor2TemperatureOutOfRange() {
		return batterypack2Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack2Sensor2TemperatureOutOfRange(boolean batterypack2Sensor2TemperatureOutOfRange) {
		this.batterypack2Sensor2TemperatureOutOfRange = batterypack2Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack3Sensor1TemperatureOutOfRange() {
		return batterypack3Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack3Sensor1TemperatureOutOfRange(boolean batterypack3Sensor1TemperatureOutOfRange) {
		this.batterypack3Sensor1TemperatureOutOfRange = batterypack3Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack3Sensor2TemperatureOutOfRange() {
		return batterypack3Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack3Sensor2TemperatureOutOfRange(boolean batterypack3Sensor2TemperatureOutOfRange) {
		this.batterypack3Sensor2TemperatureOutOfRange = batterypack3Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack4Sensor1TemperatureOutOfRange() {
		return batterypack4Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack4Sensor1TemperatureOutOfRange(boolean batterypack4Sensor1TemperatureOutOfRange) {
		this.batterypack4Sensor1TemperatureOutOfRange = batterypack4Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack4Sensor2TemperatureOutOfRange() {
		return batterypack4Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack4Sensor2TemperatureOutOfRange(boolean batterypack4Sensor2TemperatureOutOfRange) {
		this.batterypack4Sensor2TemperatureOutOfRange = batterypack4Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack5Sensor1TemperatureOutOfRange() {
		return batterypack5Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack5Sensor1TemperatureOutOfRange(boolean batterypack5Sensor1TemperatureOutOfRange) {
		this.batterypack5Sensor1TemperatureOutOfRange = batterypack5Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack5Sensor2TemperatureOutOfRange() {
		return batterypack5Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack5Sensor2TemperatureOutOfRange(boolean batterypack5Sensor2TemperatureOutOfRange) {
		this.batterypack5Sensor2TemperatureOutOfRange = batterypack5Sensor2TemperatureOutOfRange;
	}

	public boolean isBatterypack6Sensor1TemperatureOutOfRange() {
		return batterypack6Sensor1TemperatureOutOfRange;
	}

	public void setBatterypack6Sensor1TemperatureOutOfRange(boolean batterypack6Sensor1TemperatureOutOfRange) {
		this.batterypack6Sensor1TemperatureOutOfRange = batterypack6Sensor1TemperatureOutOfRange;
	}

	public boolean isBatterypack6Sensor2TemperatureOutOfRange() {
		return batterypack6Sensor2TemperatureOutOfRange;
	}

	public void setBatterypack6Sensor2TemperatureOutOfRange(boolean batterypack6Sensor2TemperatureOutOfRange) {
		this.batterypack6Sensor2TemperatureOutOfRange = batterypack6Sensor2TemperatureOutOfRange;
	}

	public boolean isMainBusVoltageoOutOfRange() {
		return mainBusVoltageoOutOfRange;
	}

	public void setMainBusVoltageoOutOfRange(boolean mainBusVoltageoOutOfRange) {
		this.mainBusVoltageoOutOfRange = mainBusVoltageoOutOfRange;
	}

	public boolean isPowerBoardSensor1TemperatureOutOfRange() {
		return powerBoardSensor1TemperatureOutOfRange;
	}

	public void setPowerBoardSensor1TemperatureOutOfRange(boolean powerBoardSensor1TemperatureOutOfRange) {
		this.powerBoardSensor1TemperatureOutOfRange = powerBoardSensor1TemperatureOutOfRange;
	}

	public boolean isPowerBoardSensor2TemperatureOutOfRange() {
		return powerBoardSensor2TemperatureOutOfRange;
	}

	public void setPowerBoardSensor2TemperatureOutOfRange(boolean powerBoardSensor2TemperatureOutOfRange) {
		this.powerBoardSensor2TemperatureOutOfRange = powerBoardSensor2TemperatureOutOfRange;
	}

}
