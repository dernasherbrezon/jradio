package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileInfo {

	private long timestamp;
	private FileInfoEntry[] entries;

	public FileInfo() {
		// do nothing
	}

	public FileInfo(LittleEndianDataInputStream ldis) throws IOException {
		timestamp = ldis.readUnsignedInt();
		entries = new FileInfoEntry[8];
		for (int i = 0; i < entries.length; i++) {
			entries[i] = new FileInfoEntry(ldis);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public FileInfoEntry[] getEntries() {
		return entries;
	}

	public void setEntries(FileInfoEntry[] entries) {
		this.entries = entries;
	}

}
