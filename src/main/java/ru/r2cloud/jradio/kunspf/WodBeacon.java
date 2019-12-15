package ru.r2cloud.jradio.kunspf;

import java.io.DataInputStream;
import java.io.IOException;

public class WodBeacon {

	private int timestamp;
	private int solarPanelVoltageX;
	private int solarPanelVoltageY;
	private int solarPanelVoltageZ;

	private int solarPanelRegulatorTemp0;
	private int solarPanelRegulatorTemp1;
	private int solarPanelRegulatorTemp2;

	private int batteryTemp;
	private int bootCause;
	private int batteryMode;
	private int solarPanelCurrent;
	private int batteryVoltage;
	private int systemCurrent;
	private int epsBootCount;
	private float radioAmplifierTemp;
	private int txCount;
	private int rxCount;
	private int lastRxRfPower;
	private int lastRfError;
	private int radioBootCount;
	private float obcTemp0;
	private float obcTemp1;
	private float gyroX;
	private float gyroY;
	private float gyroZ;

	private float magnetometerX;
	private float magnetometerY;
	private float magnetometerZ;

	private int solarPanelRegulatorCurrent0;
	private int solarPanelRegulatorCurrent1;
	private int solarPanelRegulatorCurrent2;

	private float solarPanelTemp0;
	private float solarPanelTemp1;
	private float solarPanelTemp2;
	private float solarPanelTemp3;
	private float solarPanelTemp4;
	private float solarPanelTemp5;

	private int sunSensor0;
	private int sunSensor1;
	private int sunSensor2;
	private int sunSensor3;
	private int sunSensor4;
	private int sunSensor5;

	public WodBeacon() {
		// do nothing
	}

	public WodBeacon(DataInputStream dis) throws IOException {
		timestamp = dis.readInt();

		solarPanelVoltageX = dis.readUnsignedShort();
		solarPanelVoltageY = dis.readUnsignedShort();
		solarPanelVoltageZ = dis.readUnsignedShort();

		solarPanelRegulatorTemp0 = dis.readShort();
		solarPanelRegulatorTemp1 = dis.readShort();
		solarPanelRegulatorTemp2 = dis.readShort();

		batteryTemp = dis.readShort();
		bootCause = dis.readUnsignedShort();
		batteryMode = dis.readUnsignedShort();
		solarPanelCurrent = dis.readShort();
		systemCurrent = dis.readUnsignedShort();
		batteryVoltage = dis.readUnsignedShort();
		epsBootCount = dis.readUnsignedShort();
		radioAmplifierTemp = dis.readShort();
		txCount = dis.readUnsignedShort();
		rxCount = dis.readUnsignedShort();
		lastRxRfPower = dis.readShort();
		lastRfError = dis.readUnsignedShort();
		radioBootCount = dis.readUnsignedShort();
		obcTemp0 = (float) dis.readShort() / 10;
		obcTemp1 = (float) dis.readShort() / 10;
		gyroX = (float) dis.readShort() / 100;
		gyroY = (float) dis.readShort() / 100;
		gyroZ = (float) dis.readShort() / 100;
		magnetometerX = (float) dis.readShort() / 10;
		magnetometerY = (float) dis.readShort() / 10;
		magnetometerZ = (float) dis.readShort() / 10;

		solarPanelRegulatorCurrent0 = dis.readUnsignedShort();
		solarPanelRegulatorCurrent1 = dis.readUnsignedShort();
		solarPanelRegulatorCurrent2 = dis.readUnsignedShort();

		solarPanelTemp0 = (float) dis.readShort() / 10;
		solarPanelTemp1 = (float) dis.readShort() / 10;
		solarPanelTemp2 = (float) dis.readShort() / 10;
		solarPanelTemp3 = (float) dis.readShort() / 10;
		solarPanelTemp4 = (float) dis.readShort() / 10;
		solarPanelTemp5 = (float) dis.readShort() / 10;

		sunSensor0 = dis.readShort();
		sunSensor1 = dis.readShort();
		sunSensor2 = dis.readShort();
		sunSensor3 = dis.readShort();
		sunSensor4 = dis.readShort();
		sunSensor5 = dis.readShort();
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
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

	public int getSolarPanelRegulatorTemp0() {
		return solarPanelRegulatorTemp0;
	}

	public void setSolarPanelRegulatorTemp0(int solarPanelRegulatorTemp0) {
		this.solarPanelRegulatorTemp0 = solarPanelRegulatorTemp0;
	}

	public int getSolarPanelRegulatorTemp1() {
		return solarPanelRegulatorTemp1;
	}

	public void setSolarPanelRegulatorTemp1(int solarPanelRegulatorTemp1) {
		this.solarPanelRegulatorTemp1 = solarPanelRegulatorTemp1;
	}

	public int getSolarPanelRegulatorTemp2() {
		return solarPanelRegulatorTemp2;
	}

	public void setSolarPanelRegulatorTemp2(int solarPanelRegulatorTemp2) {
		this.solarPanelRegulatorTemp2 = solarPanelRegulatorTemp2;
	}

	public int getBatteryTemp() {
		return batteryTemp;
	}

	public void setBatteryTemp(int batteryTemp) {
		this.batteryTemp = batteryTemp;
	}

	public int getBootCause() {
		return bootCause;
	}

	public void setBootCause(int bootCause) {
		this.bootCause = bootCause;
	}

	public int getBatteryMode() {
		return batteryMode;
	}

	public void setBatteryMode(int batteryMode) {
		this.batteryMode = batteryMode;
	}

	public int getSolarPanelCurrent() {
		return solarPanelCurrent;
	}

	public void setSolarPanelCurrent(int solarPanelCurrent) {
		this.solarPanelCurrent = solarPanelCurrent;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getSystemCurrent() {
		return systemCurrent;
	}

	public void setSystemCurrent(int systemCurrent) {
		this.systemCurrent = systemCurrent;
	}

	public int getEpsBootCount() {
		return epsBootCount;
	}

	public void setEpsBootCount(int epsBootCount) {
		this.epsBootCount = epsBootCount;
	}

	public float getRadioAmplifierTemp() {
		return radioAmplifierTemp;
	}

	public void setRadioAmplifierTemp(float radioAmplifierTemp) {
		this.radioAmplifierTemp = radioAmplifierTemp;
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

	public int getLastRxRfPower() {
		return lastRxRfPower;
	}

	public void setLastRxRfPower(int lastRxRfPower) {
		this.lastRxRfPower = lastRxRfPower;
	}

	public int getLastRfError() {
		return lastRfError;
	}

	public void setLastRfError(int lastRfError) {
		this.lastRfError = lastRfError;
	}

	public int getRadioBootCount() {
		return radioBootCount;
	}

	public void setRadioBootCount(int radioBootCount) {
		this.radioBootCount = radioBootCount;
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

	public float getGyroX() {
		return gyroX;
	}

	public void setGyroX(float gyroX) {
		this.gyroX = gyroX;
	}

	public float getGyroY() {
		return gyroY;
	}

	public void setGyroY(float gyroY) {
		this.gyroY = gyroY;
	}

	public float getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(float gyroZ) {
		this.gyroZ = gyroZ;
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

	public int getSolarPanelRegulatorCurrent0() {
		return solarPanelRegulatorCurrent0;
	}

	public void setSolarPanelRegulatorCurrent0(int solarPanelRegulatorCurrent0) {
		this.solarPanelRegulatorCurrent0 = solarPanelRegulatorCurrent0;
	}

	public int getSolarPanelRegulatorCurrent1() {
		return solarPanelRegulatorCurrent1;
	}

	public void setSolarPanelRegulatorCurrent1(int solarPanelRegulatorCurrent1) {
		this.solarPanelRegulatorCurrent1 = solarPanelRegulatorCurrent1;
	}

	public int getSolarPanelRegulatorCurrent2() {
		return solarPanelRegulatorCurrent2;
	}

	public void setSolarPanelRegulatorCurrent2(int solarPanelRegulatorCurrent2) {
		this.solarPanelRegulatorCurrent2 = solarPanelRegulatorCurrent2;
	}

	public float getSolarPanelTemp0() {
		return solarPanelTemp0;
	}

	public void setSolarPanelTemp0(float solarPanelTemp0) {
		this.solarPanelTemp0 = solarPanelTemp0;
	}

	public float getSolarPanelTemp1() {
		return solarPanelTemp1;
	}

	public void setSolarPanelTemp1(float solarPanelTemp1) {
		this.solarPanelTemp1 = solarPanelTemp1;
	}

	public float getSolarPanelTemp2() {
		return solarPanelTemp2;
	}

	public void setSolarPanelTemp2(float solarPanelTemp2) {
		this.solarPanelTemp2 = solarPanelTemp2;
	}

	public float getSolarPanelTemp3() {
		return solarPanelTemp3;
	}

	public void setSolarPanelTemp3(float solarPanelTemp3) {
		this.solarPanelTemp3 = solarPanelTemp3;
	}

	public float getSolarPanelTemp4() {
		return solarPanelTemp4;
	}

	public void setSolarPanelTemp4(float solarPanelTemp4) {
		this.solarPanelTemp4 = solarPanelTemp4;
	}

	public float getSolarPanelTemp5() {
		return solarPanelTemp5;
	}

	public void setSolarPanelTemp5(float solarPanelTemp5) {
		this.solarPanelTemp5 = solarPanelTemp5;
	}

	public int getSunSensor0() {
		return sunSensor0;
	}

	public void setSunSensor0(int sunSensor0) {
		this.sunSensor0 = sunSensor0;
	}

	public int getSunSensor1() {
		return sunSensor1;
	}

	public void setSunSensor1(int sunSensor1) {
		this.sunSensor1 = sunSensor1;
	}

	public int getSunSensor2() {
		return sunSensor2;
	}

	public void setSunSensor2(int sunSensor2) {
		this.sunSensor2 = sunSensor2;
	}

	public int getSunSensor3() {
		return sunSensor3;
	}

	public void setSunSensor3(int sunSensor3) {
		this.sunSensor3 = sunSensor3;
	}

	public int getSunSensor4() {
		return sunSensor4;
	}

	public void setSunSensor4(int sunSensor4) {
		this.sunSensor4 = sunSensor4;
	}

	public int getSunSensor5() {
		return sunSensor5;
	}

	public void setSunSensor5(int sunSensor5) {
		this.sunSensor5 = sunSensor5;
	}

}
