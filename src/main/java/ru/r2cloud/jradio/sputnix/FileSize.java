package ru.r2cloud.jradio.sputnix;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class FileSize {

	private long size;

	public FileSize() {
		// do nothing
	}

	public FileSize(LittleEndianDataInputStream dis) throws IOException {
		size = dis.readUnsignedInt();
	}
	
	public long getSize() {
		return size;
	}
	
	public void setSize(long size) {
		this.size = size;
	}

}
