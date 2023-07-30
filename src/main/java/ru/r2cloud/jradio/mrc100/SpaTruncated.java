package ru.r2cloud.jradio.mrc100;

import java.io.IOException;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class SpaTruncated {

	private long startFrequency;
	private long stepFrequency;
	private long timestamp;
	private int rbw;
	private int blockNumber;
	private int binCount;
	private byte[] payload;

	public SpaTruncated() {
		// do nothing
	}

	public SpaTruncated(LittleEndianDataInputStream dis) throws IOException {
		startFrequency = dis.readUnsignedInt();
		stepFrequency = dis.readUnsignedInt();
		timestamp = dis.readUnsignedInt();
		rbw = dis.readUnsignedByte();
		blockNumber = dis.readUnsignedByte();
		binCount = dis.readUnsignedByte();
		payload = new byte[binCount];
		dis.readFully(payload);
	}

	public long getStartFrequency() {
		return startFrequency;
	}

	public void setStartFrequency(long startFrequency) {
		this.startFrequency = startFrequency;
	}

	public long getStepFrequency() {
		return stepFrequency;
	}

	public void setStepFrequency(long stepFrequency) {
		this.stepFrequency = stepFrequency;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getRbw() {
		return rbw;
	}

	public void setRbw(int rbw) {
		this.rbw = rbw;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public int getBinCount() {
		return binCount;
	}

	public void setBinCount(int binCount) {
		this.binCount = binCount;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
