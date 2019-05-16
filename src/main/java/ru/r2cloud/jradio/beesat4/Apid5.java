package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid5 {

	private long cstutc; // Onboard time UTC
	private long cstsys; // OBDH uptime
	private long pcsyst; // PCU uptime
	private int acsm0x; // MFS #0: Vector X
	private int acsm0y; // MFS #0: Vector Y
	private int acsm0z; // MFS #0: Vector Z
	private int acsm1x; // MFS #1: Vector X
	private int acsm1y; // MFS #1: Vector Y
	private int acsm1z; // MFS #1: Vector Z
	private int accm2x; // MFS #2: Vector X Calibrated
	private int accm2y; // MFS #2: Vector Y Calibrated
	private int accm2z; // MFS #2: Vector Z Calibrated
	private int acsmagifx; // MAG_IF_X
	private int acsmagify; // MAG_IF_Y
	private int acsmagifz; // MAG_IF_Z
	private float cmfs0x; // Magnetic Field Sensor #0 X
	private float cmfs0y; // Magnetic Field Sensor #0 Y
	private float cmfs0z; // Magnetic Field Sensor #0 Z
	private float cmfs1x; // Magnetic Field Sensor #1 X
	private float cmfs1y; // Magnetic Field Sensor #1 Y
	private float cmfs1z; // Magnetic Field Sensor #1 Z
	private float acsm2x; // MFS #2: Vector X
	private float acsm2y; // MFS #2: Vector Y
	private float acsm2z; // MFS #2: Vector Z
	private float tslsm; // Temperature LSM303D
	private float tmfs0; // MFS #0
	private float tmfs1; // MFS #1
	private float acsg2y; // Gyro rate MAX21000 y-axis
	private float acsg2z; // Gyro rate MAX21000 z-axis
	private float acsg2x; // Gyro rate MAX21000 x-axis
	private float acsg1y; // Gyro #1 rate Y
	private float acsg1z; // Gyro #1 rate Z
	private float acsg1x; // Gyro #1 rate X
	private float acsc1x; // Gyro #1 rate X calibrated
	private float acsc1y; // Gyro #1 rate Y calibrated
	private float acsc1z; // Gyro #1 rate Z Calibrated
	private float acscmx; // Gyro Max rate X Calibrated
	private float acscmy; // Gyro Max rate Y Calibrated
	private float acscmz; // Gyro Max rate Z Calibrated
	private float acsq00; // ACS quaternion X
	private float acsq01; // ACS quaternion Y
	private float acsq02; // ACS quaternion Z
	private float acsq03; // ACS quaternion SC
	private float acsqdes00; // Desired quaternion X
	private float acsqdes01; // Desired quaternion Y
	private float acsqdes02; // Desired quaternion Z
	private float acsqdes03; // Desired quaternion SC
	private float acswq0; // Error Quaternion X
	private float acswq1; // Error Quaternion Y
	private float acswq2; // Error Quaternion Z
	private float acswq3; // Error Quaternion Scalar
	private AcsMode modacs; // ACS mode
	private AcsMode acscmod; // ACS current mode
	private AcsErrorCode acserr; // ACS Error Code
	private int acswhx; // Wheel speed X
	private int acswhy; // Wheel speed Y
	private int acswhz; // Wheel speed Z
	private int acswa0; // Wheel Accelaration 0
	private int acswa1; // Wheel Accelaration 1
	private int acswa2; // Wheel Accelaration 2
	private float acssux; // Sun vector X
	private float acssuy; // Sun vector Y
	private float acssuz; // Sun vector Z
	private int acsgyr; // Gyro used by ACS

	public Apid5(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);

		cstutc = bis.readUnsignedLong(32);
		cstsys = bis.readUnsignedLong(32);
		pcsyst = bis.readUnsignedLong(32);
		acsm0x = bis.readUnsignedShort() * 10;
		acsm0y = bis.readUnsignedShort() * 10;
		acsm0z = bis.readUnsignedShort() * 10;
		acsm1x = bis.readUnsignedShort() * 10;
		acsm1y = bis.readUnsignedShort() * 10;
		acsm1z = bis.readUnsignedShort() * 10;
		accm2x = bis.readUnsignedShort() * 10;
		accm2y = bis.readUnsignedShort() * 10;
		accm2z = bis.readUnsignedShort() * 10;
		acsmagifx = bis.readUnsignedShort() * 10;
		acsmagify = bis.readUnsignedShort() * 10;
		acsmagifz = bis.readUnsignedShort() * 10;
		cmfs0x = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs0y = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs0z = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs1x = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs1y = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		cmfs1z = bis.readUnsignedInt(12) * 0.003037316470f - 6.218905472637f;
		acsm2x = bis.readUnsignedShort() * -7.890292416f - 11584.291f;
		acsm2y = bis.readUnsignedShort() * -7.642841368f + 7968.73f;
		acsm2z = bis.readUnsignedShort() * 8.032315024f - 10266.647f;
		tslsm = bis.readUnsignedShort() * 0.125f + 25f;
		tmfs0 = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		tmfs1 = bis.readUnsignedInt(12) * 0.06103515625f - 50.0f;
		acsg2y = bis.readUnsignedShort() * -0.00104167f;
		acsg2z = bis.readUnsignedShort() * 0.00104167f;
		acsg2x = bis.readUnsignedShort() * -0.00104167f;

		acsg1y = bis.readUnsignedShort() * -0.00875f;
		acsg1z = bis.readUnsignedShort() * 0.00875f;
		acsg1x = bis.readUnsignedShort() * -0.00875f;
		acsc1x = bis.readUnsignedShort() * 0.001f;
		acsc1y = bis.readUnsignedShort() * 0.001f;
		acsc1z = bis.readUnsignedShort() * 0.001f;
		acscmx = bis.readUnsignedShort() * 0.001f;
		acscmy = bis.readUnsignedShort() * 0.001f;
		acscmz = bis.readUnsignedShort() * 0.001f;
		acsq00 = bis.readUnsignedShort() * 0.0001f;
		acsq01 = bis.readUnsignedShort() * 0.0001f;
		acsq02 = bis.readUnsignedShort() * 0.0001f;
		acsq03 = bis.readUnsignedShort() * 0.0001f;
		acsqdes00 = bis.readUnsignedShort() * 0.0001f;
		acsqdes01 = bis.readUnsignedShort() * 0.0001f;
		acsqdes02 = bis.readUnsignedShort() * 0.0001f;
		acsqdes03 = bis.readUnsignedShort() * 0.0001f;
		acswq0 = bis.readUnsignedShort() * 0.0001f;
		acswq1 = bis.readUnsignedShort() * 0.0001f;
		acswq2 = bis.readUnsignedShort() * 0.0001f;
		acswq3 = bis.readUnsignedShort() * 0.0001f;
		modacs = AcsMode.valueOfCode(bis.readUnsignedInt(4));
		acscmod = AcsMode.valueOfCode(bis.readUnsignedInt(4));
		acserr = AcsErrorCode.valueOfCode(bis.readUnsignedInt(8));
		acswhx = bis.readUnsignedShort();
		acswhy = bis.readUnsignedShort();
		acswhz = bis.readUnsignedShort();
		acswa0 = bis.readUnsignedShort();
		acswa1 = bis.readUnsignedShort();
		acswa2 = bis.readUnsignedShort();
		acssux = bis.readUnsignedShort() * 0.0001f;
		acssuy = bis.readUnsignedShort() * 0.0001f;
		acssuz = bis.readUnsignedShort() * 0.0001f;
		acsgyr = bis.readUnsignedInt(2);
		bis.skipBits(14);
	}

	public long getCstutc() {
		return cstutc;
	}

	public void setCstutc(long cstutc) {
		this.cstutc = cstutc;
	}

	public long getCstsys() {
		return cstsys;
	}

	public void setCstsys(long cstsys) {
		this.cstsys = cstsys;
	}

	public long getPcsyst() {
		return pcsyst;
	}

	public void setPcsyst(long pcsyst) {
		this.pcsyst = pcsyst;
	}

	public int getAcsm0x() {
		return acsm0x;
	}

	public void setAcsm0x(int acsm0x) {
		this.acsm0x = acsm0x;
	}

	public int getAcsm0y() {
		return acsm0y;
	}

	public void setAcsm0y(int acsm0y) {
		this.acsm0y = acsm0y;
	}

	public int getAcsm0z() {
		return acsm0z;
	}

	public void setAcsm0z(int acsm0z) {
		this.acsm0z = acsm0z;
	}

	public int getAcsm1x() {
		return acsm1x;
	}

	public void setAcsm1x(int acsm1x) {
		this.acsm1x = acsm1x;
	}

	public int getAcsm1y() {
		return acsm1y;
	}

	public void setAcsm1y(int acsm1y) {
		this.acsm1y = acsm1y;
	}

	public int getAcsm1z() {
		return acsm1z;
	}

	public void setAcsm1z(int acsm1z) {
		this.acsm1z = acsm1z;
	}

	public int getAccm2x() {
		return accm2x;
	}

	public void setAccm2x(int accm2x) {
		this.accm2x = accm2x;
	}

	public int getAccm2y() {
		return accm2y;
	}

	public void setAccm2y(int accm2y) {
		this.accm2y = accm2y;
	}

	public int getAccm2z() {
		return accm2z;
	}

	public void setAccm2z(int accm2z) {
		this.accm2z = accm2z;
	}

	public int getAcsmagifx() {
		return acsmagifx;
	}

	public void setAcsmagifx(int acsmagifx) {
		this.acsmagifx = acsmagifx;
	}

	public int getAcsmagify() {
		return acsmagify;
	}

	public void setAcsmagify(int acsmagify) {
		this.acsmagify = acsmagify;
	}

	public int getAcsmagifz() {
		return acsmagifz;
	}

	public void setAcsmagifz(int acsmagifz) {
		this.acsmagifz = acsmagifz;
	}

	public float getCmfs0x() {
		return cmfs0x;
	}

	public void setCmfs0x(float cmfs0x) {
		this.cmfs0x = cmfs0x;
	}

	public float getCmfs0y() {
		return cmfs0y;
	}

	public void setCmfs0y(float cmfs0y) {
		this.cmfs0y = cmfs0y;
	}

	public float getCmfs0z() {
		return cmfs0z;
	}

	public void setCmfs0z(float cmfs0z) {
		this.cmfs0z = cmfs0z;
	}

	public float getCmfs1x() {
		return cmfs1x;
	}

	public void setCmfs1x(float cmfs1x) {
		this.cmfs1x = cmfs1x;
	}

	public float getCmfs1y() {
		return cmfs1y;
	}

	public void setCmfs1y(float cmfs1y) {
		this.cmfs1y = cmfs1y;
	}

	public float getCmfs1z() {
		return cmfs1z;
	}

	public void setCmfs1z(float cmfs1z) {
		this.cmfs1z = cmfs1z;
	}

	public float getAcsm2x() {
		return acsm2x;
	}

	public void setAcsm2x(float acsm2x) {
		this.acsm2x = acsm2x;
	}

	public float getAcsm2y() {
		return acsm2y;
	}

	public void setAcsm2y(float acsm2y) {
		this.acsm2y = acsm2y;
	}

	public float getAcsm2z() {
		return acsm2z;
	}

	public void setAcsm2z(float acsm2z) {
		this.acsm2z = acsm2z;
	}

	public float getTslsm() {
		return tslsm;
	}

	public void setTslsm(float tslsm) {
		this.tslsm = tslsm;
	}

	public float getTmfs0() {
		return tmfs0;
	}

	public void setTmfs0(float tmfs0) {
		this.tmfs0 = tmfs0;
	}

	public float getTmfs1() {
		return tmfs1;
	}

	public void setTmfs1(float tmfs1) {
		this.tmfs1 = tmfs1;
	}

	public float getAcsg2y() {
		return acsg2y;
	}

	public void setAcsg2y(float acsg2y) {
		this.acsg2y = acsg2y;
	}

	public float getAcsg2z() {
		return acsg2z;
	}

	public void setAcsg2z(float acsg2z) {
		this.acsg2z = acsg2z;
	}

	public float getAcsg2x() {
		return acsg2x;
	}

	public void setAcsg2x(float acsg2x) {
		this.acsg2x = acsg2x;
	}

	public float getAcsg1y() {
		return acsg1y;
	}

	public void setAcsg1y(float acsg1y) {
		this.acsg1y = acsg1y;
	}

	public float getAcsg1z() {
		return acsg1z;
	}

	public void setAcsg1z(float acsg1z) {
		this.acsg1z = acsg1z;
	}

	public float getAcsg1x() {
		return acsg1x;
	}

	public void setAcsg1x(float acsg1x) {
		this.acsg1x = acsg1x;
	}

	public float getAcsc1x() {
		return acsc1x;
	}

	public void setAcsc1x(float acsc1x) {
		this.acsc1x = acsc1x;
	}

	public float getAcsc1y() {
		return acsc1y;
	}

	public void setAcsc1y(float acsc1y) {
		this.acsc1y = acsc1y;
	}

	public float getAcsc1z() {
		return acsc1z;
	}

	public void setAcsc1z(float acsc1z) {
		this.acsc1z = acsc1z;
	}

	public float getAcscmx() {
		return acscmx;
	}

	public void setAcscmx(float acscmx) {
		this.acscmx = acscmx;
	}

	public float getAcscmy() {
		return acscmy;
	}

	public void setAcscmy(float acscmy) {
		this.acscmy = acscmy;
	}

	public float getAcscmz() {
		return acscmz;
	}

	public void setAcscmz(float acscmz) {
		this.acscmz = acscmz;
	}

	public float getAcsq00() {
		return acsq00;
	}

	public void setAcsq00(float acsq00) {
		this.acsq00 = acsq00;
	}

	public float getAcsq01() {
		return acsq01;
	}

	public void setAcsq01(float acsq01) {
		this.acsq01 = acsq01;
	}

	public float getAcsq02() {
		return acsq02;
	}

	public void setAcsq02(float acsq02) {
		this.acsq02 = acsq02;
	}

	public float getAcsq03() {
		return acsq03;
	}

	public void setAcsq03(float acsq03) {
		this.acsq03 = acsq03;
	}

	public float getAcsqdes00() {
		return acsqdes00;
	}

	public void setAcsqdes00(float acsqdes00) {
		this.acsqdes00 = acsqdes00;
	}

	public float getAcsqdes01() {
		return acsqdes01;
	}

	public void setAcsqdes01(float acsqdes01) {
		this.acsqdes01 = acsqdes01;
	}

	public float getAcsqdes02() {
		return acsqdes02;
	}

	public void setAcsqdes02(float acsqdes02) {
		this.acsqdes02 = acsqdes02;
	}

	public float getAcsqdes03() {
		return acsqdes03;
	}

	public void setAcsqdes03(float acsqdes03) {
		this.acsqdes03 = acsqdes03;
	}

	public float getAcswq0() {
		return acswq0;
	}

	public void setAcswq0(float acswq0) {
		this.acswq0 = acswq0;
	}

	public float getAcswq1() {
		return acswq1;
	}

	public void setAcswq1(float acswq1) {
		this.acswq1 = acswq1;
	}

	public float getAcswq2() {
		return acswq2;
	}

	public void setAcswq2(float acswq2) {
		this.acswq2 = acswq2;
	}

	public float getAcswq3() {
		return acswq3;
	}

	public void setAcswq3(float acswq3) {
		this.acswq3 = acswq3;
	}

	public AcsMode getModacs() {
		return modacs;
	}

	public void setModacs(AcsMode modacs) {
		this.modacs = modacs;
	}

	public AcsMode getAcscmod() {
		return acscmod;
	}

	public void setAcscmod(AcsMode acscmod) {
		this.acscmod = acscmod;
	}

	public AcsErrorCode getAcserr() {
		return acserr;
	}

	public void setAcserr(AcsErrorCode acserr) {
		this.acserr = acserr;
	}

	public int getAcswhx() {
		return acswhx;
	}

	public void setAcswhx(int acswhx) {
		this.acswhx = acswhx;
	}

	public int getAcswhy() {
		return acswhy;
	}

	public void setAcswhy(int acswhy) {
		this.acswhy = acswhy;
	}

	public int getAcswhz() {
		return acswhz;
	}

	public void setAcswhz(int acswhz) {
		this.acswhz = acswhz;
	}

	public int getAcswa0() {
		return acswa0;
	}

	public void setAcswa0(int acswa0) {
		this.acswa0 = acswa0;
	}

	public int getAcswa1() {
		return acswa1;
	}

	public void setAcswa1(int acswa1) {
		this.acswa1 = acswa1;
	}

	public int getAcswa2() {
		return acswa2;
	}

	public void setAcswa2(int acswa2) {
		this.acswa2 = acswa2;
	}

	public float getAcssux() {
		return acssux;
	}

	public void setAcssux(float acssux) {
		this.acssux = acssux;
	}

	public float getAcssuy() {
		return acssuy;
	}

	public void setAcssuy(float acssuy) {
		this.acssuy = acssuy;
	}

	public float getAcssuz() {
		return acssuz;
	}

	public void setAcssuz(float acssuz) {
		this.acssuz = acssuz;
	}

	public int getAcsgyr() {
		return acsgyr;
	}

	public void setAcsgyr(int acsgyr) {
		this.acsgyr = acsgyr;
	}

}
