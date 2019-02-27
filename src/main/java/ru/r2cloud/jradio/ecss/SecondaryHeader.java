package ru.r2cloud.jradio.ecss;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class SecondaryHeader {

	public static final int LENGTH_BYTES = 13;

	private int pusVersion;
	private int timeReference;
	private int serviceType;
	private int messageSubtype;
	private int typeCounter;
	private int destinationId;
	private int day;
	private long millisecondsOfTheDay;
	
	public SecondaryHeader() {
		//do nothing
	}

	public SecondaryHeader(BitInputStream bis) throws IOException {
		pusVersion = bis.readUnsignedInt(4);
		timeReference = bis.readUnsignedInt(4);
		serviceType = bis.readUnsignedInt(8);
		messageSubtype = bis.readUnsignedInt(8);
		typeCounter = bis.readUnsignedInt(16);
		destinationId = bis.readUnsignedInt(16);
		day = bis.readUnsignedInt(16);
		millisecondsOfTheDay = bis.readUnsignedLong(32);
	}

	public int getPusVersion() {
		return pusVersion;
	}

	public void setPusVersion(int pusVersion) {
		this.pusVersion = pusVersion;
	}

	public int getTimeReference() {
		return timeReference;
	}

	public void setTimeReference(int timeReference) {
		this.timeReference = timeReference;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public int getMessageSubtype() {
		return messageSubtype;
	}

	public void setMessageSubtype(int messageSubtype) {
		this.messageSubtype = messageSubtype;
	}

	public int getTypeCounter() {
		return typeCounter;
	}

	public void setTypeCounter(int typeCounter) {
		this.typeCounter = typeCounter;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public long getMillisecondsOfTheDay() {
		return millisecondsOfTheDay;
	}

	public void setMillisecondsOfTheDay(long millisecondsOfTheDay) {
		this.millisecondsOfTheDay = millisecondsOfTheDay;
	}

}
