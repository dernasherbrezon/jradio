package ru.r2cloud.jradio.util;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class LittleEndianDataInputStream implements DataInput {

	private byte[] readBuffer = new byte[8];
	private final DataInputStream dis;

	public LittleEndianDataInputStream(DataInputStream dis) {
		this.dis = dis;
	}

	public int read(byte[] b) throws IOException {
		return dis.read(b);
	}

	@Override
	public final int readUnsignedByte() throws IOException {
		return dis.readUnsignedByte();
	}

	@Override
	public final int readUnsignedShort() throws IOException {
		int ch1 = dis.read();
		int ch2 = dis.read();
		if ((ch1 | ch2) < 0)
			throw new EOFException();
		return (ch2 << 8) + ch1;
	}

	public final long readUnsignedInt() throws IOException {
		return readUnsignedInt(dis);
	}

	public static final long readUnsignedInt(DataInputStream dis) throws IOException {
		int ch1 = dis.read();
		int ch2 = dis.read();
		int ch3 = dis.read();
		int ch4 = dis.read();
		if ((ch1 | ch2 | ch3 | ch4) < 0)
			throw new EOFException();
		return ((ch4 << 24) | (ch3 << 16) | (ch2 << 8) | ch1) & 0xFFFFFFFFL;
	}

	@Override
	public final float readFloat() throws IOException {
		return dis.readFloat();
	}

	@Override
	public final short readShort() throws IOException {
		return readShort(dis);
	}

	public static final short readShort(DataInputStream dis) throws IOException {
		int ch1 = dis.read();
		int ch2 = dis.read();
		if ((ch1 | ch2) < 0)
			throw new EOFException();
		return (short) ((ch2 << 8) + ch1);
	}

	@Override
	public final int skipBytes(int n) throws IOException {
		return dis.skipBytes(n);
	}

	@Override
	public final long readLong() throws IOException {
		dis.readFully(readBuffer, 0, 8);
		return ((long) readBuffer[7] << 56) + ((long) (readBuffer[6] & 255) << 48) + ((long) (readBuffer[5] & 255) << 40) + ((long) (readBuffer[4] & 255) << 32) + ((long) (readBuffer[3] & 255) << 24) + ((readBuffer[2] & 255) << 16) + ((readBuffer[1] & 255) << 8) + (readBuffer[0] & 255);
	}

	@Override
	public final byte readByte() throws IOException {
		return dis.readByte();
	}

	@Override
	public void readFully(byte[] b) throws IOException {
		dis.readFully(b);
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		dis.readFully(b, off, len);
	}

	@Override
	public boolean readBoolean() throws IOException {
		return dis.readBoolean();
	}

	@Override
	public char readChar() throws IOException {
		return dis.readChar();
	}

	@Override
	public int readInt() throws IOException {
		int ch1 = dis.read();
		int ch2 = dis.read();
		int ch3 = dis.read();
		int ch4 = dis.read();
		if ((ch1 | ch2 | ch3 | ch4) < 0)
			throw new EOFException();
		return ((ch4 << 24) + (ch3 << 16) + (ch2 << 8) + ch1);
	}

	@Override
	public double readDouble() throws IOException {
		return Double.longBitsToDouble(readLong());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated see java.io.DataInputStream.readLine
	 */
	@Override
	@Deprecated
	public String readLine() throws IOException {
		return dis.readLine();
	}

	@Override
	public String readUTF() throws IOException {
		return dis.readUTF();
	}

	public int available() throws IOException {
		return dis.available();
	}
}
