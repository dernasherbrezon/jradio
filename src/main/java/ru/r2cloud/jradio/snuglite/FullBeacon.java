package ru.r2cloud.jradio.snuglite;

import java.io.DataInputStream;
import java.io.IOException;

public class FullBeacon extends SimpleBeacon {

	private int batteryCurrent;
	private boolean gpsSideOn;
	private boolean magnetometerOn;
	private boolean sdcardOn;
	private boolean gpsUpOn;
	private boolean uhfOn;
	private boolean boomOn;
	private int currentBoom;
	private int currentUhf;
	private int currentGpsUP;
	private int currentSdcard;
	private int currentMagnetometer;
	private int currentGpsSide;
	private int solarCellVoltageX;
	private int solarCellVoltageY;
	private int solarCellVoltageZ;
	private int solarCellCurrentX;
	private int solarCellCurrentY;
	private int solarCellCurrentZ;
	private float estimatedAttitudeQ0;
	private float estimatedAttitudeQ1;
	private float estimatedAttitudeQ2;
	private float estimatedAttitudeQ3;
	private float gyroBiasRoll;
	private float gyroBiasPitch;
	private float gyroBiasYaw;
	private float estimatedAngularRateRoll;
	private float estimatedAngularRatePitch;
	private float estimatedAngularRateYaw;
	private float angularRateRoll;
	private float angularRatePitch;
	private float angularRateYaw;
	private boolean sun;
	private boolean attitudeConvergence;
	private float attitudeQ0;
	private float attitudeQ1;
	private float attitudeQ2;
	private float attitudeQ3;
	private OperationMode operationMode;
	private int elapsedTime; // min
	private byte solarPlusXTemperature;
	private byte solarPlusYTemperature;
	private byte solarMinusXTemperature;
	private byte solarMinusYTemperature;
	private byte solarMinusZTemperature;
	private byte obc1Temperature;
	private byte obc2Temperature;
	private byte eps1Temperature;
	private byte eps2Temperature;
	private byte eps3Temperature;
	private byte eps4Temperature;
	private byte uhf1Temperature;
	private byte uhf2Temperature;
	private boolean boomReleased;
	private boolean uhfAntennaReleased;
	private int countAntennaReleaseTrial;
	private int countBoomReleaseTrial;

	public FullBeacon() {
		// do nothing
	}

	public FullBeacon(DataInputStream dis) throws IOException {
		super(dis);
		batteryCurrent = dis.readUnsignedShort();
		int raw = dis.readUnsignedByte();
		gpsSideOn = ((raw >> 5) & 0x1) > 0;
		magnetometerOn = ((raw >> 4) & 0x1) > 0;
		sdcardOn = ((raw >> 3) & 0x1) > 0;
		gpsUpOn = ((raw >> 2) & 0x1) > 0;
		uhfOn = ((raw >> 1) & 0x1) > 0;
		boomOn = (raw & 0x1) > 0;
		currentBoom = dis.readUnsignedShort();
		currentUhf = dis.readUnsignedShort();
		currentGpsUP = dis.readUnsignedShort();
		currentSdcard = dis.readUnsignedShort();
		currentMagnetometer = dis.readUnsignedShort();
		currentGpsSide = dis.readUnsignedShort();
		solarCellVoltageX = dis.readUnsignedShort();
		solarCellVoltageY = dis.readUnsignedShort();
		solarCellVoltageZ = dis.readUnsignedShort();
		solarCellCurrentX = dis.readUnsignedShort();
		solarCellCurrentY = dis.readUnsignedShort();
		solarCellCurrentZ = dis.readUnsignedShort();
		estimatedAttitudeQ0 = dis.readFloat();
		estimatedAttitudeQ1 = dis.readFloat();
		estimatedAttitudeQ2 = dis.readFloat();
		estimatedAttitudeQ3 = dis.readFloat();
		gyroBiasRoll = dis.readFloat();
		gyroBiasPitch = dis.readFloat();
		gyroBiasYaw = dis.readFloat();
		estimatedAngularRateRoll = dis.readFloat();
		estimatedAngularRatePitch = dis.readFloat();
		estimatedAngularRateYaw = dis.readFloat();
		angularRateRoll = dis.readFloat();
		angularRatePitch = dis.readFloat();
		angularRateYaw = dis.readFloat();
		raw = dis.readUnsignedByte();
		sun = ((raw >> 1) & 0x1) > 0;
		attitudeConvergence = (raw & 0x1) > 0;
		attitudeQ0 = dis.readFloat();
		attitudeQ1 = dis.readFloat();
		attitudeQ2 = dis.readFloat();
		attitudeQ3 = dis.readFloat();
		operationMode = OperationMode.valufOfCode(dis.readUnsignedByte());
		elapsedTime = dis.readInt();
		solarPlusXTemperature = dis.readByte();
		solarPlusYTemperature = dis.readByte();
		solarMinusXTemperature = dis.readByte();
		solarMinusYTemperature = dis.readByte();
		solarMinusZTemperature = dis.readByte();
		obc1Temperature = dis.readByte();
		obc2Temperature = dis.readByte();
		eps1Temperature = dis.readByte();
		eps2Temperature = dis.readByte();
		eps3Temperature = dis.readByte();
		eps4Temperature = dis.readByte();
		uhf1Temperature = dis.readByte();
		uhf2Temperature = dis.readByte();
		raw = dis.readUnsignedByte();
		boomReleased = ((raw >> 1) & 0x1) > 0;
		uhfAntennaReleased = (raw & 0x1) > 0;
		countAntennaReleaseTrial = dis.readUnsignedByte();
		countBoomReleaseTrial = dis.readUnsignedByte();
	}

	public int getBatteryCurrent() {
		return batteryCurrent;
	}

	public void setBatteryCurrent(int batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public boolean isGpsSideOn() {
		return gpsSideOn;
	}

	public void setGpsSideOn(boolean gpsSideOn) {
		this.gpsSideOn = gpsSideOn;
	}

	public boolean isMagnetometerOn() {
		return magnetometerOn;
	}

	public void setMagnetometerOn(boolean magnetometerOn) {
		this.magnetometerOn = magnetometerOn;
	}

	public boolean isSdcardOn() {
		return sdcardOn;
	}

	public void setSdcardOn(boolean sdcardOn) {
		this.sdcardOn = sdcardOn;
	}

	public boolean isGpsUpOn() {
		return gpsUpOn;
	}

	public void setGpsUpOn(boolean gpsUpOn) {
		this.gpsUpOn = gpsUpOn;
	}

	public boolean isUhfOn() {
		return uhfOn;
	}

	public void setUhfOn(boolean uhfOn) {
		this.uhfOn = uhfOn;
	}

	public boolean isBoomOn() {
		return boomOn;
	}

	public void setBoomOn(boolean boomOn) {
		this.boomOn = boomOn;
	}

	public int getCurrentBoom() {
		return currentBoom;
	}

	public void setCurrentBoom(int currentBoom) {
		this.currentBoom = currentBoom;
	}

	public int getCurrentUhf() {
		return currentUhf;
	}

	public void setCurrentUhf(int currentUhf) {
		this.currentUhf = currentUhf;
	}

	public int getCurrentGpsUP() {
		return currentGpsUP;
	}

	public void setCurrentGpsUP(int currentGpsUP) {
		this.currentGpsUP = currentGpsUP;
	}

	public int getCurrentSdcard() {
		return currentSdcard;
	}

	public void setCurrentSdcard(int currentSdcard) {
		this.currentSdcard = currentSdcard;
	}

	public int getCurrentMagnetometer() {
		return currentMagnetometer;
	}

	public void setCurrentMagnetometer(int currentMagnetometer) {
		this.currentMagnetometer = currentMagnetometer;
	}

	public int getCurrentGpsSide() {
		return currentGpsSide;
	}

	public void setCurrentGpsSide(int currentGpsSide) {
		this.currentGpsSide = currentGpsSide;
	}

	public int getSolarCellVoltageX() {
		return solarCellVoltageX;
	}

	public void setSolarCellVoltageX(int solarCellVoltageX) {
		this.solarCellVoltageX = solarCellVoltageX;
	}

	public int getSolarCellVoltageY() {
		return solarCellVoltageY;
	}

	public void setSolarCellVoltageY(int solarCellVoltageY) {
		this.solarCellVoltageY = solarCellVoltageY;
	}

	public int getSolarCellVoltageZ() {
		return solarCellVoltageZ;
	}

	public void setSolarCellVoltageZ(int solarCellVoltageZ) {
		this.solarCellVoltageZ = solarCellVoltageZ;
	}

	public int getSolarCellCurrentX() {
		return solarCellCurrentX;
	}

	public void setSolarCellCurrentX(int solarCellCurrentX) {
		this.solarCellCurrentX = solarCellCurrentX;
	}

	public int getSolarCellCurrentY() {
		return solarCellCurrentY;
	}

	public void setSolarCellCurrentY(int solarCellCurrentY) {
		this.solarCellCurrentY = solarCellCurrentY;
	}

	public int getSolarCellCurrentZ() {
		return solarCellCurrentZ;
	}

	public void setSolarCellCurrentZ(int solarCellCurrentZ) {
		this.solarCellCurrentZ = solarCellCurrentZ;
	}

	public float getEstimatedAttitudeQ0() {
		return estimatedAttitudeQ0;
	}

	public void setEstimatedAttitudeQ0(float estimatedAttitudeQ0) {
		this.estimatedAttitudeQ0 = estimatedAttitudeQ0;
	}

	public float getEstimatedAttitudeQ1() {
		return estimatedAttitudeQ1;
	}

	public void setEstimatedAttitudeQ1(float estimatedAttitudeQ1) {
		this.estimatedAttitudeQ1 = estimatedAttitudeQ1;
	}

	public float getEstimatedAttitudeQ2() {
		return estimatedAttitudeQ2;
	}

	public void setEstimatedAttitudeQ2(float estimatedAttitudeQ2) {
		this.estimatedAttitudeQ2 = estimatedAttitudeQ2;
	}

	public float getEstimatedAttitudeQ3() {
		return estimatedAttitudeQ3;
	}

	public void setEstimatedAttitudeQ3(float estimatedAttitudeQ3) {
		this.estimatedAttitudeQ3 = estimatedAttitudeQ3;
	}

	public float getGyroBiasRoll() {
		return gyroBiasRoll;
	}

	public void setGyroBiasRoll(float gyroBiasRoll) {
		this.gyroBiasRoll = gyroBiasRoll;
	}

	public float getGyroBiasPitch() {
		return gyroBiasPitch;
	}

	public void setGyroBiasPitch(float gyroBiasPitch) {
		this.gyroBiasPitch = gyroBiasPitch;
	}

	public float getGyroBiasYaw() {
		return gyroBiasYaw;
	}

	public void setGyroBiasYaw(float gyroBiasYaw) {
		this.gyroBiasYaw = gyroBiasYaw;
	}

	public float getEstimatedAngularRateRoll() {
		return estimatedAngularRateRoll;
	}

	public void setEstimatedAngularRateRoll(float estimatedAngularRateRoll) {
		this.estimatedAngularRateRoll = estimatedAngularRateRoll;
	}

	public float getEstimatedAngularRatePitch() {
		return estimatedAngularRatePitch;
	}

	public void setEstimatedAngularRatePitch(float estimatedAngularRatePitch) {
		this.estimatedAngularRatePitch = estimatedAngularRatePitch;
	}

	public float getEstimatedAngularRateYaw() {
		return estimatedAngularRateYaw;
	}

	public void setEstimatedAngularRateYaw(float estimatedAngularRateYaw) {
		this.estimatedAngularRateYaw = estimatedAngularRateYaw;
	}

	public float getAngularRateRoll() {
		return angularRateRoll;
	}

	public void setAngularRateRoll(float angularRateRoll) {
		this.angularRateRoll = angularRateRoll;
	}

	public float getAngularRatePitch() {
		return angularRatePitch;
	}

	public void setAngularRatePitch(float angularRatePitch) {
		this.angularRatePitch = angularRatePitch;
	}

	public float getAngularRateYaw() {
		return angularRateYaw;
	}

	public void setAngularRateYaw(float angularRateYaw) {
		this.angularRateYaw = angularRateYaw;
	}

	public boolean isSun() {
		return sun;
	}

	public void setSun(boolean sun) {
		this.sun = sun;
	}

	public boolean isAttitudeConvergence() {
		return attitudeConvergence;
	}

	public void setAttitudeConvergence(boolean attitudeConvergence) {
		this.attitudeConvergence = attitudeConvergence;
	}

	public float getAttitudeQ0() {
		return attitudeQ0;
	}

	public void setAttitudeQ0(float attitudeQ0) {
		this.attitudeQ0 = attitudeQ0;
	}

	public float getAttitudeQ1() {
		return attitudeQ1;
	}

	public void setAttitudeQ1(float attitudeQ1) {
		this.attitudeQ1 = attitudeQ1;
	}

	public float getAttitudeQ2() {
		return attitudeQ2;
	}

	public void setAttitudeQ2(float attitudeQ2) {
		this.attitudeQ2 = attitudeQ2;
	}

	public float getAttitudeQ3() {
		return attitudeQ3;
	}

	public void setAttitudeQ3(float attitudeQ3) {
		this.attitudeQ3 = attitudeQ3;
	}

	public OperationMode getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(OperationMode operationMode) {
		this.operationMode = operationMode;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public byte getSolarPlusXTemperature() {
		return solarPlusXTemperature;
	}

	public void setSolarPlusXTemperature(byte solarPlusXTemperature) {
		this.solarPlusXTemperature = solarPlusXTemperature;
	}

	public byte getSolarPlusYTemperature() {
		return solarPlusYTemperature;
	}

	public void setSolarPlusYTemperature(byte solarPlusYTemperature) {
		this.solarPlusYTemperature = solarPlusYTemperature;
	}

	public byte getSolarMinusXTemperature() {
		return solarMinusXTemperature;
	}

	public void setSolarMinusXTemperature(byte solarMinusXTemperature) {
		this.solarMinusXTemperature = solarMinusXTemperature;
	}

	public byte getSolarMinusYTemperature() {
		return solarMinusYTemperature;
	}

	public void setSolarMinusYTemperature(byte solarMinusYTemperature) {
		this.solarMinusYTemperature = solarMinusYTemperature;
	}

	public byte getSolarMinusZTemperature() {
		return solarMinusZTemperature;
	}

	public void setSolarMinusZTemperature(byte solarMinusZTemperature) {
		this.solarMinusZTemperature = solarMinusZTemperature;
	}

	public byte getObc1Temperature() {
		return obc1Temperature;
	}

	public void setObc1Temperature(byte obc1Temperature) {
		this.obc1Temperature = obc1Temperature;
	}

	public byte getObc2Temperature() {
		return obc2Temperature;
	}

	public void setObc2Temperature(byte obc2Temperature) {
		this.obc2Temperature = obc2Temperature;
	}

	public byte getEps1Temperature() {
		return eps1Temperature;
	}

	public void setEps1Temperature(byte eps1Temperature) {
		this.eps1Temperature = eps1Temperature;
	}

	public byte getEps2Temperature() {
		return eps2Temperature;
	}

	public void setEps2Temperature(byte eps2Temperature) {
		this.eps2Temperature = eps2Temperature;
	}

	public byte getEps3Temperature() {
		return eps3Temperature;
	}

	public void setEps3Temperature(byte eps3Temperature) {
		this.eps3Temperature = eps3Temperature;
	}

	public byte getUhf1Temperature() {
		return uhf1Temperature;
	}

	public void setUhf1Temperature(byte uhf1Temperature) {
		this.uhf1Temperature = uhf1Temperature;
	}

	public byte getUhf2Temperature() {
		return uhf2Temperature;
	}

	public void setUhf2Temperature(byte uhf2Temperature) {
		this.uhf2Temperature = uhf2Temperature;
	}

	public boolean isBoomReleased() {
		return boomReleased;
	}

	public void setBoomReleased(boolean boomReleased) {
		this.boomReleased = boomReleased;
	}

	public boolean isUhfAntennaReleased() {
		return uhfAntennaReleased;
	}

	public void setUhfAntennaReleased(boolean uhfAntennaReleased) {
		this.uhfAntennaReleased = uhfAntennaReleased;
	}

	public int getCountAntennaReleaseTrial() {
		return countAntennaReleaseTrial;
	}

	public void setCountAntennaReleaseTrial(int countAntennaReleaseTrial) {
		this.countAntennaReleaseTrial = countAntennaReleaseTrial;
	}

	public int getCountBoomReleaseTrial() {
		return countBoomReleaseTrial;
	}

	public void setCountBoomReleaseTrial(int countBoomReleaseTrial) {
		this.countBoomReleaseTrial = countBoomReleaseTrial;
	}

	public byte getEps4Temperature() {
		return eps4Temperature;
	}

	public void setEps4Temperature(byte eps4Temperature) {
		this.eps4Temperature = eps4Temperature;
	}

}
