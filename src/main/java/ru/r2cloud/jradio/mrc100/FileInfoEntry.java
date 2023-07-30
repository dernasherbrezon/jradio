package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileInfoEntry {

	private long fileId;
	private int fileType;
	private long fileSize;
	private long fileCloseTime;

	public FileInfoEntry() {
		// do nothing
	}

	public FileInfoEntry(LittleEndianDataInputStream ldis) throws IOException {
		fileId = ldis.readUnsignedInt();
		fileType = ldis.readUnsignedByte();
		fileSize = ldis.readUnsignedInt();
		fileCloseTime = ldis.readUnsignedInt();
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public long getFileCloseTime() {
		return fileCloseTime;
	}

	public void setFileCloseTime(long fileCloseTime) {
		this.fileCloseTime = fileCloseTime;
	}

}
