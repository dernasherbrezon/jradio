package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class InformationMessage {

	private int respType;
	private int index;
	private int used;
	private String message;

	public InformationMessage() {
		// do nothing
	}

	public InformationMessage(LittleEndianDataInputStream dis) throws IOException {
		respType = dis.readUnsignedShort();
		dis.skipBytes(2);
		index = dis.readUnsignedByte();
		used = dis.readUnsignedByte();
		message = dis.readRemainingString();
	}

	public int getRespType() {
		return respType;
	}

	public void setRespType(int respType) {
		this.respType = respType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
