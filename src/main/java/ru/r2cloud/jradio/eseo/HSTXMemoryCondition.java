package ru.r2cloud.jradio.eseo;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class HSTXMemoryCondition {

	private int mbfail; // number of death cells (def.: 00).
	private boolean m5afull;
	private boolean m5full;
	private boolean m4afull;
	private boolean m4full;
	private boolean m3afull;
	private boolean m3full;
	private boolean m1afull; // Memory buffer for virtual channel VC=1 is almost full. (20% spare left).
	private boolean m1full; // memory buffer for virtual channel VC=1 is full.
	private boolean memerr;
	private boolean m7afull;
	private boolean m7full;
	private boolean m6afull;
	private boolean m6full;

	public HSTXMemoryCondition(LittleEndianDataInputStream dis) throws IOException {
		mbfail = dis.readUnsignedByte();
		dis.skipBytes(1);

		int raw = dis.readUnsignedByte();
		m5afull = ((raw >> 7) & 0x1) > 0;
		m5full = ((raw >> 6) & 0x1) > 0;
		m4afull = ((raw >> 5) & 0x1) > 0;
		m4full = ((raw >> 4) & 0x1) > 0;
		m3afull = ((raw >> 3) & 0x1) > 0;
		m3full = ((raw >> 2) & 0x1) > 0;
		m1afull = ((raw >> 1) & 0x1) > 0;
		m1full = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		memerr = ((raw >> 7) & 0x1) > 0;
		m7afull = ((raw >> 3) & 0x1) > 0;
		m7full = ((raw >> 2) & 0x1) > 0;
		m6afull = ((raw >> 1) & 0x1) > 0;
		m6full = (raw & 0x1) > 0;
	}

	public int getMbfail() {
		return mbfail;
	}

	public void setMbfail(int mbfail) {
		this.mbfail = mbfail;
	}

	public boolean isM5afull() {
		return m5afull;
	}

	public void setM5afull(boolean m5afull) {
		this.m5afull = m5afull;
	}

	public boolean isM5full() {
		return m5full;
	}

	public void setM5full(boolean m5full) {
		this.m5full = m5full;
	}

	public boolean isM4afull() {
		return m4afull;
	}

	public void setM4afull(boolean m4afull) {
		this.m4afull = m4afull;
	}

	public boolean isM4full() {
		return m4full;
	}

	public void setM4full(boolean m4full) {
		this.m4full = m4full;
	}

	public boolean isM3afull() {
		return m3afull;
	}

	public void setM3afull(boolean m3afull) {
		this.m3afull = m3afull;
	}

	public boolean isM3full() {
		return m3full;
	}

	public void setM3full(boolean m3full) {
		this.m3full = m3full;
	}

	public boolean isM1afull() {
		return m1afull;
	}

	public void setM1afull(boolean m1afull) {
		this.m1afull = m1afull;
	}

	public boolean isM1full() {
		return m1full;
	}

	public void setM1full(boolean m1full) {
		this.m1full = m1full;
	}

	public boolean isMemerr() {
		return memerr;
	}

	public void setMemerr(boolean memerr) {
		this.memerr = memerr;
	}

	public boolean isM7afull() {
		return m7afull;
	}

	public void setM7afull(boolean m7afull) {
		this.m7afull = m7afull;
	}

	public boolean isM7full() {
		return m7full;
	}

	public void setM7full(boolean m7full) {
		this.m7full = m7full;
	}

	public boolean isM6afull() {
		return m6afull;
	}

	public void setM6afull(boolean m6afull) {
		this.m6afull = m6afull;
	}

	public boolean isM6full() {
		return m6full;
	}

	public void setM6full(boolean m6full) {
		this.m6full = m6full;
	}

}
