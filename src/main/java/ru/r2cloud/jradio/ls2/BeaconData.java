package ru.r2cloud.jradio.ls2;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BeaconData {

	private SysmgrData sys;
	private CommData comm;
	private BatteryData[] batts;
	private short battPwrDraw;
	private int adcsMode;

	private boolean filterConverged;
	private boolean solarPanelsDeployed;
	private boolean solarSailDeployed;
	private boolean eclipse;

	private float quaternion0;
	private float quaternion1;
	private float quaternion2;
	private float quaternion3;
	private float xRate;
	private float yRate;
	private float zRate;

	private float gyroX;
	private float gyroY;
	private float gyroZ;
	private float internalGyroX;
	private float internalGyroY;
	private float internalGyroZ;

	private int solarSensorXxMinus;
	private int solarSensorXyMinus;
	private int solarSensorYxMinus;
	private int solarSensorYyMinus;
	private int solarSensorZxMinus;
	private int solarSensorZyMinus;
	private int solarSensorXxPlus;
	private int solarSensorXyPlus;
	private int solarSensorYxPlus;
	private int solarSensorYyPlus;

	private int magnetometerXxMinus;
	private int magnetometerYxMinus;
	private int magnetometerZxMinus;
	private int magnetometerXxPlus;
	private int magnetometerYxPlus;
	private int magnetometerZxPlus;
	private int magnetometerXyPlus;
	private int magnetometerYyPlus;
	private int magnetometerZyMinus;
	private int magnetometerZyPlus;

	private short wheelRpm;
	private CameraInfo[] cams;

	private float xTorquerCurrent;
	private float xTorquerVoltage;
	private float yTorquerCurrent;
	private float yTorquerVoltage;
	private float zTorquerCurrent;
	private float zTorquerVoltage;
	private float motorCurrent;
	private float motorVoltage;

	private boolean panelXMinusDeployed;
	private boolean panelXPlusDeployed;
	private boolean panelYMinusDeployed;
	private boolean panelYPlusDeployed;
	private boolean picArmed;
	private boolean picOn;
	private boolean picBusy;
	private boolean motorMoving;

	private int motorPositionCount;
	private int motorPositionLimit;

	public BeaconData() {
		// do nothing
	}

	public BeaconData(LittleEndianDataInputStream dis) throws IOException {
		sys = new SysmgrData(dis);
		comm = new CommData(dis);
		batts = new BatteryData[8];
		for (int i = 0; i < batts.length; i++) {
			batts[i] = new BatteryData(dis);
		}
		battPwrDraw = dis.getBigEndianDataInputStream().readShort();
		adcsMode = dis.readUnsignedByte();
		int raw = dis.readUnsignedByte();
		filterConverged = (raw & 0x1) > 0;
		solarPanelsDeployed = ((raw >> 1) & 0x1) > 0;
		solarSailDeployed = ((raw >> 2) & 0x1) > 0;
		eclipse = ((raw >> 3) & 0x1) > 0;

		quaternion0 = dis.getBigEndianDataInputStream().readShort() / 128f;
		quaternion1 = dis.getBigEndianDataInputStream().readShort() / 128f;
		quaternion2 = dis.getBigEndianDataInputStream().readShort() / 128f;
		quaternion3 = dis.getBigEndianDataInputStream().readShort() / 128f;

		xRate = dis.getBigEndianDataInputStream().readShort() / 128f;
		yRate = dis.getBigEndianDataInputStream().readShort() / 128f;
		zRate = dis.getBigEndianDataInputStream().readShort() / 128f;

		int aHigh = dis.readByte();
		int bHigh = dis.readByte();
		raw = dis.readUnsignedByte();
		gyroX = ((aHigh << 4) + (raw >> 4)) / 8f;
		gyroY = ((bHigh << 4) + (raw & 0b1111)) / 8f;
		aHigh = dis.readByte();
		bHigh = dis.readByte();
		raw = dis.readUnsignedByte();
		gyroZ = ((aHigh << 4) + (raw >> 4)) / 8f;
		internalGyroZ = ((bHigh << 4) + (raw & 0b1111)) / 8f;
		aHigh = dis.readByte();
		bHigh = dis.readByte();
		raw = dis.readUnsignedByte();
		internalGyroX = ((aHigh << 4) + (raw >> 4)) / 8f;
		internalGyroY = ((bHigh << 4) + (raw & 0b1111)) / 8f;

		solarSensorXxMinus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorXyMinus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorYxMinus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorYyMinus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorZxMinus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorZyMinus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorXxPlus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorXyPlus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorYxPlus = dis.getBigEndianDataInputStream().readUnsignedShort();
		solarSensorYyPlus = dis.getBigEndianDataInputStream().readUnsignedShort();

		aHigh = dis.readByte();
		bHigh = dis.readByte();
		raw = dis.readUnsignedByte();
		magnetometerXxMinus = ((aHigh << 4) + (raw >> 4)) * 100;
		magnetometerYxMinus = ((bHigh << 4) + (raw & 0b1111)) * 100;
		aHigh = dis.readByte();
		bHigh = dis.readByte();
		raw = dis.readUnsignedByte();
		magnetometerZxMinus = ((aHigh << 4) + (raw >> 4)) * 100;
		magnetometerZxPlus = ((bHigh << 4) + (raw & 0b1111)) * 100;
		aHigh = dis.readByte();
		bHigh = dis.readByte();
		raw = dis.readUnsignedByte();
		magnetometerXxPlus = ((aHigh << 4) + (raw >> 4)) * 100;
		magnetometerYxPlus = ((bHigh << 4) + (raw & 0b1111)) * 100;
		aHigh = dis.readByte();
		bHigh = dis.readByte();
		raw = dis.readUnsignedByte();
		magnetometerZyMinus = ((aHigh << 4) + (raw >> 4)) * 100;
		magnetometerZyPlus = ((bHigh << 4) + (raw & 0b1111)) * 100;
		aHigh = dis.readByte();
		bHigh = dis.readByte();
		raw = dis.readUnsignedByte();
		magnetometerXyPlus = ((aHigh << 4) + (raw >> 4)) * 100;
		magnetometerYyPlus = ((bHigh << 4) + (raw & 0b1111)) * 100;

		wheelRpm = dis.getBigEndianDataInputStream().readShort();

		cams = new CameraInfo[2];
		for (int i = 0; i < cams.length; i++) {
			cams[i] = new CameraInfo(dis);
		}

		xTorquerCurrent = dis.readUnsignedByte() / 64f;
		xTorquerVoltage = dis.readByte() / 16f;
		yTorquerCurrent = dis.readUnsignedByte() / 64f;
		yTorquerVoltage = dis.readByte() / 16f;
		zTorquerCurrent = dis.readUnsignedByte() / 64f;
		zTorquerVoltage = dis.readByte() / 16f;
		motorCurrent = dis.readUnsignedByte() / 64f;
		motorVoltage = dis.readUnsignedByte() / 16f;

		raw = dis.readUnsignedByte();
		panelXMinusDeployed = (raw & 0x1) > 0;
		panelXPlusDeployed = ((raw >> 1) & 0x1) > 0;
		panelYMinusDeployed = ((raw >> 2) & 0x1) > 0;
		panelYPlusDeployed = ((raw >> 3) & 0x1) > 0;
		picArmed = ((raw >> 4) & 0x1) > 0;
		picOn = ((raw >> 5) & 0x1) > 0;
		picBusy = ((raw >> 6) & 0x1) > 0;
		motorMoving = ((raw >> 7) & 0x1) > 0;

		motorPositionCount = (dis.readByte() << 16) + dis.getBigEndianDataInputStream().readUnsignedShort();
		motorPositionLimit = (dis.readByte() << 16) + dis.getBigEndianDataInputStream().readUnsignedShort();
	}

	public SysmgrData getSys() {
		return sys;
	}

	public void setSys(SysmgrData sys) {
		this.sys = sys;
	}

	public CommData getComm() {
		return comm;
	}

	public void setComm(CommData comm) {
		this.comm = comm;
	}

	public BatteryData[] getBatts() {
		return batts;
	}

	public void setBatts(BatteryData[] batts) {
		this.batts = batts;
	}

	public short getBattPwrDraw() {
		return battPwrDraw;
	}

	public void setBattPwrDraw(short battPwrDraw) {
		this.battPwrDraw = battPwrDraw;
	}

	public int getAdcsMode() {
		return adcsMode;
	}

	public void setAdcsMode(int adcsMode) {
		this.adcsMode = adcsMode;
	}

	public boolean isFilterConverged() {
		return filterConverged;
	}

	public void setFilterConverged(boolean filterConverged) {
		this.filterConverged = filterConverged;
	}

	public boolean isSolarPanelsDeployed() {
		return solarPanelsDeployed;
	}

	public void setSolarPanelsDeployed(boolean solarPanelsDeployed) {
		this.solarPanelsDeployed = solarPanelsDeployed;
	}

	public boolean isSolarSailDeployed() {
		return solarSailDeployed;
	}

	public void setSolarSailDeployed(boolean solarSailDeployed) {
		this.solarSailDeployed = solarSailDeployed;
	}

	public boolean isEclipse() {
		return eclipse;
	}

	public void setEclipse(boolean eclipse) {
		this.eclipse = eclipse;
	}

	public float getQuaternion0() {
		return quaternion0;
	}

	public void setQuaternion0(float quaternion0) {
		this.quaternion0 = quaternion0;
	}

	public float getQuaternion1() {
		return quaternion1;
	}

	public void setQuaternion1(float quaternion1) {
		this.quaternion1 = quaternion1;
	}

	public float getQuaternion2() {
		return quaternion2;
	}

	public void setQuaternion2(float quaternion2) {
		this.quaternion2 = quaternion2;
	}

	public float getQuaternion3() {
		return quaternion3;
	}

	public void setQuaternion3(float quaternion3) {
		this.quaternion3 = quaternion3;
	}

	public float getXRate() {
		return xRate;
	}

	public void setXRate(float xRate) {
		this.xRate = xRate;
	}

	public float getYRate() {
		return yRate;
	}

	public void setYRate(float yRate) {
		this.yRate = yRate;
	}

	public float getZRate() {
		return zRate;
	}

	public void setZRate(float zRate) {
		this.zRate = zRate;
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

	public float getInternalGyroX() {
		return internalGyroX;
	}

	public void setInternalGyroX(float internalGyroX) {
		this.internalGyroX = internalGyroX;
	}

	public float getInternalGyroY() {
		return internalGyroY;
	}

	public void setInternalGyroY(float internalGyroY) {
		this.internalGyroY = internalGyroY;
	}

	public float getInternalGyroZ() {
		return internalGyroZ;
	}

	public void setInternalGyroZ(float internalGyroZ) {
		this.internalGyroZ = internalGyroZ;
	}

	public int getSolarSensorXxMinus() {
		return solarSensorXxMinus;
	}

	public void setSolarSensorXxMinus(int solarSensorXxMinus) {
		this.solarSensorXxMinus = solarSensorXxMinus;
	}

	public int getSolarSensorXyMinus() {
		return solarSensorXyMinus;
	}

	public void setSolarSensorXyMinus(int solarSensorXyMinus) {
		this.solarSensorXyMinus = solarSensorXyMinus;
	}

	public int getSolarSensorYxMinus() {
		return solarSensorYxMinus;
	}

	public void setSolarSensorYxMinus(int solarSensorYxMinus) {
		this.solarSensorYxMinus = solarSensorYxMinus;
	}

	public int getSolarSensorYyMinus() {
		return solarSensorYyMinus;
	}

	public void setSolarSensorYyMinus(int solarSensorYyMinus) {
		this.solarSensorYyMinus = solarSensorYyMinus;
	}

	public int getSolarSensorZxMinus() {
		return solarSensorZxMinus;
	}

	public void setSolarSensorZxMinus(int solarSensorZxMinus) {
		this.solarSensorZxMinus = solarSensorZxMinus;
	}

	public int getSolarSensorZyMinus() {
		return solarSensorZyMinus;
	}

	public void setSolarSensorZyMinus(int solarSensorZyMinus) {
		this.solarSensorZyMinus = solarSensorZyMinus;
	}

	public int getSolarSensorXxPlus() {
		return solarSensorXxPlus;
	}

	public void setSolarSensorXxPlus(int solarSensorXxPlus) {
		this.solarSensorXxPlus = solarSensorXxPlus;
	}

	public int getSolarSensorXyPlus() {
		return solarSensorXyPlus;
	}

	public void setSolarSensorXyPlus(int solarSensorXyPlus) {
		this.solarSensorXyPlus = solarSensorXyPlus;
	}

	public int getSolarSensorYxPlus() {
		return solarSensorYxPlus;
	}

	public void setSolarSensorYxPlus(int solarSensorYxPlus) {
		this.solarSensorYxPlus = solarSensorYxPlus;
	}

	public int getSolarSensorYyPlus() {
		return solarSensorYyPlus;
	}

	public void setSolarSensorYyPlus(int solarSensorYyPlus) {
		this.solarSensorYyPlus = solarSensorYyPlus;
	}

	public int getMagnetometerXxMinus() {
		return magnetometerXxMinus;
	}

	public void setMagnetometerXxMinus(int magnetometerXxMinus) {
		this.magnetometerXxMinus = magnetometerXxMinus;
	}

	public int getMagnetometerYxMinus() {
		return magnetometerYxMinus;
	}

	public void setMagnetometerYxMinus(int magnetometerYxMinus) {
		this.magnetometerYxMinus = magnetometerYxMinus;
	}

	public int getMagnetometerZxMinus() {
		return magnetometerZxMinus;
	}

	public void setMagnetometerZxMinus(int magnetometerZxMinus) {
		this.magnetometerZxMinus = magnetometerZxMinus;
	}

	public int getMagnetometerXxPlus() {
		return magnetometerXxPlus;
	}

	public void setMagnetometerXxPlus(int magnetometerXxPlus) {
		this.magnetometerXxPlus = magnetometerXxPlus;
	}

	public int getMagnetometerYxPlus() {
		return magnetometerYxPlus;
	}

	public void setMagnetometerYxPlus(int magnetometerYxPlus) {
		this.magnetometerYxPlus = magnetometerYxPlus;
	}

	public int getMagnetometerZxPlus() {
		return magnetometerZxPlus;
	}

	public void setMagnetometerZxPlus(int magnetometerZxPlus) {
		this.magnetometerZxPlus = magnetometerZxPlus;
	}

	public int getMagnetometerXyPlus() {
		return magnetometerXyPlus;
	}

	public void setMagnetometerXyPlus(int magnetometerXyPlus) {
		this.magnetometerXyPlus = magnetometerXyPlus;
	}

	public int getMagnetometerYyPlus() {
		return magnetometerYyPlus;
	}

	public void setMagnetometerYyPlus(int magnetometerYyPlus) {
		this.magnetometerYyPlus = magnetometerYyPlus;
	}

	public int getMagnetometerZyMinus() {
		return magnetometerZyMinus;
	}

	public void setMagnetometerZyMinus(int magnetometerZyMinus) {
		this.magnetometerZyMinus = magnetometerZyMinus;
	}

	public int getMagnetometerZyPlus() {
		return magnetometerZyPlus;
	}

	public void setMagnetometerZyPlus(int magnetometerZyPlus) {
		this.magnetometerZyPlus = magnetometerZyPlus;
	}

	public short getWheelRpm() {
		return wheelRpm;
	}

	public void setWheelRpm(short wheelRpm) {
		this.wheelRpm = wheelRpm;
	}

	public CameraInfo[] getCams() {
		return cams;
	}

	public void setCams(CameraInfo[] cams) {
		this.cams = cams;
	}

	public float getXTorquerCurrent() {
		return xTorquerCurrent;
	}

	public void setXTorquerCurrent(float xTorquerCurrent) {
		this.xTorquerCurrent = xTorquerCurrent;
	}

	public float getXTorquerVoltage() {
		return xTorquerVoltage;
	}

	public void setXTorquerVoltage(float xTorquerVoltage) {
		this.xTorquerVoltage = xTorquerVoltage;
	}

	public float getYTorquerCurrent() {
		return yTorquerCurrent;
	}

	public void setYTorquerCurrent(float yTorquerCurrent) {
		this.yTorquerCurrent = yTorquerCurrent;
	}

	public float getYTorquerVoltage() {
		return yTorquerVoltage;
	}

	public void setYTorquerVoltage(float yTorquerVoltage) {
		this.yTorquerVoltage = yTorquerVoltage;
	}

	public float getZTorquerCurrent() {
		return zTorquerCurrent;
	}

	public void setZTorquerCurrent(float zTorquerCurrent) {
		this.zTorquerCurrent = zTorquerCurrent;
	}

	public float getZTorquerVoltage() {
		return zTorquerVoltage;
	}

	public void setZTorquerVoltage(float zTorquerVoltage) {
		this.zTorquerVoltage = zTorquerVoltage;
	}

	public float getMotorCurrent() {
		return motorCurrent;
	}

	public void setMotorCurrent(float motorCurrent) {
		this.motorCurrent = motorCurrent;
	}

	public float getMotorVoltage() {
		return motorVoltage;
	}

	public void setMotorVoltage(float motorVoltage) {
		this.motorVoltage = motorVoltage;
	}

	public boolean isPanelXMinusDeployed() {
		return panelXMinusDeployed;
	}

	public void setPanelXMinusDeployed(boolean panelXMinusDeployed) {
		this.panelXMinusDeployed = panelXMinusDeployed;
	}

	public boolean isPanelXPlusDeployed() {
		return panelXPlusDeployed;
	}

	public void setPanelXPlusDeployed(boolean panelXPlusDeployed) {
		this.panelXPlusDeployed = panelXPlusDeployed;
	}

	public boolean isPanelYMinusDeployed() {
		return panelYMinusDeployed;
	}

	public void setPanelYMinusDeployed(boolean panelYMinusDeployed) {
		this.panelYMinusDeployed = panelYMinusDeployed;
	}

	public boolean isPanelYPlusDeployed() {
		return panelYPlusDeployed;
	}

	public void setPanelYPlusDeployed(boolean panelYPlusDeployed) {
		this.panelYPlusDeployed = panelYPlusDeployed;
	}

	public boolean isPicArmed() {
		return picArmed;
	}

	public void setPicArmed(boolean picArmed) {
		this.picArmed = picArmed;
	}

	public boolean isPicOn() {
		return picOn;
	}

	public void setPicOn(boolean picOn) {
		this.picOn = picOn;
	}

	public boolean isPicBusy() {
		return picBusy;
	}

	public void setPicBusy(boolean picBusy) {
		this.picBusy = picBusy;
	}

	public boolean isMotorMoving() {
		return motorMoving;
	}

	public void setMotorMoving(boolean motorMoving) {
		this.motorMoving = motorMoving;
	}

	public int getMotorPositionCount() {
		return motorPositionCount;
	}

	public void setMotorPositionCount(int motorPositionCount) {
		this.motorPositionCount = motorPositionCount;
	}

	public int getMotorPositionLimit() {
		return motorPositionLimit;
	}

	public void setMotorPositionLimit(int motorPositionLimit) {
		this.motorPositionLimit = motorPositionLimit;
	}

}
