package ru.r2cloud.jradio.aausat4;

import java.io.DataInputStream;
import java.io.IOException;

public class AIS {

	private int bootCount;
	private int unique_mssi;

	public AIS(DataInputStream data) throws IOException {
		bootCount = data.readUnsignedShort();
		data.skipBytes(4);
		unique_mssi = data.readUnsignedShort();
		data.skipBytes(12);
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public int getUnique_mssi() {
		return unique_mssi;
	}

	public void setUnique_mssi(int unique_mssi) {
		this.unique_mssi = unique_mssi;
	}

}
