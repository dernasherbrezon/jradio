package ru.r2cloud.jradio.smogp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileFragment {

	private long timestamp;
	private int packetIndex;
	private int packetCount;
	private int fileType;
	private int pageAddr;
	private int fileSize;
	private long fileTimestamp;
	private String filename;
	private byte[] data;

	public FileFragment() {
		// do nothing
	}

	public FileFragment(LittleEndianDataInputStream dis) throws IOException, UncorrectableException {
		timestamp = dis.readUnsignedInt();
		packetIndex = dis.readUnsignedShort();
		packetCount = dis.readUnsignedShort();
		if (packetCount == 0 || packetCount > 128) {
			throw new UncorrectableException("invalid packet count");
		}
		if (packetIndex >= packetCount) {
			throw new UncorrectableException("invalid packet index");
		}
		fileType = dis.readUnsignedByte();
		pageAddr = dis.readUnsignedShort();
		fileSize = (dis.readUnsignedByte() << 16) | (dis.readUnsignedByte() << 8) | (dis.readUnsignedByte());
		if ((fileSize / 217) > packetCount || ((fileSize / 217) + 1) < packetCount) {
			throw new UncorrectableException("invalid file size");
		}
		fileTimestamp = dis.readUnsignedInt();
		byte[] filenameBytes = new byte[10];
		dis.readFully(filenameBytes);
		filename = new String(filenameBytes, StandardCharsets.ISO_8859_1);
		data = new byte[217];
		dis.readFully(data);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getPacketIndex() {
		return packetIndex;
	}

	public void setPacketIndex(int packetIndex) {
		this.packetIndex = packetIndex;
	}

	public int getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
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

	public long getFileTimestamp() {
		return fileTimestamp;
	}

	public void setFileTimestamp(long fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
