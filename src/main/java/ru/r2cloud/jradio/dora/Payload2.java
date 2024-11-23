package ru.r2cloud.jradio.dora;

import java.io.DataInputStream;
import java.io.IOException;

public class Payload2 {

	private int filterBankPowerCh3;
	private int filterBankPowerCh4;
	private int filterBankPowerCh5;
	private int filterBankPowerCh6;
	private int filterBankPowerCh7;
	private int filterBankPowerCh8;
	private int sdrPower;
	private int sdrBright1;
	private int sdrBright2;
	private int sdrBright3;
	private int sdrBright4;
	private int sdrBright5;
	private int sdrBright6;
	private int sdrBright7;
	private int sdrBright8;
	private int sdrBright9;
	private int sdrBright10;

	public Payload2() {
		// do nothing
	}

	public Payload2(DataInputStream dis) throws IOException {
		filterBankPowerCh3 = dis.readUnsignedByte();
		filterBankPowerCh4 = dis.readUnsignedByte();
		filterBankPowerCh5 = dis.readUnsignedByte();
		filterBankPowerCh6 = dis.readUnsignedByte();
		filterBankPowerCh7 = dis.readUnsignedByte();
		filterBankPowerCh8 = dis.readUnsignedByte();
		sdrPower = dis.readUnsignedShort();
		sdrBright1 = dis.readUnsignedShort();
		sdrBright2 = dis.readUnsignedShort();
		sdrBright3 = dis.readUnsignedShort();
		sdrBright4 = dis.readUnsignedShort();
		sdrBright5 = dis.readUnsignedShort();
		sdrBright6 = dis.readUnsignedShort();
		sdrBright7 = dis.readUnsignedShort();
		sdrBright8 = dis.readUnsignedShort();
		sdrBright9 = dis.readUnsignedShort();
		sdrBright10 = dis.readUnsignedShort();
	}

	public int getFilterBankPowerCh3() {
		return filterBankPowerCh3;
	}

	public void setFilterBankPowerCh3(int filterBankPowerCh3) {
		this.filterBankPowerCh3 = filterBankPowerCh3;
	}

	public int getFilterBankPowerCh4() {
		return filterBankPowerCh4;
	}

	public void setFilterBankPowerCh4(int filterBankPowerCh4) {
		this.filterBankPowerCh4 = filterBankPowerCh4;
	}

	public int getFilterBankPowerCh5() {
		return filterBankPowerCh5;
	}

	public void setFilterBankPowerCh5(int filterBankPowerCh5) {
		this.filterBankPowerCh5 = filterBankPowerCh5;
	}

	public int getFilterBankPowerCh6() {
		return filterBankPowerCh6;
	}

	public void setFilterBankPowerCh6(int filterBankPowerCh6) {
		this.filterBankPowerCh6 = filterBankPowerCh6;
	}

	public int getFilterBankPowerCh7() {
		return filterBankPowerCh7;
	}

	public void setFilterBankPowerCh7(int filterBankPowerCh7) {
		this.filterBankPowerCh7 = filterBankPowerCh7;
	}

	public int getFilterBankPowerCh8() {
		return filterBankPowerCh8;
	}

	public void setFilterBankPowerCh8(int filterBankPowerCh8) {
		this.filterBankPowerCh8 = filterBankPowerCh8;
	}

	public int getSdrPower() {
		return sdrPower;
	}

	public void setSdrPower(int sdrPower) {
		this.sdrPower = sdrPower;
	}

	public int getSdrBright1() {
		return sdrBright1;
	}

	public void setSdrBright1(int sdrBright1) {
		this.sdrBright1 = sdrBright1;
	}

	public int getSdrBright2() {
		return sdrBright2;
	}

	public void setSdrBright2(int sdrBright2) {
		this.sdrBright2 = sdrBright2;
	}

	public int getSdrBright3() {
		return sdrBright3;
	}

	public void setSdrBright3(int sdrBright3) {
		this.sdrBright3 = sdrBright3;
	}

	public int getSdrBright4() {
		return sdrBright4;
	}

	public void setSdrBright4(int sdrBright4) {
		this.sdrBright4 = sdrBright4;
	}

	public int getSdrBright5() {
		return sdrBright5;
	}

	public void setSdrBright5(int sdrBright5) {
		this.sdrBright5 = sdrBright5;
	}

	public int getSdrBright6() {
		return sdrBright6;
	}

	public void setSdrBright6(int sdrBright6) {
		this.sdrBright6 = sdrBright6;
	}

	public int getSdrBright7() {
		return sdrBright7;
	}

	public void setSdrBright7(int sdrBright7) {
		this.sdrBright7 = sdrBright7;
	}

	public int getSdrBright8() {
		return sdrBright8;
	}

	public void setSdrBright8(int sdrBright8) {
		this.sdrBright8 = sdrBright8;
	}

	public int getSdrBright9() {
		return sdrBright9;
	}

	public void setSdrBright9(int sdrBright9) {
		this.sdrBright9 = sdrBright9;
	}

	public int getSdrBright10() {
		return sdrBright10;
	}

	public void setSdrBright10(int sdrBright10) {
		this.sdrBright10 = sdrBright10;
	}

}
