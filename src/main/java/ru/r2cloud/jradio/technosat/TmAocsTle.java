package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TmAocsTle {

	private int epochYear; // Epoch Year (Last two digits of year)
	private float epochDay; // Epoch (Day of the year and fractional portion of the day)
	private float meanMotionDt; // First Time Derivative of the Mean Motion
	private float meanMotionDdt; // Second Time Derivative of Mean Motion
	private float bStar; // BSTAR drag term
	private int ephemerisType; // The number 0 (originally this should have been "Ephemeris type")
	private int elementNumber; // Element number
	private float inclination; // Inclination
	private float raan; // Right Ascension of the Ascending Node
	private double eccentricity; // Eccentricity
	private float argPerigee; // Argument of Perigee
	private float meanAnomaly; // Mean Anomaly
	private float meanMotion; // Mean Motion
	private long revNumber; // Revolution number at epoch

	public TmAocsTle(DataInputStream dis) throws IOException {
		epochYear = dis.readUnsignedByte();
		epochDay = dis.readFloat();
		meanMotionDt = dis.readFloat();
		meanMotionDdt = dis.readFloat();
		bStar = dis.readFloat();
		ephemerisType = dis.readUnsignedByte();
		elementNumber = dis.readUnsignedShort();
		inclination = dis.readFloat();
		raan = dis.readFloat();
		eccentricity = dis.readDouble();
		argPerigee = dis.readFloat();
		meanAnomaly = dis.readFloat();
		meanMotion = dis.readFloat();
		revNumber = StreamUtils.readUnsignedInt(dis);
	}

	public int getEpochYear() {
		return epochYear;
	}

	public void setEpochYear(int epochYear) {
		this.epochYear = epochYear;
	}

	public float getEpochDay() {
		return epochDay;
	}

	public void setEpochDay(float epochDay) {
		this.epochDay = epochDay;
	}

	public float getMeanMotionDt() {
		return meanMotionDt;
	}

	public void setMeanMotionDt(float meanMotionDt) {
		this.meanMotionDt = meanMotionDt;
	}

	public float getMeanMotionDdt() {
		return meanMotionDdt;
	}

	public void setMeanMotionDdt(float meanMotionDdt) {
		this.meanMotionDdt = meanMotionDdt;
	}

	public float getbStar() {
		return bStar;
	}

	public void setbStar(float bStar) {
		this.bStar = bStar;
	}

	public int getEphemerisType() {
		return ephemerisType;
	}

	public void setEphemerisType(int ephemerisType) {
		this.ephemerisType = ephemerisType;
	}

	public int getElementNumber() {
		return elementNumber;
	}

	public void setElementNumber(int elementNumber) {
		this.elementNumber = elementNumber;
	}

	public float getInclination() {
		return inclination;
	}

	public void setInclination(float inclination) {
		this.inclination = inclination;
	}

	public float getRaan() {
		return raan;
	}

	public void setRaan(float raan) {
		this.raan = raan;
	}

	public double getEccentricity() {
		return eccentricity;
	}

	public void setEccentricity(double eccentricity) {
		this.eccentricity = eccentricity;
	}

	public float getArgPerigee() {
		return argPerigee;
	}

	public void setArgPerigee(float argPerigee) {
		this.argPerigee = argPerigee;
	}

	public float getMeanAnomaly() {
		return meanAnomaly;
	}

	public void setMeanAnomaly(float meanAnomaly) {
		this.meanAnomaly = meanAnomaly;
	}

	public float getMeanMotion() {
		return meanMotion;
	}

	public void setMeanMotion(float meanMotion) {
		this.meanMotion = meanMotion;
	}

	public long getRevNumber() {
		return revNumber;
	}

	public void setRevNumber(long revNumber) {
		this.revNumber = revNumber;
	}

}
