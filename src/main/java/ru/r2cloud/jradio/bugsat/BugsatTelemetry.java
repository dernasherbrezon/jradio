package ru.r2cloud.jradio.bugsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class BugsatTelemetry {

	private int platformId;
	private long uptimeSeconds;
	private long rtcSeconds;
	private int resetCount;
	private int currentMode;
	private long lastBootReason;
	private int memTlmId;
	private long heapFreeBytes;
	private int cdhId;
	private long lastSeenSequenceNumber;
	private int antennaDeployStatus;
	private int pwrTlmId;
	private int lowVoltageCounter;
	private int niceBatteryMv;
	private int rawBatteryMv;
	private float batteryAmps;
	private float pcm3v3Voltage;
	private float pcm3v3Current;
	private float pcm5vVoltage;
	private float pcm5vCurrent;
	private int thermalTlmId;
	private float cpuTemperature;
	private float mirrorCellTemperature;
	private int aocsTlmId;
	private long mode;
	private float sunVectorX;
	private float sunVectorY;
	private float sunVectorZ;
	private float magnetometerX;
	private float magnetometerY;
	private float magnetometerZ;
	private float gyroX;
	private float gyroY;
	private float gyroZ;
	private float imuTemperature;
	private float fineGyroX;
	private float fineGyroY;
	private float fineGyroZ;
	private float wheel1Radsec;
	private float wheel2Radsec;
	private float wheel3Radsec;
	private float wheel4Radsec;
	private int payloadTlmId;
	private int experimentsRun;
	private int experimentsFailed;
	private short lastExperimentRun;
	private int currentState;

	public BugsatTelemetry() {
		// do nothing
	}

	public BugsatTelemetry(DataInputStream dis) throws IOException {
		platformId = dis.readUnsignedShort();
		uptimeSeconds = StreamUtils.readUnsignedInt(dis);
		rtcSeconds = StreamUtils.readUnsignedInt(dis);
		resetCount = (dis.readUnsignedByte() << 16) + (dis.readUnsignedByte() << 8) + dis.readUnsignedByte();
		currentMode = dis.readUnsignedByte();
		lastBootReason = StreamUtils.readUnsignedInt(dis);
		memTlmId = dis.readUnsignedShort();
		heapFreeBytes = StreamUtils.readUnsignedInt(dis);
		cdhId = dis.readUnsignedShort();
		lastSeenSequenceNumber = StreamUtils.readUnsignedInt(dis);
		antennaDeployStatus = dis.readUnsignedByte();
		pwrTlmId = dis.readUnsignedShort();
		lowVoltageCounter = dis.readUnsignedShort();
		niceBatteryMv = dis.readUnsignedShort();
		rawBatteryMv = dis.readUnsignedShort();
		batteryAmps = dis.readUnsignedShort() * 0.005237F;
		pcm3v3Voltage = dis.readUnsignedShort() * 0.003988F;
		pcm3v3Current = dis.readUnsignedShort() * 0.005237F;
		pcm5vVoltage = dis.readUnsignedShort() * 0.005865F;
		pcm5vCurrent = dis.readUnsignedShort() * 0.005237F;
		thermalTlmId = dis.readUnsignedShort();
		cpuTemperature = dis.readShort() / 100.0F;
		mirrorCellTemperature = dis.readShort() / 100.0F;
		aocsTlmId = dis.readUnsignedShort();
		mode = StreamUtils.readUnsignedInt(dis);
		sunVectorX = dis.readShort() / 16384.0F;
		sunVectorY = dis.readShort() / 16384.0F;
		sunVectorZ = dis.readShort() / 16384.0F;
		magnetometerX = dis.readShort() * 0.5F;
		magnetometerY = dis.readShort() * 0.5F;
		magnetometerZ = dis.readShort() * 0.5F;
		gyroX = dis.readShort() * 0.0125F;
		gyroY = dis.readShort() * 0.0125F;
		gyroZ = dis.readShort() * 0.0125F;
		imuTemperature = dis.readShort() * 0.14F + 25;
		fineGyroX = dis.readInt() * (256 / 6300.0F) / 65536;
		fineGyroY = dis.readInt() * (256 / 6300.0F) / 65536;
		fineGyroZ = dis.readInt() * (256 / 6300.0F) / 65536;
		wheel1Radsec = dis.readShort() * 0.3F;
		wheel2Radsec = dis.readShort() * 0.3F;
		wheel3Radsec = dis.readShort() * 0.3F;
		wheel4Radsec = dis.readShort() * 0.3F;
		payloadTlmId = dis.readUnsignedShort();
		experimentsRun = dis.readUnsignedShort();
		experimentsFailed = dis.readUnsignedShort();
		lastExperimentRun = dis.readShort();
		currentState = dis.readUnsignedByte();
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	public long getUptimeSeconds() {
		return uptimeSeconds;
	}

	public void setUptimeSeconds(long uptimeSeconds) {
		this.uptimeSeconds = uptimeSeconds;
	}

	public long getRtcSeconds() {
		return rtcSeconds;
	}

	public void setRtcSeconds(long rtcSeconds) {
		this.rtcSeconds = rtcSeconds;
	}

	public int getResetCount() {
		return resetCount;
	}

	public void setResetCount(int resetCount) {
		this.resetCount = resetCount;
	}

	public int getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(int currentMode) {
		this.currentMode = currentMode;
	}

	public long getLastBootReason() {
		return lastBootReason;
	}

	public void setLastBootReason(long lastBootReason) {
		this.lastBootReason = lastBootReason;
	}

	public int getMemTlmId() {
		return memTlmId;
	}

	public void setMemTlmId(int memTlmId) {
		this.memTlmId = memTlmId;
	}

	public long getHeapFreeBytes() {
		return heapFreeBytes;
	}

	public void setHeapFreeBytes(long heapFreeBytes) {
		this.heapFreeBytes = heapFreeBytes;
	}

	public int getCdhId() {
		return cdhId;
	}

	public void setCdhId(int cdhId) {
		this.cdhId = cdhId;
	}

	public long getLastSeenSequenceNumber() {
		return lastSeenSequenceNumber;
	}

	public void setLastSeenSequenceNumber(long lastSeenSequenceNumber) {
		this.lastSeenSequenceNumber = lastSeenSequenceNumber;
	}

	public int getAntennaDeployStatus() {
		return antennaDeployStatus;
	}

	public void setAntennaDeployStatus(int antennaDeployStatus) {
		this.antennaDeployStatus = antennaDeployStatus;
	}

	public int getPwrTlmId() {
		return pwrTlmId;
	}

	public void setPwrTlmId(int pwrTlmId) {
		this.pwrTlmId = pwrTlmId;
	}

	public int getLowVoltageCounter() {
		return lowVoltageCounter;
	}

	public void setLowVoltageCounter(int lowVoltageCounter) {
		this.lowVoltageCounter = lowVoltageCounter;
	}

	public int getNiceBatteryMv() {
		return niceBatteryMv;
	}

	public void setNiceBatteryMv(int niceBatteryMv) {
		this.niceBatteryMv = niceBatteryMv;
	}

	public int getRawBatteryMv() {
		return rawBatteryMv;
	}

	public void setRawBatteryMv(int rawBatteryMv) {
		this.rawBatteryMv = rawBatteryMv;
	}

	public float getBatteryAmps() {
		return batteryAmps;
	}

	public void setBatteryAmps(float batteryAmps) {
		this.batteryAmps = batteryAmps;
	}

	public float getPcm3v3Voltage() {
		return pcm3v3Voltage;
	}

	public void setPcm3v3Voltage(float pcm3v3Voltage) {
		this.pcm3v3Voltage = pcm3v3Voltage;
	}

	public float getPcm3v3Current() {
		return pcm3v3Current;
	}

	public void setPcm3v3Current(float pcm3v3Current) {
		this.pcm3v3Current = pcm3v3Current;
	}

	public float getPcm5vVoltage() {
		return pcm5vVoltage;
	}

	public void setPcm5vVoltage(float pcm5vVoltage) {
		this.pcm5vVoltage = pcm5vVoltage;
	}

	public float getPcm5vCurrent() {
		return pcm5vCurrent;
	}

	public void setPcm5vCurrent(float pcm5vCurrent) {
		this.pcm5vCurrent = pcm5vCurrent;
	}

	public int getThermalTlmId() {
		return thermalTlmId;
	}

	public void setThermalTlmId(int thermalTlmId) {
		this.thermalTlmId = thermalTlmId;
	}

	public float getCpuTemperature() {
		return cpuTemperature;
	}

	public void setCpuTemperature(float cpuTemperature) {
		this.cpuTemperature = cpuTemperature;
	}

	public float getMirrorCellTemperature() {
		return mirrorCellTemperature;
	}

	public void setMirrorCellTemperature(float mirrorCellTemperature) {
		this.mirrorCellTemperature = mirrorCellTemperature;
	}

	public int getAocsTlmId() {
		return aocsTlmId;
	}

	public void setAocsTlmId(int aocsTlmId) {
		this.aocsTlmId = aocsTlmId;
	}

	public long getMode() {
		return mode;
	}

	public void setMode(long mode) {
		this.mode = mode;
	}

	public float getSunVectorX() {
		return sunVectorX;
	}

	public void setSunVectorX(float sunVectorX) {
		this.sunVectorX = sunVectorX;
	}

	public float getSunVectorY() {
		return sunVectorY;
	}

	public void setSunVectorY(float sunVectorY) {
		this.sunVectorY = sunVectorY;
	}

	public float getSunVectorZ() {
		return sunVectorZ;
	}

	public void setSunVectorZ(float sunVectorZ) {
		this.sunVectorZ = sunVectorZ;
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

	public float getImuTemperature() {
		return imuTemperature;
	}

	public void setImuTemperature(float imuTemperature) {
		this.imuTemperature = imuTemperature;
	}

	public float getFineGyroX() {
		return fineGyroX;
	}

	public void setFineGyroX(float fineGyroX) {
		this.fineGyroX = fineGyroX;
	}

	public float getFineGyroY() {
		return fineGyroY;
	}

	public void setFineGyroY(float fineGyroY) {
		this.fineGyroY = fineGyroY;
	}

	public float getFineGyroZ() {
		return fineGyroZ;
	}

	public void setFineGyroZ(float fineGyroZ) {
		this.fineGyroZ = fineGyroZ;
	}

	public float getWheel1Radsec() {
		return wheel1Radsec;
	}

	public void setWheel1Radsec(float wheel1Radsec) {
		this.wheel1Radsec = wheel1Radsec;
	}

	public float getWheel2Radsec() {
		return wheel2Radsec;
	}

	public void setWheel2Radsec(float wheel2Radsec) {
		this.wheel2Radsec = wheel2Radsec;
	}

	public float getWheel3Radsec() {
		return wheel3Radsec;
	}

	public void setWheel3Radsec(float wheel3Radsec) {
		this.wheel3Radsec = wheel3Radsec;
	}

	public float getWheel4Radsec() {
		return wheel4Radsec;
	}

	public void setWheel4Radsec(float wheel4Radsec) {
		this.wheel4Radsec = wheel4Radsec;
	}

	public int getPayloadTlmId() {
		return payloadTlmId;
	}

	public void setPayloadTlmId(int payloadTlmId) {
		this.payloadTlmId = payloadTlmId;
	}

	public int getExperimentsRun() {
		return experimentsRun;
	}

	public void setExperimentsRun(int experimentsRun) {
		this.experimentsRun = experimentsRun;
	}

	public int getExperimentsFailed() {
		return experimentsFailed;
	}

	public void setExperimentsFailed(int experimentsFailed) {
		this.experimentsFailed = experimentsFailed;
	}

	public short getLastExperimentRun() {
		return lastExperimentRun;
	}

	public void setLastExperimentRun(short lastExperimentRun) {
		this.lastExperimentRun = lastExperimentRun;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

}
