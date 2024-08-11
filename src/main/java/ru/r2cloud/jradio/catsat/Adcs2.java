package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Adcs2 {

	private BeaconElementHeader hk415012;
	private float[] wheelTorque;
	private float[] wheelMomentum;
	private float[] wheelSpeed;
	private int[] wheelEnable;
	private int[] wheelCurrent;
	private short[] wheelTemp;
	private float[] torquerDuty;
	private float[] torquerCalib;
	private BeaconElementHeader hk415112;
	private byte acsMode;
	private byte acsDmode;
	private byte adsMode;
	private byte adsDmode;
	private byte ephemMode;
	private byte ephemDmode;
	private byte spinMode;
	private byte statusMag;
	private byte statusExtmag;
	private byte statusCss;
	private byte[] statusFss;
	private byte statusGyro;
	private byte statusExtgyro;
	private byte statusGps;
	private byte statusBdot;
	private byte statusUkf;
	private byte statusEtime;
	private byte statusEphem;
	private byte statusRun;
	private short looptime;
	private short maxLooptime;
	private float[] bdotRate;
	private float[] bdotDmag;
	private float[] bdotTorquer;
	private int bdotDetumble;

	public Adcs2() {
		// do nothing
	}

	public Adcs2(DataInputStream dis) throws IOException {
		hk415012 = new BeaconElementHeader(dis);
		wheelTorque = StreamUtils.readFloatArray(dis, 4);
		wheelMomentum = StreamUtils.readFloatArray(dis, 4);
		wheelSpeed = StreamUtils.readFloatArray(dis, 4);
		wheelEnable = StreamUtils.readUnsignedByteArray(dis, 4);
		wheelCurrent = StreamUtils.readUnsignedShortArray(dis, 4);
		wheelTemp = StreamUtils.readShortArray(dis, 4);
		torquerDuty = StreamUtils.readFloatArray(dis, 3);
		torquerCalib = StreamUtils.readFloatArray(dis, 3);
		hk415112 = new BeaconElementHeader(dis);
		acsMode = dis.readByte();
		acsDmode = dis.readByte();
		adsMode = dis.readByte();
		adsDmode = dis.readByte();
		ephemMode = dis.readByte();
		ephemDmode = dis.readByte();
		spinMode = dis.readByte();
		statusMag = dis.readByte();
		statusExtmag = dis.readByte();
		statusCss = dis.readByte();
		statusFss = new byte[5];
		dis.readFully(statusFss);
		statusGyro = dis.readByte();
		statusExtgyro = dis.readByte();
		statusGps = dis.readByte();
		statusBdot = dis.readByte();
		statusUkf = dis.readByte();
		statusEtime = dis.readByte();
		statusEphem = dis.readByte();
		statusRun = dis.readByte();
		looptime = dis.readShort();
		maxLooptime = dis.readShort();
		bdotRate = StreamUtils.readFloatArray(dis, 2);
		bdotDmag = StreamUtils.readFloatArray(dis, 3);
		bdotTorquer = StreamUtils.readFloatArray(dis, 3);
		bdotDetumble = dis.readUnsignedByte();
	}

	public BeaconElementHeader getHk415012() {
		return hk415012;
	}

	public void setHk415012(BeaconElementHeader hk415012) {
		this.hk415012 = hk415012;
	}

	public float[] getWheelTorque() {
		return wheelTorque;
	}

	public void setWheelTorque(float[] wheelTorque) {
		this.wheelTorque = wheelTorque;
	}

	public float[] getWheelMomentum() {
		return wheelMomentum;
	}

	public void setWheelMomentum(float[] wheelMomentum) {
		this.wheelMomentum = wheelMomentum;
	}

	public float[] getWheelSpeed() {
		return wheelSpeed;
	}

	public void setWheelSpeed(float[] wheelSpeed) {
		this.wheelSpeed = wheelSpeed;
	}

	public int[] getWheelEnable() {
		return wheelEnable;
	}

	public void setWheelEnable(int[] wheelEnable) {
		this.wheelEnable = wheelEnable;
	}

	public int[] getWheelCurrent() {
		return wheelCurrent;
	}

	public void setWheelCurrent(int[] wheelCurrent) {
		this.wheelCurrent = wheelCurrent;
	}

	public short[] getWheelTemp() {
		return wheelTemp;
	}

	public void setWheelTemp(short[] wheelTemp) {
		this.wheelTemp = wheelTemp;
	}

	public float[] getTorquerDuty() {
		return torquerDuty;
	}

	public void setTorquerDuty(float[] torquerDuty) {
		this.torquerDuty = torquerDuty;
	}

	public float[] getTorquerCalib() {
		return torquerCalib;
	}

	public void setTorquerCalib(float[] torquerCalib) {
		this.torquerCalib = torquerCalib;
	}

	public BeaconElementHeader getHk415112() {
		return hk415112;
	}

	public void setHk415112(BeaconElementHeader hk415112) {
		this.hk415112 = hk415112;
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

	public byte getSpinMode() {
		return spinMode;
	}

	public void setSpinMode(byte spinMode) {
		this.spinMode = spinMode;
	}

	public byte getStatusMag() {
		return statusMag;
	}

	public void setStatusMag(byte statusMag) {
		this.statusMag = statusMag;
	}

	public byte getStatusExtmag() {
		return statusExtmag;
	}

	public void setStatusExtmag(byte statusExtmag) {
		this.statusExtmag = statusExtmag;
	}

	public byte getStatusCss() {
		return statusCss;
	}

	public void setStatusCss(byte statusCss) {
		this.statusCss = statusCss;
	}

	public byte[] getStatusFss() {
		return statusFss;
	}

	public void setStatusFss(byte[] statusFss) {
		this.statusFss = statusFss;
	}

	public byte getStatusGyro() {
		return statusGyro;
	}

	public void setStatusGyro(byte statusGyro) {
		this.statusGyro = statusGyro;
	}

	public byte getStatusExtgyro() {
		return statusExtgyro;
	}

	public void setStatusExtgyro(byte statusExtgyro) {
		this.statusExtgyro = statusExtgyro;
	}

	public byte getStatusGps() {
		return statusGps;
	}

	public void setStatusGps(byte statusGps) {
		this.statusGps = statusGps;
	}

	public byte getStatusBdot() {
		return statusBdot;
	}

	public void setStatusBdot(byte statusBdot) {
		this.statusBdot = statusBdot;
	}

	public byte getStatusUkf() {
		return statusUkf;
	}

	public void setStatusUkf(byte statusUkf) {
		this.statusUkf = statusUkf;
	}

	public byte getStatusEtime() {
		return statusEtime;
	}

	public void setStatusEtime(byte statusEtime) {
		this.statusEtime = statusEtime;
	}

	public byte getStatusEphem() {
		return statusEphem;
	}

	public void setStatusEphem(byte statusEphem) {
		this.statusEphem = statusEphem;
	}

	public byte getStatusRun() {
		return statusRun;
	}

	public void setStatusRun(byte statusRun) {
		this.statusRun = statusRun;
	}

	public short getLooptime() {
		return looptime;
	}

	public void setLooptime(short looptime) {
		this.looptime = looptime;
	}

	public short getMaxLooptime() {
		return maxLooptime;
	}

	public void setMaxLooptime(short maxLooptime) {
		this.maxLooptime = maxLooptime;
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

	public float[] getBdotTorquer() {
		return bdotTorquer;
	}

	public void setBdotTorquer(float[] bdotTorquer) {
		this.bdotTorquer = bdotTorquer;
	}

	public int getBdotDetumble() {
		return bdotDetumble;
	}

	public void setBdotDetumble(int bdotDetumble) {
		this.bdotDetumble = bdotDetumble;
	}

}
