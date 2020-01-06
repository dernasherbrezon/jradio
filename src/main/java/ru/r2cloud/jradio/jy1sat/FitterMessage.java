package ru.r2cloud.jradio.jy1sat;

public class FitterMessage {

	private int frameIndex;
	private final String message;
	private final int sequenceNumber;

	public FitterMessage(int frameIndex, String message, int sequenceNumber) {
		this.frameIndex = frameIndex;
		this.message = message;
		this.sequenceNumber = sequenceNumber;
	}

	public int getFrameIndex() {
		return frameIndex;
	}

	public String getMessage() {
		return message;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

}
