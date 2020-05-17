package ru.r2cloud.jradio.huskysat1;

import java.io.IOException;

import ru.r2cloud.jradio.fox.IhuSoftError1A;
import ru.r2cloud.jradio.util.LsbBitInputStream;

public class MinTelemetry extends PayloadData {

	private IhuSoftError1A softError;
	private int minTimestampReset;
	private int minTimestampUptime;
	private boolean safeModeIndication;
	private boolean autoSafeModeActive;
	private boolean autoSafeModeAllowed;
	private boolean scienceModeActive;
	private boolean minMaxCrcError2;

	public MinTelemetry() {
		// do nothing
	}

	public MinTelemetry(LsbBitInputStream dis) throws IOException {
		super(dis);
		dis.readBitsAsInt(48);
		softError = new IhuSoftError1A(dis);
		minTimestampReset = dis.readBitsAsInt(16);
		minTimestampUptime = dis.readBitsAsInt(25);
		safeModeIndication = dis.readBit();
		autoSafeModeActive = dis.readBit();
		autoSafeModeAllowed = dis.readBit();
		scienceModeActive = dis.readBit();
		minMaxCrcError2 = dis.readBit();
		dis.readBitsAsInt(18);
		dis.readBitsAsInt(32);
	}

	public IhuSoftError1A getSoftError() {
		return softError;
	}

	public void setSoftError(IhuSoftError1A softError) {
		this.softError = softError;
	}

	public int getMinTimestampReset() {
		return minTimestampReset;
	}

	public void setMinTimestampReset(int minTimestampReset) {
		this.minTimestampReset = minTimestampReset;
	}

	public int getMinTimestampUptime() {
		return minTimestampUptime;
	}

	public void setMinTimestampUptime(int minTimestampUptime) {
		this.minTimestampUptime = minTimestampUptime;
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

	public boolean isMinMaxCrcError2() {
		return minMaxCrcError2;
	}

	public void setMinMaxCrcError2(boolean minMaxCrcError2) {
		this.minMaxCrcError2 = minMaxCrcError2;
	}

}
