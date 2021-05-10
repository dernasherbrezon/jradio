package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class SecondaryHeader {

	private int tmPacketPusVersionNumber;
	private int spacecraftTimeReferenceStatus;
	private int serviceTypeId;
	private int messageSubtypeId;
	private int messageTypeCounter;
	private int destinationId;
	private long time;
	private int sid;

	public SecondaryHeader() {
		// do nothing
	}

	public SecondaryHeader(BitInputStream bis) throws IOException {
		tmPacketPusVersionNumber = bis.readUnsignedInt(4);
		spacecraftTimeReferenceStatus = bis.readUnsignedInt(4);
		serviceTypeId = bis.readUnsignedByte();
		messageSubtypeId = bis.readUnsignedByte();
		messageTypeCounter = bis.readUnsignedShort();
		destinationId = bis.readUnsignedShort();
		time = bis.readUnsignedLong(32);
		bis.skipBits(16 + 8);
		sid = bis.readUnsignedByte();
	}

	public int getTmPacketPusVersionNumber() {
		return tmPacketPusVersionNumber;
	}

	public void setTmPacketPusVersionNumber(int tmPacketPusVersionNumber) {
		this.tmPacketPusVersionNumber = tmPacketPusVersionNumber;
	}

	public int getSpacecraftTimeReferenceStatus() {
		return spacecraftTimeReferenceStatus;
	}

	public void setSpacecraftTimeReferenceStatus(int spacecraftTimeReferenceStatus) {
		this.spacecraftTimeReferenceStatus = spacecraftTimeReferenceStatus;
	}

	public int getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public int getMessageSubtypeId() {
		return messageSubtypeId;
	}

	public void setMessageSubtypeId(int messageSubtypeId) {
		this.messageSubtypeId = messageSubtypeId;
	}

	public int getMessageTypeCounter() {
		return messageTypeCounter;
	}

	public void setMessageTypeCounter(int messageTypeCounter) {
		this.messageTypeCounter = messageTypeCounter;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

}
