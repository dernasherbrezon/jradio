package ru.r2cloud.jradio.ax25;

import java.io.DataInputStream;
import java.io.IOException;

public class Header {

	private AddressSubfield destinationAddress;
	private AddressSubfield sourceAddress;
	private FrameType frameType;
	private int sendSequenceNumber;
	private int receiveSequenceNumber;
	private SFrameControlType sControlType;
	private UFrameControlType uControlType;
	private int pid;

	public Header(DataInputStream dis) throws IOException {
		destinationAddress = new AddressSubfield(dis);
		sourceAddress = new AddressSubfield(dis);
		int controlBits = dis.readUnsignedByte();
		if ((controlBits & 0b1) == 0) {
			frameType = FrameType.I;
			pid = dis.readUnsignedByte();
		} else if ((controlBits & 0b11) == 0b01) {
			frameType = FrameType.S;
			sControlType = SFrameControlType.valueOfCode((controlBits & 0b0000_1111));
		} else if ((controlBits & 0b11) == 0b11) {
			frameType = FrameType.U;
			uControlType = UFrameControlType.valueOfCode(controlBits);
			if (uControlType.equals(UFrameControlType.UI)) {
				pid = dis.readUnsignedByte();
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public AddressSubfield getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(AddressSubfield destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public AddressSubfield getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(AddressSubfield sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public FrameType getFrameType() {
		return frameType;
	}

	public void setFrameType(FrameType frameType) {
		this.frameType = frameType;
	}

	public int getSendSequenceNumber() {
		return sendSequenceNumber;
	}

	public void setSendSequenceNumber(int sendSequenceNumber) {
		this.sendSequenceNumber = sendSequenceNumber;
	}

	public int getReceiveSequenceNumber() {
		return receiveSequenceNumber;
	}

	public void setReceiveSequenceNumber(int receiveSequenceNumber) {
		this.receiveSequenceNumber = receiveSequenceNumber;
	}

	public SFrameControlType getsControlType() {
		return sControlType;
	}

	public void setsControlType(SFrameControlType sControlType) {
		this.sControlType = sControlType;
	}

	public UFrameControlType getuControlType() {
		return uControlType;
	}

	public void setuControlType(UFrameControlType uControlType) {
		this.uControlType = uControlType;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
