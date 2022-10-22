package ru.r2cloud.jradio.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class BitInputStream {

	private static final int MAX_UNSIGNED_LONG = 63;

	private final DataInputStream dis;
	private int spareBitsInCurrentByte;
	private int current;

	public BitInputStream(DataInputStream dis) {
		this.dis = dis;
	}

	public BitInputStream(byte[] data) {
		this(new DataInputStream(new ByteArrayInputStream(data)));
	}

	public long readUnsignedLong(int numberOfBits) throws IOException {
		if (numberOfBits > MAX_UNSIGNED_LONG) {
			throw new IllegalArgumentException("unsigned long max length is 63");
		}
		long result = 0;
		int bitsAdded = 0;
		while (numberOfBits != bitsAdded) {
			int cur;
			if (spareBitsInCurrentByte == 0) {
				cur = dis.readUnsignedByte();
				spareBitsInCurrentByte = 8;
			} else {
				cur = current;
			}

			int toAdd = numberOfBits - bitsAdded;
			if (toAdd >= spareBitsInCurrentByte) {
				result = result << spareBitsInCurrentByte;
				result |= cur;
				bitsAdded += spareBitsInCurrentByte;
				spareBitsInCurrentByte = 0;
			} else {
				int nextSpare = spareBitsInCurrentByte - toAdd;
				result = result << toAdd;
				result |= cur >> nextSpare;
				current = ((cur << (8 - nextSpare) & 0xFF) >> (8 - nextSpare));
				spareBitsInCurrentByte = nextSpare;
				bitsAdded += toAdd;
			}

		}

		return result;
	}

	public void skipBits(int numberOfBits) throws IOException {
		int remaining = numberOfBits;
		while (remaining > 0) {
			int toSkip;
			if (remaining > MAX_UNSIGNED_LONG) {
				toSkip = MAX_UNSIGNED_LONG;
			} else {
				toSkip = remaining;
			}
			readUnsignedLong(toSkip);
			remaining = remaining - toSkip;
		}
	}

	public int available() throws IOException {
		return dis.available();
	}

	public int readUnsignedInt(int numberOfBits) throws IOException {
		if (numberOfBits > 31) {
			throw new IllegalArgumentException("unsigned int max length is 31");
		}
		return (int) readUnsignedLong(numberOfBits);
	}

	public boolean readBoolean() throws IOException {
		int result = readUnsignedInt(1);
		return result > 0;
	}

	public int readUnsignedShort() throws IOException {
		return readUnsignedInt(16);
	}

	public short readShort() throws IOException {
		return (short) readUnsignedInt(16);
	}

	public int readUnsignedByte() throws IOException {
		return readUnsignedInt(8);
	}

	public byte readByte() throws IOException {
		return (byte) readUnsignedInt(8);
	}

	public void readFully(byte[] buf) throws IOException {
		dis.readFully(buf);
	}

	public int readInt() throws IOException {
		return (int) readUnsignedLong(32);
	}

	public float readFloat() throws IOException {
		return dis.readFloat();
	}

	public long readLong() throws IOException {
		long ch1 = dis.read();
		long ch2 = dis.read();
		long ch3 = dis.read();
		long ch4 = dis.read();
		long ch5 = dis.read();
		long ch6 = dis.read();
		long ch7 = dis.read();
		long ch8 = dis.read();
		if ((ch1 | ch2 | ch3 | ch4 | ch5 | ch6 | ch7 | ch8) < 0)
			throw new EOFException();
		return ((ch1 << 56) | (ch2 << 48) | (ch3 << 32) | (ch4 << 24) | (ch5 << 24) | (ch6 << 16) | (ch7 << 8) | ch8) & 0xFFFFFFFFL;
	}

}
