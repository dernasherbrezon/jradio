package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanHeader {

	private int satId;
	private long info;
	private int offset;

	public GeoscanHeader() {
		// do nothing
	}

	public GeoscanHeader(LittleEndianDataInputStream dis) throws IOException {
		satId = dis.readUnsignedByte();
		info = dis.readUnsignedInt();
		offset = dis.readUnsignedShort();
	}

	public int getSatId() {
		return satId;
	}

	public void setSatId(int satId) {
		this.satId = satId;
	}

	public long getInfo() {
		return info;
	}

	public void setInfo(long info) {
		this.info = info;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
