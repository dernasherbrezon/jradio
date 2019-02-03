package ru.r2cloud.jradio.ao40;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class Ao40Header {

	private int id;
	private int frameType;
	
	public Ao40Header(BitInputStream dis) throws IOException {
		id = dis.readUnsignedInt(2);
		if( id == 0b11 ) {
			frameType = dis.readUnsignedInt(6);
			id = (dis.readUnsignedInt(6)) << 2;
			frameType = (dis.readUnsignedInt(2) << 6) | frameType;
		} else {
			frameType = dis.readUnsignedInt(6);
		}
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getFrameType() {
		return frameType;
	}

	public void setFrameType(int frameType) {
		this.frameType = frameType;
	}
	
}
