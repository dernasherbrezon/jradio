package ru.r2cloud.jradio.il2p;

import java.io.DataInputStream;
import java.io.IOException;

public class Il2pHeader {

	private int payloadLength;
	private String destination;
	private String source;
	private int srcSsid;
	private int destSsid;
	private boolean ui;
	private int pid;
	private int control;
	private boolean hdrType;

	public Il2pHeader() {
		// do nothing
	}

	public Il2pHeader(DataInputStream dis) throws IOException {
		byte[] data = new byte[13];
		dis.readFully(data);
		payloadLength = readInt(data, 2, 7, 10);
		destination = readDecSixbit(data, 0);
		source = readDecSixbit(data, 6);
		srcSsid = data[12] & 0b1111;
		destSsid = (data[12] & 0b11110000) >> 4;
		ui = (data[0] & 0b0100_0000) > 0;
		pid = readInt(data, 1, 6, 4);
		control = readInt(data, 5, 6, 7);
		hdrType = (data[1] & 0x80) > 0;
	}

	private static String readDecSixbit(byte[] data, int offset) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			char cur = (char) (((data[offset + i] & 0x3F)) + 0x20);
			b.append(cur);
		}
		return b.toString();
	}

	private static int readInt(byte[] data, int offsetByte, int offsetBit, int length) {
		int result = 0;
		int mask = 1 << offsetBit;
		for (int i = 0; i < length; i++) {
			int bit = (data[offsetByte + i] & mask) >> offsetBit;
			result = result << 1;
			result = result | bit;
		}
		return result;
	}

	public int getPayloadLength() {
		return payloadLength;
	}

	public void setPayloadLength(int payloadLength) {
		this.payloadLength = payloadLength;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getSrcSsid() {
		return srcSsid;
	}

	public void setSrcSsid(int srcSsid) {
		this.srcSsid = srcSsid;
	}

	public int getDestSsid() {
		return destSsid;
	}

	public void setDestSsid(int destSsid) {
		this.destSsid = destSsid;
	}

	public boolean isUi() {
		return ui;
	}

	public void setUi(boolean ui) {
		this.ui = ui;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public boolean isHdrType() {
		return hdrType;
	}

	public void setHdrType(boolean hdrType) {
		this.hdrType = hdrType;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(source).append(" To ").append(destination);
		result.append(" <");
		result.append(control);
		result.append(" Pid=").append(pid);
		result.append(">");
		return result.toString();
	}
}
