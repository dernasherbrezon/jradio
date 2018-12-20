package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Apid4 {

	private int TMMRRP; // TM Memory Read Pointer
	private int TMMRWP; // TM Memory Write Pointer
	private long OBCSWV; // OBC Software Revision
	private int APB4GT; // B4 Apid Gathering rate
	private int APACGT; // ACS Apid Gathering rate
	private int APEPGT; // EPS TCS OBC Apid Gathering rate
	private int APAEGT; // ACS EXT Apid Gathering rate
	private int APDEGT; // Debug Apid Gathering rate
	private int APPDGT; // PDH Apid Gathering rate
	private int APGPGT; // GPS Apid Gathering rate
	private int APICGT; // Image Catalog Apid Gathering rate
	private int APCTGT; // PDH GPS Catalog Apid Gathering rate
	private long CSTUTC; // Onboard time UTC
	private long CSTSYS; // OBDH uptime
	private long PCSYST; // PCU uptime
	private int CCTCCP; // Ctr Change Param
	private int CCSUIN; // Ctr incoming SW Upload TC
	private int CCSUHD; // Ctr handled SW Upload TC
	private int CCESUC; // Err Ctr Check Image
	private int CCESUL; // Err Ctr Load Block
	private int CCESUE; // Err Ctr Erase Block
	private int CCESUK; // Err Ctr Erase Sector
	private int CCESUM; // Err Ctr CRC Upload Message
	private int CCESUS; // Err Ctr Save Upload Message
	private int APACCGT; // ACS Calibration APID Gathering rate

	public Apid4(DataInputStream dis) throws IOException {
		BitInputStream bis = new BitInputStream(dis);

		TMMRRP = bis.readUnsignedInt(16);
		TMMRWP = bis.readUnsignedInt(16);
		OBCSWV = bis.readUnsignedLong(32);
		APB4GT = bis.readUnsignedInt(16);
		APACGT = bis.readUnsignedInt(16);
		APEPGT = bis.readUnsignedInt(16);
		APAEGT = bis.readUnsignedInt(16);
		APDEGT = bis.readUnsignedInt(16);
		APPDGT = bis.readUnsignedInt(16);
		APGPGT = bis.readUnsignedInt(16);
		APICGT = bis.readUnsignedInt(16);
		APCTGT = bis.readUnsignedInt(16);
		CSTUTC = bis.readUnsignedLong(32);
		CSTSYS = bis.readUnsignedLong(32);
		PCSYST = bis.readUnsignedLong(32);
		CCTCCP = bis.readUnsignedInt(8);
		CCSUIN = bis.readUnsignedInt(12);
		CCSUHD = bis.readUnsignedInt(12);
		CCESUC = bis.readUnsignedInt(12);
		CCESUL = bis.readUnsignedInt(12);
		CCESUE = bis.readUnsignedInt(12);
		CCESUK = bis.readUnsignedInt(12);
		CCESUM = bis.readUnsignedInt(12);
		CCESUS = bis.readUnsignedInt(12);
		APACGT = bis.readUnsignedInt(16);
		dis.skipBytes(73);
	}

	public int getTMMRRP() {
		return TMMRRP;
	}

	public void setTMMRRP(int tMMRRP) {
		TMMRRP = tMMRRP;
	}

	public int getTMMRWP() {
		return TMMRWP;
	}

	public void setTMMRWP(int tMMRWP) {
		TMMRWP = tMMRWP;
	}

	public long getOBCSWV() {
		return OBCSWV;
	}

	public void setOBCSWV(long oBCSWV) {
		OBCSWV = oBCSWV;
	}

	public int getAPB4GT() {
		return APB4GT;
	}

	public void setAPB4GT(int aPB4GT) {
		APB4GT = aPB4GT;
	}

	public int getAPACGT() {
		return APACGT;
	}

	public void setAPACGT(int aPACGT) {
		APACGT = aPACGT;
	}

	public int getAPEPGT() {
		return APEPGT;
	}

	public void setAPEPGT(int aPEPGT) {
		APEPGT = aPEPGT;
	}

	public int getAPAEGT() {
		return APAEGT;
	}

	public void setAPAEGT(int aPAEGT) {
		APAEGT = aPAEGT;
	}

	public int getAPDEGT() {
		return APDEGT;
	}

	public void setAPDEGT(int aPDEGT) {
		APDEGT = aPDEGT;
	}

	public int getAPPDGT() {
		return APPDGT;
	}

	public void setAPPDGT(int aPPDGT) {
		APPDGT = aPPDGT;
	}

	public int getAPGPGT() {
		return APGPGT;
	}

	public void setAPGPGT(int aPGPGT) {
		APGPGT = aPGPGT;
	}

	public int getAPICGT() {
		return APICGT;
	}

	public void setAPICGT(int aPICGT) {
		APICGT = aPICGT;
	}

	public int getAPCTGT() {
		return APCTGT;
	}

	public void setAPCTGT(int aPCTGT) {
		APCTGT = aPCTGT;
	}

	public long getCSTUTC() {
		return CSTUTC;
	}

	public void setCSTUTC(long cSTUTC) {
		CSTUTC = cSTUTC;
	}

	public long getCSTSYS() {
		return CSTSYS;
	}

	public void setCSTSYS(long cSTSYS) {
		CSTSYS = cSTSYS;
	}

	public long getPCSYST() {
		return PCSYST;
	}

	public void setPCSYST(long pCSYST) {
		PCSYST = pCSYST;
	}

	public int getCCTCCP() {
		return CCTCCP;
	}

	public void setCCTCCP(int cCTCCP) {
		CCTCCP = cCTCCP;
	}

	public int getCCSUIN() {
		return CCSUIN;
	}

	public void setCCSUIN(int cCSUIN) {
		CCSUIN = cCSUIN;
	}

	public int getCCSUHD() {
		return CCSUHD;
	}

	public void setCCSUHD(int cCSUHD) {
		CCSUHD = cCSUHD;
	}

	public int getCCESUC() {
		return CCESUC;
	}

	public void setCCESUC(int cCESUC) {
		CCESUC = cCESUC;
	}

	public int getCCESUL() {
		return CCESUL;
	}

	public void setCCESUL(int cCESUL) {
		CCESUL = cCESUL;
	}

	public int getCCESUE() {
		return CCESUE;
	}

	public void setCCESUE(int cCESUE) {
		CCESUE = cCESUE;
	}

	public int getCCESUK() {
		return CCESUK;
	}

	public void setCCESUK(int cCESUK) {
		CCESUK = cCESUK;
	}

	public int getCCESUM() {
		return CCESUM;
	}

	public void setCCESUM(int cCESUM) {
		CCESUM = cCESUM;
	}

	public int getCCESUS() {
		return CCESUS;
	}

	public void setCCESUS(int cCESUS) {
		CCESUS = cCESUS;
	}

	public int getAPACCGT() {
		return APACCGT;
	}

	public void setAPACCGT(int aPACCGT) {
		APACCGT = aPACCGT;
	}

}
