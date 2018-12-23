package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid40 {

	private long[] GPSDT;       // GPS Data Time Slot X
	private int[] GPSSZ;        // GPS Data Size in Chunks Slot X
	private int[] GPSID;        // GPS Data ID Slot X

	private boolean[] ALMCH;    // Almanac Checksum OK Slot X
	private int[] ALMDT;        // Almanac Date Slot X
	private boolean[] TLECH;    // TLE Checksum OK Slot X
	private int[] TLEDT;        // TLE Date Slot X

	private boolean[] AL0W;     // Slot 0 PRN XX Week OK
	private boolean[] AL0V;     // Slot 0 PRN XX VFLG OK
	private boolean[] AL1W;     // Slot 1 PRN XX Week OK
	private boolean[] AL1V;     // Slot 1 PRN XX VFLG OK
	private boolean[] AL2W;     // Slot 2 PRN XX Week OK
	private boolean[] AL2V;     // Slot 2 PRN XX VFLG OK
	private boolean[] AL3W;     // Slot 3 PRN XX Week OK
	private boolean[] AL3V;     // Slot 3 PRN XX VFLG OK

	public Apid40(DataInputStream dis) throws IOException {
		int length = 8;
		GPSDT = new long[length];
		GPSSZ = new int[length];
		GPSID = new int[length];
		for (int i = 0; i < length; i++) {
			GPSDT[i] = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
			GPSSZ[i] = dis.readUnsignedShort();
			GPSID[i] = dis.readUnsignedByte();
		}

		length = 4;
		ALMCH = new boolean[length];
		ALMDT = new int[length];
		TLECH = new boolean[length];
		TLEDT = new int[length];

		for (int i = 0; i < length; i++) {
			int raw = dis.readUnsignedByte();
			ALMCH[i] = ((raw >> 7) & 0x1) > 0;
			ALMDT[i] = ((raw & 0x7F) << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
			raw = dis.readUnsignedByte();
			TLECH[i] = ((raw >> 7) & 0x1) > 0;
			TLEDT[i] = ((raw & 0x7F) << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		}

		AL0W = readBooleanArray(dis);
		AL0V = readBooleanArray(dis);
		AL1W = readBooleanArray(dis);
		AL1V = readBooleanArray(dis);
		AL2W = readBooleanArray(dis);
		AL2V = readBooleanArray(dis);
		AL3W = readBooleanArray(dis);
		AL3V = readBooleanArray(dis);

		dis.skipBytes(6);
	}

	public long[] getGPSDT() {
		return GPSDT;
	}

	public void setGPSDT(long[] gPSDT) {
		GPSDT = gPSDT;
	}

	public int[] getGPSSZ() {
		return GPSSZ;
	}

	public void setGPSSZ(int[] gPSSZ) {
		GPSSZ = gPSSZ;
	}

	public int[] getGPSID() {
		return GPSID;
	}

	public void setGPSID(int[] gPSID) {
		GPSID = gPSID;
	}

	public boolean[] getALMCH() {
		return ALMCH;
	}

	public void setALMCH(boolean[] aLMCH) {
		ALMCH = aLMCH;
	}

	public int[] getALMDT() {
		return ALMDT;
	}

	public void setALMDT(int[] aLMDT) {
		ALMDT = aLMDT;
	}

	public boolean[] getTLECH() {
		return TLECH;
	}

	public void setTLECH(boolean[] tLECH) {
		TLECH = tLECH;
	}

	public int[] getTLEDT() {
		return TLEDT;
	}

	public void setTLEDT(int[] tLEDT) {
		TLEDT = tLEDT;
	}

	public boolean[] getAL0W() {
		return AL0W;
	}

	public void setAL0W(boolean[] aL0W) {
		AL0W = aL0W;
	}

	public boolean[] getAL0V() {
		return AL0V;
	}

	public void setAL0V(boolean[] aL0V) {
		AL0V = aL0V;
	}

	public boolean[] getAL1W() {
		return AL1W;
	}

	public void setAL1W(boolean[] aL1W) {
		AL1W = aL1W;
	}

	public boolean[] getAL1V() {
		return AL1V;
	}

	public void setAL1V(boolean[] aL1V) {
		AL1V = aL1V;
	}

	public boolean[] getAL2W() {
		return AL2W;
	}

	public void setAL2W(boolean[] aL2W) {
		AL2W = aL2W;
	}

	public boolean[] getAL2V() {
		return AL2V;
	}

	public void setAL2V(boolean[] aL2V) {
		AL2V = aL2V;
	}

	public boolean[] getAL3W() {
		return AL3W;
	}

	public void setAL3W(boolean[] aL3W) {
		AL3W = aL3W;
	}

	public boolean[] getAL3V() {
		return AL3V;
	}

	public void setAL3V(boolean[] aL3V) {
		AL3V = aL3V;
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
