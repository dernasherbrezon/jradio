package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Apid40 {

	private long[] gpsdt; // gps data time slot x
	private int[] gpssz; // gps data size in chunks slot X
	private int[] gpsid; // gps data id slot x

	private boolean[] almch; // almanac checksum ok slot X
	private int[] almdt; // almanac date slot x
	private boolean[] tlech; // tle checksum ok slot x
	private int[] tledt; // tle date slot x

	private boolean[] al0w; // slot 0 prn xx week ok
	private boolean[] al0v; // slot 0 prn xx vflg ok
	private boolean[] al1w; // slot 1 prn xx week ok
	private boolean[] al1v; // slot 1 prn xx vflg ok
	private boolean[] al2w; // slot 2 prn xx week ok
	private boolean[] al2v; // slot 2 prn xx vflg ok
	private boolean[] al3w; // slot 3 prn xx week ok
	private boolean[] al3v; // slot 3 prn xx vflg ok

	public Apid40(DataInputStream dis) throws IOException {
		int length = 8;
		gpsdt = new long[length];
		gpssz = new int[length];
		gpsid = new int[length];
		for (int i = 0; i < length; i++) {
			gpsdt[i] = StreamUtils.readUnsignedInt(dis);
			gpssz[i] = dis.readUnsignedShort();
			gpsid[i] = dis.readUnsignedByte();
		}

		length = 4;
		almch = new boolean[length];
		almdt = new int[length];
		tlech = new boolean[length];
		tledt = new int[length];

		for (int i = 0; i < length; i++) {
			int raw = dis.readUnsignedByte();
			almch[i] = ((raw >> 7) & 0x1) > 0;
			almdt[i] = ((raw & 0x7F) << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
			raw = dis.readUnsignedByte();
			tlech[i] = ((raw >> 7) & 0x1) > 0;
			tledt[i] = ((raw & 0x7F) << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		}

		al0w = readBooleanArray(dis);
		al0v = readBooleanArray(dis);
		al1w = readBooleanArray(dis);
		al1v = readBooleanArray(dis);
		al2w = readBooleanArray(dis);
		al2v = readBooleanArray(dis);
		al3w = readBooleanArray(dis);
		al3v = readBooleanArray(dis);

		dis.skipBytes(6);
	}

	public long[] getGpsdt() {
		return gpsdt;
	}

	public void setGpsdt(long[] gpsdt) {
		this.gpsdt = gpsdt;
	}

	public int[] getGpssz() {
		return gpssz;
	}

	public void setGpssz(int[] gpssz) {
		this.gpssz = gpssz;
	}

	public int[] getGpsid() {
		return gpsid;
	}

	public void setGpsid(int[] gpsid) {
		this.gpsid = gpsid;
	}

	public boolean[] getAlmch() {
		return almch;
	}

	public void setAlmch(boolean[] almch) {
		this.almch = almch;
	}

	public int[] getAlmdt() {
		return almdt;
	}

	public void setAlmdt(int[] almdt) {
		this.almdt = almdt;
	}

	public boolean[] getTlech() {
		return tlech;
	}

	public void setTlech(boolean[] tlech) {
		this.tlech = tlech;
	}

	public int[] getTledt() {
		return tledt;
	}

	public void setTledt(int[] tledt) {
		this.tledt = tledt;
	}

	public boolean[] getAl0w() {
		return al0w;
	}

	public void setAl0w(boolean[] al0w) {
		this.al0w = al0w;
	}

	public boolean[] getAl0v() {
		return al0v;
	}

	public void setAl0v(boolean[] al0v) {
		this.al0v = al0v;
	}

	public boolean[] getAl1w() {
		return al1w;
	}

	public void setAl1w(boolean[] al1w) {
		this.al1w = al1w;
	}

	public boolean[] getAl1v() {
		return al1v;
	}

	public void setAl1v(boolean[] al1v) {
		this.al1v = al1v;
	}

	public boolean[] getAl2w() {
		return al2w;
	}

	public void setAl2w(boolean[] al2w) {
		this.al2w = al2w;
	}

	public boolean[] getAl2v() {
		return al2v;
	}

	public void setAl2v(boolean[] al2v) {
		this.al2v = al2v;
	}

	public boolean[] getAl3w() {
		return al3w;
	}

	public void setAl3w(boolean[] al3w) {
		this.al3w = al3w;
	}

	public boolean[] getAl3v() {
		return al3v;
	}

	public void setAl3v(boolean[] al3v) {
		this.al3v = al3v;
	}

	private static boolean[] readBooleanArray(DataInputStream dis) throws IOException {
		boolean[] result = new boolean[32];
		int raw = 0;
		for (int i = 0; i < result.length; i++) {
			int remainder = i % 8;
			if (remainder == 0) {
				raw = dis.readUnsignedByte();
			}
			result[i] = ((raw >> (7 - remainder)) & 0x1) > 0;
		}
		return result;
	}
}
