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
	private float obcTemp0;
	private float obcTemp1;
	private int angVelocityMag;
	private float magnetometerX;
	private float magnetometerY;
	private float magnetometerZ;
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
	private float radioAmplifierTemp;
	private int lastRxRfPower;
	private int lastRfError;
	private int radioBootCount;
	private float gyroX;
	private float gyroY;
	private float gyroZ;

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

	private long beginSample;
	private long beginMillis;

	private int imageBlock;
	private byte[] imageChunk;

	@Override
	public void readExternal(byte[] rawData) throws IOException {
		this.rawData = rawData;
		header = new Header(Arrays.copyOfRange(rawData, 0, Header.LENGTH));
		byte[] data = Arrays.copyOfRange(rawData, Header.LENGTH, rawData.length);
		if (data.length == 134) {
			readImage(data);
		} else if (data.length >= 88) {
			readWODBeacon(data);
		} else {
			readBeacon(data);
		}
	}

	private void readImage(byte[] data) {
		imageBlock = ((data[0] & 0xFF) << 8) | (data[1] & 0xFF);
		imageChunk = Arrays.copyOfRange(data, 2, data.length - 4);
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

	private void readWODBeacon(byte[] data) throws IOException {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
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

	public void setObcTemp0(int obcTemp0) {
		this.obcTemp0 = obcTemp0;
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

	public float getRadioAmplifierTemp() {
		return radioAmplifierTemp;
	}

	public void setRadioAmplifierTemp(float radioAmplifierTemp) {
		this.radioAmplifierTemp = radioAmplifierTemp;
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

	public int getImageBlock() {
		return imageBlock;
	}
	
	public void setImageBlock(int imageBlock) {
		this.imageBlock = imageBlock;
	}
	
	public byte[] getImageChunk() {
		return imageChunk;
	}
	
	public void setImageChunk(byte[] imageChunk) {
		this.imageChunk = imageChunk;
	}
}
