package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class Payload1BMaxValues extends Payload1BData {

	private IhuHardError ihuHardError;
	private int maxTimestampResetCount;
	private int maxTimestampUptime;
	private boolean safeModeIndication;
	private boolean autoSafeModeActive;
	private boolean autoSafeModeAllowed;

	public Payload1BMaxValues() {
		// do nothing
	}

	public Payload1BMaxValues(LsbBitInputStream dis, String lookupTablePrefix, boolean useIHUVBatt) throws IOException {
		super(dis, lookupTablePrefix, useIHUVBatt);
		ihuHardError = new IhuHardError(dis);
		maxTimestampResetCount = dis.readBitsAsInt(16);
		maxTimestampUptime = dis.readBitsAsInt(25);
		safeModeIndication = dis.readBit();
		autoSafeModeActive = dis.readBit();
		autoSafeModeAllowed = dis.readBit();
		dis.readBitsAsInt(20);
	}

	public IhuHardError getIhuHardError() {
		return ihuHardError;
	}
	
	public void setIhuHardError(IhuHardError ihuHardError) {
		this.ihuHardError = ihuHardError;
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
