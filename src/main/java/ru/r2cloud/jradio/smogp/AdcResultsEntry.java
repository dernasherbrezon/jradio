package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class AdcResultsEntry {

	private float vcc;
	private float vbg;
	private float vcore;
	private float vextref;
	private float[] an;

	public AdcResultsEntry() {
		// do nothing
	}

	public AdcResultsEntry(LittleEndianDataInputStream dis) throws IOException {
		vcc = dis.readUnsignedShort() / 1000.0f;
		vbg = dis.readUnsignedShort() / 1000.0f;
		vcore = dis.readUnsignedShort() / 1000.0f;
		vextref = dis.readUnsignedShort() / 1000.0f;
		an = new float[4];
		for (int i = 0; i < an.length; i++) {
			an[i] = dis.readUnsignedShort() / 1000.0f;
		}
	}

	public float getVcc() {
		return vcc;
	}

	public void setVcc(float vcc) {
		this.vcc = vcc;
	}

	public float getVbg() {
		return vbg;
	}

	public void setVbg(float vbg) {
		this.vbg = vbg;
	}

	public float getVcore() {
		return vcore;
	}

	public void setVcore(float vcore) {
		this.vcore = vcore;
	}

	public float getVextref() {
		return vextref;
	}

	public void setVextref(float vextref) {
		this.vextref = vextref;
	}

	public float[] getAn() {
		return an;
	}

	public void setAn(float[] an) {
		this.an = an;
	}

}
