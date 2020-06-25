package ru.r2cloud.jradio.udp;

import java.io.DataInputStream;
import java.io.IOException;

public class Header {

	private int srcPort;
	private int dstPort;
	private int length;
	private int checksum;

	public Header() {
		// do nothing
	}

	public Header(DataInputStream dis) throws IOException {
		srcPort = dis.readUnsignedShort();
		dstPort = dis.readUnsignedShort();
		length = dis.readUnsignedShort();
		checksum = dis.readUnsignedShort();
	}

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public int getDstPort() {
		return dstPort;
	}

	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

}
