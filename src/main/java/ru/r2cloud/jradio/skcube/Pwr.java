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
	private int currentemperatureSolarYp;
	private int currentemperatureSolarXp;
	private int currentemperatureSolarYm;
	private int currentemperatureSolarXm;
	private int currentemperatureSolarZp;
	private int currentemperatureSolarZm;
	private int temperatureSolarYp;
	private int temperatureSolarXp;
	private int temperatureSolarYm;
	private int temperatureSolarXm;
	private int temperatureSolarZp;
	private int temperatureSolarZm;
	private int voltageBatA;
	private int voltageBatB;
	private int temperatureBat;
	private int capacityBatA;
	private int capacityBatB;
	private int capacity;
	private int currentBatA;
	private int currentBatB;
	private int currentBatAmin;
	private int currentBatBmin;
	private int currentBatAmax;
	private int currentBatBmax;
	private int currentBatAavg;
	private int currentBatBavg;
	private int currentCdhsMin;
	private int currentCdhsMax;
	private int currentCdhsAvg;
	private int currentCdhsActual;
	private int currentComMin;
	private int currentComMax;
	private int currentComAvg;
	private int currentComActual;
	private int currentCamMin;
	private int currentCamMax;
	private int currentCamAvg;
	private int currentCamActual;
	private int currentExpMin;
	private int currentExpMax;
	private int currentExpAvg;
	private int currentExpActual;
	private int currentAdcsMin;
	private int currentAdcsMax;
	private int currentAdcsAvg;
	private int currentAdcsActual;
	private int voltageSysMin;
	private int voltageSysMax;
	private int voltageSysActual;

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
	private int psuError;
	private int psuLastError;
	private int comError;
	private int comLastError;

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
		currentemperatureSolarYp = dis.readShort();
		currentemperatureSolarXp = dis.readShort();
		currentemperatureSolarYm = dis.readShort();
		currentemperatureSolarXm = dis.readShort();
		currentemperatureSolarZp = dis.readShort();
		currentemperatureSolarZm = dis.readShort();
		temperatureSolarYp = dis.readShort();
		temperatureSolarXp = dis.readShort();
		temperatureSolarYm = dis.readShort();
		temperatureSolarXm = dis.readShort();
		temperatureSolarZp = dis.readShort();
		temperatureSolarZm = dis.readShort();
		voltageBatA = dis.readShort();
		voltageBatB = dis.readShort();
		temperatureBat = dis.readShort();
		capacityBatA = dis.readUnsignedShort();
		capacityBatB = dis.readUnsignedShort();
		capacity = dis.readUnsignedShort();
		currentBatA = dis.readShort();
		currentBatB = dis.readShort();
		currentBatAmin = dis.readShort();
		currentBatBmin = dis.readShort();
		currentBatAmax = dis.readShort();
		currentBatBmax = dis.readShort();
		currentBatAavg = dis.readShort();
		currentBatBavg = dis.readShort();
		currentCdhsMin = dis.readShort();
		currentCdhsMax = dis.readShort();
		currentCdhsAvg = dis.readShort();
		currentCdhsActual = dis.readShort();
		currentComMin = dis.readShort();
		currentComMax = dis.readShort();
		currentComAvg = dis.readShort();
		currentComActual = dis.readShort();
		currentCamMin = dis.readShort();
		currentCamMax = dis.readShort();
		currentCamAvg = dis.readShort();
		currentCamActual = dis.readShort();
		currentExpMin = dis.readShort();
		currentExpMax = dis.readShort();
		currentExpAvg = dis.readShort();
		currentExpActual = dis.readShort();
		currentAdcsMin = dis.readShort();
		currentAdcsMax = dis.readShort();
		currentAdcsAvg = dis.readShort();
		currentAdcsActual = dis.readShort();
		voltageSysMin = dis.readUnsignedShort();
		voltageSysMax = dis.readUnsignedShort();
		voltageSysActual = dis.readUnsignedShort();
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
		psuError = dis.readShort();
		psuLastError = dis.readShort();
		comError = dis.readShort();
		comLastError = dis.readShort();

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

	public int getCurrentemperatureSolarYp() {
		return currentemperatureSolarYp;
	}

	public void setCurrentemperatureSolarYp(int currentemperatureSolarYp) {
		this.currentemperatureSolarYp = currentemperatureSolarYp;
	}

	public int getCurrentemperatureSolarXp() {
		return currentemperatureSolarXp;
	}

	public void setCurrentemperatureSolarXp(int currentemperatureSolarXp) {
		this.currentemperatureSolarXp = currentemperatureSolarXp;
	}

	public int getCurrentemperatureSolarYm() {
		return currentemperatureSolarYm;
	}

	public void setCurrentemperatureSolarYm(int currentemperatureSolarYm) {
		this.currentemperatureSolarYm = currentemperatureSolarYm;
	}

	public int getCurrentemperatureSolarXm() {
		return currentemperatureSolarXm;
	}

	public void setCurrentemperatureSolarXm(int currentemperatureSolarXm) {
		this.currentemperatureSolarXm = currentemperatureSolarXm;
	}

	public int getCurrentemperatureSolarZp() {
		return currentemperatureSolarZp;
	}

	public void setCurrentemperatureSolarZp(int currentemperatureSolarZp) {
		this.currentemperatureSolarZp = currentemperatureSolarZp;
	}

	public int getCurrentemperatureSolarZm() {
		return currentemperatureSolarZm;
	}

	public void setCurrentemperatureSolarZm(int currentemperatureSolarZm) {
		this.currentemperatureSolarZm = currentemperatureSolarZm;
	}

	public int getTemperatureSolarYp() {
		return temperatureSolarYp;
	}

	public void setTemperatureSolarYp(int temperatureSolarYp) {
		this.temperatureSolarYp = temperatureSolarYp;
	}

	public int getTemperatureSolarXp() {
		return temperatureSolarXp;
	}

	public void setTemperatureSolarXp(int temperatureSolarXp) {
		this.temperatureSolarXp = temperatureSolarXp;
	}

	public int getTemperatureSolarYm() {
		return temperatureSolarYm;
	}

	public void setTemperatureSolarYm(int temperatureSolarYm) {
		this.temperatureSolarYm = temperatureSolarYm;
	}

	public int getTemperatureSolarXm() {
		return temperatureSolarXm;
	}

	public void setTemperatureSolarXm(int temperatureSolarXm) {
		this.temperatureSolarXm = temperatureSolarXm;
	}

	public int getTemperatureSolarZp() {
		return temperatureSolarZp;
	}

	public void setTemperatureSolarZp(int temperatureSolarZp) {
		this.temperatureSolarZp = temperatureSolarZp;
	}

	public int getTemperatureSolarZm() {
		return temperatureSolarZm;
	}

	public void setTemperatureSolarZm(int temperatureSolarZm) {
		this.temperatureSolarZm = temperatureSolarZm;
	}

	public int getVoltageBatA() {
		return voltageBatA;
	}

	public void setVoltageBatA(int voltageBatA) {
		this.voltageBatA = voltageBatA;
	}

	public int getVoltageBatB() {
		return voltageBatB;
	}

	public void setVoltageBatB(int voltageBatB) {
		this.voltageBatB = voltageBatB;
	}

	public int getTemperatureBat() {
		return temperatureBat;
	}

	public void setTemperatureBat(int temperatureBat) {
		this.temperatureBat = temperatureBat;
	}

	public int getCapacityBatA() {
		return capacityBatA;
	}

	public void setCapacityBatA(int capacityBatA) {
		this.capacityBatA = capacityBatA;
	}

	public int getCapacityBatB() {
		return capacityBatB;
	}

	public void setCapacityBatB(int capacityBatB) {
		this.capacityBatB = capacityBatB;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCurrentBatA() {
		return currentBatA;
	}

	public void setCurrentBatA(int currentBatA) {
		this.currentBatA = currentBatA;
	}

	public int getCurrentBatB() {
		return currentBatB;
	}

	public void setCurrentBatB(int currentBatB) {
		this.currentBatB = currentBatB;
	}

	public int getCurrentBatAmin() {
		return currentBatAmin;
	}

	public void setCurrentBatAmin(int currentBatAmin) {
		this.currentBatAmin = currentBatAmin;
	}

	public int getCurrentBatBmin() {
		return currentBatBmin;
	}

	public void setCurrentBatBmin(int currentBatBmin) {
		this.currentBatBmin = currentBatBmin;
	}

	public int getCurrentBatAmax() {
		return currentBatAmax;
	}

	public void setCurrentBatAmax(int currentBatAmax) {
		this.currentBatAmax = currentBatAmax;
	}

	public int getCurrentBatBmax() {
		return currentBatBmax;
	}

	public void setCurrentBatBmax(int currentBatBmax) {
		this.currentBatBmax = currentBatBmax;
	}

	public int getCurrentBatAavg() {
		return currentBatAavg;
	}

	public void setCurrentBatAavg(int currentBatAavg) {
		this.currentBatAavg = currentBatAavg;
	}

	public int getCurrentBatBavg() {
		return currentBatBavg;
	}

	public void setCurrentBatBavg(int currentBatBavg) {
		this.currentBatBavg = currentBatBavg;
	}

	public int getCurrentCdhsMin() {
		return currentCdhsMin;
	}

	public void setCurrentCdhsMin(int currentCdhsMin) {
		this.currentCdhsMin = currentCdhsMin;
	}

	public int getCurrentCdhsMax() {
		return currentCdhsMax;
	}

	public void setCurrentCdhsMax(int currentCdhsMax) {
		this.currentCdhsMax = currentCdhsMax;
	}

	public int getCurrentCdhsAvg() {
		return currentCdhsAvg;
	}

	public void setCurrentCdhsAvg(int currentCdhsAvg) {
		this.currentCdhsAvg = currentCdhsAvg;
	}

	public int getCurrentCdhsActual() {
		return currentCdhsActual;
	}

	public void setCurrentCdhsActual(int currentCdhsActual) {
		this.currentCdhsActual = currentCdhsActual;
	}

	public int getCurrentComMin() {
		return currentComMin;
	}

	public void setCurrentComMin(int currentComMin) {
		this.currentComMin = currentComMin;
	}

	public int getCurrentComMax() {
		return currentComMax;
	}

	public void setCurrentComMax(int currentComMax) {
		this.currentComMax = currentComMax;
	}

	public int getCurrentComAvg() {
		return currentComAvg;
	}

	public void setCurrentComAvg(int currentComAvg) {
		this.currentComAvg = currentComAvg;
	}

	public int getCurrentComActual() {
		return currentComActual;
	}

	public void setCurrentComActual(int currentComActual) {
		this.currentComActual = currentComActual;
	}

	public int getCurrentCamMin() {
		return currentCamMin;
	}

	public void setCurrentCamMin(int currentCamMin) {
		this.currentCamMin = currentCamMin;
	}

	public int getCurrentCamMax() {
		return currentCamMax;
	}

	public void setCurrentCamMax(int currentCamMax) {
		this.currentCamMax = currentCamMax;
	}

	public int getCurrentCamAvg() {
		return currentCamAvg;
	}

	public void setCurrentCamAvg(int currentCamAvg) {
		this.currentCamAvg = currentCamAvg;
	}

	public int getCurrentCamActual() {
		return currentCamActual;
	}

	public void setCurrentCamActual(int currentCamActual) {
		this.currentCamActual = currentCamActual;
	}

	public int getCurrentExpMin() {
		return currentExpMin;
	}

	public void setCurrentExpMin(int currentExpMin) {
		this.currentExpMin = currentExpMin;
	}

	public int getCurrentExpMax() {
		return currentExpMax;
	}

	public void setCurrentExpMax(int currentExpMax) {
		this.currentExpMax = currentExpMax;
	}

	public int getCurrentExpAvg() {
		return currentExpAvg;
	}

	public void setCurrentExpAvg(int currentExpAvg) {
		this.currentExpAvg = currentExpAvg;
	}

	public int getCurrentExpActual() {
		return currentExpActual;
	}

	public void setCurrentExpActual(int currentExpActual) {
		this.currentExpActual = currentExpActual;
	}

	public int getCurrentAdcsMin() {
		return currentAdcsMin;
	}

	public void setCurrentAdcsMin(int currentAdcsMin) {
		this.currentAdcsMin = currentAdcsMin;
	}

	public int getCurrentAdcsMax() {
		return currentAdcsMax;
	}

	public void setCurrentAdcsMax(int currentAdcsMax) {
		this.currentAdcsMax = currentAdcsMax;
	}

	public int getCurrentAdcsAvg() {
		return currentAdcsAvg;
	}

	public void setCurrentAdcsAvg(int currentAdcsAvg) {
		this.currentAdcsAvg = currentAdcsAvg;
	}

	public int getCurrentAdcsActual() {
		return currentAdcsActual;
	}

	public void setCurrentAdcsActual(int currentAdcsActual) {
		this.currentAdcsActual = currentAdcsActual;
	}

	public int getVoltageSysMin() {
		return voltageSysMin;
	}

	public void setVoltageSysMin(int voltageSysMin) {
		this.voltageSysMin = voltageSysMin;
	}

	public int getVoltageSysMax() {
		return voltageSysMax;
	}

	public void setVoltageSysMax(int voltageSysMax) {
		this.voltageSysMax = voltageSysMax;
	}

	public int getVoltageSysActual() {
		return voltageSysActual;
	}

	public void setVoltageSysActual(int voltageSysActual) {
		this.voltageSysActual = voltageSysActual;
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

	public int getPsuError() {
		return psuError;
	}

	public void setPsuError(int psuError) {
		this.psuError = psuError;
	}

	public int getPsuLastError() {
		return psuLastError;
	}

	public void setPsuLastError(int psuLastError) {
		this.psuLastError = psuLastError;
	}

	public int getComError() {
		return comError;
	}

	public void setComError(int comError) {
		this.comError = comError;
	}

	public int getComLastError() {
		return comLastError;
	}

	public void setComLastError(int comLastError) {
		this.comLastError = comLastError;
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
