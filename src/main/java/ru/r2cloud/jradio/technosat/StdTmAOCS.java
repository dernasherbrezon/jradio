package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class StdTmAOCS {

	private byte NODENO;                     //   redundant node number
	private boolean RST_EN;                  //   the watchdog application is enabled to reset the node
	private byte BOTSLT;                     //   currently running internal software slot
	private boolean SYNPPS;                  //   shall the node synchronize with the PPS signal
	private boolean DISUTC;                  //   shall the node distribute the UTC time at the next PPS signal
	private boolean DULBSY;                  //   Indicates the state of the UploadManagers Flash Controller
	private AcsMode ACS_MODE;                //
	private boolean MFS_RECEIVED;            //   True, if data from the magnetic field sensor system was received in the current control cycle
	private boolean SSS_RECEIVED;            //   True, if data from the Sun sensor system was received in the current control cycle
	private boolean GYR_RECEIVED;            //   True, if data from the MEMS gyro system was received in the current control cycle
	private boolean FOR_RECEIVED;            //   True, if data from the fiber optic rate sensor system was received in the current control cycle
	private boolean STR_RECEIVED;            //   True, if data from the star tracker system was received in the current control cycle
	private boolean MTS_RECEIVED;            //   True, if data from the magnetic torquer system was received in the current control cycle      
	private boolean RW0_RECEIVED;            //   True, if data from the reaction wheel 0 was received in the current control cycle             
	private boolean RW1_RECEIVED;            //   True, if data from the reaction wheel 1 was received in the current control cycle             
	private boolean RW2_RECEIVED;            //   True, if data from the reaction wheel 2 was received in the current control cycle             
	private boolean RW3_RECEIVED;            //   True, if data from the reaction wheel 3 was received in the current control cycle             
	private float STD_Q_S;                   //   
	private float STD_Q_X;                   //
	private float STD_Q_Y;                   //
	private float STD_Q_Z;                   //
	private float STD_RATE_X;                //
	private float STD_RATE_Y;                //
	private float STD_RATE_Z;                //
	private int STD_R_X;                     //
	private int STD_R_Y;                     //
	private int STD_R_Z;                     //

	public StdTmAOCS(DataInputStream dis) throws IOException {
		int raw = dis.readUnsignedByte();
		NODENO = (byte) (raw >> 7);
		RST_EN = ((raw >> 6) & 0x1) > 0;
		BOTSLT = (byte) ((raw >> 3) & 0x7);
		SYNPPS = ((raw >> 2) & 0x1) > 0;
		DISUTC = ((raw >> 1) & 0x1) > 0;
		DULBSY = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ACS_MODE = AcsMode.valueOfCode(raw >> 3);
		MFS_RECEIVED = ((raw >> 2) & 0x1) > 0;
		SSS_RECEIVED = ((raw >> 1) & 0x1) > 0;
		GYR_RECEIVED = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		FOR_RECEIVED = ((raw >> 7) & 0x1) > 0;
		STR_RECEIVED = ((raw >> 6) & 0x1) > 0;
		// skip 1 bit
		MTS_RECEIVED = ((raw >> 4) & 0x1) > 0;
		RW0_RECEIVED = ((raw >> 3) & 0x1) > 0;
		RW1_RECEIVED = ((raw >> 2) & 0x1) > 0;
		RW2_RECEIVED = ((raw >> 1) & 0x1) > 0;
		RW3_RECEIVED = ((raw >> 0) & 0x1) > 0;

		STD_Q_S = dis.readShort() * 0.0001f;
		STD_Q_X = dis.readShort() * 0.0001f;
		STD_Q_Y = dis.readShort() * 0.0001f;
		STD_Q_Z = dis.readShort() * 0.0001f;

		STD_RATE_X = dis.readByte() * 0.2f;
		STD_RATE_Y = dis.readByte() * 0.2f;
		STD_RATE_Z = dis.readByte() * 0.2f;
		STD_R_X = dis.readByte() * 100;
		STD_R_Y = dis.readByte() * 100;
		STD_R_Z = dis.readByte() * 100;
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

	public AcsMode getACS_MODE() {
		return ACS_MODE;
	}

	public void setACS_MODE(AcsMode aCS_MODE) {
		ACS_MODE = aCS_MODE;
	}

	public boolean isMFS_RECEIVED() {
		return MFS_RECEIVED;
	}

	public void setMFS_RECEIVED(boolean mFS_RECEIVED) {
		MFS_RECEIVED = mFS_RECEIVED;
	}

	public boolean isSSS_RECEIVED() {
		return SSS_RECEIVED;
	}

	public void setSSS_RECEIVED(boolean sSS_RECEIVED) {
		SSS_RECEIVED = sSS_RECEIVED;
	}

	public boolean isGYR_RECEIVED() {
		return GYR_RECEIVED;
	}

	public void setGYR_RECEIVED(boolean gYR_RECEIVED) {
		GYR_RECEIVED = gYR_RECEIVED;
	}

	public boolean isFOR_RECEIVED() {
		return FOR_RECEIVED;
	}

	public void setFOR_RECEIVED(boolean fOR_RECEIVED) {
		FOR_RECEIVED = fOR_RECEIVED;
	}

	public boolean isSTR_RECEIVED() {
		return STR_RECEIVED;
	}

	public void setSTR_RECEIVED(boolean sTR_RECEIVED) {
		STR_RECEIVED = sTR_RECEIVED;
	}

	public boolean isMTS_RECEIVED() {
		return MTS_RECEIVED;
	}

	public void setMTS_RECEIVED(boolean mTS_RECEIVED) {
		MTS_RECEIVED = mTS_RECEIVED;
	}

	public boolean isRW0_RECEIVED() {
		return RW0_RECEIVED;
	}

	public void setRW0_RECEIVED(boolean rW0_RECEIVED) {
		RW0_RECEIVED = rW0_RECEIVED;
	}

	public boolean isRW1_RECEIVED() {
		return RW1_RECEIVED;
	}

	public void setRW1_RECEIVED(boolean rW1_RECEIVED) {
		RW1_RECEIVED = rW1_RECEIVED;
	}

	public boolean isRW2_RECEIVED() {
		return RW2_RECEIVED;
	}

	public void setRW2_RECEIVED(boolean rW2_RECEIVED) {
		RW2_RECEIVED = rW2_RECEIVED;
	}

	public boolean isRW3_RECEIVED() {
		return RW3_RECEIVED;
	}

	public void setRW3_RECEIVED(boolean rW3_RECEIVED) {
		RW3_RECEIVED = rW3_RECEIVED;
	}

	public float getSTD_Q_S() {
		return STD_Q_S;
	}

	public void setSTD_Q_S(float sTD_Q_S) {
		STD_Q_S = sTD_Q_S;
	}

	public float getSTD_Q_X() {
		return STD_Q_X;
	}

	public void setSTD_Q_X(float sTD_Q_X) {
		STD_Q_X = sTD_Q_X;
	}

	public float getSTD_Q_Y() {
		return STD_Q_Y;
	}

	public void setSTD_Q_Y(float sTD_Q_Y) {
		STD_Q_Y = sTD_Q_Y;
	}

	public float getSTD_Q_Z() {
		return STD_Q_Z;
	}

	public void setSTD_Q_Z(float sTD_Q_Z) {
		STD_Q_Z = sTD_Q_Z;
	}

	public float getSTD_RATE_X() {
		return STD_RATE_X;
	}

	public void setSTD_RATE_X(float sTD_RATE_X) {
		STD_RATE_X = sTD_RATE_X;
	}

	public float getSTD_RATE_Y() {
		return STD_RATE_Y;
	}

	public void setSTD_RATE_Y(float sTD_RATE_Y) {
		STD_RATE_Y = sTD_RATE_Y;
	}

	public float getSTD_RATE_Z() {
		return STD_RATE_Z;
	}

	public void setSTD_RATE_Z(float sTD_RATE_Z) {
		STD_RATE_Z = sTD_RATE_Z;
	}

	public int getSTD_R_X() {
		return STD_R_X;
	}

	public void setSTD_R_X(int sTD_R_X) {
		STD_R_X = sTD_R_X;
	}

	public int getSTD_R_Y() {
		return STD_R_Y;
	}

	public void setSTD_R_Y(int sTD_R_Y) {
		STD_R_Y = sTD_R_Y;
	}

	public int getSTD_R_Z() {
		return STD_R_Z;
	}

	public void setSTD_R_Z(int sTD_R_Z) {
		STD_R_Z = sTD_R_Z;
	}

}
