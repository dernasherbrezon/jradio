package ru.r2cloud.jradio.chomptt;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SpacecraftTelemetry {

	private int messageNumber;
	private long time;
	private SpacecraftMode mode;
	private int idleTimer;
	private float vbatVoltage;
	private float solarPanelCurrentXPlus;
	private float solarPanelCurrentXMinus;
	private float solarPanelCurrentYPlus;
	private float solarPanelCurrentZPlus;
	private float solarPanelCurrentZMinus;

	private float stensatBoardTemperature;
	private float epsBoardTemperature;
	private float nexusSBoardTemperature;
	private float acsBoardTemperature;
	private float routerBoardTemperature;
	private float lithiumBoardTemperature;

	private float solarPanelTempXPlus;
	private float solarPanelTempXMinus;
	private float solarPanelTempYPlus;
	private float solarPanelTempZPlus;
	private float solarPanelTempZMinus;

	private float batteryVoltage;
	private float watchdogCurrent;
	private float spacecraftCurrent;
	private float sensorInterfaceCurrent;
	private float gpsArduinoCurrent;
	private float gpsNovatelCurrent;
	private float stensatCurrent;
	private float acsCurrent;
	private float routerCurrent;
	private float lithiumCurrent;
	private float magX;
	private float magY;
	private float magZ;

	public SpacecraftTelemetry() {
		// do nothing
	}

	public SpacecraftTelemetry(LittleEndianDataInputStream dis) throws IOException {
		messageNumber = dis.readUnsignedShort();
		time = dis.readUnsignedInt();
		mode = SpacecraftMode.valueOfCode(dis.readUnsignedByte());
		idleTimer = dis.readUnsignedShort();
		vbatVoltage = dis.readUnsignedShort() * 9.765e-3f;
		solarPanelCurrentXPlus = dis.readShort() * 0.244e-3f;
		solarPanelCurrentXMinus = dis.readShort() * 0.244e-3f;
		solarPanelCurrentYPlus = dis.readShort() * 0.244e-3f;
		solarPanelCurrentZPlus = dis.readShort() * 0.244e-3f;
		solarPanelCurrentZMinus = dis.readShort() * 0.244e-3f;

		stensatBoardTemperature = dis.readUnsignedShort() * 0.49f - 273.15f;
		epsBoardTemperature = dis.readUnsignedShort() * 0.49f - 273.15f;
		nexusSBoardTemperature = dis.readUnsignedShort() * 0.49f - 273.15f;
		acsBoardTemperature = dis.readUnsignedShort() * 0.49f - 273.15f;
		routerBoardTemperature = dis.readUnsignedShort() * 0.49f - 273.15f;
		lithiumBoardTemperature = dis.readUnsignedShort() * 0.49f - 273.15f;

		solarPanelTempXPlus = dis.readShort() * 0.25f;
		solarPanelTempXMinus = dis.readShort() * 0.25f;
		solarPanelTempYPlus = dis.readShort() * 0.25f;
		solarPanelTempZPlus = dis.readShort() * 0.25f;
		solarPanelTempZMinus = dis.readShort() * 0.25f;

		batteryVoltage = dis.readUnsignedShort() * 9.765e-3f;
		watchdogCurrent = dis.readShort() * 0.25e-3f;
		spacecraftCurrent = dis.readShort() * 4.89e-3f;
		sensorInterfaceCurrent = dis.readShort() * 0.25e-3f;
		gpsArduinoCurrent = dis.readShort() * 0.25e-3f;
		gpsNovatelCurrent = dis.readShort();
		stensatCurrent = dis.readShort() * 0.244e-3f;
		acsCurrent = dis.readShort() * 65.1e-6f;
		routerCurrent = dis.readShort() * 0.195e-3f;
		lithiumCurrent = dis.readShort() * 2.5e-3f;
		magX = Float.intBitsToFloat(dis.readInt());
		magY = Float.intBitsToFloat(dis.readInt());
		magZ = Float.intBitsToFloat(dis.readInt());
	}

	public int getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public SpacecraftMode getMode() {
		return mode;
	}

	public void setMode(SpacecraftMode mode) {
		this.mode = mode;
	}

	public int getIdleTimer() {
		return idleTimer;
	}

	public void setIdleTimer(int idleTimer) {
		this.idleTimer = idleTimer;
	}

	public float getVbatVoltage() {
		return vbatVoltage;
	}

	public void setVbatVoltage(float vbatVoltage) {
		this.vbatVoltage = vbatVoltage;
	}

	public float getSolarPanelCurrentXPlus() {
		return solarPanelCurrentXPlus;
	}

	public void setSolarPanelCurrentXPlus(float solarPanelCurrentXPlus) {
		this.solarPanelCurrentXPlus = solarPanelCurrentXPlus;
	}

	public float getSolarPanelCurrentXMinus() {
		return solarPanelCurrentXMinus;
	}

	public void setSolarPanelCurrentXMinus(float solarPanelCurrentXMinus) {
		this.solarPanelCurrentXMinus = solarPanelCurrentXMinus;
	}

	public float getSolarPanelCurrentYPlus() {
		return solarPanelCurrentYPlus;
	}

	public void setSolarPanelCurrentYPlus(float solarPanelCurrentYPlus) {
		this.solarPanelCurrentYPlus = solarPanelCurrentYPlus;
	}

	public float getSolarPanelCurrentZPlus() {
		return solarPanelCurrentZPlus;
	}

	public void setSolarPanelCurrentZPlus(float solarPanelCurrentZPlus) {
		this.solarPanelCurrentZPlus = solarPanelCurrentZPlus;
	}

	public float getSolarPanelCurrentZMinus() {
		return solarPanelCurrentZMinus;
	}

	public void setSolarPanelCurrentZMinus(float solarPanelCurrentZMinus) {
		this.solarPanelCurrentZMinus = solarPanelCurrentZMinus;
	}

	public float getStensatBoardTemperature() {
		return stensatBoardTemperature;
	}

	public void setStensatBoardTemperature(float stensatBoardTemperature) {
		this.stensatBoardTemperature = stensatBoardTemperature;
	}

	public float getEpsBoardTemperature() {
		return epsBoardTemperature;
	}

	public void setEpsBoardTemperature(float epsBoardTemperature) {
		this.epsBoardTemperature = epsBoardTemperature;
	}

	public float getNexusSBoardTemperature() {
		return nexusSBoardTemperature;
	}

	public void setNexusSBoardTemperature(float nexusSBoardTemperature) {
		this.nexusSBoardTemperature = nexusSBoardTemperature;
	}

	public float getAcsBoardTemperature() {
		return acsBoardTemperature;
	}

	public void setAcsBoardTemperature(float acsBoardTemperature) {
		this.acsBoardTemperature = acsBoardTemperature;
	}

	public float getRouterBoardTemperature() {
		return routerBoardTemperature;
	}

	public void setRouterBoardTemperature(float routerBoardTemperature) {
		this.routerBoardTemperature = routerBoardTemperature;
	}

	public float getLithiumBoardTemperature() {
		return lithiumBoardTemperature;
	}

	public void setLithiumBoardTemperature(float lithiumBoardTemperature) {
		this.lithiumBoardTemperature = lithiumBoardTemperature;
	}

	public float getSolarPanelTempXPlus() {
		return solarPanelTempXPlus;
	}

	public void setSolarPanelTempXPlus(float solarPanelTempXPlus) {
		this.solarPanelTempXPlus = solarPanelTempXPlus;
	}

	public float getSolarPanelTempXMinus() {
		return solarPanelTempXMinus;
	}

	public void setSolarPanelTempXMinus(float solarPanelTempXMinus) {
		this.solarPanelTempXMinus = solarPanelTempXMinus;
	}

	public float getSolarPanelTempYPlus() {
		return solarPanelTempYPlus;
	}

	public void setSolarPanelTempYPlus(float solarPanelTempYPlus) {
		this.solarPanelTempYPlus = solarPanelTempYPlus;
	}

	public float getSolarPanelTempZPlus() {
		return solarPanelTempZPlus;
	}

	public void setSolarPanelTempZPlus(float solarPanelTempZPlus) {
		this.solarPanelTempZPlus = solarPanelTempZPlus;
	}

	public float getSolarPanelTempZMinus() {
		return solarPanelTempZMinus;
	}

	public void setSolarPanelTempZMinus(float solarPanelTempZMinus) {
		this.solarPanelTempZMinus = solarPanelTempZMinus;
	}

	public float getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public float getWatchdogCurrent() {
		return watchdogCurrent;
	}

	public void setWatchdogCurrent(float watchdogCurrent) {
		this.watchdogCurrent = watchdogCurrent;
	}

	public float getSpacecraftCurrent() {
		return spacecraftCurrent;
	}

	public void setSpacecraftCurrent(float spacecraftCurrent) {
		this.spacecraftCurrent = spacecraftCurrent;
	}

	public float getSensorInterfaceCurrent() {
		return sensorInterfaceCurrent;
	}

	public void setSensorInterfaceCurrent(float sensorInterfaceCurrent) {
		this.sensorInterfaceCurrent = sensorInterfaceCurrent;
	}

	public float getGpsArduinoCurrent() {
		return gpsArduinoCurrent;
	}

	public void setGpsArduinoCurrent(float gpsArduinoCurrent) {
		this.gpsArduinoCurrent = gpsArduinoCurrent;
	}

	public float getGpsNovatelCurrent() {
		return gpsNovatelCurrent;
	}

	public void setGpsNovatelCurrent(float gpsNovatelCurrent) {
		this.gpsNovatelCurrent = gpsNovatelCurrent;
	}

	public float getStensatCurrent() {
		return stensatCurrent;
	}

	public void setStensatCurrent(float stensatCurrent) {
		this.stensatCurrent = stensatCurrent;
	}

	public float getAcsCurrent() {
		return acsCurrent;
	}

	public void setAcsCurrent(float acsCurrent) {
		this.acsCurrent = acsCurrent;
	}

	public float getRouterCurrent() {
		return routerCurrent;
	}

	public void setRouterCurrent(float routerCurrent) {
		this.routerCurrent = routerCurrent;
	}

	public float getLithiumCurrent() {
		return lithiumCurrent;
	}

	public void setLithiumCurrent(float lithiumCurrent) {
		this.lithiumCurrent = lithiumCurrent;
	}

	public float getMagX() {
		return magX;
	}

	public void setMagX(float magX) {
		this.magX = magX;
	}

	public float getMagY() {
		return magY;
	}

	public void setMagY(float magY) {
		this.magY = magY;
	}

	public float getMagZ() {
		return magZ;
	}

	public void setMagZ(float magZ) {
		this.magZ = magZ;
	}

}
