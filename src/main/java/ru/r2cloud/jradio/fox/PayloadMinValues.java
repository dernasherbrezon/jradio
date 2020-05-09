package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class PayloadMinValues extends PayloadData {

	private int dacOverflows;
	private int i2cRetries;
	private int spiRetries;
	private int mramCRCs;

	private int maxTimestampResetCount;
	private int maxTimestampUptime;
	private boolean safeModeIndication;
	private boolean autoSafeModeActive;
	private boolean autoSafeModeAllowed;

	public PayloadMinValues() {
		// do nothing
	}

	public PayloadMinValues(LsbBitInputStream dis) throws IOException {
		super(dis);
		int rawValue = dis.readBitsAsInt(32);
		dacOverflows = rawValue & 0xff;
		i2cRetries = (rawValue >> 8) & 0xff;
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
