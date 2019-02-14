package ru.r2cloud.jradio.lume1;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class B5Temps {

	private float sunsTempPx;
	private float sunsTempNx;
	private float sunsTempPy;
	private float sunsTempNy;
	private float sunsTempPz;
	private float extMagTemp32;
	private float fssTempPx;
	private float fssTempNx;
	private float fssTempPy;
	private float fssTempNy;
	private float fssTempPz;
	private float gyroTemp32;
	private float aocsTempA;
	private float aocsTempB;
	private short temp0;
	private short temp1;
	private short temp2;
	private short temp3;
	private short temp4;
	private short temp5;
	private float obcTempA;
	private float obcTempB;
	private float gyroTemp;
	private float tempBrd;
	private float tempPa;

	public B5Temps(BitInputStream bis) throws IOException {
		sunsTempPx = bis.readFloat();
		sunsTempNx = bis.readFloat();
		sunsTempPy = bis.readFloat();
		sunsTempNy = bis.readFloat();
		sunsTempPz = bis.readFloat();
		bis.skipBits(32);
		extMagTemp32 = bis.readFloat();
		fssTempPx = bis.readFloat();
		fssTempNx = bis.readFloat();
		fssTempPy = bis.readFloat();
		fssTempNy = bis.readFloat();
		fssTempPz = bis.readFloat();
		bis.skipBits(32);
		bis.skipBits(32);
		bis.skipBits(32);
		gyroTemp32 = bis.readFloat();
		aocsTempA = bis.readShort() * 0.1f;
		aocsTempB = bis.readShort() * 0.1f;
		temp0 = bis.readShort();
		temp1 = bis.readShort();
		temp2 = bis.readShort();
		temp3 = bis.readShort();
		temp4 = bis.readShort();
		temp5 = bis.readShort();
		obcTempA = bis.readShort() * 0.1f;
		obcTempB = bis.readShort() * 0.1f;
		gyroTemp = bis.readFloat();
		tempBrd = bis.readShort() * 0.1f;
		tempPa = bis.readShort() * 0.1f;
	}

	public float getSunsTempPx() {
		return sunsTempPx;
	}

	public void setSunsTempPx(float sunsTempPx) {
		this.sunsTempPx = sunsTempPx;
	}

	public float getSunsTempNx() {
		return sunsTempNx;
	}

	public void setSunsTempNx(float sunsTempNx) {
		this.sunsTempNx = sunsTempNx;
	}

	public float getSunsTempPy() {
		return sunsTempPy;
	}

	public void setSunsTempPy(float sunsTempPy) {
		this.sunsTempPy = sunsTempPy;
	}

	public float getSunsTempNy() {
		return sunsTempNy;
	}

	public void setSunsTempNy(float sunsTempNy) {
		this.sunsTempNy = sunsTempNy;
	}

	public float getSunsTempPz() {
		return sunsTempPz;
	}

	public void setSunsTempPz(float sunsTempPz) {
		this.sunsTempPz = sunsTempPz;
	}

	public float getExtMagTemp32() {
		return extMagTemp32;
	}

	public void setExtMagTemp32(float extMagTemp32) {
		this.extMagTemp32 = extMagTemp32;
	}

	public float getFssTempPx() {
		return fssTempPx;
	}

	public void setFssTempPx(float fssTempPx) {
		this.fssTempPx = fssTempPx;
	}

	public float getFssTempNx() {
		return fssTempNx;
	}

	public void setFssTempNx(float fssTempNx) {
		this.fssTempNx = fssTempNx;
	}

	public float getFssTempPy() {
		return fssTempPy;
	}

	public void setFssTempPy(float fssTempPy) {
		this.fssTempPy = fssTempPy;
	}

	public float getFssTempNy() {
		return fssTempNy;
	}

	public void setFssTempNy(float fssTempNy) {
		this.fssTempNy = fssTempNy;
	}

	public float getFssTempPz() {
		return fssTempPz;
	}

	public void setFssTempPz(float fssTempPz) {
		this.fssTempPz = fssTempPz;
	}

	public float getGyroTemp32() {
		return gyroTemp32;
	}

	public void setGyroTemp32(float gyroTemp32) {
		this.gyroTemp32 = gyroTemp32;
	}

	public float getAocsTempA() {
		return aocsTempA;
	}

	public void setAocsTempA(float aocsTempA) {
		this.aocsTempA = aocsTempA;
	}

	public float getAocsTempB() {
		return aocsTempB;
	}

	public void setAocsTempB(float aocsTempB) {
		this.aocsTempB = aocsTempB;
	}

	public short getTemp0() {
		return temp0;
	}

	public void setTemp0(short temp0) {
		this.temp0 = temp0;
	}

	public short getTemp1() {
		return temp1;
	}

	public void setTemp1(short temp1) {
		this.temp1 = temp1;
	}

	public short getTemp2() {
		return temp2;
	}

	public void setTemp2(short temp2) {
		this.temp2 = temp2;
	}

	public short getTemp3() {
		return temp3;
	}

	public void setTemp3(short temp3) {
		this.temp3 = temp3;
	}

	public short getTemp4() {
		return temp4;
	}

	public void setTemp4(short temp4) {
		this.temp4 = temp4;
	}

	public short getTemp5() {
		return temp5;
	}

	public void setTemp5(short temp5) {
		this.temp5 = temp5;
	}

	public float getObcTempA() {
		return obcTempA;
	}

	public void setObcTempA(float obcTempA) {
		this.obcTempA = obcTempA;
	}

	public float getObcTempB() {
		return obcTempB;
	}

	public void setObcTempB(float obcTempB) {
		this.obcTempB = obcTempB;
	}

	public float getGyroTemp() {
		return gyroTemp;
	}

	public void setGyroTemp(float gyroTemp) {
		this.gyroTemp = gyroTemp;
	}

	public float getTempBrd() {
		return tempBrd;
	}

	public void setTempBrd(float tempBrd) {
		this.tempBrd = tempBrd;
	}

	public float getTempPa() {
		return tempPa;
	}

	public void setTempPa(float tempPa) {
		this.tempPa = tempPa;
	}

}
