package ru.r2cloud.jradio.snet;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianBitInputStream;

public class ADCSTelemetry {

	private byte iModeChkListThisStepActive;
	private int iAttDetFinalState;
	private int iSensorArrayAvailStatusGA;
	private int iSensorArrayAvailStatusMFSA;
	private int iSensorArrayAvailStatusSUSEA;
	private int iActArrayAvailStatusRWA;
	private int iActArrayAvailStatusMATA;
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
	private float dCtrlTorqueRWAx;
	private float dCtrlTorqueRWAy;
	private float dCtrlTorqueRWAz;
	private float dCtrlMagMomentMATAx;
	private float dCtrlMagMomentMATAy;
	private float dCtrlMagMomentMATAz;
	private float iReadTorqueRWx;
	private float iReadTorqueRWy;
	private float iReadTorqueRWz;

	private int iReadRotSpeedRWx;
	private int iReadRotSpeedRWy;
	private int iReadRotSpeedRWz;
	private float sgp4LatXPEF;
	private float sgp4LongYPEF;
	private float sgp4AltPEF;
	private float attitudeErrorAngle;
	private int targetDataDistance;
	private int targetDataControlIsActive;

	public ADCSTelemetry(LittleEndianBitInputStream bis) throws IOException {
		iModeChkListThisStepActive = bis.readByte();
		iAttDetFinalState = bis.readUnsignedByte();
		iSensorArrayAvailStatusGA = bis.readUnsignedByte();
		iSensorArrayAvailStatusMFSA = bis.readUnsignedByte();
		iSensorArrayAvailStatusSUSEA = bis.readUnsignedByte();
		iActArrayAvailStatusRWA = bis.readUnsignedByte();
		iActArrayAvailStatusMATA = bis.readUnsignedByte();
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
		dCtrlTorqueRWAx = bis.readUnsignedByte() / 38484.0f;
		dCtrlTorqueRWAy = bis.readUnsignedByte() / 38484.0f;
		dCtrlTorqueRWAz = bis.readUnsignedByte() / 38484.0f;
		dCtrlMagMomentMATAx = bis.readUnsignedByte() / 127.0f;
		dCtrlMagMomentMATAy = bis.readUnsignedByte() / 127.0f;
		dCtrlMagMomentMATAz = bis.readUnsignedByte() / 127.0f;
		iReadTorqueRWx = bis.readUnsignedShort() / 9696969.0f;
		iReadTorqueRWy = bis.readUnsignedShort() / 9696969.0f;
		iReadTorqueRWz = bis.readUnsignedShort() / 9696969.0f;
		iReadRotSpeedRWx = bis.readUnsignedShort();
		iReadRotSpeedRWy = bis.readUnsignedShort();
		iReadRotSpeedRWz = bis.readUnsignedShort();
		sgp4LatXPEF = bis.readUnsignedShort() / 355.0f;
		sgp4LongYPEF = bis.readUnsignedShort() / 177.0f;
		sgp4AltPEF = bis.readUnsignedByte() / 0.25f;
		attitudeErrorAngle = bis.readUnsignedShort() / 177.0f;
		targetDataDistance = bis.readUnsignedShort();
		targetDataControlIsActive = bis.readUnsignedByte();
	}

	public byte getiModeChkListThisStepActive() {
		return iModeChkListThisStepActive;
	}

	public void setiModeChkListThisStepActive(byte iModeChkListThisStepActive) {
		this.iModeChkListThisStepActive = iModeChkListThisStepActive;
	}

	public int getiAttDetFinalState() {
		return iAttDetFinalState;
	}

	public void setiAttDetFinalState(int iAttDetFinalState) {
		this.iAttDetFinalState = iAttDetFinalState;
	}

	public int getiSensorArrayAvailStatusGA() {
		return iSensorArrayAvailStatusGA;
	}

	public void setiSensorArrayAvailStatusGA(int iSensorArrayAvailStatusGA) {
		this.iSensorArrayAvailStatusGA = iSensorArrayAvailStatusGA;
	}

	public int getiSensorArrayAvailStatusMFSA() {
		return iSensorArrayAvailStatusMFSA;
	}

	public void setiSensorArrayAvailStatusMFSA(int iSensorArrayAvailStatusMFSA) {
		this.iSensorArrayAvailStatusMFSA = iSensorArrayAvailStatusMFSA;
	}

	public int getiSensorArrayAvailStatusSUSEA() {
		return iSensorArrayAvailStatusSUSEA;
	}

	public void setiSensorArrayAvailStatusSUSEA(int iSensorArrayAvailStatusSUSEA) {
		this.iSensorArrayAvailStatusSUSEA = iSensorArrayAvailStatusSUSEA;
	}

	public int getiActArrayAvailStatusRWA() {
		return iActArrayAvailStatusRWA;
	}

	public void setiActArrayAvailStatusRWA(int iActArrayAvailStatusRWA) {
		this.iActArrayAvailStatusRWA = iActArrayAvailStatusRWA;
	}

	public int getiActArrayAvailStatusMATA() {
		return iActArrayAvailStatusMATA;
	}

	public void setiActArrayAvailStatusMATA(int iActArrayAvailStatusMATA) {
		this.iActArrayAvailStatusMATA = iActArrayAvailStatusMATA;
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

	public float getdCtrlTorqueRWAx() {
		return dCtrlTorqueRWAx;
	}

	public void setdCtrlTorqueRWAx(float dCtrlTorqueRWAx) {
		this.dCtrlTorqueRWAx = dCtrlTorqueRWAx;
	}

	public float getdCtrlTorqueRWAy() {
		return dCtrlTorqueRWAy;
	}

	public void setdCtrlTorqueRWAy(float dCtrlTorqueRWAy) {
		this.dCtrlTorqueRWAy = dCtrlTorqueRWAy;
	}

	public float getdCtrlTorqueRWAz() {
		return dCtrlTorqueRWAz;
	}

	public void setdCtrlTorqueRWAz(float dCtrlTorqueRWAz) {
		this.dCtrlTorqueRWAz = dCtrlTorqueRWAz;
	}

	public float getdCtrlMagMomentMATAx() {
		return dCtrlMagMomentMATAx;
	}

	public void setdCtrlMagMomentMATAx(float dCtrlMagMomentMATAx) {
		this.dCtrlMagMomentMATAx = dCtrlMagMomentMATAx;
	}

	public float getdCtrlMagMomentMATAy() {
		return dCtrlMagMomentMATAy;
	}

	public void setdCtrlMagMomentMATAy(float dCtrlMagMomentMATAy) {
		this.dCtrlMagMomentMATAy = dCtrlMagMomentMATAy;
	}

	public float getdCtrlMagMomentMATAz() {
		return dCtrlMagMomentMATAz;
	}

	public void setdCtrlMagMomentMATAz(float dCtrlMagMomentMATAz) {
		this.dCtrlMagMomentMATAz = dCtrlMagMomentMATAz;
	}

	public float getiReadTorqueRWx() {
		return iReadTorqueRWx;
	}

	public void setiReadTorqueRWx(float iReadTorqueRWx) {
		this.iReadTorqueRWx = iReadTorqueRWx;
	}

	public float getiReadTorqueRWy() {
		return iReadTorqueRWy;
	}

	public void setiReadTorqueRWy(float iReadTorqueRWy) {
		this.iReadTorqueRWy = iReadTorqueRWy;
	}

	public float getiReadTorqueRWz() {
		return iReadTorqueRWz;
	}

	public void setiReadTorqueRWz(float iReadTorqueRWz) {
		this.iReadTorqueRWz = iReadTorqueRWz;
	}

	public int getiReadRotSpeedRWx() {
		return iReadRotSpeedRWx;
	}

	public void setiReadRotSpeedRWx(int iReadRotSpeedRWx) {
		this.iReadRotSpeedRWx = iReadRotSpeedRWx;
	}

	public int getiReadRotSpeedRWy() {
		return iReadRotSpeedRWy;
	}

	public void setiReadRotSpeedRWy(int iReadRotSpeedRWy) {
		this.iReadRotSpeedRWy = iReadRotSpeedRWy;
	}

	public int getiReadRotSpeedRWz() {
		return iReadRotSpeedRWz;
	}

	public void setiReadRotSpeedRWz(int iReadRotSpeedRWz) {
		this.iReadRotSpeedRWz = iReadRotSpeedRWz;
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
