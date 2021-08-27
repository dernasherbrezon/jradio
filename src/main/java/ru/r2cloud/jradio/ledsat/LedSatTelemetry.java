package ru.r2cloud.jradio.ledsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class LedSatTelemetry {

	private int telemetryId;
	private int unixMillis;
	private long unixSeconds;
	private int tlmProcessTimeMillis;
	private int panelXVoltage;
	private int panelYVoltage;
	private int panelZVoltage;
	private int panelXCurrent;
	private int panelYCurrent;
	private int panelZCurrent;
	private int epsBootCause;
	private int epsBatteryMode;
	private int mpttXTemperature;
	private int mpttYTemperature;
	private int epsTemperature;
	private int batter1Temperature;
	private int batter2Temperature;
	private int batter3Temperature;
	private int panelTotalCurrent;
	private int systemConsumedCurrent;
	private int batteryVoltage;
	private int gpsCurrent;
	private int epsBootCount;
	private float paTransceiverTemperature;
	private long transceiverTxCount;
	private long transceiverRxCount;
	private int lastRssi;
	private int radioBootCounter;
	private float obc1Temperature;
	private float obc2Temperature;
	private float gyroX;
	private float gyroY;
	private float gyroZ;
	private int magnetometerX;
	private int magnetometerY;
	private int magnetometerZ;
	private float panelXpTemperature;
	private float panelYpTemperature;
	private float panelXmTemperature;
	private float panelYmTemperature;
	private float panelZpTemperature;
	private float panelZmTemperature;
	private int sunSensorXp;
	private int sunSensorYp;
	private int sunSensorZp;
	private int sunSensorXm;
	private int sunSensorYm;
	private int sunSensorZm;
	private int epsOutputsStatus;
	private int obcBootCounter;
	private int ledStatus;
	private int gpsStatus;
	private long gpsFixTime;
	private float gpsLatitude;
	private float gpsLongitude;
	private long gpsAltitude;
	private int softwareStatus;
	private float externalGyroX;
	private float externalGyroY;
	private float externalGyroZ;
	private int externalMagnetometerX;
	private int externalMagnetometerY;
	private int externalMagnetometerZ;

	public LedSatTelemetry() {
		// do nothing
	}

	public LedSatTelemetry(DataInputStream dis) throws IOException {
		telemetryId = dis.readUnsignedShort();
		unixMillis = dis.readUnsignedShort();
		unixSeconds = StreamUtils.readUnsignedInt(dis);
		tlmProcessTimeMillis = dis.readUnsignedShort();
		panelXVoltage = dis.readUnsignedShort();
		panelYVoltage = dis.readUnsignedShort();
		panelZVoltage = dis.readUnsignedShort();
		panelXCurrent = dis.readUnsignedShort();
		panelYCurrent = dis.readUnsignedShort();
		panelZCurrent = dis.readUnsignedShort();
		epsBootCause = dis.readUnsignedByte();
		epsBatteryMode = dis.readUnsignedByte();
		mpttXTemperature = dis.readUnsignedShort();
		mpttYTemperature = dis.readUnsignedShort();
		epsTemperature = dis.readUnsignedShort();
		batter1Temperature = dis.readUnsignedShort();
		batter2Temperature = dis.readUnsignedShort();
		batter3Temperature = dis.readUnsignedShort();
		panelTotalCurrent = dis.readUnsignedShort();
		systemConsumedCurrent = dis.readUnsignedShort();
		batteryVoltage = dis.readUnsignedShort();
		gpsCurrent = dis.readUnsignedByte();
		epsBootCount = dis.readUnsignedShort();
		paTransceiverTemperature = dis.readUnsignedShort() / 10.0f;
		transceiverTxCount = StreamUtils.readUnsignedInt(dis);
		transceiverRxCount = StreamUtils.readUnsignedInt(dis);
		lastRssi = dis.readUnsignedShort();
		radioBootCounter = dis.readUnsignedShort();
		obc1Temperature = dis.readUnsignedShort() / 10.0f;
		obc2Temperature = dis.readUnsignedShort() / 10.0f;
		gyroX = dis.readUnsignedShort() / 100.0f;
		gyroY = dis.readUnsignedShort() / 100.0f;
		gyroZ = dis.readUnsignedShort() / 100.0f;
		magnetometerX = dis.readUnsignedShort();
		magnetometerY = dis.readUnsignedShort();
		magnetometerZ = dis.readUnsignedShort();
		panelXpTemperature = dis.readUnsignedShort() / 100.0f;
		panelYpTemperature = dis.readUnsignedShort() / 100.0f;
		panelXmTemperature = dis.readUnsignedShort() / 100.0f;
		panelYmTemperature = dis.readUnsignedShort() / 100.0f;
		panelZpTemperature = dis.readUnsignedShort() / 100.0f;
		panelZmTemperature = dis.readUnsignedShort() / 100.0f;
		sunSensorXp = dis.readUnsignedShort();
		sunSensorYp = dis.readUnsignedShort();
		sunSensorZp = dis.readUnsignedShort();
		sunSensorXm = dis.readUnsignedShort();
		sunSensorYm = dis.readUnsignedShort();
		sunSensorZm = dis.readUnsignedShort();
		epsOutputsStatus = dis.readUnsignedByte();
		obcBootCounter = dis.readUnsignedShort();
		ledStatus = dis.readUnsignedByte();
		gpsStatus = dis.readUnsignedByte();
		gpsFixTime = StreamUtils.readUnsignedInt(dis);
		gpsLatitude = StreamUtils.readUnsignedInt(dis) / 1000000.0f;
		gpsLongitude = StreamUtils.readUnsignedInt(dis) / 1000000.0f;
		gpsAltitude = StreamUtils.readUnsignedInt(dis);
		softwareStatus = dis.readUnsignedByte();
		externalGyroX = dis.readUnsignedShort() / 100.0f;
		externalGyroY = dis.readUnsignedShort() / 100.0f;
		externalGyroZ = dis.readUnsignedShort() / 100.0f;
		externalMagnetometerX = dis.readUnsignedShort();
		externalMagnetometerY = dis.readUnsignedShort();
		externalMagnetometerZ = dis.readUnsignedShort();
	}

	public int getTelemetryId() {
		return telemetryId;
	}

	public void setTelemetryId(int telemetryId) {
		this.telemetryId = telemetryId;
	}

	public int getUnixMillis() {
		return unixMillis;
	}

	public void setUnixMillis(int unixMillis) {
		this.unixMillis = unixMillis;
	}

	public long getUnixSeconds() {
		return unixSeconds;
	}

	public void setUnixSeconds(long unixSeconds) {
		this.unixSeconds = unixSeconds;
	}

	public int getTlmProcessTimeMillis() {
		return tlmProcessTimeMillis;
	}

	public void setTlmProcessTimeMillis(int tlmProcessTimeMillis) {
		this.tlmProcessTimeMillis = tlmProcessTimeMillis;
	}

	public int getPanelXVoltage() {
		return panelXVoltage;
	}

	public void setPanelXVoltage(int panelXVoltage) {
		this.panelXVoltage = panelXVoltage;
	}

	public int getPanelYVoltage() {
		return panelYVoltage;
	}

	public void setPanelYVoltage(int panelYVoltage) {
		this.panelYVoltage = panelYVoltage;
	}

	public int getPanelZVoltage() {
		return panelZVoltage;
	}

	public void setPanelZVoltage(int panelZVoltage) {
		this.panelZVoltage = panelZVoltage;
	}

	public int getPanelXCurrent() {
		return panelXCurrent;
	}

	public void setPanelXCurrent(int panelXCurrent) {
		this.panelXCurrent = panelXCurrent;
	}

	public int getPanelYCurrent() {
		return panelYCurrent;
	}

	public void setPanelYCurrent(int panelYCurrent) {
		this.panelYCurrent = panelYCurrent;
	}

	public int getPanelZCurrent() {
		return panelZCurrent;
	}

	public void setPanelZCurrent(int panelZCurrent) {
		this.panelZCurrent = panelZCurrent;
	}

	public int getEpsBootCause() {
		return epsBootCause;
	}

	public void setEpsBootCause(int epsBootCause) {
		this.epsBootCause = epsBootCause;
	}

	public int getEpsBatteryMode() {
		return epsBatteryMode;
	}

	public void setEpsBatteryMode(int epsBatteryMode) {
		this.epsBatteryMode = epsBatteryMode;
	}

	public int getMpttXTemperature() {
		return mpttXTemperature;
	}

	public void setMpttXTemperature(int mpttXTemperature) {
		this.mpttXTemperature = mpttXTemperature;
	}

	public int getMpttYTemperature() {
		return mpttYTemperature;
	}

	public void setMpttYTemperature(int mpttYTemperature) {
		this.mpttYTemperature = mpttYTemperature;
	}

	public int getEpsTemperature() {
		return epsTemperature;
	}

	public void setEpsTemperature(int epsTemperature) {
		this.epsTemperature = epsTemperature;
	}

	public int getBatter1Temperature() {
		return batter1Temperature;
	}

	public void setBatter1Temperature(int batter1Temperature) {
		this.batter1Temperature = batter1Temperature;
	}

	public int getBatter2Temperature() {
		return batter2Temperature;
	}

	public void setBatter2Temperature(int batter2Temperature) {
		this.batter2Temperature = batter2Temperature;
	}

	public int getBatter3Temperature() {
		return batter3Temperature;
	}

	public void setBatter3Temperature(int batter3Temperature) {
		this.batter3Temperature = batter3Temperature;
	}

	public int getPanelTotalCurrent() {
		return panelTotalCurrent;
	}

	public void setPanelTotalCurrent(int panelTotalCurrent) {
		this.panelTotalCurrent = panelTotalCurrent;
	}

	public int getSystemConsumedCurrent() {
		return systemConsumedCurrent;
	}

	public void setSystemConsumedCurrent(int systemConsumedCurrent) {
		this.systemConsumedCurrent = systemConsumedCurrent;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getGpsCurrent() {
		return gpsCurrent;
	}

	public void setGpsCurrent(int gpsCurrent) {
		this.gpsCurrent = gpsCurrent;
	}

	public int getEpsBootCount() {
		return epsBootCount;
	}

	public void setEpsBootCount(int epsBootCount) {
		this.epsBootCount = epsBootCount;
	}

	public float getPaTransceiverTemperature() {
		return paTransceiverTemperature;
	}

	public void setPaTransceiverTemperature(float paTransceiverTemperature) {
		this.paTransceiverTemperature = paTransceiverTemperature;
	}

	public long getTransceiverTxCount() {
		return transceiverTxCount;
	}

	public void setTransceiverTxCount(long transceiverTxCount) {
		this.transceiverTxCount = transceiverTxCount;
	}

	public long getTransceiverRxCount() {
		return transceiverRxCount;
	}

	public void setTransceiverRxCount(long transceiverRxCount) {
		this.transceiverRxCount = transceiverRxCount;
	}

	public int getLastRssi() {
		return lastRssi;
	}

	public void setLastRssi(int lastRssi) {
		this.lastRssi = lastRssi;
	}

	public int getRadioBootCounter() {
		return radioBootCounter;
	}

	public void setRadioBootCounter(int radioBootCounter) {
		this.radioBootCounter = radioBootCounter;
	}

	public float getObc1Temperature() {
		return obc1Temperature;
	}

	public void setObc1Temperature(float obc1Temperature) {
		this.obc1Temperature = obc1Temperature;
	}

	public float getObc2Temperature() {
		return obc2Temperature;
	}

	public void setObc2Temperature(float obc2Temperature) {
		this.obc2Temperature = obc2Temperature;
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

	public float getPanelXpTemperature() {
		return panelXpTemperature;
	}

	public void setPanelXpTemperature(float panelXpTemperature) {
		this.panelXpTemperature = panelXpTemperature;
	}

	public float getPanelYpTemperature() {
		return panelYpTemperature;
	}

	public void setPanelYpTemperature(float panelYpTemperature) {
		this.panelYpTemperature = panelYpTemperature;
	}

	public float getPanelXmTemperature() {
		return panelXmTemperature;
	}

	public void setPanelXmTemperature(float panelXmTemperature) {
		this.panelXmTemperature = panelXmTemperature;
	}

	public float getPanelYmTemperature() {
		return panelYmTemperature;
	}

	public void setPanelYmTemperature(float panelYmTemperature) {
		this.panelYmTemperature = panelYmTemperature;
	}

	public float getPanelZpTemperature() {
		return panelZpTemperature;
	}

	public void setPanelZpTemperature(float panelZpTemperature) {
		this.panelZpTemperature = panelZpTemperature;
	}

	public float getPanelZmTemperature() {
		return panelZmTemperature;
	}

	public void setPanelZmTemperature(float panelZmTemperature) {
		this.panelZmTemperature = panelZmTemperature;
	}

	public int getSunSensorXp() {
		return sunSensorXp;
	}

	public void setSunSensorXp(int sunSensorXp) {
		this.sunSensorXp = sunSensorXp;
	}

	public int getSunSensorYp() {
		return sunSensorYp;
	}

	public void setSunSensorYp(int sunSensorYp) {
		this.sunSensorYp = sunSensorYp;
	}

	public int getSunSensorZp() {
		return sunSensorZp;
	}

	public void setSunSensorZp(int sunSensorZp) {
		this.sunSensorZp = sunSensorZp;
	}

	public int getSunSensorXm() {
		return sunSensorXm;
	}

	public void setSunSensorXm(int sunSensorXm) {
		this.sunSensorXm = sunSensorXm;
	}

	public int getSunSensorYm() {
		return sunSensorYm;
	}

	public void setSunSensorYm(int sunSensorYm) {
		this.sunSensorYm = sunSensorYm;
	}

	public int getSunSensorZm() {
		return sunSensorZm;
	}

	public void setSunSensorZm(int sunSensorZm) {
		this.sunSensorZm = sunSensorZm;
	}

	public int getEpsOutputsStatus() {
		return epsOutputsStatus;
	}

	public void setEpsOutputsStatus(int epsOutputsStatus) {
		this.epsOutputsStatus = epsOutputsStatus;
	}

	public int getObcBootCounter() {
		return obcBootCounter;
	}

	public void setObcBootCounter(int obcBootCounter) {
		this.obcBootCounter = obcBootCounter;
	}

	public int getLedStatus() {
		return ledStatus;
	}

	public void setLedStatus(int ledStatus) {
		this.ledStatus = ledStatus;
	}

	public int getGpsStatus() {
		return gpsStatus;
	}

	public void setGpsStatus(int gpsStatus) {
		this.gpsStatus = gpsStatus;
	}

	public long getGpsFixTime() {
		return gpsFixTime;
	}

	public void setGpsFixTime(long gpsFixTime) {
		this.gpsFixTime = gpsFixTime;
	}

	public float getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(float gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public float getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public long getGpsAltitude() {
		return gpsAltitude;
	}

	public void setGpsAltitude(long gpsAltitude) {
		this.gpsAltitude = gpsAltitude;
	}

	public int getSoftwareStatus() {
		return softwareStatus;
	}

	public void setSoftwareStatus(int softwareStatus) {
		this.softwareStatus = softwareStatus;
	}

	public float getExternalGyroX() {
		return externalGyroX;
	}

	public void setExternalGyroX(float externalGyroX) {
		this.externalGyroX = externalGyroX;
	}

	public float getExternalGyroY() {
		return externalGyroY;
	}

	public void setExternalGyroY(float externalGyroY) {
		this.externalGyroY = externalGyroY;
	}

	public float getExternalGyroZ() {
		return externalGyroZ;
	}

	public void setExternalGyroZ(float externalGyroZ) {
		this.externalGyroZ = externalGyroZ;
	}

	public int getExternalMagnetometerX() {
		return externalMagnetometerX;
	}

	public void setExternalMagnetometerX(int externalMagnetometerX) {
		this.externalMagnetometerX = externalMagnetometerX;
	}

	public int getExternalMagnetometerY() {
		return externalMagnetometerY;
	}

	public void setExternalMagnetometerY(int externalMagnetometerY) {
		this.externalMagnetometerY = externalMagnetometerY;
	}

	public int getExternalMagnetometerZ() {
		return externalMagnetometerZ;
	}

	public void setExternalMagnetometerZ(int externalMagnetometerZ) {
		this.externalMagnetometerZ = externalMagnetometerZ;
	}

}
