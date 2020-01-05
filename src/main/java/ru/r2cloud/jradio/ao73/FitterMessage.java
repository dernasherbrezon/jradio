package ru.r2cloud.jradio.ao73;

public class FitterMessage {

	private final FrameType frameType;
	private final String message;
	private final int sequenceNumber;

	public FitterMessage(FrameType frameType, String message, int sequenceNumber) {
		this.frameType = frameType;
		this.message = message;
		this.sequenceNumber = sequenceNumber;
	}

	public FrameType getFrameType() {
		return frameType;
	}

	public String getMessage() {
		return message;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

}
