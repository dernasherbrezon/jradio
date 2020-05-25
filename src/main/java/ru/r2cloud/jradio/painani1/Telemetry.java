package ru.r2cloud.jradio.painani1;

import java.io.DataInputStream;
import java.io.IOException;

public class Telemetry {

	private int length;
	private int antenna;
	private int temperaturePanel1;
	private int temperaturePanel2;
	private int temperaturePanel3;
	private int temperatureBank1;
	private int temperatureBank2;
	private int chargeBank1;
	private int chargeBank2;
	private int gyroX;
	private int gyroY;
	private int gyroZ;
	private int state;
	private int reboots;
	private long rtc;
	private int voltageBank1;
	private int voltageBank2;
	private int currentBank1;
	private int currentBank2;
	private int gps;
	private int voltage3V3;
	private int batteryVoltage;
	private int lnaVoltage;
	private int voltage5V;
	private int voltageGps;
	private int cameraVoltage;

	public Telemetry() {
		// do nothing
	}

	public Telemetry(DataInputStream dis) throws IOException {
		length = dis.readUnsignedByte();
		antenna = dis.readUnsignedByte();
		temperaturePanel1 = dis.readShort();
		temperaturePanel2 = dis.readShort();
		temperaturePanel3 = dis.readShort();

		temperatureBank1 = dis.readShort();
		temperatureBank2 = dis.readShort();

		chargeBank1 = dis.readShort();
		chargeBank2 = dis.readShort();

		gyroX = dis.readShort();
		gyroY = dis.readShort();
		gyroZ = dis.readShort();

		state = dis.readUnsignedByte();
		reboots = dis.readUnsignedShort();
		rtc = (((long) dis.readUnsignedByte() << 40) | ((long) dis.readUnsignedByte() << 32) | ((long) dis.readUnsignedByte() << 24) | ((long) dis.readUnsignedByte() << 16) | ((long) dis.readUnsignedByte() << 8) | dis.readUnsignedByte());

		voltageBank1 = dis.readUnsignedShort();
		voltageBank2 = dis.readUnsignedShort();
		currentBank1 = dis.readShort();
		currentBank2 = dis.readShort();
		gps = dis.readUnsignedByte();
		voltage3V3 = dis.readUnsignedShort();
		batteryVoltage = dis.readUnsignedShort();
		lnaVoltage = dis.readUnsignedShort();
		voltage5V = dis.readUnsignedShort();
		voltageGps = dis.readUnsignedShort();
		cameraVoltage = dis.readUnsignedShort();
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getAntenna() {
		return antenna;
	}

	public void setAntenna(int antenna) {
		this.antenna = antenna;
	}

	public int getTemperaturePanel1() {
		return temperaturePanel1;
	}

	public void setTemperaturePanel1(int temperaturePanel1) {
		this.temperaturePanel1 = temperaturePanel1;
	}

	public int getTemperaturePanel2() {
		return temperaturePanel2;
	}

	public void setTemperaturePanel2(int temperaturePanel2) {
		this.temperaturePanel2 = temperaturePanel2;
	}

	public int getTemperaturePanel3() {
		return temperaturePanel3;
	}

	public void setTemperaturePanel3(int temperaturePanel3) {
		this.temperaturePanel3 = temperaturePanel3;
	}

	public int getTemperatureBank1() {
		return temperatureBank1;
	}

	public void setTemperatureBank1(int temperatureBank1) {
		this.temperatureBank1 = temperatureBank1;
	}

	public int getTemperatureBank2() {
		return temperatureBank2;
	}

	public void setTemperatureBank2(int temperatureBank2) {
		this.temperatureBank2 = temperatureBank2;
	}

	public int getChargeBank1() {
		return chargeBank1;
	}

	public void setChargeBank1(int chargeBank1) {
		this.chargeBank1 = chargeBank1;
	}

	public int getChargeBank2() {
		return chargeBank2;
	}

	public void setChargeBank2(int chargeBank2) {
		this.chargeBank2 = chargeBank2;
	}

	public int getGyroX() {
		return gyroX;
	}

	public void setGyroX(int gyroX) {
		this.gyroX = gyroX;
	}

	public int getGyroY() {
		return gyroY;
	}

	public void setGyroY(int gyroY) {
		this.gyroY = gyroY;
	}

	public int getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(int gyroZ) {
		this.gyroZ = gyroZ;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getReboots() {
		return reboots;
	}

	public void setReboots(int reboots) {
		this.reboots = reboots;
	}

	public long getRtc() {
		return rtc;
	}

	public void setRtc(long rtc) {
		this.rtc = rtc;
	}

	public int getVoltageBank1() {
		return voltageBank1;
	}

	public void setVoltageBank1(int voltageBank1) {
		this.voltageBank1 = voltageBank1;
	}

	public int getVoltageBank2() {
		return voltageBank2;
	}

	public void setVoltageBank2(int voltageBank2) {
		this.voltageBank2 = voltageBank2;
	}

	public int getCurrentBank1() {
		return currentBank1;
	}

	public void setCurrentBank1(int currentBank1) {
		this.currentBank1 = currentBank1;
	}

	public int getCurrentBank2() {
		return currentBank2;
	}

	public void setCurrentBank2(int currentBank2) {
		this.currentBank2 = currentBank2;
	}

	public int getGps() {
		return gps;
	}

	public void setGps(int gps) {
		this.gps = gps;
	}

	public int getVoltage3V3() {
		return voltage3V3;
	}

	public void setVoltage3V3(int voltage3v3) {
		voltage3V3 = voltage3v3;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getLnaVoltage() {
		return lnaVoltage;
	}

	public void setLnaVoltage(int lnaVoltage) {
		this.lnaVoltage = lnaVoltage;
	}

	public int getVoltage5V() {
		return voltage5V;
	}

	public void setVoltage5V(int voltage5v) {
		voltage5V = voltage5v;
	}

	public int getVoltageGps() {
		return voltageGps;
	}

	public void setVoltageGps(int voltageGps) {
		this.voltageGps = voltageGps;
	}

	public int getCameraVoltage() {
		return cameraVoltage;
	}

	public void setCameraVoltage(int cameraVoltage) {
		this.cameraVoltage = cameraVoltage;
	}

}
