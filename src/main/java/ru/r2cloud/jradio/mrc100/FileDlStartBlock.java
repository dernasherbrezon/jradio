package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileDlStartBlock {

	private long fileId;
	private int fileType;
	private int fileSize;
	private long fileCloseTime;
	private byte[] payload;

	public FileDlStartBlock() {
		// do nothing
	}

	public FileDlStartBlock(LittleEndianDataInputStream ldis) throws IOException {
		fileId = ldis.readUnsignedInt();
		fileType = ldis.readUnsignedByte();
		fileSize = ldis.readUnsigned3Bytes();
		fileCloseTime = ldis.readUnsignedInt();
		int payloadSize;
		if (fileSize < 104) {
			payloadSize = fileSize;
		} else {
			payloadSize = 104;
		}
		payload = new byte[payloadSize];
		ldis.readFully(payload);
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

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public long getFileCloseTime() {
		return fileCloseTime;
	}

	public void setFileCloseTime(long fileCloseTime) {
		this.fileCloseTime = fileCloseTime;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "FileDlStartBlock [fileId=" + fileId + ", fileType=" + fileType + ", fileSize=" + fileSize + ", fileCloseTime=" + fileCloseTime + "]";
	}

}
