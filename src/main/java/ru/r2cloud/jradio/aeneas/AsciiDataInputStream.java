package ru.r2cloud.jradio.aeneas;

import java.io.DataInputStream;
import java.io.IOException;

public class AsciiDataInputStream {

	private final DataInputStream dis;

	public AsciiDataInputStream(DataInputStream dis) {
		this.dis = dis;
	}

	public int readUnsignedShortLittleEndian() throws IOException {
		int ch1 = readUnsignedByte();
		int ch2 = readUnsignedByte();
		return ((ch2 << 8) + ch1);
	}

	public int readUnsignedShort() throws IOException {
		int ch1 = readUnsignedByte();
		int ch2 = readUnsignedByte();
		return ((ch1 << 8) + ch2);
	}

	public int readUnsignedByte() throws IOException {
		char firstDigit = (char) (dis.readUnsignedByte() & 0xFF);
		char secondDigit = (char) (dis.readUnsignedByte() & 0xFF);
		String str = "0x" + firstDigit + secondDigit;
		return Integer.decode(str).byteValue() & 0xFF;
	}

	public int skipBytes(int n) throws IOException {
		return dis.skipBytes(n * 2);
	}

	public final void readFully(byte b[]) throws IOException {
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) readUnsignedByte();
		}
	}
}
