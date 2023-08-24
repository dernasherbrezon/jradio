package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class ResponseLastLog {

	private long timestamp1;
	private long timestamp2;
	private int priority;
	private String message;

	public ResponseLastLog() {
		// do nothing
	}

	public ResponseLastLog(LittleEndianDataInputStream dis) throws IOException {
		timestamp1 = dis.readUnsignedInt();
		timestamp2 = dis.readUnsignedInt();
		priority = dis.readUnsignedByte();
		message = dis.readRemainingString();
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
