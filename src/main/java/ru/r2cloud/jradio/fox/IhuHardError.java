package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class IhuHardError {

	private int watchDogReports;
	private IhuErrorType error;
	private int mramErrorCount;
	private int nonFatalErrorCount;
	private IhuTask ihuTask;
	private int alignment;

	public IhuHardError() {
		// do nothing
	}

	public IhuHardError(LsbBitInputStream dis) throws IOException {
		int rawValue = dis.readBitsAsInt(32);
		watchDogReports = rawValue & 0x1ff;
		// Error code is the next 5 bits
		error = IhuErrorType.valueOfCode((rawValue >> 9) & 0x1f);
		// mramErrorCount is the next 3 bits
		mramErrorCount = (rawValue >> 14) & 0x07;
		// nonFatalErrorCount is the next 3 bits
		nonFatalErrorCount = (rawValue >> 17) & 0x07;
		// taskNumber is the next 4 bits
		ihuTask = IhuTask.valueOfCode((rawValue >> 20) & 0x0f);
		// alignment is the next 8 bits
		alignment = (rawValue >> 24) & 0xff;
	}

	public int getWatchDogReports() {
		return watchDogReports;
	}

	public void setWatchDogReports(int watchDogReports) {
		this.watchDogReports = watchDogReports;
	}

	public IhuErrorType getError() {
		return error;
	}

	public void setError(IhuErrorType error) {
		this.error = error;
	}

	public int getMramErrorCount() {
		return mramErrorCount;
	}

	public void setMramErrorCount(int mramErrorCount) {
		this.mramErrorCount = mramErrorCount;
	}

	public int getNonFatalErrorCount() {
		return nonFatalErrorCount;
	}

	public void setNonFatalErrorCount(int nonFatalErrorCount) {
		this.nonFatalErrorCount = nonFatalErrorCount;
	}

	public IhuTask getIhuTask() {
		return ihuTask;
	}

	public void setIhuTask(IhuTask ihuTask) {
		this.ihuTask = ihuTask;
	}

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

}
