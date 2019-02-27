package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class PrimaryHeader {

	public static final int LENGTH_BYTES = 6;

	private int packetVersion;
	private int packetType;
	private boolean secondaryHeader;
	private int applicationProcessId;
	private int sequenceFlag;
	private int packetName;
	private int packetDataLength;
	
	public PrimaryHeader() {
		//do nothing
	}

	public PrimaryHeader(BitInputStream bis) throws IOException {
		packetVersion = bis.readUnsignedInt(3);
		packetType = bis.readUnsignedInt(1);
		secondaryHeader = bis.readBoolean();
		applicationProcessId = bis.readUnsignedInt(11);
		sequenceFlag = bis.readUnsignedInt(2);
		packetName = bis.readUnsignedInt(14);
		packetDataLength = bis.readUnsignedInt(16);
	}

	public int getPacketVersion() {
		return packetVersion;
	}

	public void setPacketVersion(int packetVersion) {
		this.packetVersion = packetVersion;
	}

	public int getPacketType() {
		return packetType;
	}

	public void setPacketType(int packetType) {
		this.packetType = packetType;
	}

	public boolean isSecondaryHeader() {
		return secondaryHeader;
	}

	public void setSecondaryHeader(boolean secondaryHeader) {
		this.secondaryHeader = secondaryHeader;
	}

	public int getApplicationProcessId() {
		return applicationProcessId;
	}

	public void setApplicationProcessId(int applicationProcessId) {
		this.applicationProcessId = applicationProcessId;
	}

	public int getSequenceFlag() {
		return sequenceFlag;
	}

	public void setSequenceFlag(int sequenceFlag) {
		this.sequenceFlag = sequenceFlag;
	}

	public int getPacketName() {
		return packetName;
	}

	public void setPacketName(int packetName) {
		this.packetName = packetName;
	}

	public int getPacketDataLength() {
		return packetDataLength;
	}

	public void setPacketDataLength(int packetDataLength) {
		this.packetDataLength = packetDataLength;
	}

}
