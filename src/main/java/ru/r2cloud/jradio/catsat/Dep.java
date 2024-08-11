package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Dep {

	private BeaconElementHeader hk1966;
	private short[] antBrn;
	private byte[] antRel;
	private short[] dspBrn;
	private byte[] dspRel;

	public Dep() {
		// do nothing
	}

	public Dep(DataInputStream dis) throws IOException {
		hk1966 = new BeaconElementHeader(dis);
		antBrn = StreamUtils.readShortArray(dis, 4);
		antRel = new byte[4];
		dis.readFully(antRel);
		dspBrn = StreamUtils.readShortArray(dis, 2);
		dspRel = new byte[2];
		dis.readFully(dspRel);
	}

	public BeaconElementHeader getHk1966() {
		return hk1966;
	}

	public void setHk1966(BeaconElementHeader hk1966) {
		this.hk1966 = hk1966;
	}

	public short[] getAntBrn() {
		return antBrn;
	}

	public void setAntBrn(short[] antBrn) {
		this.antBrn = antBrn;
	}

	public byte[] getAntRel() {
		return antRel;
	}

	public void setAntRel(byte[] antRel) {
		this.antRel = antRel;
	}

	public short[] getDspBrn() {
		return dspBrn;
	}

	public void setDspBrn(short[] dspBrn) {
		this.dspBrn = dspBrn;
	}

	public byte[] getDspRel() {
		return dspRel;
	}

	public void setDspRel(byte[] dspRel) {
		this.dspRel = dspRel;
	}

}
