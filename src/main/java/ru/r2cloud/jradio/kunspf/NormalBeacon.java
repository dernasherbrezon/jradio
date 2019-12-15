package ru.r2cloud.jradio.kunspf;

import java.io.DataInputStream;
import java.io.IOException;

public class NormalBeacon {

	private int beaconCounter;
	private int solarPanelVoltageX;
	private int solarPanelVoltageY;
	private int solarPanelVoltageZ;

	private int epsTemp0;
	private int epsTemp1;
	private int epsTemp2;
	private int epsTemp3;

	private int epsBootCause;
	private int epsBattMode;

	private int solarPanelCurrent;
	private int systemInputCurrent;
	private int batteryVoltage;
	private int radioPATemp;

	private int txCount;
	private int rxCount;
	private float obcTemp0;
	private float obcTemp1;

	private int angVelocityMag;
	private float magnetometerX;
	private float magnetometerY;
	private float magnetometerZ;
	private int mainAxisOfRotation;

	public NormalBeacon() {
		// do nothing
	}

	public NormalBeacon(DataInputStream dis) throws IOException {
		beaconCounter = dis.readUnsignedShort();
		solarPanelVoltageX = dis.readUnsignedByte() * 16;
		solarPanelVoltageY = dis.readUnsignedByte() * 16;
		solarPanelVoltageZ = dis.readUnsignedByte() * 16;

		epsTemp0 = dis.readUnsignedByte() - 100;
		epsTemp1 = dis.readUnsignedByte() - 100;
		epsTemp2 = dis.readUnsignedByte() - 100;
		epsTemp3 = dis.readUnsignedByte() - 100;

		epsBootCause = dis.readUnsignedByte();
		epsBattMode = dis.readUnsignedByte();

		solarPanelCurrent = dis.readUnsignedByte() * 10;
		systemInputCurrent = dis.readUnsignedByte() * 16;
		batteryVoltage = dis.readUnsignedByte() * 34;
		radioPATemp = dis.readUnsignedByte() - 100;

		txCount = dis.readUnsignedShort();
		rxCount = dis.readUnsignedShort();

		obcTemp0 = dis.readUnsignedByte() - 100.0f;
		obcTemp1 = dis.readUnsignedByte() - 100.0f;

		angVelocityMag = dis.readUnsignedByte();

		magnetometerX = dis.readByte() * 6.0f;
		magnetometerY = dis.readByte() * 6.0f;
		magnetometerZ = dis.readByte() * 6.0f;

		mainAxisOfRotation = dis.readUnsignedByte();
	}

	public int getBeaconCounter() {
		return beaconCounter;
	}

	public void setBeaconCounter(int beaconCounter) {
		this.beaconCounter = beaconCounter;
	}

	public int getSolarPanelVoltageX() {
		return solarPanelVoltageX;
	}

	public void setSolarPanelVoltageX(int solarPanelVoltageX) {
		this.solarPanelVoltageX = solarPanelVoltageX;
	}

	public int getSolarPanelVoltageY() {
		return solarPanelVoltageY;
	}

	public void setSolarPanelVoltageY(int solarPanelVoltageY) {
		this.solarPanelVoltageY = solarPanelVoltageY;
	}

	public int getSolarPanelVoltageZ() {
		return solarPanelVoltageZ;
	}

	public void setSolarPanelVoltageZ(int solarPanelVoltageZ) {
		this.solarPanelVoltageZ = solarPanelVoltageZ;
	}

	public int getEpsTemp0() {
		return epsTemp0;
	}

	public void setEpsTemp0(int epsTemp0) {
		this.epsTemp0 = epsTemp0;
	}

	public int getEpsTemp1() {
		return epsTemp1;
	}

	public void setEpsTemp1(int epsTemp1) {
		this.epsTemp1 = epsTemp1;
	}

	public int getEpsTemp2() {
		return epsTemp2;
	}

	public void setEpsTemp2(int epsTemp2) {
		this.epsTemp2 = epsTemp2;
	}

	public int getEpsTemp3() {
		return epsTemp3;
	}

	public void setEpsTemp3(int epsTemp3) {
		this.epsTemp3 = epsTemp3;
	}

	public int getEpsBootCause() {
		return epsBootCause;
	}

	public void setEpsBootCause(int epsBootCause) {
		this.epsBootCause = epsBootCause;
	}

	public int getEpsBattMode() {
		return epsBattMode;
	}

	public void setEpsBattMode(int epsBattMode) {
		this.epsBattMode = epsBattMode;
	}

	public int getSolarPanelCurrent() {
		return solarPanelCurrent;
	}

	public void setSolarPanelCurrent(int solarPanelCurrent) {
		this.solarPanelCurrent = solarPanelCurrent;
	}

	public int getSystemInputCurrent() {
		return systemInputCurrent;
	}

	public void setSystemInputCurrent(int systemInputCurrent) {
		this.systemInputCurrent = systemInputCurrent;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getRadioPATemp() {
		return radioPATemp;
	}

	public void setRadioPATemp(int radioPATemp) {
		this.radioPATemp = radioPATemp;
	}

	public int getTxCount() {
		return txCount;
	}

	public void setTxCount(int txCount) {
		this.txCount = txCount;
	}

	public int getRxCount() {
		return rxCount;
	}

	public void setRxCount(int rxCount) {
		this.rxCount = rxCount;
	}

	public float getObcTemp0() {
		return obcTemp0;
	}

	public void setObcTemp0(float obcTemp0) {
		this.obcTemp0 = obcTemp0;
	}

	public float getObcTemp1() {
		return obcTemp1;
	}

	public void setObcTemp1(float obcTemp1) {
		this.obcTemp1 = obcTemp1;
	}

	public int getAngVelocityMag() {
		return angVelocityMag;
	}

	public void setAngVelocityMag(int angVelocityMag) {
		this.angVelocityMag = angVelocityMag;
	}

	public float getMagnetometerX() {
		return magnetometerX;
	}

	public void setMagnetometerX(float magnetometerX) {
		this.magnetometerX = magnetometerX;
	}

	public float getMagnetometerY() {
		return magnetometerY;
	}

	public void setMagnetometerY(float magnetometerY) {
		this.magnetometerY = magnetometerY;
	}

	public float getMagnetometerZ() {
		return magnetometerZ;
	}

	public void setMagnetometerZ(float magnetometerZ) {
		this.magnetometerZ = magnetometerZ;
	}

	public int getMainAxisOfRotation() {
		return mainAxisOfRotation;
	}

	public void setMainAxisOfRotation(int mainAxisOfRotation) {
		this.mainAxisOfRotation = mainAxisOfRotation;
	}

}
