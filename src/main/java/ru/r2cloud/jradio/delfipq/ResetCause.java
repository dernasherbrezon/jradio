package ru.r2cloud.jradio.delfipq;

import java.io.DataInputStream;
import java.io.IOException;

public class ResetCause {

	private boolean softResetWDTTimerexpiration;
	private boolean cpuLockUp;
	private boolean porPowerSettle;
	private boolean porClockSettle;
	private boolean voltageAnomaly;
	private boolean hardResetWDTWrongPassword;
	private boolean hardResetWDTTimerexpiration;
	private boolean systemResetOutput;

	private boolean sysCTLReboot;
	private boolean nmiPin;
	private boolean exitLPM45;
	private boolean exitLPM35;
	private boolean badBandGapReference;
	private boolean supplySupervisorVccTrip;
	private boolean vccDetectorTrip;
	private boolean softResetWDTWrongPassword;

	private boolean dcoShortCircuitFault;

	public ResetCause() {
		// do nothing
	}

	public ResetCause(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		softResetWDTTimerexpiration = ((raw >> 7) & 0x1) > 0;
		cpuLockUp = ((raw >> 6) & 0x1) > 0;
		porPowerSettle = ((raw >> 5) & 0x1) > 0;
		porClockSettle = ((raw >> 4) & 0x1) > 0;
		voltageAnomaly = ((raw >> 3) & 0x1) > 0;
		hardResetWDTWrongPassword = ((raw >> 2) & 0x1) > 0;
		hardResetWDTTimerexpiration = ((raw >> 1) & 0x1) > 0;
		systemResetOutput = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		sysCTLReboot = ((raw >> 7) & 0x1) > 0;
		nmiPin = ((raw >> 6) & 0x1) > 0;
		exitLPM45 = ((raw >> 5) & 0x1) > 0;
		exitLPM35 = ((raw >> 4) & 0x1) > 0;
		badBandGapReference = ((raw >> 3) & 0x1) > 0;
		supplySupervisorVccTrip = ((raw >> 2) & 0x1) > 0;
		vccDetectorTrip = ((raw >> 1) & 0x1) > 0;
		softResetWDTWrongPassword = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		dcoShortCircuitFault = (raw & 0x1) > 0;
	}

	public boolean isSoftResetWDTTimerexpiration() {
		return softResetWDTTimerexpiration;
	}

	public void setSoftResetWDTTimerexpiration(boolean softResetWDTTimerexpiration) {
		this.softResetWDTTimerexpiration = softResetWDTTimerexpiration;
	}

	public boolean isCpuLockUp() {
		return cpuLockUp;
	}

	public void setCpuLockUp(boolean cpuLockUp) {
		this.cpuLockUp = cpuLockUp;
	}

	public boolean isPorPowerSettle() {
		return porPowerSettle;
	}

	public void setPorPowerSettle(boolean porPowerSettle) {
		this.porPowerSettle = porPowerSettle;
	}

	public boolean isPorClockSettle() {
		return porClockSettle;
	}

	public void setPorClockSettle(boolean porClockSettle) {
		this.porClockSettle = porClockSettle;
	}

	public boolean isVoltageAnomaly() {
		return voltageAnomaly;
	}

	public void setVoltageAnomaly(boolean voltageAnomaly) {
		this.voltageAnomaly = voltageAnomaly;
	}

	public boolean isHardResetWDTWrongPassword() {
		return hardResetWDTWrongPassword;
	}

	public void setHardResetWDTWrongPassword(boolean hardResetWDTWrongPassword) {
		this.hardResetWDTWrongPassword = hardResetWDTWrongPassword;
	}

	public boolean isHardResetWDTTimerexpiration() {
		return hardResetWDTTimerexpiration;
	}

	public void setHardResetWDTTimerexpiration(boolean hardResetWDTTimerexpiration) {
		this.hardResetWDTTimerexpiration = hardResetWDTTimerexpiration;
	}

	public boolean isSystemResetOutput() {
		return systemResetOutput;
	}

	public void setSystemResetOutput(boolean systemResetOutput) {
		this.systemResetOutput = systemResetOutput;
	}

	public boolean isSysCTLReboot() {
		return sysCTLReboot;
	}

	public void setSysCTLReboot(boolean sysCTLReboot) {
		this.sysCTLReboot = sysCTLReboot;
	}

	public boolean isNmiPin() {
		return nmiPin;
	}

	public void setNmiPin(boolean nmiPin) {
		this.nmiPin = nmiPin;
	}

	public boolean isExitLPM45() {
		return exitLPM45;
	}

	public void setExitLPM45(boolean exitLPM45) {
		this.exitLPM45 = exitLPM45;
	}

	public boolean isExitLPM35() {
		return exitLPM35;
	}

	public void setExitLPM35(boolean exitLPM35) {
		this.exitLPM35 = exitLPM35;
	}

	public boolean isBadBandGapReference() {
		return badBandGapReference;
	}

	public void setBadBandGapReference(boolean badBandGapReference) {
		this.badBandGapReference = badBandGapReference;
	}

	public boolean isSupplySupervisorVccTrip() {
		return supplySupervisorVccTrip;
	}

	public void setSupplySupervisorVccTrip(boolean supplySupervisorVccTrip) {
		this.supplySupervisorVccTrip = supplySupervisorVccTrip;
	}

	public boolean isVccDetectorTrip() {
		return vccDetectorTrip;
	}

	public void setVccDetectorTrip(boolean vccDetectorTrip) {
		this.vccDetectorTrip = vccDetectorTrip;
	}

	public boolean isSoftResetWDTWrongPassword() {
		return softResetWDTWrongPassword;
	}

	public void setSoftResetWDTWrongPassword(boolean softResetWDTWrongPassword) {
		this.softResetWDTWrongPassword = softResetWDTWrongPassword;
	}

	public boolean isDcoShortCircuitFault() {
		return dcoShortCircuitFault;
	}

	public void setDcoShortCircuitFault(boolean dcoShortCircuitFault) {
		this.dcoShortCircuitFault = dcoShortCircuitFault;
	}

}
