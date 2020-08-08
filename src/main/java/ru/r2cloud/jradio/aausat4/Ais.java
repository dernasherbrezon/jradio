package ru.r2cloud.jradio.aausat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Ais {

	private int bootCount;
	private int uniqueMssi;

	public Ais() {
		// do nothing
	}

	public Ais(DataInputStream data) throws IOException {
		bootCount = data.readUnsignedShort();
		data.skipBytes(4);
		uniqueMssi = data.readUnsignedShort();
		data.skipBytes(12);
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public int getUniqueMssi() {
		return uniqueMssi;
	}

	public void setUniqueMssi(int uniqueMssi) {
		this.uniqueMssi = uniqueMssi;
	}

}
