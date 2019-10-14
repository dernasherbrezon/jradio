package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TmCameraPicture2 {

	private int pid; // ID
	private int chNum; // Chunk number
	private int totCh; // Total chunks
	private byte[] crc32; // CRC32
	private long recTm; // Recording time
	private PictureType type; // Media type
	private byte[] pchk; // Raw Data

	public TmCameraPicture2(DataInputStream dis) throws IOException {
		pid = dis.readUnsignedShort();
		chNum = dis.readUnsignedShort();
		totCh = dis.readUnsignedShort();
		crc32 = new byte[4];
		dis.readFully(crc32);
		recTm = StreamUtils.readUnsignedInt(dis);
		type = PictureType.valueOfCode(dis.readUnsignedByte());
		pchk = new byte[256];
		dis.readFully(pchk);
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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

	public long getRecTm() {
		return recTm;
	}

	public void setRecTm(long recTm) {
		this.recTm = recTm;
	}

	public PictureType getType() {
		return type;
	}

	public void setType(PictureType type) {
		this.type = type;
	}

	public byte[] getPchk() {
		return pchk;
	}

	public void setPchk(byte[] pchk) {
		this.pchk = pchk;
	}

}
