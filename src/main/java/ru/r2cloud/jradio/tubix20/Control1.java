package ru.r2cloud.jradio.tubix20;

public class Control1 {

	private MessageType type;
	private int numberOfBlocks;
	private int numberOfErrors;

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}

	public void setNumberOfBlocks(int numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}

	public int getNumberOfErrors() {
		return numberOfErrors;
	}

	public void setNumberOfErrors(int numberOfErrors) {
		this.numberOfErrors = numberOfErrors;
	}

}
