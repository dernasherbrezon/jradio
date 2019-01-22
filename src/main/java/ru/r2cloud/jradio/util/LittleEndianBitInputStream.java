package ru.r2cloud.jradio.util;

import java.io.EOFException;
import java.io.IOException;

public class LittleEndianBitInputStream {

	private final LittleEndianDataInputStream dis;
	private int spareBitsInCurrentByte;
	private int current;

	public LittleEndianBitInputStream(LittleEndianDataInputStream dis) {
		this.dis = dis;
	}

	public long readUnsignedLong(int numberOfBits) throws IOException {
		if (numberOfBits > 63) {
			throw new IllegalArgumentException("unsigned long max length is 63");
		}
		long result = 0;
		int bitsAdded = 0;
		while (numberOfBits != bitsAdded) {
			long cur;
			if (spareBitsInCurrentByte == 0) {
				cur = dis.readUnsignedByte();
				spareBitsInCurrentByte = 8;
			} else {
				cur = current;
			}

			int toAdd = numberOfBits - bitsAdded;
			if (toAdd >= spareBitsInCurrentByte) {
				result |= (cur << bitsAdded);
				bitsAdded += spareBitsInCurrentByte;
				spareBitsInCurrentByte = 0;
			} else {
				int nextSpare = spareBitsInCurrentByte - toAdd;
				result |= ((((cur << (8 - toAdd)) & 0xFF) >> (8 - toAdd)) << bitsAdded);
				current = (int) (cur >> toAdd);
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

	public short readShortTwosComplement() throws IOException {
		return (short)readIntTwosComplement(16);
	}
	
	public long readLongTwosComplement(int numberOfBits) throws IOException {
		long val = readUnsignedLong(numberOfBits);
		if ((val & (1 << (numberOfBits - 1))) != 0) { // if sign bit is set e.g., 8bit: 128-255
			val = val - (1 << numberOfBits); // compute negative value
		}
		return val;
	}
	
	public int readIntTwosComplement(int numberOfBits) throws IOException {
		int val = readUnsignedInt(numberOfBits);
		if ((val & (1 << (numberOfBits - 1))) != 0) { // if sign bit is set e.g., 8bit: 128-255
			val = val - (1 << numberOfBits); // compute negative value
		}
		return val; // return positive value as is
	}

	public long readUnsignedInt() throws IOException {
		return readUnsignedLong(32);
	}

	public int readUnsignedByte() throws IOException {
		return readUnsignedInt(8);
	}

	public int readUnsignedShort() throws IOException {
		return readUnsignedInt(16);
	}

	public long readLong() throws IOException {
		int ch1 = readUnsignedInt(8);
		int ch2 = readUnsignedInt(8);
		int ch3 = readUnsignedInt(8);
		int ch4 = readUnsignedInt(8);
		int ch5 = readUnsignedInt(8);
		int ch6 = readUnsignedInt(8);
		int ch7 = readUnsignedInt(8);
		int ch8 = readUnsignedInt(8);
		if ((ch1 | ch2 | ch3 | ch4 | ch5 | ch6 | ch7 | ch8) < 0)
			throw new EOFException();
		return ((ch8 << 56) | (ch7 << 48) | (ch6 << 40) | (ch4 << 32) | (ch4 << 24) | (ch3 << 16) | (ch2 << 8) | ch1);
	}
}
