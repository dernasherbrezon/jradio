package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid51U54 {

	private int almidx; // Almanac ID of PDH almanac data, unique per Slot
	private byte[] almdat; // Almanac Data

	public Apid51U54(DataInputStream dis) throws IOException {
		almidx = dis.readUnsignedByte();
		almdat = new byte[125];
		dis.readFully(almdat);
	}

	public int getAlmidx() {
		return almidx;
	}

	public void setAlmidx(int almidx) {
		this.almidx = almidx;
	}

	public byte[] getAlmdat() {
		return almdat;
	}

	public void setAlmdat(byte[] almdat) {
		this.almdat = almdat;
	}

}
