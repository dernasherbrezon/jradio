package ru.r2cloud.jradio.iris;

import java.io.DataInputStream;
import java.io.IOException;

public class SecondaryHeader {

	private int frameId;
	private int masterFrameCount;
	private int virtualChannelFrameCount;
	private int firstHeaderPoint;;

	public SecondaryHeader() {
		// do nothing
	}

	public SecondaryHeader(DataInputStream dis) throws IOException {
		frameId = dis.readUnsignedByte();
		masterFrameCount = dis.readUnsignedByte();
		virtualChannelFrameCount = dis.readUnsignedByte();
		firstHeaderPoint = dis.readUnsignedByte();
	}

	public int getFrameId() {
		return frameId;
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}

	public int getMasterFrameCount() {
		return masterFrameCount;
	}

	public void setMasterFrameCount(int masterFrameCount) {
		this.masterFrameCount = masterFrameCount;
	}

	public int getVirtualChannelFrameCount() {
		return virtualChannelFrameCount;
	}

	public void setVirtualChannelFrameCount(int virtualChannelFrameCount) {
		this.virtualChannelFrameCount = virtualChannelFrameCount;
	}

	public int getFirstHeaderPoint() {
		return firstHeaderPoint;
	}

	public void setFirstHeaderPoint(int firstHeaderPoint) {
		this.firstHeaderPoint = firstHeaderPoint;
	}

}
