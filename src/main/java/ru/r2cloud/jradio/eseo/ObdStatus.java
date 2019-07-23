package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ObdStatus {

	private boolean initASM;
	private boolean initCOPYSECTION;
	private boolean initCLEARBSS;
	private boolean initCLOCK;
	private boolean initCPU;
	private boolean initFLASHRW;
	private boolean initGPIO;
	private boolean initINTERRUPT;
	private boolean initINTERRUPTTEST;
	private boolean initUSART;
	private boolean initCPUTIMER;
	private boolean initRTC;
	private boolean initWDG;
	private boolean initFAULTHAND;
	private boolean initFLASH;
	private boolean initSPIBUS;
	private boolean initCANCONF;
	private boolean initCANTEST;
	private boolean initADC;
	private boolean initTIMER;
	private boolean initCONSOLE;
	private boolean initPOWONRESET;
	private boolean initWDRESET;
	private boolean initASWIMAGE;
	private boolean initCOMPLETED;
	private boolean initSTANDBYACTIVE;
	private boolean initTAU;
	private boolean initPDUADC;
	private boolean initCANOPEN;

	public ObdStatus(LittleEndianDataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		initASM = ((raw >> 7) & 0x1) > 0;
		initCOPYSECTION = ((raw >> 6) & 0x1) > 0;
		initCLEARBSS = ((raw >> 5) & 0x1) > 0;
		initCLOCK = ((raw >> 4) & 0x1) > 0;
		initCPU = ((raw >> 3) & 0x1) > 0;
		initFLASHRW = ((raw >> 2) & 0x1) > 0;
		initGPIO = ((raw >> 1) & 0x1) > 0;
		initINTERRUPT = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		initINTERRUPTTEST = ((raw >> 7) & 0x1) > 0;
		initUSART = ((raw >> 6) & 0x1) > 0;
		initCPUTIMER = ((raw >> 5) & 0x1) > 0;
		initRTC = ((raw >> 4) & 0x1) > 0;
		initWDG = ((raw >> 3) & 0x1) > 0;
		initFAULTHAND = ((raw >> 2) & 0x1) > 0;
		initFLASH = ((raw >> 1) & 0x1) > 0;
		initSPIBUS = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		initCANCONF = ((raw >> 7) & 0x1) > 0;
		initCANTEST = ((raw >> 6) & 0x1) > 0;
		initADC = ((raw >> 5) & 0x1) > 0;
		initTIMER = ((raw >> 4) & 0x1) > 0;
		initCONSOLE = ((raw >> 3) & 0x1) > 0;
		initPOWONRESET = ((raw >> 2) & 0x1) > 0;
		initWDRESET = ((raw >> 1) & 0x1) > 0;
		initASWIMAGE = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		initCOMPLETED = ((raw >> 7) & 0x1) > 0;
		initSTANDBYACTIVE = ((raw >> 6) & 0x1) > 0;
		initTAU = ((raw >> 5) & 0x1) > 0;
		initPDUADC = ((raw >> 4) & 0x1) > 0;
		initCANOPEN = ((raw >> 3) & 0x1) > 0;
	}

	public boolean isInitASM() {
		return initASM;
	}

	public void setInitASM(boolean initASM) {
		this.initASM = initASM;
	}

	public boolean isInitCOPYSECTION() {
		return initCOPYSECTION;
	}

	public void setInitCOPYSECTION(boolean initCOPYSECTION) {
		this.initCOPYSECTION = initCOPYSECTION;
	}

	public boolean isInitCLEARBSS() {
		return initCLEARBSS;
	}

	public void setInitCLEARBSS(boolean initCLEARBSS) {
		this.initCLEARBSS = initCLEARBSS;
	}

	public boolean isInitCLOCK() {
		return initCLOCK;
	}

	public void setInitCLOCK(boolean initCLOCK) {
		this.initCLOCK = initCLOCK;
	}

	public boolean isInitCPU() {
		return initCPU;
	}

	public void setInitCPU(boolean initCPU) {
		this.initCPU = initCPU;
	}

	public boolean isInitFLASHRW() {
		return initFLASHRW;
	}

	public void setInitFLASHRW(boolean initFLASHRW) {
		this.initFLASHRW = initFLASHRW;
	}

	public boolean isInitGPIO() {
		return initGPIO;
	}

	public void setInitGPIO(boolean initGPIO) {
		this.initGPIO = initGPIO;
	}

	public boolean isInitINTERRUPT() {
		return initINTERRUPT;
	}

	public void setInitINTERRUPT(boolean initINTERRUPT) {
		this.initINTERRUPT = initINTERRUPT;
	}

	public boolean isInitINTERRUPTTEST() {
		return initINTERRUPTTEST;
	}

	public void setInitINTERRUPTTEST(boolean initINTERRUPTTEST) {
		this.initINTERRUPTTEST = initINTERRUPTTEST;
	}

	public boolean isInitUSART() {
		return initUSART;
	}

	public void setInitUSART(boolean initUSART) {
		this.initUSART = initUSART;
	}

	public boolean isInitCPUTIMER() {
		return initCPUTIMER;
	}

	public void setInitCPUTIMER(boolean initCPUTIMER) {
		this.initCPUTIMER = initCPUTIMER;
	}

	public boolean isInitRTC() {
		return initRTC;
	}

	public void setInitRTC(boolean initRTC) {
		this.initRTC = initRTC;
	}

	public boolean isInitWDG() {
		return initWDG;
	}

	public void setInitWDG(boolean initWDG) {
		this.initWDG = initWDG;
	}

	public boolean isInitFAULTHAND() {
		return initFAULTHAND;
	}

	public void setInitFAULTHAND(boolean initFAULTHAND) {
		this.initFAULTHAND = initFAULTHAND;
	}

	public boolean isInitFLASH() {
		return initFLASH;
	}

	public void setInitFLASH(boolean initFLASH) {
		this.initFLASH = initFLASH;
	}

	public boolean isInitSPIBUS() {
		return initSPIBUS;
	}

	public void setInitSPIBUS(boolean initSPIBUS) {
		this.initSPIBUS = initSPIBUS;
	}

	public boolean isInitCANCONF() {
		return initCANCONF;
	}

	public void setInitCANCONF(boolean initCANCONF) {
		this.initCANCONF = initCANCONF;
	}

	public boolean isInitCANTEST() {
		return initCANTEST;
	}

	public void setInitCANTEST(boolean initCANTEST) {
		this.initCANTEST = initCANTEST;
	}

	public boolean isInitADC() {
		return initADC;
	}

	public void setInitADC(boolean initADC) {
		this.initADC = initADC;
	}

	public boolean isInitTIMER() {
		return initTIMER;
	}

	public void setInitTIMER(boolean initTIMER) {
		this.initTIMER = initTIMER;
	}

	public boolean isInitCONSOLE() {
		return initCONSOLE;
	}

	public void setInitCONSOLE(boolean initCONSOLE) {
		this.initCONSOLE = initCONSOLE;
	}

	public boolean isInitPOWONRESET() {
		return initPOWONRESET;
	}

	public void setInitPOWONRESET(boolean initPOWONRESET) {
		this.initPOWONRESET = initPOWONRESET;
	}

	public boolean isInitWDRESET() {
		return initWDRESET;
	}

	public void setInitWDRESET(boolean initWDRESET) {
		this.initWDRESET = initWDRESET;
	}

	public boolean isInitASWIMAGE() {
		return initASWIMAGE;
	}

	public void setInitASWIMAGE(boolean initASWIMAGE) {
		this.initASWIMAGE = initASWIMAGE;
	}

	public boolean isInitCOMPLETED() {
		return initCOMPLETED;
	}

	public void setInitCOMPLETED(boolean initCOMPLETED) {
		this.initCOMPLETED = initCOMPLETED;
	}

	public boolean isInitSTANDBYACTIVE() {
		return initSTANDBYACTIVE;
	}

	public void setInitSTANDBYACTIVE(boolean initSTANDBYACTIVE) {
		this.initSTANDBYACTIVE = initSTANDBYACTIVE;
	}

	public boolean isInitTAU() {
		return initTAU;
	}

	public void setInitTAU(boolean initTAU) {
		this.initTAU = initTAU;
	}

	public boolean isInitPDUADC() {
		return initPDUADC;
	}

	public void setInitPDUADC(boolean initPDUADC) {
		this.initPDUADC = initPDUADC;
	}

	public boolean isInitCANOPEN() {
		return initCANOPEN;
	}

	public void setInitCANOPEN(boolean initCANOPEN) {
		this.initCANOPEN = initCANOPEN;
	}

}
