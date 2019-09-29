package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmCOM {

	private byte nodeNo; // redundant node number
	private boolean rstEn; // the watchdog application is enabled to reset the node
	private byte botSlt; // currently running internal software slot
	private boolean synPps; // shall the node synchronize with the PPS signal
	private boolean disUTC; // shall the node distribute the UTC time at the next PPS signal
	private boolean dulBsy; // Indicates the state of the UploadManagers Flash Controller

	private float comPaTmp; // temperature underneathe both PAs of a COM side
	private int comRssi0; // RSSI of Transceiver 0
	private int comRssi1; // RSSI of Transceiver 1

	public StdTmCOM(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		nodeNo = (byte) (raw >> 7);
		rstEn = ((raw >> 6) & 0x1) > 0;
		botSlt = (byte) ((raw >> 3) & 0x7);
		synPps = ((raw >> 2) & 0x1) > 0;
		disUTC = ((raw >> 1) & 0x1) > 0;
		dulBsy = (raw & 0x1) > 0;

		comPaTmp = dis.readUnsignedShort() * 0.1f;
		comRssi0 = dis.readUnsignedByte();
		comRssi1 = dis.readUnsignedByte();
	}

	public byte getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(byte nodeNo) {
		this.nodeNo = nodeNo;
	}

	public boolean isRstEn() {
		return rstEn;
	}

	public void setRstEn(boolean rstEn) {
		this.rstEn = rstEn;
	}

	public byte getBotSlt() {
		return botSlt;
	}

	public void setBotSlt(byte botSlt) {
		this.botSlt = botSlt;
	}

	public boolean isSynPps() {
		return synPps;
	}

	public void setSynPps(boolean synPps) {
		this.synPps = synPps;
	}

	public boolean isDisUTC() {
		return disUTC;
	}

	public void setDisUTC(boolean disUTC) {
		this.disUTC = disUTC;
	}

	public boolean isDulBsy() {
		return dulBsy;
	}

	public void setDulBsy(boolean dulBsy) {
		this.dulBsy = dulBsy;
	}

	public float getComPaTmp() {
		return comPaTmp;
	}

	public void setComPaTmp(float comPaTmp) {
		this.comPaTmp = comPaTmp;
	}

	public int getComRssi0() {
		return comRssi0;
	}

	public void setComRssi0(int comRssi0) {
		this.comRssi0 = comRssi0;
	}

	public int getComRssi1() {
		return comRssi1;
	}

	public void setComRssi1(int comRssi1) {
		this.comRssi1 = comRssi1;
	}

}
