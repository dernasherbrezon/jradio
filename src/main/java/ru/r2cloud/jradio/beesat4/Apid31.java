package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid31 {

	private int gpsweek; // gps week
	private int gpsutc; // gps time to utc offset
	private double gpssecw; // gps seconds of week
	private double gpsposx; // gps position x
	private double gpsposy; // gps position y
	private double gpsposz; // gps position z
	private double gpsvelx; // gps velocity x
	private double gpsvely; // gps velocity y
	private double gpsvelz; // gps velocity z
	private float gpspdop; // precision dilution of position
	private int gpsalmwk; // gps almanac week
	private int gpsdoffs; // gps doppler offset
	private int gpsnavstat; // gps navigation status
	private int gpsnsat; // gps number of tracked satellites
	private int gpsbitph; // gps bit placeholder
	private int gpsoutf; // gps output format
	private int gpsmode; // gps mode
	private int gpsupdr; // gps update rate of navigation
	private int gpselevm; // gps elevation mask
	private int gpspdopm; // gps pdop mask
	private double gpsltime; // gps launch time

	public Apid31(DataInputStream dis) throws IOException {
		gpsweek = dis.readUnsignedShort();
		gpsutc = dis.readUnsignedByte();
		gpssecw = dis.readDouble();
		gpsposx = dis.readDouble();
		gpsposy = dis.readDouble();
		gpsposz = dis.readDouble();
		gpsvelx = dis.readDouble();
		gpsvely = dis.readDouble();
		gpsvelz = dis.readDouble();
		gpspdop = dis.readFloat();
		gpsalmwk = dis.readUnsignedShort();
		gpsdoffs = dis.readShort();

		int raw = dis.readUnsignedByte();
		gpsnavstat = (raw >> 6);
		gpsnsat = (raw & 0b111111);

		raw = dis.readUnsignedByte();
		gpsbitph = (raw >> 7);
		gpsoutf = (raw >> 6);
		gpsmode = ((raw >> 4) & 0b11);
		gpsupdr = (raw & 0b1111);
		gpselevm = dis.readByte();
		gpspdopm = dis.readUnsignedByte();
		gpsltime = dis.readDouble();
	}

	public int getGpsweek() {
		return gpsweek;
	}

	public void setGpsweek(int gpsweek) {
		this.gpsweek = gpsweek;
	}

	public int getGpsutc() {
		return gpsutc;
	}

	public void setGpsutc(int gpsutc) {
		this.gpsutc = gpsutc;
	}

	public double getGpssecw() {
		return gpssecw;
	}

	public void setGpssecw(double gpssecw) {
		this.gpssecw = gpssecw;
	}

	public double getGpsposx() {
		return gpsposx;
	}

	public void setGpsposx(double gpsposx) {
		this.gpsposx = gpsposx;
	}

	public double getGpsposy() {
		return gpsposy;
	}

	public void setGpsposy(double gpsposy) {
		this.gpsposy = gpsposy;
	}

	public double getGpsposz() {
		return gpsposz;
	}

	public void setGpsposz(double gpsposz) {
		this.gpsposz = gpsposz;
	}

	public double getGpsvelx() {
		return gpsvelx;
	}

	public void setGpsvelx(double gpsvelx) {
		this.gpsvelx = gpsvelx;
	}

	public double getGpsvely() {
		return gpsvely;
	}

	public void setGpsvely(double gpsvely) {
		this.gpsvely = gpsvely;
	}

	public double getGpsvelz() {
		return gpsvelz;
	}

	public void setGpsvelz(double gpsvelz) {
		this.gpsvelz = gpsvelz;
	}

	public float getGpspdop() {
		return gpspdop;
	}

	public void setGpspdop(float gpspdop) {
		this.gpspdop = gpspdop;
	}

	public int getGpsalmwk() {
		return gpsalmwk;
	}

	public void setGpsalmwk(int gpsalmwk) {
		this.gpsalmwk = gpsalmwk;
	}

	public int getGpsdoffs() {
		return gpsdoffs;
	}

	public void setGpsdoffs(int gpsdoffs) {
		this.gpsdoffs = gpsdoffs;
	}

	public int getGpsnavstat() {
		return gpsnavstat;
	}

	public void setGpsnavstat(int gpsnavstat) {
		this.gpsnavstat = gpsnavstat;
	}

	public int getGpsnsat() {
		return gpsnsat;
	}

	public void setGpsnsat(int gpsnsat) {
		this.gpsnsat = gpsnsat;
	}

	public int getGpsbitph() {
		return gpsbitph;
	}

	public void setGpsbitph(int gpsbitph) {
		this.gpsbitph = gpsbitph;
	}

	public int getGpsoutf() {
		return gpsoutf;
	}

	public void setGpsoutf(int gpsoutf) {
		this.gpsoutf = gpsoutf;
	}

	public int getGpsmode() {
		return gpsmode;
	}

	public void setGpsmode(int gpsmode) {
		this.gpsmode = gpsmode;
	}

	public int getGpsupdr() {
		return gpsupdr;
	}

	public void setGpsupdr(int gpsupdr) {
		this.gpsupdr = gpsupdr;
	}

	public int getGpselevm() {
		return gpselevm;
	}

	public void setGpselevm(int gpselevm) {
		this.gpselevm = gpselevm;
	}

	public int getGpspdopm() {
		return gpspdopm;
	}

	public void setGpspdopm(int gpspdopm) {
		this.gpspdopm = gpspdopm;
	}

	public double getGpsltime() {
		return gpsltime;
	}

	public void setGpsltime(double gpsltime) {
		this.gpsltime = gpsltime;
	}

}
