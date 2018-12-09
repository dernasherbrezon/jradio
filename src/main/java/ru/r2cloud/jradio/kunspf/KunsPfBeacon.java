package ru.r2cloud.jradio.kunspf;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import ru.r2cloud.jradio.Externalizable;
import ru.r2cloud.jradio.csp.Header;

public class KunsPfBeacon implements Externalizable {

	private Header header;
	private byte[] rawData;

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
	private int obcTemp0;
	private int obcTemp1;
	private int angVelocityMag;
	private int magnetometerX;
	private int magnetometerY;
	private int magnetometerZ;
	private int mainAxisOfRotation;

	private int timestamp;

	private int solarPanelRegulatorTemp0;
	private int solarPanelRegulatorTemp1;
	private int solarPanelRegulatorTemp2;

	private int batteryTemp;
	private int bootCause;
	private int batteryMode;
	private int solarPannelCurrent;
	private int systemCurrent;
	private int epsBootCount;
	private int radioAmplifierTemp;
	private int lastRxRfPower;
	private int lastRfError;
	private int radioBootCount;
	private int gyroX;
	private int gyroY;
	private int gyroZ;

	private int solarPanelRegulatorCurrent0;
	private int solarPanelRegulatorCurrent1;
	private int solarPanelRegulatorCurrent2;

	private int solarPanelTemp0;
	private int solarPanelTemp1;
	private int solarPanelTemp2;
	private int solarPanelTemp3;
	private int solarPanelTemp4;
	private int solarPanelTemp5;

	private int sunSensor0;
	private int sunSensor1;
	private int sunSensor2;
	private int sunSensor3;
	private int sunSensor4;
	private int sunSensor5;

	private long beginSample;
	private long beginMillis;

	@Override
	public void readExternal(byte[] rawData) throws IOException {
		this.rawData = rawData;
		header = new Header(Arrays.copyOfRange(rawData, 0, Header.LENGTH));
		byte[] data = Arrays.copyOfRange(rawData, Header.LENGTH, rawData.length);
		if (data.length >= 88) {
			readWODBeacon(data);
		} else {
			readBeacon(data);
		}
	}

	private void readBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
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

		obcTemp0 = dis.readUnsignedByte() - 100;
		obcTemp1 = dis.readUnsignedByte() - 100;

		angVelocityMag = dis.readUnsignedByte();

		magnetometerX = dis.readByte() * 6;
		magnetometerY = dis.readByte() * 6;
		magnetometerZ = dis.readByte() * 6;

		mainAxisOfRotation = dis.readUnsignedByte();
	}

	// TODO not tested on real data
	private void readWODBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		timestamp = dis.readInt();

		solarPanelVoltageX = dis.readUnsignedShort() / 1000;
		solarPanelVoltageY = dis.readUnsignedShort() / 1000;
		solarPanelVoltageZ = dis.readUnsignedShort() / 1000;

		solarPanelRegulatorTemp0 = dis.readShort();
		solarPanelRegulatorTemp1 = dis.readShort();
		solarPanelRegulatorTemp2 = dis.readShort();

		batteryTemp = dis.readShort();
		bootCause = dis.readUnsignedShort();
		batteryMode = dis.readUnsignedShort();
		solarPanelCurrent = dis.readShort();
		systemCurrent = dis.readUnsignedShort();
		batteryVoltage = dis.readUnsignedShort() / 1000;
		epsBootCount = dis.readUnsignedShort();
		radioAmplifierTemp = dis.readShort() / 10;
		txCount = dis.readUnsignedShort();
		rxCount = dis.readUnsignedShort();
		lastRxRfPower = dis.readShort();
		lastRfError = dis.readUnsignedShort();
		radioBootCount = dis.readUnsignedShort();
		obcTemp0 = dis.readShort() / 10;
		obcTemp1 = dis.readShort() / 10;
		gyroX = dis.readShort() / 100;
		gyroY = dis.readShort() / 100;
		gyroZ = dis.readShort() / 100;
		magnetometerX = dis.readShort() / 10;
		magnetometerY = dis.readShort() / 10;
		magnetometerZ = dis.readShort() / 10;

		solarPanelRegulatorCurrent0 = dis.readUnsignedShort();
		solarPanelRegulatorCurrent1 = dis.readUnsignedShort();
		solarPanelRegulatorCurrent2 = dis.readUnsignedShort();

		solarPanelTemp0 = dis.readShort() / 10;
		solarPanelTemp1 = dis.readShort() / 10;
		solarPanelTemp2 = dis.readShort() / 10;
		solarPanelTemp3 = dis.readShort() / 10;
		solarPanelTemp4 = dis.readShort() / 10;
		solarPanelTemp5 = dis.readShort() / 10;

		sunSensor0 = dis.readShort();
		sunSensor1 = dis.readShort();
		sunSensor2 = dis.readShort();
		sunSensor3 = dis.readShort();
		sunSensor4 = dis.readShort();
		sunSensor5 = dis.readShort();
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
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

	public int getObcTemp0() {
		return obcTemp0;
	}

	public void setObcTemp0(int obcTemp0) {
		this.obcTemp0 = obcTemp0;
	}

	public int getObcTemp1() {
		return obcTemp1;
	}

	public void setObcTemp1(int obcTemp1) {
		this.obcTemp1 = obcTemp1;
	}

	public int getAngVelocityMag() {
		return angVelocityMag;
	}

	public void setAngVelocityMag(int angVelocityMag) {
		this.angVelocityMag = angVelocityMag;
	}

	public int getMagnetometerX() {
		return magnetometerX;
	}

	public void setMagnetometerX(int magnetometerX) {
		this.magnetometerX = magnetometerX;
	}

	public int getMagnetometerY() {
		return magnetometerY;
	}

	public void setMagnetometerY(int magnetometerY) {
		this.magnetometerY = magnetometerY;
	}

	public int getMagnetometerZ() {
		return magnetometerZ;
	}

	public void setMagnetometerZ(int magnetometerZ) {
		this.magnetometerZ = magnetometerZ;
	}

	public int getMainAxisOfRotation() {
		return mainAxisOfRotation;
	}

	public void setMainAxisOfRotation(int mainAxisOfRotation) {
		this.mainAxisOfRotation = mainAxisOfRotation;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
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

	public int getSolarPannelCurrent() {
		return solarPannelCurrent;
	}

	public void setSolarPannelCurrent(int solarPannelCurrent) {
		this.solarPannelCurrent = solarPannelCurrent;
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

	public int getRadioAmplifierTemp() {
		return radioAmplifierTemp;
	}

	public void setRadioAmplifierTemp(int radioAmplifierTemp) {
		this.radioAmplifierTemp = radioAmplifierTemp;
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

	public int getSolarPanelTemp0() {
		return solarPanelTemp0;
	}

	public void setSolarPanelTemp0(int solarPanelTemp0) {
		this.solarPanelTemp0 = solarPanelTemp0;
	}

	public int getSolarPanelTemp1() {
		return solarPanelTemp1;
	}

	public void setSolarPanelTemp1(int solarPanelTemp1) {
		this.solarPanelTemp1 = solarPanelTemp1;
	}

	public int getSolarPanelTemp2() {
		return solarPanelTemp2;
	}

	public void setSolarPanelTemp2(int solarPanelTemp2) {
		this.solarPanelTemp2 = solarPanelTemp2;
	}

	public int getSolarPanelTemp3() {
		return solarPanelTemp3;
	}

	public void setSolarPanelTemp3(int solarPanelTemp3) {
		this.solarPanelTemp3 = solarPanelTemp3;
	}

	public int getSolarPanelTemp4() {
		return solarPanelTemp4;
	}

	public void setSolarPanelTemp4(int solarPanelTemp4) {
		this.solarPanelTemp4 = solarPanelTemp4;
	}

	public int getSolarPanelTemp5() {
		return solarPanelTemp5;
	}

	public void setSolarPanelTemp5(int solarPanelTemp5) {
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

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}
	
}
