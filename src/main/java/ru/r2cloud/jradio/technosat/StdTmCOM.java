package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmCOM {

	private byte NODENO;           // redundant node number
	private boolean RST_EN;        // the watchdog application is enabled to reset the node
	private byte BOTSLT;           // currently running internal software slot
	private boolean SYNPPS;        // shall the node synchronize with the PPS signal
	private boolean DISUTC;        // shall the node distribute the UTC time at the next PPS signal
	private boolean DULBSY;        // Indicates the state of the UploadManagers Flash Controller

	private float COMPATMP;        // temperature underneathe both PAs of a COM side
	private int COMRSSI0;          // RSSI of Transceiver 0
	private int COMRSSI1;          // RSSI of Transceiver 1

	public StdTmCOM(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		NODENO = (byte) (raw >> 7);
		RST_EN = ((raw >> 6) & 0x1) > 0;
		BOTSLT = (byte) ((raw >> 3) & 0x7);
		SYNPPS = ((raw >> 2) & 0x1) > 0;
		DISUTC = ((raw >> 1) & 0x1) > 0;
		DULBSY = ((raw >> 0) & 0x1) > 0;

		COMPATMP = dis.readUnsignedShort() * 0.1f;
		COMRSSI0 = dis.readUnsignedByte();
		COMRSSI1 = dis.readUnsignedByte();
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

	public float getCOMPATMP() {
		return COMPATMP;
	}

	public void setCOMPATMP(float cOMPATMP) {
		COMPATMP = cOMPATMP;
	}

	public int getCOMRSSI0() {
		return COMRSSI0;
	}

	public void setCOMRSSI0(int cOMRSSI0) {
		COMRSSI0 = cOMRSSI0;
	}

	public int getCOMRSSI1() {
		return COMRSSI1;
	}

	public void setCOMRSSI1(int cOMRSSI1) {
		COMRSSI1 = cOMRSSI1;
	}

}
