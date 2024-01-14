package ru.r2cloud.jradio.kafasat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Acu {

	private int vin0;
	private int vin1;
	private int vin2;
	private int vin3;
	private int vin4;
	private int vin5;
	private short cin0;
	private short cin1;
	private short cin2;
	private short cin3;
	private short cin4;
	private short cin5;

	public Acu() {
		// do nothing
	}

	public Acu(LittleEndianDataInputStream dis) throws IOException {
		vin0 = dis.readUnsignedShort();
		vin1 = dis.readUnsignedShort();
		vin2 = dis.readUnsignedShort();
		vin3 = dis.readUnsignedShort();
		vin4 = dis.readUnsignedShort();
		vin5 = dis.readUnsignedShort();
		cin0 = dis.readShort();
		cin1 = dis.readShort();
		cin2 = dis.readShort();
		cin3 = dis.readShort();
		cin4 = dis.readShort();
		cin5 = dis.readShort();
	}

	public int getVin0() {
		return vin0;
	}

	public void setVin0(int vin0) {
		this.vin0 = vin0;
	}

	public int getVin1() {
		return vin1;
	}

	public void setVin1(int vin1) {
		this.vin1 = vin1;
	}

	public int getVin2() {
		return vin2;
	}

	public void setVin2(int vin2) {
		this.vin2 = vin2;
	}

	public int getVin3() {
		return vin3;
	}

	public void setVin3(int vin3) {
		this.vin3 = vin3;
	}

	public int getVin4() {
		return vin4;
	}

	public void setVin4(int vin4) {
		this.vin4 = vin4;
	}

	public int getVin5() {
		return vin5;
	}

	public void setVin5(int vin5) {
		this.vin5 = vin5;
	}

	public short getCin0() {
		return cin0;
	}

	public void setCin0(short cin0) {
		this.cin0 = cin0;
	}

	public short getCin1() {
		return cin1;
	}

	public void setCin1(short cin1) {
		this.cin1 = cin1;
	}

	public short getCin2() {
		return cin2;
	}

	public void setCin2(short cin2) {
		this.cin2 = cin2;
	}

	public short getCin3() {
		return cin3;
	}

	public void setCin3(short cin3) {
		this.cin3 = cin3;
	}

	public short getCin4() {
		return cin4;
	}

	public void setCin4(short cin4) {
		this.cin4 = cin4;
	}

	public short getCin5() {
		return cin5;
	}

	public void setCin5(short cin5) {
		this.cin5 = cin5;
	}

}
