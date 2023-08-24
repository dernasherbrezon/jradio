package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ResponseLastDrop {

	private long timestamp1;
	private long timestamp2;
	private int sizeMsg;
	private int crc;

	public ResponseLastDrop() {
		// do nothing
	}

	public ResponseLastDrop(LittleEndianDataInputStream ldis) throws IOException {
		timestamp1 = ldis.readUnsignedInt();
		timestamp2 = ldis.readUnsignedInt();
		sizeMsg = ldis.readUnsignedShort();
		crc = ldis.readUnsignedShort();
	}

	public long getTimestamp1() {
		return timestamp1;
	}

	public void setTimestamp1(long timestamp1) {
		this.timestamp1 = timestamp1;
	}

	public long getTimestamp2() {
		return timestamp2;
	}

	public void setTimestamp2(long timestamp2) {
		this.timestamp2 = timestamp2;
	}

	public int getSizeMsg() {
		return sizeMsg;
	}

	public void setSizeMsg(int sizeMsg) {
		this.sizeMsg = sizeMsg;
	}

	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}

}
