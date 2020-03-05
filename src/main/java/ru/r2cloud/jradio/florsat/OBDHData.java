package ru.r2cloud.jradio.florsat;

import java.io.DataInputStream;
import java.io.IOException;

public class OBDHData extends EPSData {

	private boolean imuStatus;
	private boolean sdCardStatus;
	private boolean rushStatus;
	private boolean epsStatus;
	private boolean antennaStatus;

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
		super(dis);
		int flags = dis.readUnsignedByte();
		imuStatus = ((flags >> 4) & 0x1) > 0;
		sdCardStatus = ((flags >> 3) & 0x1) > 0;
		rushStatus = ((flags >> 1) & 0x1) > 0;
		epsStatus = ((flags) & 0x1) > 0;
		antennaStatus = ((flags >> 5) & 0x1) > 0;

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
		timeMinutes = ((b1 << 16) | (b2 << 8) | (b3)) % 60;
		timeHours = ((b1 << 16) | (b2 << 8) | (b3)) / 60;

		obdhResets = dis.readUnsignedShort();
	}

	private static double imuAccel(short val) {
		return val * 16.0 / 32768.0;
	}

	private static double imuGyro(short val) {
		return val * 250.0 / 32768.0;
	}

	public boolean isImuStatus() {
		return imuStatus;
	}

	public void setImuStatus(boolean imuStatus) {
		this.imuStatus = imuStatus;
	}

	public boolean isSdCardStatus() {
		return sdCardStatus;
	}

	public void setSdCardStatus(boolean sdCardStatus) {
		this.sdCardStatus = sdCardStatus;
	}

	public boolean isRushStatus() {
		return rushStatus;
	}

	public void setRushStatus(boolean rushStatus) {
		this.rushStatus = rushStatus;
	}

	public boolean isEpsStatus() {
		return epsStatus;
	}

	public void setEpsStatus(boolean epsStatus) {
		this.epsStatus = epsStatus;
	}

	public boolean isAntennaStatus() {
		return antennaStatus;
	}

	public void setAntennaStatus(boolean antennaStatus) {
		this.antennaStatus = antennaStatus;
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
