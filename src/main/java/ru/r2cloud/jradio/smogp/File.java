package ru.r2cloud.jradio.smogp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class File {

	private int fileId;
	private int fileType;
	private int pageAddr;
	private int fileSize;
	private long timestamp;
	private String filename;

	public File() {
		// do nothing
	}

	public File(LittleEndianDataInputStream dis) throws IOException {
		fileId = dis.readUnsignedByte();
		fileType = dis.readUnsignedByte();
		pageAddr = dis.readUnsignedShort();
		fileSize = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		timestamp = dis.readUnsignedInt();
		byte[] filenameBytes = new byte[10];
		dis.readFully(filenameBytes);
		filename = new String(filenameBytes, StandardCharsets.ISO_8859_1);
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public int getPageAddr() {
		return pageAddr;
	}

	public void setPageAddr(int pageAddr) {
		this.pageAddr = pageAddr;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
