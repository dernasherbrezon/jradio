package ru.r2cloud.jradio.sputnix;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileData {

	private long offset;
	private byte[] data;

	public FileData() {
		// do nothing
	}

	public FileData(LittleEndianDataInputStream dis) throws IOException {
		dis.skipBytes(1);
		offset = dis.readUnsignedInt();
		data = new byte[dis.available()];
		dis.readFully(data);
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
