package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class PayloadModeOBC {

	private float acsOrbitX;
	private float acsOrbitY;
	private float acsOrbitZ;
	private int pmmAmsatCurrent;
	private int mwmVoltage;
	private int mwmCurrent;
	private int mwmOmegaMesured;
	private short mpshpt01;
	private short pmmTempSP1Sens1;
	private short pmmTempBp1Sens1;

	public PayloadModeOBC(BitInputStream dis) throws IOException {
		acsOrbitX = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		acsOrbitY = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		acsOrbitZ = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		pmmAmsatCurrent = dis.readUnsignedInt(16);
		mwmVoltage = dis.readUnsignedByte();
		mwmCurrent = dis.readUnsignedShort();
		mwmOmegaMesured = dis.readUnsignedShort();
		mpshpt01 = dis.readShort();
		pmmTempSP1Sens1 = (short) dis.readUnsignedInt(12);
		pmmTempBp1Sens1 = (short) dis.readUnsignedInt(12);
		dis.skipBits(5);
	}

	public float getAcsOrbitX() {
		return acsOrbitX;
	}

	public void setAcsOrbitX(float acsOrbitX) {
		this.acsOrbitX = acsOrbitX;
	}

	public float getAcsOrbitY() {
		return acsOrbitY;
	}

	public void setAcsOrbitY(float acsOrbitY) {
		this.acsOrbitY = acsOrbitY;
	}

	public float getAcsOrbitZ() {
		return acsOrbitZ;
	}

	public void setAcsOrbitZ(float acsOrbitZ) {
		this.acsOrbitZ = acsOrbitZ;
	}

	public int getPmmAmsatCurrent() {
		return pmmAmsatCurrent;
	}

	public void setPmmAmsatCurrent(int pmmAmsatCurrent) {
		this.pmmAmsatCurrent = pmmAmsatCurrent;
	}

	public int getMwmVoltage() {
		return mwmVoltage;
	}

	public void setMwmVoltage(int mwmVoltage) {
		this.mwmVoltage = mwmVoltage;
	}

	public int getMwmCurrent() {
		return mwmCurrent;
	}

	public void setMwmCurrent(int mwmCurrent) {
		this.mwmCurrent = mwmCurrent;
	}

	public int getMwmOmegaMesured() {
		return mwmOmegaMesured;
	}

	public void setMwmOmegaMesured(int mwmOmegaMesured) {
		this.mwmOmegaMesured = mwmOmegaMesured;
	}

	public short getMpshpt01() {
		return mpshpt01;
	}

	public void setMpshpt01(short mpshpt01) {
		this.mpshpt01 = mpshpt01;
	}

	public short getPmmTempSP1Sens1() {
		return pmmTempSP1Sens1;
	}

	public void setPmmTempSP1Sens1(short pmmTempSP1Sens1) {
		this.pmmTempSP1Sens1 = pmmTempSP1Sens1;
	}

	public short getPmmTempBp1Sens1() {
		return pmmTempBp1Sens1;
	}

	public void setPmmTempBp1Sens1(short pmmTempBp1Sens1) {
		this.pmmTempBp1Sens1 = pmmTempBp1Sens1;
	}

}
