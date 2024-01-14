package ru.r2cloud.jradio.randev;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class CommRx {

	private float dopplerHz;
	private float rssiDbm;
	private float voltageVolts;
	private float totalCurrentAmpere;
	private float trCurrentAmpere;
	private float rxCurrentAmpere;
	private float paCurrentAmpere;
	private float paTempDegree;
	private float osciTempDegree;

	public CommRx() {
		// do nothing
	}

	public CommRx(LittleEndianDataInputStream dis) throws IOException {
		dopplerHz = dis.readUnsignedShort() * 13.352f - 22300;
		rssiDbm = dis.readUnsignedShort() * 0.03f - 152;
		voltageVolts = dis.readUnsignedShort() * 0.00488f;
		totalCurrentAmpere = dis.readUnsignedShort() * 0.16643964f / 1000;
		trCurrentAmpere = dis.readUnsignedShort() * 0.16643964f / 1000;
		rxCurrentAmpere = dis.readUnsignedShort() * 0.16643964f / 1000;
		paCurrentAmpere = dis.readUnsignedShort() * 0.16643964f / 1000;
		paTempDegree = -0.07669f * dis.readUnsignedShort() + 195.6038f;
		osciTempDegree = -0.07669f * dis.readUnsignedShort() + 195.6038f;
	}

	public float getDopplerHz() {
		return dopplerHz;
	}

	public void setDopplerHz(float dopplerHz) {
		this.dopplerHz = dopplerHz;
	}

	public float getRssiDbm() {
		return rssiDbm;
	}

	public void setRssiDbm(float rssiDbm) {
		this.rssiDbm = rssiDbm;
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
