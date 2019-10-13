package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmCameraPicture {

	private int pic; // ID
	private int chNum; // Chunk number
	private int totCh; // Total chunks
	private byte[] crc32; // CRC32
	private byte[] pchk; // Raw Data

	public TmCameraPicture(DataInputStream dis) throws IOException {
		pic = dis.readUnsignedByte();
		chNum = dis.readUnsignedShort();
		totCh = dis.readUnsignedShort();
		crc32 = new byte[4];
		dis.readFully(crc32);
		pchk = new byte[256];
		dis.readFully(pchk);
	}

	public int getPic() {
		return pic;
	}

	public void setPic(int pic) {
		this.pic = pic;
	}

	public int getChNum() {
		return chNum;
	}

	public void setChNum(int chNum) {
		this.chNum = chNum;
	}

	public int getTotCh() {
		return totCh;
	}

	public void setTotCh(int totCh) {
		this.totCh = totCh;
	}

	public byte[] getCrc32() {
		return crc32;
	}

	public void setCrc32(byte[] crc32) {
		this.crc32 = crc32;
	}

	public byte[] getPchk() {
		return pchk;
	}

	public void setPchk(byte[] pchk) {
		this.pchk = pchk;
	}

}
