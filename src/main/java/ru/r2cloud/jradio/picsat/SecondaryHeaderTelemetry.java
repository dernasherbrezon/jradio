package ru.r2cloud.jradio.picsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class SecondaryHeaderTelemetry {

	private int daysSinceRef;
	private long msSinceToday;

	public SecondaryHeaderTelemetry() {
		// do nothing
	}

	public SecondaryHeaderTelemetry(BitInputStream bis) throws IOException {
		daysSinceRef = bis.readUnsignedInt(16);
		msSinceToday = bis.readUnsignedLong(32);
	}

	public int getDaysSinceRef() {
		return daysSinceRef;
	}

	public void setDaysSinceRef(int daysSinceRef) {
		this.daysSinceRef = daysSinceRef;
	}

	public long getMsSinceToday() {
		return msSinceToday;
	}

	public void setMsSinceToday(long msSinceToday) {
		this.msSinceToday = msSinceToday;
	}

}
