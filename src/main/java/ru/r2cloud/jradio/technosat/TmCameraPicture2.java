package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class TmCameraPicture2 {

	private int PID;               // ID
	private int CHNUM;             // Chunk number
	private int TOTCH;             // Total chunks
	private byte[] CRC32;          // CRC32
	private long RECTM;            // Recording time
	private PictureType TYPE;      // Media type
	private byte[] PCHK;           // Raw Data

	public TmCameraPicture2(DataInputStream dis) throws IOException {
		PID = dis.readUnsignedShort();
		CHNUM = dis.readUnsignedShort();
		TOTCH = dis.readUnsignedShort();
		CRC32 = new byte[4];
		dis.readFully(CRC32);
		RECTM = StreamUtils.readUnsignedInt(dis);
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
