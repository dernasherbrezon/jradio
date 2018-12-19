package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmFOR {

	private byte NODENO;
	private boolean RST_EN;
	private byte BOTSLT;
	private boolean SYNPPS;
	private boolean DISUTC;
	private boolean DULBSY;
	private ForMode FOR_MODE;

	public StdTmFOR(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		NODENO = (byte) (raw >> 7);
		RST_EN = ((raw >> 6) & 0x1) > 0;
		BOTSLT = (byte) ((raw >> 3) & 0x7);
		SYNPPS = ((raw >> 2) & 0x1) > 0;
		DISUTC = ((raw >> 1) & 0x1) > 0;
		DULBSY = ((raw >> 0) & 0x1) > 0;
		FOR_MODE = ForMode.valueOfCode(dis.readUnsignedByte());
	}

	public byte getNODENO() {
		return NODENO;
	}

	public void setNODENO(byte nODENO) {
		NODENO = nODENO;
	}

	public boolean isRST_EN() {
		return RST_EN;
	}

	public void setRST_EN(boolean rST_EN) {
		RST_EN = rST_EN;
	}

	public byte getBOTSLT() {
		return BOTSLT;
	}

	public void setBOTSLT(byte bOTSLT) {
		BOTSLT = bOTSLT;
	}

	public boolean isSYNPPS() {
		return SYNPPS;
	}

	public void setSYNPPS(boolean sYNPPS) {
		SYNPPS = sYNPPS;
	}

	public boolean isDISUTC() {
		return DISUTC;
	}

	public void setDISUTC(boolean dISUTC) {
		DISUTC = dISUTC;
	}

	public boolean isDULBSY() {
		return DULBSY;
	}

	public void setDULBSY(boolean dULBSY) {
		DULBSY = dULBSY;
	}

	public ForMode getFOR_MODE() {
		return FOR_MODE;
	}

	public void setFOR_MODE(ForMode fOR_MODE) {
		FOR_MODE = fOR_MODE;
	}

}
