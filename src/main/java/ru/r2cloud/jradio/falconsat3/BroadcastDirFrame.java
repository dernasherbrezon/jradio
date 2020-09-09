package ru.r2cloud.jradio.falconsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BroadcastDirFrame {

	private boolean eof;
	private boolean newest;
	private long fileId;
	private FileType type;
	private long offset;
	private byte[] data;
	private long newTime;
	private long oldTime;

	public BroadcastDirFrame() {
		// do nothing
	}

	public BroadcastDirFrame(DataInputStream dis) throws IOException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		int flags = ldis.readUnsignedByte();
		eof = ((flags >> 5) & 0b1) > 0;
		newest = ((flags >> 6) & 0b1) > 0;
		fileId = ldis.readUnsignedInt();
		offset = ldis.readUnsignedInt();

		oldTime = ldis.readUnsignedInt();
		newTime = ldis.readUnsignedInt();
		data = new byte[ldis.available() - 2];
		ldis.readFully(data);
	}

	public boolean isEof() {
		return eof;
	}

	public void setEof(boolean eof) {
		this.eof = eof;
	}

	public boolean isNewest() {
		return newest;
	}

	public void setNewest(boolean newest) {
		this.newest = newest;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
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
