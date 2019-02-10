package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class HSTXMemoryCondition {

	private int MBFAIL; // Number of death cells (def.: 00).
	private boolean M5AFULL;
	private boolean M5FULL;
	private boolean M4AFULL;
	private boolean M4FULL;
	private boolean M3AFULL;
	private boolean M3FULL;
	private boolean M1AFULL; // Memory buffer for virtual channel VC=1 is almost full. (20% spare left).
	private boolean M1FULL; // Memory buffer for virtual channel VC=1 is full.
	private boolean MEMERR;
	private boolean M7AFULL;
	private boolean M7FULL;
	private boolean M6AFULL;
	private boolean M6FULL;

	public HSTXMemoryCondition(LittleEndianDataInputStream dis) throws IOException {
		MBFAIL = dis.readUnsignedByte();
		dis.skipBytes(1);

		int raw = dis.readUnsignedByte();
		M5AFULL = ((raw >> 7) & 0x1) > 0;
		M5FULL = ((raw >> 6) & 0x1) > 0;
		M4AFULL = ((raw >> 5) & 0x1) > 0;
		M4FULL = ((raw >> 4) & 0x1) > 0;
		M3AFULL = ((raw >> 3) & 0x1) > 0;
		M3FULL = ((raw >> 2) & 0x1) > 0;
		M1AFULL = ((raw >> 1) & 0x1) > 0;
		M1FULL = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MEMERR = ((raw >> 7) & 0x1) > 0;
		M7AFULL = ((raw >> 3) & 0x1) > 0;
		M7FULL = ((raw >> 2) & 0x1) > 0;
		M6AFULL = ((raw >> 1) & 0x1) > 0;
		M6FULL = (raw & 0x1) > 0;
	}

	public int getMBFAIL() {
		return MBFAIL;
	}

	public void setMBFAIL(int mBFAIL) {
		MBFAIL = mBFAIL;
	}

	public boolean isM5AFULL() {
		return M5AFULL;
	}

	public void setM5AFULL(boolean m5afull) {
		M5AFULL = m5afull;
	}

	public boolean isM5FULL() {
		return M5FULL;
	}

	public void setM5FULL(boolean m5full) {
		M5FULL = m5full;
	}

	public boolean isM4AFULL() {
		return M4AFULL;
	}

	public void setM4AFULL(boolean m4afull) {
		M4AFULL = m4afull;
	}

	public boolean isM4FULL() {
		return M4FULL;
	}

	public void setM4FULL(boolean m4full) {
		M4FULL = m4full;
	}

	public boolean isM3AFULL() {
		return M3AFULL;
	}

	public void setM3AFULL(boolean m3afull) {
		M3AFULL = m3afull;
	}

	public boolean isM3FULL() {
		return M3FULL;
	}

	public void setM3FULL(boolean m3full) {
		M3FULL = m3full;
	}

	public boolean isM1AFULL() {
		return M1AFULL;
	}

	public void setM1AFULL(boolean m1afull) {
		M1AFULL = m1afull;
	}

	public boolean isM1FULL() {
		return M1FULL;
	}

	public void setM1FULL(boolean m1full) {
		M1FULL = m1full;
	}

	public boolean isMEMERR() {
		return MEMERR;
	}

	public void setMEMERR(boolean mEMERR) {
		MEMERR = mEMERR;
	}

	public boolean isM7AFULL() {
		return M7AFULL;
	}

	public void setM7AFULL(boolean m7afull) {
		M7AFULL = m7afull;
	}

	public boolean isM7FULL() {
		return M7FULL;
	}

	public void setM7FULL(boolean m7full) {
		M7FULL = m7full;
	}

	public boolean isM6AFULL() {
		return M6AFULL;
	}

	public void setM6AFULL(boolean m6afull) {
		M6AFULL = m6afull;
	}

	public boolean isM6FULL() {
		return M6FULL;
	}

	public void setM6FULL(boolean m6full) {
		M6FULL = m6full;
	}

}
