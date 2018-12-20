package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid13 {

	private long[] IMHD00;    // Header slot #X Magic word
	private long[] IMHD01;    // Header slot #X CRC32, dept.
	private long[] IMHD02;    // Header slot #X Image size
	private long[] IMHD03;    // Header slot #X Base address
	private long[] IMHD04;    // Header slot #X CRC32, calc.

	public Apid13(DataInputStream dis) throws IOException {
		IMHD00 = new long[4];
		IMHD01 = new long[4];
		IMHD02 = new long[4];
		IMHD03 = new long[4];
		IMHD04 = new long[4];
		for (int i = 0; i < 4; i++) {
			IMHD00[i] = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
			IMHD01[i] = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
			IMHD02[i] = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
			IMHD03[i] = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
			IMHD04[i] = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		}
		dis.skipBytes(46);
	}

	public long[] getIMHD00() {
		return IMHD00;
	}

	public void setIMHD00(long[] iMHD00) {
		IMHD00 = iMHD00;
	}

	public long[] getIMHD01() {
		return IMHD01;
	}

	public void setIMHD01(long[] iMHD01) {
		IMHD01 = iMHD01;
	}

	public long[] getIMHD02() {
		return IMHD02;
	}

	public void setIMHD02(long[] iMHD02) {
		IMHD02 = iMHD02;
	}

	public long[] getIMHD03() {
		return IMHD03;
	}

	public void setIMHD03(long[] iMHD03) {
		IMHD03 = iMHD03;
	}

	public long[] getIMHD04() {
		return IMHD04;
	}

	public void setIMHD04(long[] iMHD04) {
		IMHD04 = iMHD04;
	}
	
	
}
