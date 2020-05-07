package ru.r2cloud.jradio.quetzal1;

import java.io.DataInputStream;
import java.io.IOException;

public class RamParams {

	private int cDHSCycleTime;
	private int cDHSWDTTime;
	private int aDMSOCLimit;
	private int aDCSSOCLimit;
	private int cOMMSSOCLimit;
	private int pLDSOCLimit;
	private int hTRCycleTime;
	private int heaterEmergencyOnTime;
	private int heaterEmergencyOffTime;
	private int aDMCycleTime;
	private int aDMBurnTime;
	private int aDMMaxCycles;
	private int aDMWaitTime1;
	private int aDMWaitTime2;
	private int aDMEnable;
	private int cOMMCycleTime;
	private int pLDCycleTime;
	private int pLDOperationMode;
	private int cameraResolution;
	private int cameraExposure;
	private int cameraPictureSaveTime;
	private int payloadEnable;

	public RamParams() {
		// do nothing
	}

	public RamParams(DataInputStream dis) throws IOException {
		cDHSCycleTime = dis.readUnsignedByte();
		cDHSWDTTime = dis.readUnsignedByte();
		aDMSOCLimit = dis.readUnsignedByte();
		aDCSSOCLimit = dis.readUnsignedByte();
		cOMMSSOCLimit = dis.readUnsignedByte();
		pLDSOCLimit = dis.readUnsignedByte();
		hTRCycleTime = dis.readUnsignedByte();
		heaterEmergencyOnTime = dis.readUnsignedByte();
		heaterEmergencyOffTime = dis.readUnsignedByte();
		aDMCycleTime = dis.readUnsignedByte();
		aDMBurnTime = dis.readUnsignedByte();
		aDMMaxCycles = dis.readUnsignedByte();
		aDMWaitTime1 = dis.readUnsignedByte();
		aDMWaitTime2 = dis.readUnsignedByte();
		aDMEnable = dis.readUnsignedByte();
		cOMMCycleTime = dis.readUnsignedByte();
		pLDCycleTime = dis.readUnsignedByte();
		pLDOperationMode = dis.readUnsignedByte();
		cameraResolution = dis.readUnsignedByte();
		cameraExposure = dis.readUnsignedByte();
		cameraPictureSaveTime = dis.readUnsignedByte();
		payloadEnable = dis.readUnsignedByte();
	}

	public int getcDHSCycleTime() {
		return cDHSCycleTime;
	}

	public void setcDHSCycleTime(int cDHSCycleTime) {
		this.cDHSCycleTime = cDHSCycleTime;
	}

	public int getcDHSWDTTime() {
		return cDHSWDTTime;
	}

	public void setcDHSWDTTime(int cDHSWDTTime) {
		this.cDHSWDTTime = cDHSWDTTime;
	}

	public int getaDMSOCLimit() {
		return aDMSOCLimit;
	}

	public void setaDMSOCLimit(int aDMSOCLimit) {
		this.aDMSOCLimit = aDMSOCLimit;
	}

	public int getaDCSSOCLimit() {
		return aDCSSOCLimit;
	}

	public void setaDCSSOCLimit(int aDCSSOCLimit) {
		this.aDCSSOCLimit = aDCSSOCLimit;
	}

	public int getcOMMSSOCLimit() {
		return cOMMSSOCLimit;
	}

	public void setcOMMSSOCLimit(int cOMMSSOCLimit) {
		this.cOMMSSOCLimit = cOMMSSOCLimit;
	}

	public int getpLDSOCLimit() {
		return pLDSOCLimit;
	}

	public void setpLDSOCLimit(int pLDSOCLimit) {
		this.pLDSOCLimit = pLDSOCLimit;
	}

	public int gethTRCycleTime() {
		return hTRCycleTime;
	}

	public void sethTRCycleTime(int hTRCycleTime) {
		this.hTRCycleTime = hTRCycleTime;
	}

	public int getHeaterEmergencyOnTime() {
		return heaterEmergencyOnTime;
	}

	public void setHeaterEmergencyOnTime(int heaterEmergencyOnTime) {
		this.heaterEmergencyOnTime = heaterEmergencyOnTime;
	}

	public int getHeaterEmergencyOffTime() {
		return heaterEmergencyOffTime;
	}

	public void setHeaterEmergencyOffTime(int heaterEmergencyOffTime) {
		this.heaterEmergencyOffTime = heaterEmergencyOffTime;
	}

	public int getaDMCycleTime() {
		return aDMCycleTime;
	}

	public void setaDMCycleTime(int aDMCycleTime) {
		this.aDMCycleTime = aDMCycleTime;
	}

	public int getaDMBurnTime() {
		return aDMBurnTime;
	}

	public void setaDMBurnTime(int aDMBurnTime) {
		this.aDMBurnTime = aDMBurnTime;
	}

	public int getaDMMaxCycles() {
		return aDMMaxCycles;
	}

	public void setaDMMaxCycles(int aDMMaxCycles) {
		this.aDMMaxCycles = aDMMaxCycles;
	}

	public int getaDMWaitTime1() {
		return aDMWaitTime1;
	}

	public void setaDMWaitTime1(int aDMWaitTime1) {
		this.aDMWaitTime1 = aDMWaitTime1;
	}

	public int getaDMWaitTime2() {
		return aDMWaitTime2;
	}

	public void setaDMWaitTime2(int aDMWaitTime2) {
		this.aDMWaitTime2 = aDMWaitTime2;
	}

	public int getaDMEnable() {
		return aDMEnable;
	}

	public void setaDMEnable(int aDMEnable) {
		this.aDMEnable = aDMEnable;
	}

	public int getcOMMCycleTime() {
		return cOMMCycleTime;
	}

	public void setcOMMCycleTime(int cOMMCycleTime) {
		this.cOMMCycleTime = cOMMCycleTime;
	}

	public int getpLDCycleTime() {
		return pLDCycleTime;
	}

	public void setpLDCycleTime(int pLDCycleTime) {
		this.pLDCycleTime = pLDCycleTime;
	}

	public int getpLDOperationMode() {
		return pLDOperationMode;
	}

	public void setpLDOperationMode(int pLDOperationMode) {
		this.pLDOperationMode = pLDOperationMode;
	}

	public int getCameraResolution() {
		return cameraResolution;
	}

	public void setCameraResolution(int cameraResolution) {
		this.cameraResolution = cameraResolution;
	}

	public int getCameraExposure() {
		return cameraExposure;
	}

	public void setCameraExposure(int cameraExposure) {
		this.cameraExposure = cameraExposure;
	}

	public int getCameraPictureSaveTime() {
		return cameraPictureSaveTime;
	}

	public void setCameraPictureSaveTime(int cameraPictureSaveTime) {
		this.cameraPictureSaveTime = cameraPictureSaveTime;
	}

	public int getPayloadEnable() {
		return payloadEnable;
	}

	public void setPayloadEnable(int payloadEnable) {
		this.payloadEnable = payloadEnable;
	}

}
