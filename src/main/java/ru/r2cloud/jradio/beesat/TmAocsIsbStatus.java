package ru.r2cloud.jradio.beesat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmAocsIsbStatus {

	private float I2C_0_CURRENT;
	private float I2C_1_CURRENT;
	private boolean I2C_0_ENABLED;
	private boolean I2C_1_ENABLED;
	private boolean I2C_0_LOCKED;
	private boolean I2C_1_LOCKED;
	private boolean SSU_00_BOUND;
	private boolean SSU_01_BOUND;
	private boolean SSU_02_BOUND;
	private boolean SSU_03_BOUND;
	private boolean SSU_04_BOUND;
	private boolean SSU_05_BOUND;
	private boolean SSU_10_BOUND;
	private boolean SSU_11_BOUND;
	private boolean SSU_12_BOUND;
	private boolean SSU_13_BOUND;
	private boolean SSU_14_BOUND;
	private boolean SSU_15_BOUND;
	private boolean MFSA_00_BOUND;
	private boolean MFSA_01_BOUND;
	private boolean MFSA_02_BOUND;
	private boolean MFSA_03_BOUND;
	private boolean MFSA_04_BOUND;
	private boolean MFSA_05_BOUND;
	private boolean MFSA_10_BOUND;
	private boolean MFSA_11_BOUND;
	private boolean MFSA_12_BOUND;
	private boolean MFSA_13_BOUND;
	private boolean MFSA_14_BOUND;
	private boolean MFSA_15_BOUND;
	private boolean GYRA_00_BOUND;
	private boolean GYRA_01_BOUND;
	private boolean GYRA_02_BOUND;
	private boolean GYRA_03_BOUND;
	private boolean GYRA_04_BOUND;
	private boolean GYRA_05_BOUND;
	private boolean GYRA_10_BOUND;
	private boolean GYRA_11_BOUND;
	private boolean GYRA_12_BOUND;
	private boolean GYRA_13_BOUND;
	private boolean GYRA_14_BOUND;
	private boolean GYRA_15_BOUND;
	private boolean GYRA_T_00_BOUND;
	private boolean GYRA_T_01_BOUND;
	private boolean GYRA_T_02_BOUND;
	private boolean GYRA_T_03_BOUND;
	private boolean GYRA_T_04_BOUND;
	private boolean GYRA_T_05_BOUND;
	private boolean GYRA_T_10_BOUND;
	private boolean GYRA_T_11_BOUND;
	private boolean GYRA_T_12_BOUND;
	private boolean GYRA_T_13_BOUND;
	private boolean GYRA_T_14_BOUND;
	private boolean GYRA_T_15_BOUND;
	private boolean MFSA_F_00_ACTIV;
	private boolean MFSA_F_01_ACTIV;
	private boolean MFSA_F_02_ACTIV;
	private boolean MFSA_F_03_ACTIV;
	private boolean MFSA_F_04_ACTIV;
	private boolean MFSA_F_05_ACTIV;
	private boolean MFSA_F_10_ACTIV;
	private boolean MFSA_F_11_ACTIV;
	private boolean MFSA_F_12_ACTIV;
	private boolean MFSA_F_13_ACTIV;
	private boolean MFSA_F_14_ACTIV;
	private boolean MFSA_F_15_ACTIV;
	private boolean GYRA_F_00_ACTIV;
	private boolean GYRA_F_01_ACTIV;
	private boolean GYRA_F_02_ACTIV;
	private boolean GYRA_F_03_ACTIV;
	private boolean GYRA_F_04_ACTIV;
	private boolean GYRA_F_05_ACTIV;
	private boolean GYRA_F_10_ACTIV;
	private boolean GYRA_F_11_ACTIV;
	private boolean GYRA_F_12_ACTIV;
	private boolean GYRA_F_13_ACTIV;
	private boolean GYRA_F_14_ACTIV;
	private boolean GYRA_F_15_ACTIV;

	public TmAocsIsbStatus(DataInputStream dis) throws IOException {
		I2C_0_CURRENT = dis.readUnsignedShort() * 0.1f;
		I2C_1_CURRENT = dis.readUnsignedShort() * 0.1f;
		int raw = dis.readUnsignedByte();
		I2C_0_ENABLED = ((raw >> 7) & 0x1) > 0;
		I2C_1_ENABLED = ((raw >> 6) & 0x1) > 0;
		I2C_0_LOCKED = ((raw >> 5) & 0x1) > 0;
		I2C_1_LOCKED = ((raw >> 4) & 0x1) > 0;
		SSU_00_BOUND = ((raw >> 3) & 0x1) > 0;
		SSU_01_BOUND = ((raw >> 2) & 0x1) > 0;
		SSU_02_BOUND = ((raw >> 1) & 0x1) > 0;
		SSU_03_BOUND = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		SSU_04_BOUND = ((raw >> 7) & 0x1) > 0;
		SSU_05_BOUND = ((raw >> 6) & 0x1) > 0;
		SSU_10_BOUND = ((raw >> 5) & 0x1) > 0;
		SSU_11_BOUND = ((raw >> 4) & 0x1) > 0;
		SSU_12_BOUND = ((raw >> 3) & 0x1) > 0;
		SSU_13_BOUND = ((raw >> 2) & 0x1) > 0;
		SSU_14_BOUND = ((raw >> 1) & 0x1) > 0;
		SSU_15_BOUND = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MFSA_00_BOUND = ((raw >> 7) & 0x1) > 0;
		MFSA_01_BOUND = ((raw >> 6) & 0x1) > 0;
		MFSA_02_BOUND = ((raw >> 5) & 0x1) > 0;
		MFSA_03_BOUND = ((raw >> 4) & 0x1) > 0;
		MFSA_04_BOUND = ((raw >> 3) & 0x1) > 0;
		MFSA_05_BOUND = ((raw >> 2) & 0x1) > 0;
		MFSA_10_BOUND = ((raw >> 1) & 0x1) > 0;
		MFSA_11_BOUND = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MFSA_12_BOUND = ((raw >> 7) & 0x1) > 0;
		MFSA_13_BOUND = ((raw >> 6) & 0x1) > 0;
		MFSA_14_BOUND = ((raw >> 5) & 0x1) > 0;
		MFSA_15_BOUND = ((raw >> 4) & 0x1) > 0;
		GYRA_00_BOUND = ((raw >> 3) & 0x1) > 0;
		GYRA_01_BOUND = ((raw >> 2) & 0x1) > 0;
		GYRA_02_BOUND = ((raw >> 1) & 0x1) > 0;
		GYRA_03_BOUND = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		GYRA_04_BOUND = ((raw >> 7) & 0x1) > 0;
		GYRA_05_BOUND = ((raw >> 6) & 0x1) > 0;
		GYRA_10_BOUND = ((raw >> 5) & 0x1) > 0;
		GYRA_11_BOUND = ((raw >> 4) & 0x1) > 0;
		GYRA_12_BOUND = ((raw >> 3) & 0x1) > 0;
		GYRA_13_BOUND = ((raw >> 2) & 0x1) > 0;
		GYRA_14_BOUND = ((raw >> 1) & 0x1) > 0;
		GYRA_15_BOUND = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		GYRA_T_00_BOUND = ((raw >> 7) & 0x1) > 0;
		GYRA_T_01_BOUND = ((raw >> 6) & 0x1) > 0;
		GYRA_T_02_BOUND = ((raw >> 5) & 0x1) > 0;
		GYRA_T_03_BOUND = ((raw >> 4) & 0x1) > 0;
		GYRA_T_04_BOUND = ((raw >> 3) & 0x1) > 0;
		GYRA_T_05_BOUND = ((raw >> 2) & 0x1) > 0;
		GYRA_T_10_BOUND = ((raw >> 1) & 0x1) > 0;
		GYRA_T_11_BOUND = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		GYRA_T_12_BOUND = ((raw >> 7) & 0x1) > 0;
		GYRA_T_13_BOUND = ((raw >> 6) & 0x1) > 0;
		GYRA_T_14_BOUND = ((raw >> 5) & 0x1) > 0;
		GYRA_T_15_BOUND = ((raw >> 4) & 0x1) > 0;
		MFSA_F_00_ACTIV = ((raw >> 3) & 0x1) > 0;
		MFSA_F_01_ACTIV = ((raw >> 2) & 0x1) > 0;
		MFSA_F_02_ACTIV = ((raw >> 1) & 0x1) > 0;
		MFSA_F_03_ACTIV = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		MFSA_F_04_ACTIV = ((raw >> 7) & 0x1) > 0;
		MFSA_F_05_ACTIV = ((raw >> 6) & 0x1) > 0;
		MFSA_F_10_ACTIV = ((raw >> 5) & 0x1) > 0;
		MFSA_F_11_ACTIV = ((raw >> 4) & 0x1) > 0;
		MFSA_F_12_ACTIV = ((raw >> 3) & 0x1) > 0;
		MFSA_F_13_ACTIV = ((raw >> 2) & 0x1) > 0;
		MFSA_F_14_ACTIV = ((raw >> 1) & 0x1) > 0;
		MFSA_F_15_ACTIV = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		GYRA_F_00_ACTIV = ((raw >> 7) & 0x1) > 0;
		GYRA_F_01_ACTIV = ((raw >> 6) & 0x1) > 0;
		GYRA_F_02_ACTIV = ((raw >> 5) & 0x1) > 0;
		GYRA_F_03_ACTIV = ((raw >> 4) & 0x1) > 0;
		GYRA_F_04_ACTIV = ((raw >> 3) & 0x1) > 0;
		GYRA_F_05_ACTIV = ((raw >> 2) & 0x1) > 0;
		GYRA_F_10_ACTIV = ((raw >> 1) & 0x1) > 0;
		GYRA_F_11_ACTIV = ((raw >> 0) & 0x1) > 0;

		raw = dis.readUnsignedByte();
		GYRA_F_12_ACTIV = ((raw >> 7) & 0x1) > 0;
		GYRA_F_13_ACTIV = ((raw >> 6) & 0x1) > 0;
		GYRA_F_14_ACTIV = ((raw >> 5) & 0x1) > 0;
		GYRA_F_15_ACTIV = ((raw >> 4) & 0x1) > 0;

	}

	public float getI2C_0_CURRENT() {
		return I2C_0_CURRENT;
	}

	public void setI2C_0_CURRENT(float i2c_0_CURRENT) {
		I2C_0_CURRENT = i2c_0_CURRENT;
	}

	public float getI2C_1_CURRENT() {
		return I2C_1_CURRENT;
	}

	public void setI2C_1_CURRENT(float i2c_1_CURRENT) {
		I2C_1_CURRENT = i2c_1_CURRENT;
	}

	public boolean isI2C_0_ENABLED() {
		return I2C_0_ENABLED;
	}

	public void setI2C_0_ENABLED(boolean i2c_0_ENABLED) {
		I2C_0_ENABLED = i2c_0_ENABLED;
	}

	public boolean isI2C_1_ENABLED() {
		return I2C_1_ENABLED;
	}

	public void setI2C_1_ENABLED(boolean i2c_1_ENABLED) {
		I2C_1_ENABLED = i2c_1_ENABLED;
	}

	public boolean isI2C_0_LOCKED() {
		return I2C_0_LOCKED;
	}

	public void setI2C_0_LOCKED(boolean i2c_0_LOCKED) {
		I2C_0_LOCKED = i2c_0_LOCKED;
	}

	public boolean isI2C_1_LOCKED() {
		return I2C_1_LOCKED;
	}

	public void setI2C_1_LOCKED(boolean i2c_1_LOCKED) {
		I2C_1_LOCKED = i2c_1_LOCKED;
	}

	public boolean isSSU_00_BOUND() {
		return SSU_00_BOUND;
	}

	public void setSSU_00_BOUND(boolean sSU_00_BOUND) {
		SSU_00_BOUND = sSU_00_BOUND;
	}

	public boolean isSSU_01_BOUND() {
		return SSU_01_BOUND;
	}

	public void setSSU_01_BOUND(boolean sSU_01_BOUND) {
		SSU_01_BOUND = sSU_01_BOUND;
	}

	public boolean isSSU_02_BOUND() {
		return SSU_02_BOUND;
	}

	public void setSSU_02_BOUND(boolean sSU_02_BOUND) {
		SSU_02_BOUND = sSU_02_BOUND;
	}

	public boolean isSSU_03_BOUND() {
		return SSU_03_BOUND;
	}

	public void setSSU_03_BOUND(boolean sSU_03_BOUND) {
		SSU_03_BOUND = sSU_03_BOUND;
	}

	public boolean isSSU_04_BOUND() {
		return SSU_04_BOUND;
	}

	public void setSSU_04_BOUND(boolean sSU_04_BOUND) {
		SSU_04_BOUND = sSU_04_BOUND;
	}

	public boolean isSSU_05_BOUND() {
		return SSU_05_BOUND;
	}

	public void setSSU_05_BOUND(boolean sSU_05_BOUND) {
		SSU_05_BOUND = sSU_05_BOUND;
	}

	public boolean isSSU_10_BOUND() {
		return SSU_10_BOUND;
	}

	public void setSSU_10_BOUND(boolean sSU_10_BOUND) {
		SSU_10_BOUND = sSU_10_BOUND;
	}

	public boolean isSSU_11_BOUND() {
		return SSU_11_BOUND;
	}

	public void setSSU_11_BOUND(boolean sSU_11_BOUND) {
		SSU_11_BOUND = sSU_11_BOUND;
	}

	public boolean isSSU_12_BOUND() {
		return SSU_12_BOUND;
	}

	public void setSSU_12_BOUND(boolean sSU_12_BOUND) {
		SSU_12_BOUND = sSU_12_BOUND;
	}

	public boolean isSSU_13_BOUND() {
		return SSU_13_BOUND;
	}

	public void setSSU_13_BOUND(boolean sSU_13_BOUND) {
		SSU_13_BOUND = sSU_13_BOUND;
	}

	public boolean isSSU_14_BOUND() {
		return SSU_14_BOUND;
	}

	public void setSSU_14_BOUND(boolean sSU_14_BOUND) {
		SSU_14_BOUND = sSU_14_BOUND;
	}

	public boolean isSSU_15_BOUND() {
		return SSU_15_BOUND;
	}

	public void setSSU_15_BOUND(boolean sSU_15_BOUND) {
		SSU_15_BOUND = sSU_15_BOUND;
	}

	public boolean isMFSA_00_BOUND() {
		return MFSA_00_BOUND;
	}

	public void setMFSA_00_BOUND(boolean mFSA_00_BOUND) {
		MFSA_00_BOUND = mFSA_00_BOUND;
	}

	public boolean isMFSA_01_BOUND() {
		return MFSA_01_BOUND;
	}

	public void setMFSA_01_BOUND(boolean mFSA_01_BOUND) {
		MFSA_01_BOUND = mFSA_01_BOUND;
	}

	public boolean isMFSA_02_BOUND() {
		return MFSA_02_BOUND;
	}

	public void setMFSA_02_BOUND(boolean mFSA_02_BOUND) {
		MFSA_02_BOUND = mFSA_02_BOUND;
	}

	public boolean isMFSA_03_BOUND() {
		return MFSA_03_BOUND;
	}

	public void setMFSA_03_BOUND(boolean mFSA_03_BOUND) {
		MFSA_03_BOUND = mFSA_03_BOUND;
	}

	public boolean isMFSA_04_BOUND() {
		return MFSA_04_BOUND;
	}

	public void setMFSA_04_BOUND(boolean mFSA_04_BOUND) {
		MFSA_04_BOUND = mFSA_04_BOUND;
	}

	public boolean isMFSA_05_BOUND() {
		return MFSA_05_BOUND;
	}

	public void setMFSA_05_BOUND(boolean mFSA_05_BOUND) {
		MFSA_05_BOUND = mFSA_05_BOUND;
	}

	public boolean isMFSA_10_BOUND() {
		return MFSA_10_BOUND;
	}

	public void setMFSA_10_BOUND(boolean mFSA_10_BOUND) {
		MFSA_10_BOUND = mFSA_10_BOUND;
	}

	public boolean isMFSA_11_BOUND() {
		return MFSA_11_BOUND;
	}

	public void setMFSA_11_BOUND(boolean mFSA_11_BOUND) {
		MFSA_11_BOUND = mFSA_11_BOUND;
	}

	public boolean isMFSA_12_BOUND() {
		return MFSA_12_BOUND;
	}

	public void setMFSA_12_BOUND(boolean mFSA_12_BOUND) {
		MFSA_12_BOUND = mFSA_12_BOUND;
	}

	public boolean isMFSA_13_BOUND() {
		return MFSA_13_BOUND;
	}

	public void setMFSA_13_BOUND(boolean mFSA_13_BOUND) {
		MFSA_13_BOUND = mFSA_13_BOUND;
	}

	public boolean isMFSA_14_BOUND() {
		return MFSA_14_BOUND;
	}

	public void setMFSA_14_BOUND(boolean mFSA_14_BOUND) {
		MFSA_14_BOUND = mFSA_14_BOUND;
	}

	public boolean isMFSA_15_BOUND() {
		return MFSA_15_BOUND;
	}

	public void setMFSA_15_BOUND(boolean mFSA_15_BOUND) {
		MFSA_15_BOUND = mFSA_15_BOUND;
	}

	public boolean isGYRA_00_BOUND() {
		return GYRA_00_BOUND;
	}

	public void setGYRA_00_BOUND(boolean gYRA_00_BOUND) {
		GYRA_00_BOUND = gYRA_00_BOUND;
	}

	public boolean isGYRA_01_BOUND() {
		return GYRA_01_BOUND;
	}

	public void setGYRA_01_BOUND(boolean gYRA_01_BOUND) {
		GYRA_01_BOUND = gYRA_01_BOUND;
	}

	public boolean isGYRA_02_BOUND() {
		return GYRA_02_BOUND;
	}

	public void setGYRA_02_BOUND(boolean gYRA_02_BOUND) {
		GYRA_02_BOUND = gYRA_02_BOUND;
	}

	public boolean isGYRA_03_BOUND() {
		return GYRA_03_BOUND;
	}

	public void setGYRA_03_BOUND(boolean gYRA_03_BOUND) {
		GYRA_03_BOUND = gYRA_03_BOUND;
	}

	public boolean isGYRA_04_BOUND() {
		return GYRA_04_BOUND;
	}

	public void setGYRA_04_BOUND(boolean gYRA_04_BOUND) {
		GYRA_04_BOUND = gYRA_04_BOUND;
	}

	public boolean isGYRA_05_BOUND() {
		return GYRA_05_BOUND;
	}

	public void setGYRA_05_BOUND(boolean gYRA_05_BOUND) {
		GYRA_05_BOUND = gYRA_05_BOUND;
	}

	public boolean isGYRA_10_BOUND() {
		return GYRA_10_BOUND;
	}

	public void setGYRA_10_BOUND(boolean gYRA_10_BOUND) {
		GYRA_10_BOUND = gYRA_10_BOUND;
	}

	public boolean isGYRA_11_BOUND() {
		return GYRA_11_BOUND;
	}

	public void setGYRA_11_BOUND(boolean gYRA_11_BOUND) {
		GYRA_11_BOUND = gYRA_11_BOUND;
	}

	public boolean isGYRA_12_BOUND() {
		return GYRA_12_BOUND;
	}

	public void setGYRA_12_BOUND(boolean gYRA_12_BOUND) {
		GYRA_12_BOUND = gYRA_12_BOUND;
	}

	public boolean isGYRA_13_BOUND() {
		return GYRA_13_BOUND;
	}

	public void setGYRA_13_BOUND(boolean gYRA_13_BOUND) {
		GYRA_13_BOUND = gYRA_13_BOUND;
	}

	public boolean isGYRA_14_BOUND() {
		return GYRA_14_BOUND;
	}

	public void setGYRA_14_BOUND(boolean gYRA_14_BOUND) {
		GYRA_14_BOUND = gYRA_14_BOUND;
	}

	public boolean isGYRA_15_BOUND() {
		return GYRA_15_BOUND;
	}

	public void setGYRA_15_BOUND(boolean gYRA_15_BOUND) {
		GYRA_15_BOUND = gYRA_15_BOUND;
	}

	public boolean isGYRA_T_00_BOUND() {
		return GYRA_T_00_BOUND;
	}

	public void setGYRA_T_00_BOUND(boolean gYRA_T_00_BOUND) {
		GYRA_T_00_BOUND = gYRA_T_00_BOUND;
	}

	public boolean isGYRA_T_01_BOUND() {
		return GYRA_T_01_BOUND;
	}

	public void setGYRA_T_01_BOUND(boolean gYRA_T_01_BOUND) {
		GYRA_T_01_BOUND = gYRA_T_01_BOUND;
	}

	public boolean isGYRA_T_02_BOUND() {
		return GYRA_T_02_BOUND;
	}

	public void setGYRA_T_02_BOUND(boolean gYRA_T_02_BOUND) {
		GYRA_T_02_BOUND = gYRA_T_02_BOUND;
	}

	public boolean isGYRA_T_03_BOUND() {
		return GYRA_T_03_BOUND;
	}

	public void setGYRA_T_03_BOUND(boolean gYRA_T_03_BOUND) {
		GYRA_T_03_BOUND = gYRA_T_03_BOUND;
	}

	public boolean isGYRA_T_04_BOUND() {
		return GYRA_T_04_BOUND;
	}

	public void setGYRA_T_04_BOUND(boolean gYRA_T_04_BOUND) {
		GYRA_T_04_BOUND = gYRA_T_04_BOUND;
	}

	public boolean isGYRA_T_05_BOUND() {
		return GYRA_T_05_BOUND;
	}

	public void setGYRA_T_05_BOUND(boolean gYRA_T_05_BOUND) {
		GYRA_T_05_BOUND = gYRA_T_05_BOUND;
	}

	public boolean isGYRA_T_10_BOUND() {
		return GYRA_T_10_BOUND;
	}

	public void setGYRA_T_10_BOUND(boolean gYRA_T_10_BOUND) {
		GYRA_T_10_BOUND = gYRA_T_10_BOUND;
	}

	public boolean isGYRA_T_11_BOUND() {
		return GYRA_T_11_BOUND;
	}

	public void setGYRA_T_11_BOUND(boolean gYRA_T_11_BOUND) {
		GYRA_T_11_BOUND = gYRA_T_11_BOUND;
	}

	public boolean isGYRA_T_12_BOUND() {
		return GYRA_T_12_BOUND;
	}

	public void setGYRA_T_12_BOUND(boolean gYRA_T_12_BOUND) {
		GYRA_T_12_BOUND = gYRA_T_12_BOUND;
	}

	public boolean isGYRA_T_13_BOUND() {
		return GYRA_T_13_BOUND;
	}

	public void setGYRA_T_13_BOUND(boolean gYRA_T_13_BOUND) {
		GYRA_T_13_BOUND = gYRA_T_13_BOUND;
	}

	public boolean isGYRA_T_14_BOUND() {
		return GYRA_T_14_BOUND;
	}

	public void setGYRA_T_14_BOUND(boolean gYRA_T_14_BOUND) {
		GYRA_T_14_BOUND = gYRA_T_14_BOUND;
	}

	public boolean isGYRA_T_15_BOUND() {
		return GYRA_T_15_BOUND;
	}

	public void setGYRA_T_15_BOUND(boolean gYRA_T_15_BOUND) {
		GYRA_T_15_BOUND = gYRA_T_15_BOUND;
	}

	public boolean isMFSA_F_00_ACTIV() {
		return MFSA_F_00_ACTIV;
	}

	public void setMFSA_F_00_ACTIV(boolean mFSA_F_00_ACTIV) {
		MFSA_F_00_ACTIV = mFSA_F_00_ACTIV;
	}

	public boolean isMFSA_F_01_ACTIV() {
		return MFSA_F_01_ACTIV;
	}

	public void setMFSA_F_01_ACTIV(boolean mFSA_F_01_ACTIV) {
		MFSA_F_01_ACTIV = mFSA_F_01_ACTIV;
	}

	public boolean isMFSA_F_02_ACTIV() {
		return MFSA_F_02_ACTIV;
	}

	public void setMFSA_F_02_ACTIV(boolean mFSA_F_02_ACTIV) {
		MFSA_F_02_ACTIV = mFSA_F_02_ACTIV;
	}

	public boolean isMFSA_F_03_ACTIV() {
		return MFSA_F_03_ACTIV;
	}

	public void setMFSA_F_03_ACTIV(boolean mFSA_F_03_ACTIV) {
		MFSA_F_03_ACTIV = mFSA_F_03_ACTIV;
	}

	public boolean isMFSA_F_04_ACTIV() {
		return MFSA_F_04_ACTIV;
	}

	public void setMFSA_F_04_ACTIV(boolean mFSA_F_04_ACTIV) {
		MFSA_F_04_ACTIV = mFSA_F_04_ACTIV;
	}

	public boolean isMFSA_F_05_ACTIV() {
		return MFSA_F_05_ACTIV;
	}

	public void setMFSA_F_05_ACTIV(boolean mFSA_F_05_ACTIV) {
		MFSA_F_05_ACTIV = mFSA_F_05_ACTIV;
	}

	public boolean isMFSA_F_10_ACTIV() {
		return MFSA_F_10_ACTIV;
	}

	public void setMFSA_F_10_ACTIV(boolean mFSA_F_10_ACTIV) {
		MFSA_F_10_ACTIV = mFSA_F_10_ACTIV;
	}

	public boolean isMFSA_F_11_ACTIV() {
		return MFSA_F_11_ACTIV;
	}

	public void setMFSA_F_11_ACTIV(boolean mFSA_F_11_ACTIV) {
		MFSA_F_11_ACTIV = mFSA_F_11_ACTIV;
	}

	public boolean isMFSA_F_12_ACTIV() {
		return MFSA_F_12_ACTIV;
	}

	public void setMFSA_F_12_ACTIV(boolean mFSA_F_12_ACTIV) {
		MFSA_F_12_ACTIV = mFSA_F_12_ACTIV;
	}

	public boolean isMFSA_F_13_ACTIV() {
		return MFSA_F_13_ACTIV;
	}

	public void setMFSA_F_13_ACTIV(boolean mFSA_F_13_ACTIV) {
		MFSA_F_13_ACTIV = mFSA_F_13_ACTIV;
	}

	public boolean isMFSA_F_14_ACTIV() {
		return MFSA_F_14_ACTIV;
	}

	public void setMFSA_F_14_ACTIV(boolean mFSA_F_14_ACTIV) {
		MFSA_F_14_ACTIV = mFSA_F_14_ACTIV;
	}

	public boolean isMFSA_F_15_ACTIV() {
		return MFSA_F_15_ACTIV;
	}

	public void setMFSA_F_15_ACTIV(boolean mFSA_F_15_ACTIV) {
		MFSA_F_15_ACTIV = mFSA_F_15_ACTIV;
	}

	public boolean isGYRA_F_00_ACTIV() {
		return GYRA_F_00_ACTIV;
	}

	public void setGYRA_F_00_ACTIV(boolean gYRA_F_00_ACTIV) {
		GYRA_F_00_ACTIV = gYRA_F_00_ACTIV;
	}

	public boolean isGYRA_F_01_ACTIV() {
		return GYRA_F_01_ACTIV;
	}

	public void setGYRA_F_01_ACTIV(boolean gYRA_F_01_ACTIV) {
		GYRA_F_01_ACTIV = gYRA_F_01_ACTIV;
	}

	public boolean isGYRA_F_02_ACTIV() {
		return GYRA_F_02_ACTIV;
	}

	public void setGYRA_F_02_ACTIV(boolean gYRA_F_02_ACTIV) {
		GYRA_F_02_ACTIV = gYRA_F_02_ACTIV;
	}

	public boolean isGYRA_F_03_ACTIV() {
		return GYRA_F_03_ACTIV;
	}

	public void setGYRA_F_03_ACTIV(boolean gYRA_F_03_ACTIV) {
		GYRA_F_03_ACTIV = gYRA_F_03_ACTIV;
	}

	public boolean isGYRA_F_04_ACTIV() {
		return GYRA_F_04_ACTIV;
	}

	public void setGYRA_F_04_ACTIV(boolean gYRA_F_04_ACTIV) {
		GYRA_F_04_ACTIV = gYRA_F_04_ACTIV;
	}

	public boolean isGYRA_F_05_ACTIV() {
		return GYRA_F_05_ACTIV;
	}

	public void setGYRA_F_05_ACTIV(boolean gYRA_F_05_ACTIV) {
		GYRA_F_05_ACTIV = gYRA_F_05_ACTIV;
	}

	public boolean isGYRA_F_10_ACTIV() {
		return GYRA_F_10_ACTIV;
	}

	public void setGYRA_F_10_ACTIV(boolean gYRA_F_10_ACTIV) {
		GYRA_F_10_ACTIV = gYRA_F_10_ACTIV;
	}

	public boolean isGYRA_F_11_ACTIV() {
		return GYRA_F_11_ACTIV;
	}

	public void setGYRA_F_11_ACTIV(boolean gYRA_F_11_ACTIV) {
		GYRA_F_11_ACTIV = gYRA_F_11_ACTIV;
	}

	public boolean isGYRA_F_12_ACTIV() {
		return GYRA_F_12_ACTIV;
	}

	public void setGYRA_F_12_ACTIV(boolean gYRA_F_12_ACTIV) {
		GYRA_F_12_ACTIV = gYRA_F_12_ACTIV;
	}

	public boolean isGYRA_F_13_ACTIV() {
		return GYRA_F_13_ACTIV;
	}

	public void setGYRA_F_13_ACTIV(boolean gYRA_F_13_ACTIV) {
		GYRA_F_13_ACTIV = gYRA_F_13_ACTIV;
	}

	public boolean isGYRA_F_14_ACTIV() {
		return GYRA_F_14_ACTIV;
	}

	public void setGYRA_F_14_ACTIV(boolean gYRA_F_14_ACTIV) {
		GYRA_F_14_ACTIV = gYRA_F_14_ACTIV;
	}

	public boolean isGYRA_F_15_ACTIV() {
		return GYRA_F_15_ACTIV;
	}

	public void setGYRA_F_15_ACTIV(boolean gYRA_F_15_ACTIV) {
		GYRA_F_15_ACTIV = gYRA_F_15_ACTIV;
	}

}
