package ru.r2cloud.jradio.gomx1;

import java.io.DataInputStream;
import java.io.IOException;

public class TypeB {

	private int[] sun; // sun sensor +x +y -x -y -z
	private int ineclipse; // 0=in sun, 1=ineclipse
	private float[] xest; // State vector [_i^cq ^c\omega b_mag b_gyro \tau] in total 16 states
	private float[] zfilt; // Filt meas vector [v_sun v_mag omega_gyro]
	private int enableVector; // Enable vector (bit mask) for meas vector [xsunsensor,3xmag,3xgyro]
	private float[] ref_q; // Control ref
	private float[] err_q; // control err
	private float[] ierr_q; // Control ierr
	private float[] err_rate; // Control err rate
	private float[] sc_reci; // Ephem satellite pos
	private float[] sun_eci; // Ephem sun pos
	private float[] mag_eci; // Ephem mag

	public TypeB(DataInputStream dis) throws IOException {
		sun = new int[5];
		for (int i = 0; i < sun.length; i++) {
			sun[i] = dis.readUnsignedShort();
		}
		ineclipse = dis.readUnsignedByte();
		xest = readFloat(dis, 16);
		zfilt = readFloat(dis, 9);
		enableVector = dis.readUnsignedShort();
		ref_q = readFloat(dis, 4);
		err_q = readFloat(dis, 4);
		ierr_q = readFloat(dis, 3);
		err_rate = readFloat(dis, 3);
		sc_reci = readFloat(dis, 3);
		sun_eci = readFloat(dis, 3);
		mag_eci = readFloat(dis, 3);
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

	public float[] getRef_q() {
		return ref_q;
	}

	public void setRef_q(float[] ref_q) {
		this.ref_q = ref_q;
	}

	public float[] getErr_q() {
		return err_q;
	}

	public void setErr_q(float[] err_q) {
		this.err_q = err_q;
	}

	public float[] getIerr_q() {
		return ierr_q;
	}

	public void setIerr_q(float[] ierr_q) {
		this.ierr_q = ierr_q;
	}

	public float[] getErr_rate() {
		return err_rate;
	}

	public void setErr_rate(float[] err_rate) {
		this.err_rate = err_rate;
	}

	public float[] getSc_reci() {
		return sc_reci;
	}

	public void setSc_reci(float[] sc_reci) {
		this.sc_reci = sc_reci;
	}

	public float[] getSun_eci() {
		return sun_eci;
	}

	public void setSun_eci(float[] sun_eci) {
		this.sun_eci = sun_eci;
	}

	public float[] getMag_eci() {
		return mag_eci;
	}

	public void setMag_eci(float[] mag_eci) {
		this.mag_eci = mag_eci;
	}

	private static float[] readFloat(DataInputStream dis, int size) throws IOException {
		float[] result = new float[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = dis.readFloat();
		}
		return result;
	}

}
