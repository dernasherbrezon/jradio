package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class HSTXCommunicationCondition {

	private int canTEC; // Can Controller Transmit Error Counter.
	private int canREC; // CAN Controller Received Error Counter
	private boolean hrb; // Detection of the heartbeat messages on both CAN busses
	private boolean pltcINT; // Error in interpreting data from HSTX
	private boolean obtcINT; // Error in interpreting TC from OBDH (address out of range)
	private boolean tcSEQ; // Error in interpreting TC from OBDH (sequence error)
	private int lec0; // CAN Controller Last Error Code.
	private boolean comerr; // One or more errors in STX_COM_ERROR occurred.

	public HSTXCommunicationCondition(LittleEndianDataInputStream dis) throws IOException {
		canTEC = dis.readUnsignedByte();
		canREC = dis.readUnsignedByte();

		int raw = dis.readUnsignedByte();
		hrb = ((raw >> 6) & 0x1) > 0;
		pltcINT = ((raw >> 5) & 0x1) > 0;
		obtcINT = ((raw >> 4) & 0x1) > 0;
		tcSEQ = ((raw >> 3) & 0x1) > 0;
		lec0 = (raw & 0b111);

		raw = dis.readUnsignedByte();
		comerr = ((raw >> 7) & 0x1) > 0;
	}

	public int getCanTEC() {
		return canTEC;
	}

	public void setCanTEC(int canTEC) {
		this.canTEC = canTEC;
	}

	public int getCanREC() {
		return canREC;
	}

	public void setCanREC(int canREC) {
		this.canREC = canREC;
	}

	public boolean isHrb() {
		return hrb;
	}

	public void setHrb(boolean hrb) {
		this.hrb = hrb;
	}

	public boolean isPltcINT() {
		return pltcINT;
	}

	public void setPltcINT(boolean pltcINT) {
		this.pltcINT = pltcINT;
	}

	public boolean isObtcINT() {
		return obtcINT;
	}

	public void setObtcINT(boolean obtcINT) {
		this.obtcINT = obtcINT;
	}

	public boolean isTcSEQ() {
		return tcSEQ;
	}

	public void setTcSEQ(boolean tcSEQ) {
		this.tcSEQ = tcSEQ;
	}

	public int getLec0() {
		return lec0;
	}

	public void setLec0(int lec0) {
		this.lec0 = lec0;
	}

	public boolean isComerr() {
		return comerr;
	}

	public void setComerr(boolean comerr) {
		this.comerr = comerr;
	}

}
