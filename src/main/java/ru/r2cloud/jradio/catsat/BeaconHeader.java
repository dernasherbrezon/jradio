package ru.r2cloud.jradio.catsat;

import java.io.DataInputStream;
import java.io.IOException;

public class BeaconHeader {

	private int protocolVersion;
	private int type;
	private int version;
	private int satid;

	public BeaconHeader() {
		// do nothing
	}

	public BeaconHeader(DataInputStream dis) throws IOException {
		protocolVersion = dis.readUnsignedByte();
		type = dis.readUnsignedByte();
		version = dis.readUnsignedByte();
		satid = dis.readUnsignedShort();
	}

	public int getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(int protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getSatid() {
		return satid;
	}

	public void setSatid(int satid) {
		this.satid = satid;
	}

}
