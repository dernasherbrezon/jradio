package ru.r2cloud.jradio.eseo.funcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class PayloadModeOBC {

	private float ACSOrbitX;
	private float ACSOrbitY;
	private float ACSOrbitZ;
	private int PMMAmsatCurrent;
	private int MWMVoltage;
	private int MWMCurrent;
	private int MWMOMEGAMESURED;
	private short MPSHPT01;
	private short PMMTempSP1Sens1;
	private short PMMTempBp1Sens1;

	public PayloadModeOBC(BitInputStream dis) throws IOException {
		ACSOrbitX = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		ACSOrbitY = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		ACSOrbitZ = Float.intBitsToFloat((int) dis.readUnsignedLong(32));
		PMMAmsatCurrent = dis.readUnsignedInt(16);
		MWMVoltage = dis.readUnsignedByte();
		MWMCurrent = dis.readUnsignedShort();
		MWMOMEGAMESURED = dis.readUnsignedShort();
		MPSHPT01 = dis.readShort();
		PMMTempSP1Sens1 = (short) dis.readUnsignedInt(12);
		PMMTempBp1Sens1 = (short) dis.readUnsignedInt(12);
		dis.skipBits(5);
	}

	public float getACSOrbitX() {
		return ACSOrbitX;
	}

	public void setACSOrbitX(float aCSOrbitX) {
		ACSOrbitX = aCSOrbitX;
	}

	public float getACSOrbitY() {
		return ACSOrbitY;
	}

	public void setACSOrbitY(float aCSOrbitY) {
		ACSOrbitY = aCSOrbitY;
	}

	public float getACSOrbitZ() {
		return ACSOrbitZ;
	}

	public void setACSOrbitZ(float aCSOrbitZ) {
		ACSOrbitZ = aCSOrbitZ;
	}

	public int getPMMAmsatCurrent() {
		return PMMAmsatCurrent;
	}

	public void setPMMAmsatCurrent(int pMMAmsatCurrent) {
		PMMAmsatCurrent = pMMAmsatCurrent;
	}

	public int getMWMVoltage() {
		return MWMVoltage;
	}

	public void setMWMVoltage(int mWMVoltage) {
		MWMVoltage = mWMVoltage;
	}

	public int getMWMCurrent() {
		return MWMCurrent;
	}

	public void setMWMCurrent(int mWMCurrent) {
		MWMCurrent = mWMCurrent;
	}

	public int getMWMOMEGAMESURED() {
		return MWMOMEGAMESURED;
	}

	public void setMWMOMEGAMESURED(int mWMOMEGAMESURED) {
		MWMOMEGAMESURED = mWMOMEGAMESURED;
	}

	public short getMPSHPT01() {
		return MPSHPT01;
	}

	public void setMPSHPT01(short mPSHPT01) {
		MPSHPT01 = mPSHPT01;
	}

	public short getPMMTempSP1Sens1() {
		return PMMTempSP1Sens1;
	}

	public void setPMMTempSP1Sens1(short pMMTempSP1Sens1) {
		PMMTempSP1Sens1 = pMMTempSP1Sens1;
	}

	public short getPMMTempBp1Sens1() {
		return PMMTempBp1Sens1;
	}

	public void setPMMTempBp1Sens1(short pMMTempBp1Sens1) {
		PMMTempBp1Sens1 = pMMTempBp1Sens1;
	}

}
