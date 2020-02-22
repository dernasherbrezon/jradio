package ru.r2cloud.jradio.util;

import java.io.EOFException;
import java.io.IOException;

public class GapDataInputStream {

	private final GapData source;

	private int currentChunk = 0;
	private int indexWithinChunk;

	public GapDataInputStream(GapData source) {
		this.source = source;
	}

	public Integer readUnsignedByte() throws IOException {
		if (currentChunk >= source.getChunks().length) {
			throw new EOFException();
		}
		Integer result;
		if (source.getChunks()[currentChunk] == null) {
			result = null;
		} else {
			result = source.getChunks()[currentChunk][indexWithinChunk] & 0xFF;
		}
		advance(1);
		return result;
	}

	public Integer readUnsignedShort() throws IOException {
		Integer ch1 = readUnsignedByte();
		Integer ch2 = readUnsignedByte();
		if (ch1 == null || ch2 == null) {
			return null;
		}
		return (ch1 << 8) + ch2;
	}

	public Short readLittleEndianShort() throws IOException {
		Integer ch1 = readUnsignedByte();
		Integer ch2 = readUnsignedByte();
		if (ch1 == null || ch2 == null) {
			return null;
		}
		return (short) ((ch2 << 8) + ch1);
	}

	public Long readLittleEndianUnsignedInt() throws IOException {
		Integer ch1 = readUnsignedByte();
		Integer ch2 = readUnsignedByte();
		Integer ch3 = readUnsignedByte();
		Integer ch4 = readUnsignedByte();
		if (ch1 == null || ch2 == null || ch3 == null || ch4 == null) {
			return null;
		}
		return ((ch4 << 24) | (ch3 << 16) | (ch2 << 8) | ch1) & 0xFFFFFFFFL;
	}

	public void skipBytes(int n) throws IOException {
		advance(n);
	}

	private void advance(int numberOfBytes) throws IOException {
		indexWithinChunk += numberOfBytes;
		while (indexWithinChunk >= source.getLengths()[currentChunk]) {
			indexWithinChunk = indexWithinChunk - source.getLengths()[currentChunk];
			currentChunk++;
			if (currentChunk >= source.getChunks().length) {
				// pointing to the end
				if (indexWithinChunk == 0) {
					return;
				}
				throw new EOFException();
			}
		}
	}

}
