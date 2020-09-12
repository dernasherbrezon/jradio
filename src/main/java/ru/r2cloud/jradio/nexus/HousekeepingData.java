package ru.r2cloud.jradio.nexus;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class HousekeepingData {

	private long satelliteTime;

	private boolean forcedExecution;
	private boolean heater;
	private boolean regulator3v5;
	private boolean cdh;
	private boolean cam;
	private boolean qpsk;
	private boolean fsk;
	private boolean tpr;

	private int resetInformationFmr;
	private int resetInformationCdh;
	private int resetInformationCw;
	private int resetInformationEps;
	private int resetInformationSg;

	private float batteryVoltage;
	private float batteryCurrent;

	private float[] currentData;

	private float batteryTemperature1;
	private float batteryTemperature2;
	private float regulator5vTemperature1;
	private float regulator5vTemperature2;
	private float regulator3v5Temperature;
	private float powerAmplifierTemperature;
	private float qpskTransmitterTemperature;
	private float fskTransmitterTemperature;
	private float solarXPlusTemperature;
	private float solarYPlusTemperature;
	private float solarZPlusTemperature;
	private float solarXMinusTemperature;
	private float solarYMinusTemperature;
	private float solarZMinusTemperature;
	private float busTransmitterTemperature;
	private float busReceiverTemperature;
	private float gyroXTemperature;
	private float gyroYTemperature;
	private float gyroZTemperature;
	private float gyroX;
	private float gyroY;
	private float gyroZ;
	private float magnetDataX;
	private float magnetDataY;
	private float magnetDataZ;
	private float magnetDataRef;

	public HousekeepingData() {
		// do nothing
	}

	public HousekeepingData(DataInputStream dis) throws IOException {
		satelliteTime = (long) (LittleEndianDataInputStream.readUnsignedInt(dis) * 0.5 * 1000); // convert from 0.5s resolution to millisecond resolution
		int raw = dis.readUnsignedByte();
		forcedExecution = ((raw >> 7) & 0x1) > 0;
		heater = ((raw >> 6) & 0x1) > 0;
		regulator3v5 = ((raw >> 5) & 0x1) > 0;
		cdh = ((raw >> 4) & 0x1) > 0;
		cam = ((raw >> 3) & 0x1) > 0;
		qpsk = ((raw >> 2) & 0x1) > 0;
		fsk = ((raw >> 1) & 0x1) > 0;
		tpr = (raw & 0x1) > 0;

		resetInformationFmr = dis.readUnsignedByte();
		resetInformationCdh = dis.readUnsignedByte();
		resetInformationCw = dis.readUnsignedByte();
		resetInformationEps = dis.readUnsignedByte();
		resetInformationSg = dis.readUnsignedByte();

		batteryVoltage = readUnsigned(dis);
		batteryCurrent = readUnsigned(dis) / 0.0005f;

		currentData = new float[6];
		for (int i = 0; i < currentData.length; i++) {
			currentData[i] = readUnsigned(dis) / 0.01f;
		}

		batteryTemperature1 = -37.50f * readUnsigned(dis) + 127;
		batteryTemperature2 = -36.83f * readUnsigned(dis) + 126;
		regulator5vTemperature1 = -37.38f * readUnsigned(dis) + 127;
		regulator5vTemperature2 = -37.06f * readUnsigned(dis) + 126;
		regulator3v5Temperature = -36.95f * readUnsigned(dis) + 125;
		powerAmplifierTemperature = -37.19f * readUnsigned(dis) + 126;
		qpskTransmitterTemperature = -37.56f * readUnsigned(dis) + 128;
		fskTransmitterTemperature = -36.89f * readUnsigned(dis) + 125;
		solarXPlusTemperature = -37.33f * readUnsigned(dis) + 127;
		solarYPlusTemperature = -37.35f * readUnsigned(dis) + 127;
		solarZPlusTemperature = -37.14f * readUnsigned(dis) + 126;
		solarXMinusTemperature = -37.27f * readUnsigned(dis) + 127;
		solarYMinusTemperature = -37.02f * readUnsigned(dis) + 125;
		solarZMinusTemperature = -37.04f * readUnsigned(dis) + 127;
		busTransmitterTemperature = -37.67f * readUnsigned(dis) + 126;
		busReceiverTemperature = -37.72f * readUnsigned(dis) + 128;

		gyroXTemperature = 0.2f * dis.readShort() + 45;
		gyroYTemperature = 0.2f * dis.readShort() + 45;
		gyroZTemperature = 0.2f * dis.readShort() + 45;
		gyroX = dis.readShort() * 0.0125f;
		gyroY = dis.readShort() * 0.0125f;
		gyroZ = dis.readShort() * 0.0125f;

		magnetDataX = readUnsigned(dis) / 10e-5f;
		magnetDataY = readUnsigned(dis) / 10e-5f;
		magnetDataZ = readUnsigned(dis) / 10e-5f;
		magnetDataRef = readUnsigned(dis) / 10e-5f;
	}

	private static float readUnsigned(DataInputStream dis) throws IOException {
		return 5 * dis.readUnsignedShort() / 4096f;
	}

	public long getSatelliteTime() {
		return satelliteTime;
	}

	public void setSatelliteTime(long satelliteTime) {
		this.satelliteTime = satelliteTime;
	}

	public boolean isForcedExecution() {
		return forcedExecution;
	}

	public void setForcedExecution(boolean forcedExecution) {
		this.forcedExecution = forcedExecution;
	}

	public boolean isHeater() {
		return heater;
	}

	public void setHeater(boolean heater) {
		this.heater = heater;
	}

	public boolean isRegulator3v5() {
		return regulator3v5;
	}

	public void setRegulator3v5(boolean regulator3v5) {
		this.regulator3v5 = regulator3v5;
	}

	public boolean isCdh() {
		return cdh;
	}

	public void setCdh(boolean cdh) {
		this.cdh = cdh;
	}

	public boolean isCam() {
		return cam;
	}

	public void setCam(boolean cam) {
		this.cam = cam;
	}

	public boolean isQpsk() {
		return qpsk;
	}

	public void setQpsk(boolean qpsk) {
		this.qpsk = qpsk;
	}

	public boolean isFsk() {
		return fsk;
	}

	public void setFsk(boolean fsk) {
		this.fsk = fsk;
	}

	public boolean isTpr() {
		return tpr;
	}

	public void setTpr(boolean tpr) {
		this.tpr = tpr;
	}

	public int getResetInformationFmr() {
		return resetInformationFmr;
	}

	public void setResetInformationFmr(int resetInformationFmr) {
		this.resetInformationFmr = resetInformationFmr;
	}

	public int getResetInformationCdh() {
		return resetInformationCdh;
	}

	public void setResetInformationCdh(int resetInformationCdh) {
		this.resetInformationCdh = resetInformationCdh;
	}

	public int getResetInformationCw() {
		return resetInformationCw;
	}

	public void setResetInformationCw(int resetInformationCw) {
		this.resetInformationCw = resetInformationCw;
	}

	public int getResetInformationEps() {
		return resetInformationEps;
	}

	public void setResetInformationEps(int resetInformationEps) {
		this.resetInformationEps = resetInformationEps;
	}

	public int getResetInformationSg() {
		return resetInformationSg;
	}

	public void setResetInformationSg(int resetInformationSg) {
		this.resetInformationSg = resetInformationSg;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(float batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public float[] getCurrentData() {
		return currentData;
	}

	public void setCurrentData(float[] currentData) {
		this.currentData = currentData;
	}

	public float getBatteryTemperature1() {
		return batteryTemperature1;
	}

	public void setBatteryTemperature1(float batteryTemperature1) {
		this.batteryTemperature1 = batteryTemperature1;
	}

	public float getBatteryTemperature2() {
		return batteryTemperature2;
	}

	public void setBatteryTemperature2(float batteryTemperature2) {
		this.batteryTemperature2 = batteryTemperature2;
	}

	public float getRegulator5vTemperature1() {
		return regulator5vTemperature1;
	}

	public void setRegulator5vTemperature1(float regulator5vTemperature1) {
		this.regulator5vTemperature1 = regulator5vTemperature1;
	}

	public float getRegulator5vTemperature2() {
		return regulator5vTemperature2;
	}

	public void setRegulator5vTemperature2(float regulator5vTemperature2) {
		this.regulator5vTemperature2 = regulator5vTemperature2;
	}

	public float getRegulator3v5Temperature() {
		return regulator3v5Temperature;
	}

	public void setRegulator3v5Temperature(float regulator3v5Temperature) {
		this.regulator3v5Temperature = regulator3v5Temperature;
	}

	public float getPowerAmplifierTemperature() {
		return powerAmplifierTemperature;
	}

	public void setPowerAmplifierTemperature(float powerAmplifierTemperature) {
		this.powerAmplifierTemperature = powerAmplifierTemperature;
	}

	public float getQpskTransmitterTemperature() {
		return qpskTransmitterTemperature;
	}

	public void setQpskTransmitterTemperature(float qpskTransmitterTemperature) {
		this.qpskTransmitterTemperature = qpskTransmitterTemperature;
	}

	public float getFskTransmitterTemperature() {
		return fskTransmitterTemperature;
	}

	public void setFskTransmitterTemperature(float fskTransmitterTemperature) {
		this.fskTransmitterTemperature = fskTransmitterTemperature;
	}

	public float getSolarXPlusTemperature() {
		return solarXPlusTemperature;
	}

	public void setSolarXPlusTemperature(float solarXPlusTemperature) {
		this.solarXPlusTemperature = solarXPlusTemperature;
	}

	public float getSolarYPlusTemperature() {
		return solarYPlusTemperature;
	}

	public void setSolarYPlusTemperature(float solarYPlusTemperature) {
		this.solarYPlusTemperature = solarYPlusTemperature;
	}

	public float getSolarZPlusTemperature() {
		return solarZPlusTemperature;
	}

	public void setSolarZPlusTemperature(float solarZPlusTemperature) {
		this.solarZPlusTemperature = solarZPlusTemperature;
	}

	public float getSolarXMinusTemperature() {
		return solarXMinusTemperature;
	}

	public void setSolarXMinusTemperature(float solarXMinusTemperature) {
		this.solarXMinusTemperature = solarXMinusTemperature;
	}

	public float getSolarYMinusTemperature() {
		return solarYMinusTemperature;
	}

	public void setSolarYMinusTemperature(float solarYMinusTemperature) {
		this.solarYMinusTemperature = solarYMinusTemperature;
	}

	public float getSolarZMinusTemperature() {
		return solarZMinusTemperature;
	}

	public void setSolarZMinusTemperature(float solarZMinusTemperature) {
		this.solarZMinusTemperature = solarZMinusTemperature;
	}

	public float getBusTransmitterTemperature() {
		return busTransmitterTemperature;
	}

	public void setBusTransmitterTemperature(float busTransmitterTemperature) {
		this.busTransmitterTemperature = busTransmitterTemperature;
	}

	public float getBusReceiverTemperature() {
		return busReceiverTemperature;
	}

	public void setBusReceiverTemperature(float busReceiverTemperature) {
		this.busReceiverTemperature = busReceiverTemperature;
	}

	public float getGyroXTemperature() {
		return gyroXTemperature;
	}

	public void setGyroXTemperature(float gyroXTemperature) {
		this.gyroXTemperature = gyroXTemperature;
	}

	public float getGyroYTemperature() {
		return gyroYTemperature;
	}

	public void setGyroYTemperature(float gyroYTemperature) {
		this.gyroYTemperature = gyroYTemperature;
	}

	public float getGyroZTemperature() {
		return gyroZTemperature;
	}

	public void setGyroZTemperature(float gyroZTemperature) {
		this.gyroZTemperature = gyroZTemperature;
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

	public float getMagnetDataX() {
		return magnetDataX;
	}

	public void setMagnetDataX(float magnetDataX) {
		this.magnetDataX = magnetDataX;
	}

	public float getMagnetDataY() {
		return magnetDataY;
	}

	public void setMagnetDataY(float magnetDataY) {
		this.magnetDataY = magnetDataY;
	}

	public float getMagnetDataZ() {
		return magnetDataZ;
	}

	public void setMagnetDataZ(float magnetDataZ) {
		this.magnetDataZ = magnetDataZ;
	}

	public float getMagnetDataRef() {
		return magnetDataRef;
	}

	public void setMagnetDataRef(float magnetDataRef) {
		this.magnetDataRef = magnetDataRef;
	}

}
