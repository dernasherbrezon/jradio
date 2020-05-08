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

	public int getCDHSCycleTime() {
		return cDHSCycleTime;
	}

	public void setCDHSCycleTime(int cDHSCycleTime) {
		this.cDHSCycleTime = cDHSCycleTime;
	}

	public int getCDHSWDTTime() {
		return cDHSWDTTime;
	}

	public void setCDHSWDTTime(int cDHSWDTTime) {
		this.cDHSWDTTime = cDHSWDTTime;
	}

	public int getADMSOCLimit() {
		return aDMSOCLimit;
	}

	public void setADMSOCLimit(int aDMSOCLimit) {
		this.aDMSOCLimit = aDMSOCLimit;
	}

	public int getADCSSOCLimit() {
		return aDCSSOCLimit;
	}

	public void setADCSSOCLimit(int aDCSSOCLimit) {
		this.aDCSSOCLimit = aDCSSOCLimit;
	}

	public int getCOMMSSOCLimit() {
		return cOMMSSOCLimit;
	}

	public void setCOMMSSOCLimit(int cOMMSSOCLimit) {
		this.cOMMSSOCLimit = cOMMSSOCLimit;
	}

	public int getPLDSOCLimit() {
		return pLDSOCLimit;
	}

	public void setPLDSOCLimit(int pLDSOCLimit) {
		this.pLDSOCLimit = pLDSOCLimit;
	}

	public int getHTRCycleTime() {
		return hTRCycleTime;
	}

	public void setHTRCycleTime(int hTRCycleTime) {
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

	public int getADMCycleTime() {
		return aDMCycleTime;
	}

	public void setADMCycleTime(int aDMCycleTime) {
		this.aDMCycleTime = aDMCycleTime;
	}

	public int getADMBurnTime() {
		return aDMBurnTime;
	}

	public void setADMBurnTime(int aDMBurnTime) {
		this.aDMBurnTime = aDMBurnTime;
	}

	public int getADMMaxCycles() {
		return aDMMaxCycles;
	}

	public void setADMMaxCycles(int aDMMaxCycles) {
		this.aDMMaxCycles = aDMMaxCycles;
	}

	public int getADMWaitTime1() {
		return aDMWaitTime1;
	}

	public void setADMWaitTime1(int aDMWaitTime1) {
		this.aDMWaitTime1 = aDMWaitTime1;
	}

	public int getADMWaitTime2() {
		return aDMWaitTime2;
	}

	public void setADMWaitTime2(int aDMWaitTime2) {
		this.aDMWaitTime2 = aDMWaitTime2;
	}

	public int getADMEnable() {
		return aDMEnable;
	}

	public void setADMEnable(int aDMEnable) {
		this.aDMEnable = aDMEnable;
	}

	public int getCOMMCycleTime() {
		return cOMMCycleTime;
	}

	public void setCOMMCycleTime(int cOMMCycleTime) {
		this.cOMMCycleTime = cOMMCycleTime;
	}

	public int getPLDCycleTime() {
		return pLDCycleTime;
	}

	public void setPLDCycleTime(int pLDCycleTime) {
		this.pLDCycleTime = pLDCycleTime;
	}

	public int getPLDOperationMode() {
		return pLDOperationMode;
	}

	public void setPLDOperationMode(int pLDOperationMode) {
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
