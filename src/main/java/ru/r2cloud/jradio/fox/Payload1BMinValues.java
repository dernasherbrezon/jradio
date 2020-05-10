package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Payload1BMinValues extends Payload1BData {

	private int dacOverflows;
	private int i2c1Retries;
	private int i2c2Retries;
	private int spiRetries;
	private int mramCRCs;

	private int maxTimestampResetCount;
	private int maxTimestampUptime;
	private boolean safeModeIndication;
	private boolean autoSafeModeActive;
	private boolean autoSafeModeAllowed;

	public Payload1BMinValues() {
		// do nothing
	}

	public Payload1BMinValues(LsbBitInputStream dis) throws IOException {
		super(dis);
		int rawValue = dis.readBitsAsInt(32);
		dacOverflows = rawValue & 0xff;
		i2c1Retries = (rawValue >> 8) & 0x0f;
		i2c2Retries = (rawValue >> 12) & 0x0f;
		spiRetries = (rawValue >> 16) & 0xff;
		mramCRCs = (rawValue >> 24) & 0xff;

		maxTimestampResetCount = dis.readBitsAsInt(16);
		maxTimestampUptime = dis.readBitsAsInt(25);
		safeModeIndication = dis.readBit();
		autoSafeModeActive = dis.readBit();
		autoSafeModeAllowed = dis.readBit();
		dis.readBitsAsInt(20);
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

	public int getMaxTimestampResetCount() {
		return maxTimestampResetCount;
	}

	public void setMaxTimestampResetCount(int maxTimestampResetCount) {
		this.maxTimestampResetCount = maxTimestampResetCount;
	}

	public int getMaxTimestampUptime() {
		return maxTimestampUptime;
	}

	public void setMaxTimestampUptime(int maxTimestampUptime) {
		this.maxTimestampUptime = maxTimestampUptime;
	}

	public boolean isSafeModeIndication() {
		return safeModeIndication;
	}

	public void setSafeModeIndication(boolean safeModeIndication) {
		this.safeModeIndication = safeModeIndication;
	}

	public boolean isAutoSafeModeActive() {
		return autoSafeModeActive;
	}

	public void setAutoSafeModeActive(boolean autoSafeModeActive) {
		this.autoSafeModeActive = autoSafeModeActive;
	}

	public boolean isAutoSafeModeAllowed() {
		return autoSafeModeAllowed;
	}

	public void setAutoSafeModeAllowed(boolean autoSafeModeAllowed) {
		this.autoSafeModeAllowed = autoSafeModeAllowed;
	}

}
