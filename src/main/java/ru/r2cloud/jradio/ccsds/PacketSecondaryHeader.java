package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class PacketSecondaryHeader {

	private int pusHeader;
	private int serviceType;
	private int serviceSubType;

	public PacketSecondaryHeader() {
		// do nothing
	}

	public PacketSecondaryHeader(BitInputStream bis) throws IOException {
		pusHeader = bis.readUnsignedByte();
		serviceType = bis.readUnsignedByte();
		serviceSubType = bis.readUnsignedByte();
	}

	public int getPusHeader() {
		return pusHeader;
	}

	public void setPusHeader(int pusHeader) {
		this.pusHeader = pusHeader;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public int getServiceSubType() {
		return serviceSubType;
	}

	public void setServiceSubType(int serviceSubType) {
		this.serviceSubType = serviceSubType;
	}

}
