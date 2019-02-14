package ru.r2cloud.jradio.lume1;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class B4Adcs {

	private int extMagValid;
	private float extMagX;
	private float extMagY;
	private float extMagZ;
	private float gpsPosDevX;
	private float gpsPosDevY;
	private float gpsPosDevZ;
	private float gpsPosX;
	private float gpsPosY;
	private float gpsPosZ;
	private int gpsValid;
	private int gyroValid;

	private float gyroX;
	private float gyroY;
	private float gyroZ;

	private float magX;
	private float magY;
	private float magZ;

	private int magValid;
	private byte statusRun;
	private byte acsMode;
	private byte adsMode;
	private byte ephemMode;
	private int bdotDetumb;
	private long bootCause;
	private int bootCount;
	private int curGssb1;
	private int curGssb2;
	private int curPwm;
	private int curGps;
	private int curWde;

	public B4Adcs(BitInputStream bis) throws IOException {
		extMagValid = bis.readUnsignedByte();
		extMagX = bis.readFloat();
		extMagY = bis.readFloat();
		extMagZ = bis.readFloat();
		gpsPosDevX = bis.readFloat();
		gpsPosDevY = bis.readFloat();
		gpsPosDevZ = bis.readFloat();
		gpsPosX = bis.readFloat();
		gpsPosY = bis.readFloat();
		gpsPosZ = bis.readFloat();
		gpsValid = bis.readUnsignedByte();
		gyroValid = bis.readUnsignedByte();

		gyroX = bis.readFloat();
		gyroY = bis.readFloat();
		gyroZ = bis.readFloat();

		magX = bis.readFloat();
		magY = bis.readFloat();
		magZ = bis.readFloat();

		magValid = bis.readUnsignedByte();
		statusRun = bis.readByte();
		acsMode = bis.readByte();
		adsMode = bis.readByte();
		ephemMode = bis.readByte();
		bdotDetumb = bis.readUnsignedByte();
		bootCause = bis.readUnsignedLong(32);
		bootCount = bis.readUnsignedInt(16);
		curGssb1 = bis.readUnsignedInt(16);
		curGssb2 = bis.readUnsignedInt(16);
		curPwm = bis.readUnsignedInt(16);
		curGps = bis.readUnsignedInt(16);
		curWde = bis.readUnsignedInt(16);
	}

	public int getExtMagValid() {
		return extMagValid;
	}

	public void setExtMagValid(int extMagValid) {
		this.extMagValid = extMagValid;
	}

	public float getExtMagX() {
		return extMagX;
	}

	public void setExtMagX(float extMagX) {
		this.extMagX = extMagX;
	}

	public float getExtMagY() {
		return extMagY;
	}

	public void setExtMagY(float extMagY) {
		this.extMagY = extMagY;
	}

	public float getExtMagZ() {
		return extMagZ;
	}

	public void setExtMagZ(float extMagZ) {
		this.extMagZ = extMagZ;
	}

	public float getGpsPosDevX() {
		return gpsPosDevX;
	}

	public void setGpsPosDevX(float gpsPosDevX) {
		this.gpsPosDevX = gpsPosDevX;
	}

	public float getGpsPosDevY() {
		return gpsPosDevY;
	}

	public void setGpsPosDevY(float gpsPosDevY) {
		this.gpsPosDevY = gpsPosDevY;
	}

	public float getGpsPosDevZ() {
		return gpsPosDevZ;
	}

	public void setGpsPosDevZ(float gpsPosDevZ) {
		this.gpsPosDevZ = gpsPosDevZ;
	}

	public float getGpsPosX() {
		return gpsPosX;
	}

	public void setGpsPosX(float gpsPosX) {
		this.gpsPosX = gpsPosX;
	}

	public float getGpsPosY() {
		return gpsPosY;
	}

	public void setGpsPosY(float gpsPosY) {
		this.gpsPosY = gpsPosY;
	}

	public float getGpsPosZ() {
		return gpsPosZ;
	}

	public void setGpsPosZ(float gpsPosZ) {
		this.gpsPosZ = gpsPosZ;
	}

	public int getGpsValid() {
		return gpsValid;
	}

	public void setGpsValid(int gpsValid) {
		this.gpsValid = gpsValid;
	}

	public int getGyroValid() {
		return gyroValid;
	}

	public void setGyroValid(int gyroValid) {
		this.gyroValid = gyroValid;
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

	public int getMagValid() {
		return magValid;
	}

	public void setMagValid(int magValid) {
		this.magValid = magValid;
	}

	public byte getStatusRun() {
		return statusRun;
	}

	public void setStatusRun(byte statusRun) {
		this.statusRun = statusRun;
	}

	public byte getAcsMode() {
		return acsMode;
	}

	public void setAcsMode(byte acsMode) {
		this.acsMode = acsMode;
	}

	public byte getAdsMode() {
		return adsMode;
	}

	public void setAdsMode(byte adsMode) {
		this.adsMode = adsMode;
	}

	public byte getEphemMode() {
		return ephemMode;
	}

	public void setEphemMode(byte ephemMode) {
		this.ephemMode = ephemMode;
	}

	public int getBdotDetumb() {
		return bdotDetumb;
	}

	public void setBdotDetumb(int bdotDetumb) {
		this.bdotDetumb = bdotDetumb;
	}

	public long getBootCause() {
		return bootCause;
	}

	public void setBootCause(long bootCause) {
		this.bootCause = bootCause;
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public int getCurGssb1() {
		return curGssb1;
	}

	public void setCurGssb1(int curGssb1) {
		this.curGssb1 = curGssb1;
	}

	public int getCurGssb2() {
		return curGssb2;
	}

	public void setCurGssb2(int curGssb2) {
		this.curGssb2 = curGssb2;
	}

	public int getCurPwm() {
		return curPwm;
	}

	public void setCurPwm(int curPwm) {
		this.curPwm = curPwm;
	}

	public int getCurGps() {
		return curGps;
	}

	public void setCurGps(int curGps) {
		this.curGps = curGps;
	}

	public int getCurWde() {
		return curWde;
	}

	public void setCurWde(int curWde) {
		this.curWde = curWde;
	}
	
}
