package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class IhuSoftError {

	private int dacOverflows;
	private int i2c1Retries;
	private int i2c2Retries;
	private int spiRetries;
	private int mramCRCs;

	public IhuSoftError() {
		// do nothing
	}

	public IhuSoftError(LsbBitInputStream dis) throws IOException {
		int rawValue = dis.readBitsAsInt(32);
		dacOverflows = rawValue & 0xff;
		i2c1Retries = (rawValue >> 8) & 0x0f;
		i2c2Retries = (rawValue >> 12) & 0x0f;
		spiRetries = (rawValue >> 16) & 0xff;
		mramCRCs = (rawValue >> 24) & 0xff;
	}

	public int getDacOverflows() {
		return dacOverflows;
	}

	public void setDacOverflows(int dacOverflows) {
		this.dacOverflows = dacOverflows;
	}

	public int getI2c1Retries() {
		return i2c1Retries;
	}

	public void setI2c1Retries(int i2c1Retries) {
		this.i2c1Retries = i2c1Retries;
	}

	public int getI2c2Retries() {
		return i2c2Retries;
	}

	public void setI2c2Retries(int i2c2Retries) {
		this.i2c2Retries = i2c2Retries;
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
