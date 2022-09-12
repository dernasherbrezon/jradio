package ru.r2cloud.jradio.fees;

import java.io.DataInputStream;
import java.io.IOException;

public class FeesHeader {

	private int msgTypeId0;
	private int msgTypeId1;
	private int msgTypeId2;

	public FeesHeader() {
		// do nothing
	}

	public FeesHeader(DataInputStream dis) throws IOException {
		msgTypeId0 = dis.readUnsignedByte();
		msgTypeId1 = dis.readUnsignedByte();
		msgTypeId2 = dis.readUnsignedByte();
	}

	public int getMsgTypeId0() {
		return msgTypeId0;
	}

	public void setMsgTypeId0(int msgTypeId0) {
		this.msgTypeId0 = msgTypeId0;
	}

	public int getMsgTypeId1() {
		return msgTypeId1;
	}

	public void setMsgTypeId1(int msgTypeId1) {
		this.msgTypeId1 = msgTypeId1;
	}

	public int getMsgTypeId2() {
		return msgTypeId2;
	}

	public void setMsgTypeId2(int msgTypeId2) {
		this.msgTypeId2 = msgTypeId2;
	}

}
