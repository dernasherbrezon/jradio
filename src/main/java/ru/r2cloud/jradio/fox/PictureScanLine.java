package ru.r2cloud.jradio.fox;

import java.io.IOException;

import ru.r2cloud.jradio.util.LsbBitInputStream;

public class PictureScanLine {

	private int counter;
	private int lineNumber;
	private byte[] data;

	public PictureScanLine() {
		// do nothing
	}

	public PictureScanLine(LsbBitInputStream dis) throws IOException {
		dis.readBitsAsInt(6 * 8); // skip preamble
		counter = dis.readBitsAsInt(8);
		int raw = dis.readBitsAsInt(16);
		int length = (raw & 0x3FF);
		lineNumber = (raw >> 12) & 0x3F;

		data = new byte[length];
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) dis.readBitsAsInt(8);
		}
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
