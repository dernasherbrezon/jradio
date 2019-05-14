package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid42 {

	private double tledoy;    // TLE Day of Year
	private double tlemem;    // TLE Mean motion
	private double tleecc;    // TLE Eccentricity
	private double tleinc;    // TLE Inclination
	private double tleaop;    // TLE Arg of Perigee
	private double tleraa;    // TLE Right Ascension of Ascending Node
	private double tleman;    // TLE Mean Anomaly
	private double tledrt;    // TLE Drag Term
	private double tlexpc;    // TLE X Pole Coordinate
	private double tleypc;    // TLE Y Pole Coordinate
	private double tlettd;    // TLE TT and UTC time difference
	private double tleutd;    // TLE UT and UTC time difference
	private double tledep;    // TLE Correction nutation angle (Delta Epsilon)
	private double tledps;    // TLE Correction nutation angle (Delta Psi)
	private int tleyea;       // TLE Year

	public Apid42(DataInputStream dis) throws IOException {
		tledoy = dis.readDouble();
		tlemem = dis.readDouble();
		tleecc = dis.readDouble();
		tleinc = dis.readDouble();
		tleaop = dis.readDouble();
		tleraa = dis.readDouble();
		tleman = dis.readDouble();
		tledrt = dis.readDouble();
		tlexpc = dis.readDouble();
		tleypc = dis.readDouble();
		tlettd = dis.readDouble();
		tleutd = dis.readDouble();
		tledep = dis.readDouble();
		tledps = dis.readDouble();
		tleyea = dis.readUnsignedShort();
		dis.skipBytes(12);
	}

	public double getTledoy() {
		return tledoy;
	}

	public void setTledoy(double tledoy) {
		this.tledoy = tledoy;
	}

	public double getTlemem() {
		return tlemem;
	}

	public void setTlemem(double tlemem) {
		this.tlemem = tlemem;
	}

	public double getTleecc() {
		return tleecc;
	}

	public void setTleecc(double tleecc) {
		this.tleecc = tleecc;
	}

	public double getTleinc() {
		return tleinc;
	}

	public void setTleinc(double tleinc) {
		this.tleinc = tleinc;
	}

	public double getTleaop() {
		return tleaop;
	}

	public void setTleaop(double tleaop) {
		this.tleaop = tleaop;
	}

	public double getTleraa() {
		return tleraa;
	}

	public void setTleraa(double tleraa) {
		this.tleraa = tleraa;
	}

	public double getTleman() {
		return tleman;
	}

	public void setTleman(double tleman) {
		this.tleman = tleman;
	}

	public double getTledrt() {
		return tledrt;
	}

	public void setTledrt(double tledrt) {
		this.tledrt = tledrt;
	}

	public double getTlexpc() {
		return tlexpc;
	}

	public void setTlexpc(double tlexpc) {
		this.tlexpc = tlexpc;
	}

	public double getTleypc() {
		return tleypc;
	}

	public void setTleypc(double tleypc) {
		this.tleypc = tleypc;
	}

	public double getTlettd() {
		return tlettd;
	}

	public void setTlettd(double tlettd) {
		this.tlettd = tlettd;
	}

	public double getTleutd() {
		return tleutd;
	}

	public void setTleutd(double tleutd) {
		this.tleutd = tleutd;
	}

	public double getTledep() {
		return tledep;
	}

	public void setTledep(double tledep) {
		this.tledep = tledep;
	}

	public double getTledps() {
		return tledps;
	}

	public void setTledps(double tledps) {
		this.tledps = tledps;
	}

	public int getTleyea() {
		return tleyea;
	}

	public void setTleyea(int tleyea) {
		this.tleyea = tleyea;
	}

	

}
