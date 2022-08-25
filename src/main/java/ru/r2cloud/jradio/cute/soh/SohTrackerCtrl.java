package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

public class SohTrackerCtrl {

	private int aidStatus1;
	private int aidStatus2;
	private int starIdStatus;

	public SohTrackerCtrl() {
		// do nothing
	}

	public SohTrackerCtrl(DataInputStream dis) throws IOException {
		aidStatus1 = dis.readUnsignedByte();
		aidStatus2 = dis.readUnsignedByte();
		starIdStatus = dis.readUnsignedByte();
	}

	public int getAidStatus1() {
		return aidStatus1;
	}

	public void setAidStatus1(int aidStatus1) {
		this.aidStatus1 = aidStatus1;
	}

	public int getAidStatus2() {
		return aidStatus2;
	}

	public void setAidStatus2(int aidStatus2) {
		this.aidStatus2 = aidStatus2;
	}

	public int getStarIdStatus() {
		return starIdStatus;
	}

	public void setStarIdStatus(int starIdStatus) {
		this.starIdStatus = starIdStatus;
	}

}
