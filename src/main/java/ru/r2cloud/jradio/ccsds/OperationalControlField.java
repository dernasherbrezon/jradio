package ru.r2cloud.jradio.ccsds;

import java.io.IOException;

import ru.r2cloud.jradio.util.BitInputStream;

public class OperationalControlField {
	
	public static final int LENGTH = 4; 

	private int type;

	public OperationalControlField() {
		// do nothing
	}

	public OperationalControlField(BitInputStream bis) throws IOException {
		type = bis.readUnsignedInt(1);
		// do not parse it for now
		bis.skipBits(4 * 8 - 1);
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

}
