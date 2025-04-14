package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class Geoscan2Header {

	private int satId;
	private int fieldSize;

	public Geoscan2Header() {
		// do nothing
	}

	public Geoscan2Header(LittleEndianDataInputStream dis) throws IOException {
		satId = dis.readUnsignedByte();
		dis.skipBytes(1);
		fieldSize = dis.readUnsignedByte();
		dis.skipBytes(2);
	}

	public int getSatId() {
		return satId;
	}

	public void setSatId(int satId) {
		this.satId = satId;
	}

	public int getFieldSize() {
		return fieldSize;
	}

	public void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}

}
