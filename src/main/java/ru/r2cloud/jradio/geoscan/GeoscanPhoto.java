package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanPhoto {

	private byte[] data;

	public GeoscanPhoto() {
		// do nothing
	}

	public GeoscanPhoto(LittleEndianDataInputStream dis) throws IOException {
		data = new byte[dis.available()];
		dis.readFully(data);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
