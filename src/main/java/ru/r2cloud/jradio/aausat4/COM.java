package ru.r2cloud.jradio.aausat4;

import java.io.DataInputStream;
import java.io.IOException;

public class COM {

	private int bootCount;
	private int packetsReceived;
	private int packetsSend;
	private short latestRssi;
	private int latestBitCorrection;
	private int latestByteCorrection;
	
	public COM() {
		//do nothing
	}

	public COM(DataInputStream data) throws IOException {
		bootCount = data.readUnsignedShort() & 0x1fff;
		packetsReceived = data.readUnsignedShort();
		packetsSend = data.readUnsignedShort();
		latestRssi = data.readShort();
		latestBitCorrection = data.readUnsignedByte();
		latestByteCorrection = data.readUnsignedByte();
	}

	public int getBootCount() {
		return bootCount;
	}

	public void setBootCount(int bootCount) {
		this.bootCount = bootCount;
	}

	public int getPacketsReceived() {
		return packetsReceived;
	}

	public void setPacketsReceived(int packetsReceived) {
		this.packetsReceived = packetsReceived;
	}

	public int getPacketsSend() {
		return packetsSend;
	}

	public void setPacketsSend(int packetsSend) {
		this.packetsSend = packetsSend;
	}

	public short getLatestRssi() {
		return latestRssi;
	}

	public void setLatestRssi(short latestRssi) {
		this.latestRssi = latestRssi;
	}

	public int getLatestBitCorrection() {
		return latestBitCorrection;
	}

	public void setLatestBitCorrection(int latestBitCorrection) {
		this.latestBitCorrection = latestBitCorrection;
	}

	public int getLatestByteCorrection() {
		return latestByteCorrection;
	}

	public void setLatestByteCorrection(int latestByteCorrection) {
		this.latestByteCorrection = latestByteCorrection;
	}

}
