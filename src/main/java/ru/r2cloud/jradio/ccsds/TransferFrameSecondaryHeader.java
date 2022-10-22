package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

//as defined at CCSDS 132.0-B-3 Section 4.1.3
public class TransferFrameSecondaryHeader {

	private int version;
	private int length;
	private byte[] payload;

	public TransferFrameSecondaryHeader() {
		// do nothing
	}

	public TransferFrameSecondaryHeader(BitInputStream bis) throws IOException {
		version = bis.readUnsignedInt(2);
		length = bis.readUnsignedInt(6);
		payload = new byte[length];
		bis.readFully(payload);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
