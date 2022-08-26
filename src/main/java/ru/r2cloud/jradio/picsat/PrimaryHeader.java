package ru.r2cloud.jradio.picsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

// looks similar to ccsds, but not
public class PrimaryHeader {

	public static final int LENGTH_BYTES = 6;

	private int packetVersion;
	private boolean packetType;
	private boolean secondaryHeader;
	private int applicationProcessId;
	private boolean levelFlag;
	private boolean payloadFlag;
	private int packetCategory;
	private int sequenceFlag;
	private int packetId;
	private int packetDataLength;

	public PrimaryHeader() {
		// do nothing
	}

	public PrimaryHeader(BitInputStream bis) throws IOException {
		packetVersion = bis.readUnsignedInt(3);
		packetType = bis.readBoolean();
		secondaryHeader = bis.readBoolean();
		applicationProcessId = bis.readUnsignedInt(4);
		levelFlag = bis.readBoolean();
		payloadFlag = bis.readBoolean();
		packetCategory = bis.readUnsignedInt(5);
		sequenceFlag = bis.readUnsignedInt(2);
		packetId = bis.readUnsignedInt(14);
		packetDataLength = bis.readUnsignedInt(16);
	}

	public int getPacketVersion() {
		return packetVersion;
	}

	public void setPacketVersion(int packetVersion) {
		this.packetVersion = packetVersion;
	}

	public boolean isPacketType() {
		return packetType;
	}
	
	public void setPacketType(boolean packetType) {
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

	public boolean isLevelFlag() {
		return levelFlag;
	}

	public void setLevelFlag(boolean levelFlag) {
		this.levelFlag = levelFlag;
	}

	public boolean isPayloadFlag() {
		return payloadFlag;
	}

	public void setPayloadFlag(boolean payloadFlag) {
		this.payloadFlag = payloadFlag;
	}

	public int getPacketCategory() {
		return packetCategory;
	}

	public void setPacketCategory(int packetCategory) {
		this.packetCategory = packetCategory;
	}

	public int getSequenceFlag() {
		return sequenceFlag;
	}

	public void setSequenceFlag(int sequenceFlag) {
		this.sequenceFlag = sequenceFlag;
	}

	public int getPacketId() {
		return packetId;
	}

	public void setPacketId(int packetId) {
		this.packetId = packetId;
	}

	public int getPacketDataLength() {
		return packetDataLength;
	}

	public void setPacketDataLength(int packetDataLength) {
		this.packetDataLength = packetDataLength;
	}

}
