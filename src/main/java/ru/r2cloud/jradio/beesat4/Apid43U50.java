package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid43U50 {

	private int gpsidx; // gps iD of PDH GPS data, unique per Slot
	private byte[] gpsdat; // gpS Data

	public Apid43U50(DataInputStream dis) throws IOException {
		gpsidx = dis.readUnsignedByte();
		gpsdat = new byte[125];
		dis.readFully(gpsdat);
	}

	public int getGpsidx() {
		return gpsidx;
	}

	public void setGpsidx(int gpsidx) {
		this.gpsidx = gpsidx;
	}

	public byte[] getGpsdat() {
		return gpsdat;
	}

	public void setGpsdat(byte[] gpsdat) {
		this.gpsdat = gpsdat;
	}

}
