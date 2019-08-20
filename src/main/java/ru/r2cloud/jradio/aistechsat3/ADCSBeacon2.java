package ru.r2cloud.jradio.aistechsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class ADCSBeacon2 {

	private DataFieldMeta ukfMeta;
	private float[] ukfX;
	private float[] ukfPdiag;
	private float[] ukfZpred;
	private int ukfInEclipse;

	private DataFieldMeta ephemMeta;
	private double ephemJdate;
	private float[] ephemReci;
	private float[] ephemVeci;

	public ADCSBeacon2() {
		// do nothing
	}

	public ADCSBeacon2(DataInputStream dis) throws IOException {
		ukfMeta = new DataFieldMeta(dis);
		ukfX = StreamUtils.readFloatArray(dis, 13);
		ukfPdiag = StreamUtils.readFloatArray(dis, 12);
		ukfZpred = StreamUtils.readFloatArray(dis, 12);
		ukfInEclipse = dis.readUnsignedByte();

		ephemMeta = new DataFieldMeta(dis);
		ephemJdate = Double.longBitsToDouble(dis.readLong());
		ephemReci = StreamUtils.readFloatArray(dis, 3);
		ephemVeci = StreamUtils.readFloatArray(dis, 3);
	}

	public DataFieldMeta getUkfMeta() {
		return ukfMeta;
	}

	public void setUkfMeta(DataFieldMeta ukfMeta) {
		this.ukfMeta = ukfMeta;
	}

	public float[] getUkfX() {
		return ukfX;
	}

	public void setUkfX(float[] ukfX) {
		this.ukfX = ukfX;
	}

	public float[] getUkfPdiag() {
		return ukfPdiag;
	}

	public void setUkfPdiag(float[] ukfPdiag) {
		this.ukfPdiag = ukfPdiag;
	}

	public float[] getUkfZpred() {
		return ukfZpred;
	}

	public void setUkfZpred(float[] ukfZpred) {
		this.ukfZpred = ukfZpred;
	}

	public int getUkfInEclipse() {
		return ukfInEclipse;
	}

	public void setUkfInEclipse(int ukfInEclipse) {
		this.ukfInEclipse = ukfInEclipse;
	}

	public DataFieldMeta getEphemMeta() {
		return ephemMeta;
	}

	public void setEphemMeta(DataFieldMeta ephemMeta) {
		this.ephemMeta = ephemMeta;
	}

	public double getEphemJdate() {
		return ephemJdate;
	}

	public void setEphemJdate(double ephemJdate) {
		this.ephemJdate = ephemJdate;
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

}
