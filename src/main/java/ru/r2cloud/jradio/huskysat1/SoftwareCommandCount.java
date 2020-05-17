package ru.r2cloud.jradio.huskysat1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class SoftwareCommandCount {

	private int timeMarker;
	private int softwareCommands;

	public SoftwareCommandCount() {
		// do nothing
	}

	public SoftwareCommandCount(LsbBitInputStream dis) throws IOException {
		int rawValue = dis.readBitsAsInt(6);
		// 1 bit for Reply Time Checking bit
		timeMarker = (rawValue) & 0x1;
		// 11 MSBs are software command number
		softwareCommands = (rawValue >> 1) & 0xfff;
	}

	public int getTimeMarker() {
		return timeMarker;
	}

	public void setTimeMarker(int timeMarker) {
		this.timeMarker = timeMarker;
	}

	public int getSoftwareCommands() {
		return softwareCommands;
	}

	public void setSoftwareCommands(int softwareCommands) {
		this.softwareCommands = softwareCommands;
	}

}
