package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.bobcat1.BeaconElementHeader;
import ru.r2cloud.jradio.util.StreamUtils;

public class Bcn {

	private BeaconElementHeader hk19393;
	private int[] infBlob;

	public Bcn() {
		// do nothing
	}

	public Bcn(DataInputStream dis) throws IOException {
		hk19393 = new BeaconElementHeader(dis);
		infBlob = StreamUtils.readUnsignedByteArray(dis, 42);
	}

	public BeaconElementHeader getHk19393() {
		return hk19393;
	}

	public void setHk19393(BeaconElementHeader hk19393) {
		this.hk19393 = hk19393;
	}

	public int[] getInfBlob() {
		return infBlob;
	}

	public void setInfBlob(int[] infBlob) {
		this.infBlob = infBlob;
	}

}
