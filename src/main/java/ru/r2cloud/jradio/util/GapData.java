package ru.r2cloud.jradio.util;

public class GapData {

	private final int[] lengths;
	private final byte[][] chunks;

	private int written = 0;
	private int nonEmptyBlocks;

	public GapData(int numberOfChunks) {
		chunks = new byte[numberOfChunks][];
		lengths = new int[numberOfChunks];
	}

	public synchronized void write(byte[] b) {
		chunks[written] = b;
		lengths[written] = b.length;
		written++;
		nonEmptyBlocks++;
	}

	public synchronized void gap(int numberOfBytes) {
		chunks[written] = null;
		lengths[written] = numberOfBytes;
		written++;
	}
	
	public byte[][] getChunks() {
		return chunks;
	}
	
	public int[] getLengths() {
		return lengths;
	}

	public int getNonEmptyBlocks() {
		return nonEmptyBlocks;
	}
}
