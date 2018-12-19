package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmCameraPicture2 {

	private int PID;
	private int CHNUM;
	private int TOTCH;
	private byte[] CRC32;
	private long RECTM;
	private PictureType TYPE;
	private byte[] PCHK;

	public TmCameraPicture2(DataInputStream dis) throws IOException {
		PID = dis.readUnsignedShort();
		CHNUM = dis.readUnsignedShort();
		TOTCH = dis.readUnsignedShort();
		CRC32 = new byte[4];
		dis.readFully(CRC32);
		RECTM = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		TYPE = PictureType.valueOfCode(dis.readUnsignedByte());
		PCHK = new byte[256];
		dis.readFully(PCHK);
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public int getCHNUM() {
		return CHNUM;
	}

	public void setCHNUM(int cHNUM) {
		CHNUM = cHNUM;
	}

	public int getTOTCH() {
		return TOTCH;
	}

	public void setTOTCH(int tOTCH) {
		TOTCH = tOTCH;
	}

	public byte[] getCRC32() {
		return CRC32;
	}

	public void setCRC32(byte[] cRC32) {
		CRC32 = cRC32;
	}

	public long getRECTM() {
		return RECTM;
	}

	public void setRECTM(long rECTM) {
		RECTM = rECTM;
	}

	public PictureType getTYPE() {
		return TYPE;
	}

	public void setTYPE(PictureType tYPE) {
		TYPE = tYPE;
	}

	public byte[] getPCHK() {
		return PCHK;
	}

	public void setPCHK(byte[] pCHK) {
		PCHK = pCHK;
	}

}
