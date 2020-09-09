package ru.r2cloud.jradio.falconsat3;

public class PacsatFile {

	private PacsatFileHeader header;
	private long fileId;
	private byte[] body;

	public void setBody(byte[] body) {
		this.body = body;
	}

	public byte[] getBody() {
		return body;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public PacsatFileHeader getHeader() {
		return header;
	}

	public void setHeader(PacsatFileHeader header) {
		this.header = header;
	}
}
