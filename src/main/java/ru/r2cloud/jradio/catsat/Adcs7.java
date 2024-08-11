package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Adcs7 {

	private BeaconElementHeader hk415417;
	private float[] ctrlRefq;
	private float[] ctrlErrq;
	private float[] ctrlErrrate;
	private float[] ctrlM;
	private float[] ctrlMwtorque;
	private float[] ctrlMwspeed;
	private float[] ctrlMwmoment;
	private float[] ctrlRefrate;
	private float[] ctrlEuleroff;
	private float[] ctrlBtorque;
	private float[] ctrlBmoment;

	public Adcs7() {
		// do nothing
	}

	public Adcs7(DataInputStream dis) throws IOException {
		hk415417 = new BeaconElementHeader(dis);
		ctrlRefq = StreamUtils.readFloatArray(dis, 4);
		ctrlErrq = StreamUtils.readFloatArray(dis, 4);
		ctrlErrrate = StreamUtils.readFloatArray(dis, 3);
		ctrlM = StreamUtils.readFloatArray(dis, 3);
		ctrlMwtorque = StreamUtils.readFloatArray(dis, 4);
		ctrlMwspeed = StreamUtils.readFloatArray(dis, 4);
		ctrlMwmoment = StreamUtils.readFloatArray(dis, 4);
		ctrlRefrate = StreamUtils.readFloatArray(dis, 3);
		ctrlEuleroff = StreamUtils.readFloatArray(dis, 3);
		ctrlBtorque = StreamUtils.readFloatArray(dis, 3);
		ctrlBmoment = StreamUtils.readFloatArray(dis, 3);
	}

	public BeaconElementHeader getHk415417() {
		return hk415417;
	}

	public void setHk415417(BeaconElementHeader hk415417) {
		this.hk415417 = hk415417;
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

	public float[] getCtrlErrrate() {
		return ctrlErrrate;
	}

	public void setCtrlErrrate(float[] ctrlErrrate) {
		this.ctrlErrrate = ctrlErrrate;
	}

	public float[] getCtrlM() {
		return ctrlM;
	}

	public void setCtrlM(float[] ctrlM) {
		this.ctrlM = ctrlM;
	}

	public float[] getCtrlMwtorque() {
		return ctrlMwtorque;
	}

	public void setCtrlMwtorque(float[] ctrlMwtorque) {
		this.ctrlMwtorque = ctrlMwtorque;
	}

	public float[] getCtrlMwspeed() {
		return ctrlMwspeed;
	}

	public void setCtrlMwspeed(float[] ctrlMwspeed) {
		this.ctrlMwspeed = ctrlMwspeed;
	}

	public float[] getCtrlMwmoment() {
		return ctrlMwmoment;
	}

	public void setCtrlMwmoment(float[] ctrlMwmoment) {
		this.ctrlMwmoment = ctrlMwmoment;
	}

	public float[] getCtrlRefrate() {
		return ctrlRefrate;
	}

	public void setCtrlRefrate(float[] ctrlRefrate) {
		this.ctrlRefrate = ctrlRefrate;
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

	public float[] getCtrlBmoment() {
		return ctrlBmoment;
	}

	public void setCtrlBmoment(float[] ctrlBmoment) {
		this.ctrlBmoment = ctrlBmoment;
	}

}
