package ru.r2cloud.jradio.kunspf;

import java.io.DataInputStream;
import java.io.IOException;

public class KunsPfImageChunk {

	private int imageBlock;
	private byte[] imageChunk;

	public KunsPfImageChunk() {
		// do nothing
	}

	public KunsPfImageChunk(DataInputStream dis) throws IOException {
		imageBlock = dis.readUnsignedShort();
		imageChunk = new byte[128];
		dis.readFully(imageChunk);
	}

	public int getImageBlock() {
		return imageBlock;
	}

	public void setImageBlock(int imageBlock) {
		this.imageBlock = imageBlock;
	}

	public byte[] getImageChunk() {
		return imageChunk;
	}

	public void setImageChunk(byte[] imageChunk) {
		this.imageChunk = imageChunk;
	}

}
