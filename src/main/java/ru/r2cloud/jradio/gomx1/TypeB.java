package ru.r2cloud.jradio.gomx1;

import java.io.DataInputStream;
import java.io.IOException;

public class TypeB {

	private int[] sun; // sun sensor +x +y -x -y -z
	private int ineclipse; // 0=in sun, 1=ineclipse
	private float[] xest; // State vector [_i^cq ^c\omega b_mag b_gyro \tau] in total 16 states
	private float[] zfilt; // Filt meas vector [v_sun v_mag omega_gyro]
	private int enableVector; // Enable vector (bit mask) for meas vector [xsunsensor,3xmag,3xgyro]
	private float[] refQ; // Control ref
	private float[] errQ; // control err
	private float[] ierrQ; // Control ierr
	private float[] errRate; // Control err rate
	private float[] scReci; // Ephem satellite pos
	private float[] sunEci; // Ephem sun pos
	private float[] magEci; // Ephem mag

	public TypeB() {
		// do nothing
	}

	public TypeB(DataInputStream dis) throws IOException {
		sun = new int[5];
		for (int i = 0; i < sun.length; i++) {
			sun[i] = dis.readUnsignedShort();
		}
		ineclipse = dis.readUnsignedByte();
		xest = readFloat(dis, 16);
		zfilt = readFloat(dis, 9);
		enableVector = dis.readUnsignedShort();
		refQ = readFloat(dis, 4);
		errQ = readFloat(dis, 4);
		ierrQ = readFloat(dis, 3);
		errRate = readFloat(dis, 3);
		scReci = readFloat(dis, 3);
		sunEci = readFloat(dis, 3);
		magEci = readFloat(dis, 3);
	}

	private static float[] readFloat(DataInputStream dis, int size) throws IOException {
		float[] result = new float[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = dis.readFloat();
		}
		return result;
	}

	public int[] getSun() {
		return sun;
	}

	public void setSun(int[] sun) {
		this.sun = sun;
	}

	public int getIneclipse() {
		return ineclipse;
	}

	public void setIneclipse(int ineclipse) {
		this.ineclipse = ineclipse;
	}

	public float[] getXest() {
		return xest;
	}

	public void setXest(float[] xest) {
		this.xest = xest;
	}

	public float[] getZfilt() {
		return zfilt;
	}

	public void setZfilt(float[] zfilt) {
		this.zfilt = zfilt;
	}

	public int getEnableVector() {
		return enableVector;
	}

	public void setEnableVector(int enableVector) {
		this.enableVector = enableVector;
	}

	public float[] getRefQ() {
		return refQ;
	}

	public void setRefQ(float[] refQ) {
		this.refQ = refQ;
	}

	public float[] getErrQ() {
		return errQ;
	}

	public void setErrQ(float[] errQ) {
		this.errQ = errQ;
	}

	public float[] getIerrQ() {
		return ierrQ;
	}

	public void setIerrQ(float[] ierrQ) {
		this.ierrQ = ierrQ;
	}

	public float[] getErrRate() {
		return errRate;
	}

	public void setErrRate(float[] errRate) {
		this.errRate = errRate;
	}

	public float[] getScReci() {
		return scReci;
	}

	public void setScReci(float[] scReci) {
		this.scReci = scReci;
	}

	public float[] getSunEci() {
		return sunEci;
	}

	public void setSunEci(float[] sunEci) {
		this.sunEci = sunEci;
	}

	public float[] getMagEci() {
		return magEci;
	}

	public void setMagEci(float[] magEci) {
		this.magEci = magEci;
	}

}
