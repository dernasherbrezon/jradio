package ru.r2cloud.jradio.polyitan1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Beacon1 {

	private int deviceErrors;
	private int ss256;
	private int rtcSeconds;
	private int satMode;
	private int submode;
	private int magtlX;
	private int magtlY;
	private int magtlZ;
	private int gyroX;
	private int gyroY;
	private int gyroZ;

	private float muxi3v3;
	private float muxi5v0;
	private float ads1248Tmp;
	private int arege;
	private int antennaStatus;
	private float charge;
	private long chargeCalculated;
	private short eab1;
	private short eab2;
	private short eab3;
	private short vAcb;
	private short cab1;
	private short cab2;
	private short cab3;
	private short cFromSbAll;
	private int cLoadAll;
	private float temperatureAb2;
	private float temperatureAb3;
	private short pSbx;
	private short pSby;
	private short pSbz;

	public Beacon1() {
		// do nothing
	}

	public Beacon1(LittleEndianDataInputStream dis) throws IOException {
		deviceErrors = read3Bytes(dis);
		ss256 = dis.readUnsignedByte();
		rtcSeconds = dis.readInt();
		satMode = dis.readUnsignedByte();
		submode = dis.readUnsignedByte();
		magtlX = read3Bytes(dis);
		magtlY = read3Bytes(dis);
		magtlZ = read3Bytes(dis);
		gyroX = read3Bytes(dis);
		gyroY = read3Bytes(dis);
		gyroZ = read3Bytes(dis);

		muxi3v3 = dis.readUnsignedShort() * 0.1f;
		muxi5v0 = dis.readUnsignedShort() * 0.1f;
		ads1248Tmp = dis.readShort() * 0.1f;
		arege = dis.readUnsignedByte();
		antennaStatus = dis.readUnsignedByte();
		charge = dis.readUnsignedShort() * 0.1f;
		chargeCalculated = dis.readUnsignedInt();
		eab1 = dis.readShort();
		eab2 = dis.readShort();
		eab3 = dis.readShort();
		vAcb = dis.readShort();
		cab1 = dis.readShort();
		cab2 = dis.readShort();
		cab3 = dis.readShort();
		cFromSbAll = dis.readShort();
		cLoadAll = dis.readUnsignedShort();
		temperatureAb2 = dis.readShort() * 0.1f;
		temperatureAb3 = dis.readShort() * 0.1f;
		pSbx = dis.readShort();
		pSby = dis.readShort();
		pSbz = dis.readShort();
	}

	public int getDeviceErrors() {
		return deviceErrors;
	}

	public void setDeviceErrors(int deviceErrors) {
		this.deviceErrors = deviceErrors;
	}

	public int getSs256() {
		return ss256;
	}

	public void setSs256(int ss256) {
		this.ss256 = ss256;
	}

	public int getRtcSeconds() {
		return rtcSeconds;
	}

	public void setRtcSeconds(int rtcSeconds) {
		this.rtcSeconds = rtcSeconds;
	}

	public int getSatMode() {
		return satMode;
	}

	public void setSatMode(int satMode) {
		this.satMode = satMode;
	}

	public int getSubmode() {
		return submode;
	}

	public void setSubmode(int submode) {
		this.submode = submode;
	}

	public int getMagtlX() {
		return magtlX;
	}

	public void setMagtlX(int magtlX) {
		this.magtlX = magtlX;
	}

	public int getMagtlY() {
		return magtlY;
	}

	public void setMagtlY(int magtlY) {
		this.magtlY = magtlY;
	}

	public int getMagtlZ() {
		return magtlZ;
	}

	public void setMagtlZ(int magtlZ) {
		this.magtlZ = magtlZ;
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

	public float getMuxi3v3() {
		return muxi3v3;
	}

	public void setMuxi3v3(float muxi3v3) {
		this.muxi3v3 = muxi3v3;
	}

	public float getMuxi5v0() {
		return muxi5v0;
	}

	public void setMuxi5v0(float muxi5v0) {
		this.muxi5v0 = muxi5v0;
	}

	public float getAds1248Tmp() {
		return ads1248Tmp;
	}

	public void setAds1248Tmp(float ads1248Tmp) {
		this.ads1248Tmp = ads1248Tmp;
	}

	public int getArege() {
		return arege;
	}

	public void setArege(int arege) {
		this.arege = arege;
	}

	public int getAntennaStatus() {
		return antennaStatus;
	}

	public void setAntennaStatus(int antennaStatus) {
		this.antennaStatus = antennaStatus;
	}

	public float getCharge() {
		return charge;
	}

	public void setCharge(float charge) {
		this.charge = charge;
	}

	public long getChargeCalculated() {
		return chargeCalculated;
	}

	public void setChargeCalculated(long chargeCalculated) {
		this.chargeCalculated = chargeCalculated;
	}

	public short getEab1() {
		return eab1;
	}

	public void setEab1(short eab1) {
		this.eab1 = eab1;
	}

	public short getEab2() {
		return eab2;
	}

	public void setEab2(short eab2) {
		this.eab2 = eab2;
	}

	public short getEab3() {
		return eab3;
	}

	public void setEab3(short eab3) {
		this.eab3 = eab3;
	}

	public short getVAcb() {
		return vAcb;
	}

	public void setVAcb(short vAcb) {
		this.vAcb = vAcb;
	}

	public short getCab1() {
		return cab1;
	}

	public void setCab1(short cab1) {
		this.cab1 = cab1;
	}

	public short getCab2() {
		return cab2;
	}

	public void setCab2(short cab2) {
		this.cab2 = cab2;
	}

	public short getCab3() {
		return cab3;
	}

	public void setCab3(short cab3) {
		this.cab3 = cab3;
	}

	public short getCFromSbAll() {
		return cFromSbAll;
	}

	public void setCFromSbAll(short cFromSbAll) {
		this.cFromSbAll = cFromSbAll;
	}

	public int getCLoadAll() {
		return cLoadAll;
	}

	public void setCLoadAll(int cLoadAll) {
		this.cLoadAll = cLoadAll;
	}

	public float getTemperatureAb2() {
		return temperatureAb2;
	}

	public void setTemperatureAb2(float temperatureAb2) {
		this.temperatureAb2 = temperatureAb2;
	}

	public float getTemperatureAb3() {
		return temperatureAb3;
	}

	public void setTemperatureAb3(float temperatureAb3) {
		this.temperatureAb3 = temperatureAb3;
	}

	public short getPSbx() {
		return pSbx;
	}

	public void setPSbx(short pSbx) {
		this.pSbx = pSbx;
	}

	public short getPSby() {
		return pSby;
	}

	public void setPSby(short pSby) {
		this.pSby = pSby;
	}

	public short getPSbz() {
		return pSbz;
	}

	public void setPSbz(short pSbz) {
		this.pSbz = pSbz;
	}

	private static int read3Bytes(LittleEndianDataInputStream dis) throws IOException {
		int b1 = dis.readUnsignedByte();
		int b2 = dis.readUnsignedByte();
		int b3 = dis.readUnsignedByte();
		return (b3 << 16) | (b2 << 8) | b1;
	}

}
