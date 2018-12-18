package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class HistoryBufferInfo {

	private HistoryBufferType TMMTFHIST;
	private HistoryBufferType TMMHSHIST;
	private HighSpeedState TMMHSSTAT;

	private int TMMBUSUSD;
	private int TMMPLDUSD;
	private int TMMDIAUSD;
	private int TMMBUSCNT;
	private int TMMPLDCNT;
	private int TMMDIACNT;
	private int TMMBUSCRE;
	private int TMMPLDCRE;
	private int TMMDIACRE;
	private int TMMBUSFLP;
	private int TMMPLDFLP;
	private int TMMDIAFLP;
	private int TMMBUSLSP;
	private int TMMPLDLSP;
	private int TMMDIALSP;
	private int TMMSTDLSP;
	private int TMMRTTLSP;
	private int TMMBUSVOL;
	private int TMMPLDVOL;
	private int TMMDIAVOL;

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
