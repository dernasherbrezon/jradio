package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class HistoryBufferInfo {

	private HistoryBufferType tmmTFHIST; // indicates the history buffer used to fill up transfer frames with history telemetry
	private HistoryBufferType tmmHSHIST; // indicates the history buffer used to fill up the high speed downlink
	private HighSpeedState tmmHSSTAT; // indicates the current state of highspeed telemetry transmissions

	private int tmmBUSUSD; // Used flash pages for bus history tm
	private int tmmPLDUSD; // Used flash pages for payload history tm
	private int tmmDIAUSD; // Used flash pages for diagnosis history tm
	private int tmmBUSCNT; // Current count of bus history sourcepackets in memory
	private int tmmPLDCNT; // Current count of payload history sourcepackets in memory
	private int tmmDIACNT; // Current count of bus history sourcepackets in memory
	private int tmmBUSCRE; // CRC error count for bus history sourcepackets
	private int tmmPLDCRE; // CRC error count for payload history sourcepackets
	private int tmmDIACRE; // CRC error count for diagnosis history sourcepackets
	private int tmmBUSFLP; // Fill level of the bus history buffer
	private int tmmPLDFLP; // Fill level of the payload history buffer
	private int tmmDIAFLP; // Fill level of the diagnosis history buffer
	private int tmmBUSLSP; // Current Count of bus history sourcepackets lost due to full fifo
	private int tmmPLDLSP; // Current Count of payload history sourcepackets lost due to full fifo
	private int tmmDIALSP; // Current Count of diagnosis history sourcepackets lost due to full fifo
	private int tmmSTDLSP; // Current Count of standard TM sourcepackets lost due to full fifo
	private int tmmRTTLSP; // Current Count of real time TM sourcepackets lost due to full fifo
	private int tmmBUSVOL; // total bus history data stored in kByte
	private int tmmPLDVOL; // total payload history data stored in kByte
	private int tmmDIAVOL; // total diagnosis history data stored in kByte

	public HistoryBufferInfo(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		tmmTFHIST = HistoryBufferType.valueOfCode(raw >> 6);
		tmmHSHIST = HistoryBufferType.valueOfCode((raw >> 4) & 0b11);
		tmmHSSTAT = HighSpeedState.valueOfCode((raw >> 2) & 0b11);

		tmmBUSUSD = dis.readUnsignedShort();
		tmmPLDUSD = dis.readUnsignedShort();
		tmmDIAUSD = dis.readUnsignedShort();
		tmmBUSCNT = dis.readUnsignedShort();
		tmmPLDCNT = dis.readUnsignedShort();
		tmmDIACNT = dis.readUnsignedShort();
		tmmBUSCRE = dis.readUnsignedShort();
		tmmPLDCRE = dis.readUnsignedShort();
		tmmDIACRE = dis.readUnsignedShort();
		tmmBUSFLP = dis.readUnsignedByte();
		tmmPLDFLP = dis.readUnsignedByte();
		tmmDIAFLP = dis.readUnsignedByte();
		tmmBUSLSP = dis.readUnsignedShort();
		tmmPLDLSP = dis.readUnsignedShort();
		tmmDIALSP = dis.readUnsignedShort();
		tmmSTDLSP = dis.readUnsignedShort();
		tmmRTTLSP = dis.readUnsignedShort();
		tmmBUSVOL = dis.readUnsignedShort();
		tmmPLDVOL = dis.readUnsignedShort();
		tmmDIAVOL = dis.readUnsignedShort();
	}

	public HistoryBufferType getTmmTFHIST() {
		return tmmTFHIST;
	}

	public void setTmmTFHIST(HistoryBufferType tmmTFHIST) {
		this.tmmTFHIST = tmmTFHIST;
	}

	public HistoryBufferType getTmmHSHIST() {
		return tmmHSHIST;
	}

	public void setTmmHSHIST(HistoryBufferType tmmHSHIST) {
		this.tmmHSHIST = tmmHSHIST;
	}

	public HighSpeedState getTmmHSSTAT() {
		return tmmHSSTAT;
	}

	public void setTmmHSSTAT(HighSpeedState tmmHSSTAT) {
		this.tmmHSSTAT = tmmHSSTAT;
	}

	public int getTmmBUSUSD() {
		return tmmBUSUSD;
	}

	public void setTmmBUSUSD(int tmmBUSUSD) {
		this.tmmBUSUSD = tmmBUSUSD;
	}

	public int getTmmPLDUSD() {
		return tmmPLDUSD;
	}

	public void setTmmPLDUSD(int tmmPLDUSD) {
		this.tmmPLDUSD = tmmPLDUSD;
	}

	public int getTmmDIAUSD() {
		return tmmDIAUSD;
	}

	public void setTmmDIAUSD(int tmmDIAUSD) {
		this.tmmDIAUSD = tmmDIAUSD;
	}

	public int getTmmBUSCNT() {
		return tmmBUSCNT;
	}

	public void setTmmBUSCNT(int tmmBUSCNT) {
		this.tmmBUSCNT = tmmBUSCNT;
	}

	public int getTmmPLDCNT() {
		return tmmPLDCNT;
	}

	public void setTmmPLDCNT(int tmmPLDCNT) {
		this.tmmPLDCNT = tmmPLDCNT;
	}

	public int getTmmDIACNT() {
		return tmmDIACNT;
	}

	public void setTmmDIACNT(int tmmDIACNT) {
		this.tmmDIACNT = tmmDIACNT;
	}

	public int getTmmBUSCRE() {
		return tmmBUSCRE;
	}

	public void setTmmBUSCRE(int tmmBUSCRE) {
		this.tmmBUSCRE = tmmBUSCRE;
	}

	public int getTmmPLDCRE() {
		return tmmPLDCRE;
	}

	public void setTmmPLDCRE(int tmmPLDCRE) {
		this.tmmPLDCRE = tmmPLDCRE;
	}

	public int getTmmDIACRE() {
		return tmmDIACRE;
	}

	public void setTmmDIACRE(int tmmDIACRE) {
		this.tmmDIACRE = tmmDIACRE;
	}

	public int getTmmBUSFLP() {
		return tmmBUSFLP;
	}

	public void setTmmBUSFLP(int tmmBUSFLP) {
		this.tmmBUSFLP = tmmBUSFLP;
	}

	public int getTmmPLDFLP() {
		return tmmPLDFLP;
	}

	public void setTmmPLDFLP(int tmmPLDFLP) {
		this.tmmPLDFLP = tmmPLDFLP;
	}

	public int getTmmDIAFLP() {
		return tmmDIAFLP;
	}

	public void setTmmDIAFLP(int tmmDIAFLP) {
		this.tmmDIAFLP = tmmDIAFLP;
	}

	public int getTmmBUSLSP() {
		return tmmBUSLSP;
	}

	public void setTmmBUSLSP(int tmmBUSLSP) {
		this.tmmBUSLSP = tmmBUSLSP;
	}

	public int getTmmPLDLSP() {
		return tmmPLDLSP;
	}

	public void setTmmPLDLSP(int tmmPLDLSP) {
		this.tmmPLDLSP = tmmPLDLSP;
	}

	public int getTmmDIALSP() {
		return tmmDIALSP;
	}

	public void setTmmDIALSP(int tmmDIALSP) {
		this.tmmDIALSP = tmmDIALSP;
	}

	public int getTmmSTDLSP() {
		return tmmSTDLSP;
	}

	public void setTmmSTDLSP(int tmmSTDLSP) {
		this.tmmSTDLSP = tmmSTDLSP;
	}

	public int getTmmRTTLSP() {
		return tmmRTTLSP;
	}

	public void setTmmRTTLSP(int tmmRTTLSP) {
		this.tmmRTTLSP = tmmRTTLSP;
	}

	public int getTmmBUSVOL() {
		return tmmBUSVOL;
	}

	public void setTmmBUSVOL(int tmmBUSVOL) {
		this.tmmBUSVOL = tmmBUSVOL;
	}

	public int getTmmPLDVOL() {
		return tmmPLDVOL;
	}

	public void setTmmPLDVOL(int tmmPLDVOL) {
		this.tmmPLDVOL = tmmPLDVOL;
	}

	public int getTmmDIAVOL() {
		return tmmDIAVOL;
	}

	public void setTmmDIAVOL(int tmmDIAVOL) {
		this.tmmDIAVOL = tmmDIAVOL;
	}

}
