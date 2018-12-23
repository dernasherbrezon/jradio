package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid43_50 {

	private int GPSIDX; // GPS ID of PDH GPS data, unique per Slot
	private byte[] GPSDAT; // GPS Data

	public Apid43_50(DataInputStream dis) throws IOException {
		GPSIDX = dis.readUnsignedByte();
		GPSDAT = new byte[125];
		dis.readFully(GPSDAT);
	}

	public int getGPSIDX() {
		return GPSIDX;
	}

	public void setGPSIDX(int gPSIDX) {
		GPSIDX = gPSIDX;
	}

	public byte[] getGPSDAT() {
		return GPSDAT;
	}

	public void setGPSDAT(byte[] gPSDAT) {
		GPSDAT = gPSDAT;
	}

}
