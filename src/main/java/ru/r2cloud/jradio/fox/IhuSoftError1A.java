package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class IhuSoftError1A {

	private int dacOverflows;
	private int i2cRetries;
	private int spiRetries;
	private int mramCRCs;

	public IhuSoftError1A() {
		// do nothing
	}

	public IhuSoftError1A(LsbBitInputStream dis) throws IOException {
		int rawValue = dis.readBitsAsInt(32);
		dacOverflows = rawValue & 0xff;
		i2cRetries = (rawValue >> 8) & 0xff;
		spiRetries = (rawValue >> 16) & 0xff;
		mramCRCs = (rawValue >> 24) & 0xff;
	}

	public int getDacOverflows() {
		return dacOverflows;
	}

	public void setDacOverflows(int dacOverflows) {
		this.dacOverflows = dacOverflows;
	}

	public int getI2cRetries() {
		return i2cRetries;
	}

	public void setI2cRetries(int i2cRetries) {
		this.i2cRetries = i2cRetries;
	}

	public int getSpiRetries() {
		return spiRetries;
	}

	public void setSpiRetries(int spiRetries) {
		this.spiRetries = spiRetries;
	}

	public int getMramCRCs() {
		return mramCRCs;
	}

	public void setMramCRCs(int mramCRCs) {
		this.mramCRCs = mramCRCs;
	}

}
