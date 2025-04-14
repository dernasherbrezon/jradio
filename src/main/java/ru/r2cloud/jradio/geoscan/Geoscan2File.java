package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Geoscan2File {

	private long offset;
	private int filenum;
	private byte[] data;

	public Geoscan2File() {
		// do nothing
	}

	public Geoscan2File(LittleEndianDataInputStream dis) throws IOException {
		offset = dis.readUnsignedInt();
		filenum = dis.readUnsignedShort();
		data = new byte[54];
		dis.readFully(data);
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public int getFilenum() {
		return filenum;
	}

	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
