package ru.r2cloud.jradio.quetzal1;

import java.io.DataInputStream;
import java.io.IOException;

public class Adcs {

	private float gyroX;
	private float gyroY;
	private float gyroZ;

	private float magX;
	private float magY;
	private float magZ;

	private float adc11;
	private float adc12;
	private float adc13;
	private float adc14;
	private float adc15;
	private float adc16;
	private float adc21;
	private float adc22;
	private float adc23;
	private float adc24;
	private float adc25;
	private float adc26;

	private byte bno055Temperature;
	private short tmp100Temperature;

	private boolean bno055Transmission;
	private boolean adc1Transmission;
	private boolean adc2Transmission;
	private boolean tmp100Transmission;

	public Adcs() {
		// do nothing
	}

	public Adcs(DataInputStream dis) throws IOException {
		gyroX = (dis.readUnsignedByte() - 127.5f) / 1.275f;
		gyroY = (dis.readUnsignedByte() - 127.5f) / 1.275f;
		gyroZ = (dis.readUnsignedByte() - 127.5f) / 1.275f;

		magX = (dis.readUnsignedByte() - 65536.0f / 2) / (8192.0f / 325);
		magY = (dis.readUnsignedByte() - 65536.0f / 2) / (8192.0f / 325);
		magZ = (dis.readUnsignedByte() - 65536.0f / 2) / (8192.0f / 325);

		adc11 = 77.27f * dis.readUnsignedByte();
		adc12 = 77.27f * dis.readUnsignedByte();
		adc13 = 77.27f * dis.readUnsignedByte();
		adc14 = 77.27f * dis.readUnsignedByte();
		adc15 = 77.27f * dis.readUnsignedByte();
		adc16 = 77.27f * dis.readUnsignedByte();
		adc21 = 77.27f * dis.readUnsignedByte();
		adc22 = 77.27f * dis.readUnsignedByte();
		adc23 = 77.27f * dis.readUnsignedByte();
		adc24 = 77.27f * dis.readUnsignedByte();
		adc25 = 77.27f * dis.readUnsignedByte();
		adc26 = 77.27f * dis.readUnsignedByte();

		bno055Temperature = dis.readByte();
		tmp100Temperature = dis.readShort();

		int raw = dis.readUnsignedByte();
		bno055Transmission = ((raw) & 0x1) > 0;
		adc1Transmission = ((raw >> 1) & 0x1) > 0;
		adc2Transmission = ((raw >> 2) & 0x1) > 0;
		tmp100Transmission = ((raw >> 3) & 0x1) > 0;
	}

	public float getGyroX() {
		return gyroX;
	}

	public void setGyroX(float gyroX) {
		this.gyroX = gyroX;
	}

	public float getGyroY() {
		return gyroY;
	}

	public void setGyroY(float gyroY) {
		this.gyroY = gyroY;
	}

	public float getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(float gyroZ) {
		this.gyroZ = gyroZ;
	}

	public float getMagX() {
		return magX;
	}

	public void setMagX(float magX) {
		this.magX = magX;
	}

	public float getMagY() {
		return magY;
	}

	public void setMagY(float magY) {
		this.magY = magY;
	}

	public float getMagZ() {
		return magZ;
	}

	public void setMagZ(float magZ) {
		this.magZ = magZ;
	}

	public float getAdc11() {
		return adc11;
	}

	public void setAdc11(float adc11) {
		this.adc11 = adc11;
	}

	public float getAdc12() {
		return adc12;
	}

	public void setAdc12(float adc12) {
		this.adc12 = adc12;
	}

	public float getAdc13() {
		return adc13;
	}

	public void setAdc13(float adc13) {
		this.adc13 = adc13;
	}

	public float getAdc14() {
		return adc14;
	}

	public void setAdc14(float adc14) {
		this.adc14 = adc14;
	}

	public float getAdc15() {
		return adc15;
	}

	public void setAdc15(float adc15) {
		this.adc15 = adc15;
	}

	public float getAdc16() {
		return adc16;
	}

	public void setAdc16(float adc16) {
		this.adc16 = adc16;
	}

	public float getAdc21() {
		return adc21;
	}

	public void setAdc21(float adc21) {
		this.adc21 = adc21;
	}

	public float getAdc22() {
		return adc22;
	}

	public void setAdc22(float adc22) {
		this.adc22 = adc22;
	}

	public float getAdc23() {
		return adc23;
	}

	public void setAdc23(float adc23) {
		this.adc23 = adc23;
	}

	public float getAdc24() {
		return adc24;
	}

	public void setAdc24(float adc24) {
		this.adc24 = adc24;
	}

	public float getAdc25() {
		return adc25;
	}

	public void setAdc25(float adc25) {
		this.adc25 = adc25;
	}

	public float getAdc26() {
		return adc26;
	}

	public void setAdc26(float adc26) {
		this.adc26 = adc26;
	}

	public byte getBno055Temperature() {
		return bno055Temperature;
	}

	public void setBno055Temperature(byte bno055Temperature) {
		this.bno055Temperature = bno055Temperature;
	}

	public short getTmp100Temperature() {
		return tmp100Temperature;
	}

	public void setTmp100Temperature(short tmp100Temperature) {
		this.tmp100Temperature = tmp100Temperature;
	}

	public boolean isBno055Transmission() {
		return bno055Transmission;
	}

	public void setBno055Transmission(boolean bno055Transmission) {
		this.bno055Transmission = bno055Transmission;
	}

	public boolean isAdc1Transmission() {
		return adc1Transmission;
	}

	public void setAdc1Transmission(boolean adc1Transmission) {
		this.adc1Transmission = adc1Transmission;
	}

	public boolean isAdc2Transmission() {
		return adc2Transmission;
	}

	public void setAdc2Transmission(boolean adc2Transmission) {
		this.adc2Transmission = adc2Transmission;
	}

	public boolean isTmp100Transmission() {
		return tmp100Transmission;
	}

	public void setTmp100Transmission(boolean tmp100Transmission) {
		this.tmp100Transmission = tmp100Transmission;
	}

}
