package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class NodeInfos {

	private int CNTIMM;          // counts all immediate telecommands for this node
	private int CNTTTC;          // counts all timed telecommands for this node
	private int CNTINT;          // counts all internal commands for this node
	private int CNTEXE;          // counts all commands executed by applications on this node
	private int CNTREJ;          // counts all commands rejected (bad TCC or Parameter) by applications on this node
	private int LSTCID;          // ID of the last immediate/timetagged/internal command that was received by applications on this node (executed and rejected)
	private float MCUCUR;        // supply current of the node's microcontroller
	private long RSTSTS;         // the reset status register contents
	private int BOTCNT;          // counts all soft- and hardware triggered reboots and power-ups
	private long TIMLOC;         // current uptime since last reboot
	private byte TMPINT;         // internal temperature of node-MCU

	public NodeInfos(DataInputStream dis) throws IOException {
		CNTIMM = dis.readUnsignedByte();
		CNTTTC = dis.readUnsignedByte();
		CNTINT = dis.readUnsignedByte();
		CNTEXE = dis.readUnsignedByte();
		CNTREJ = dis.readUnsignedByte();
		LSTCID = dis.readUnsignedByte();
		MCUCUR = dis.readUnsignedShort() * 0.1f;
		RSTSTS = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		BOTCNT = dis.readUnsignedShort();
		TIMLOC = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		TMPINT = dis.readByte();
	}

	public int getCNTIMM() {
		return CNTIMM;
	}

	public void setCNTIMM(int cNTIMM) {
		CNTIMM = cNTIMM;
	}

	public int getCNTTTC() {
		return CNTTTC;
	}

	public void setCNTTTC(int cNTTTC) {
		CNTTTC = cNTTTC;
	}

	public int getCNTINT() {
		return CNTINT;
	}

	public void setCNTINT(int cNTINT) {
		CNTINT = cNTINT;
	}

	public int getCNTEXE() {
		return CNTEXE;
	}

	public void setCNTEXE(int cNTEXE) {
		CNTEXE = cNTEXE;
	}

	public int getCNTREJ() {
		return CNTREJ;
	}

	public void setCNTREJ(int cNTREJ) {
		CNTREJ = cNTREJ;
	}

	public int getLSTCID() {
		return LSTCID;
	}

	public void setLSTCID(int lSTCID) {
		LSTCID = lSTCID;
	}

	public float getMCUCUR() {
		return MCUCUR;
	}

	public void setMCUCUR(float mCUCUR) {
		MCUCUR = mCUCUR;
	}

	public long getRSTSTS() {
		return RSTSTS;
	}

	public void setRSTSTS(long rSTSTS) {
		RSTSTS = rSTSTS;
	}

	public int getBOTCNT() {
		return BOTCNT;
	}

	public void setBOTCNT(int bOTCNT) {
		BOTCNT = bOTCNT;
	}

	public long getTIMLOC() {
		return TIMLOC;
	}

	public void setTIMLOC(long tIMLOC) {
		TIMLOC = tIMLOC;
	}

	public byte getTMPINT() {
		return TMPINT;
	}

	public void setTMPINT(byte tMPINT) {
		TMPINT = tMPINT;
	}

}
