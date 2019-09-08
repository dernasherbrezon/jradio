package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class ADCSBeacon6 {

	private DataFieldMeta meta;
	private boolean fsMounted;
	private int bootCount;
	private long bootCause;
	private long clock;
	private short tempMcu;
	private short tempRam;
	private int iGssb1;
	private int iGssb2;
	private int iFlash;
	private int iPwm;
	private DataFieldMeta enMeta;
	private int swloadCnt1;
	private boolean gssb1PwrEn;
	private boolean gssb2PwrEn;
	private boolean flashPwrEn;
	private boolean pwmPwrEn;
	private DataFieldMeta tempMeta;
	private float extmagTemp;
	private short[] sunsTemp;
	private float gyroTemp;
	private float extGyroTemp;
	private short[] wheelTemp;
	private int[] wheelCur;

	public ADCSBeacon6() {
		// do nothing
	}

	public ADCSBeacon6(DataInputStream dis) throws IOException {
		meta = new DataFieldMeta(dis);
		fsMounted = dis.readBoolean();
		bootCount = dis.readUnsignedShort();
		bootCause = StreamUtils.readUnsignedInt(dis);
		clock = StreamUtils.readUnsignedInt(dis);
		tempMcu = dis.readShort();
		tempRam = dis.readShort();
		iGssb1 = dis.readUnsignedShort();
		iGssb2 = dis.readUnsignedShort();
		iFlash = dis.readUnsignedShort();
		iPwm = dis.readUnsignedShort();
		enMeta = new DataFieldMeta(dis);
		swloadCnt1 = dis.readUnsignedShort();
		gssb1PwrEn = dis.readBoolean();
		gssb2PwrEn = dis.readBoolean();
		flashPwrEn = dis.readBoolean();
		pwmPwrEn = dis.readBoolean();
		tempMeta = new DataFieldMeta(dis);
		extmagTemp = Float.intBitsToFloat(dis.readInt());
		sunsTemp = StreamUtils.readShortArray(dis, 6);
		gyroTemp = Float.intBitsToFloat(dis.readInt());
		extGyroTemp = Float.intBitsToFloat(dis.readInt());
		wheelTemp = StreamUtils.readShortArray(dis, 4);
		wheelCur = StreamUtils.readUnsignedShortArray(dis, 4);
	}

	public boolean isFsMounted() {
		return fsMounted;
	}

	public void setFsMounted(boolean fsMounted) {
		this.fsMounted = fsMounted;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public long getBootCause() {
		return bootCause;
	}

	public void setBootCause(long bootCause) {
		this.bootCause = bootCause;
	}

	public long getClock() {
		return clock;
	}

	public void setClock(long clock) {
		this.clock = clock;
	}

	public short getTempMcu() {
		return tempMcu;
	}

	public void setTempMcu(short tempMcu) {
		this.tempMcu = tempMcu;
	}

	public short getTempRam() {
		return tempRam;
	}

	public void setTempRam(short tempRam) {
		this.tempRam = tempRam;
	}

	public int getIGssb1() {
		return iGssb1;
	}

	public void setIGssb1(int iGssb1) {
		this.iGssb1 = iGssb1;
	}

	public int getIGssb2() {
		return iGssb2;
	}

	public void setIGssb2(int iGssb2) {
		this.iGssb2 = iGssb2;
	}

	public int getIFlash() {
		return iFlash;
	}

	public void setIFlash(int iFlash) {
		this.iFlash = iFlash;
	}

	public int getIPwm() {
		return iPwm;
	}

	public void setIPwm(int iPwm) {
		this.iPwm = iPwm;
	}

	public int getSwloadCnt1() {
		return swloadCnt1;
	}

	public void setSwloadCnt1(int swloadCnt1) {
		this.swloadCnt1 = swloadCnt1;
	}

	public boolean isGssb1PwrEn() {
		return gssb1PwrEn;
	}

	public void setGssb1PwrEn(boolean gssb1PwrEn) {
		this.gssb1PwrEn = gssb1PwrEn;
	}

	public boolean isGssb2PwrEn() {
		return gssb2PwrEn;
	}

	public void setGssb2PwrEn(boolean gssb2PwrEn) {
		this.gssb2PwrEn = gssb2PwrEn;
	}

	public boolean isFlashPwrEn() {
		return flashPwrEn;
	}

	public void setFlashPwrEn(boolean flashPwrEn) {
		this.flashPwrEn = flashPwrEn;
	}

	public boolean isPwmPwrEn() {
		return pwmPwrEn;
	}

	public void setPwmPwrEn(boolean pwmPwrEn) {
		this.pwmPwrEn = pwmPwrEn;
	}

	public float getExtmagTemp() {
		return extmagTemp;
	}

	public void setExtmagTemp(float extmagTemp) {
		this.extmagTemp = extmagTemp;
	}

	public short[] getSunsTemp() {
		return sunsTemp;
	}

	public void setSunsTemp(short[] sunsTemp) {
		this.sunsTemp = sunsTemp;
	}

	public float getGyroTemp() {
		return gyroTemp;
	}

	public void setGyroTemp(float gyroTemp) {
		this.gyroTemp = gyroTemp;
	}

	public float getExtGyroTemp() {
		return extGyroTemp;
	}

	public void setExtGyroTemp(float extGyroTemp) {
		this.extGyroTemp = extGyroTemp;
	}

	public short[] getWheelTemp() {
		return wheelTemp;
	}

	public void setWheelTemp(short[] wheelTemp) {
		this.wheelTemp = wheelTemp;
	}

	public int[] getWheelCur() {
		return wheelCur;
	}

	public void setWheelCur(int[] wheelCur) {
		this.wheelCur = wheelCur;
	}

	public DataFieldMeta getMeta() {
		return meta;
	}

	public void setMeta(DataFieldMeta meta) {
		this.meta = meta;
	}

	public DataFieldMeta getEnMeta() {
		return enMeta;
	}

	public void setEnMeta(DataFieldMeta enMeta) {
		this.enMeta = enMeta;
	}

	public DataFieldMeta getTempMeta() {
		return tempMeta;
	}

	public void setTempMeta(DataFieldMeta tempMeta) {
		this.tempMeta = tempMeta;
	}

}
