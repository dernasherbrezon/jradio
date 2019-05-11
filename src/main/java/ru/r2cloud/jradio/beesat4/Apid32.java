package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Apid32 {

	private int[] icid;                //  image id slot x
	private long[] icdatetime;         //  image capture datetime Slot X
	private int[] icsize;              //  image size in chunks slot X
	private boolean[] ictxdone;        //  jpeg transfer done slot X
	private boolean[] icof;            //  jpeg fifo overflow slot X
	private boolean[] icwbsteady;      //  auto whitebalance steady Slot X
	private boolean[] icawblimit;      //  auto whitebalance limits reached Slot X
	private boolean[] icaelimit;       //  auto exposure limit slot X
	private int[] ictries;             //  capture tries slot x

	public Apid32(DataInputStream dis) throws IOException {
		int size = 15;
		icid = new int[size];
		icdatetime = new long[size];
		icsize = new int[size];
		ictxdone = new boolean[size];
		icof = new boolean[size];
		icwbsteady = new boolean[size];
		icawblimit = new boolean[size];
		icaelimit = new boolean[size];
		ictries = new int[size];
		for (int i = 0; i < size; i++) {
			icid[i] = dis.readUnsignedByte();
			icdatetime[i] = StreamUtils.readUnsignedInt(dis);
			icsize[i] = dis.readUnsignedShort();
			int raw = dis.readUnsignedByte();
			ictxdone[i] = ((raw >> 7) & 0x1) > 0;
			icof[i] = ((raw >> 6) & 0x1) > 0;
			icwbsteady[i] = ((raw >> 5) & 0x1) > 0;
			icawblimit[i] = ((raw >> 4) & 0x1) > 0;
			icaelimit[i] = ((raw >> 3) & 0x1) > 0;
			ictries[i] = (raw & 0b111);
		}
		dis.skipBytes(6);
	}

	public int[] getIcid() {
		return icid;
	}

	public void setIcid(int[] icid) {
		this.icid = icid;
	}

	public long[] getIcdatetime() {
		return icdatetime;
	}

	public void setIcdatetime(long[] icdatetime) {
		this.icdatetime = icdatetime;
	}

	public int[] getIcsize() {
		return icsize;
	}

	public void setIcsize(int[] icsize) {
		this.icsize = icsize;
	}

	public boolean[] getIctxdone() {
		return ictxdone;
	}

	public void setIctxdone(boolean[] ictxdone) {
		this.ictxdone = ictxdone;
	}

	public boolean[] getIcof() {
		return icof;
	}

	public void setIcof(boolean[] icof) {
		this.icof = icof;
	}

	public boolean[] getIcwbsteady() {
		return icwbsteady;
	}

	public void setIcwbsteady(boolean[] icwbsteady) {
		this.icwbsteady = icwbsteady;
	}

	public boolean[] getIcawblimit() {
		return icawblimit;
	}

	public void setIcawblimit(boolean[] icawblimit) {
		this.icawblimit = icawblimit;
	}

	public boolean[] getIcaelimit() {
		return icaelimit;
	}

	public void setIcaelimit(boolean[] icaelimit) {
		this.icaelimit = icaelimit;
	}

	public int[] getIctries() {
		return ictries;
	}

	public void setIctries(int[] ictries) {
		this.ictries = ictries;
	}

	
}
