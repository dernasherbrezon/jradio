package ru.r2cloud.jradio.ao73;

public class FitterMessageBatch {

	private final byte[] data;
	private final int sequenceNumber;

	public FitterMessageBatch(int sequenceNumber, byte[] data) {
		this.sequenceNumber = sequenceNumber;
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}
	
	public int getSequenceNumber() {
		return sequenceNumber;
	}
}
