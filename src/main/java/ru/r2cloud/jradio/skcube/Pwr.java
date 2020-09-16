package ru.r2cloud.jradio.skcube;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Pwr {

	private long timestamp;
	private long fullUptime;
	private long uptime;
	private int activeUP;
	private int fwVer;
	private int reboots;
	private int countCOMErrors;
	private int countPSUErrors;
	private int iSolarYp;
	private int iSolarXp;
	private int iSolarYm;
	private int iSolarXm;
	private int iSolarZp;
	private int iSolarZm;
	private int tSolarYp;
	private int tSolarXp;
	private int tSolarYm;
	private int tSolarXm;
	private int tSolarZp;
	private int tSolarZm;
	private int uBatA;
	private int uBatB;
	private int tBat;
	private int cBatA;
	private int cBatB;
	private int capacity;
	private int iBatA;
	private int iBatB;
	private int iBatAmin;
	private int iBatBmin;
	private int iBatAmax;
	private int iBatBmax;
	private int iBatAavg;
	private int iBatBavg;
	private int iCDHSmin;
	private int iCDHSmax;
	private int iCDHSavg;
	private int iCDHSactual;
	private int iCOMmin;
	private int iCOMmax;
	private int iCOMavg;
	private int iCOMactual;
	private int iCAMmin;
	private int iCAMmax;
	private int iCAMavg;
	private int iCAMactual;
	private int iEXPmin;
	private int iEXPmax;
	private int iEXPavg;
	private int iEXPactual;
	private int iADCSmin;
	private int iADCSmax;
	private int iADCSavg;
	private int iADCSactual;
	private int uSYSmin;
	private int uSYSmax;
	private int uSYSactual;

	private boolean psuErrorTrxSwOvercurrent;
	private boolean psuErrorTrxHwOvercurrent;
	private boolean psuErrorObcSwOvercurrent;
	private boolean psuErrorObcHwOvercurrent;
	private boolean psuErrorAdcsSwOvercurrent;
	private boolean psuErrorAdcsHwOvercurrent;
	private boolean psuErrorCamSwOvercurrent;
	private boolean psuErrorCamHwOvercurrent;
	private boolean psuErrorExpSwOvercurrent;
	private boolean psuErrorExpHwOvercurrent;
	private boolean psuTrxSwitchedByObc;
	private boolean psuObcSwitchedByObc;
	private boolean psuErrorAdcsUndervoltage;
	private boolean psuErrorCamUndervoltage;
	private boolean psuErrorExpUndervoltage;

	private boolean psuConnectionStatusAdcs;
	private boolean psuConnectionStatusCam;
	private boolean psuConnectionStatusExp;
	private boolean psuConnectionStatusBat1;
	private boolean psuConnectionStatusBat2;
	private boolean psuConnectionStatusSolarDcdc;
	private boolean psuConnectionStatusHeating;
	private boolean psuBatteryCurrentUnbalanced;
	private boolean psuMateCommOk;
	private boolean psuServiceMode;

	private int tsTemp;
	private long countCOMpacket;
	private int PSUError;
	private int PSULastError;
	private int COMError;
	private int COMLastError;

	private int cdhsILimit;
	private int comILimit;
	private int camILimit;
	private int adcsILimit;
	private int expILimit;
	private int camUvLimit;
	private int expUvLimit;
	private int adcsUvLimit;
	private int bat1VoltageMax;
	private int bat1VoltageMin;
	private int bat2VoltageMax;
	private int bat2VoltageMin;

	private int endofdata;

	public Pwr() {
		// do nothing
	}

	public Pwr(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		fullUptime = dis.readUnsignedInt();
		uptime = dis.readUnsignedInt();
		activeUP = dis.readUnsignedShort();
		fwVer = dis.readUnsignedShort();
		reboots = dis.readUnsignedShort();
		countCOMErrors = dis.readUnsignedShort();
		countPSUErrors = dis.readUnsignedShort();
		iSolarYp = dis.readShort();
		iSolarXp = dis.readShort();
		iSolarYm = dis.readShort();
		iSolarXm = dis.readShort();
		iSolarZp = dis.readShort();
		iSolarZm = dis.readShort();
		tSolarYp = dis.readShort();
		tSolarXp = dis.readShort();
		tSolarYm = dis.readShort();
		tSolarXm = dis.readShort();
		tSolarZp = dis.readShort();
		tSolarZm = dis.readShort();
		uBatA = dis.readShort();
		uBatB = dis.readShort();
		tBat = dis.readShort();
		cBatA = dis.readUnsignedShort();
		cBatB = dis.readUnsignedShort();
		capacity = dis.readUnsignedShort();
		iBatA = dis.readShort();
		iBatB = dis.readShort();
		iBatAmin = dis.readShort();
		iBatBmin = dis.readShort();
		iBatAmax = dis.readShort();
		iBatBmax = dis.readShort();
		iBatAavg = dis.readShort();
		iBatBavg = dis.readShort();
		iCDHSmin = dis.readShort();
		iCDHSmax = dis.readShort();
		iCDHSavg = dis.readShort();
		iCDHSactual = dis.readShort();
		iCOMmin = dis.readShort();
		iCOMmax = dis.readShort();
		iCOMavg = dis.readShort();
		iCOMactual = dis.readShort();
		iCAMmin = dis.readShort();
		iCAMmax = dis.readShort();
		iCAMavg = dis.readShort();
		iCAMactual = dis.readShort();
		iEXPmin = dis.readShort();
		iEXPmax = dis.readShort();
		iEXPavg = dis.readShort();
		iEXPactual = dis.readShort();
		iADCSmin = dis.readShort();
		iADCSmax = dis.readShort();
		iADCSavg = dis.readShort();
		iADCSactual = dis.readShort();
		uSYSmin = dis.readUnsignedShort();
		uSYSmax = dis.readUnsignedShort();
		uSYSactual = dis.readUnsignedShort();
		int raw = dis.readUnsignedShort();
		psuErrorTrxSwOvercurrent = (raw & 0x1) > 0;
		psuErrorTrxHwOvercurrent = ((raw >> 1) & 0x1) > 0;
		psuErrorObcSwOvercurrent = ((raw >> 2) & 0x1) > 0;
		psuErrorObcHwOvercurrent = ((raw >> 3) & 0x1) > 0;
		psuErrorAdcsSwOvercurrent = ((raw >> 4) & 0x1) > 0;
		psuErrorAdcsHwOvercurrent = ((raw >> 5) & 0x1) > 0;
		psuErrorCamSwOvercurrent = ((raw >> 6) & 0x1) > 0;
		psuErrorCamHwOvercurrent = ((raw >> 7) & 0x1) > 0;
		psuErrorExpSwOvercurrent = ((raw >> 8) & 0x1) > 0;
		psuErrorExpHwOvercurrent = ((raw >> 9) & 0x1) > 0;
		psuTrxSwitchedByObc = ((raw >> 10) & 0x1) > 0;
		psuObcSwitchedByObc = ((raw >> 11) & 0x1) > 0;
		psuErrorAdcsUndervoltage = ((raw >> 12) & 0x1) > 0;
		psuErrorCamUndervoltage = ((raw >> 13) & 0x1) > 0;
		psuErrorExpUndervoltage = ((raw >> 14) & 0x1) > 0;

		raw = dis.readUnsignedShort();
		psuConnectionStatusAdcs = (raw & 0x1) > 0;
		psuConnectionStatusCam = ((raw >> 1) & 0x1) > 0;
		psuConnectionStatusExp = ((raw >> 2) & 0x1) > 0;
		psuConnectionStatusBat1 = ((raw >> 3) & 0x1) > 0;
		psuConnectionStatusBat2 = ((raw >> 4) & 0x1) > 0;
		psuConnectionStatusSolarDcdc = ((raw >> 5) & 0x1) > 0;
		psuConnectionStatusHeating = ((raw >> 6) & 0x1) > 0;
		psuBatteryCurrentUnbalanced = ((raw >> 13) & 0x1) > 0;
		psuMateCommOk = ((raw >> 14) & 0x1) > 0;
		psuServiceMode = ((raw >> 15) & 0x1) > 0;
		tsTemp = dis.readShort();
		countCOMpacket = dis.readUnsignedInt();
		PSUError = dis.readShort();
		PSULastError = dis.readShort();
		COMError = dis.readShort();
		COMLastError = dis.readShort();

		cdhsILimit = dis.readUnsignedByte();
		comILimit = dis.readUnsignedByte();
		camILimit = dis.readUnsignedByte();
		adcsILimit = dis.readUnsignedByte();
		expILimit = dis.readUnsignedByte();
		camUvLimit = dis.readUnsignedByte();
		expUvLimit = dis.readUnsignedByte();
		adcsUvLimit = dis.readUnsignedByte();
		bat1VoltageMax = dis.readShort();
		bat1VoltageMin = dis.readShort();
		bat2VoltageMax = dis.readShort();
		bat2VoltageMin = dis.readShort();

		endofdata = dis.readUnsignedShort();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getFullUptime() {
		return fullUptime;
	}

	public void setFullUptime(long fullUptime) {
		this.fullUptime = fullUptime;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getActiveUP() {
		return activeUP;
	}

	public void setActiveUP(int activeUP) {
		this.activeUP = activeUP;
	}

	public int getFwVer() {
		return fwVer;
	}

	public void setFwVer(int fwVer) {
		this.fwVer = fwVer;
	}

	public int getReboots() {
		return reboots;
	}

	public void setReboots(int reboots) {
		this.reboots = reboots;
	}

	public int getCountCOMErrors() {
		return countCOMErrors;
	}

	public void setCountCOMErrors(int countCOMErrors) {
		this.countCOMErrors = countCOMErrors;
	}

	public int getCountPSUErrors() {
		return countPSUErrors;
	}

	public void setCountPSUErrors(int countPSUErrors) {
		this.countPSUErrors = countPSUErrors;
	}

	public int getiSolarYp() {
		return iSolarYp;
	}

	public void setiSolarYp(int iSolarYp) {
		this.iSolarYp = iSolarYp;
	}

	public int getiSolarXp() {
		return iSolarXp;
	}

	public void setiSolarXp(int iSolarXp) {
		this.iSolarXp = iSolarXp;
	}

	public int getiSolarYm() {
		return iSolarYm;
	}

	public void setiSolarYm(int iSolarYm) {
		this.iSolarYm = iSolarYm;
	}

	public int getiSolarXm() {
		return iSolarXm;
	}

	public void setiSolarXm(int iSolarXm) {
		this.iSolarXm = iSolarXm;
	}

	public int getiSolarZp() {
		return iSolarZp;
	}

	public void setiSolarZp(int iSolarZp) {
		this.iSolarZp = iSolarZp;
	}

	public int getiSolarZm() {
		return iSolarZm;
	}

	public void setiSolarZm(int iSolarZm) {
		this.iSolarZm = iSolarZm;
	}

	public int gettSolarYp() {
		return tSolarYp;
	}

	public void settSolarYp(int tSolarYp) {
		this.tSolarYp = tSolarYp;
	}

	public int gettSolarXp() {
		return tSolarXp;
	}

	public void settSolarXp(int tSolarXp) {
		this.tSolarXp = tSolarXp;
	}

	public int gettSolarYm() {
		return tSolarYm;
	}

	public void settSolarYm(int tSolarYm) {
		this.tSolarYm = tSolarYm;
	}

	public int gettSolarXm() {
		return tSolarXm;
	}

	public void settSolarXm(int tSolarXm) {
		this.tSolarXm = tSolarXm;
	}

	public int gettSolarZp() {
		return tSolarZp;
	}

	public void settSolarZp(int tSolarZp) {
		this.tSolarZp = tSolarZp;
	}

	public int gettSolarZm() {
		return tSolarZm;
	}

	public void settSolarZm(int tSolarZm) {
		this.tSolarZm = tSolarZm;
	}

	public int getuBatA() {
		return uBatA;
	}

	public void setuBatA(int uBatA) {
		this.uBatA = uBatA;
	}

	public int getuBatB() {
		return uBatB;
	}

	public void setuBatB(int uBatB) {
		this.uBatB = uBatB;
	}

	public int gettBat() {
		return tBat;
	}

	public void settBat(int tBat) {
		this.tBat = tBat;
	}

	public int getcBatA() {
		return cBatA;
	}

	public void setcBatA(int cBatA) {
		this.cBatA = cBatA;
	}

	public int getcBatB() {
		return cBatB;
	}

	public void setcBatB(int cBatB) {
		this.cBatB = cBatB;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getiBatA() {
		return iBatA;
	}

	public void setiBatA(int iBatA) {
		this.iBatA = iBatA;
	}

	public int getiBatB() {
		return iBatB;
	}

	public void setiBatB(int iBatB) {
		this.iBatB = iBatB;
	}

	public int getiBatAmin() {
		return iBatAmin;
	}

	public void setiBatAmin(int iBatAmin) {
		this.iBatAmin = iBatAmin;
	}

	public int getiBatBmin() {
		return iBatBmin;
	}

	public void setiBatBmin(int iBatBmin) {
		this.iBatBmin = iBatBmin;
	}

	public int getiBatAmax() {
		return iBatAmax;
	}

	public void setiBatAmax(int iBatAmax) {
		this.iBatAmax = iBatAmax;
	}

	public int getiBatBmax() {
		return iBatBmax;
	}

	public void setiBatBmax(int iBatBmax) {
		this.iBatBmax = iBatBmax;
	}

	public int getiBatAavg() {
		return iBatAavg;
	}

	public void setiBatAavg(int iBatAavg) {
		this.iBatAavg = iBatAavg;
	}

	public int getiBatBavg() {
		return iBatBavg;
	}

	public void setiBatBavg(int iBatBavg) {
		this.iBatBavg = iBatBavg;
	}

	public int getiCDHSmin() {
		return iCDHSmin;
	}

	public void setiCDHSmin(int iCDHSmin) {
		this.iCDHSmin = iCDHSmin;
	}

	public int getiCDHSmax() {
		return iCDHSmax;
	}

	public void setiCDHSmax(int iCDHSmax) {
		this.iCDHSmax = iCDHSmax;
	}

	public int getiCDHSavg() {
		return iCDHSavg;
	}

	public void setiCDHSavg(int iCDHSavg) {
		this.iCDHSavg = iCDHSavg;
	}

	public int getiCDHSactual() {
		return iCDHSactual;
	}

	public void setiCDHSactual(int iCDHSactual) {
		this.iCDHSactual = iCDHSactual;
	}

	public int getiCOMmin() {
		return iCOMmin;
	}

	public void setiCOMmin(int iCOMmin) {
		this.iCOMmin = iCOMmin;
	}

	public int getiCOMmax() {
		return iCOMmax;
	}

	public void setiCOMmax(int iCOMmax) {
		this.iCOMmax = iCOMmax;
	}

	public int getiCOMavg() {
		return iCOMavg;
	}

	public void setiCOMavg(int iCOMavg) {
		this.iCOMavg = iCOMavg;
	}

	public int getiCOMactual() {
		return iCOMactual;
	}

	public void setiCOMactual(int iCOMactual) {
		this.iCOMactual = iCOMactual;
	}

	public int getiCAMmin() {
		return iCAMmin;
	}

	public void setiCAMmin(int iCAMmin) {
		this.iCAMmin = iCAMmin;
	}

	public int getiCAMmax() {
		return iCAMmax;
	}

	public void setiCAMmax(int iCAMmax) {
		this.iCAMmax = iCAMmax;
	}

	public int getiCAMavg() {
		return iCAMavg;
	}

	public void setiCAMavg(int iCAMavg) {
		this.iCAMavg = iCAMavg;
	}

	public int getiCAMactual() {
		return iCAMactual;
	}

	public void setiCAMactual(int iCAMactual) {
		this.iCAMactual = iCAMactual;
	}

	public int getiEXPmin() {
		return iEXPmin;
	}

	public void setiEXPmin(int iEXPmin) {
		this.iEXPmin = iEXPmin;
	}

	public int getiEXPmax() {
		return iEXPmax;
	}

	public void setiEXPmax(int iEXPmax) {
		this.iEXPmax = iEXPmax;
	}

	public int getiEXPavg() {
		return iEXPavg;
	}

	public void setiEXPavg(int iEXPavg) {
		this.iEXPavg = iEXPavg;
	}

	public int getiEXPactual() {
		return iEXPactual;
	}

	public void setiEXPactual(int iEXPactual) {
		this.iEXPactual = iEXPactual;
	}

	public int getiADCSmin() {
		return iADCSmin;
	}

	public void setiADCSmin(int iADCSmin) {
		this.iADCSmin = iADCSmin;
	}

	public int getiADCSmax() {
		return iADCSmax;
	}

	public void setiADCSmax(int iADCSmax) {
		this.iADCSmax = iADCSmax;
	}

	public int getiADCSavg() {
		return iADCSavg;
	}

	public void setiADCSavg(int iADCSavg) {
		this.iADCSavg = iADCSavg;
	}

	public int getiADCSactual() {
		return iADCSactual;
	}

	public void setiADCSactual(int iADCSactual) {
		this.iADCSactual = iADCSactual;
	}

	public int getuSYSmin() {
		return uSYSmin;
	}

	public void setuSYSmin(int uSYSmin) {
		this.uSYSmin = uSYSmin;
	}

	public int getuSYSmax() {
		return uSYSmax;
	}

	public void setuSYSmax(int uSYSmax) {
		this.uSYSmax = uSYSmax;
	}

	public int getuSYSactual() {
		return uSYSactual;
	}

	public void setuSYSactual(int uSYSactual) {
		this.uSYSactual = uSYSactual;
	}

	public boolean isPsuErrorTrxSwOvercurrent() {
		return psuErrorTrxSwOvercurrent;
	}

	public void setPsuErrorTrxSwOvercurrent(boolean psuErrorTrxSwOvercurrent) {
		this.psuErrorTrxSwOvercurrent = psuErrorTrxSwOvercurrent;
	}

	public boolean isPsuErrorTrxHwOvercurrent() {
		return psuErrorTrxHwOvercurrent;
	}

	public void setPsuErrorTrxHwOvercurrent(boolean psuErrorTrxHwOvercurrent) {
		this.psuErrorTrxHwOvercurrent = psuErrorTrxHwOvercurrent;
	}

	public boolean isPsuErrorObcSwOvercurrent() {
		return psuErrorObcSwOvercurrent;
	}

	public void setPsuErrorObcSwOvercurrent(boolean psuErrorObcSwOvercurrent) {
		this.psuErrorObcSwOvercurrent = psuErrorObcSwOvercurrent;
	}

	public boolean isPsuErrorObcHwOvercurrent() {
		return psuErrorObcHwOvercurrent;
	}

	public void setPsuErrorObcHwOvercurrent(boolean psuErrorObcHwOvercurrent) {
		this.psuErrorObcHwOvercurrent = psuErrorObcHwOvercurrent;
	}

	public boolean isPsuErrorAdcsSwOvercurrent() {
		return psuErrorAdcsSwOvercurrent;
	}

	public void setPsuErrorAdcsSwOvercurrent(boolean psuErrorAdcsSwOvercurrent) {
		this.psuErrorAdcsSwOvercurrent = psuErrorAdcsSwOvercurrent;
	}

	public boolean isPsuErrorAdcsHwOvercurrent() {
		return psuErrorAdcsHwOvercurrent;
	}

	public void setPsuErrorAdcsHwOvercurrent(boolean psuErrorAdcsHwOvercurrent) {
		this.psuErrorAdcsHwOvercurrent = psuErrorAdcsHwOvercurrent;
	}

	public boolean isPsuErrorCamSwOvercurrent() {
		return psuErrorCamSwOvercurrent;
	}

	public void setPsuErrorCamSwOvercurrent(boolean psuErrorCamSwOvercurrent) {
		this.psuErrorCamSwOvercurrent = psuErrorCamSwOvercurrent;
	}

	public boolean isPsuErrorCamHwOvercurrent() {
		return psuErrorCamHwOvercurrent;
	}

	public void setPsuErrorCamHwOvercurrent(boolean psuErrorCamHwOvercurrent) {
		this.psuErrorCamHwOvercurrent = psuErrorCamHwOvercurrent;
	}

	public boolean isPsuErrorExpSwOvercurrent() {
		return psuErrorExpSwOvercurrent;
	}

	public void setPsuErrorExpSwOvercurrent(boolean psuErrorExpSwOvercurrent) {
		this.psuErrorExpSwOvercurrent = psuErrorExpSwOvercurrent;
	}

	public boolean isPsuErrorExpHwOvercurrent() {
		return psuErrorExpHwOvercurrent;
	}

	public void setPsuErrorExpHwOvercurrent(boolean psuErrorExpHwOvercurrent) {
		this.psuErrorExpHwOvercurrent = psuErrorExpHwOvercurrent;
	}

	public boolean isPsuTrxSwitchedByObc() {
		return psuTrxSwitchedByObc;
	}

	public void setPsuTrxSwitchedByObc(boolean psuTrxSwitchedByObc) {
		this.psuTrxSwitchedByObc = psuTrxSwitchedByObc;
	}

	public boolean isPsuObcSwitchedByObc() {
		return psuObcSwitchedByObc;
	}

	public void setPsuObcSwitchedByObc(boolean psuObcSwitchedByObc) {
		this.psuObcSwitchedByObc = psuObcSwitchedByObc;
	}

	public boolean isPsuErrorAdcsUndervoltage() {
		return psuErrorAdcsUndervoltage;
	}

	public void setPsuErrorAdcsUndervoltage(boolean psuErrorAdcsUndervoltage) {
		this.psuErrorAdcsUndervoltage = psuErrorAdcsUndervoltage;
	}

	public boolean isPsuErrorCamUndervoltage() {
		return psuErrorCamUndervoltage;
	}

	public void setPsuErrorCamUndervoltage(boolean psuErrorCamUndervoltage) {
		this.psuErrorCamUndervoltage = psuErrorCamUndervoltage;
	}

	public boolean isPsuErrorExpUndervoltage() {
		return psuErrorExpUndervoltage;
	}

	public void setPsuErrorExpUndervoltage(boolean psuErrorExpUndervoltage) {
		this.psuErrorExpUndervoltage = psuErrorExpUndervoltage;
	}

	public boolean isPsuConnectionStatusAdcs() {
		return psuConnectionStatusAdcs;
	}

	public void setPsuConnectionStatusAdcs(boolean psuConnectionStatusAdcs) {
		this.psuConnectionStatusAdcs = psuConnectionStatusAdcs;
	}

	public boolean isPsuConnectionStatusCam() {
		return psuConnectionStatusCam;
	}

	public void setPsuConnectionStatusCam(boolean psuConnectionStatusCam) {
		this.psuConnectionStatusCam = psuConnectionStatusCam;
	}

	public boolean isPsuConnectionStatusExp() {
		return psuConnectionStatusExp;
	}

	public void setPsuConnectionStatusExp(boolean psuConnectionStatusExp) {
		this.psuConnectionStatusExp = psuConnectionStatusExp;
	}

	public boolean isPsuConnectionStatusBat1() {
		return psuConnectionStatusBat1;
	}

	public void setPsuConnectionStatusBat1(boolean psuConnectionStatusBat1) {
		this.psuConnectionStatusBat1 = psuConnectionStatusBat1;
	}

	public boolean isPsuConnectionStatusBat2() {
		return psuConnectionStatusBat2;
	}

	public void setPsuConnectionStatusBat2(boolean psuConnectionStatusBat2) {
		this.psuConnectionStatusBat2 = psuConnectionStatusBat2;
	}

	public boolean isPsuConnectionStatusSolarDcdc() {
		return psuConnectionStatusSolarDcdc;
	}

	public void setPsuConnectionStatusSolarDcdc(boolean psuConnectionStatusSolarDcdc) {
		this.psuConnectionStatusSolarDcdc = psuConnectionStatusSolarDcdc;
	}

	public boolean isPsuConnectionStatusHeating() {
		return psuConnectionStatusHeating;
	}

	public void setPsuConnectionStatusHeating(boolean psuConnectionStatusHeating) {
		this.psuConnectionStatusHeating = psuConnectionStatusHeating;
	}

	public boolean isPsuBatteryCurrentUnbalanced() {
		return psuBatteryCurrentUnbalanced;
	}

	public void setPsuBatteryCurrentUnbalanced(boolean psuBatteryCurrentUnbalanced) {
		this.psuBatteryCurrentUnbalanced = psuBatteryCurrentUnbalanced;
	}

	public boolean isPsuMateCommOk() {
		return psuMateCommOk;
	}

	public void setPsuMateCommOk(boolean psuMateCommOk) {
		this.psuMateCommOk = psuMateCommOk;
	}

	public boolean isPsuServiceMode() {
		return psuServiceMode;
	}

	public void setPsuServiceMode(boolean psuServiceMode) {
		this.psuServiceMode = psuServiceMode;
	}

	public int getTsTemp() {
		return tsTemp;
	}

	public void setTsTemp(int tsTemp) {
		this.tsTemp = tsTemp;
	}

	public long getCountCOMpacket() {
		return countCOMpacket;
	}

	public void setCountCOMpacket(long countCOMpacket) {
		this.countCOMpacket = countCOMpacket;
	}

	public int getPSUError() {
		return PSUError;
	}

	public void setPSUError(int pSUError) {
		PSUError = pSUError;
	}

	public int getPSULastError() {
		return PSULastError;
	}

	public void setPSULastError(int pSULastError) {
		PSULastError = pSULastError;
	}

	public int getCOMError() {
		return COMError;
	}

	public void setCOMError(int cOMError) {
		COMError = cOMError;
	}

	public int getCOMLastError() {
		return COMLastError;
	}

	public void setCOMLastError(int cOMLastError) {
		COMLastError = cOMLastError;
	}

	public int getCdhsILimit() {
		return cdhsILimit;
	}

	public void setCdhsILimit(int cdhsILimit) {
		this.cdhsILimit = cdhsILimit;
	}

	public int getComILimit() {
		return comILimit;
	}

	public void setComILimit(int comILimit) {
		this.comILimit = comILimit;
	}

	public int getCamILimit() {
		return camILimit;
	}

	public void setCamILimit(int camILimit) {
		this.camILimit = camILimit;
	}

	public int getAdcsILimit() {
		return adcsILimit;
	}

	public void setAdcsILimit(int adcsILimit) {
		this.adcsILimit = adcsILimit;
	}

	public int getExpILimit() {
		return expILimit;
	}

	public void setExpILimit(int expILimit) {
		this.expILimit = expILimit;
	}

	public int getCamUvLimit() {
		return camUvLimit;
	}

	public void setCamUvLimit(int camUvLimit) {
		this.camUvLimit = camUvLimit;
	}

	public int getExpUvLimit() {
		return expUvLimit;
	}

	public void setExpUvLimit(int expUvLimit) {
		this.expUvLimit = expUvLimit;
	}

	public int getAdcsUvLimit() {
		return adcsUvLimit;
	}

	public void setAdcsUvLimit(int adcsUvLimit) {
		this.adcsUvLimit = adcsUvLimit;
	}

	public int getBat1VoltageMax() {
		return bat1VoltageMax;
	}

	public void setBat1VoltageMax(int bat1VoltageMax) {
		this.bat1VoltageMax = bat1VoltageMax;
	}

	public int getBat1VoltageMin() {
		return bat1VoltageMin;
	}

	public void setBat1VoltageMin(int bat1VoltageMin) {
		this.bat1VoltageMin = bat1VoltageMin;
	}

	public int getBat2VoltageMax() {
		return bat2VoltageMax;
	}

	public void setBat2VoltageMax(int bat2VoltageMax) {
		this.bat2VoltageMax = bat2VoltageMax;
	}

	public int getBat2VoltageMin() {
		return bat2VoltageMin;
	}

	public void setBat2VoltageMin(int bat2VoltageMin) {
		this.bat2VoltageMin = bat2VoltageMin;
	}

	public int getEndofdata() {
		return endofdata;
	}

	public void setEndofdata(int endofdata) {
		this.endofdata = endofdata;
	}

}
