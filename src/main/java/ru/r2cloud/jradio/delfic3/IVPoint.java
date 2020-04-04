package ru.r2cloud.jradio.delfic3;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class IVPoint {

	private int i;
	private int v;

	public IVPoint() {
		// do nothing
	}

	public IVPoint(LsbBitInputStream bis) throws IOException {
		i = bis.readBitsAsInt(8) * 4;
		v = bis.readBitsAsInt(8) * 8;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

}
