package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmAocsTle {

	private int EPOCH_YEAR;
	private float EPOCH_DAY;
	private float MEAN_MOTION_DT;
	private float MEAN_MOTION_DDT;
	private float BSTAR;
	private int EPHEMERIS_TYPE;
	private int ELEMENT_NUMBER;
	private float INCLINATION;
	private float RAAN;
	private double ECCENTRICITY;
	private float ARG_PERIGEE;
	private float MEAN_ANOMALY;
	private float MEAN_MOTION;
	private long REV_NUMBER;

	public TmAocsTle(DataInputStream dis) throws IOException {
		EPOCH_YEAR = dis.readUnsignedByte();
		EPOCH_DAY = dis.readFloat();
		MEAN_MOTION_DT = dis.readFloat();
		MEAN_MOTION_DDT = dis.readFloat();
		BSTAR = dis.readFloat();
		EPHEMERIS_TYPE = dis.readUnsignedByte();
		ELEMENT_NUMBER = dis.readUnsignedShort();
		INCLINATION = dis.readFloat();
		RAAN = dis.readFloat();
		ECCENTRICITY = dis.readDouble();
		ARG_PERIGEE = dis.readFloat();
		MEAN_ANOMALY = dis.readFloat();
		MEAN_MOTION = dis.readFloat();
		REV_NUMBER = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
	}

	public int getEPOCH_YEAR() {
		return EPOCH_YEAR;
	}

	public void setEPOCH_YEAR(int ePOCH_YEAR) {
		EPOCH_YEAR = ePOCH_YEAR;
	}

	public float getEPOCH_DAY() {
		return EPOCH_DAY;
	}

	public void setEPOCH_DAY(float ePOCH_DAY) {
		EPOCH_DAY = ePOCH_DAY;
	}

	public float getMEAN_MOTION_DT() {
		return MEAN_MOTION_DT;
	}

	public void setMEAN_MOTION_DT(float mEAN_MOTION_DT) {
		MEAN_MOTION_DT = mEAN_MOTION_DT;
	}

	public float getMEAN_MOTION_DDT() {
		return MEAN_MOTION_DDT;
	}

	public void setMEAN_MOTION_DDT(float mEAN_MOTION_DDT) {
		MEAN_MOTION_DDT = mEAN_MOTION_DDT;
	}

	public float getBSTAR() {
		return BSTAR;
	}

	public void setBSTAR(float bSTAR) {
		BSTAR = bSTAR;
	}

	public int getEPHEMERIS_TYPE() {
		return EPHEMERIS_TYPE;
	}

	public void setEPHEMERIS_TYPE(int ePHEMERIS_TYPE) {
		EPHEMERIS_TYPE = ePHEMERIS_TYPE;
	}

	public int getELEMENT_NUMBER() {
		return ELEMENT_NUMBER;
	}

	public void setELEMENT_NUMBER(int eLEMENT_NUMBER) {
		ELEMENT_NUMBER = eLEMENT_NUMBER;
	}

	public float getINCLINATION() {
		return INCLINATION;
	}

	public void setINCLINATION(float iNCLINATION) {
		INCLINATION = iNCLINATION;
	}

	public float getRAAN() {
		return RAAN;
	}

	public void setRAAN(float rAAN) {
		RAAN = rAAN;
	}

	public double getECCENTRICITY() {
		return ECCENTRICITY;
	}

	public void setECCENTRICITY(double eCCENTRICITY) {
		ECCENTRICITY = eCCENTRICITY;
	}

	public float getARG_PERIGEE() {
		return ARG_PERIGEE;
	}

	public void setARG_PERIGEE(float aRG_PERIGEE) {
		ARG_PERIGEE = aRG_PERIGEE;
	}

	public float getMEAN_ANOMALY() {
		return MEAN_ANOMALY;
	}

	public void setMEAN_ANOMALY(float mEAN_ANOMALY) {
		MEAN_ANOMALY = mEAN_ANOMALY;
	}

	public float getMEAN_MOTION() {
		return MEAN_MOTION;
	}

	public void setMEAN_MOTION(float mEAN_MOTION) {
		MEAN_MOTION = mEAN_MOTION;
	}

	public long getREV_NUMBER() {
		return REV_NUMBER;
	}

	public void setREV_NUMBER(long rEV_NUMBER) {
		REV_NUMBER = rEV_NUMBER;
	}

}
