package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmAocsMts {

	private float mt0Volt; // MT 0 voltage
	private float mt1Volt; // MT 1 voltage
	private float mt2Volt; // MT 2 voltage
	private float mt0Curr; // MT 0 current
	private float mt1Curr; // MT 1 current
	private float mt2Curr; // MT 2 current
	private float mt0Dipole; // MT 0 dipole
	private float mt1Dipole; // MT 1 dipole
	private float mt2Dipole; // MT 2 dipole
	private int mt0ErrorCnt; // MT 0 error counter
	private int mt1ErrorCnt; // MT 1 error counter
	private int mt2ErrorCnt; // MT 2 error counter
	private boolean mt0Enabled; // MT 0 enabled
	private boolean mt1Enabled; // MT 1 enabled
	private boolean mt2Enabled; // MT 2 enabled
	private boolean mt0Locked; // MT 0 locked
	private boolean mt1Locked; // MT 1 locked
	private boolean mt2Locked; // MT 2 locked
	private boolean mtCurr0Bound; // Current sensor 0 bound
	private boolean mtCurr1Bound; // Current sensor 1 bound
	private boolean mtCurr2Bound; // Current sensor 2 bound

	public TmAocsMts(DataInputStream dis) throws IOException {
		mt0Volt = dis.readShort() * 0.02f;
		mt1Volt = dis.readShort() * 0.02f;
		mt2Volt = dis.readShort() * 0.02f;
		mt0Curr = dis.readShort() * 0.02f;
		mt1Curr = dis.readShort() * 0.02f;
		mt2Curr = dis.readShort() * 0.02f;
		mt0Dipole = dis.readShort() * 0.001f;
		mt1Dipole = dis.readShort() * 0.001f;
		mt2Dipole = dis.readShort() * 0.001f;
		mt0ErrorCnt = dis.readUnsignedShort();
		mt1ErrorCnt = dis.readUnsignedShort();
		mt2ErrorCnt = dis.readUnsignedShort();

		int raw = dis.readUnsignedByte();
		mt0Enabled = ((raw >> 7) & 0x1) > 0;
		mt1Enabled = ((raw >> 6) & 0x1) > 0;
		mt2Enabled = ((raw >> 5) & 0x1) > 0;
		mt0Locked = ((raw >> 4) & 0x1) > 0;
		mt1Locked = ((raw >> 3) & 0x1) > 0;
		mt2Locked = ((raw >> 2) & 0x1) > 0;
		mtCurr0Bound = ((raw >> 1) & 0x1) > 0;
		mtCurr1Bound = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mtCurr2Bound = ((raw >> 7) & 0x1) > 0;
	}

	public float getMt0Volt() {
		return mt0Volt;
	}

	public void setMt0Volt(float mt0Volt) {
		this.mt0Volt = mt0Volt;
	}

	public float getMt1Volt() {
		return mt1Volt;
	}

	public void setMt1Volt(float mt1Volt) {
		this.mt1Volt = mt1Volt;
	}

	public float getMt2Volt() {
		return mt2Volt;
	}

	public void setMt2Volt(float mt2Volt) {
		this.mt2Volt = mt2Volt;
	}

	public float getMt0Curr() {
		return mt0Curr;
	}

	public void setMt0Curr(float mt0Curr) {
		this.mt0Curr = mt0Curr;
	}

	public float getMt1Curr() {
		return mt1Curr;
	}

	public void setMt1Curr(float mt1Curr) {
		this.mt1Curr = mt1Curr;
	}

	public float getMt2Curr() {
		return mt2Curr;
	}

	public void setMt2Curr(float mt2Curr) {
		this.mt2Curr = mt2Curr;
	}

	public float getMt0Dipole() {
		return mt0Dipole;
	}

	public void setMt0Dipole(float mt0Dipole) {
		this.mt0Dipole = mt0Dipole;
	}

	public float getMt1Dipole() {
		return mt1Dipole;
	}

	public void setMt1Dipole(float mt1Dipole) {
		this.mt1Dipole = mt1Dipole;
	}

	public float getMt2Dipole() {
		return mt2Dipole;
	}

	public void setMt2Dipole(float mt2Dipole) {
		this.mt2Dipole = mt2Dipole;
	}

	public int getMt0ErrorCnt() {
		return mt0ErrorCnt;
	}

	public void setMt0ErrorCnt(int mt0ErrorCnt) {
		this.mt0ErrorCnt = mt0ErrorCnt;
	}

	public int getMt1ErrorCnt() {
		return mt1ErrorCnt;
	}

	public void setMt1ErrorCnt(int mt1ErrorCnt) {
		this.mt1ErrorCnt = mt1ErrorCnt;
	}

	public int getMt2ErrorCnt() {
		return mt2ErrorCnt;
	}

	public void setMt2ErrorCnt(int mt2ErrorCnt) {
		this.mt2ErrorCnt = mt2ErrorCnt;
	}

	public boolean isMt0Enabled() {
		return mt0Enabled;
	}

	public void setMt0Enabled(boolean mt0Enabled) {
		this.mt0Enabled = mt0Enabled;
	}

	public boolean isMt1Enabled() {
		return mt1Enabled;
	}

	public void setMt1Enabled(boolean mt1Enabled) {
		this.mt1Enabled = mt1Enabled;
	}

	public boolean isMt2Enabled() {
		return mt2Enabled;
	}

	public void setMt2Enabled(boolean mt2Enabled) {
		this.mt2Enabled = mt2Enabled;
	}

	public boolean isMt0Locked() {
		return mt0Locked;
	}

	public void setMt0Locked(boolean mt0Locked) {
		this.mt0Locked = mt0Locked;
	}

	public boolean isMt1Locked() {
		return mt1Locked;
	}

	public void setMt1Locked(boolean mt1Locked) {
		this.mt1Locked = mt1Locked;
	}

	public boolean isMt2Locked() {
		return mt2Locked;
	}

	public void setMt2Locked(boolean mt2Locked) {
		this.mt2Locked = mt2Locked;
	}

	public boolean isMtCurr0Bound() {
		return mtCurr0Bound;
	}

	public void setMtCurr0Bound(boolean mtCurr0Bound) {
		this.mtCurr0Bound = mtCurr0Bound;
	}

	public boolean isMtCurr1Bound() {
		return mtCurr1Bound;
	}

	public void setMtCurr1Bound(boolean mtCurr1Bound) {
		this.mtCurr1Bound = mtCurr1Bound;
	}

	public boolean isMtCurr2Bound() {
		return mtCurr2Bound;
	}

	public void setMtCurr2Bound(boolean mtCurr2Bound) {
		this.mtCurr2Bound = mtCurr2Bound;
	}
	
}
