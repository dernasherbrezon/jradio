package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid30 {

	private float CPDHMCU; // PDH uC
	private float CPDHCAM; // Camera consumption

	private int PDHCECCM; // Err Ctr CAN bus
	private int PDHCECTC; // Err Ctr invalid TC
	private int PDHCETLII; // Err Ctr loading Image Chunks
	private int PDHCETLC; // Err Ctr loading Image Catalog
	private int PDHCTI; // Ctr incoming Telemetry TC
	private int PDHCTH; // Ctr Telemetry TC
	private int PDHCEISI; // Err Ctr saving images
	private int PDHCEICI; // Err Ctr image capture
	private int PDHCEIDS; // Err Ctr delete slot
	private PdhMode PDHMODE; // Current workmode
	private int PDHCII; // Ctr incoming Image TC
	private int PDHCIH; // Ctr Image TC
	private int PDHCESCM; // Err Ctr CRC messages
	private int PDHCESSM; // Err Ctr saving messages
	private int PDHCESCB; // Err Ctr CRC blocks
	private int PDHCESLB; // Err Ctr loading blocks
	private int PDHCESSS; // Err Ctr saving in scratch pad
	private int PDHCESSB; // Err Ctr saving blocks
	private int PDHCESEB; // Err Ctr erasing blocks
	private int PDHCESCI; // Err Ctr checking image
	private int PDHCSI; // Ctr incoming SW Upload TC
	private int PDHCSH; // Ctr SW Upload TC
	private int PDHBTIMG; // PDH boot slot
	private int PDHSWVERSION; // PDH Software Revision
	private long PDHCSTSYS; // PDH Uptime
	private long PDHCTSTUTC; // PDH Time UTC
	private int GPSCTI; // Ctr incoming GPS TC
	private int GPSCTH; // Ctr GPS TC
	private int GPSECH; // Err Ctr GPS Checksum
	private int GPSECM; // Err Ctr GPS Commands
	private int GPSEWS; // Err Ctr GPS Warm-Start
	private int GPSECS; // Err Ctr GPS Cold-Start
	private int GPSFCM; // Last Failed Command
	private int GPSEAL; // Err Ctr delete Almanac
	private int GPSETL; // Err Ctr delete TLE
	private int GPSESA; // Err Ctr save Almanac
	private int GPSF13; // Err Ctr save F13
	private int GPSSLT; // Current GPS Storage Slot
	private int GPSESD; // Err Ctr save Almanac Header

	public Apid30(DataInputStream dis) throws IOException {
		CPDHMCU = dis.readUnsignedShort() * 0.04029304f;
		CPDHCAM = dis.readUnsignedShort() * 0.201465201f;
		dis.skipBytes(2);
		PDHCECCM = dis.readUnsignedByte();
		PDHCECTC = dis.readUnsignedByte();
		PDHCETLII = dis.readUnsignedByte();
		PDHCETLC = dis.readUnsignedByte();
		PDHCTI = dis.readUnsignedByte();
		PDHCTH = dis.readUnsignedByte();
		PDHCEISI = dis.readUnsignedByte();
		PDHCEICI = dis.readUnsignedByte();
		PDHCEIDS = dis.readUnsignedByte();
		PDHMODE = PdhMode.valueOfCode(dis.readUnsignedByte());
		PDHCII = dis.readUnsignedByte();
		PDHCIH = dis.readUnsignedByte();
		PDHCESCM = dis.readUnsignedByte();
		PDHCESSM = dis.readUnsignedByte();
		PDHCESCB = dis.readUnsignedByte();
		PDHCESLB = dis.readUnsignedByte();
		PDHCESSS = dis.readUnsignedByte();
		PDHCESSB = dis.readUnsignedByte();
		PDHCESEB = dis.readUnsignedByte();
		PDHCESCI = dis.readUnsignedByte();
		PDHCSI = dis.readUnsignedByte();
		PDHCSH = dis.readUnsignedByte();
		PDHBTIMG = dis.readUnsignedByte();
		PDHSWVERSION = dis.readUnsignedShort();
		PDHCSTSYS = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		PDHCTSTUTC = (dis.readUnsignedByte() << 24) | (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | dis.readUnsignedByte();
		GPSCTI = dis.readUnsignedByte();
		GPSCTH = dis.readUnsignedByte();
		GPSECH = dis.readUnsignedByte();
		GPSECM = dis.readUnsignedByte();
		GPSEWS = dis.readUnsignedByte();
		GPSECS = dis.readUnsignedByte();
		GPSFCM = dis.readUnsignedShort();
		GPSEAL = dis.readUnsignedByte();
		GPSETL = dis.readUnsignedByte();
		GPSESA = dis.readUnsignedByte();
		GPSF13 = dis.readUnsignedByte();
		GPSSLT = dis.readUnsignedByte();
		GPSESD = dis.readUnsignedByte();
		dis.skipBytes(73);
	}

	public float getCPDHMCU() {
		return CPDHMCU;
	}

	public void setCPDHMCU(float cPDHMCU) {
		CPDHMCU = cPDHMCU;
	}

	public float getCPDHCAM() {
		return CPDHCAM;
	}

	public void setCPDHCAM(float cPDHCAM) {
		CPDHCAM = cPDHCAM;
	}

	public int getPDHCECCM() {
		return PDHCECCM;
	}

	public void setPDHCECCM(int pDHCECCM) {
		PDHCECCM = pDHCECCM;
	}

	public int getPDHCECTC() {
		return PDHCECTC;
	}

	public void setPDHCECTC(int pDHCECTC) {
		PDHCECTC = pDHCECTC;
	}

	public int getPDHCETLII() {
		return PDHCETLII;
	}

	public void setPDHCETLII(int pDHCETLII) {
		PDHCETLII = pDHCETLII;
	}

	public int getPDHCETLC() {
		return PDHCETLC;
	}

	public void setPDHCETLC(int pDHCETLC) {
		PDHCETLC = pDHCETLC;
	}

	public int getPDHCTI() {
		return PDHCTI;
	}

	public void setPDHCTI(int pDHCTI) {
		PDHCTI = pDHCTI;
	}

	public int getPDHCTH() {
		return PDHCTH;
	}

	public void setPDHCTH(int pDHCTH) {
		PDHCTH = pDHCTH;
	}

	public int getPDHCEISI() {
		return PDHCEISI;
	}

	public void setPDHCEISI(int pDHCEISI) {
		PDHCEISI = pDHCEISI;
	}

	public int getPDHCEICI() {
		return PDHCEICI;
	}

	public void setPDHCEICI(int pDHCEICI) {
		PDHCEICI = pDHCEICI;
	}

	public int getPDHCEIDS() {
		return PDHCEIDS;
	}

	public void setPDHCEIDS(int pDHCEIDS) {
		PDHCEIDS = pDHCEIDS;
	}

	public PdhMode getPDHMODE() {
		return PDHMODE;
	}

	public void setPDHMODE(PdhMode pDHMODE) {
		PDHMODE = pDHMODE;
	}

	public int getPDHCII() {
		return PDHCII;
	}

	public void setPDHCII(int pDHCII) {
		PDHCII = pDHCII;
	}

	public int getPDHCIH() {
		return PDHCIH;
	}

	public void setPDHCIH(int pDHCIH) {
		PDHCIH = pDHCIH;
	}

	public int getPDHCESCM() {
		return PDHCESCM;
	}

	public void setPDHCESCM(int pDHCESCM) {
		PDHCESCM = pDHCESCM;
	}

	public int getPDHCESSM() {
		return PDHCESSM;
	}

	public void setPDHCESSM(int pDHCESSM) {
		PDHCESSM = pDHCESSM;
	}

	public int getPDHCESCB() {
		return PDHCESCB;
	}

	public void setPDHCESCB(int pDHCESCB) {
		PDHCESCB = pDHCESCB;
	}

	public int getPDHCESLB() {
		return PDHCESLB;
	}

	public void setPDHCESLB(int pDHCESLB) {
		PDHCESLB = pDHCESLB;
	}

	public int getPDHCESSS() {
		return PDHCESSS;
	}

	public void setPDHCESSS(int pDHCESSS) {
		PDHCESSS = pDHCESSS;
	}

	public int getPDHCESSB() {
		return PDHCESSB;
	}

	public void setPDHCESSB(int pDHCESSB) {
		PDHCESSB = pDHCESSB;
	}

	public int getPDHCESEB() {
		return PDHCESEB;
	}

	public void setPDHCESEB(int pDHCESEB) {
		PDHCESEB = pDHCESEB;
	}

	public int getPDHCESCI() {
		return PDHCESCI;
	}

	public void setPDHCESCI(int pDHCESCI) {
		PDHCESCI = pDHCESCI;
	}

	public int getPDHCSI() {
		return PDHCSI;
	}

	public void setPDHCSI(int pDHCSI) {
		PDHCSI = pDHCSI;
	}

	public int getPDHCSH() {
		return PDHCSH;
	}

	public void setPDHCSH(int pDHCSH) {
		PDHCSH = pDHCSH;
	}

	public int getPDHBTIMG() {
		return PDHBTIMG;
	}

	public void setPDHBTIMG(int pDHBTIMG) {
		PDHBTIMG = pDHBTIMG;
	}

	public int getPDHSWVERSION() {
		return PDHSWVERSION;
	}

	public void setPDHSWVERSION(int pDHSWVERSION) {
		PDHSWVERSION = pDHSWVERSION;
	}

	public long getPDHCSTSYS() {
		return PDHCSTSYS;
	}

	public void setPDHCSTSYS(long pDHCSTSYS) {
		PDHCSTSYS = pDHCSTSYS;
	}

	public long getPDHCTSTUTC() {
		return PDHCTSTUTC;
	}

	public void setPDHCTSTUTC(long pDHCTSTUTC) {
		PDHCTSTUTC = pDHCTSTUTC;
	}

	public int getGPSCTI() {
		return GPSCTI;
	}

	public void setGPSCTI(int gPSCTI) {
		GPSCTI = gPSCTI;
	}

	public int getGPSCTH() {
		return GPSCTH;
	}

	public void setGPSCTH(int gPSCTH) {
		GPSCTH = gPSCTH;
	}

	public int getGPSECH() {
		return GPSECH;
	}

	public void setGPSECH(int gPSECH) {
		GPSECH = gPSECH;
	}

	public int getGPSECM() {
		return GPSECM;
	}

	public void setGPSECM(int gPSECM) {
		GPSECM = gPSECM;
	}

	public int getGPSEWS() {
		return GPSEWS;
	}

	public void setGPSEWS(int gPSEWS) {
		GPSEWS = gPSEWS;
	}

	public int getGPSECS() {
		return GPSECS;
	}

	public void setGPSECS(int gPSECS) {
		GPSECS = gPSECS;
	}

	public int getGPSFCM() {
		return GPSFCM;
	}

	public void setGPSFCM(int gPSFCM) {
		GPSFCM = gPSFCM;
	}

	public int getGPSEAL() {
		return GPSEAL;
	}

	public void setGPSEAL(int gPSEAL) {
		GPSEAL = gPSEAL;
	}

	public int getGPSETL() {
		return GPSETL;
	}

	public void setGPSETL(int gPSETL) {
		GPSETL = gPSETL;
	}

	public int getGPSESA() {
		return GPSESA;
	}

	public void setGPSESA(int gPSESA) {
		GPSESA = gPSESA;
	}

	public int getGPSF13() {
		return GPSF13;
	}

	public void setGPSF13(int gPSF13) {
		GPSF13 = gPSF13;
	}

	public int getGPSSLT() {
		return GPSSLT;
	}

	public void setGPSSLT(int gPSSLT) {
		GPSSLT = gPSSLT;
	}

	public int getGPSESD() {
		return GPSESD;
	}

	public void setGPSESD(int gPSESD) {
		GPSESD = gPSESD;
	}

}
