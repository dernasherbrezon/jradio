package ru.r2cloud.jradio.cute.soh;

import java.io.DataInputStream;
import java.io.IOException;

public class SohImu {

	private int newPacketCount;
	private boolean imuVectorValid;

	public SohImu() {
		// do nothing
	}

	public SohImu(DataInputStream dis) throws IOException {
		newPacketCount = dis.readUnsignedByte();
		imuVectorValid = dis.readBoolean();
	}

	public int getNewPacketCount() {
		return newPacketCount;
	}

	public void setNewPacketCount(int newPacketCount) {
		this.newPacketCount = newPacketCount;
	}

	public boolean isImuVectorValid() {
		return imuVectorValid;
	}

	public void setImuVectorValid(boolean imuVectorValid) {
		this.imuVectorValid = imuVectorValid;
	}

}
