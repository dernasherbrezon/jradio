package ru.r2cloud.jradio.uvsqsat;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class MainboardHk {

	private long mainboardHkTime;
	private int hkPlus5V;
	private int hkMinus5V;
	private int hkMinusPolar;
	private int hkTempADC;
	private int hkFeePlusXVref;
	private int hkFeeMinusXVref;
	private int hkFeePlusYVref;
	private int hkFeeMinusYVref;

	public MainboardHk() {
		// do nothing
	}

	public MainboardHk(DataInputStream dis) throws IOException {
		mainboardHkTime = StreamUtils.readUnsignedInt(dis);
		hkPlus5V = dis.readUnsignedShort();
		hkMinus5V = dis.readUnsignedShort();
		hkMinusPolar = dis.readUnsignedShort();
		hkTempADC = dis.readUnsignedShort();
		hkFeePlusXVref = dis.readUnsignedShort();
		hkFeeMinusXVref = dis.readUnsignedShort();
		hkFeePlusYVref = dis.readUnsignedShort();
		hkFeeMinusYVref = dis.readUnsignedShort();
	}

	public long getMainboardHkTime() {
		return mainboardHkTime;
	}

	public void setMainboardHkTime(long mainboardHkTime) {
		this.mainboardHkTime = mainboardHkTime;
	}

	public int getHkPlus5V() {
		return hkPlus5V;
	}

	public void setHkPlus5V(int hkPlus5V) {
		this.hkPlus5V = hkPlus5V;
	}

	public int getHkMinus5V() {
		return hkMinus5V;
	}

	public void setHkMinus5V(int hkMinus5V) {
		this.hkMinus5V = hkMinus5V;
	}

	public int getHkMinusPolar() {
		return hkMinusPolar;
	}

	public void setHkMinusPolar(int hkMinusPolar) {
		this.hkMinusPolar = hkMinusPolar;
	}

	public int getHkTempADC() {
		return hkTempADC;
	}

	public void setHkTempADC(int hkTempADC) {
		this.hkTempADC = hkTempADC;
	}

	public int getHkFeePlusXVref() {
		return hkFeePlusXVref;
	}

	public void setHkFeePlusXVref(int hkFeePlusXVref) {
		this.hkFeePlusXVref = hkFeePlusXVref;
	}

	public int getHkFeeMinusXVref() {
		return hkFeeMinusXVref;
	}

	public void setHkFeeMinusXVref(int hkFeeMinusXVref) {
		this.hkFeeMinusXVref = hkFeeMinusXVref;
	}

	public int getHkFeePlusYVref() {
		return hkFeePlusYVref;
	}

	public void setHkFeePlusYVref(int hkFeePlusYVref) {
		this.hkFeePlusYVref = hkFeePlusYVref;
	}

	public int getHkFeeMinusYVref() {
		return hkFeeMinusYVref;
	}

	public void setHkFeeMinusYVref(int hkFeeMinusYVref) {
		this.hkFeeMinusYVref = hkFeeMinusYVref;
	}

}
