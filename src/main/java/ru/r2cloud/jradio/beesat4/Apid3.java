package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid3 {

	private float acswcx; // commanded wheel speed X
	private float acswcy; // commanded wheel speed Y
	private float acswcz; // commanded wheel speed Z
	private float acsm2y; // mfs #2: vector y
	private float acsm2z; // mfs #2: vector z
	private float acsm2x; // mfs #2: vector x
	private float acsg2y; // gyro rate max21000 y-axis
	private float acsg2z; // gyro rate max21000 z-axis
	private float acsg2x; // gyro rate max21000 x-axis
	private float acsg1y; // gyro #1 rate y
	private float acsg1z; // gyro #1 rate z
	private float acsg1x; // gyro #1 rate x
	private float acg1ym; // gyro #1 rate y mean
	private float acg1zm; // gyro #1 rate z mean
	private float acg1xm; // gyro #1 rate x mean
	private long cstutc; // onboard time utc
	private long cstsys; // obdh uptime
	private long pcsyst; // pcu uptime
	private long asstop; // auto send stop time
	private int asintv; // auto send interval
	private int ascest; // auto send start error
	private boolean asactv; // auto send active
	private float acswu0; // wheelcontroller u0
	private float acswu1; // wheelcontroller u1
	private float acswu2; // wheelcontroller u2
	private float acswq0; // error quaternion x
	private float acswq1; // error quaternion y
	private float acswq2; // error quaternion z
	private float acswq3; // error quaternion scalar
	private int acswa0; // wheel accelaration 0
	private int acswa1; // wheel accelaration 1
	private int acswa2; // wheel accelaration 2

	public Apid3(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);

		acswcx = bis.readUnsignedShort() * -0.5f;
		acswcy = bis.readUnsignedShort() * -0.5f;
		acswcz = bis.readUnsignedShort() * 0.5f;
		acsm2y = bis.readUnsignedShort() * -7.642841368f + 7968.73f;
		acsm2z = bis.readUnsignedShort() * 8.032315024f - 10266.647f;
		acsm2x = bis.readUnsignedShort() * -7.890292416f - 11584.291f;
		acsg2y = bis.readUnsignedShort() * -0.00104167f;
		acsg2z = bis.readUnsignedShort() * 0.00104167f;
		acsg2x = bis.readUnsignedShort() * -0.00104167f;
		acsg1y = bis.readUnsignedShort() * -0.00875f;
		acsg1z = bis.readUnsignedShort() * -0.00875f;
		acsg1x = bis.readUnsignedShort() * -0.00875f;
		acg1ym = bis.readUnsignedShort() * -0.00875f;
		acg1zm = bis.readUnsignedShort() * -0.00875f;
		acg1xm = bis.readUnsignedShort() * -0.00875f;
		cstutc = bis.readUnsignedLong(32);
		cstsys = bis.readUnsignedLong(32);
		pcsyst = bis.readUnsignedLong(32);
		asstop = bis.readUnsignedLong(32);
		asintv = bis.readUnsignedInt(16);
		ascest = bis.readUnsignedInt(8);
		asactv = bis.readBoolean();
		acswu0 = bis.readUnsignedShort() * 0.00000001f;
		acswu1 = bis.readUnsignedShort() * 0.00000001f;
		acswu2 = bis.readUnsignedShort() * 0.00000001f;
		acswq0 = bis.readUnsignedShort() * 0.0001f;
		acswq1 = bis.readUnsignedShort() * 0.0001f;
		acswq2 = bis.readUnsignedShort() * 0.0001f;
		acswq3 = bis.readUnsignedShort() * 0.0001f;
		acswa0 = bis.readUnsignedShort();
		acswa1 = bis.readUnsignedShort();
		acswa2 = bis.readUnsignedShort();
		dis.skipBytes(56);
	}

	public float getAcswcx() {
		return acswcx;
	}

	public void setAcswcx(float acswcx) {
		this.acswcx = acswcx;
	}

	public float getAcswcy() {
		return acswcy;
	}

	public void setAcswcy(float acswcy) {
		this.acswcy = acswcy;
	}

	public float getAcswcz() {
		return acswcz;
	}

	public void setAcswcz(float acswcz) {
		this.acswcz = acswcz;
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

	public float getAcsm2x() {
		return acsm2x;
	}

	public void setAcsm2x(float acsm2x) {
		this.acsm2x = acsm2x;
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

	public float getAcg1ym() {
		return acg1ym;
	}

	public void setAcg1ym(float acg1ym) {
		this.acg1ym = acg1ym;
	}

	public float getAcg1zm() {
		return acg1zm;
	}

	public void setAcg1zm(float acg1zm) {
		this.acg1zm = acg1zm;
	}

	public float getAcg1xm() {
		return acg1xm;
	}

	public void setAcg1xm(float acg1xm) {
		this.acg1xm = acg1xm;
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

	public long getAsstop() {
		return asstop;
	}

	public void setAsstop(long asstop) {
		this.asstop = asstop;
	}

	public int getAsintv() {
		return asintv;
	}

	public void setAsintv(int asintv) {
		this.asintv = asintv;
	}

	public int getAscest() {
		return ascest;
	}

	public void setAscest(int ascest) {
		this.ascest = ascest;
	}

	public boolean isAsactv() {
		return asactv;
	}

	public void setAsactv(boolean asactv) {
		this.asactv = asactv;
	}

	public float getAcswu0() {
		return acswu0;
	}

	public void setAcswu0(float acswu0) {
		this.acswu0 = acswu0;
	}

	public float getAcswu1() {
		return acswu1;
	}

	public void setAcswu1(float acswu1) {
		this.acswu1 = acswu1;
	}

	public float getAcswu2() {
		return acswu2;
	}

	public void setAcswu2(float acswu2) {
		this.acswu2 = acswu2;
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

}
