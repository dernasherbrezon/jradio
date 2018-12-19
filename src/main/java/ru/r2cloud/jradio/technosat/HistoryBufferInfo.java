package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class HistoryBufferInfo {

	private HistoryBufferType TMMTFHIST;       // indicates the history buffer used to fill up transfer frames with history telemetry
	private HistoryBufferType TMMHSHIST;       // indicates the history buffer used to fill up the high speed downlink
	private HighSpeedState TMMHSSTAT;          // indicates the current state of highspeed telemetry transmissions

	private int TMMBUSUSD;            //  Used flash pages for bus history tm
	private int TMMPLDUSD;            //  Used flash pages for payload history tm
	private int TMMDIAUSD;            //  Used flash pages for diagnosis history tm
	private int TMMBUSCNT;            //  Current count of bus history sourcepackets in memory
	private int TMMPLDCNT;            //  Current count of payload history sourcepackets in memory
	private int TMMDIACNT;            //  Current count of bus history sourcepackets in memory
	private int TMMBUSCRE;            //  CRC error count for bus history sourcepackets
	private int TMMPLDCRE;            //  CRC error count for payload history sourcepackets
	private int TMMDIACRE;            //  CRC error count for diagnosis history sourcepackets
	private int TMMBUSFLP;            //  Fill level of the bus history buffer
	private int TMMPLDFLP;            //  Fill level of the payload history buffer
	private int TMMDIAFLP;            //  Fill level of the diagnosis history buffer
	private int TMMBUSLSP;            //  Current Count of bus history sourcepackets lost due to full fifo
	private int TMMPLDLSP;            //  Current Count of payload history sourcepackets lost due to full fifo
	private int TMMDIALSP;            //  Current Count of diagnosis history sourcepackets lost due to full fifo
	private int TMMSTDLSP;            //  Current Count of standard TM sourcepackets lost due to full fifo
	private int TMMRTTLSP;            //  Current Count of real time TM sourcepackets lost due to full fifo
	private int TMMBUSVOL;            //  total bus history data stored in kByte
	private int TMMPLDVOL;            //  total payload history data stored in kByte
	private int TMMDIAVOL;            //  total diagnosis history data stored in kByte

	public HistoryBufferInfo(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		TMMTFHIST = HistoryBufferType.valueOfCode(raw >> 6);
		TMMHSHIST = HistoryBufferType.valueOfCode((raw >> 4) & 0b11);
		TMMHSSTAT = HighSpeedState.valueOfCode((raw >> 2) & 0b11);

		TMMBUSUSD = dis.readUnsignedShort();
		TMMPLDUSD = dis.readUnsignedShort();
		TMMDIAUSD = dis.readUnsignedShort();
		TMMBUSCNT = dis.readUnsignedShort();
		TMMPLDCNT = dis.readUnsignedShort();
		TMMDIACNT = dis.readUnsignedShort();
		TMMBUSCRE = dis.readUnsignedShort();
		TMMPLDCRE = dis.readUnsignedShort();
		TMMDIACRE = dis.readUnsignedShort();
		TMMBUSFLP = dis.readUnsignedByte();
		TMMPLDFLP = dis.readUnsignedByte();
		TMMDIAFLP = dis.readUnsignedByte();
		TMMBUSLSP = dis.readUnsignedShort();
		TMMPLDLSP = dis.readUnsignedShort();
		TMMDIALSP = dis.readUnsignedShort();
		TMMSTDLSP = dis.readUnsignedShort();
		TMMRTTLSP = dis.readUnsignedShort();
		TMMBUSVOL = dis.readUnsignedShort();
		TMMPLDVOL = dis.readUnsignedShort();
		TMMDIAVOL = dis.readUnsignedShort();
	}

	public HistoryBufferType getTMMTFHIST() {
		return TMMTFHIST;
	}

	public void setTMMTFHIST(HistoryBufferType tMMTFHIST) {
		TMMTFHIST = tMMTFHIST;
	}

	public HistoryBufferType getTMMHSHIST() {
		return TMMHSHIST;
	}

	public void setTMMHSHIST(HistoryBufferType tMMHSHIST) {
		TMMHSHIST = tMMHSHIST;
	}

	public HighSpeedState getTMMHSSTAT() {
		return TMMHSSTAT;
	}

	public void setTMMHSSTAT(HighSpeedState tMMHSSTAT) {
		TMMHSSTAT = tMMHSSTAT;
	}

	public int getTMMBUSUSD() {
		return TMMBUSUSD;
	}

	public void setTMMBUSUSD(int tMMBUSUSD) {
		TMMBUSUSD = tMMBUSUSD;
	}

	public int getTMMPLDUSD() {
		return TMMPLDUSD;
	}

	public void setTMMPLDUSD(int tMMPLDUSD) {
		TMMPLDUSD = tMMPLDUSD;
	}

	public int getTMMDIAUSD() {
		return TMMDIAUSD;
	}

	public void setTMMDIAUSD(int tMMDIAUSD) {
		TMMDIAUSD = tMMDIAUSD;
	}

	public int getTMMBUSCNT() {
		return TMMBUSCNT;
	}

	public void setTMMBUSCNT(int tMMBUSCNT) {
		TMMBUSCNT = tMMBUSCNT;
	}

	public int getTMMPLDCNT() {
		return TMMPLDCNT;
	}

	public void setTMMPLDCNT(int tMMPLDCNT) {
		TMMPLDCNT = tMMPLDCNT;
	}

	public int getTMMDIACNT() {
		return TMMDIACNT;
	}

	public void setTMMDIACNT(int tMMDIACNT) {
		TMMDIACNT = tMMDIACNT;
	}

	public int getTMMBUSCRE() {
		return TMMBUSCRE;
	}

	public void setTMMBUSCRE(int tMMBUSCRE) {
		TMMBUSCRE = tMMBUSCRE;
	}

	public int getTMMPLDCRE() {
		return TMMPLDCRE;
	}

	public void setTMMPLDCRE(int tMMPLDCRE) {
		TMMPLDCRE = tMMPLDCRE;
	}

	public int getTMMDIACRE() {
		return TMMDIACRE;
	}

	public void setTMMDIACRE(int tMMDIACRE) {
		TMMDIACRE = tMMDIACRE;
	}

	public int getTMMBUSFLP() {
		return TMMBUSFLP;
	}

	public void setTMMBUSFLP(int tMMBUSFLP) {
		TMMBUSFLP = tMMBUSFLP;
	}

	public int getTMMPLDFLP() {
		return TMMPLDFLP;
	}

	public void setTMMPLDFLP(int tMMPLDFLP) {
		TMMPLDFLP = tMMPLDFLP;
	}

	public int getTMMDIAFLP() {
		return TMMDIAFLP;
	}

	public void setTMMDIAFLP(int tMMDIAFLP) {
		TMMDIAFLP = tMMDIAFLP;
	}

	public int getTMMBUSLSP() {
		return TMMBUSLSP;
	}

	public void setTMMBUSLSP(int tMMBUSLSP) {
		TMMBUSLSP = tMMBUSLSP;
	}

	public int getTMMPLDLSP() {
		return TMMPLDLSP;
	}

	public void setTMMPLDLSP(int tMMPLDLSP) {
		TMMPLDLSP = tMMPLDLSP;
	}

	public int getTMMDIALSP() {
		return TMMDIALSP;
	}

	public void setTMMDIALSP(int tMMDIALSP) {
		TMMDIALSP = tMMDIALSP;
	}

	public int getTMMSTDLSP() {
		return TMMSTDLSP;
	}

	public void setTMMSTDLSP(int tMMSTDLSP) {
		TMMSTDLSP = tMMSTDLSP;
	}

	public int getTMMRTTLSP() {
		return TMMRTTLSP;
	}

	public void setTMMRTTLSP(int tMMRTTLSP) {
		TMMRTTLSP = tMMRTTLSP;
	}

	public int getTMMBUSVOL() {
		return TMMBUSVOL;
	}

	public void setTMMBUSVOL(int tMMBUSVOL) {
		TMMBUSVOL = tMMBUSVOL;
	}

	public int getTMMPLDVOL() {
		return TMMPLDVOL;
	}

	public void setTMMPLDVOL(int tMMPLDVOL) {
		TMMPLDVOL = tMMPLDVOL;
	}

	public int getTMMDIAVOL() {
		return TMMDIAVOL;
	}

	public void setTMMDIAVOL(int tMMDIAVOL) {
		TMMDIAVOL = tMMDIAVOL;
	}

}
