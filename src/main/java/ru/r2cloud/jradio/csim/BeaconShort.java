package ru.r2cloud.jradio.csim;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.cute.AttStatus;
import ru.r2cloud.jradio.cute.StarIdStep;
import ru.r2cloud.jradio.util.StreamUtils;

public class BeaconShort {

	private int imuInvalidCount;
	private int newPacketCount;
	private boolean imuVectorValid;
	private long hrRunCount;
	private int hrExecTimeMs1;
	private int hrExecTimeMs2;
	private int hrExecTimeMs3;
	private int hrExecTimeMs4;
	private int hrExecTimeMs5;
	private long payloadSecSinceLastTlm;
	private float payloadTlmRxCount;
	private float payloadTlmAckCount;
	private float payloadTlmNakCount;
	private float voltage12p0;
	private float voltage8p0;
	private float voltage5p0;
	private float voltage3p3;
	private float detTemp;
	private float det2Temp;
	private float box1Temp;
	private float imuTemp;
	private float motor1Temp;
	private float motor2Temp;
	private float motor3Temp;
	private float busVoltage;
	private float batteryVoltage;
	private float batteryCurrent;
	private float battery1Temp;
	private float battery2Temp;
	private float userAnalog1;
	private float userAnalog2;
	private int operatingMode;
	private StarIdStep starIdStep;
	private AttStatus attStatus;
	private int detTimeoutCount;
	private int numAttitudeStars;
	private long cyclesSinceCrcData;
	private int gpsLockCount;
	private boolean gpsValid;
	private boolean gpsEnabled;
	private int macrosStatus2;
	private long sdMinuteCur;
	private int sdPercentUsedTotal;
	private int sdPercentUsedFsw;
	private int sdPercentUsedSoh;
	private int sdPercentUsedLine;
	private int sdPercentUsedTbl;
	private int sdPercentUsedPay;
	private long sdrTxFrames;
	private long sdrRxFrames;
	private boolean sdrTx;
	private byte sdrTxPower;
	private boolean sdrRxLock;
	private byte sdrRxPower;
	private int sdrRxFreqOffset;
	private byte sdrTemp;
	private boolean sdrCommError;
	private byte sqChannel;
	private int sqTrapCount;
	private int sqTemp;
	private int aidStatus;
	private int starIdStatus;
	private long powerStatus;

	public BeaconShort() {
		// do nothing
	}

	public BeaconShort(DataInputStream dis) throws IOException {
		dis.skipBytes(4);
		imuInvalidCount = dis.readUnsignedShort();
		newPacketCount = dis.readUnsignedByte();
		imuVectorValid = dis.readBoolean();
		hrRunCount = StreamUtils.readUnsignedInt(dis);
		hrExecTimeMs1 = dis.readUnsignedByte();
		hrExecTimeMs2 = dis.readUnsignedByte();
		hrExecTimeMs3 = dis.readUnsignedByte();
		hrExecTimeMs4 = dis.readUnsignedByte();
		hrExecTimeMs5 = dis.readUnsignedByte();
		payloadSecSinceLastTlm = StreamUtils.readUnsignedInt(dis);
		payloadTlmRxCount = dis.readUnsignedShort();
		payloadTlmAckCount = dis.readUnsignedShort();
		payloadTlmNakCount = dis.readUnsignedShort();
		voltage12p0 = 0.1f * dis.readUnsignedByte();
		voltage8p0 = 0.1f * dis.readUnsignedByte();
		voltage5p0 = 0.025f * dis.readUnsignedByte();
		voltage3p3 = 0.015f * dis.readUnsignedByte();
		detTemp = 0.8f * dis.readByte();
		det2Temp = 0.8f * dis.readByte();
		box1Temp = 0.0049999999f * dis.readShort();
		imuTemp = 0.0049999999f * dis.readShort();
		motor1Temp = 0.0049999999f * dis.readShort();
		motor2Temp = 0.0049999999f * dis.readShort();
		motor3Temp = 0.0049999999f * dis.readShort();
		busVoltage = 0.001f * dis.readUnsignedShort();
		batteryVoltage = 0.0020000001f * dis.readUnsignedShort();
		batteryCurrent = 0.0020000001f * dis.readShort();
		battery1Temp = 0.0049999999f * dis.readShort();
		battery2Temp = 0.0049999999f * dis.readShort();
		userAnalog1 = 0.0050000002f * dis.readInt();
		userAnalog2 = 0.0050000002f * dis.readInt();
		operatingMode = dis.readUnsignedByte();
		starIdStep = StarIdStep.valueOfCode(dis.readUnsignedByte());
		attStatus = AttStatus.valueOfCode(dis.readUnsignedByte());
		detTimeoutCount = dis.readUnsignedShort();
		numAttitudeStars = dis.readUnsignedByte();
		cyclesSinceCrcData = StreamUtils.readUnsignedInt(dis);
		gpsLockCount = dis.readUnsignedShort();
		gpsValid = dis.readBoolean();
		gpsEnabled = dis.readBoolean();
		macrosStatus2 = dis.readShort();
		sdMinuteCur = StreamUtils.readUnsignedInt(dis);
		sdPercentUsedTotal = dis.readUnsignedByte();
		sdPercentUsedFsw = dis.readUnsignedByte();
		sdPercentUsedSoh = dis.readUnsignedByte();
		sdPercentUsedLine = dis.readUnsignedByte();
		sdPercentUsedTbl = dis.readUnsignedByte();
		sdPercentUsedPay = dis.readUnsignedByte();
		sdrTxFrames = StreamUtils.readUnsignedInt(dis);
		sdrRxFrames = StreamUtils.readUnsignedInt(dis);
		sdrTx = dis.readBoolean();
		sdrTxPower = dis.readByte();
		sdrRxLock = dis.readBoolean();
		sdrRxPower = dis.readByte();
		sdrRxFreqOffset = dis.readInt();
		sdrTemp = dis.readByte();
		sdrCommError = dis.readBoolean();
		sqChannel = dis.readByte();
		sqTrapCount = dis.readUnsignedByte();
		sqTemp = dis.readUnsignedByte();
		aidStatus = dis.readUnsignedShort();
		starIdStatus = dis.readUnsignedByte();
		powerStatus = StreamUtils.readUnsignedInt(dis);
	}

	public int getImuInvalidCount() {
		return imuInvalidCount;
	}

	public void setImuInvalidCount(int imuInvalidCount) {
		this.imuInvalidCount = imuInvalidCount;
	}

	public int getNewPacketCount() {
		return newPacketCount;
	}

	public void setNewPacketCount(int newPacketCount) {
		this.newPacketCount = newPacketCount;
	}

	public boolean isImuVectorValid() {
		return imuVectorValid;
	}

	public void setImuVectorValid(boolean imuVectorValid) {
		this.imuVectorValid = imuVectorValid;
	}

	public long getHrRunCount() {
		return hrRunCount;
	}

	public void setHrRunCount(long hrRunCount) {
		this.hrRunCount = hrRunCount;
	}

	public int getHrExecTimeMs1() {
		return hrExecTimeMs1;
	}

	public void setHrExecTimeMs1(int hrExecTimeMs1) {
		this.hrExecTimeMs1 = hrExecTimeMs1;
	}

	public int getHrExecTimeMs2() {
		return hrExecTimeMs2;
	}

	public void setHrExecTimeMs2(int hrExecTimeMs2) {
		this.hrExecTimeMs2 = hrExecTimeMs2;
	}

	public int getHrExecTimeMs3() {
		return hrExecTimeMs3;
	}

	public void setHrExecTimeMs3(int hrExecTimeMs3) {
		this.hrExecTimeMs3 = hrExecTimeMs3;
	}

	public int getHrExecTimeMs4() {
		return hrExecTimeMs4;
	}

	public void setHrExecTimeMs4(int hrExecTimeMs4) {
		this.hrExecTimeMs4 = hrExecTimeMs4;
	}

	public int getHrExecTimeMs5() {
		return hrExecTimeMs5;
	}

	public void setHrExecTimeMs5(int hrExecTimeMs5) {
		this.hrExecTimeMs5 = hrExecTimeMs5;
	}

	public long getPayloadSecSinceLastTlm() {
		return payloadSecSinceLastTlm;
	}

	public void setPayloadSecSinceLastTlm(long payloadSecSinceLastTlm) {
		this.payloadSecSinceLastTlm = payloadSecSinceLastTlm;
	}

	public float getPayloadTlmRxCount() {
		return payloadTlmRxCount;
	}

	public void setPayloadTlmRxCount(float payloadTlmRxCount) {
		this.payloadTlmRxCount = payloadTlmRxCount;
	}

	public float getPayloadTlmAckCount() {
		return payloadTlmAckCount;
	}

	public void setPayloadTlmAckCount(float payloadTlmAckCount) {
		this.payloadTlmAckCount = payloadTlmAckCount;
	}

	public float getPayloadTlmNakCount() {
		return payloadTlmNakCount;
	}

	public void setPayloadTlmNakCount(float payloadTlmNakCount) {
		this.payloadTlmNakCount = payloadTlmNakCount;
	}

	public float getVoltage12p0() {
		return voltage12p0;
	}

	public void setVoltage12p0(float voltage12p0) {
		this.voltage12p0 = voltage12p0;
	}

	public float getVoltage8p0() {
		return voltage8p0;
	}

	public void setVoltage8p0(float voltage8p0) {
		this.voltage8p0 = voltage8p0;
	}

	public float getVoltage5p0() {
		return voltage5p0;
	}

	public void setVoltage5p0(float voltage5p0) {
		this.voltage5p0 = voltage5p0;
	}

	public float getVoltage3p3() {
		return voltage3p3;
	}

	public void setVoltage3p3(float voltage3p3) {
		this.voltage3p3 = voltage3p3;
	}

	public float getDetTemp() {
		return detTemp;
	}

	public void setDetTemp(float detTemp) {
		this.detTemp = detTemp;
	}

	public float getDet2Temp() {
		return det2Temp;
	}

	public void setDet2Temp(float det2Temp) {
		this.det2Temp = det2Temp;
	}

	public float getBox1Temp() {
		return box1Temp;
	}

	public void setBox1Temp(float box1Temp) {
		this.box1Temp = box1Temp;
	}

	public float getImuTemp() {
		return imuTemp;
	}

	public void setImuTemp(float imuTemp) {
		this.imuTemp = imuTemp;
	}

	public float getMotor1Temp() {
		return motor1Temp;
	}

	public void setMotor1Temp(float motor1Temp) {
		this.motor1Temp = motor1Temp;
	}

	public float getMotor2Temp() {
		return motor2Temp;
	}

	public void setMotor2Temp(float motor2Temp) {
		this.motor2Temp = motor2Temp;
	}

	public float getMotor3Temp() {
		return motor3Temp;
	}

	public void setMotor3Temp(float motor3Temp) {
		this.motor3Temp = motor3Temp;
	}

	public float getBusVoltage() {
		return busVoltage;
	}

	public void setBusVoltage(float busVoltage) {
		this.busVoltage = busVoltage;
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

	public float getBattery1Temp() {
		return battery1Temp;
	}

	public void setBattery1Temp(float battery1Temp) {
		this.battery1Temp = battery1Temp;
	}

	public float getBattery2Temp() {
		return battery2Temp;
	}

	public void setBattery2Temp(float battery2Temp) {
		this.battery2Temp = battery2Temp;
	}

	public float getUserAnalog1() {
		return userAnalog1;
	}

	public void setUserAnalog1(float userAnalog1) {
		this.userAnalog1 = userAnalog1;
	}

	public float getUserAnalog2() {
		return userAnalog2;
	}

	public void setUserAnalog2(float userAnalog2) {
		this.userAnalog2 = userAnalog2;
	}

	public int getOperatingMode() {
		return operatingMode;
	}

	public void setOperatingMode(int operatingMode) {
		this.operatingMode = operatingMode;
	}

	public StarIdStep getStarIdStep() {
		return starIdStep;
	}

	public void setStarIdStep(StarIdStep starIdStep) {
		this.starIdStep = starIdStep;
	}

	public AttStatus getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(AttStatus attStatus) {
		this.attStatus = attStatus;
	}

	public int getDetTimeoutCount() {
		return detTimeoutCount;
	}

	public void setDetTimeoutCount(int detTimeoutCount) {
		this.detTimeoutCount = detTimeoutCount;
	}

	public int getNumAttitudeStars() {
		return numAttitudeStars;
	}

	public void setNumAttitudeStars(int numAttitudeStars) {
		this.numAttitudeStars = numAttitudeStars;
	}

	public long getCyclesSinceCrcData() {
		return cyclesSinceCrcData;
	}

	public void setCyclesSinceCrcData(long cyclesSinceCrcData) {
		this.cyclesSinceCrcData = cyclesSinceCrcData;
	}

	public int getGpsLockCount() {
		return gpsLockCount;
	}

	public void setGpsLockCount(int gpsLockCount) {
		this.gpsLockCount = gpsLockCount;
	}

	public boolean isGpsValid() {
		return gpsValid;
	}

	public void setGpsValid(boolean gpsValid) {
		this.gpsValid = gpsValid;
	}

	public boolean isGpsEnabled() {
		return gpsEnabled;
	}

	public void setGpsEnabled(boolean gpsEnabled) {
		this.gpsEnabled = gpsEnabled;
	}

	public int getMacrosStatus2() {
		return macrosStatus2;
	}

	public void setMacrosStatus2(int macrosStatus2) {
		this.macrosStatus2 = macrosStatus2;
	}

	public long getSdMinuteCur() {
		return sdMinuteCur;
	}

	public void setSdMinuteCur(long sdMinuteCur) {
		this.sdMinuteCur = sdMinuteCur;
	}

	public int getSdPercentUsedTotal() {
		return sdPercentUsedTotal;
	}

	public void setSdPercentUsedTotal(int sdPercentUsedTotal) {
		this.sdPercentUsedTotal = sdPercentUsedTotal;
	}

	public int getSdPercentUsedFsw() {
		return sdPercentUsedFsw;
	}

	public void setSdPercentUsedFsw(int sdPercentUsedFsw) {
		this.sdPercentUsedFsw = sdPercentUsedFsw;
	}

	public int getSdPercentUsedSoh() {
		return sdPercentUsedSoh;
	}

	public void setSdPercentUsedSoh(int sdPercentUsedSoh) {
		this.sdPercentUsedSoh = sdPercentUsedSoh;
	}

	public int getSdPercentUsedLine() {
		return sdPercentUsedLine;
	}

	public void setSdPercentUsedLine(int sdPercentUsedLine) {
		this.sdPercentUsedLine = sdPercentUsedLine;
	}

	public int getSdPercentUsedTbl() {
		return sdPercentUsedTbl;
	}

	public void setSdPercentUsedTbl(int sdPercentUsedTbl) {
		this.sdPercentUsedTbl = sdPercentUsedTbl;
	}

	public int getSdPercentUsedPay() {
		return sdPercentUsedPay;
	}

	public void setSdPercentUsedPay(int sdPercentUsedPay) {
		this.sdPercentUsedPay = sdPercentUsedPay;
	}

	public long getSdrTxFrames() {
		return sdrTxFrames;
	}

	public void setSdrTxFrames(long sdrTxFrames) {
		this.sdrTxFrames = sdrTxFrames;
	}

	public long getSdrRxFrames() {
		return sdrRxFrames;
	}

	public void setSdrRxFrames(long sdrRxFrames) {
		this.sdrRxFrames = sdrRxFrames;
	}

	public boolean isSdrTx() {
		return sdrTx;
	}

	public void setSdrTx(boolean sdrTx) {
		this.sdrTx = sdrTx;
	}

	public byte getSdrTxPower() {
		return sdrTxPower;
	}

	public void setSdrTxPower(byte sdrTxPower) {
		this.sdrTxPower = sdrTxPower;
	}

	public boolean isSdrRxLock() {
		return sdrRxLock;
	}

	public void setSdrRxLock(boolean sdrRxLock) {
		this.sdrRxLock = sdrRxLock;
	}

	public byte getSdrRxPower() {
		return sdrRxPower;
	}

	public void setSdrRxPower(byte sdrRxPower) {
		this.sdrRxPower = sdrRxPower;
	}

	public int getSdrRxFreqOffset() {
		return sdrRxFreqOffset;
	}

	public void setSdrRxFreqOffset(int sdrRxFreqOffset) {
		this.sdrRxFreqOffset = sdrRxFreqOffset;
	}

	public byte getSdrTemp() {
		return sdrTemp;
	}

	public void setSdrTemp(byte sdrTemp) {
		this.sdrTemp = sdrTemp;
	}

	public boolean isSdrCommError() {
		return sdrCommError;
	}

	public void setSdrCommError(boolean sdrCommError) {
		this.sdrCommError = sdrCommError;
	}

	public byte getSqChannel() {
		return sqChannel;
	}

	public void setSqChannel(byte sqChannel) {
		this.sqChannel = sqChannel;
	}

	public int getSqTrapCount() {
		return sqTrapCount;
	}

	public void setSqTrapCount(int sqTrapCount) {
		this.sqTrapCount = sqTrapCount;
	}

	public int getSqTemp() {
		return sqTemp;
	}

	public void setSqTemp(int sqTemp) {
		this.sqTemp = sqTemp;
	}

	public int getAidStatus() {
		return aidStatus;
	}

	public void setAidStatus(int aidStatus) {
		this.aidStatus = aidStatus;
	}

	public int getStarIdStatus() {
		return starIdStatus;
	}

	public void setStarIdStatus(int starIdStatus) {
		this.starIdStatus = starIdStatus;
	}

	public long getPowerStatus() {
		return powerStatus;
	}

	public void setPowerStatus(long powerStatus) {
		this.powerStatus = powerStatus;
	}

}
