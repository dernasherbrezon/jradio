package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class MailboxMessage {

	private long timestamp1;
	private long timestamp2;
	private String callsign;
	private int index;
	private byte[] value;

	public MailboxMessage() {
		// do nothing
	}

	public MailboxMessage(LittleEndianDataInputStream dis) throws IOException {
		timestamp1 = dis.readUnsignedInt();
		timestamp2 = dis.readUnsignedInt();
		callsign = dis.readString(6);
		index = dis.readUnsignedByte();
		value = new byte[dis.available()];
		dis.readFully(value);
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

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

}
