package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class ADCSBeacon0 {

	private float extmagTemp;
	private float[] mag;
	private float[] extMag;
	private float[] suns;
	private short[] sunsTemp;
	private float[] gyro;
	private float[] gyroTrend;
	private float gyroTemp;
	private float[] extGyro;
	private float extGyroTemp;
	private int extGyroValid;
	private float[] torquerDuty;
	private byte statusMag;
	private byte statusExtMag;
	private byte statusCss;
	private byte statusGyro;
	private byte statusBdot;
	private byte statusRun;
	private int loopTime;
	private int maxLoopTime;
	private float[] bdotRate;
	private float[] bdotDmag;
	private int bdotDetumb;
	private byte acsMode;
	private byte acsDmode;
	private byte adsMode;
	private byte adsDmode;
	private byte ephemMode;
	private byte ephemDmode;

	public ADCSBeacon0() {
		// do nothing
	}

	public ADCSBeacon0(DataInputStream dis) throws IOException {
		extmagTemp = Float.intBitsToFloat(dis.readInt());
		mag = StreamUtils.readFloatArray(dis, 3);
		extMag = StreamUtils.readFloatArray(dis, 3);
		suns = StreamUtils.readFloatArray(dis, 6);
		sunsTemp = StreamUtils.readShortArray(dis, 6);
		gyro = StreamUtils.readFloatArray(dis, 3);
		gyroTrend = StreamUtils.readFloatArray(dis, 3);
		gyroTemp = Float.intBitsToFloat(dis.readInt());
		extGyro = StreamUtils.readFloatArray(dis, 3);
		extGyroTemp = Float.intBitsToFloat(dis.readInt());
		extGyroValid = dis.readUnsignedByte();
		torquerDuty = StreamUtils.readFloatArray(dis, 3);
		statusMag = dis.readByte();
		statusExtMag = dis.readByte();
		statusCss = dis.readByte();
		statusGyro = dis.readByte();
		statusBdot = dis.readByte();
		statusRun = dis.readByte();
		loopTime = dis.readUnsignedShort();
		maxLoopTime = dis.readUnsignedShort();
		bdotRate = StreamUtils.readFloatArray(dis, 2);
		bdotDmag = StreamUtils.readFloatArray(dis, 3);
		bdotDetumb = dis.readUnsignedByte();
		acsMode = dis.readByte();
		acsDmode = dis.readByte();
		adsMode = dis.readByte();
		adsDmode = dis.readByte();
		ephemMode = dis.readByte();
		ephemDmode = dis.readByte();
	}

	public float getExtmagTemp() {
		return extmagTemp;
	}

	public void setExtmagTemp(float extmagTemp) {
		this.extmagTemp = extmagTemp;
	}

	public float[] getMag() {
		return mag;
	}

	public void setMag(float[] mag) {
		this.mag = mag;
	}

	public float[] getExtMag() {
		return extMag;
	}

	public void setExtMag(float[] extMag) {
		this.extMag = extMag;
	}

	public float[] getSuns() {
		return suns;
	}

	public void setSuns(float[] suns) {
		this.suns = suns;
	}

	public short[] getSunsTemp() {
		return sunsTemp;
	}

	public void setSunsTemp(short[] sunsTemp) {
		this.sunsTemp = sunsTemp;
	}

	public float[] getGyro() {
		return gyro;
	}

	public void setGyro(float[] gyro) {
		this.gyro = gyro;
	}

	public float[] getGyroTrend() {
		return gyroTrend;
	}

	public void setGyroTrend(float[] gyroTrend) {
		this.gyroTrend = gyroTrend;
	}

	public float getGyroTemp() {
		return gyroTemp;
	}

	public void setGyroTemp(float gyroTemp) {
		this.gyroTemp = gyroTemp;
	}

	public float[] getExtGyro() {
		return extGyro;
	}

	public void setExtGyro(float[] extGyro) {
		this.extGyro = extGyro;
	}

	public float getExtGyroTemp() {
		return extGyroTemp;
	}

	public void setExtGyroTemp(float extGyroTemp) {
		this.extGyroTemp = extGyroTemp;
	}

	public int getExtGyroValid() {
		return extGyroValid;
	}

	public void setExtGyroValid(int extGyroValid) {
		this.extGyroValid = extGyroValid;
	}

	public float[] getTorquerDuty() {
		return torquerDuty;
	}

	public void setTorquerDuty(float[] torquerDuty) {
		this.torquerDuty = torquerDuty;
	}

	public byte getStatusMag() {
		return statusMag;
	}

	public void setStatusMag(byte statusMag) {
		this.statusMag = statusMag;
	}

	public byte getStatusExtMag() {
		return statusExtMag;
	}

	public void setStatusExtMag(byte statusExtMag) {
		this.statusExtMag = statusExtMag;
	}

	public byte getStatusCss() {
		return statusCss;
	}

	public void setStatusCss(byte statusCss) {
		this.statusCss = statusCss;
	}

	public byte getStatusGyro() {
		return statusGyro;
	}

	public void setStatusGyro(byte statusGyro) {
		this.statusGyro = statusGyro;
	}

	public byte getStatusBdot() {
		return statusBdot;
	}

	public void setStatusBdot(byte statusBdot) {
		this.statusBdot = statusBdot;
	}

	public byte getStatusRun() {
		return statusRun;
	}

	public void setStatusRun(byte statusRun) {
		this.statusRun = statusRun;
	}

	public int getLoopTime() {
		return loopTime;
	}

	public void setLoopTime(int loopTime) {
		this.loopTime = loopTime;
	}

	public int getMaxLoopTime() {
		return maxLoopTime;
	}

	public void setMaxLoopTime(int maxLoopTime) {
		this.maxLoopTime = maxLoopTime;
	}

	public float[] getBdotRate() {
		return bdotRate;
	}

	public void setBdotRate(float[] bdotRate) {
		this.bdotRate = bdotRate;
	}

	public float[] getBdotDmag() {
		return bdotDmag;
	}

	public void setBdotDmag(float[] bdotDmag) {
		this.bdotDmag = bdotDmag;
	}

	public int getBdotDetumb() {
		return bdotDetumb;
	}

	public void setBdotDetumb(int bdotDetumb) {
		this.bdotDetumb = bdotDetumb;
	}

	public byte getAcsMode() {
		return acsMode;
	}

	public void setAcsMode(byte acsMode) {
		this.acsMode = acsMode;
	}

	public byte getAcsDmode() {
		return acsDmode;
	}

	public void setAcsDmode(byte acsDmode) {
		this.acsDmode = acsDmode;
	}

	public byte getAdsMode() {
		return adsMode;
	}

	public void setAdsMode(byte adsMode) {
		this.adsMode = adsMode;
	}

	public byte getAdsDmode() {
		return adsDmode;
	}

	public void setAdsDmode(byte adsDmode) {
		this.adsDmode = adsDmode;
	}

	public byte getEphemMode() {
		return ephemMode;
	}

	public void setEphemMode(byte ephemMode) {
		this.ephemMode = ephemMode;
	}

	public byte getEphemDmode() {
		return ephemDmode;
	}

	public void setEphemDmode(byte ephemDmode) {
		this.ephemDmode = ephemDmode;
	}

}
