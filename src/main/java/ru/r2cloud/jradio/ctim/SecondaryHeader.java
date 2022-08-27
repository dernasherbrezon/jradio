package ru.r2cloud.jradio.ctim;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.util.StreamUtils;

public class SecondaryHeader {

	private long shCoarse;
	private int shFine;

	public SecondaryHeader() {
		// do nothing
	}

	public SecondaryHeader(DataInputStream dis) throws IOException {
		shCoarse = StreamUtils.readUnsignedInt(dis);
		shFine = dis.readUnsignedShort();
	}

	public long getShCoarse() {
		return shCoarse;
	}

	public void setShCoarse(long shCoarse) {
		this.shCoarse = shCoarse;
	}

	public int getShFine() {
		return shFine;
	}

	public void setShFine(int shFine) {
		this.shFine = shFine;
	}

}
