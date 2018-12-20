package ru.r2cloud.jradio.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class BitInputStream {

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
		if (numberOfBits > 63) {
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
		readUnsignedLong(numberOfBits);
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

	public short readShort() throws IOException {
		return (short) readUnsignedInt(16);
	}

}
