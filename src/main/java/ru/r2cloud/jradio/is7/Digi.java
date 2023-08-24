package ru.r2cloud.jradio.is7;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Digi {

	private int size;
	private String data;

	public Digi() {
		// do nothing
	}

	public Digi(LittleEndianDataInputStream dis) throws IOException {
		size = dis.readUnsignedShort();
		data = dis.readRemainingString();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
