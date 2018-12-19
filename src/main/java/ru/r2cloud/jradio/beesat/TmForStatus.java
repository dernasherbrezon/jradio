package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmForStatus {

	private float FOR_0_CURRE;
	private float FOR_1_CURRE;
	private float FOR_2_CURRE;
	private float FOR_I2C_0_CURRE;
	private boolean FOR_0_ENABLED;
	private boolean FOR_1_ENABLED;
	private boolean FOR_2_ENABLED;
	private boolean FOR_0_LOCKED;
	private boolean FOR_1_LOCKED;
	private boolean FOR_2_LOCKED;
	private boolean FOR_0_BOUND;
	private boolean FOR_1_BOUND;
	private boolean FOR_2_BOUND;
	private boolean FOR_I2C_0_ENABL;
	private boolean FOR_I2C_0_LOCKE;
	private boolean FOR_TMP_0_BOUND;
	private boolean FOR_TMP_1_BOUND;
	private boolean FOR_TMP_2_BOUND;

	public TmForStatus(DataInputStream dis) throws IOException {
		FOR_0_CURRE = dis.readUnsignedShort() * 0.1f;
		FOR_1_CURRE = dis.readUnsignedShort() * 0.1f;
		FOR_2_CURRE = dis.readUnsignedShort() * 0.1f;
		FOR_I2C_0_CURRE = dis.readUnsignedShort() * 0.1f;

		int raw = dis.readUnsignedByte();
		FOR_0_ENABLED = ((raw >> 7) & 0x1) > 0;
		FOR_1_ENABLED = ((raw >> 6) & 0x1) > 0;
		FOR_2_ENABLED = ((raw >> 5) & 0x1) > 0;
		FOR_0_LOCKED = ((raw >> 4) & 0x1) > 0;
		FOR_1_LOCKED = ((raw >> 3) & 0x1) > 0;
		FOR_2_LOCKED = ((raw >> 2) & 0x1) > 0;
		FOR_0_BOUND = ((raw >> 1) & 0x1) > 0;
		FOR_1_BOUND = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		FOR_2_BOUND = ((raw >> 7) & 0x1) > 0;
		FOR_I2C_0_ENABL = ((raw >> 6) & 0x1) > 0;
		FOR_I2C_0_LOCKE = ((raw >> 5) & 0x1) > 0;
		FOR_TMP_0_BOUND = ((raw >> 4) & 0x1) > 0;
		FOR_TMP_1_BOUND = ((raw >> 3) & 0x1) > 0;
		FOR_TMP_2_BOUND = ((raw >> 2) & 0x1) > 0;
	}

	public float getFOR_0_CURRE() {
		return FOR_0_CURRE;
	}

	public void setFOR_0_CURRE(float fOR_0_CURRE) {
		FOR_0_CURRE = fOR_0_CURRE;
	}

	public float getFOR_1_CURRE() {
		return FOR_1_CURRE;
	}

	public void setFOR_1_CURRE(float fOR_1_CURRE) {
		FOR_1_CURRE = fOR_1_CURRE;
	}

	public float getFOR_2_CURRE() {
		return FOR_2_CURRE;
	}

	public void setFOR_2_CURRE(float fOR_2_CURRE) {
		FOR_2_CURRE = fOR_2_CURRE;
	}

	public float getFOR_I2C_0_CURRE() {
		return FOR_I2C_0_CURRE;
	}

	public void setFOR_I2C_0_CURRE(float fOR_I2C_0_CURRE) {
		FOR_I2C_0_CURRE = fOR_I2C_0_CURRE;
	}

	public boolean isFOR_0_ENABLED() {
		return FOR_0_ENABLED;
	}

	public void setFOR_0_ENABLED(boolean fOR_0_ENABLED) {
		FOR_0_ENABLED = fOR_0_ENABLED;
	}

	public boolean isFOR_1_ENABLED() {
		return FOR_1_ENABLED;
	}

	public void setFOR_1_ENABLED(boolean fOR_1_ENABLED) {
		FOR_1_ENABLED = fOR_1_ENABLED;
	}

	public boolean isFOR_2_ENABLED() {
		return FOR_2_ENABLED;
	}

	public void setFOR_2_ENABLED(boolean fOR_2_ENABLED) {
		FOR_2_ENABLED = fOR_2_ENABLED;
	}

	public boolean isFOR_0_LOCKED() {
		return FOR_0_LOCKED;
	}

	public void setFOR_0_LOCKED(boolean fOR_0_LOCKED) {
		FOR_0_LOCKED = fOR_0_LOCKED;
	}

	public boolean isFOR_1_LOCKED() {
		return FOR_1_LOCKED;
	}

	public void setFOR_1_LOCKED(boolean fOR_1_LOCKED) {
		FOR_1_LOCKED = fOR_1_LOCKED;
	}

	public boolean isFOR_2_LOCKED() {
		return FOR_2_LOCKED;
	}

	public void setFOR_2_LOCKED(boolean fOR_2_LOCKED) {
		FOR_2_LOCKED = fOR_2_LOCKED;
	}

	public boolean isFOR_0_BOUND() {
		return FOR_0_BOUND;
	}

	public void setFOR_0_BOUND(boolean fOR_0_BOUND) {
		FOR_0_BOUND = fOR_0_BOUND;
	}

	public boolean isFOR_1_BOUND() {
		return FOR_1_BOUND;
	}

	public void setFOR_1_BOUND(boolean fOR_1_BOUND) {
		FOR_1_BOUND = fOR_1_BOUND;
	}

	public boolean isFOR_2_BOUND() {
		return FOR_2_BOUND;
	}

	public void setFOR_2_BOUND(boolean fOR_2_BOUND) {
		FOR_2_BOUND = fOR_2_BOUND;
	}

	public boolean isFOR_I2C_0_ENABL() {
		return FOR_I2C_0_ENABL;
	}

	public void setFOR_I2C_0_ENABL(boolean fOR_I2C_0_ENABL) {
		FOR_I2C_0_ENABL = fOR_I2C_0_ENABL;
	}

	public boolean isFOR_I2C_0_LOCKE() {
		return FOR_I2C_0_LOCKE;
	}

	public void setFOR_I2C_0_LOCKE(boolean fOR_I2C_0_LOCKE) {
		FOR_I2C_0_LOCKE = fOR_I2C_0_LOCKE;
	}

	public boolean isFOR_TMP_0_BOUND() {
		return FOR_TMP_0_BOUND;
	}

	public void setFOR_TMP_0_BOUND(boolean fOR_TMP_0_BOUND) {
		FOR_TMP_0_BOUND = fOR_TMP_0_BOUND;
	}

	public boolean isFOR_TMP_1_BOUND() {
		return FOR_TMP_1_BOUND;
	}

	public void setFOR_TMP_1_BOUND(boolean fOR_TMP_1_BOUND) {
		FOR_TMP_1_BOUND = fOR_TMP_1_BOUND;
	}

	public boolean isFOR_TMP_2_BOUND() {
		return FOR_TMP_2_BOUND;
	}

	public void setFOR_TMP_2_BOUND(boolean fOR_TMP_2_BOUND) {
		FOR_TMP_2_BOUND = fOR_TMP_2_BOUND;
	}

}
