package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmEPS {

	private byte NODENO;
	private boolean RST_EN;
	private byte BOTSLT;
	private boolean SYNPPS;
	private boolean DISUTC;
	private boolean DULBSY;

	private boolean ACTIVE_CAN;
	private boolean PWRSTS00;
	private boolean PWRSTS01;
	private boolean PWRSTS02;
	private boolean PWRSTS03;
	private boolean PWRSTS04;
	private boolean PWRSTS05;
	private boolean PWRSTS06;
	private boolean PWRSTS07;
	private boolean PWRSTS08;
	private boolean PWRSTS09;
	private boolean PWRSTS10;
	private boolean PWRSTS11;
	private boolean PWRSTS12;
	private boolean PWRSTS13;
	private boolean PWRSTS14;
	private boolean PWRSTS15;
	private boolean PWRSTS16;
	private boolean PWRSTS18;
	private boolean PWRSTS20;
	private boolean PWRSTS22;
	private boolean SOL_STATUS_0;
	private boolean SOL_STATUS_1;
	private boolean REG_PWR_STAT_0;
	private boolean REG_PWR_STAT_1;
	private short SOL_PWR_INPUT;
	private short PWR_INTO_BAT;
	private int BAT_ENRG_AVAIL;
	private int PWR_INTO_SYS;
	private float SP_VOLT_A;
	private float SP_VOLT_B;

	public StdTmEPS(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		NODENO = (byte) (raw >> 7);
		RST_EN = ((raw >> 6) & 0x1) > 0;
		BOTSLT = (byte) ((raw >> 3) & 0x7);
		SYNPPS = ((raw >> 2) & 0x1) > 0;
		DISUTC = ((raw >> 1) & 0x1) > 0;
		DULBSY = ((raw >> 0) & 0x1) > 0;
		dis.skipBytes(1);
		raw = dis.readUnsignedByte();
		// skip first 6 bits
		ACTIVE_CAN = ((raw >> 1) & 0x1) > 0;
		PWRSTS00 = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		PWRSTS01 = ((raw >> 7) & 0x1) > 0;
		PWRSTS02 = ((raw >> 6) & 0x1) > 0;
		PWRSTS03 = ((raw >> 5) & 0x1) > 0;
		PWRSTS04 = ((raw >> 4) & 0x1) > 0;
		PWRSTS05 = ((raw >> 3) & 0x1) > 0;
		PWRSTS06 = ((raw >> 2) & 0x1) > 0;
		PWRSTS07 = ((raw >> 1) & 0x1) > 0;
		PWRSTS08 = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		PWRSTS09 = ((raw >> 7) & 0x1) > 0;
		PWRSTS10 = ((raw >> 6) & 0x1) > 0;
		PWRSTS11 = ((raw >> 5) & 0x1) > 0;
		PWRSTS12 = ((raw >> 4) & 0x1) > 0;
		PWRSTS13 = ((raw >> 3) & 0x1) > 0;
		PWRSTS14 = ((raw >> 2) & 0x1) > 0;
		PWRSTS15 = ((raw >> 1) & 0x1) > 0;
		PWRSTS16 = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		PWRSTS18 = ((raw >> 7) & 0x1) > 0;
		PWRSTS20 = ((raw >> 6) & 0x1) > 0;
		PWRSTS22 = ((raw >> 5) & 0x1) > 0;
		SOL_STATUS_0 = ((raw >> 4) & 0x1) > 0;
		SOL_STATUS_1 = ((raw >> 3) & 0x1) > 0;
		REG_PWR_STAT_0 = ((raw >> 2) & 0x1) > 0;
		REG_PWR_STAT_1 = ((raw >> 1) & 0x1) > 0;
		// skip last bit

		SOL_PWR_INPUT = dis.readShort();
		PWR_INTO_BAT = dis.readShort();
		BAT_ENRG_AVAIL = dis.readUnsignedShort();
		PWR_INTO_SYS = dis.readUnsignedShort();
		SP_VOLT_A = dis.readUnsignedByte() * 0.098039216f;
		SP_VOLT_B = dis.readUnsignedByte() * 0.098039216f;
	}

	public byte getNODENO() {
		return NODENO;
	}

	public void setNODENO(byte nODENO) {
		NODENO = nODENO;
	}

	public boolean isRST_EN() {
		return RST_EN;
	}

	public void setRST_EN(boolean rST_EN) {
		RST_EN = rST_EN;
	}

	public byte getBOTSLT() {
		return BOTSLT;
	}

	public void setBOTSLT(byte bOTSLT) {
		BOTSLT = bOTSLT;
	}

	public boolean isSYNPPS() {
		return SYNPPS;
	}

	public void setSYNPPS(boolean sYNPPS) {
		SYNPPS = sYNPPS;
	}

	public boolean isDISUTC() {
		return DISUTC;
	}

	public void setDISUTC(boolean dISUTC) {
		DISUTC = dISUTC;
	}

	public boolean isDULBSY() {
		return DULBSY;
	}

	public void setDULBSY(boolean dULBSY) {
		DULBSY = dULBSY;
	}

	public boolean isACTIVE_CAN() {
		return ACTIVE_CAN;
	}

	public void setACTIVE_CAN(boolean aCTIVE_CAN) {
		ACTIVE_CAN = aCTIVE_CAN;
	}

	public boolean isPWRSTS00() {
		return PWRSTS00;
	}

	public void setPWRSTS00(boolean pWRSTS00) {
		PWRSTS00 = pWRSTS00;
	}

	public boolean isPWRSTS01() {
		return PWRSTS01;
	}

	public void setPWRSTS01(boolean pWRSTS01) {
		PWRSTS01 = pWRSTS01;
	}

	public boolean isPWRSTS02() {
		return PWRSTS02;
	}

	public void setPWRSTS02(boolean pWRSTS02) {
		PWRSTS02 = pWRSTS02;
	}

	public boolean isPWRSTS03() {
		return PWRSTS03;
	}

	public void setPWRSTS03(boolean pWRSTS03) {
		PWRSTS03 = pWRSTS03;
	}

	public boolean isPWRSTS04() {
		return PWRSTS04;
	}

	public void setPWRSTS04(boolean pWRSTS04) {
		PWRSTS04 = pWRSTS04;
	}

	public boolean isPWRSTS05() {
		return PWRSTS05;
	}

	public void setPWRSTS05(boolean pWRSTS05) {
		PWRSTS05 = pWRSTS05;
	}

	public boolean isPWRSTS06() {
		return PWRSTS06;
	}

	public void setPWRSTS06(boolean pWRSTS06) {
		PWRSTS06 = pWRSTS06;
	}

	public boolean isPWRSTS07() {
		return PWRSTS07;
	}

	public void setPWRSTS07(boolean pWRSTS07) {
		PWRSTS07 = pWRSTS07;
	}

	public boolean isPWRSTS08() {
		return PWRSTS08;
	}

	public void setPWRSTS08(boolean pWRSTS08) {
		PWRSTS08 = pWRSTS08;
	}

	public boolean isPWRSTS09() {
		return PWRSTS09;
	}

	public void setPWRSTS09(boolean pWRSTS09) {
		PWRSTS09 = pWRSTS09;
	}

	public boolean isPWRSTS10() {
		return PWRSTS10;
	}

	public void setPWRSTS10(boolean pWRSTS10) {
		PWRSTS10 = pWRSTS10;
	}

	public boolean isPWRSTS11() {
		return PWRSTS11;
	}

	public void setPWRSTS11(boolean pWRSTS11) {
		PWRSTS11 = pWRSTS11;
	}

	public boolean isPWRSTS12() {
		return PWRSTS12;
	}

	public void setPWRSTS12(boolean pWRSTS12) {
		PWRSTS12 = pWRSTS12;
	}

	public boolean isPWRSTS13() {
		return PWRSTS13;
	}

	public void setPWRSTS13(boolean pWRSTS13) {
		PWRSTS13 = pWRSTS13;
	}

	public boolean isPWRSTS14() {
		return PWRSTS14;
	}

	public void setPWRSTS14(boolean pWRSTS14) {
		PWRSTS14 = pWRSTS14;
	}

	public boolean isPWRSTS15() {
		return PWRSTS15;
	}

	public void setPWRSTS15(boolean pWRSTS15) {
		PWRSTS15 = pWRSTS15;
	}

	public boolean isPWRSTS16() {
		return PWRSTS16;
	}

	public void setPWRSTS16(boolean pWRSTS16) {
		PWRSTS16 = pWRSTS16;
	}

	public boolean isPWRSTS18() {
		return PWRSTS18;
	}

	public void setPWRSTS18(boolean pWRSTS18) {
		PWRSTS18 = pWRSTS18;
	}

	public boolean isPWRSTS20() {
		return PWRSTS20;
	}

	public void setPWRSTS20(boolean pWRSTS20) {
		PWRSTS20 = pWRSTS20;
	}

	public boolean isPWRSTS22() {
		return PWRSTS22;
	}

	public void setPWRSTS22(boolean pWRSTS22) {
		PWRSTS22 = pWRSTS22;
	}

	public boolean isSOL_STATUS_0() {
		return SOL_STATUS_0;
	}

	public void setSOL_STATUS_0(boolean sOL_STATUS_0) {
		SOL_STATUS_0 = sOL_STATUS_0;
	}

	public boolean isSOL_STATUS_1() {
		return SOL_STATUS_1;
	}

	public void setSOL_STATUS_1(boolean sOL_STATUS_1) {
		SOL_STATUS_1 = sOL_STATUS_1;
	}

	public boolean isREG_PWR_STAT_0() {
		return REG_PWR_STAT_0;
	}

	public void setREG_PWR_STAT_0(boolean rEG_PWR_STAT_0) {
		REG_PWR_STAT_0 = rEG_PWR_STAT_0;
	}

	public boolean isREG_PWR_STAT_1() {
		return REG_PWR_STAT_1;
	}

	public void setREG_PWR_STAT_1(boolean rEG_PWR_STAT_1) {
		REG_PWR_STAT_1 = rEG_PWR_STAT_1;
	}

	public short getSOL_PWR_INPUT() {
		return SOL_PWR_INPUT;
	}

	public void setSOL_PWR_INPUT(short sOL_PWR_INPUT) {
		SOL_PWR_INPUT = sOL_PWR_INPUT;
	}

	public short getPWR_INTO_BAT() {
		return PWR_INTO_BAT;
	}

	public void setPWR_INTO_BAT(short pWR_INTO_BAT) {
		PWR_INTO_BAT = pWR_INTO_BAT;
	}

	public int getBAT_ENRG_AVAIL() {
		return BAT_ENRG_AVAIL;
	}

	public void setBAT_ENRG_AVAIL(int bAT_ENRG_AVAIL) {
		BAT_ENRG_AVAIL = bAT_ENRG_AVAIL;
	}

	public int getPWR_INTO_SYS() {
		return PWR_INTO_SYS;
	}

	public void setPWR_INTO_SYS(int pWR_INTO_SYS) {
		PWR_INTO_SYS = pWR_INTO_SYS;
	}

	public float getSP_VOLT_A() {
		return SP_VOLT_A;
	}

	public void setSP_VOLT_A(float sP_VOLT_A) {
		SP_VOLT_A = sP_VOLT_A;
	}

	public float getSP_VOLT_B() {
		return SP_VOLT_B;
	}

	public void setSP_VOLT_B(float sP_VOLT_B) {
		SP_VOLT_B = sP_VOLT_B;
	}

}
