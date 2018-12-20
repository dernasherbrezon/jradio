package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid6_12 {

	private long crc[]; // CRC32 slot #0 block #XX

	public Apid6_12(DataInputStream dis) throws IOException {
		crc = new long[31];
		for (int i = 0; i < crc.length; i++) {
			crc[i] = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		}
		dis.skipBytes(2);
	}

	public long[] getCrc() {
		return crc;
	}

	public void setCrc(long[] crc) {
		this.crc = crc;
	}

}
