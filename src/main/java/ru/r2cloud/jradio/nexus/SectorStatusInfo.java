package ru.r2cloud.jradio.nexus;

import java.io.DataInputStream;
import java.io.IOException;

public class SectorStatusInfo {

	private int sectorNum;
	private long shootingTime;
	private ImageFormat format;
	private int continuousShootingNum;
	private int dataSize;
	private int storageStartAddress;

	public SectorStatusInfo() {
		// do nothing
	}

	public SectorStatusInfo(DataInputStream dis) throws IOException {
		sectorNum = dis.readUnsignedByte();
		// shooting time is always wrong. 1970 year mostly
		// either format is different or clock non synchronized
		dis.skipBytes(4);
		format = ImageFormat.valueOfCode(dis.readUnsignedByte());
		continuousShootingNum = dis.readUnsignedShort();
		dataSize = ((dis.readUnsignedByte() & 0xFF) << 16) | ((dis.readUnsignedByte() & 0xFF) << 8) | (dis.readUnsignedByte() & 0xFF);
		storageStartAddress = ((dis.readUnsignedByte() & 0xFF) << 16) | ((dis.readUnsignedByte() & 0xFF) << 8) | (dis.readUnsignedByte() & 0xFF);
		dis.skipBytes(1);
	}

	public int getSectorNum() {
		return sectorNum;
	}

	public void setSectorNum(int sectorNum) {
		this.sectorNum = sectorNum;
	}

	public long getShootingTime() {
		return shootingTime;
	}

	public void setShootingTime(long shootingTime) {
		this.shootingTime = shootingTime;
	}

	public ImageFormat getFormat() {
		return format;
	}

	public void setFormat(ImageFormat format) {
		this.format = format;
	}

	public int getContinuousShootingNum() {
		return continuousShootingNum;
	}

	public void setContinuousShootingNum(int continuousShootingNum) {
		this.continuousShootingNum = continuousShootingNum;
	}

	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	public int getStorageStartAddress() {
		return storageStartAddress;
	}

	public void setStorageStartAddress(int storageStartAddress) {
		this.storageStartAddress = storageStartAddress;
	}

}
