package ru.r2cloud.jradio.bsusat1;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class RfImage {

	private int fullSize;
	private int frameNumber;
	private byte[] payload;

	public RfImage() {
		// do nothing
	}

	public RfImage(LittleEndianDataInputStream dis, int infoSize) throws IOException {
		dis.skipBytes(1);
		fullSize = dis.readUnsignedShort();
		frameNumber = dis.readUnsignedShort();
		payload = new byte[27 + infoSize - 6];
		dis.readFully(payload);
	}

	public int getFullSize() {
		return fullSize;
	}

	public void setFullSize(int fullSize) {
		this.fullSize = fullSize;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
	
}
