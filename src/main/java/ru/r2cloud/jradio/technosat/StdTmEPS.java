package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmEPS {

	private byte nodeNo; // redundant node number
	private boolean rstEn; // the watchdog application is enabled to reset the node
	private byte botSlt; // currently running internal software slot
	private boolean synPps; // shall the node synchronize with the PPS signal
	private boolean disUTC; // shall the node distribute the UTC time at the next PPS signal
	private boolean dulBsy; // Indicates the state of the UploadManagers Flash Controller

	private boolean activeCan; // Active CAN bus
	private boolean pwrSTS00; // AOCS 0
	private boolean pwrSTS01; // AOCS 1
	private boolean pwrSTS02; // FOR 0
	private boolean pwrSTS03; // FOR 1
	private boolean pwrSTS04; // OBC 0
	private boolean pwrSTS05; // OBC 1
	private boolean pwrSTS06; // PDH 0
	private boolean pwrSTS07; // PDH 1
	private boolean pwrSTS08; // SOLID 0
	private boolean pwrSTS09; // SOLID 1
	private boolean pwrSTS10; // FDA 0
	private boolean pwrSTS11; // FDA 1
	private boolean pwrSTS12; // STELLA 0
	private boolean pwrSTS13; // STELLA 1
	private boolean pwrSTS14; // COM 0
	private boolean pwrSTS15; // COM 1
	private boolean pwrSTS16; // RW0 0
	private boolean pwrSTS18; // RW1 0
	private boolean pwrSTS20; // RW2 0
	private boolean pwrSTS22; // RW3 0
	private boolean solStatus0; // Solarring 0 status
	private boolean solStatus1; // Solarring 1 status
	private boolean regPwrStat0; // Reg Pwr 0 status
	private boolean regPwrStat1; // Reg Pwr 1 status
	private short solPwrInput; // Pwr Input via Solarpanels
	private short pwrIntoBat; // Pwr into/from the Batteries
	private int batEnrgAvail; // Energy left in Batteries
	private int pwrIntoSys; // Pwr into the system
	private float spVoltA; // PCU A sum point voltage
	private float spVoltB; // PCU B sum point voltage

	public StdTmEPS(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		nodeNo = (byte) (raw >> 7);
		rstEn = ((raw >> 6) & 0x1) > 0;
		botSlt = (byte) ((raw >> 3) & 0x7);
		synPps = ((raw >> 2) & 0x1) > 0;
		disUTC = ((raw >> 1) & 0x1) > 0;
		dulBsy = (raw & 0x1) > 0;
		dis.skipBytes(1);
		raw = dis.readUnsignedByte();
		// skip first 6 bits
		activeCan = ((raw >> 1) & 0x1) > 0;
		pwrSTS00 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		pwrSTS01 = ((raw >> 7) & 0x1) > 0;
		pwrSTS02 = ((raw >> 6) & 0x1) > 0;
		pwrSTS03 = ((raw >> 5) & 0x1) > 0;
		pwrSTS04 = ((raw >> 4) & 0x1) > 0;
		pwrSTS05 = ((raw >> 3) & 0x1) > 0;
		pwrSTS06 = ((raw >> 2) & 0x1) > 0;
		pwrSTS07 = ((raw >> 1) & 0x1) > 0;
		pwrSTS08 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		pwrSTS09 = ((raw >> 7) & 0x1) > 0;
		pwrSTS10 = ((raw >> 6) & 0x1) > 0;
		pwrSTS11 = ((raw >> 5) & 0x1) > 0;
		pwrSTS12 = ((raw >> 4) & 0x1) > 0;
		pwrSTS13 = ((raw >> 3) & 0x1) > 0;
		pwrSTS14 = ((raw >> 2) & 0x1) > 0;
		pwrSTS15 = ((raw >> 1) & 0x1) > 0;
		pwrSTS16 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		pwrSTS18 = ((raw >> 7) & 0x1) > 0;
		pwrSTS20 = ((raw >> 6) & 0x1) > 0;
		pwrSTS22 = ((raw >> 5) & 0x1) > 0;
		solStatus0 = ((raw >> 4) & 0x1) > 0;
		solStatus1 = ((raw >> 3) & 0x1) > 0;
		regPwrStat0 = ((raw >> 2) & 0x1) > 0;
		regPwrStat1 = ((raw >> 1) & 0x1) > 0;
		// skip last bit

		solPwrInput = dis.readShort();
		pwrIntoBat = dis.readShort();
		batEnrgAvail = dis.readUnsignedShort();
		pwrIntoSys = dis.readUnsignedShort();
		spVoltA = dis.readUnsignedByte() * 0.098039216f;
		spVoltB = dis.readUnsignedByte() * 0.098039216f;
	}

	public byte getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(byte nodeNo) {
		this.nodeNo = nodeNo;
	}

	public boolean isRstEn() {
		return rstEn;
	}

	public void setRstEn(boolean rstEn) {
		this.rstEn = rstEn;
	}

	public byte getBotSlt() {
		return botSlt;
	}

	public void setBotSlt(byte botSlt) {
		this.botSlt = botSlt;
	}

	public boolean isSynPps() {
		return synPps;
	}

	public void setSynPps(boolean synPps) {
		this.synPps = synPps;
	}

	public boolean isDisUTC() {
		return disUTC;
	}

	public void setDisUTC(boolean disUTC) {
		this.disUTC = disUTC;
	}

	public boolean isDulBsy() {
		return dulBsy;
	}

	public void setDulBsy(boolean dulBsy) {
		this.dulBsy = dulBsy;
	}

	public boolean isActiveCan() {
		return activeCan;
	}

	public void setActiveCan(boolean activeCan) {
		this.activeCan = activeCan;
	}

	public boolean isPwrSTS00() {
		return pwrSTS00;
	}

	public void setPwrSTS00(boolean pwrSTS00) {
		this.pwrSTS00 = pwrSTS00;
	}

	public boolean isPwrSTS01() {
		return pwrSTS01;
	}

	public void setPwrSTS01(boolean pwrSTS01) {
		this.pwrSTS01 = pwrSTS01;
	}

	public boolean isPwrSTS02() {
		return pwrSTS02;
	}

	public void setPwrSTS02(boolean pwrSTS02) {
		this.pwrSTS02 = pwrSTS02;
	}

	public boolean isPwrSTS03() {
		return pwrSTS03;
	}

	public void setPwrSTS03(boolean pwrSTS03) {
		this.pwrSTS03 = pwrSTS03;
	}

	public boolean isPwrSTS04() {
		return pwrSTS04;
	}

	public void setPwrSTS04(boolean pwrSTS04) {
		this.pwrSTS04 = pwrSTS04;
	}

	public boolean isPwrSTS05() {
		return pwrSTS05;
	}

	public void setPwrSTS05(boolean pwrSTS05) {
		this.pwrSTS05 = pwrSTS05;
	}

	public boolean isPwrSTS06() {
		return pwrSTS06;
	}

	public void setPwrSTS06(boolean pwrSTS06) {
		this.pwrSTS06 = pwrSTS06;
	}

	public boolean isPwrSTS07() {
		return pwrSTS07;
	}

	public void setPwrSTS07(boolean pwrSTS07) {
		this.pwrSTS07 = pwrSTS07;
	}

	public boolean isPwrSTS08() {
		return pwrSTS08;
	}

	public void setPwrSTS08(boolean pwrSTS08) {
		this.pwrSTS08 = pwrSTS08;
	}

	public boolean isPwrSTS09() {
		return pwrSTS09;
	}

	public void setPwrSTS09(boolean pwrSTS09) {
		this.pwrSTS09 = pwrSTS09;
	}

	public boolean isPwrSTS10() {
		return pwrSTS10;
	}

	public void setPwrSTS10(boolean pwrSTS10) {
		this.pwrSTS10 = pwrSTS10;
	}

	public boolean isPwrSTS11() {
		return pwrSTS11;
	}

	public void setPwrSTS11(boolean pwrSTS11) {
		this.pwrSTS11 = pwrSTS11;
	}

	public boolean isPwrSTS12() {
		return pwrSTS12;
	}

	public void setPwrSTS12(boolean pwrSTS12) {
		this.pwrSTS12 = pwrSTS12;
	}

	public boolean isPwrSTS13() {
		return pwrSTS13;
	}

	public void setPwrSTS13(boolean pwrSTS13) {
		this.pwrSTS13 = pwrSTS13;
	}

	public boolean isPwrSTS14() {
		return pwrSTS14;
	}

	public void setPwrSTS14(boolean pwrSTS14) {
		this.pwrSTS14 = pwrSTS14;
	}

	public boolean isPwrSTS15() {
		return pwrSTS15;
	}

	public void setPwrSTS15(boolean pwrSTS15) {
		this.pwrSTS15 = pwrSTS15;
	}

	public boolean isPwrSTS16() {
		return pwrSTS16;
	}

	public void setPwrSTS16(boolean pwrSTS16) {
		this.pwrSTS16 = pwrSTS16;
	}

	public boolean isPwrSTS18() {
		return pwrSTS18;
	}

	public void setPwrSTS18(boolean pwrSTS18) {
		this.pwrSTS18 = pwrSTS18;
	}

	public boolean isPwrSTS20() {
		return pwrSTS20;
	}

	public void setPwrSTS20(boolean pwrSTS20) {
		this.pwrSTS20 = pwrSTS20;
	}

	public boolean isPwrSTS22() {
		return pwrSTS22;
	}

	public void setPwrSTS22(boolean pwrSTS22) {
		this.pwrSTS22 = pwrSTS22;
	}

	public boolean isSolStatus0() {
		return solStatus0;
	}

	public void setSolStatus0(boolean solStatus0) {
		this.solStatus0 = solStatus0;
	}

	public boolean isSolStatus1() {
		return solStatus1;
	}

	public void setSolStatus1(boolean solStatus1) {
		this.solStatus1 = solStatus1;
	}

	public boolean isRegPwrStat0() {
		return regPwrStat0;
	}

	public void setRegPwrStat0(boolean regPwrStat0) {
		this.regPwrStat0 = regPwrStat0;
	}

	public boolean isRegPwrStat1() {
		return regPwrStat1;
	}

	public void setRegPwrStat1(boolean regPwrStat1) {
		this.regPwrStat1 = regPwrStat1;
	}

	public short getSolPwrInput() {
		return solPwrInput;
	}

	public void setSolPwrInput(short solPwrInput) {
		this.solPwrInput = solPwrInput;
	}

	public short getPwrIntoBat() {
		return pwrIntoBat;
	}

	public void setPwrIntoBat(short pwrIntoBat) {
		this.pwrIntoBat = pwrIntoBat;
	}

	public int getBatEnrgAvail() {
		return batEnrgAvail;
	}

	public void setBatEnrgAvail(int batEnrgAvail) {
		this.batEnrgAvail = batEnrgAvail;
	}

	public int getPwrIntoSys() {
		return pwrIntoSys;
	}

	public void setPwrIntoSys(int pwrIntoSys) {
		this.pwrIntoSys = pwrIntoSys;
	}

	public float getSpVoltA() {
		return spVoltA;
	}

	public void setSpVoltA(float spVoltA) {
		this.spVoltA = spVoltA;
	}

	public float getSpVoltB() {
		return spVoltB;
	}

	public void setSpVoltB(float spVoltB) {
		this.spVoltB = spVoltB;
	}

}
