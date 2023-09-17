package ru.r2cloud.jradio.geoscan;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class GeoscanFile {

	private byte[] data;

	public GeoscanFile() {
		// do nothing
	}

	public GeoscanFile(LittleEndianDataInputStream dis, int length) throws IOException {
		data = new byte[length];
		dis.readFully(data);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
