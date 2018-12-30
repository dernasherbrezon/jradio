package ru.r2cloud.jradio.eseo;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import ru.r2cloud.jradio.Externalizable;

public class EseoBeacon implements Externalizable {

	public static final String FLAG = "0111111001111110";

	private FrameType frame;
	private AddressSubfield destinationAddress;
	private AddressSubfield sourceAddress;

	// "I" frame control data
	private int sendSequenceNumber;
	private int receiveSequenceNumber;
	private int pid;
	private int beaconType;
	private Type1 type1;

	// "U" frame
	private UFrameControlType uFrameType;

	// "S" frame
	private SFrameControlType sFrameType;

	private long beginSample;
	private long beginMillis;
	private byte[] rawData;

	@Override
	public void readExternal(byte[] data) throws IOException {
		this.rawData = data;
		if (data.length == 21) {
			frame = FrameType.U;
		} else if (data.length == 22) {
			frame = FrameType.S;
		} else {
			frame = FrameType.I;
		}
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		destinationAddress = new AddressSubfield(dis);
		sourceAddress = new AddressSubfield(dis);
		switch (frame) {
		case I:
			sendSequenceNumber = dis.readUnsignedByte() >> 1;
			receiveSequenceNumber = dis.readUnsignedByte() >> 1;
			pid = dis.readUnsignedByte();
			beaconType = dis.readUnsignedByte();
			dis.skipBytes(1);
			switch (beaconType) {
			case 3:
				type1 = new Type1(dis);
				break;

			default:
				throw new IllegalArgumentException("unsupported beacon type: " + beaconType);
			}
			break;
		case U:
			uFrameType = UFrameControlType.valueOfCode(dis.readUnsignedByte());
			break;
		case S:
			sFrameType = SFrameControlType.valueOfCode(dis.readUnsignedByte());
			receiveSequenceNumber = dis.readUnsignedByte() >> 1;
			break;
		default:
			throw new IllegalArgumentException("unsupported frame type: " + frame);
		}
	}

	public long getBeginSample() {
		return beginSample;
	}

	public void setBeginSample(long beginSample) {
		this.beginSample = beginSample;
	}

	public long getBeginMillis() {
		return beginMillis;
	}

	public void setBeginMillis(long beginMillis) {
		this.beginMillis = beginMillis;
	}

	public byte[] getRawData() {
		return rawData;
	}

	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	public FrameType getFrame() {
		return frame;
	}

	public void setFrame(FrameType frame) {
		this.frame = frame;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getBeaconType() {
		return beaconType;
	}

	public void setBeaconType(int beaconType) {
		this.beaconType = beaconType;
	}

	public Type1 getType1() {
		return type1;
	}

	public void setType1(Type1 type1) {
		this.type1 = type1;
	}

	public UFrameControlType getuFrameType() {
		return uFrameType;
	}

	public void setuFrameType(UFrameControlType uFrameType) {
		this.uFrameType = uFrameType;
	}

	public SFrameControlType getsFrameType() {
		return sFrameType;
	}

	public void setsFrameType(SFrameControlType sFrameType) {
		this.sFrameType = sFrameType;
	}

}
