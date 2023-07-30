package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileDl {

	private long fileId;
	private int fileType;
	private int dataCount;
	private int blockNumber;
	private Long fileSize;
	private Long fileCloseTime;
	private byte[] payload;

	public FileDl() {
		// do nothing
	}

	public FileDl(LittleEndianDataInputStream ldis) throws IOException {
		fileId = ldis.readUnsignedInt();
		fileType = ldis.readUnsignedByte();
		dataCount = ldis.readUnsignedByte();
		blockNumber = ldis.readUnsignedShort();
		if (blockNumber % 2 == 0) {
			fileCloseTime = ldis.readUnsignedInt();
		} else {
			fileSize = ldis.readUnsignedInt();
		}
		payload = new byte[dataCount];
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

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getFileCloseTime() {
		return fileCloseTime;
	}

	public void setFileCloseTime(Long fileCloseTime) {
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
		return "FileDl [fileId=" + fileId + ", fileType=" + fileType + ", dataCount=" + dataCount + ", blockNumber=" + blockNumber + ", fileSize=" + fileSize + ", fileCloseTime=" + fileCloseTime + "]";
	}

}
