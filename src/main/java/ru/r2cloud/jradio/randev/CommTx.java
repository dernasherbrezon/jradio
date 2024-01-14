package ru.r2cloud.jradio.randev;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CommTx {

	private float reflectedDb;
	private float forwardDb;
	private float voltageVolts;
	private float totalCurrentAmpere;
	private float trCurrentAmpere;
	private float rxCurrentAmpere;
	private float paCurrentAmpere;
	private float paTempDegree;
	private float osciTempDegree;

	public CommTx() {
		// do nothing
	}

	public CommTx(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedShort();
		reflectedDb = raw * raw * 5.887f * 1e-5f;
		raw = dis.readUnsignedShort();
		forwardDb = raw * raw * 5.887f * 1e-5f;
		voltageVolts = dis.readUnsignedShort() * 0.00488f;
		totalCurrentAmpere = dis.readUnsignedShort() * 0.16643964f / 1000;
		trCurrentAmpere = dis.readUnsignedShort() * 0.16643964f / 1000;
		rxCurrentAmpere = dis.readUnsignedShort() * 0.16643964f / 1000;
		paCurrentAmpere = dis.readUnsignedShort() * 0.16643964f / 1000;
		paTempDegree = -0.07669f * dis.readUnsignedShort() + 195.6038f;
		osciTempDegree = -0.07669f * dis.readUnsignedShort() + 195.6038f;
	}

	public float getReflectedDb() {
		return reflectedDb;
	}

	public void setReflectedDb(float reflectedDb) {
		this.reflectedDb = reflectedDb;
	}

	public float getForwardDb() {
		return forwardDb;
	}

	public void setForwardDb(float forwardDb) {
		this.forwardDb = forwardDb;
	}

	public float getVoltageVolts() {
		return voltageVolts;
	}

	public void setVoltageVolts(float voltageVolts) {
		this.voltageVolts = voltageVolts;
	}

	public float getTotalCurrentAmpere() {
		return totalCurrentAmpere;
	}

	public void setTotalCurrentAmpere(float totalCurrentAmpere) {
		this.totalCurrentAmpere = totalCurrentAmpere;
	}

	public float getTrCurrentAmpere() {
		return trCurrentAmpere;
	}

	public void setTrCurrentAmpere(float trCurrentAmpere) {
		this.trCurrentAmpere = trCurrentAmpere;
	}

	public float getRxCurrentAmpere() {
		return rxCurrentAmpere;
	}

	public void setRxCurrentAmpere(float rxCurrentAmpere) {
		this.rxCurrentAmpere = rxCurrentAmpere;
	}

	public float getPaCurrentAmpere() {
		return paCurrentAmpere;
	}

	public void setPaCurrentAmpere(float paCurrentAmpere) {
		this.paCurrentAmpere = paCurrentAmpere;
	}

	public float getPaTempDegree() {
		return paTempDegree;
	}

	public void setPaTempDegree(float paTempDegree) {
		this.paTempDegree = paTempDegree;
	}

	public float getOsciTempDegree() {
		return osciTempDegree;
	}

	public void setOsciTempDegree(float osciTempDegree) {
		this.osciTempDegree = osciTempDegree;
	}

}
