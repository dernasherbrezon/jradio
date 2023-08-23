package ru.r2cloud.jradio.dhabi;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class DhabisatHeader {

	private int dataType;
	private long timestamp;
	private int fileIndex;
	private int interval;
	private int totalPackets;
	private int currentPacket;

	public DhabisatHeader() {
		// do nothing
	}

	public DhabisatHeader(LittleEndianDataInputStream dis) throws IOException {
		dataType = dis.readUnsignedByte();
		timestamp = dis.readUnsignedInt();
		fileIndex = dis.readUnsignedShort();
		interval = dis.readUnsignedShort();
		totalPackets = dis.readUnsignedShort();
		currentPacket = dis.readUnsignedShort();
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(int fileIndex) {
		this.fileIndex = fileIndex;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getTotalPackets() {
		return totalPackets;
	}

	public void setTotalPackets(int totalPackets) {
		this.totalPackets = totalPackets;
	}

	public int getCurrentPacket() {
		return currentPacket;
	}

	public void setCurrentPacket(int currentPacket) {
		this.currentPacket = currentPacket;
	}

}
