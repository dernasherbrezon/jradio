package ru.r2cloud.jradio.eseo;

import java.io.DataInputStream;
import java.io.IOException;

public class HSTXCommunicationCondition {

	private int CAN_TEC; // Can Controller Transmit Error Counter.
	private int CAN_REC; // CAN Controller Received Error Counter
	private boolean HRB; // Detection of the heartbeat messages on both CAN busses
	private boolean PLTC_INT; // Error in interpreting data from HSTX
	private boolean OBTC_INT; // Error in interpreting TC from OBDH (address out of range)
	private boolean TC_SEQ; // Error in interpreting TC from OBDH (sequence error)
	private int LEC0; // CAN Controller Last Error Code.
	private boolean COMERR; // One or more errors in STX_COM_ERROR occurred.

	public HSTXCommunicationCondition(DataInputStream dis) throws IOException {
		CAN_TEC = dis.readUnsignedByte();
		CAN_REC = dis.readUnsignedByte();

		int raw = dis.readUnsignedByte();
		HRB = ((raw >> 6) & 0x1) > 0;
		PLTC_INT = ((raw >> 5) & 0x1) > 0;
		OBTC_INT = ((raw >> 4) & 0x1) > 0;
		TC_SEQ = ((raw >> 3) & 0x1) > 0;
		LEC0 = (raw & 0b111);

		raw = dis.readUnsignedByte();
		COMERR = ((raw >> 7) & 0x1) > 0;
	}

	public int getCAN_TEC() {
		return CAN_TEC;
	}

	public void setCAN_TEC(int cAN_TEC) {
		CAN_TEC = cAN_TEC;
	}

	public int getCAN_REC() {
		return CAN_REC;
	}

	public void setCAN_REC(int cAN_REC) {
		CAN_REC = cAN_REC;
	}

	public boolean isHRB() {
		return HRB;
	}

	public void setHRB(boolean hRB) {
		HRB = hRB;
	}

	public boolean isPLTC_INT() {
		return PLTC_INT;
	}

	public void setPLTC_INT(boolean pLTC_INT) {
		PLTC_INT = pLTC_INT;
	}

	public boolean isOBTC_INT() {
		return OBTC_INT;
	}

	public void setOBTC_INT(boolean oBTC_INT) {
		OBTC_INT = oBTC_INT;
	}

	public boolean isTC_SEQ() {
		return TC_SEQ;
	}

	public void setTC_SEQ(boolean tC_SEQ) {
		TC_SEQ = tC_SEQ;
	}

	public int getLEC0() {
		return LEC0;
	}

	public void setLEC0(int lEC0) {
		LEC0 = lEC0;
	}

	public boolean isCOMERR() {
		return COMERR;
	}

	public void setCOMERR(boolean cOMERR) {
		COMERR = cOMERR;
	}

}
