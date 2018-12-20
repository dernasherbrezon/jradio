package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid3 {

	private float ACSWCX; // Commanded Wheel Speed X
	private float ACSWCY; // Commanded Wheel Speed Y
	private float ACSWCZ; // Commanded Wheel Speed Z
	private float ACSM2Y; // MFS #2: Vector Y
	private float ACSM2Z; // MFS #2: Vector Z
	private float ACSM2X; // MFS #2: Vector X
	private float ACSG2Y; // Gyro rate MAX21000 y-axis
	private float ACSG2Z; // Gyro rate MAX21000 z-axis
	private float ACSG2X; // Gyro rate MAX21000 x-axis
	private float ACSG1Y; // Gyro #1 rate Y
	private float ACSG1Z; // Gyro #1 rate Z
	private float ACSG1X; // Gyro #1 rate X
	private float ACG1YM; // Gyro #1 rate Y MEAN
	private float ACG1ZM; // Gyro #1 rate Z MEAN
	private float ACG1XM; // Gyro #1 rate X MEAN
	private long CSTUTC; // Onboard time UTC
	private long CSTSYS; // OBDH uptime
	private long PCSYST; // PCU uptime
	private long ASSTOP; // Auto Send Stop Time
	private int ASINTV; // Auto Send Interval
	private int ASCEST; // Auto Send Start Error
	private boolean ASACTV; // Auto Send Active
	private float ACSWU0; // Wheelcontroller U0
	private float ACSWU1; // Wheelcontroller U1
	private float ACSWU2; // Wheelcontroller U2
	private float ACSWQ0; // Error Quaternion X
	private float ACSWQ1; // Error Quaternion Y
	private float ACSWQ2; // Error Quaternion Z
	private float ACSWQ3; // Error Quaternion Scalar
	private int ACSWA0; // Wheel Accelaration 0
	private int ACSWA1; // Wheel Accelaration 1
	private int ACSWA2; // Wheel Accelaration 2

	public Apid3(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);

		ACSWCX = bis.readShort() * -0.5f;
		ACSWCY = bis.readShort() * -0.5f;
		ACSWCZ = bis.readShort() * 0.5f;
		ACSM2Y = bis.readShort() * -7.642841368f + 7968.73f;
		ACSM2Z = bis.readShort() * 8.032315024f - 10266.647f;
		ACSM2X = bis.readShort() * -7.890292416f - 11584.291f;
		ACSG2Y = bis.readShort() * -0.00104167f;
		ACSG2Z = bis.readShort() * 0.00104167f;
		ACSG2X = bis.readShort() * -0.00104167f;
		ACSG1Y = bis.readShort() * -0.00875f;
		ACSG1Z = bis.readShort() * -0.00875f;
		ACSG1X = bis.readShort() * -0.00875f;
		ACG1YM = bis.readShort() * -0.00875f;
		ACG1ZM = bis.readShort() * -0.00875f;
		ACG1XM = bis.readShort() * -0.00875f;
		CSTUTC = bis.readUnsignedLong(32);
		CSTSYS = bis.readUnsignedLong(32);
		PCSYST = bis.readUnsignedLong(32);
		ASSTOP = bis.readUnsignedLong(32);
		ASINTV = bis.readUnsignedInt(16);
		ASCEST = bis.readUnsignedInt(8);
		ASACTV = bis.readBoolean();
		ACSWU0 = bis.readShort() * 0.00000001f;
		ACSWU1 = bis.readShort() * 0.00000001f;
		ACSWU2 = bis.readShort() * 0.00000001f;
		ACSWQ0 = bis.readShort() * 0.0001f;
		ACSWQ1 = bis.readShort() * 0.0001f;
		ACSWQ2 = bis.readShort() * 0.0001f;
		ACSWQ3 = bis.readShort() * 0.0001f;
		ACSWA0 = bis.readShort();
		ACSWA1 = bis.readShort();
		ACSWA2 = bis.readShort();
		dis.skipBytes(56);
	}

	public float getACSWCX() {
		return ACSWCX;
	}

	public void setACSWCX(float aCSWCX) {
		ACSWCX = aCSWCX;
	}

	public float getACSWCY() {
		return ACSWCY;
	}

	public void setACSWCY(float aCSWCY) {
		ACSWCY = aCSWCY;
	}

	public float getACSWCZ() {
		return ACSWCZ;
	}

	public void setACSWCZ(float aCSWCZ) {
		ACSWCZ = aCSWCZ;
	}

	public float getACSM2Y() {
		return ACSM2Y;
	}

	public void setACSM2Y(float aCSM2Y) {
		ACSM2Y = aCSM2Y;
	}

	public float getACSM2Z() {
		return ACSM2Z;
	}

	public void setACSM2Z(float aCSM2Z) {
		ACSM2Z = aCSM2Z;
	}

	public float getACSM2X() {
		return ACSM2X;
	}

	public void setACSM2X(float aCSM2X) {
		ACSM2X = aCSM2X;
	}

	public float getACSG2Y() {
		return ACSG2Y;
	}

	public void setACSG2Y(float aCSG2Y) {
		ACSG2Y = aCSG2Y;
	}

	public float getACSG2Z() {
		return ACSG2Z;
	}

	public void setACSG2Z(float aCSG2Z) {
		ACSG2Z = aCSG2Z;
	}

	public float getACSG2X() {
		return ACSG2X;
	}

	public void setACSG2X(float aCSG2X) {
		ACSG2X = aCSG2X;
	}

	public float getACSG1Y() {
		return ACSG1Y;
	}

	public void setACSG1Y(float aCSG1Y) {
		ACSG1Y = aCSG1Y;
	}

	public float getACSG1Z() {
		return ACSG1Z;
	}

	public void setACSG1Z(float aCSG1Z) {
		ACSG1Z = aCSG1Z;
	}

	public float getACSG1X() {
		return ACSG1X;
	}

	public void setACSG1X(float aCSG1X) {
		ACSG1X = aCSG1X;
	}

	public float getACG1YM() {
		return ACG1YM;
	}

	public void setACG1YM(float aCG1YM) {
		ACG1YM = aCG1YM;
	}

	public float getACG1ZM() {
		return ACG1ZM;
	}

	public void setACG1ZM(float aCG1ZM) {
		ACG1ZM = aCG1ZM;
	}

	public float getACG1XM() {
		return ACG1XM;
	}

	public void setACG1XM(float aCG1XM) {
		ACG1XM = aCG1XM;
	}

	public long getCSTUTC() {
		return CSTUTC;
	}

	public void setCSTUTC(long cSTUTC) {
		CSTUTC = cSTUTC;
	}

	public long getCSTSYS() {
		return CSTSYS;
	}

	public void setCSTSYS(long cSTSYS) {
		CSTSYS = cSTSYS;
	}

	public long getPCSYST() {
		return PCSYST;
	}

	public void setPCSYST(long pCSYST) {
		PCSYST = pCSYST;
	}

	public long getASSTOP() {
		return ASSTOP;
	}

	public void setASSTOP(long aSSTOP) {
		ASSTOP = aSSTOP;
	}

	public int getASINTV() {
		return ASINTV;
	}

	public void setASINTV(int aSINTV) {
		ASINTV = aSINTV;
	}

	public int getASCEST() {
		return ASCEST;
	}

	public void setASCEST(int aSCEST) {
		ASCEST = aSCEST;
	}

	public boolean isASACTV() {
		return ASACTV;
	}

	public void setASACTV(boolean aSACTV) {
		ASACTV = aSACTV;
	}

	public float getACSWU0() {
		return ACSWU0;
	}

	public void setACSWU0(float aCSWU0) {
		ACSWU0 = aCSWU0;
	}

	public float getACSWU1() {
		return ACSWU1;
	}

	public void setACSWU1(float aCSWU1) {
		ACSWU1 = aCSWU1;
	}

	public float getACSWU2() {
		return ACSWU2;
	}

	public void setACSWU2(float aCSWU2) {
		ACSWU2 = aCSWU2;
	}

	public float getACSWQ0() {
		return ACSWQ0;
	}

	public void setACSWQ0(float aCSWQ0) {
		ACSWQ0 = aCSWQ0;
	}

	public float getACSWQ1() {
		return ACSWQ1;
	}

	public void setACSWQ1(float aCSWQ1) {
		ACSWQ1 = aCSWQ1;
	}

	public float getACSWQ2() {
		return ACSWQ2;
	}

	public void setACSWQ2(float aCSWQ2) {
		ACSWQ2 = aCSWQ2;
	}

	public float getACSWQ3() {
		return ACSWQ3;
	}

	public void setACSWQ3(float aCSWQ3) {
		ACSWQ3 = aCSWQ3;
	}

	public int getACSWA0() {
		return ACSWA0;
	}

	public void setACSWA0(int aCSWA0) {
		ACSWA0 = aCSWA0;
	}

	public int getACSWA1() {
		return ACSWA1;
	}

	public void setACSWA1(int aCSWA1) {
		ACSWA1 = aCSWA1;
	}

	public int getACSWA2() {
		return ACSWA2;
	}

	public void setACSWA2(int aCSWA2) {
		ACSWA2 = aCSWA2;
	}

}
