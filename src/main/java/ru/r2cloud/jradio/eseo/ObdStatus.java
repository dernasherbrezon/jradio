package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class ObdStatus {

	private boolean INITASM;
	private boolean INITCOPYSECTION;
	private boolean INITCLEARBSS;
	private boolean INITCLOCK;
	private boolean INITCPU;
	private boolean INITFLASHRW;
	private boolean INITGPIO;
	private boolean INITINTERRUPT;
	private boolean INITINTERRUPTTEST;
	private boolean INITUSART;
	private boolean INITCPUTIMER;
	private boolean INITRTC;
	private boolean INITWDG;
	private boolean INITFAULTHAND;
	private boolean INITFLASH;
	private boolean INITSPIBUS;
	private boolean INITCANCONF;
	private boolean INITCANTEST;
	private boolean INITADC;
	private boolean INITTIMER;
	private boolean INITCONSOLE;
	private boolean INITPOWONRESET;
	private boolean INITWDRESET;
	private boolean INITASWIMAGE;
	private boolean INITCOMPLETED;
	private boolean INITSTANDBYACTIVE;
	private boolean INITTAU;
	private boolean INITPDUADC;
	private boolean INITCANOPEN;

	public ObdStatus(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		INITASM = ((raw >> 7) & 0x1) > 0;
		INITCOPYSECTION = ((raw >> 6) & 0x1) > 0;
		INITCLEARBSS = ((raw >> 5) & 0x1) > 0;
		INITCLOCK = ((raw >> 4) & 0x1) > 0;
		INITCPU = ((raw >> 3) & 0x1) > 0;
		INITFLASHRW = ((raw >> 2) & 0x1) > 0;
		INITGPIO = ((raw >> 1) & 0x1) > 0;
		INITINTERRUPT = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		INITINTERRUPTTEST = ((raw >> 7) & 0x1) > 0;
		INITUSART = ((raw >> 6) & 0x1) > 0;
		INITCPUTIMER = ((raw >> 5) & 0x1) > 0;
		INITRTC = ((raw >> 4) & 0x1) > 0;
		INITWDG = ((raw >> 3) & 0x1) > 0;
		INITFAULTHAND = ((raw >> 2) & 0x1) > 0;
		INITFLASH = ((raw >> 1) & 0x1) > 0;
		INITSPIBUS = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		INITCANCONF = ((raw >> 7) & 0x1) > 0;
		INITCANTEST = ((raw >> 6) & 0x1) > 0;
		INITADC = ((raw >> 5) & 0x1) > 0;
		INITTIMER = ((raw >> 4) & 0x1) > 0;
		INITCONSOLE = ((raw >> 3) & 0x1) > 0;
		INITPOWONRESET = ((raw >> 2) & 0x1) > 0;
		INITWDRESET = ((raw >> 1) & 0x1) > 0;
		INITASWIMAGE = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		INITCOMPLETED = ((raw >> 7) & 0x1) > 0;
		INITSTANDBYACTIVE = ((raw >> 6) & 0x1) > 0;
		INITTAU = ((raw >> 5) & 0x1) > 0;
		INITPDUADC = ((raw >> 4) & 0x1) > 0;
		INITCANOPEN = ((raw >> 3) & 0x1) > 0;
	}

	public boolean isINITASM() {
		return INITASM;
	}

	public void setINITASM(boolean iNITASM) {
		INITASM = iNITASM;
	}

	public boolean isINITCOPYSECTION() {
		return INITCOPYSECTION;
	}

	public void setINITCOPYSECTION(boolean iNITCOPYSECTION) {
		INITCOPYSECTION = iNITCOPYSECTION;
	}

	public boolean isINITCLEARBSS() {
		return INITCLEARBSS;
	}

	public void setINITCLEARBSS(boolean iNITCLEARBSS) {
		INITCLEARBSS = iNITCLEARBSS;
	}

	public boolean isINITCLOCK() {
		return INITCLOCK;
	}

	public void setINITCLOCK(boolean iNITCLOCK) {
		INITCLOCK = iNITCLOCK;
	}

	public boolean isINITCPU() {
		return INITCPU;
	}

	public void setINITCPU(boolean iNITCPU) {
		INITCPU = iNITCPU;
	}

	public boolean isINITFLASHRW() {
		return INITFLASHRW;
	}

	public void setINITFLASHRW(boolean iNITFLASHRW) {
		INITFLASHRW = iNITFLASHRW;
	}

	public boolean isINITGPIO() {
		return INITGPIO;
	}

	public void setINITGPIO(boolean iNITGPIO) {
		INITGPIO = iNITGPIO;
	}

	public boolean isINITINTERRUPT() {
		return INITINTERRUPT;
	}

	public void setINITINTERRUPT(boolean iNITINTERRUPT) {
		INITINTERRUPT = iNITINTERRUPT;
	}

	public boolean isINITINTERRUPTTEST() {
		return INITINTERRUPTTEST;
	}

	public void setINITINTERRUPTTEST(boolean iNITINTERRUPTTEST) {
		INITINTERRUPTTEST = iNITINTERRUPTTEST;
	}

	public boolean isINITUSART() {
		return INITUSART;
	}

	public void setINITUSART(boolean iNITUSART) {
		INITUSART = iNITUSART;
	}

	public boolean isINITCPUTIMER() {
		return INITCPUTIMER;
	}

	public void setINITCPUTIMER(boolean iNITCPUTIMER) {
		INITCPUTIMER = iNITCPUTIMER;
	}

	public boolean isINITRTC() {
		return INITRTC;
	}

	public void setINITRTC(boolean iNITRTC) {
		INITRTC = iNITRTC;
	}

	public boolean isINITWDG() {
		return INITWDG;
	}

	public void setINITWDG(boolean iNITWDG) {
		INITWDG = iNITWDG;
	}

	public boolean isINITFAULTHAND() {
		return INITFAULTHAND;
	}

	public void setINITFAULTHAND(boolean iNITFAULTHAND) {
		INITFAULTHAND = iNITFAULTHAND;
	}

	public boolean isINITFLASH() {
		return INITFLASH;
	}

	public void setINITFLASH(boolean iNITFLASH) {
		INITFLASH = iNITFLASH;
	}

	public boolean isINITSPIBUS() {
		return INITSPIBUS;
	}

	public void setINITSPIBUS(boolean iNITSPIBUS) {
		INITSPIBUS = iNITSPIBUS;
	}

	public boolean isINITCANCONF() {
		return INITCANCONF;
	}

	public void setINITCANCONF(boolean iNITCANCONF) {
		INITCANCONF = iNITCANCONF;
	}

	public boolean isINITCANTEST() {
		return INITCANTEST;
	}

	public void setINITCANTEST(boolean iNITCANTEST) {
		INITCANTEST = iNITCANTEST;
	}

	public boolean isINITADC() {
		return INITADC;
	}

	public void setINITADC(boolean iNITADC) {
		INITADC = iNITADC;
	}

	public boolean isINITTIMER() {
		return INITTIMER;
	}

	public void setINITTIMER(boolean iNITTIMER) {
		INITTIMER = iNITTIMER;
	}

	public boolean isINITCONSOLE() {
		return INITCONSOLE;
	}

	public void setINITCONSOLE(boolean iNITCONSOLE) {
		INITCONSOLE = iNITCONSOLE;
	}

	public boolean isINITPOWONRESET() {
		return INITPOWONRESET;
	}

	public void setINITPOWONRESET(boolean iNITPOWONRESET) {
		INITPOWONRESET = iNITPOWONRESET;
	}

	public boolean isINITWDRESET() {
		return INITWDRESET;
	}

	public void setINITWDRESET(boolean iNITWDRESET) {
		INITWDRESET = iNITWDRESET;
	}

	public boolean isINITASWIMAGE() {
		return INITASWIMAGE;
	}

	public void setINITASWIMAGE(boolean iNITASWIMAGE) {
		INITASWIMAGE = iNITASWIMAGE;
	}

	public boolean isINITCOMPLETED() {
		return INITCOMPLETED;
	}

	public void setINITCOMPLETED(boolean iNITCOMPLETED) {
		INITCOMPLETED = iNITCOMPLETED;
	}

	public boolean isINITSTANDBYACTIVE() {
		return INITSTANDBYACTIVE;
	}

	public void setINITSTANDBYACTIVE(boolean iNITSTANDBYACTIVE) {
		INITSTANDBYACTIVE = iNITSTANDBYACTIVE;
	}

	public boolean isINITTAU() {
		return INITTAU;
	}

	public void setINITTAU(boolean iNITTAU) {
		INITTAU = iNITTAU;
	}

	public boolean isINITPDUADC() {
		return INITPDUADC;
	}

	public void setINITPDUADC(boolean iNITPDUADC) {
		INITPDUADC = iNITPDUADC;
	}

	public boolean isINITCANOPEN() {
		return INITCANOPEN;
	}

	public void setINITCANOPEN(boolean iNITCANOPEN) {
		INITCANOPEN = iNITCANOPEN;
	}

}
