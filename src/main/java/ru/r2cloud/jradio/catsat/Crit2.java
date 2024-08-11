package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Crit2 {

	private BeaconElementHeader hk1042;
	private short[] pduX3Cout;
	private BeaconElementHeader hk1142;
	private int[] acuPower;
	private BeaconElementHeader hk442;
	private int adcsBootCnt;
	private long adcsClock;
	private BeaconElementHeader hk41502;
	private float[] extgyro;
	private float[] gpsPos;
	private float[] gpsVel;
	private BeaconElementHeader hk41512;
	private int acsMode;
	private int statusExtmag;
	private int[] statusFss;
	private int statusExtgyro;
	private int statusGps;

	public Crit2() {
		// do nothing
	}

	public Crit2(DataInputStream dis) throws IOException {
		hk1042 = new BeaconElementHeader(dis);
		pduX3Cout = StreamUtils.readShortArray(dis, 9);
		hk1142 = new BeaconElementHeader(dis);
		acuPower = StreamUtils.readUnsignedShortArray(dis, 6);
		hk442 = new BeaconElementHeader(dis);
		adcsBootCnt = dis.readUnsignedShort();
		adcsClock = StreamUtils.readUnsignedInt(dis);
		hk41502 = new BeaconElementHeader(dis);
		extgyro = StreamUtils.readFloatArray(dis, 3);
		gpsPos = StreamUtils.readFloatArray(dis, 3);
		gpsVel = StreamUtils.readFloatArray(dis, 3);
		hk41512 = new BeaconElementHeader(dis);
		acsMode = dis.readUnsignedByte();
		statusExtmag = dis.readUnsignedByte();
		statusFss = StreamUtils.readUnsignedByteArray(dis, 5);
		statusExtgyro = dis.readUnsignedByte();
		statusGps = dis.readUnsignedByte();
	}

	public BeaconElementHeader getHk1042() {
		return hk1042;
	}

	public void setHk1042(BeaconElementHeader hk1042) {
		this.hk1042 = hk1042;
	}

	public short[] getPduX3Cout() {
		return pduX3Cout;
	}

	public void setPduX3Cout(short[] pduX3Cout) {
		this.pduX3Cout = pduX3Cout;
	}

	public BeaconElementHeader getHk1142() {
		return hk1142;
	}

	public void setHk1142(BeaconElementHeader hk1142) {
		this.hk1142 = hk1142;
	}

	public int[] getAcuPower() {
		return acuPower;
	}

	public void setAcuPower(int[] acuPower) {
		this.acuPower = acuPower;
	}

	public BeaconElementHeader getHk442() {
		return hk442;
	}

	public void setHk442(BeaconElementHeader hk442) {
		this.hk442 = hk442;
	}

	public int getAdcsBootCnt() {
		return adcsBootCnt;
	}

	public void setAdcsBootCnt(int adcsBootCnt) {
		this.adcsBootCnt = adcsBootCnt;
	}

	public long getAdcsClock() {
		return adcsClock;
	}

	public void setAdcsClock(long adcsClock) {
		this.adcsClock = adcsClock;
	}

	public BeaconElementHeader getHk41502() {
		return hk41502;
	}

	public void setHk41502(BeaconElementHeader hk41502) {
		this.hk41502 = hk41502;
	}

	public float[] getExtgyro() {
		return extgyro;
	}

	public void setExtgyro(float[] extgyro) {
		this.extgyro = extgyro;
	}

	public float[] getGpsPos() {
		return gpsPos;
	}

	public void setGpsPos(float[] gpsPos) {
		this.gpsPos = gpsPos;
	}

	public float[] getGpsVel() {
		return gpsVel;
	}

	public void setGpsVel(float[] gpsVel) {
		this.gpsVel = gpsVel;
	}

	public BeaconElementHeader getHk41512() {
		return hk41512;
	}

	public void setHk41512(BeaconElementHeader hk41512) {
		this.hk41512 = hk41512;
	}

	public int getAcsMode() {
		return acsMode;
	}

	public void setAcsMode(int acsMode) {
		this.acsMode = acsMode;
	}

	public int getStatusExtmag() {
		return statusExtmag;
	}

	public void setStatusExtmag(int statusExtmag) {
		this.statusExtmag = statusExtmag;
	}

	public int[] getStatusFss() {
		return statusFss;
	}

	public void setStatusFss(int[] statusFss) {
		this.statusFss = statusFss;
	}

	public int getStatusExtgyro() {
		return statusExtgyro;
	}

	public void setStatusExtgyro(int statusExtgyro) {
		this.statusExtgyro = statusExtgyro;
	}

	public int getStatusGps() {
		return statusGps;
	}

	public void setStatusGps(int statusGps) {
		this.statusGps = statusGps;
	}

}
