package ru.r2cloud.jradio.falconsat3;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.crc.Crc16Ccitt;
import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;
import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Qst {

	private boolean lengthPresent;
	private boolean eof;
	private long fileId;
	private FileType type;
	private int offset;
	private byte[] data;

	public Qst() {
		// do nothing
	}

	public Qst(DataInputStream dis) throws IOException, UncorrectableException {
		byte[] bytes = new byte[dis.available()];
		dis.readFully(bytes);
		if (Crc16Ccitt.calculate(bytes) != 0) {
			throw new UncorrectableException("crc mismatch");
		}
		DataInputStream newDis = new DataInputStream(new ByteArrayInputStream(bytes));
		LittleEndianDataInputStream ldis = new LittleEndianDataInputStream(newDis);
		int flags = ldis.readUnsignedByte();
		lengthPresent = (flags & 0b1) > 0;
		eof = ((flags >> 1) & 0b1) > 0;
		fileId = ldis.readUnsignedInt();
		type = FileType.valueOfType(ldis.readUnsignedByte());
		offset = ldis.readUnsigned3Bytes();
		data = new byte[ldis.available() - 2];
		ldis.readFully(data);
	}

	public boolean isLengthPresent() {
		return lengthPresent;
	}

	public void setLengthPresent(boolean lengthPresent) {
		this.lengthPresent = lengthPresent;
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
