package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanHeader {

	private int satId;
	private int fieldSize;
	private int messageType;
	private int packetOffset;
	private int subsystemNumber;

	public GeoscanHeader() {
		// do nothing
	}

	public GeoscanHeader(LittleEndianDataInputStream dis) throws IOException {
		satId = dis.readUnsignedByte();
		dis.skipBytes(1);
		fieldSize = dis.readUnsignedByte();
		messageType = dis.readUnsignedShort();
		packetOffset = dis.readUnsignedShort();
		subsystemNumber = dis.readUnsignedByte();
	}

	public int getSatId() {
		return satId;
	}

	public void setSatId(int satId) {
		this.satId = satId;
	}

	public int getFieldSize() {
		return fieldSize;
	}

	public void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getPacketOffset() {
		return packetOffset;
	}

	public void setPacketOffset(int packetOffset) {
		this.packetOffset = packetOffset;
	}

	public int getSubsystemNumber() {
		return subsystemNumber;
	}

	public void setSubsystemNumber(int subsystemNumber) {
		this.subsystemNumber = subsystemNumber;
	}

}
