package ru.r2cloud.jradio.huskysat1;

import java.io.IOException;

import ru.r2cloud.jradio.fox.IhuHardError;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class MaxTelemetry extends PayloadData {

	private IhuHardError ihuHardError;
	private int maxTimestampReset;
	private int maxTimestampUptime;
	private boolean safeModeIndication;
	private boolean autoSafeModeActive;
	private boolean autoSafeModeAllowed;
	private boolean scienceModeActive;
	private boolean minMaxCrcError1;

	public MaxTelemetry() {
		// do nothing
	}

	public MaxTelemetry(LsbBitInputStream dis) throws IOException {
		super(dis);
		dis.readBitsAsInt(48);
		ihuHardError = new IhuHardError(dis);
		maxTimestampReset = dis.readBitsAsInt(16);
		maxTimestampUptime = dis.readBitsAsInt(25);
		safeModeIndication = dis.readBit();
		autoSafeModeActive = dis.readBit();
		autoSafeModeAllowed = dis.readBit();
		scienceModeActive = dis.readBit();
		minMaxCrcError1 = dis.readBit();
		dis.readBitsAsInt(18);
		dis.readBitsAsInt(32);
	}

	public IhuHardError getIhuHardError() {
		return ihuHardError;
	}

	public void setIhuHardError(IhuHardError ihuHardError) {
		this.ihuHardError = ihuHardError;
	}

	public int getMaxTimestampReset() {
		return maxTimestampReset;
	}

	public void setMaxTimestampReset(int maxTimestampReset) {
		this.maxTimestampReset = maxTimestampReset;
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

	public boolean isScienceModeActive() {
		return scienceModeActive;
	}

	public void setScienceModeActive(boolean scienceModeActive) {
		this.scienceModeActive = scienceModeActive;
	}

	public boolean isMinMaxCrcError1() {
		return minMaxCrcError1;
	}

	public void setMinMaxCrcError1(boolean minMaxCrcError1) {
		this.minMaxCrcError1 = minMaxCrcError1;
	}

}
