package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmAocsMts {

	private float MT_0_VOLT;
	private float MT_1_VOLT;
	private float MT_2_VOLT;
	private float MT_0_CURR;
	private float MT_1_CURR;
	private float MT_2_CURR;
	private float MT_0_DIPOLE;
	private float MT_1_DIPOLE;
	private float MT_2_DIPOLE;
	private int MT_0_ERROR_CNT;
	private int MT_1_ERROR_CNT;
	private int MT_2_ERROR_CNT;
	private boolean MT_0_ENABLED;
	private boolean MT_1_ENABLED;
	private boolean MT_2_ENABLED;
	private boolean MT_0_LOCKED;
	private boolean MT_1_LOCKED;
	private boolean MT_2_LOCKED;
	private boolean MT_CURR_0_BOUND;
	private boolean MT_CURR_1_BOUND;
	private boolean MT_CURR_2_BOUND;

	public TmAocsMts(DataInputStream dis) throws IOException {
		MT_0_VOLT = dis.readShort() * 0.02f;
		MT_1_VOLT = dis.readShort() * 0.02f;
		MT_2_VOLT = dis.readShort() * 0.02f;
		MT_0_CURR = dis.readShort() * 0.02f;
		MT_1_CURR = dis.readShort() * 0.02f;
		MT_2_CURR = dis.readShort() * 0.02f;
		MT_0_DIPOLE = dis.readShort() * 0.001f;
		MT_1_DIPOLE = dis.readShort() * 0.001f;
		MT_2_DIPOLE = dis.readShort() * 0.001f;
		MT_0_ERROR_CNT = dis.readUnsignedShort();
		MT_1_ERROR_CNT = dis.readUnsignedShort();
		MT_2_ERROR_CNT = dis.readUnsignedShort();

		int raw = dis.readUnsignedByte();
		MT_0_ENABLED = ((raw >> 7) & 0x1) > 0;
		MT_1_ENABLED = ((raw >> 6) & 0x1) > 0;
		MT_2_ENABLED = ((raw >> 5) & 0x1) > 0;
		MT_0_LOCKED = ((raw >> 4) & 0x1) > 0;
		MT_1_LOCKED = ((raw >> 3) & 0x1) > 0;
		MT_2_LOCKED = ((raw >> 2) & 0x1) > 0;
		MT_CURR_0_BOUND = ((raw >> 1) & 0x1) > 0;
		MT_CURR_1_BOUND = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MT_CURR_2_BOUND = ((raw >> 7) & 0x1) > 0;
	}

	public float getMT_0_VOLT() {
		return MT_0_VOLT;
	}

	public void setMT_0_VOLT(float mT_0_VOLT) {
		MT_0_VOLT = mT_0_VOLT;
	}

	public float getMT_1_VOLT() {
		return MT_1_VOLT;
	}

	public void setMT_1_VOLT(float mT_1_VOLT) {
		MT_1_VOLT = mT_1_VOLT;
	}

	public float getMT_2_VOLT() {
		return MT_2_VOLT;
	}

	public void setMT_2_VOLT(float mT_2_VOLT) {
		MT_2_VOLT = mT_2_VOLT;
	}

	public float getMT_0_CURR() {
		return MT_0_CURR;
	}

	public void setMT_0_CURR(float mT_0_CURR) {
		MT_0_CURR = mT_0_CURR;
	}

	public float getMT_1_CURR() {
		return MT_1_CURR;
	}

	public void setMT_1_CURR(float mT_1_CURR) {
		MT_1_CURR = mT_1_CURR;
	}

	public float getMT_2_CURR() {
		return MT_2_CURR;
	}

	public void setMT_2_CURR(float mT_2_CURR) {
		MT_2_CURR = mT_2_CURR;
	}

	public float getMT_0_DIPOLE() {
		return MT_0_DIPOLE;
	}

	public void setMT_0_DIPOLE(float mT_0_DIPOLE) {
		MT_0_DIPOLE = mT_0_DIPOLE;
	}

	public float getMT_1_DIPOLE() {
		return MT_1_DIPOLE;
	}

	public void setMT_1_DIPOLE(float mT_1_DIPOLE) {
		MT_1_DIPOLE = mT_1_DIPOLE;
	}

	public float getMT_2_DIPOLE() {
		return MT_2_DIPOLE;
	}

	public void setMT_2_DIPOLE(float mT_2_DIPOLE) {
		MT_2_DIPOLE = mT_2_DIPOLE;
	}

	public int getMT_0_ERROR_CNT() {
		return MT_0_ERROR_CNT;
	}

	public void setMT_0_ERROR_CNT(int mT_0_ERROR_CNT) {
		MT_0_ERROR_CNT = mT_0_ERROR_CNT;
	}

	public int getMT_1_ERROR_CNT() {
		return MT_1_ERROR_CNT;
	}

	public void setMT_1_ERROR_CNT(int mT_1_ERROR_CNT) {
		MT_1_ERROR_CNT = mT_1_ERROR_CNT;
	}

	public int getMT_2_ERROR_CNT() {
		return MT_2_ERROR_CNT;
	}

	public void setMT_2_ERROR_CNT(int mT_2_ERROR_CNT) {
		MT_2_ERROR_CNT = mT_2_ERROR_CNT;
	}

	public boolean isMT_0_ENABLED() {
		return MT_0_ENABLED;
	}

	public void setMT_0_ENABLED(boolean mT_0_ENABLED) {
		MT_0_ENABLED = mT_0_ENABLED;
	}

	public boolean isMT_1_ENABLED() {
		return MT_1_ENABLED;
	}

	public void setMT_1_ENABLED(boolean mT_1_ENABLED) {
		MT_1_ENABLED = mT_1_ENABLED;
	}

	public boolean isMT_2_ENABLED() {
		return MT_2_ENABLED;
	}

	public void setMT_2_ENABLED(boolean mT_2_ENABLED) {
		MT_2_ENABLED = mT_2_ENABLED;
	}

	public boolean isMT_0_LOCKED() {
		return MT_0_LOCKED;
	}

	public void setMT_0_LOCKED(boolean mT_0_LOCKED) {
		MT_0_LOCKED = mT_0_LOCKED;
	}

	public boolean isMT_1_LOCKED() {
		return MT_1_LOCKED;
	}

	public void setMT_1_LOCKED(boolean mT_1_LOCKED) {
		MT_1_LOCKED = mT_1_LOCKED;
	}

	public boolean isMT_2_LOCKED() {
		return MT_2_LOCKED;
	}

	public void setMT_2_LOCKED(boolean mT_2_LOCKED) {
		MT_2_LOCKED = mT_2_LOCKED;
	}

	public boolean isMT_CURR_0_BOUND() {
		return MT_CURR_0_BOUND;
	}

	public void setMT_CURR_0_BOUND(boolean mT_CURR_0_BOUND) {
		MT_CURR_0_BOUND = mT_CURR_0_BOUND;
	}

	public boolean isMT_CURR_1_BOUND() {
		return MT_CURR_1_BOUND;
	}

	public void setMT_CURR_1_BOUND(boolean mT_CURR_1_BOUND) {
		MT_CURR_1_BOUND = mT_CURR_1_BOUND;
	}

	public boolean isMT_CURR_2_BOUND() {
		return MT_CURR_2_BOUND;
	}

	public void setMT_CURR_2_BOUND(boolean mT_CURR_2_BOUND) {
		MT_CURR_2_BOUND = mT_CURR_2_BOUND;
	}

}
