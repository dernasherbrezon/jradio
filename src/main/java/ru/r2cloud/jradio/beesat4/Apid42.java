package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid42 {

	private double TLEDOY;    // TLE Day of Year
	private double TLEMEM;    // TLE Mean motion
	private double TLEECC;    // TLE Eccentricity
	private double TLEINC;    // TLE Inclination
	private double TLEAOP;    // TLE Arg of Perigee
	private double TLERAA;    // TLE Right Ascension of Ascending Node
	private double TLEMAN;    // TLE Mean Anomaly
	private double TLEDRT;    // TLE Drag Term
	private double TLEXPC;    // TLE X Pole Coordinate
	private double TLEYPC;    // TLE Y Pole Coordinate
	private double TLETTD;    // TLE TT and UTC time difference
	private double TLEUTD;    // TLE UT and UTC time difference
	private double TLEDEP;    // TLE Correction nutation angle (Delta Epsilon)
	private double TLEDPS;    // TLE Correction nutation angle (Delta Psi)
	private int TLEYEA;       // TLE Year

	public Apid42(DataInputStream dis) throws IOException {
		TLEDOY = dis.readDouble();
		TLEMEM = dis.readDouble();
		TLEECC = dis.readDouble();
		TLEINC = dis.readDouble();
		TLEAOP = dis.readDouble();
		TLERAA = dis.readDouble();
		TLEMAN = dis.readDouble();
		TLEDRT = dis.readDouble();
		TLEXPC = dis.readDouble();
		TLEYPC = dis.readDouble();
		TLETTD = dis.readDouble();
		TLEUTD = dis.readDouble();
		TLEDEP = dis.readDouble();
		TLEDPS = dis.readDouble();
		TLEYEA = dis.readUnsignedShort();
		dis.skipBytes(12);
	}

	public double getTLEDOY() {
		return TLEDOY;
	}

	public void setTLEDOY(double tLEDOY) {
		TLEDOY = tLEDOY;
	}

	public double getTLEMEM() {
		return TLEMEM;
	}

	public void setTLEMEM(double tLEMEM) {
		TLEMEM = tLEMEM;
	}

	public double getTLEECC() {
		return TLEECC;
	}

	public void setTLEECC(double tLEECC) {
		TLEECC = tLEECC;
	}

	public double getTLEINC() {
		return TLEINC;
	}

	public void setTLEINC(double tLEINC) {
		TLEINC = tLEINC;
	}

	public double getTLEAOP() {
		return TLEAOP;
	}

	public void setTLEAOP(double tLEAOP) {
		TLEAOP = tLEAOP;
	}

	public double getTLERAA() {
		return TLERAA;
	}

	public void setTLERAA(double tLERAA) {
		TLERAA = tLERAA;
	}

	public double getTLEMAN() {
		return TLEMAN;
	}

	public void setTLEMAN(double tLEMAN) {
		TLEMAN = tLEMAN;
	}

	public double getTLEDRT() {
		return TLEDRT;
	}

	public void setTLEDRT(double tLEDRT) {
		TLEDRT = tLEDRT;
	}

	public double getTLEXPC() {
		return TLEXPC;
	}

	public void setTLEXPC(double tLEXPC) {
		TLEXPC = tLEXPC;
	}

	public double getTLEYPC() {
		return TLEYPC;
	}

	public void setTLEYPC(double tLEYPC) {
		TLEYPC = tLEYPC;
	}

	public double getTLETTD() {
		return TLETTD;
	}

	public void setTLETTD(double tLETTD) {
		TLETTD = tLETTD;
	}

	public double getTLEUTD() {
		return TLEUTD;
	}

	public void setTLEUTD(double tLEUTD) {
		TLEUTD = tLEUTD;
	}

	public double getTLEDEP() {
		return TLEDEP;
	}

	public void setTLEDEP(double tLEDEP) {
		TLEDEP = tLEDEP;
	}

	public double getTLEDPS() {
		return TLEDPS;
	}

	public void setTLEDPS(double tLEDPS) {
		TLEDPS = tLEDPS;
	}

	public int getTLEYEA() {
		return TLEYEA;
	}

	public void setTLEYEA(int tLEYEA) {
		TLEYEA = tLEYEA;
	}

}
