package ru.r2cloud.jradio.ax25;

import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.fec.ccsds.UncorrectableException;

public class Header {

	public static final int LENGTH_BYTES = 16;
	private AddressSubfield destinationAddress;
	private AddressSubfield sourceAddress;
	private AddressSubfield repeaterAddress;
	private AddressSubfield repeater2Address;
	private FrameType frameType;
	private int sendSequenceNumber;
	private int receiveSequenceNumber;
	private SFrameControlType sControlType;
	private UFrameControlType uControlType;
	private int pid;

	public Header() {
		// do nothing
	}

	public Header(DataInputStream dis) throws IOException, UncorrectableException {
		this(dis, true);
	}

	public Header(DataInputStream dis, boolean checkForRepeater) throws IOException, UncorrectableException {
		destinationAddress = new AddressSubfield(dis);
		sourceAddress = new AddressSubfield(dis);
		if (checkForRepeater) {
			if (sourceAddress.getExtensionBit() == 0) {
				repeaterAddress = new AddressSubfield(dis);
			}
			if (repeaterAddress != null && repeaterAddress.getExtensionBit() == 0) {
				repeater2Address = new AddressSubfield(dis);
			}
		}
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
			throw new IOException("unsupported control bits:" + controlBits);
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

	public AddressSubfield getRepeaterAddress() {
		return repeaterAddress;
	}

	public void setRepeaterAddress(AddressSubfield repeaterAddress) {
		this.repeaterAddress = repeaterAddress;
	}

	public AddressSubfield getRepeater2Address() {
		return repeater2Address;
	}

	public void setRepeater2Address(AddressSubfield repeater2Address) {
		this.repeater2Address = repeater2Address;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(sourceAddress).append(" To ").append(destinationAddress);
		if (repeaterAddress != null) {
			result.append(" Via ").append(repeaterAddress);
			if (repeater2Address != null) {
				result.append(", ").append(repeater2Address);
			}
		}
		result.append(" <");
		if (uControlType != null) {
			result.append(uControlType);
		} else {
			result.append(frameType);
		}
		result.append(" Pid=").append(pid);
		result.append(">");
		return result.toString();
	}

}
