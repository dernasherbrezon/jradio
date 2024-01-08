package ru.r2cloud.jradio.sputnix;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileHeader {

	private int mode;
	private int sesId;
	private int bs;
	private long offset;
	private String filename;

	public FileHeader() {
		// do nothing
	}

	public FileHeader(LittleEndianDataInputStream dis) throws IOException {
		mode = dis.readUnsignedByte();
		sesId = dis.readUnsignedByte();
		bs = dis.readUnsignedShort();
		offset = dis.readUnsignedInt();
		dis.skipBytes(2);
		byte[] filenameBytes = new byte[dis.available()];
		dis.readFully(filenameBytes);
		filename = new String(filenameBytes, StandardCharsets.US_ASCII).trim();
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getSesId() {
		return sesId;
	}

	public void setSesId(int sesId) {
		this.sesId = sesId;
	}

	public int getBs() {
		return bs;
	}

	public void setBs(int bs) {
		this.bs = bs;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
