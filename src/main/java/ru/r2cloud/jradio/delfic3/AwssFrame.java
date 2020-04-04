package ru.r2cloud.jradio.delfic3;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class AwssFrame {

	private AwssBlock zm;
	private AwssBlock zp;
	private int zmTimestamp;
	private int zpTimestamp;

	public AwssFrame() {
		// do nothing
	}

	public AwssFrame(LsbBitInputStream bis) throws IOException {
		zm = new AwssBlock(bis);
		zp = new AwssBlock(bis);
		zmTimestamp = bis.readBitsAsInt(4);
		zpTimestamp = bis.readBitsAsInt(4);
	}

	public AwssBlock getZm() {
		return zm;
	}

	public void setZm(AwssBlock zm) {
		this.zm = zm;
	}

	public AwssBlock getZp() {
		return zp;
	}

	public void setZp(AwssBlock zp) {
		this.zp = zp;
	}

	public int getZmTimestamp() {
		return zmTimestamp;
	}

	public void setZmTimestamp(int zmTimestamp) {
		this.zmTimestamp = zmTimestamp;
	}

	public int getZpTimestamp() {
		return zpTimestamp;
	}

	public void setZpTimestamp(int zpTimestamp) {
		this.zpTimestamp = zpTimestamp;
	}

}
