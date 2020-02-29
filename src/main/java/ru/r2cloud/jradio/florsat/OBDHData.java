package ru.r2cloud.jradio.florsat;

import java.io.DataInputStream;
import java.io.IOException;

public class OBDHData {

	private double batteryVoltage1;
	private double batteryVoltage2;
	private double batteryTemperature1;
	private double batteryTemperature2;
	private double batteryCharge;
	private double[] solarPanelCurrent;
	private double[] solarPanelVoltage;
	private int energyLevel;
	private boolean imu_status;
	private boolean sd_card_status;
	private boolean rush_status;
	private boolean eps_status;
	private boolean antenna_status;

	private double accelerometerX;
	private double accelerometerY;
	private double accelerometerZ;

	private double gyroscopeX;
	private double gyroscopeY;
	private double gyroscopeZ;

	private int timeSeconds;
	private int timeMinutes;
	private int timeHours;

	private int obdhResets;

	public OBDHData() {
		// do nothing
	}

	public OBDHData(DataInputStream dis) throws IOException {
		batteryVoltage1 = batteryVoltage(dis.readUnsignedShort());
		batteryVoltage2 = batteryVoltage(dis.readUnsignedShort());
		batteryTemperature1 = batteryTemperature((dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte());
		batteryTemperature2 = batteryTemperature((dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte());
		batteryCharge = batteryCharge(dis.readUnsignedShort());
		solarPanelCurrent = new double[6];
		for (int i = 0; i < solarPanelCurrent.length; i++) {
			solarPanelCurrent[i] = solarPanelCurrent(dis.readUnsignedShort());
		}
		solarPanelVoltage = new double[3];
		for (int i = 0; i < solarPanelVoltage.length; i++) {
			solarPanelVoltage[i] = solarPanelVoltage(dis.readUnsignedShort());
		}
		energyLevel = dis.readUnsignedByte();
		int flags = dis.readUnsignedByte();
		imu_status = ((flags >> 4) & 0x1) > 0;
		sd_card_status = ((flags >> 3) & 0x1) > 0;
		rush_status = ((flags >> 1) & 0x1) > 0;
		eps_status = ((flags >> 0) & 0x1) > 0;
		antenna_status = ((flags >> 5) & 0x1) > 0;

		accelerometerX = imuAccel(dis.readShort());
		accelerometerY = imuAccel(dis.readShort());
		accelerometerZ = imuAccel(dis.readShort());

		gyroscopeX = imuGyro(dis.readShort());
		gyroscopeY = imuGyro(dis.readShort());
		gyroscopeZ = imuGyro(dis.readShort());

		timeSeconds = dis.readUnsignedByte();

		int b1 = dis.readUnsignedByte();
		int b2 = dis.readUnsignedByte();
		int b3 = dis.readUnsignedByte();
		timeMinutes = ((b1 << 16) | (b2 << 8) | (b3 << 0)) % 60;
		timeHours = ((b1 << 16) | (b2 << 8) | (b3 << 0)) / 60;

		obdhResets = dis.readUnsignedShort();
	}

	private static double batteryVoltage(int val) {
		return (val / 32.0) * 4.883e-3;
	}

	private static double batteryTemperature(long val) {
		return (val * 0.125) / 32.0;
	}

	private static double batteryCharge(int val) {
		return val * (6.25 * 1e-4);
	}

	private static double solarPanelCurrent(int val) {
		return val * (2.5 / 4095) * (1 / (0.05 * 0.025 * 3300));
	}

	private static double solarPanelVoltage(int val) {
		return val * (2.5 / 4095) * (100e3 + 93.1e3) / 100e3;
	}

	private static double imuAccel(short val) {
		return val * 16.0 / 32768.0;
	}

	private static double imuGyro(short val) {
		return val * 250.0 / 32768.0;
	}

	public double getBatteryVoltage1() {
		return batteryVoltage1;
	}

	public void setBatteryVoltage1(double batteryVoltage1) {
		this.batteryVoltage1 = batteryVoltage1;
	}

	public double getBatteryVoltage2() {
		return batteryVoltage2;
	}

	public void setBatteryVoltage2(double batteryVoltage2) {
		this.batteryVoltage2 = batteryVoltage2;
	}

	public double getBatteryTemperature1() {
		return batteryTemperature1;
	}

	public void setBatteryTemperature1(double batteryTemperature1) {
		this.batteryTemperature1 = batteryTemperature1;
	}

	public double getBatteryTemperature2() {
		return batteryTemperature2;
	}

	public void setBatteryTemperature2(double batteryTemperature2) {
		this.batteryTemperature2 = batteryTemperature2;
	}

	public double getBatteryCharge() {
		return batteryCharge;
	}

	public void setBatteryCharge(double batteryCharge) {
		this.batteryCharge = batteryCharge;
	}

	public double[] getSolarPanelCurrent() {
		return solarPanelCurrent;
	}

	public void setSolarPanelCurrent(double[] solarPanelCurrent) {
		this.solarPanelCurrent = solarPanelCurrent;
	}

	public double[] getSolarPanelVoltage() {
		return solarPanelVoltage;
	}

	public void setSolarPanelVoltage(double[] solarPanelVoltage) {
		this.solarPanelVoltage = solarPanelVoltage;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	public boolean isImu_status() {
		return imu_status;
	}

	public void setImu_status(boolean imu_status) {
		this.imu_status = imu_status;
	}

	public boolean isSd_card_status() {
		return sd_card_status;
	}

	public void setSd_card_status(boolean sd_card_status) {
		this.sd_card_status = sd_card_status;
	}

	public boolean isRush_status() {
		return rush_status;
	}

	public void setRush_status(boolean rush_status) {
		this.rush_status = rush_status;
	}

	public boolean isEps_status() {
		return eps_status;
	}

	public void setEps_status(boolean eps_status) {
		this.eps_status = eps_status;
	}

	public boolean isAntenna_status() {
		return antenna_status;
	}

	public void setAntenna_status(boolean antenna_status) {
		this.antenna_status = antenna_status;
	}

	public double getAccelerometerX() {
		return accelerometerX;
	}

	public void setAccelerometerX(double accelerometerX) {
		this.accelerometerX = accelerometerX;
	}

	public double getAccelerometerY() {
		return accelerometerY;
	}

	public void setAccelerometerY(double accelerometerY) {
		this.accelerometerY = accelerometerY;
	}

	public double getAccelerometerZ() {
		return accelerometerZ;
	}

	public void setAccelerometerZ(double accelerometerZ) {
		this.accelerometerZ = accelerometerZ;
	}

	public double getGyroscopeX() {
		return gyroscopeX;
	}

	public void setGyroscopeX(double gyroscopeX) {
		this.gyroscopeX = gyroscopeX;
	}

	public double getGyroscopeY() {
		return gyroscopeY;
	}

	public void setGyroscopeY(double gyroscopeY) {
		this.gyroscopeY = gyroscopeY;
	}

	public double getGyroscopeZ() {
		return gyroscopeZ;
	}

	public void setGyroscopeZ(double gyroscopeZ) {
		this.gyroscopeZ = gyroscopeZ;
	}

	public int getTimeSeconds() {
		return timeSeconds;
	}

	public void setTimeSeconds(int timeSeconds) {
		this.timeSeconds = timeSeconds;
	}

	public int getTimeMinutes() {
		return timeMinutes;
	}

	public void setTimeMinutes(int timeMinutes) {
		this.timeMinutes = timeMinutes;
	}

	public int getTimeHours() {
		return timeHours;
	}

	public void setTimeHours(int timeHours) {
		this.timeHours = timeHours;
	}

	public int getObdhResets() {
		return obdhResets;
	}

	public void setObdhResets(int obdhResets) {
		this.obdhResets = obdhResets;
	}

}
