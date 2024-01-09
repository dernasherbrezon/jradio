package ru.r2cloud.jradio.sstk1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class StratosatTk1Header {

	private int satId;
	private int fieldSize;
	private int messageType;
	private int packetOffset;

	public StratosatTk1Header() {
		// do nothing
	}

	public StratosatTk1Header(LittleEndianDataInputStream dis) throws IOException {
		satId = dis.readUnsignedByte();
		dis.skipBytes(1);
		fieldSize = dis.readUnsignedByte();
		messageType = dis.readUnsignedShort();
		switch (messageType) {
		case 0x0905:
			packetOffset = dis.readUnsignedShort();
			dis.skipBytes(1);
			break;
		case 0x9820:
			packetOffset = dis.readUnsigned3Bytes();
			break;
		default:
			break;
		}
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

}
