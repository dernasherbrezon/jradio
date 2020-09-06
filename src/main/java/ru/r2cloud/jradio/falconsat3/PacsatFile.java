package ru.r2cloud.jradio.falconsat3;

import java.util.HashSet;
import java.util.Set;

public class PacsatFile {

	private PacsatFileHeader header;
	private long fileId;
	private byte[] body;

	private Set<Integer> holes = new HashSet<>();

	public void append(byte[] part, int offset) {
		System.arraycopy(part, 0, body, offset, part.length);
		holes.remove(offset);
		int nextExpectedOffset = offset + part.length;
		// add hole of the next offset
		// if this is the last chunk, then do not it's offset
		if (nextExpectedOffset < body.length) {
			holes.add(nextExpectedOffset);
		}
	}

	public boolean isComplete() {
		return holes.isEmpty();
	}

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
