package ru.r2cloud.jradio.technosat;

import java.io.DataInputStream;
import java.io.IOException;

public class TmAocsIsbStatus {

	private float i2c0Current; // I2C0 current
	private float i2c1Current; // I2C1 current
	private boolean i2c0Enabled; // I2C0 enabled
	private boolean i2c1Enabled; // I2C1 enabled
	private boolean i2c0Locked; // I2C0 locked
	private boolean i2c1Locked; // I2C1 locked
	private boolean ssu00Bound; // bound
	private boolean ssu01Bound; // bound
	private boolean ssu02Bound; // bound
	private boolean ssu03Bound; // bound
	private boolean ssu04Bound; // bound
	private boolean ssu05Bound; // bound
	private boolean ssu10Bound; // bound
	private boolean ssu11Bound; // bound
	private boolean ssu12Bound; // bound
	private boolean ssu13Bound; // bound
	private boolean ssu14Bound; // bound
	private boolean ssu15Bound; // bound
	private boolean mfsa00Bound; // bound
	private boolean mfsa01Bound; // bound
	private boolean mfsa02Bound; // bound
	private boolean mfsa03Bound; // bound
	private boolean mfsa04Bound; // bound
	private boolean mfsa05Bound; // bound
	private boolean mfsa10Bound; // bound
	private boolean mfsa11Bound; // bound
	private boolean mfsa12Bound; // bound
	private boolean mfsa13Bound; // bound
	private boolean mfsa14Bound; // bound
	private boolean mfsa15Bound; // bound
	private boolean gyra00Bound; // bound
	private boolean gyra01Bound; // bound
	private boolean gyra02Bound; // bound
	private boolean gyra03Bound; // bound
	private boolean gyra04Bound; // bound
	private boolean gyra05Bound; // bound
	private boolean gyra10Bound; // bound
	private boolean gyra11Bound; // bound
	private boolean gyra12Bound; // bound
	private boolean gyra13Bound; // bound
	private boolean gyra14Bound; // bound
	private boolean gyra15Bound; // bound
	private boolean gyraT00Bound; // bound
	private boolean gyraT01Bound; // bound
	private boolean gyraT02Bound; // bound
	private boolean gyraT03Bound; // bound
	private boolean gyraT04Bound; // bound
	private boolean gyraT05Bound; // bound
	private boolean gyraT10Bound; // bound
	private boolean gyraT11Bound; // bound
	private boolean gyraT12Bound; // bound
	private boolean gyraT13Bound; // bound
	private boolean gyraT14Bound; // bound
	private boolean gyraT15Bound; // bound
	private boolean mfsaF00Activ; // active
	private boolean mfsaF01Activ; // active
	private boolean mfsaF02Activ; // active
	private boolean mfsaF03Activ; // active
	private boolean mfsaF04Activ; // active
	private boolean mfsaF05Activ; // active
	private boolean mfsaF10Activ; // active
	private boolean mfsaF11Activ; // active
	private boolean mfsaF12Activ; // active
	private boolean mfsaF13Activ; // active
	private boolean mfsaF14Activ; // active
	private boolean mfsaF15Activ; // active
	private boolean gyraF00Activ; // active
	private boolean gyraF01Activ; // active
	private boolean gyraF02Activ; // active
	private boolean gyraF03Activ; // active
	private boolean gyraF04Activ; // active
	private boolean gyraF05Activ; // active
	private boolean gyraF10Activ; // active
	private boolean gyraF11Activ; // active
	private boolean gyraF12Activ; // active
	private boolean gyraF13Activ; // active
	private boolean gyraF14Activ; // active
	private boolean gyraF15Activ; // active

	public TmAocsIsbStatus(DataInputStream dis) throws IOException {
		i2c0Current = dis.readUnsignedShort() * 0.1f;
		i2c1Current = dis.readUnsignedShort() * 0.1f;
		int raw = dis.readUnsignedByte();
		i2c0Enabled = ((raw >> 7) & 0x1) > 0;
		i2c1Enabled = ((raw >> 6) & 0x1) > 0;
		i2c0Locked = ((raw >> 5) & 0x1) > 0;
		i2c1Locked = ((raw >> 4) & 0x1) > 0;
		ssu00Bound = ((raw >> 3) & 0x1) > 0;
		ssu01Bound = ((raw >> 2) & 0x1) > 0;
		ssu02Bound = ((raw >> 1) & 0x1) > 0;
		ssu03Bound = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		ssu04Bound = ((raw >> 7) & 0x1) > 0;
		ssu05Bound = ((raw >> 6) & 0x1) > 0;
		ssu10Bound = ((raw >> 5) & 0x1) > 0;
		ssu11Bound = ((raw >> 4) & 0x1) > 0;
		ssu12Bound = ((raw >> 3) & 0x1) > 0;
		ssu13Bound = ((raw >> 2) & 0x1) > 0;
		ssu14Bound = ((raw >> 1) & 0x1) > 0;
		ssu15Bound = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mfsa00Bound = ((raw >> 7) & 0x1) > 0;
		mfsa01Bound = ((raw >> 6) & 0x1) > 0;
		mfsa02Bound = ((raw >> 5) & 0x1) > 0;
		mfsa03Bound = ((raw >> 4) & 0x1) > 0;
		mfsa04Bound = ((raw >> 3) & 0x1) > 0;
		mfsa05Bound = ((raw >> 2) & 0x1) > 0;
		mfsa10Bound = ((raw >> 1) & 0x1) > 0;
		mfsa11Bound = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mfsa12Bound = ((raw >> 7) & 0x1) > 0;
		mfsa13Bound = ((raw >> 6) & 0x1) > 0;
		mfsa14Bound = ((raw >> 5) & 0x1) > 0;
		mfsa15Bound = ((raw >> 4) & 0x1) > 0;
		gyra00Bound = ((raw >> 3) & 0x1) > 0;
		gyra01Bound = ((raw >> 2) & 0x1) > 0;
		gyra02Bound = ((raw >> 1) & 0x1) > 0;
		gyra03Bound = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		gyra04Bound = ((raw >> 7) & 0x1) > 0;
		gyra05Bound = ((raw >> 6) & 0x1) > 0;
		gyra10Bound = ((raw >> 5) & 0x1) > 0;
		gyra11Bound = ((raw >> 4) & 0x1) > 0;
		gyra12Bound = ((raw >> 3) & 0x1) > 0;
		gyra13Bound = ((raw >> 2) & 0x1) > 0;
		gyra14Bound = ((raw >> 1) & 0x1) > 0;
		gyra15Bound = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		gyraT00Bound = ((raw >> 7) & 0x1) > 0;
		gyraT01Bound = ((raw >> 6) & 0x1) > 0;
		gyraT02Bound = ((raw >> 5) & 0x1) > 0;
		gyraT03Bound = ((raw >> 4) & 0x1) > 0;
		gyraT04Bound = ((raw >> 3) & 0x1) > 0;
		gyraT05Bound = ((raw >> 2) & 0x1) > 0;
		gyraT10Bound = ((raw >> 1) & 0x1) > 0;
		gyraT11Bound = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		gyraT12Bound = ((raw >> 7) & 0x1) > 0;
		gyraT13Bound = ((raw >> 6) & 0x1) > 0;
		gyraT14Bound = ((raw >> 5) & 0x1) > 0;
		gyraT15Bound = ((raw >> 4) & 0x1) > 0;
		mfsaF00Activ = ((raw >> 3) & 0x1) > 0;
		mfsaF01Activ = ((raw >> 2) & 0x1) > 0;
		mfsaF02Activ = ((raw >> 1) & 0x1) > 0;
		mfsaF03Activ = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		mfsaF04Activ = ((raw >> 7) & 0x1) > 0;
		mfsaF05Activ = ((raw >> 6) & 0x1) > 0;
		mfsaF10Activ = ((raw >> 5) & 0x1) > 0;
		mfsaF11Activ = ((raw >> 4) & 0x1) > 0;
		mfsaF12Activ = ((raw >> 3) & 0x1) > 0;
		mfsaF13Activ = ((raw >> 2) & 0x1) > 0;
		mfsaF14Activ = ((raw >> 1) & 0x1) > 0;
		mfsaF15Activ = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		gyraF00Activ = ((raw >> 7) & 0x1) > 0;
		gyraF01Activ = ((raw >> 6) & 0x1) > 0;
		gyraF02Activ = ((raw >> 5) & 0x1) > 0;
		gyraF03Activ = ((raw >> 4) & 0x1) > 0;
		gyraF04Activ = ((raw >> 3) & 0x1) > 0;
		gyraF05Activ = ((raw >> 2) & 0x1) > 0;
		gyraF10Activ = ((raw >> 1) & 0x1) > 0;
		gyraF11Activ = (raw & 0x1) > 0;

		raw = dis.readUnsignedByte();
		gyraF12Activ = ((raw >> 7) & 0x1) > 0;
		gyraF13Activ = ((raw >> 6) & 0x1) > 0;
		gyraF14Activ = ((raw >> 5) & 0x1) > 0;
		gyraF15Activ = ((raw >> 4) & 0x1) > 0;

	}

	public float getI2c0Current() {
		return i2c0Current;
	}

	public void setI2c0Current(float i2c0Current) {
		this.i2c0Current = i2c0Current;
	}

	public float getI2c1Current() {
		return i2c1Current;
	}

	public void setI2c1Current(float i2c1Current) {
		this.i2c1Current = i2c1Current;
	}

	public boolean isI2c0Enabled() {
		return i2c0Enabled;
	}

	public void setI2c0Enabled(boolean i2c0Enabled) {
		this.i2c0Enabled = i2c0Enabled;
	}

	public boolean isI2c1Enabled() {
		return i2c1Enabled;
	}

	public void setI2c1Enabled(boolean i2c1Enabled) {
		this.i2c1Enabled = i2c1Enabled;
	}

	public boolean isI2c0Locked() {
		return i2c0Locked;
	}

	public void setI2c0Locked(boolean i2c0Locked) {
		this.i2c0Locked = i2c0Locked;
	}

	public boolean isI2c1Locked() {
		return i2c1Locked;
	}

	public void setI2c1Locked(boolean i2c1Locked) {
		this.i2c1Locked = i2c1Locked;
	}

	public boolean isSsu00Bound() {
		return ssu00Bound;
	}

	public void setSsu00Bound(boolean ssu00Bound) {
		this.ssu00Bound = ssu00Bound;
	}

	public boolean isSsu01Bound() {
		return ssu01Bound;
	}

	public void setSsu01Bound(boolean ssu01Bound) {
		this.ssu01Bound = ssu01Bound;
	}

	public boolean isSsu02Bound() {
		return ssu02Bound;
	}

	public void setSsu02Bound(boolean ssu02Bound) {
		this.ssu02Bound = ssu02Bound;
	}

	public boolean isSsu03Bound() {
		return ssu03Bound;
	}

	public void setSsu03Bound(boolean ssu03Bound) {
		this.ssu03Bound = ssu03Bound;
	}

	public boolean isSsu04Bound() {
		return ssu04Bound;
	}

	public void setSsu04Bound(boolean ssu04Bound) {
		this.ssu04Bound = ssu04Bound;
	}

	public boolean isSsu05Bound() {
		return ssu05Bound;
	}

	public void setSsu05Bound(boolean ssu05Bound) {
		this.ssu05Bound = ssu05Bound;
	}

	public boolean isSsu10Bound() {
		return ssu10Bound;
	}

	public void setSsu10Bound(boolean ssu10Bound) {
		this.ssu10Bound = ssu10Bound;
	}

	public boolean isSsu11Bound() {
		return ssu11Bound;
	}

	public void setSsu11Bound(boolean ssu11Bound) {
		this.ssu11Bound = ssu11Bound;
	}

	public boolean isSsu12Bound() {
		return ssu12Bound;
	}

	public void setSsu12Bound(boolean ssu12Bound) {
		this.ssu12Bound = ssu12Bound;
	}

	public boolean isSsu13Bound() {
		return ssu13Bound;
	}

	public void setSsu13Bound(boolean ssu13Bound) {
		this.ssu13Bound = ssu13Bound;
	}

	public boolean isSsu14Bound() {
		return ssu14Bound;
	}

	public void setSsu14Bound(boolean ssu14Bound) {
		this.ssu14Bound = ssu14Bound;
	}

	public boolean isSsu15Bound() {
		return ssu15Bound;
	}

	public void setSsu15Bound(boolean ssu15Bound) {
		this.ssu15Bound = ssu15Bound;
	}

	public boolean isMfsa00Bound() {
		return mfsa00Bound;
	}

	public void setMfsa00Bound(boolean mfsa00Bound) {
		this.mfsa00Bound = mfsa00Bound;
	}

	public boolean isMfsa01Bound() {
		return mfsa01Bound;
	}

	public void setMfsa01Bound(boolean mfsa01Bound) {
		this.mfsa01Bound = mfsa01Bound;
	}

	public boolean isMfsa02Bound() {
		return mfsa02Bound;
	}

	public void setMfsa02Bound(boolean mfsa02Bound) {
		this.mfsa02Bound = mfsa02Bound;
	}

	public boolean isMfsa03Bound() {
		return mfsa03Bound;
	}

	public void setMfsa03Bound(boolean mfsa03Bound) {
		this.mfsa03Bound = mfsa03Bound;
	}

	public boolean isMfsa04Bound() {
		return mfsa04Bound;
	}

	public void setMfsa04Bound(boolean mfsa04Bound) {
		this.mfsa04Bound = mfsa04Bound;
	}

	public boolean isMfsa05Bound() {
		return mfsa05Bound;
	}

	public void setMfsa05Bound(boolean mfsa05Bound) {
		this.mfsa05Bound = mfsa05Bound;
	}

	public boolean isMfsa10Bound() {
		return mfsa10Bound;
	}

	public void setMfsa10Bound(boolean mfsa10Bound) {
		this.mfsa10Bound = mfsa10Bound;
	}

	public boolean isMfsa11Bound() {
		return mfsa11Bound;
	}

	public void setMfsa11Bound(boolean mfsa11Bound) {
		this.mfsa11Bound = mfsa11Bound;
	}

	public boolean isMfsa12Bound() {
		return mfsa12Bound;
	}

	public void setMfsa12Bound(boolean mfsa12Bound) {
		this.mfsa12Bound = mfsa12Bound;
	}

	public boolean isMfsa13Bound() {
		return mfsa13Bound;
	}

	public void setMfsa13Bound(boolean mfsa13Bound) {
		this.mfsa13Bound = mfsa13Bound;
	}

	public boolean isMfsa14Bound() {
		return mfsa14Bound;
	}

	public void setMfsa14Bound(boolean mfsa14Bound) {
		this.mfsa14Bound = mfsa14Bound;
	}

	public boolean isMfsa15Bound() {
		return mfsa15Bound;
	}

	public void setMfsa15Bound(boolean mfsa15Bound) {
		this.mfsa15Bound = mfsa15Bound;
	}

	public boolean isGyra00Bound() {
		return gyra00Bound;
	}

	public void setGyra00Bound(boolean gyra00Bound) {
		this.gyra00Bound = gyra00Bound;
	}

	public boolean isGyra01Bound() {
		return gyra01Bound;
	}

	public void setGyra01Bound(boolean gyra01Bound) {
		this.gyra01Bound = gyra01Bound;
	}

	public boolean isGyra02Bound() {
		return gyra02Bound;
	}

	public void setGyra02Bound(boolean gyra02Bound) {
		this.gyra02Bound = gyra02Bound;
	}

	public boolean isGyra03Bound() {
		return gyra03Bound;
	}

	public void setGyra03Bound(boolean gyra03Bound) {
		this.gyra03Bound = gyra03Bound;
	}

	public boolean isGyra04Bound() {
		return gyra04Bound;
	}

	public void setGyra04Bound(boolean gyra04Bound) {
		this.gyra04Bound = gyra04Bound;
	}

	public boolean isGyra05Bound() {
		return gyra05Bound;
	}

	public void setGyra05Bound(boolean gyra05Bound) {
		this.gyra05Bound = gyra05Bound;
	}

	public boolean isGyra10Bound() {
		return gyra10Bound;
	}

	public void setGyra10Bound(boolean gyra10Bound) {
		this.gyra10Bound = gyra10Bound;
	}

	public boolean isGyra11Bound() {
		return gyra11Bound;
	}

	public void setGyra11Bound(boolean gyra11Bound) {
		this.gyra11Bound = gyra11Bound;
	}

	public boolean isGyra12Bound() {
		return gyra12Bound;
	}

	public void setGyra12Bound(boolean gyra12Bound) {
		this.gyra12Bound = gyra12Bound;
	}

	public boolean isGyra13Bound() {
		return gyra13Bound;
	}

	public void setGyra13Bound(boolean gyra13Bound) {
		this.gyra13Bound = gyra13Bound;
	}

	public boolean isGyra14Bound() {
		return gyra14Bound;
	}

	public void setGyra14Bound(boolean gyra14Bound) {
		this.gyra14Bound = gyra14Bound;
	}

	public boolean isGyra15Bound() {
		return gyra15Bound;
	}

	public void setGyra15Bound(boolean gyra15Bound) {
		this.gyra15Bound = gyra15Bound;
	}

	public boolean isGyraT00Bound() {
		return gyraT00Bound;
	}

	public void setGyraT00Bound(boolean gyraT00Bound) {
		this.gyraT00Bound = gyraT00Bound;
	}

	public boolean isGyraT01Bound() {
		return gyraT01Bound;
	}

	public void setGyraT01Bound(boolean gyraT01Bound) {
		this.gyraT01Bound = gyraT01Bound;
	}

	public boolean isGyraT02Bound() {
		return gyraT02Bound;
	}

	public void setGyraT02Bound(boolean gyraT02Bound) {
		this.gyraT02Bound = gyraT02Bound;
	}

	public boolean isGyraT03Bound() {
		return gyraT03Bound;
	}

	public void setGyraT03Bound(boolean gyraT03Bound) {
		this.gyraT03Bound = gyraT03Bound;
	}

	public boolean isGyraT04Bound() {
		return gyraT04Bound;
	}

	public void setGyraT04Bound(boolean gyraT04Bound) {
		this.gyraT04Bound = gyraT04Bound;
	}

	public boolean isGyraT05Bound() {
		return gyraT05Bound;
	}

	public void setGyraT05Bound(boolean gyraT05Bound) {
		this.gyraT05Bound = gyraT05Bound;
	}

	public boolean isGyraT10Bound() {
		return gyraT10Bound;
	}

	public void setGyraT10Bound(boolean gyraT10Bound) {
		this.gyraT10Bound = gyraT10Bound;
	}

	public boolean isGyraT11Bound() {
		return gyraT11Bound;
	}

	public void setGyraT11Bound(boolean gyraT11Bound) {
		this.gyraT11Bound = gyraT11Bound;
	}

	public boolean isGyraT12Bound() {
		return gyraT12Bound;
	}

	public void setGyraT12Bound(boolean gyraT12Bound) {
		this.gyraT12Bound = gyraT12Bound;
	}

	public boolean isGyraT13Bound() {
		return gyraT13Bound;
	}

	public void setGyraT13Bound(boolean gyraT13Bound) {
		this.gyraT13Bound = gyraT13Bound;
	}

	public boolean isGyraT14Bound() {
		return gyraT14Bound;
	}

	public void setGyraT14Bound(boolean gyraT14Bound) {
		this.gyraT14Bound = gyraT14Bound;
	}

	public boolean isGyraT15Bound() {
		return gyraT15Bound;
	}

	public void setGyraT15Bound(boolean gyraT15Bound) {
		this.gyraT15Bound = gyraT15Bound;
	}

	public boolean isMfsaF00Activ() {
		return mfsaF00Activ;
	}

	public void setMfsaF00Activ(boolean mfsaF00Activ) {
		this.mfsaF00Activ = mfsaF00Activ;
	}

	public boolean isMfsaF01Activ() {
		return mfsaF01Activ;
	}

	public void setMfsaF01Activ(boolean mfsaF01Activ) {
		this.mfsaF01Activ = mfsaF01Activ;
	}

	public boolean isMfsaF02Activ() {
		return mfsaF02Activ;
	}

	public void setMfsaF02Activ(boolean mfsaF02Activ) {
		this.mfsaF02Activ = mfsaF02Activ;
	}

	public boolean isMfsaF03Activ() {
		return mfsaF03Activ;
	}

	public void setMfsaF03Activ(boolean mfsaF03Activ) {
		this.mfsaF03Activ = mfsaF03Activ;
	}

	public boolean isMfsaF04Activ() {
		return mfsaF04Activ;
	}

	public void setMfsaF04Activ(boolean mfsaF04Activ) {
		this.mfsaF04Activ = mfsaF04Activ;
	}

	public boolean isMfsaF05Activ() {
		return mfsaF05Activ;
	}

	public void setMfsaF05Activ(boolean mfsaF05Activ) {
		this.mfsaF05Activ = mfsaF05Activ;
	}

	public boolean isMfsaF10Activ() {
		return mfsaF10Activ;
	}

	public void setMfsaF10Activ(boolean mfsaF10Activ) {
		this.mfsaF10Activ = mfsaF10Activ;
	}

	public boolean isMfsaF11Activ() {
		return mfsaF11Activ;
	}

	public void setMfsaF11Activ(boolean mfsaF11Activ) {
		this.mfsaF11Activ = mfsaF11Activ;
	}

	public boolean isMfsaF12Activ() {
		return mfsaF12Activ;
	}

	public void setMfsaF12Activ(boolean mfsaF12Activ) {
		this.mfsaF12Activ = mfsaF12Activ;
	}

	public boolean isMfsaF13Activ() {
		return mfsaF13Activ;
	}

	public void setMfsaF13Activ(boolean mfsaF13Activ) {
		this.mfsaF13Activ = mfsaF13Activ;
	}

	public boolean isMfsaF14Activ() {
		return mfsaF14Activ;
	}

	public void setMfsaF14Activ(boolean mfsaF14Activ) {
		this.mfsaF14Activ = mfsaF14Activ;
	}

	public boolean isMfsaF15Activ() {
		return mfsaF15Activ;
	}

	public void setMfsaF15Activ(boolean mfsaF15Activ) {
		this.mfsaF15Activ = mfsaF15Activ;
	}

	public boolean isGyraF00Activ() {
		return gyraF00Activ;
	}

	public void setGyraF00Activ(boolean gyraF00Activ) {
		this.gyraF00Activ = gyraF00Activ;
	}

	public boolean isGyraF01Activ() {
		return gyraF01Activ;
	}

	public void setGyraF01Activ(boolean gyraF01Activ) {
		this.gyraF01Activ = gyraF01Activ;
	}

	public boolean isGyraF02Activ() {
		return gyraF02Activ;
	}

	public void setGyraF02Activ(boolean gyraF02Activ) {
		this.gyraF02Activ = gyraF02Activ;
	}

	public boolean isGyraF03Activ() {
		return gyraF03Activ;
	}

	public void setGyraF03Activ(boolean gyraF03Activ) {
		this.gyraF03Activ = gyraF03Activ;
	}

	public boolean isGyraF04Activ() {
		return gyraF04Activ;
	}

	public void setGyraF04Activ(boolean gyraF04Activ) {
		this.gyraF04Activ = gyraF04Activ;
	}

	public boolean isGyraF05Activ() {
		return gyraF05Activ;
	}

	public void setGyraF05Activ(boolean gyraF05Activ) {
		this.gyraF05Activ = gyraF05Activ;
	}

	public boolean isGyraF10Activ() {
		return gyraF10Activ;
	}

	public void setGyraF10Activ(boolean gyraF10Activ) {
		this.gyraF10Activ = gyraF10Activ;
	}

	public boolean isGyraF11Activ() {
		return gyraF11Activ;
	}

	public void setGyraF11Activ(boolean gyraF11Activ) {
		this.gyraF11Activ = gyraF11Activ;
	}

	public boolean isGyraF12Activ() {
		return gyraF12Activ;
	}

	public void setGyraF12Activ(boolean gyraF12Activ) {
		this.gyraF12Activ = gyraF12Activ;
	}

	public boolean isGyraF13Activ() {
		return gyraF13Activ;
	}

	public void setGyraF13Activ(boolean gyraF13Activ) {
		this.gyraF13Activ = gyraF13Activ;
	}

	public boolean isGyraF14Activ() {
		return gyraF14Activ;
	}

	public void setGyraF14Activ(boolean gyraF14Activ) {
		this.gyraF14Activ = gyraF14Activ;
	}

	public boolean isGyraF15Activ() {
		return gyraF15Activ;
	}

	public void setGyraF15Activ(boolean gyraF15Activ) {
		this.gyraF15Activ = gyraF15Activ;
	}

}
