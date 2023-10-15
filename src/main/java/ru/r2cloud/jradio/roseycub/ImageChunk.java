package ru.r2cloud.jradio.roseycub;

import java.io.DataInputStream;
import java.io.IOException;

public class ImageChunk {

	private int sequenceId;
	private boolean isPreview;
	private int elementId;
	private byte[] data;

	public ImageChunk() {
		// do nothing
	}

	public ImageChunk(DataInputStream dis) throws IOException {
		sequenceId = dis.readUnsignedShort();
		isPreview = (dis.readUnsignedByte() > 0);
		elementId = dis.readUnsignedShort();
		data = new byte[dis.available()];
		dis.readFully(data);
	}

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public boolean isPreview() {
		return isPreview;
	}

	public void setPreview(boolean isPreview) {
		this.isPreview = isPreview;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
