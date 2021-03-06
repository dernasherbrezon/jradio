package ru.r2cloud.jradio.util;

import java.io.IOException;
import java.io.InputStream;

public class LsbBitInputStream {

	private final InputStream stream;
	private int buffer;
	private int bitsleft = 0;

	public boolean readBit() throws IOException {
		if (this.bitsleft == 0) {
			this.buffer = this.stream.read();
			this.bitsleft = 8;
		}
		--this.bitsleft;
		return (this.buffer & 1 << 7 - this.bitsleft) != 0;
	}

	public int available() throws IOException {
		return this.stream.available() * 8;
	}

	public int readBitsAsInt(int n) throws IOException {
		int r = 0;
		int mask = 1;
		for (int i = 0; i < n; ++i) {
			if (this.readBit()) {
				r |= mask;
			}
			mask <<= 1;
		}
		return r;
	}

	public String readBitsAsString(int n) throws IOException {
		String[] wb = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		int rest = n % 4;
		int resterend = n - rest;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < resterend / 4; ++i) {
			int tbHexed = this.readBitsAsInt(4);
			str.insert(0, wb[tbHexed]);
		}
		if (rest != 0) {
			int tbHexed = this.readBitsAsInt(rest);
			str.insert(0, wb[tbHexed]);
		}
		return str.toString();
	}
	
	public void readFully(byte[] data) throws IOException {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte)readBitsAsInt(8);
		}
	}

	public LsbBitInputStream(InputStream istream) {
		this.stream = istream;
	}
}
