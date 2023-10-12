package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Obc {

	private float tlmBoardTemp1;
	private float tlmBoardTemp2;
	private float tlmBoardTemp3;
	private float tlmVbatV;
	private float tlmVbatI;
	private float tlmVbatPlatV;
	private float tlm3v3PlatV;
	private float tlmVbatPeriphI;
	private float tlm3v3PeriphI;
	private float tlmVbatPeriphV;
	private float tlm3v3PeriphV;

	public Obc() {
		// do nothing
	}

	public Obc(LittleEndianDataInputStream dis) throws IOException {
		tlmBoardTemp1 = dis.readShort() / 100.0f;
		tlmBoardTemp2 = dis.readShort() / 100.0f;
		tlmBoardTemp3 = dis.readShort() / 100.0f;
		tlmVbatV = dis.readUnsignedShort() / 1000.0f;
		tlmVbatI = dis.readUnsignedShort() / 1000.0f;
		tlmVbatPlatV = dis.readUnsignedShort() / 1000.0f;
		tlm3v3PlatV = dis.readUnsignedShort() / 1000.0f;
		tlmVbatPeriphI = dis.readUnsignedShort() / 100.0f;
		tlm3v3PeriphI = dis.readUnsignedShort() / 100.0f;
		tlmVbatPeriphV = dis.readUnsignedShort() / 1000.0f;
		tlm3v3PeriphV = dis.readUnsignedShort() / 1000.0f;
	}

	public float getTlmBoardTemp1() {
		return tlmBoardTemp1;
	}

	public void setTlmBoardTemp1(float tlmBoardTemp1) {
		this.tlmBoardTemp1 = tlmBoardTemp1;
	}

	public float getTlmBoardTemp2() {
		return tlmBoardTemp2;
	}

	public void setTlmBoardTemp2(float tlmBoardTemp2) {
		this.tlmBoardTemp2 = tlmBoardTemp2;
	}

	public float getTlmBoardTemp3() {
		return tlmBoardTemp3;
	}

	public void setTlmBoardTemp3(float tlmBoardTemp3) {
		this.tlmBoardTemp3 = tlmBoardTemp3;
	}

	public float getTlmVbatV() {
		return tlmVbatV;
	}

	public void setTlmVbatV(float tlmVbatV) {
		this.tlmVbatV = tlmVbatV;
	}

	public float getTlmVbatI() {
		return tlmVbatI;
	}

	public void setTlmVbatI(float tlmVbatI) {
		this.tlmVbatI = tlmVbatI;
	}

	public float getTlmVbatPlatV() {
		return tlmVbatPlatV;
	}

	public void setTlmVbatPlatV(float tlmVbatPlatV) {
		this.tlmVbatPlatV = tlmVbatPlatV;
	}

	public float getTlm3v3PlatV() {
		return tlm3v3PlatV;
	}

	public void setTlm3v3PlatV(float tlm3v3PlatV) {
		this.tlm3v3PlatV = tlm3v3PlatV;
	}

	public float getTlmVbatPeriphI() {
		return tlmVbatPeriphI;
	}

	public void setTlmVbatPeriphI(float tlmVbatPeriphI) {
		this.tlmVbatPeriphI = tlmVbatPeriphI;
	}

	public float getTlm3v3PeriphI() {
		return tlm3v3PeriphI;
	}

	public void setTlm3v3PeriphI(float tlm3v3PeriphI) {
		this.tlm3v3PeriphI = tlm3v3PeriphI;
	}

	public float getTlmVbatPeriphV() {
		return tlmVbatPeriphV;
	}

	public void setTlmVbatPeriphV(float tlmVbatPeriphV) {
		this.tlmVbatPeriphV = tlmVbatPeriphV;
	}

	public float getTlm3v3PeriphV() {
		return tlm3v3PeriphV;
	}

	public void setTlm3v3PeriphV(float tlm3v3PeriphV) {
		this.tlm3v3PeriphV = tlm3v3PeriphV;
	}

}
