package ru.r2cloud.jradio.sharjahsat;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Sharjahsat1Header {

	private String identifier;
	private int tmId;
	private int dataLength;
	private long packetCounter;

	public Sharjahsat1Header() {
		// do nothing
	}

	public Sharjahsat1Header(LittleEndianDataInputStream dis) throws IOException {
		identifier = dis.readString(4);
		tmId = dis.readUnsignedByte();
		dataLength = dis.readUnsignedByte();
		packetCounter = dis.readUnsignedInt();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public int getTmId() {
		return tmId;
	}

	public void setTmId(int tmId) {
		this.tmId = tmId;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public long getPacketCounter() {
		return packetCounter;
	}

	public void setPacketCounter(long packetCounter) {
		this.packetCounter = packetCounter;
	}

}
