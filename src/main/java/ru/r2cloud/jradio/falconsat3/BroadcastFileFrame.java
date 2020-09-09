package ru.r2cloud.jradio.falconsat3;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class BroadcastFileFrame {

	private boolean eof;
	private long fileId;
	private FileType type;
	private int offset;
	private byte[] data;

	public BroadcastFileFrame() {
		// do nothing
	}

	public BroadcastFileFrame(DataInputStream dis) throws IOException {
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(dis);
		int flags = ldis.readUnsignedByte();
		eof = ((flags >> 1) & 0b1) > 0;
		fileId = ldis.readUnsignedInt();
		type = FileType.valueOfType(ldis.readUnsignedByte());
		offset = ldis.readUnsigned3Bytes();
		int length;
		if ((flags & 0b1) > 0) {
			// It is the number of bits that are to be used in the data field.
			length = ldis.readUnsignedShort() / 8;
		} else {
			length = ldis.available();
		}
		data = new byte[length - 2];
		ldis.readFully(data);
	}

	public boolean isEof() {
		return eof;
	}

	public void setEof(boolean eof) {
		this.eof = eof;
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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
