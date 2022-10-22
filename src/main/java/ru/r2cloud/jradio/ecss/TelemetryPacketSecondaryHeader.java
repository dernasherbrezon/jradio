package ru.r2cloud.jradio.ecss;

import java.io.IOException;

import ru.r2cloud.jradio.ccsds.PField;
import ru.r2cloud.jradio.ccsds.TimeCode;
import ru.r2cloud.jradio.util.BitInputStream;

// as defined in ECSS-E-ST-70-41C Section 7.4.3.1
public class TelemetryPacketSecondaryHeader {

	private int tmPacketPusVersionNumber;
	private int spacecraftTimeReferenceStatus;
	private int serviceTypeId;
	private int messageSubtypeId;
	private int messageTypeCounter;
	private int destinationId;
	private TimeCode time;

	public TelemetryPacketSecondaryHeader() {
		// do nothing
	}

	public TelemetryPacketSecondaryHeader(BitInputStream bis, PField pfield) throws IOException {
		tmPacketPusVersionNumber = bis.readUnsignedInt(4);
		spacecraftTimeReferenceStatus = bis.readUnsignedInt(4);
		serviceTypeId = bis.readUnsignedByte();
		messageSubtypeId = bis.readUnsignedByte();
		messageTypeCounter = bis.readUnsignedShort();
		destinationId = bis.readUnsignedShort();
		time = new TimeCode(bis, pfield);
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

	public TimeCode getTime() {
		return time;
	}

	public void setTime(TimeCode time) {
		this.time = time;
	}

}
