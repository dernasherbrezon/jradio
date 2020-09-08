package ru.r2cloud.jradio.falconsat3;

public class PacsatDirEntry {

	private PacsatFileHeader header;
	private boolean newest;
	private long newTime;
	private long oldTime;
	private long fileId;
	
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

	public boolean isNewest() {
		return newest;
	}

	public void setNewest(boolean newest) {
		this.newest = newest;
	}

	public long getNewTime() {
		return newTime;
	}

	public void setNewTime(long newTime) {
		this.newTime = newTime;
	}

	public long getOldTime() {
		return oldTime;
	}

	public void setOldTime(long oldTime) {
		this.oldTime = oldTime;
	}

}
