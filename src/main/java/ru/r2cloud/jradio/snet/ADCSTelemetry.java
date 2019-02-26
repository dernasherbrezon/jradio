package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;

public class ADCSTelemetry {

	private byte modeChkListThisStepActive;
	private int attDetFinalState;
	private int sensorArrayAvailStatusGA;
	private int sensorArrayAvailStatusMFSA;
	private int sensorArrayAvailStatusSUSEA;
	private int actArrayAvailStatusRWA;
	private int actArrayAvailStatusMATA;
	private int attDetMfsDistCorrMode;
	private int attDetSuseDistCorrMode;

	private boolean attDetTrackIGRFDeltaB;
	private boolean attDetSuseAlbedoTracking;
	private boolean suse1Albedo;
	private boolean suse2Albedo;
	private boolean suse3Albedo;
	private boolean suse4Albedo;
	private boolean suse5Albedo;
	private boolean suse6Albedo;
	private boolean attDetAutoVirtualizeMFSA;
	private boolean attDetAutoVirtualizeSUSEA;
	private boolean attDetNarrowVectors;
	private boolean attDetMismatchingVectors;

	private float omegaXOptimal;
	private float omegaYOptimal;
	private float omegaZOptimal;
	private float magXOptimal;
	private float magYOptimal;
	private float magZOptimal;
	private float sunXOptimal;
	private float sunYOptimal;
	private float sunZOptimal;
	private float ctrlTorqueRWAx;
	private float ctrlTorqueRWAy;
	private float ctrlTorqueRWAz;
	private float ctrlMagMomentMATAx;
	private float ctrlMagMomentMATAy;
	private float ctrlMagMomentMATAz;
	private float readTorqueRWx;
	private float readTorqueRWy;
	private float readTorqueRWz;

	private int readRotSpeedRWx;
	private int readRotSpeedRWy;
	private int readRotSpeedRWz;
	private float sgp4LatXPEF;
	private float sgp4LongYPEF;
	private float sgp4AltPEF;
	private float attitudeErrorAngle;
	private int targetDataDistance;
	private int targetDataControlIsActive;

	public ADCSTelemetry() {
		// do nothing
	}

	public ADCSTelemetry(LittleEndianBitInputStream bis) throws IOException {
		modeChkListThisStepActive = bis.readByte();
		attDetFinalState = bis.readUnsignedByte();
		sensorArrayAvailStatusGA = bis.readUnsignedByte();
		sensorArrayAvailStatusMFSA = bis.readUnsignedByte();
		sensorArrayAvailStatusSUSEA = bis.readUnsignedByte();
		actArrayAvailStatusRWA = bis.readUnsignedByte();
		actArrayAvailStatusMATA = bis.readUnsignedByte();
		attDetMfsDistCorrMode = bis.readUnsignedByte();
		attDetSuseDistCorrMode = bis.readUnsignedByte();

		bis.skipBits(4);
		attDetTrackIGRFDeltaB = bis.readBoolean();
		attDetSuseAlbedoTracking = bis.readBoolean();
		suse1Albedo = bis.readBoolean();
		suse2Albedo = bis.readBoolean();
		suse3Albedo = bis.readBoolean();
		suse4Albedo = bis.readBoolean();
		suse5Albedo = bis.readBoolean();
		suse6Albedo = bis.readBoolean();
		attDetAutoVirtualizeMFSA = bis.readBoolean();
		attDetAutoVirtualizeSUSEA = bis.readBoolean();
		attDetNarrowVectors = bis.readBoolean();
		attDetMismatchingVectors = bis.readBoolean();

		omegaXOptimal = bis.readShort() / 260.0f;
		omegaYOptimal = bis.readShort() / 260.0f;
		omegaZOptimal = bis.readShort() / 260.0f;
		magXOptimal = bis.readShort() / 0.1f;
		magYOptimal = bis.readShort() / 0.1f;
		magZOptimal = bis.readShort() / 0.1f;
		sunXOptimal = bis.readShort() / 32000.0f;
		sunYOptimal = bis.readShort() / 32000.0f;
		sunZOptimal = bis.readShort() / 32000.0f;
		ctrlTorqueRWAx = bis.readUnsignedByte() / 38484.0f;
		ctrlTorqueRWAy = bis.readUnsignedByte() / 38484.0f;
		ctrlTorqueRWAz = bis.readUnsignedByte() / 38484.0f;
		ctrlMagMomentMATAx = bis.readUnsignedByte() / 127.0f;
		ctrlMagMomentMATAy = bis.readUnsignedByte() / 127.0f;
		ctrlMagMomentMATAz = bis.readUnsignedByte() / 127.0f;
		readTorqueRWx = bis.readUnsignedShort() / 9696969.0f;
		readTorqueRWy = bis.readUnsignedShort() / 9696969.0f;
		readTorqueRWz = bis.readUnsignedShort() / 9696969.0f;
		readRotSpeedRWx = bis.readUnsignedShort();
		readRotSpeedRWy = bis.readUnsignedShort();
		readRotSpeedRWz = bis.readUnsignedShort();
		sgp4LatXPEF = bis.readUnsignedShort() / 355.0f;
		sgp4LongYPEF = bis.readUnsignedShort() / 177.0f;
		sgp4AltPEF = bis.readUnsignedByte() / 0.25f;
		attitudeErrorAngle = bis.readUnsignedShort() / 177.0f;
		targetDataDistance = bis.readUnsignedShort();
		targetDataControlIsActive = bis.readUnsignedByte();
	}

	public byte getModeChkListThisStepActive() {
		return modeChkListThisStepActive;
	}

	public void setModeChkListThisStepActive(byte modeChkListThisStepActive) {
		this.modeChkListThisStepActive = modeChkListThisStepActive;
	}

	public int getAttDetFinalState() {
		return attDetFinalState;
	}

	public void setAttDetFinalState(int attDetFinalState) {
		this.attDetFinalState = attDetFinalState;
	}

	public int getSensorArrayAvailStatusGA() {
		return sensorArrayAvailStatusGA;
	}

	public void setSensorArrayAvailStatusGA(int sensorArrayAvailStatusGA) {
		this.sensorArrayAvailStatusGA = sensorArrayAvailStatusGA;
	}

	public int getSensorArrayAvailStatusMFSA() {
		return sensorArrayAvailStatusMFSA;
	}

	public void setSensorArrayAvailStatusMFSA(int sensorArrayAvailStatusMFSA) {
		this.sensorArrayAvailStatusMFSA = sensorArrayAvailStatusMFSA;
	}

	public int getSensorArrayAvailStatusSUSEA() {
		return sensorArrayAvailStatusSUSEA;
	}

	public void setSensorArrayAvailStatusSUSEA(int sensorArrayAvailStatusSUSEA) {
		this.sensorArrayAvailStatusSUSEA = sensorArrayAvailStatusSUSEA;
	}

	public int getActArrayAvailStatusRWA() {
		return actArrayAvailStatusRWA;
	}

	public void setActArrayAvailStatusRWA(int actArrayAvailStatusRWA) {
		this.actArrayAvailStatusRWA = actArrayAvailStatusRWA;
	}

	public int getActArrayAvailStatusMATA() {
		return actArrayAvailStatusMATA;
	}

	public void setActArrayAvailStatusMATA(int actArrayAvailStatusMATA) {
		this.actArrayAvailStatusMATA = actArrayAvailStatusMATA;
	}

	public int getAttDetMfsDistCorrMode() {
		return attDetMfsDistCorrMode;
	}

	public void setAttDetMfsDistCorrMode(int attDetMfsDistCorrMode) {
		this.attDetMfsDistCorrMode = attDetMfsDistCorrMode;
	}

	public int getAttDetSuseDistCorrMode() {
		return attDetSuseDistCorrMode;
	}

	public void setAttDetSuseDistCorrMode(int attDetSuseDistCorrMode) {
		this.attDetSuseDistCorrMode = attDetSuseDistCorrMode;
	}

	public boolean isAttDetTrackIGRFDeltaB() {
		return attDetTrackIGRFDeltaB;
	}

	public void setAttDetTrackIGRFDeltaB(boolean attDetTrackIGRFDeltaB) {
		this.attDetTrackIGRFDeltaB = attDetTrackIGRFDeltaB;
	}

	public boolean isAttDetSuseAlbedoTracking() {
		return attDetSuseAlbedoTracking;
	}

	public void setAttDetSuseAlbedoTracking(boolean attDetSuseAlbedoTracking) {
		this.attDetSuseAlbedoTracking = attDetSuseAlbedoTracking;
	}

	public boolean isSuse1Albedo() {
		return suse1Albedo;
	}

	public void setSuse1Albedo(boolean suse1Albedo) {
		this.suse1Albedo = suse1Albedo;
	}

	public boolean isSuse2Albedo() {
		return suse2Albedo;
	}

	public void setSuse2Albedo(boolean suse2Albedo) {
		this.suse2Albedo = suse2Albedo;
	}

	public boolean isSuse3Albedo() {
		return suse3Albedo;
	}

	public void setSuse3Albedo(boolean suse3Albedo) {
		this.suse3Albedo = suse3Albedo;
	}

	public boolean isSuse4Albedo() {
		return suse4Albedo;
	}

	public void setSuse4Albedo(boolean suse4Albedo) {
		this.suse4Albedo = suse4Albedo;
	}

	public boolean isSuse5Albedo() {
		return suse5Albedo;
	}

	public void setSuse5Albedo(boolean suse5Albedo) {
		this.suse5Albedo = suse5Albedo;
	}

	public boolean isSuse6Albedo() {
		return suse6Albedo;
	}

	public void setSuse6Albedo(boolean suse6Albedo) {
		this.suse6Albedo = suse6Albedo;
	}

	public boolean isAttDetAutoVirtualizeMFSA() {
		return attDetAutoVirtualizeMFSA;
	}

	public void setAttDetAutoVirtualizeMFSA(boolean attDetAutoVirtualizeMFSA) {
		this.attDetAutoVirtualizeMFSA = attDetAutoVirtualizeMFSA;
	}

	public boolean isAttDetAutoVirtualizeSUSEA() {
		return attDetAutoVirtualizeSUSEA;
	}

	public void setAttDetAutoVirtualizeSUSEA(boolean attDetAutoVirtualizeSUSEA) {
		this.attDetAutoVirtualizeSUSEA = attDetAutoVirtualizeSUSEA;
	}

	public boolean isAttDetNarrowVectors() {
		return attDetNarrowVectors;
	}

	public void setAttDetNarrowVectors(boolean attDetNarrowVectors) {
		this.attDetNarrowVectors = attDetNarrowVectors;
	}

	public boolean isAttDetMismatchingVectors() {
		return attDetMismatchingVectors;
	}

	public void setAttDetMismatchingVectors(boolean attDetMismatchingVectors) {
		this.attDetMismatchingVectors = attDetMismatchingVectors;
	}

	public float getOmegaXOptimal() {
		return omegaXOptimal;
	}

	public void setOmegaXOptimal(float omegaXOptimal) {
		this.omegaXOptimal = omegaXOptimal;
	}

	public float getOmegaYOptimal() {
		return omegaYOptimal;
	}

	public void setOmegaYOptimal(float omegaYOptimal) {
		this.omegaYOptimal = omegaYOptimal;
	}

	public float getOmegaZOptimal() {
		return omegaZOptimal;
	}

	public void setOmegaZOptimal(float omegaZOptimal) {
		this.omegaZOptimal = omegaZOptimal;
	}

	public float getMagXOptimal() {
		return magXOptimal;
	}

	public void setMagXOptimal(float magXOptimal) {
		this.magXOptimal = magXOptimal;
	}

	public float getMagYOptimal() {
		return magYOptimal;
	}

	public void setMagYOptimal(float magYOptimal) {
		this.magYOptimal = magYOptimal;
	}

	public float getMagZOptimal() {
		return magZOptimal;
	}

	public void setMagZOptimal(float magZOptimal) {
		this.magZOptimal = magZOptimal;
	}

	public float getSunXOptimal() {
		return sunXOptimal;
	}

	public void setSunXOptimal(float sunXOptimal) {
		this.sunXOptimal = sunXOptimal;
	}

	public float getSunYOptimal() {
		return sunYOptimal;
	}

	public void setSunYOptimal(float sunYOptimal) {
		this.sunYOptimal = sunYOptimal;
	}

	public float getSunZOptimal() {
		return sunZOptimal;
	}

	public void setSunZOptimal(float sunZOptimal) {
		this.sunZOptimal = sunZOptimal;
	}

	public float getCtrlTorqueRWAx() {
		return ctrlTorqueRWAx;
	}

	public void setCtrlTorqueRWAx(float ctrlTorqueRWAx) {
		this.ctrlTorqueRWAx = ctrlTorqueRWAx;
	}

	public float getCtrlTorqueRWAy() {
		return ctrlTorqueRWAy;
	}

	public void setCtrlTorqueRWAy(float ctrlTorqueRWAy) {
		this.ctrlTorqueRWAy = ctrlTorqueRWAy;
	}

	public float getCtrlTorqueRWAz() {
		return ctrlTorqueRWAz;
	}

	public void setCtrlTorqueRWAz(float ctrlTorqueRWAz) {
		this.ctrlTorqueRWAz = ctrlTorqueRWAz;
	}

	public float getCtrlMagMomentMATAx() {
		return ctrlMagMomentMATAx;
	}

	public void setCtrlMagMomentMATAx(float ctrlMagMomentMATAx) {
		this.ctrlMagMomentMATAx = ctrlMagMomentMATAx;
	}

	public float getCtrlMagMomentMATAy() {
		return ctrlMagMomentMATAy;
	}

	public void setCtrlMagMomentMATAy(float ctrlMagMomentMATAy) {
		this.ctrlMagMomentMATAy = ctrlMagMomentMATAy;
	}

	public float getCtrlMagMomentMATAz() {
		return ctrlMagMomentMATAz;
	}

	public void setCtrlMagMomentMATAz(float ctrlMagMomentMATAz) {
		this.ctrlMagMomentMATAz = ctrlMagMomentMATAz;
	}

	public float getReadTorqueRWx() {
		return readTorqueRWx;
	}

	public void setReadTorqueRWx(float readTorqueRWx) {
		this.readTorqueRWx = readTorqueRWx;
	}

	public float getReadTorqueRWy() {
		return readTorqueRWy;
	}

	public void setReadTorqueRWy(float readTorqueRWy) {
		this.readTorqueRWy = readTorqueRWy;
	}

	public float getReadTorqueRWz() {
		return readTorqueRWz;
	}

	public void setReadTorqueRWz(float readTorqueRWz) {
		this.readTorqueRWz = readTorqueRWz;
	}

	public int getReadRotSpeedRWx() {
		return readRotSpeedRWx;
	}

	public void setReadRotSpeedRWx(int readRotSpeedRWx) {
		this.readRotSpeedRWx = readRotSpeedRWx;
	}

	public int getReadRotSpeedRWy() {
		return readRotSpeedRWy;
	}

	public void setReadRotSpeedRWy(int readRotSpeedRWy) {
		this.readRotSpeedRWy = readRotSpeedRWy;
	}

	public int getReadRotSpeedRWz() {
		return readRotSpeedRWz;
	}

	public void setReadRotSpeedRWz(int readRotSpeedRWz) {
		this.readRotSpeedRWz = readRotSpeedRWz;
	}

	public float getSgp4LatXPEF() {
		return sgp4LatXPEF;
	}

	public void setSgp4LatXPEF(float sgp4LatXPEF) {
		this.sgp4LatXPEF = sgp4LatXPEF;
	}

	public float getSgp4LongYPEF() {
		return sgp4LongYPEF;
	}

	public void setSgp4LongYPEF(float sgp4LongYPEF) {
		this.sgp4LongYPEF = sgp4LongYPEF;
	}

	public float getSgp4AltPEF() {
		return sgp4AltPEF;
	}

	public void setSgp4AltPEF(float sgp4AltPEF) {
		this.sgp4AltPEF = sgp4AltPEF;
	}

	public float getAttitudeErrorAngle() {
		return attitudeErrorAngle;
	}

	public void setAttitudeErrorAngle(float attitudeErrorAngle) {
		this.attitudeErrorAngle = attitudeErrorAngle;
	}

	public int getTargetDataDistance() {
		return targetDataDistance;
	}

	public void setTargetDataDistance(int targetDataDistance) {
		this.targetDataDistance = targetDataDistance;
	}

	public int getTargetDataControlIsActive() {
		return targetDataControlIsActive;
	}

	public void setTargetDataControlIsActive(int targetDataControlIsActive) {
		this.targetDataControlIsActive = targetDataControlIsActive;
	}

}
