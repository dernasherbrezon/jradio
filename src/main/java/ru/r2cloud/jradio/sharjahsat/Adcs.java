package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Adcs {

	private int adcsState;
	private float satPosLlhLat;
	private float satPosLlhLon;
	private float satPosLlhAlt;
	private float estmAttAngleYaw;
	private float estmAttAnglePitch;
	private float estmAttAngleRoll;
	private float estmAngRateYaw;
	private float estmAngRatePitch;
	private float estmAngRateRoll;
	private byte[] dataB64T;

	public Adcs() {
		// do nothing
	}

	public Adcs(LittleEndianDataInputStream dis) throws IOException {
		adcsState = dis.readUnsignedByte();
		satPosLlhLat = dis.readShort() * 0.01f;
		satPosLlhLon = dis.readShort() * 0.01f;
		satPosLlhAlt = dis.readShort() * 0.01f;
		estmAttAngleYaw = dis.readShort() * 0.01f;
		estmAttAnglePitch = dis.readShort() * 0.01f;
		estmAttAngleRoll = dis.readShort() * 0.01f;
		estmAngRateYaw = dis.readShort() * 0.01f;
		estmAngRatePitch = dis.readShort() * 0.01f;
		estmAngRateRoll = dis.readShort() * 0.01f;
		dataB64T = new byte[18];
		dis.readFully(dataB64T);
	}

	public int getAdcsState() {
		return adcsState;
	}

	public void setAdcsState(int adcsState) {
		this.adcsState = adcsState;
	}

	public float getSatPosLlhLat() {
		return satPosLlhLat;
	}

	public void setSatPosLlhLat(float satPosLlhLat) {
		this.satPosLlhLat = satPosLlhLat;
	}

	public float getSatPosLlhLon() {
		return satPosLlhLon;
	}

	public void setSatPosLlhLon(float satPosLlhLon) {
		this.satPosLlhLon = satPosLlhLon;
	}

	public float getSatPosLlhAlt() {
		return satPosLlhAlt;
	}

	public void setSatPosLlhAlt(float satPosLlhAlt) {
		this.satPosLlhAlt = satPosLlhAlt;
	}

	public float getEstmAttAngleYaw() {
		return estmAttAngleYaw;
	}

	public void setEstmAttAngleYaw(float estmAttAngleYaw) {
		this.estmAttAngleYaw = estmAttAngleYaw;
	}

	public float getEstmAttAnglePitch() {
		return estmAttAnglePitch;
	}

	public void setEstmAttAnglePitch(float estmAttAnglePitch) {
		this.estmAttAnglePitch = estmAttAnglePitch;
	}

	public float getEstmAttAngleRoll() {
		return estmAttAngleRoll;
	}

	public void setEstmAttAngleRoll(float estmAttAngleRoll) {
		this.estmAttAngleRoll = estmAttAngleRoll;
	}

	public float getEstmAngRateYaw() {
		return estmAngRateYaw;
	}

	public void setEstmAngRateYaw(float estmAngRateYaw) {
		this.estmAngRateYaw = estmAngRateYaw;
	}

	public float getEstmAngRatePitch() {
		return estmAngRatePitch;
	}

	public void setEstmAngRatePitch(float estmAngRatePitch) {
		this.estmAngRatePitch = estmAngRatePitch;
	}

	public float getEstmAngRateRoll() {
		return estmAngRateRoll;
	}

	public void setEstmAngRateRoll(float estmAngRateRoll) {
		this.estmAngRateRoll = estmAngRateRoll;
	}

	public byte[] getDataB64T() {
		return dataB64T;
	}

	public void setDataB64T(byte[] dataB64T) {
		this.dataB64T = dataB64T;
	}

}
