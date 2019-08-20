package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class ADCSBeacon3 {

	private DataFieldMeta ctrlMeta;
	private float[] ctrlRefq;
	private float[] ctrlErrq;
	private float[] ctrlErrRate;
	private float[] ctrlM;
	private float[] ctrlMwSpeed;
	private float[] ctrlMwTorque;
	private DataFieldMeta ukfMeta;
	private float[] ukfQ;
	private float[] ukfW;
	private DataFieldMeta ephemMeta;
	private float[] ephemReci;
	private float[] ephemVeci;

	public ADCSBeacon3() {
		// do nothing
	}

	public ADCSBeacon3(DataInputStream dis) throws IOException {
		ctrlMeta = new DataFieldMeta(dis);
		ctrlRefq = StreamUtils.readFloatArray(dis, 4);
		ctrlErrq = StreamUtils.readFloatArray(dis, 4);
		ctrlErrRate = StreamUtils.readFloatArray(dis, 3);
		ctrlM = StreamUtils.readFloatArray(dis, 3);
		ctrlMwSpeed = StreamUtils.readFloatArray(dis, 4);
		ctrlMwTorque = StreamUtils.readFloatArray(dis, 4);
		ukfMeta = new DataFieldMeta(dis);
		ukfQ = StreamUtils.readFloatArray(dis, 4);
		ukfW = StreamUtils.readFloatArray(dis, 3);
		ephemMeta = new DataFieldMeta(dis);
		ephemReci = StreamUtils.readFloatArray(dis, 3);
		ephemVeci = StreamUtils.readFloatArray(dis, 3);
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

	public float[] getCtrlErrRate() {
		return ctrlErrRate;
	}

	public void setCtrlErrRate(float[] ctrlErrRate) {
		this.ctrlErrRate = ctrlErrRate;
	}

	public float[] getCtrlM() {
		return ctrlM;
	}

	public void setCtrlM(float[] ctrlM) {
		this.ctrlM = ctrlM;
	}

	public float[] getCtrlMwSpeed() {
		return ctrlMwSpeed;
	}

	public void setCtrlMwSpeed(float[] ctrlMwSpeed) {
		this.ctrlMwSpeed = ctrlMwSpeed;
	}

	public float[] getCtrlMwTorque() {
		return ctrlMwTorque;
	}

	public void setCtrlMwTorque(float[] ctrlMwTorque) {
		this.ctrlMwTorque = ctrlMwTorque;
	}

	public float[] getUkfQ() {
		return ukfQ;
	}

	public void setUkfQ(float[] ukfQ) {
		this.ukfQ = ukfQ;
	}

	public float[] getUkfW() {
		return ukfW;
	}

	public void setUkfW(float[] ukfW) {
		this.ukfW = ukfW;
	}

	public float[] getEphemReci() {
		return ephemReci;
	}

	public void setEphemReci(float[] ephemReci) {
		this.ephemReci = ephemReci;
	}

	public float[] getEphemVeci() {
		return ephemVeci;
	}

	public void setEphemVeci(float[] ephemVeci) {
		this.ephemVeci = ephemVeci;
	}

	public DataFieldMeta getCtrlMeta() {
		return ctrlMeta;
	}

	public void setCtrlMeta(DataFieldMeta ctrlMeta) {
		this.ctrlMeta = ctrlMeta;
	}

	public DataFieldMeta getUkfMeta() {
		return ukfMeta;
	}

	public void setUkfMeta(DataFieldMeta ukfMeta) {
		this.ukfMeta = ukfMeta;
	}

	public DataFieldMeta getEphemMeta() {
		return ephemMeta;
	}

	public void setEphemMeta(DataFieldMeta ephemMeta) {
		this.ephemMeta = ephemMeta;
	}

}
