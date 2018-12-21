package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid31 {

	private int GPSWEEK;             // GPS Week
	private int GPSUTC;              // GPS time to UTC offset
	private double GPSSECW;          // GPS seconds of week
	private double GPSPOSX;          // GPS position X
	private double GPSPOSY;          // GPS position Y
	private double GPSPOSZ;          // GPS position Z
	private double GPSVELX;          // GPS velocity X
	private double GPSVELY;          // GPS velocity Y
	private double GPSVELZ;          // GPS velocity Z
	private float GPSPDOP;           // Precision dilution of position
	private int GPSALMWK;            // GPS almanac week
	private int GPSDOFFS;            // GPS Doppler offset
	private int GPSNAVSTAT;          // GPS navigation status
	private int GPSNSAT;             // GPS number of tracked satellites
	private int GPSBITPH;            // GPS Bit placeholder
	private int GPSOUTF;             // GPS output format
	private int GPSMODE;             // GPS mode
	private int GPSUPDR;             // GPS update rate of navigation
	private int GPSELEVM;            // GPS elevation mask
	private int GPSPDOPM;            // GPS PDOP mask
	private double GPSLTIME;         // GPS launch time

	public Apid31(DataInputStream dis) throws IOException {
		GPSWEEK = dis.readUnsignedShort();
		GPSUTC = dis.readUnsignedByte();
		GPSSECW = dis.readDouble();
		GPSPOSX = dis.readDouble();
		GPSPOSY = dis.readDouble();
		GPSPOSZ = dis.readDouble();
		GPSVELX = dis.readDouble();
		GPSVELY = dis.readDouble();
		GPSVELZ = dis.readDouble();
		GPSPDOP = dis.readFloat();
		GPSALMWK = dis.readUnsignedShort();
		GPSDOFFS = dis.readShort();

		int raw = dis.readUnsignedByte();
		GPSNAVSTAT = (raw >> 6);
		GPSNSAT = (raw & 0b111111);

		raw = dis.readUnsignedByte();
		GPSBITPH = (raw >> 7);
		GPSOUTF = (raw >> 6);
		GPSMODE = ((raw >> 4) & 0b11);
		GPSUPDR = (raw & 0b1111);
		GPSELEVM = dis.readByte();
		GPSPDOPM = dis.readUnsignedByte();
		GPSLTIME = dis.readDouble();
	}

	public int getGPSWEEK() {
		return GPSWEEK;
	}

	public void setGPSWEEK(int gPSWEEK) {
		GPSWEEK = gPSWEEK;
	}

	public int getGPSUTC() {
		return GPSUTC;
	}

	public void setGPSUTC(int gPSUTC) {
		GPSUTC = gPSUTC;
	}

	public double getGPSSECW() {
		return GPSSECW;
	}

	public void setGPSSECW(double gPSSECW) {
		GPSSECW = gPSSECW;
	}

	public double getGPSPOSX() {
		return GPSPOSX;
	}

	public void setGPSPOSX(double gPSPOSX) {
		GPSPOSX = gPSPOSX;
	}

	public double getGPSPOSY() {
		return GPSPOSY;
	}

	public void setGPSPOSY(double gPSPOSY) {
		GPSPOSY = gPSPOSY;
	}

	public double getGPSPOSZ() {
		return GPSPOSZ;
	}

	public void setGPSPOSZ(double gPSPOSZ) {
		GPSPOSZ = gPSPOSZ;
	}

	public double getGPSVELX() {
		return GPSVELX;
	}

	public void setGPSVELX(double gPSVELX) {
		GPSVELX = gPSVELX;
	}

	public double getGPSVELY() {
		return GPSVELY;
	}

	public void setGPSVELY(double gPSVELY) {
		GPSVELY = gPSVELY;
	}

	public double getGPSVELZ() {
		return GPSVELZ;
	}

	public void setGPSVELZ(double gPSVELZ) {
		GPSVELZ = gPSVELZ;
	}

	public float getGPSPDOP() {
		return GPSPDOP;
	}

	public void setGPSPDOP(float gPSPDOP) {
		GPSPDOP = gPSPDOP;
	}

	public int getGPSALMWK() {
		return GPSALMWK;
	}

	public void setGPSALMWK(int gPSALMWK) {
		GPSALMWK = gPSALMWK;
	}

	public int getGPSDOFFS() {
		return GPSDOFFS;
	}

	public void setGPSDOFFS(int gPSDOFFS) {
		GPSDOFFS = gPSDOFFS;
	}

	public int getGPSNAVSTAT() {
		return GPSNAVSTAT;
	}

	public void setGPSNAVSTAT(int gPSNAVSTAT) {
		GPSNAVSTAT = gPSNAVSTAT;
	}

	public int getGPSNSAT() {
		return GPSNSAT;
	}

	public void setGPSNSAT(int gPSNSAT) {
		GPSNSAT = gPSNSAT;
	}

	public int getGPSBITPH() {
		return GPSBITPH;
	}

	public void setGPSBITPH(int gPSBITPH) {
		GPSBITPH = gPSBITPH;
	}

	public int getGPSOUTF() {
		return GPSOUTF;
	}

	public void setGPSOUTF(int gPSOUTF) {
		GPSOUTF = gPSOUTF;
	}

	public int getGPSMODE() {
		return GPSMODE;
	}

	public void setGPSMODE(int gPSMODE) {
		GPSMODE = gPSMODE;
	}

	public int getGPSUPDR() {
		return GPSUPDR;
	}

	public void setGPSUPDR(int gPSUPDR) {
		GPSUPDR = gPSUPDR;
	}

	public int getGPSELEVM() {
		return GPSELEVM;
	}

	public void setGPSELEVM(int gPSELEVM) {
		GPSELEVM = gPSELEVM;
	}

	public int getGPSPDOPM() {
		return GPSPDOPM;
	}

	public void setGPSPDOPM(int gPSPDOPM) {
		GPSPDOPM = gPSPDOPM;
	}

	public double getGPSLTIME() {
		return GPSLTIME;
	}

	public void setGPSLTIME(double gPSLTIME) {
		GPSLTIME = gPSLTIME;
	}

}
