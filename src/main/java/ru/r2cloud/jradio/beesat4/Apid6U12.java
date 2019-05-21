package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Apid6U12 {

	private long crc[]; // CRC32 slot #0 block #XX

	public Apid6U12(DataInputStream dis) throws IOException {
		crc = new long[31];
		for (int i = 0; i < crc.length; i++) {
			crc[i] = StreamUtils.readUnsignedInt(dis);
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
