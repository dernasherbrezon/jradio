package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmAocsTle {

	private int EPOCH_YEAR;               //  Epoch Year	(Last two digits of year)
	private float EPOCH_DAY;              //  Epoch	(Day of the year and fractional portion of the day)
	private float MEAN_MOTION_DT;         //  First Time Derivative of the Mean Motion	
	private float MEAN_MOTION_DDT;        //  Second Time Derivative of Mean Motion	
	private float BSTAR;                  //  BSTAR drag term	
	private int EPHEMERIS_TYPE;           //  The number 0	(originally this should have been "Ephemeris type")
	private int ELEMENT_NUMBER;           //  Element number	
	private float INCLINATION;            //  Inclination	
	private float RAAN;                   //  Right Ascension of the Ascending Node	
	private double ECCENTRICITY;          //  Eccentricity	
	private float ARG_PERIGEE;            //  Argument of Perigee	
	private float MEAN_ANOMALY;           //  Mean Anomaly	
	private float MEAN_MOTION;            //  Mean Motion	
	private long REV_NUMBER;              //  Revolution number at epoch	

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
