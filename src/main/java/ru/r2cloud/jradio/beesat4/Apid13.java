package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class Apid13 {

	private long[] imhd00; // Header slot #X Magic word
	private long[] imhd01; // Header slot #X CRC32, dept.
	private long[] imhd02; // Header slot #X Image size
	private long[] imhd03; // Header slot #X Base address
	private long[] imhd04; // Header slot #X CRC32, calc.

	public Apid13(DataInputStream dis) throws IOException {
		imhd00 = new long[4];
		imhd01 = new long[4];
		imhd02 = new long[4];
		imhd03 = new long[4];
		imhd04 = new long[4];
		for (int i = 0; i < 4; i++) {
			imhd00[i] = StreamUtils.readUnsignedInt(dis);
			imhd01[i] = StreamUtils.readUnsignedInt(dis);
			imhd02[i] = StreamUtils.readUnsignedInt(dis);
			imhd03[i] = StreamUtils.readUnsignedInt(dis);
			imhd04[i] = StreamUtils.readUnsignedInt(dis);
		}
		dis.skipBytes(46);
	}

	public long[] getImhd00() {
		return imhd00;
	}

	public void setImhd00(long[] imhd00) {
		this.imhd00 = imhd00;
	}

	public long[] getImhd01() {
		return imhd01;
	}

	public void setImhd01(long[] imhd01) {
		this.imhd01 = imhd01;
	}

	public long[] getImhd02() {
		return imhd02;
	}

	public void setImhd02(long[] imhd02) {
		this.imhd02 = imhd02;
	}

	public long[] getImhd03() {
		return imhd03;
	}

	public void setImhd03(long[] imhd03) {
		this.imhd03 = imhd03;
	}

	public long[] getImhd04() {
		return imhd04;
	}

	public void setImhd04(long[] imhd04) {
		this.imhd04 = imhd04;
	}

}
