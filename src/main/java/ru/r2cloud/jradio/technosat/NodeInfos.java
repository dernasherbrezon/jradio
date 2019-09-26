package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class NodeInfos {

	private int cntIMM; // counts all immediate telecommands for this node
	private int cntTTC; // counts all timed telecommands for this node
	private int cntINT; // counts all internal commands for this node
	private int cntEXE; // counts all commands executed by applications on this node
	private int cntREJ; // counts all commands rejected (bad TCC or Parameter) by applications on this node
	private int lstCID; // ID of the last immediate/timetagged/internal command that was received by applications on this node (executed and rejected)
	private float mcuCUR; // supply current of the node's microcontroller
	private long rstSTS; // the reset status register contents
	private int botCNT; // counts all soft- and hardware triggered reboots and power-ups
	private long timLOC; // current uptime since last reboot
	private byte tmpINT; // internal temperature of node-MCU

	public NodeInfos(DataInputStream dis) throws IOException {
		cntIMM = dis.readUnsignedByte();
		cntTTC = dis.readUnsignedByte();
		cntINT = dis.readUnsignedByte();
		cntEXE = dis.readUnsignedByte();
		cntREJ = dis.readUnsignedByte();
		lstCID = dis.readUnsignedByte();
		mcuCUR = dis.readUnsignedShort() * 0.1f;
		rstSTS = StreamUtils.readUnsignedInt(dis);
		botCNT = dis.readUnsignedShort();
		timLOC = StreamUtils.readUnsignedInt(dis);
		tmpINT = dis.readByte();
	}

	public int getCntIMM() {
		return cntIMM;
	}

	public void setCntIMM(int cntIMM) {
		this.cntIMM = cntIMM;
	}

	public int getCntTTC() {
		return cntTTC;
	}

	public void setCntTTC(int cntTTC) {
		this.cntTTC = cntTTC;
	}

	public int getCntINT() {
		return cntINT;
	}

	public void setCntINT(int cntINT) {
		this.cntINT = cntINT;
	}

	public int getCntEXE() {
		return cntEXE;
	}

	public void setCntEXE(int cntEXE) {
		this.cntEXE = cntEXE;
	}

	public int getCntREJ() {
		return cntREJ;
	}

	public void setCntREJ(int cntREJ) {
		this.cntREJ = cntREJ;
	}

	public int getLstCID() {
		return lstCID;
	}

	public void setLstCID(int lstCID) {
		this.lstCID = lstCID;
	}

	public float getMcuCUR() {
		return mcuCUR;
	}

	public void setMcuCUR(float mcuCUR) {
		this.mcuCUR = mcuCUR;
	}

	public long getRstSTS() {
		return rstSTS;
	}

	public void setRstSTS(long rstSTS) {
		this.rstSTS = rstSTS;
	}

	public int getBotCNT() {
		return botCNT;
	}

	public void setBotCNT(int botCNT) {
		this.botCNT = botCNT;
	}

	public long getTimLOC() {
		return timLOC;
	}

	public void setTimLOC(long timLOC) {
		this.timLOC = timLOC;
	}

	public byte getTmpINT() {
		return tmpINT;
	}

	public void setTmpINT(byte tmpINT) {
		this.tmpINT = tmpINT;
	}

}
