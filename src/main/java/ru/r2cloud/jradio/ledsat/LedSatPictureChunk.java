package ru.r2cloud.jradio.ledsat;

import java.io.DataInputStream;
import java.io.IOException;

public class LedSatPictureChunk {

	private int packetNumber;
	private byte[] jpegImageData;

	public LedSatPictureChunk() {
		// do nothing
	}

	public LedSatPictureChunk(DataInputStream dis) throws IOException {
		packetNumber = dis.readUnsignedByte();
		jpegImageData = new byte[dis.available()];
		dis.readFully(jpegImageData);
	}

	public int getPacketNumber() {
		return packetNumber;
	}

	public void setPacketNumber(int packetNumber) {
		this.packetNumber = packetNumber;
	}

	public byte[] getJpegImageData() {
		return jpegImageData;
	}

	public void setJpegImageData(byte[] jpegImageData) {
		this.jpegImageData = jpegImageData;
	}

}
