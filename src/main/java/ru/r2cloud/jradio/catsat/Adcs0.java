package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Adcs0 {

	private BeaconElementHeader hk41507;
	private float[] extmag;
	private float[] torquerDuty;
	private BeaconElementHeader hk41517;
	private float[] bdotRate;
	private float[] bdotDmag;
	private float[] bdotTorquer;
	private int bdotDetumble;
	private BeaconElementHeader hk41547;
	private float[] ctrlRefq;
	private float[] ctrlErrq;
	private float[] ctrlM;
	private float[] ctrlMwspeed;
	private float[] ctrlEuleroff;
	private float[] ctrlBtorque;

	public Adcs0() {
		// do nothing
	}

	public Adcs0(DataInputStream dis) throws IOException {
		hk41507 = new BeaconElementHeader(dis);
		extmag = StreamUtils.readFloatArray(dis, 3);
		torquerDuty = StreamUtils.readFloatArray(dis, 3);
		hk41517 = new BeaconElementHeader(dis);
		bdotRate = StreamUtils.readFloatArray(dis, 2);
		bdotDmag = StreamUtils.readFloatArray(dis, 3);
		bdotTorquer = StreamUtils.readFloatArray(dis, 3);
		bdotDetumble = dis.readUnsignedByte();
		hk41547 = new BeaconElementHeader(dis);
		ctrlRefq = StreamUtils.readFloatArray(dis, 4);
		ctrlErrq = StreamUtils.readFloatArray(dis, 4);
		ctrlM = StreamUtils.readFloatArray(dis, 3);
		ctrlMwspeed = StreamUtils.readFloatArray(dis, 4);
		ctrlEuleroff = StreamUtils.readFloatArray(dis, 3);
		ctrlBtorque = StreamUtils.readFloatArray(dis, 3);
	}

	public BeaconElementHeader getHk41507() {
		return hk41507;
	}

	public void setHk41507(BeaconElementHeader hk41507) {
		this.hk41507 = hk41507;
	}

	public float[] getExtmag() {
		return extmag;
	}

	public void setExtmag(float[] extmag) {
		this.extmag = extmag;
	}

	public float[] getTorquerDuty() {
		return torquerDuty;
	}

	public void setTorquerDuty(float[] torquerDuty) {
		this.torquerDuty = torquerDuty;
	}

	public BeaconElementHeader getHk41517() {
		return hk41517;
	}

	public void setHk41517(BeaconElementHeader hk41517) {
		this.hk41517 = hk41517;
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

	public BeaconElementHeader getHk41547() {
		return hk41547;
	}

	public void setHk41547(BeaconElementHeader hk41547) {
		this.hk41547 = hk41547;
	}

	public float[] getCtrlRefq() {
		return ctrlRefq;
	}

	public void setCtrlRefq(float[] ctrlRefq) {
		this.ctrlRefq = ctrlRefq;
	}

	public float[] getCtrlErrq() {
		return ctrlErrq;
	}

	public void setCtrlErrq(float[] ctrlErrq) {
		this.ctrlErrq = ctrlErrq;
	}

	public float[] getCtrlM() {
		return ctrlM;
	}

	public void setCtrlM(float[] ctrlM) {
		this.ctrlM = ctrlM;
	}

	public float[] getCtrlMwspeed() {
		return ctrlMwspeed;
	}

	public void setCtrlMwspeed(float[] ctrlMwspeed) {
		this.ctrlMwspeed = ctrlMwspeed;
	}

	public float[] getCtrlEuleroff() {
		return ctrlEuleroff;
	}

	public void setCtrlEuleroff(float[] ctrlEuleroff) {
		this.ctrlEuleroff = ctrlEuleroff;
	}

	public float[] getCtrlBtorque() {
		return ctrlBtorque;
	}

	public void setCtrlBtorque(float[] ctrlBtorque) {
		this.ctrlBtorque = ctrlBtorque;
	}

}
