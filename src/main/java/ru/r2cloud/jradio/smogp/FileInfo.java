package ru.r2cloud.jradio.smogp;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileInfo {

	private long timestamp;
	private File[] files;

	public FileInfo() {
		// do nothing
	}

	public FileInfo(LittleEndianDataInputStream dis) throws IOException {
		timestamp = dis.readUnsignedInt();
		files = new File[5];
		for (int i = 0; i < files.length; i++) {
			files[i] = new File(dis);
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

}
