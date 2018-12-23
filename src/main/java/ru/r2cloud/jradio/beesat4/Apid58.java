package ru.r2cloud.jradio.beesat4;

import java.io.DataInputStream;
import java.io.IOException;

public class Apid58 {

	private float MAG1TY;     // MFS #1 Incline Temp Y
	private float MAG1TZ;     // MFS #1 Incline Temp Z
	private float MAG2TX;     // MFS #2 Incline Temp X
	private float MAG2TY;     // MFS #2 Incline Temp Y
	private float MAG2TZ;     // MFS #2 Incline Temp Z
	private float GYRMSX;     // Gyro Max Scale X
	private float GYRMSY;     // Gyro Max Scale Y
	private float GYRMSZ;     // Gyro Max Scale Z
	private float GYRMOX;     // Gyro Max Offset X
	private float GYRMOY;     // Gyro Max Offset Y
	private float GYRMOZ;     // Gyro Max Offset Z
	private float GYRMTX;     // Gyro Max Incline Temp X
	private float GYRMTY;     // Gyro Max Incline Temp Y
	private float GYRMTZ;     // Gyro Max Incline Temp Z
	private float GYRMST;     // Gyro Max Scale Temperature
	private float GYRMOT;     // Gyro Max Offset Temperature
	private float GYRLSX;     // Gyro L3G Scale X
	private float GYRLSY;     // Gyro L3G Scale Y
	private float GYRLSZ;     // Gyro L3G Scale Z
	private float GYRLOX;     // Gyro L3G Offset X
	private float GYRLOY;     // Gyro L3G Offset Y
	private float GYRLOZ;     // Gyro L3G Offset Z
	private float GYRLTX;     // Gyro L3G Incline Temp X
	private float GYRLTY;     // Gyro L3G Incline Temp Y
	private float GYRLTZ;     // Gyro L3G Incline Temp Z
	private float GYRLST;     // Gyro L3G Scale Temperature
	private float GYRLOT;     // Gyro L3G Offset Temperature

	public Apid58(DataInputStream dis) throws IOException {
		MAG1TY = dis.readFloat();
		MAG1TZ = dis.readFloat();
		MAG2TX = dis.readFloat();
		MAG2TY = dis.readFloat();
		MAG2TZ = dis.readFloat();
		GYRMSX = dis.readFloat();
		GYRMSY = dis.readFloat();
		GYRMSZ = dis.readFloat();
		GYRMOX = dis.readFloat();
		GYRMOY = dis.readFloat();
		GYRMOZ = dis.readFloat();
		GYRMTX = dis.readFloat();
		GYRMTY = dis.readFloat();
		GYRMTZ = dis.readFloat();
		GYRMST = dis.readFloat();
		GYRMOT = dis.readFloat();
		GYRLSX = dis.readFloat();
		GYRLSY = dis.readFloat();
		GYRLSZ = dis.readFloat();
		GYRLOX = dis.readFloat();
		GYRLOY = dis.readFloat();
		GYRLOZ = dis.readFloat();
		GYRLTX = dis.readFloat();
		GYRLTY = dis.readFloat();
		GYRLTZ = dis.readFloat();
		GYRLST = dis.readFloat();
		GYRLOT = dis.readFloat();
		dis.skipBytes(18);
	}

	public float getMAG1TY() {
		return MAG1TY;
	}

	public void setMAG1TY(float mAG1TY) {
		MAG1TY = mAG1TY;
	}

	public float getMAG1TZ() {
		return MAG1TZ;
	}

	public void setMAG1TZ(float mAG1TZ) {
		MAG1TZ = mAG1TZ;
	}

	public float getMAG2TX() {
		return MAG2TX;
	}

	public void setMAG2TX(float mAG2TX) {
		MAG2TX = mAG2TX;
	}

	public float getMAG2TY() {
		return MAG2TY;
	}

	public void setMAG2TY(float mAG2TY) {
		MAG2TY = mAG2TY;
	}

	public float getMAG2TZ() {
		return MAG2TZ;
	}

	public void setMAG2TZ(float mAG2TZ) {
		MAG2TZ = mAG2TZ;
	}

	public float getGYRMSX() {
		return GYRMSX;
	}

	public void setGYRMSX(float gYRMSX) {
		GYRMSX = gYRMSX;
	}

	public float getGYRMSY() {
		return GYRMSY;
	}

	public void setGYRMSY(float gYRMSY) {
		GYRMSY = gYRMSY;
	}

	public float getGYRMSZ() {
		return GYRMSZ;
	}

	public void setGYRMSZ(float gYRMSZ) {
		GYRMSZ = gYRMSZ;
	}

	public float getGYRMOX() {
		return GYRMOX;
	}

	public void setGYRMOX(float gYRMOX) {
		GYRMOX = gYRMOX;
	}

	public float getGYRMOY() {
		return GYRMOY;
	}

	public void setGYRMOY(float gYRMOY) {
		GYRMOY = gYRMOY;
	}

	public float getGYRMOZ() {
		return GYRMOZ;
	}

	public void setGYRMOZ(float gYRMOZ) {
		GYRMOZ = gYRMOZ;
	}

	public float getGYRMTX() {
		return GYRMTX;
	}

	public void setGYRMTX(float gYRMTX) {
		GYRMTX = gYRMTX;
	}

	public float getGYRMTY() {
		return GYRMTY;
	}

	public void setGYRMTY(float gYRMTY) {
		GYRMTY = gYRMTY;
	}

	public float getGYRMTZ() {
		return GYRMTZ;
	}

	public void setGYRMTZ(float gYRMTZ) {
		GYRMTZ = gYRMTZ;
	}

	public float getGYRMST() {
		return GYRMST;
	}

	public void setGYRMST(float gYRMST) {
		GYRMST = gYRMST;
	}

	public float getGYRMOT() {
		return GYRMOT;
	}

	public void setGYRMOT(float gYRMOT) {
		GYRMOT = gYRMOT;
	}

	public float getGYRLSX() {
		return GYRLSX;
	}

	public void setGYRLSX(float gYRLSX) {
		GYRLSX = gYRLSX;
	}

	public float getGYRLSY() {
		return GYRLSY;
	}

	public void setGYRLSY(float gYRLSY) {
		GYRLSY = gYRLSY;
	}

	public float getGYRLSZ() {
		return GYRLSZ;
	}

	public void setGYRLSZ(float gYRLSZ) {
		GYRLSZ = gYRLSZ;
	}

	public float getGYRLOX() {
		return GYRLOX;
	}

	public void setGYRLOX(float gYRLOX) {
		GYRLOX = gYRLOX;
	}

	public float getGYRLOY() {
		return GYRLOY;
	}

	public void setGYRLOY(float gYRLOY) {
		GYRLOY = gYRLOY;
	}

	public float getGYRLOZ() {
		return GYRLOZ;
	}

	public void setGYRLOZ(float gYRLOZ) {
		GYRLOZ = gYRLOZ;
	}

	public float getGYRLTX() {
		return GYRLTX;
	}

	public void setGYRLTX(float gYRLTX) {
		GYRLTX = gYRLTX;
	}

	public float getGYRLTY() {
		return GYRLTY;
	}

	public void setGYRLTY(float gYRLTY) {
		GYRLTY = gYRLTY;
	}

	public float getGYRLTZ() {
		return GYRLTZ;
	}

	public void setGYRLTZ(float gYRLTZ) {
		GYRLTZ = gYRLTZ;
	}

	public float getGYRLST() {
		return GYRLST;
	}

	public void setGYRLST(float gYRLST) {
		GYRLST = gYRLST;
	}

	public float getGYRLOT() {
		return GYRLOT;
	}

	public void setGYRLOT(float gYRLOT) {
		GYRLOT = gYRLOT;
	}

}
