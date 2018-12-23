package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Apid32 {

	private int[] ICID;                //  Image ID Slot X
	private long[] ICDATETIME;         //  Image capture datetime Slot X
	private int[] ICSIZE;              //  Image size in chunks Slot X
	private boolean[] ICTXDONE;        //  JPEG transfer done Slot X
	private boolean[] ICOF;            //  JPEG fifo overflow Slot X
	private boolean[] ICWBSTEADY;      //  Auto whitebalance steady Slot X
	private boolean[] ICAWBLIMIT;      //  Auto whitebalance limits reached Slot X
	private boolean[] ICAELIMIT;       //  Auto exposure limit Slot X
	private int[] ICTRIES;             //  Capture tries Slot X

	public Apid32(DataInputStream dis) throws IOException {
		int size = 15;
		ICID = new int[size];
		ICDATETIME = new long[size];
		ICSIZE = new int[size];
		ICTXDONE = new boolean[size];
		ICOF = new boolean[size];
		ICWBSTEADY = new boolean[size];
		ICAWBLIMIT = new boolean[size];
		ICAELIMIT = new boolean[size];
		ICTRIES = new int[size];
		for (int i = 0; i < size; i++) {
			ICID[i] = dis.readUnsignedByte();
			ICDATETIME[i] = StreamUtils.readUnsignedInt(dis);
			ICSIZE[i] = dis.readUnsignedShort();
			int raw = dis.readUnsignedByte();
			ICTXDONE[i] = ((raw >> 7) & 0x1) > 0;
			ICOF[i] = ((raw >> 6) & 0x1) > 0;
			ICWBSTEADY[i] = ((raw >> 5) & 0x1) > 0;
			ICAWBLIMIT[i] = ((raw >> 4) & 0x1) > 0;
			ICAELIMIT[i] = ((raw >> 3) & 0x1) > 0;
			ICTRIES[i] = (raw & 0b111);
		}
		dis.skipBytes(6);
	}

	public int[] getICID() {
		return ICID;
	}

	public void setICID(int[] iCID) {
		ICID = iCID;
	}

	public long[] getICDATETIME() {
		return ICDATETIME;
	}

	public void setICDATETIME(long[] iCDATETIME) {
		ICDATETIME = iCDATETIME;
	}

	public int[] getICSIZE() {
		return ICSIZE;
	}

	public void setICSIZE(int[] iCSIZE) {
		ICSIZE = iCSIZE;
	}

	public boolean[] getICTXDONE() {
		return ICTXDONE;
	}

	public void setICTXDONE(boolean[] iCTXDONE) {
		ICTXDONE = iCTXDONE;
	}

	public boolean[] getICOF() {
		return ICOF;
	}

	public void setICOF(boolean[] iCOF) {
		ICOF = iCOF;
	}

	public boolean[] getICWBSTEADY() {
		return ICWBSTEADY;
	}

	public void setICWBSTEADY(boolean[] iCWBSTEADY) {
		ICWBSTEADY = iCWBSTEADY;
	}

	public boolean[] getICAWBLIMIT() {
		return ICAWBLIMIT;
	}

	public void setICAWBLIMIT(boolean[] iCAWBLIMIT) {
		ICAWBLIMIT = iCAWBLIMIT;
	}

	public boolean[] getICAELIMIT() {
		return ICAELIMIT;
	}

	public void setICAELIMIT(boolean[] iCAELIMIT) {
		ICAELIMIT = iCAELIMIT;
	}

	public int[] getICTRIES() {
		return ICTRIES;
	}

	public void setICTRIES(int[] iCTRIES) {
		ICTRIES = iCTRIES;
	}

}
