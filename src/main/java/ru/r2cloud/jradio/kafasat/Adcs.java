package ru.r2cloud.jradio.kafasat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Adcs {

	private int attitudeEstimationMode;
	private int controlMode;
	private int adcsRunMode;
	private int asgp4Mode;
	private boolean cubecontrolSignalEnabled;
	private boolean cubecontrolMotorEnabled;
	private boolean cubeSenseEnabled;
	private boolean cubeEnabled1;
	private int cubeError1;
	private long cubeError2;
	private long cubeError3;
	private short estimatedRollAngle;
	private short estimatedPitchAngle;
	private short estimatedYawAngle;
	private short estimatedQ1;
	private short estimatedQ2;
	private short estimatedQ3;
	private short estimatedXAngularRate;
	private short estimatedYAngularRate;
	private short estimatedZAngularRate;
	private short positionX;
	private short positionY;
	private short positionZ;
	private short velocityX;
	private short velocityY;
	private short velocityZ;
	private short latitude;
	private short longitude;
	private short altitude;
	private short ecefX;
	private short ecefY;
	private short ecefZ;

	public Adcs() {
		// do nothing
	}

	public Adcs(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		attitudeEstimationMode = ((raw >> 4) & 0b1111);
		controlMode = (raw & 0b1111);
		raw = dis.readUnsignedByte();
		adcsRunMode = ((raw >> 6) & 0b11);
		asgp4Mode = ((raw >> 4) & 0b11);
		cubecontrolSignalEnabled = (((raw >> 3) & 0b11) > 0);
		cubecontrolMotorEnabled = (((raw >> 2) & 0b11) > 0);
		cubeSenseEnabled = (raw & 0b11) > 0;
		cubeEnabled1 = dis.readUnsignedByte() > 0;
		cubeError1 = dis.readUnsignedByte();
		cubeError2 = dis.readUnsignedInt();
		cubeError3 = dis.readUnsignedInt();
		estimatedRollAngle = dis.readShort();
		estimatedPitchAngle = dis.readShort();
		estimatedYawAngle = dis.readShort();
		estimatedQ1 = dis.readShort();
		estimatedQ2 = dis.readShort();
		estimatedQ3 = dis.readShort();
		estimatedXAngularRate = dis.readShort();
		estimatedYAngularRate = dis.readShort();
		estimatedZAngularRate = dis.readShort();
		positionX = dis.readShort();
		positionY = dis.readShort();
		positionZ = dis.readShort();
		velocityX = dis.readShort();
		velocityY = dis.readShort();
		velocityZ = dis.readShort();
		latitude = dis.readShort();
		longitude = dis.readShort();
		altitude = dis.readShort();
		ecefX = dis.readShort();
		ecefY = dis.readShort();
		ecefZ = dis.readShort();
	}

	public int getAttitudeEstimationMode() {
		return attitudeEstimationMode;
	}

	public void setAttitudeEstimationMode(int attitudeEstimationMode) {
		this.attitudeEstimationMode = attitudeEstimationMode;
	}

	public int getControlMode() {
		return controlMode;
	}

	public void setControlMode(int controlMode) {
		this.controlMode = controlMode;
	}

	public int getAdcsRunMode() {
		return adcsRunMode;
	}

	public void setAdcsRunMode(int adcsRunMode) {
		this.adcsRunMode = adcsRunMode;
	}

	public int getAsgp4Mode() {
		return asgp4Mode;
	}

	public void setAsgp4Mode(int asgp4Mode) {
		this.asgp4Mode = asgp4Mode;
	}

	public boolean isCubecontrolSignalEnabled() {
		return cubecontrolSignalEnabled;
	}

	public void setCubecontrolSignalEnabled(boolean cubecontrolSignalEnabled) {
		this.cubecontrolSignalEnabled = cubecontrolSignalEnabled;
	}

	public boolean isCubecontrolMotorEnabled() {
		return cubecontrolMotorEnabled;
	}

	public void setCubecontrolMotorEnabled(boolean cubecontrolMotorEnabled) {
		this.cubecontrolMotorEnabled = cubecontrolMotorEnabled;
	}

	public boolean isCubeSenseEnabled() {
		return cubeSenseEnabled;
	}

	public void setCubeSenseEnabled(boolean cubeSenseEnabled) {
		this.cubeSenseEnabled = cubeSenseEnabled;
	}

	public boolean isCubeEnabled1() {
		return cubeEnabled1;
	}

	public void setCubeEnabled1(boolean cubeEnabled1) {
		this.cubeEnabled1 = cubeEnabled1;
	}

	public int getCubeError1() {
		return cubeError1;
	}

	public void setCubeError1(int cubeError1) {
		this.cubeError1 = cubeError1;
	}

	public long getCubeError2() {
		return cubeError2;
	}

	public void setCubeError2(long cubeError2) {
		this.cubeError2 = cubeError2;
	}

	public long getCubeError3() {
		return cubeError3;
	}

	public void setCubeError3(long cubeError3) {
		this.cubeError3 = cubeError3;
	}

	public short getEstimatedRollAngle() {
		return estimatedRollAngle;
	}

	public void setEstimatedRollAngle(short estimatedRollAngle) {
		this.estimatedRollAngle = estimatedRollAngle;
	}

	public short getEstimatedPitchAngle() {
		return estimatedPitchAngle;
	}

	public void setEstimatedPitchAngle(short estimatedPitchAngle) {
		this.estimatedPitchAngle = estimatedPitchAngle;
	}

	public short getEstimatedYawAngle() {
		return estimatedYawAngle;
	}

	public void setEstimatedYawAngle(short estimatedYawAngle) {
		this.estimatedYawAngle = estimatedYawAngle;
	}

	public short getEstimatedQ1() {
		return estimatedQ1;
	}

	public void setEstimatedQ1(short estimatedQ1) {
		this.estimatedQ1 = estimatedQ1;
	}

	public short getEstimatedQ2() {
		return estimatedQ2;
	}

	public void setEstimatedQ2(short estimatedQ2) {
		this.estimatedQ2 = estimatedQ2;
	}

	public short getEstimatedQ3() {
		return estimatedQ3;
	}

	public void setEstimatedQ3(short estimatedQ3) {
		this.estimatedQ3 = estimatedQ3;
	}

	public short getEstimatedXAngularRate() {
		return estimatedXAngularRate;
	}

	public void setEstimatedXAngularRate(short estimatedXAngularRate) {
		this.estimatedXAngularRate = estimatedXAngularRate;
	}

	public short getEstimatedYAngularRate() {
		return estimatedYAngularRate;
	}

	public void setEstimatedYAngularRate(short estimatedYAngularRate) {
		this.estimatedYAngularRate = estimatedYAngularRate;
	}

	public short getEstimatedZAngularRate() {
		return estimatedZAngularRate;
	}

	public void setEstimatedZAngularRate(short estimatedZAngularRate) {
		this.estimatedZAngularRate = estimatedZAngularRate;
	}

	public short getPositionX() {
		return positionX;
	}

	public void setPositionX(short positionX) {
		this.positionX = positionX;
	}

	public short getPositionY() {
		return positionY;
	}

	public void setPositionY(short positionY) {
		this.positionY = positionY;
	}

	public short getPositionZ() {
		return positionZ;
	}

	public void setPositionZ(short positionZ) {
		this.positionZ = positionZ;
	}

	public short getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(short velocityX) {
		this.velocityX = velocityX;
	}

	public short getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(short velocityY) {
		this.velocityY = velocityY;
	}

	public short getVelocityZ() {
		return velocityZ;
	}

	public void setVelocityZ(short velocityZ) {
		this.velocityZ = velocityZ;
	}

	public short getLatitude() {
		return latitude;
	}

	public void setLatitude(short latitude) {
		this.latitude = latitude;
	}

	public short getLongitude() {
		return longitude;
	}

	public void setLongitude(short longitude) {
		this.longitude = longitude;
	}

	public short getAltitude() {
		return altitude;
	}

	public void setAltitude(short altitude) {
		this.altitude = altitude;
	}

	public short getEcefX() {
		return ecefX;
	}

	public void setEcefX(short ecefX) {
		this.ecefX = ecefX;
	}

	public short getEcefY() {
		return ecefY;
	}

	public void setEcefY(short ecefY) {
		this.ecefY = ecefY;
	}

	public short getEcefZ() {
		return ecefZ;
	}

	public void setEcefZ(short ecefZ) {
		this.ecefZ = ecefZ;
	}

}
